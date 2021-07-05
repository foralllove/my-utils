package pers.util.xml.annotation;

import pers.util.xml.model.JaxbXmlDemo;

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

    public static <T> T xmlToBean(String xml, Class<T> tClass) throws IllegalAccessException, InstantiationException {
        if (xml == null || "".equals(xml) || tClass == null) {
            return null;
        }
//        Dom4jXml dom4jXml = tClass.getAnnotation(Dom4jXml.class);
        if (!tClass.isAnnotationPresent(Dom4jXml.class)) {
            return null;
        }
        tClass.getFields();
        return null;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        JaxbXmlDemo ww = xmlToBean("ww", JaxbXmlDemo.class);
    }
}
