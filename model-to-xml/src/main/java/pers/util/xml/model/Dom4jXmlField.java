package pers.util.xml.model;

import lombok.Data;
import pers.util.xml.annotation.Dom4jXml;

/**
 * 描述：Dom4jXmlFieldXml
 *
 * @author 归墟
 * @email huanghe@shzx.com
 * @date 2021/7/7 11:24
 * @company 数海掌讯
 */
@Data
public class Dom4jXmlField {
    @Dom4jXml(name = "huanghe",attribute = Dom4jXmlAttribute.class)
    private String hh;

    private Dom4jXmlAttribute hhAttribute;

    private String sh;
}
