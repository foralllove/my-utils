package pers.util.xml.api;

/**
 * 描述：XmlTag
 *
 * @author 归墟
 * @date 2021/2/25 20:31
 */
public interface XmlTag {

    /**
     * 转换为xml格式
     *
     * @return string 类型 失败为null
     */
    String toXml();
}
