package pers.util.xml.model;

import lombok.Data;
import pers.util.xml.annotation.Dom4jFieldXml;

/**
 * 描述：Dom4jXmlAttribute
 *
 * @author 归墟
 * @email huanghe@shzx.com
 * @date 2021/7/7 11:13
 * @company 数海掌讯
 */
@Data
public class Dom4jXmlAttribute {

    private String ks;

    @Dom4jFieldXml(name = "wo")
    private String ws;
}
