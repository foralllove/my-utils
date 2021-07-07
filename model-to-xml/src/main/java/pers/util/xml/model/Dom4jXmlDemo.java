package pers.util.xml.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pers.util.xml.annotation.Dom4jFieldXml;
import pers.util.xml.annotation.Dom4jXml;

/**
 * 描述：Dom4jXmlDemo
 *
 * @author 归墟
 * @email huanghe@shzx.com
 * @date 2021/7/6 17:59
 * @company 数海掌讯
 */
@Data
@NoArgsConstructor
@Dom4jXml(name = "file", namespace = Dom4jXmlNamespace.class)
public class Dom4jXmlDemo {

    @Dom4jFieldXml(name = "xid",attribute = Dom4jXmlAttribute.class)
    private Dom4jXmlField id;

    private Dom4jXmlAttribute idAttribute;

    private String key;

    private Dom4jXmlNamespace namespace;
}
