package pers.util.xml.model;

import lombok.Data;
import pers.util.xml.annotation.Dom4jXml;

/**
 * 描述：Dom4jXmlAttribute
 *
 * @author 归墟
 * @email huanghe@shzx.com
 * @date 2021/7/6 18:56
 * @company 数海掌讯
 */
@Data
public class Dom4jXmlNamespace {

    @Dom4jXml
    private String a;

    @Dom4jXml(name = "x")
    private String b;
}
