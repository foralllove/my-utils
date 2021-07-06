package pers.util.xml.annotation;

import org.dom4j.*;
import pers.util.xml.model.Dom4jXmlDemo;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Dom4jXmlUtils
 *
 * @author: hh
 * @since: 2021/7/6 1:32
 */
public class Dom4jXmlUtils {
    private Dom4jXmlUtils() {
        throw new IllegalArgumentException("工具类");
    }

    public static <T> T xmlToBean(String xmlStr, Class<T> tClass) throws Exception {
        if (xmlStr == null || "".equals(xmlStr) || tClass == null) {
            return null;
        }
        //是否标注可转换
        if (!tClass.isAnnotationPresent(Dom4jXml.class)) {
            return null;
        }
        T t = null;
        //获取注解
        Dom4jXml dom4jXml = tClass.getAnnotation(Dom4jXml.class);
        Document doc = DocumentHelper.parseText(xmlStr);
        Element rootElement = doc.getRootElement();
        // 根属性转换
        Class<?> rootAttributeClass = dom4jXml.namespace();
        Object rootAttribute = null;
        if (rootAttributeClass != void.class) {
            //属性转换的对象
            rootAttribute = namespace(rootElement, rootAttributeClass);
        }

        t = createInstanceAndField(rootElement, tClass);
        try {
            Field field = tClass.getDeclaredField("namespace");
            field.setAccessible(true);
            field.set(t, rootAttribute);
        } catch (NoSuchFieldException exception) {
            //无根
        }
        return t;
    }


    public static <T> T createInstanceAndField(Element element, Class<T> tClass) throws Exception {
        T t = tClass.newInstance();
        List<Field> allField = getAllField(tClass);
        for (Field field : allField) {
            String name = field.getName();
            //节点
            Element nodeElement = element.element(name);
            Dom4jFieldXml dom4jFieldXml = field.getAnnotation(Dom4jFieldXml.class);
            if (dom4jFieldXml != null && !"".equals(dom4jFieldXml.name())) {
                nodeElement = element.element(dom4jFieldXml.name());
            }
            if (nodeElement == null) {
                continue;
            }

            //设置元素值
            if (nodeElement.elements().isEmpty()) {
                field.setAccessible(true);
                field.set(t, nodeElement.getData());
                continue;
            }
            Object fieldValue = createInstanceAndField(nodeElement, field.getDeclaringClass());
            field.setAccessible(true);
            field.set(t, fieldValue);
        }
        return t;
    }

    private static <T> T namespace(Element element, Class<T> tClass) throws Exception {
        T t = tClass.newInstance();
        List<Field> allFieldList = getAllField(tClass);
        for (Field field : allFieldList) {
            Namespace namespace = element.getNamespaceForPrefix(field.getName());

            Dom4jFieldXml dom4jFieldXml = field.getAnnotation(Dom4jFieldXml.class);
            if (dom4jFieldXml != null) {
                String name = dom4jFieldXml.name();
                namespace = element.getNamespaceForPrefix(name);
            }


            if (namespace != null) {
                //设置属性
                field.setAccessible(true);
                field.set(t, namespace.getURI());
            }
        }
        return t;
    }

    /**
     * 属性对象处理
     * @param element 属性节点
     * @param tClass 对象
     * @param <T> 属性类型
     * @return 属性对象
     * @throws Exception 异常
     */
    private static <T> T attribute(Element element, Class<T> tClass) throws Exception {
        //获取属性
        List<Field> allFieldList = getAllField(tClass);

        Map<String, String> allAttributeMap = getAllAttribute(element);

        T t = tClass.newInstance();
        for (Field field : allFieldList) {
            //原属性
            String value = allAttributeMap.get(field.getName());

            Dom4jFieldXml dom4jFieldXml = field.getAnnotation(Dom4jFieldXml.class);
            if (dom4jFieldXml != null) {
                String name = dom4jFieldXml.name();
                if (!"".equals(name)) {
                    value = allAttributeMap.get(name);
                }
            }
            if (value != null) {
                //设置属性
                field.setAccessible(true);
                field.set(t, value);
            }

        }
        return t;
    }

    private static Map<String, String> getAllAttribute(Element element) {
        Map<String, String> map = new HashMap<>(element.attributeCount());
        //属性map
        for (Iterator it = element.attributeIterator(); it.hasNext(); ) {
            Attribute attribute = (Attribute) it.next();
            String name = attribute.getName();
            String text = attribute.getValue();
            map.put(name, text);
        }
        return map;
    }

    private static List<Field> getAllField(Class<?> tClass) {
        Class<?> clazz = tClass;
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    public static void main(String[] args) throws Exception {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
                "<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\" xmlns:x=\"urn:gsma:params:xml:ns:rcs:rcs:up:fthttpext\">" +
                "<xid>xid1234</xid>" +
                "<key>wode</key>" +
                "</file>";
        Dom4jXmlDemo ww = xmlToBean(str, Dom4jXmlDemo.class);
        System.out.println("gg");
    }
}
