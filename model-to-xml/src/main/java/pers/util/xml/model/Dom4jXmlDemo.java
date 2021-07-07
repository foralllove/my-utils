package pers.util.xml.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pers.util.xml.annotation.Dom4jFieldXml;
import pers.util.xml.annotation.Dom4jXml;

import java.util.List;

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
    private List<Dom4jXmlField> idList;

    private List<Dom4jXmlAttribute> idListAttribute;

    private String key;

    private Dom4jXmlNamespace namespace;
}
