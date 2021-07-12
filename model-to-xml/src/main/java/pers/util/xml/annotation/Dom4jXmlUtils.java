package pers.util.xml.annotation;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import pers.util.xml.exception.Dom4jException;
import pers.util.xml.model.Dom4jXmlAttribute;
import pers.util.xml.model.Dom4jXmlDemo;
import pers.util.xml.model.Dom4jXmlField;
import pers.util.xml.model.Dom4jXmlNamespace;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Dom4jXmlUtils
 *
 * @author: hh
 * @since: 2021/7/6 1:32
 */
@Slf4j
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
            rootAttribute = readNamespace(rootElement, rootAttributeClass);
        }
        //节点处理
        t = readInstanceAndField(rootElement, tClass);
        try {
            Field field = tClass.getDeclaredField("readNamespace");
            field.setAccessible(true);
            field.set(t, rootAttribute);
        } catch (NoSuchFieldException exception) {
            //无根
        }
        return t;
    }


    public static <T> T readInstanceAndField(Element element, Class<T> tClass) throws Exception {
        T t = tClass.newInstance();

        List<Field> allField = getAllField(tClass);
        for (Field field : allField) {
            String name = field.getName();
            boolean nameFlag = false;
            boolean attributeFlag = false;
            Dom4jXml dom4jFieldXml = field.getAnnotation(Dom4jXml.class);
            if (dom4jFieldXml != null && !"".equals(dom4jFieldXml.name())) {
                nameFlag = true;
            }
            if (dom4jFieldXml != null && dom4jFieldXml.attribute() != void.class) {
                attributeFlag = true;
            }
            if (field.getType() == List.class) {
                List<Element> elementList = element.elements(name);
                if (nameFlag) {
                    elementList = element.elements(dom4jFieldXml.name());
                }
                if (elementList == null || elementList.isEmpty()) {
                    continue;
                }

                List fieldValue = new ArrayList();
                List attributeValue = new ArrayList();
                for (Element ele : elementList) {
                    if (attributeFlag) {
                        Object nodeAttribute = readAttribute(ele, dom4jFieldXml.attribute());
                        attributeValue.add(nodeAttribute);
                    }

                    if (ele.elements().isEmpty()) {
                        fieldValue.add(ele.getData());
                        continue;
                    }
                    Class<?> eClass = (Class<?>) ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0];
                    Object nextValue = readInstanceAndField(ele, eClass);
                    fieldValue.add(nextValue);
                }
                Field declaredField = tClass.getDeclaredField(name + "Attribute");
                declaredField.setAccessible(true);
                declaredField.set(t, attributeValue);

                field.setAccessible(true);
                field.set(t, fieldValue);
                continue;
            }
            //节点
            Element nodeElement = element.element(name);
            if (nameFlag) {
                nodeElement = element.element(dom4jFieldXml.name());
            }
            if (nodeElement == null) {
                continue;
            }
            //设置根属性
            if (attributeFlag) {
                Object nodeAttribute = readAttribute(nodeElement, dom4jFieldXml.attribute());
                Field declaredField = tClass.getDeclaredField(name + "Attribute");
                declaredField.setAccessible(true);
                declaredField.set(t, nodeAttribute);
            }

            //设置元素值
            if (nodeElement.elements().isEmpty()) {
                field.setAccessible(true);
                field.set(t, nodeElement.getData());
                continue;
            }
            Object fieldValue = readInstanceAndField(nodeElement, field.getType());
            field.setAccessible(true);
            field.set(t, fieldValue);
        }
        return t;
    }

    private static <T> T readNamespace(Element element, Class<T> tClass) throws Exception {
        T t = tClass.newInstance();
        List<Field> allFieldList = getAllField(tClass);
        for (Field field : allFieldList) {
            Namespace namespace = element.getNamespaceForPrefix(field.getName());

            Dom4jXml dom4jFieldXml = field.getAnnotation(Dom4jXml.class);
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
     *
     * @param element 属性节点
     * @param tClass  对象
     * @param <T>     属性类型
     * @return 属性对象
     * @throws Exception 异常
     */
    private static <T> T readAttribute(Element element, Class<T> tClass) throws Exception {
        //获取属性
        List<Field> allFieldList = getAllField(tClass);

        Map<String, String> allAttributeMap = getAllAttribute(element);

        T t = tClass.newInstance();
        for (Field field : allFieldList) {
            //原属性
            String value = allAttributeMap.get(field.getName());

            Dom4jXml dom4jFieldXml = field.getAnnotation(Dom4jXml.class);
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

    public static String beanToXml(Object t) throws NoSuchFieldException {
        if (t == null) {
            throw new Dom4jException("对象为空,无法转换xml");
        }
        Class<?> tClass = t.getClass();
        //是否标注可转换
        if (!tClass.isAnnotationPresent(Dom4jXml.class)) {
            log.warn(String.format("对象没有Dom4jXml注解%s", tClass.getTypeName()));
            return "";
        }
        //创建xml文档
        Document document = DocumentHelper.createDocument();
        //根节点写入
        Element rootElement = writeRootElement(t, document);
        //根节点写入节点
        writeElement(t, rootElement);
        return document.asXML();
    }

    private static boolean isBaseType(Object tClass) {
        return (tClass instanceof Number) ||
                (tClass instanceof Boolean) ||
                (tClass instanceof String) ||
                (tClass instanceof Date);
    }


    /**
     * 写入xml 根节点
     * @param t 对象值
     * @param document 文档
     * @return 根节点
     */
    private static Element writeRootElement(Object t, Document document) {
        Class<?> tClass = t.getClass();
        Dom4jXml dom4jXml = tClass.getAnnotation(Dom4jXml.class);
        String name = dom4jXml.name();
        Element rootElement;
        //根节点创建命名
        if ("".equals(name)) {
            name = "root";
        }
        QName qName = DocumentHelper.createQName(name, new Namespace("", "urn:gsma:params:xml:ns:rcs:rcs:fthttp"));
        rootElement = document.addElement(qName);

        //命名空间处理
        Class<?> namespaceClass = dom4jXml.namespace();
        if (namespaceClass == void.class) {
            return rootElement;
        }
        Field namespaceField = null;
        try {
            namespaceField = tClass.getDeclaredField("namespace");
        } catch (NoSuchFieldException exception) {
            throw new Dom4jException(String.format("未查询到命名空间对象参数属性:%s", tClass.getName()), exception.getCause());
        }
        //命名空间 对象
        Object nameSpaceValue = getFieldValue(t, namespaceField);
        if (nameSpaceValue == null) {
            return rootElement;
        }
        //所有字段
        List<Field> nameSpaceField = getAllField(namespaceClass);
        for (Field field : nameSpaceField) {
            Object fieldValue = getFieldValue(nameSpaceValue, field);
            if (fieldValue == null) {
                continue;
            }
            Dom4jXml dom4jFieldXml = field.getAnnotation(Dom4jXml.class);
            //根节点设置命名空间
            rootElement.addNamespace(dom4jFieldXml == null ? field.getName() : dom4jFieldXml.name(), String.valueOf(fieldValue));
        }
        return rootElement;
    }

    private static void writeElement(Object t, Element element) {
        if (t == null || element == null) {
            return;
        }
        //基本信息写入值
        if (isBaseType(t)) {
            element.setText(String.valueOf(t));
            return;
        }
        Class<?> tClass = t.getClass();
        List<Field> allField = getAllField(tClass);
        for (Field field : allField) {
            Object fieldValue;
            // 节点属性值和命名空间 值为空
            if (field.getName().endsWith("Attribute") || "namespace".equals(field.getName()) || (fieldValue = getFieldValue(t, field)) == null) {
                continue;
            }
            // 存在字段注解
            Dom4jXml dom4jFieldXml = field.getAnnotation(Dom4jXml.class);
            //节点属性
            Object attributeValue = getFieldAttributeValue(t, field);
            Field attributeField;
            try {
                //存在属性注解获取属性字段
                attributeField = dom4jFieldXml == null || dom4jFieldXml.attribute() == void.class ? null :
                        tClass.getDeclaredField(field.getName() + "Attribute");
            } catch (NoSuchFieldException e) {
                throw new Dom4jException(String.format("未查询到属性字段：%s", field.getName()), e.getCause());
            }
            addFieldElement(field, fieldValue, attributeField, attributeValue, element);
        }
    }

    /**
     * 新增字段节点
     * @param field 节点字段
     * @param fieldValue 节点字段值
     * @param attributeField 节点属性字段
     * @param attributeValue 节点属性字段值
     * @param fatherElement 父节点
     */
    private static void addFieldElement(Field field, Object fieldValue, Field attributeField, Object attributeValue, Element fatherElement) {
        // 存在字段注解
        Dom4jXml dom4jFieldXml = field.getAnnotation(Dom4jXml.class);
        // 节点名称
        String elementName = dom4jFieldXml == null || "".equals(dom4jFieldXml.name()) ? field.getName() : dom4jFieldXml.name();

        if (field.getType() == List.class || field.getType().isArray()) {
            List<Object> list = castList(fieldValue, Object.class);
            List<Object> aList = castList(attributeValue, Object.class);
            for (int i = 0; i < list.size(); i++) {
                QName qName = DocumentHelper.createQName(elementName, fatherElement.getNamespace());
                Element element = fatherElement.addElement(qName);
                writeElement(list.get(i), element);
                writeAttribute(aList.get(i), attributeField, element);
            }
            return;
        }
        QName qName = DocumentHelper.createQName(elementName, fatherElement.getNamespace());
        Element element = fatherElement.addElement(qName);
        writeElement(fieldValue, element);
        //属性设置
        writeAttribute(attributeValue, attributeField, element);
    }

    /**
     * 写入字段属性
     * @param fieldValue 字段值
     * @param field 字段类型
     * @param element 字段节点
     */
    private static void writeAttribute(Object fieldValue, Field field, Element element) {
        if (fieldValue == null || field == null || element == null) {
            return;
        }
        //list对象是获取泛型
        Class<?> type = field.getType();
        if (type == List.class) {
            type = (Class<?>) ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0];
        }
        //数组获取组件类型
        if (type.isArray()) {
            type = field.getType().getComponentType();
        }

        for (Field fd : getAllField(type)) {
            Dom4jXml dom4jFieldXml = fd.getAnnotation(Dom4jXml.class);
            Object fdValue = getFieldValue(fieldValue, fd);
            if (fdValue == null) {
                continue;
            }
            element.addAttribute(dom4jFieldXml != null && "".equals(dom4jFieldXml.name())
                    ? dom4jFieldXml.name() : fd.getName(), String.valueOf(fdValue));
        }
    }

    /**
     * 获取对象属性值
     * @param t 对象
     * @param field 属性
     * @return 属性值
     */
    private static Object getFieldValue(Object t, Field field) {
        Class<?> tClass = t.getClass();
        String methodName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        try {
            Method m = tClass.getMethod("get" + methodName);
            return m.invoke(t);
        } catch (Exception e) {
            throw new Dom4jException(String.format("获取对象属性值错误:%s,%s", field.getName(), e.getMessage()), e.getCause());
        }
    }

    /**
     * 获取字段属性值
     * @param t 对象
     * @param field 字段
     * @return 字段对应属性得值
     */
    private static Object getFieldAttributeValue(Object t, Field field) {
        Dom4jXml dom4jFieldXml = field.getAnnotation(Dom4jXml.class);
        if (dom4jFieldXml == null || dom4jFieldXml.attribute() == void.class) {
            return null;
        }
        Class<?> tClass = t.getClass();
        String methodName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        try {
            Method m = tClass.getMethod("get" + methodName + "Attribute");
            return m.invoke(t);
        } catch (Exception e) {
            throw new Dom4jException(String.format("获取节点属性值：%s , %s ", field.getName(), e.getMessage()), e.getCause());
        }
    }

    /**
     * 数组 list 转list
     * @param obj list /数据对象
     * @param clazz 对象泛型
     * @param <T> 对象泛型 返回列表
     * @return list返回对象
     */
    private static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj == null || clazz == null) {
            return result;
        }

        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }

        if (obj instanceof Object[]) {
            for (Object o : (Object[]) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
                "<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\" xmlns:x=\"urn:gsma:params:xml:ns:rcs:rcs:up:fthttpext\">" +
                "<xid wo=\"kkk\" ks=\"gg\">" +
                "<huanghe wo=\"laile\" ks=\"zoule\">sg</huanghe>" +
                "<sh>sb</sh>" +
                "</xid>" +
                "<xid wo=\"ksk\" ks=\"gsg\">" +
                "<huanghe wo=\"lailes\" ks=\"zoules\">sgs</huanghe>" +
                "<sh>sbs</sh>" +
                "</xid>" +
                "<key>wode</key>" +
                "</file>";
        // Dom4jXmlDemo ww = xmlToBean(str, Dom4jXmlDemo.class);
        // System.out.println("gg");


        Dom4jXmlDemo dom4jXmlDemo = new Dom4jXmlDemo();
        Dom4jXmlNamespace namespace = new Dom4jXmlNamespace();
        namespace.setA("urn:gsma:params:xml:ns:rcs:rcs:fthttp");
        namespace.setB("urn:gsma:params:xml:ns:rcs:rcs:up:fthttpext");
        dom4jXmlDemo.setNamespace(namespace);

        Dom4jXmlField dom4jXmlField = new Dom4jXmlField();
        dom4jXmlField.setHh("sg");
        dom4jXmlField.setSh("sb");

        Dom4jXmlField dom4jXmlField2 = new Dom4jXmlField();
        dom4jXmlField2.setHh("sg2");
        dom4jXmlField2.setSh("sb2");

        List<Dom4jXmlField> list = new ArrayList<>();
        list.add(dom4jXmlField);
        list.add(dom4jXmlField2);

        Dom4jXmlAttribute dom4jXmlAttribute = new Dom4jXmlAttribute();
        dom4jXmlAttribute.setKs("zoule");
        dom4jXmlAttribute.setWs("laile");
        List<Dom4jXmlAttribute> list2 = new ArrayList<>();
        list2.add(null);
        list2.add(null);

        dom4jXmlDemo.setIdList(list);
        dom4jXmlDemo.setIdListAttribute(list2);

        System.out.println(beanToXml(dom4jXmlDemo));

    }
}
