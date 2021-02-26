package pers.util.xml.api;

import com.thoughtworks.xstream.XStream;
import pers.util.xml.config.StreamTransformer;

/**
 * 描述：StreamXmlTag
 *
 * @author 归墟
 * @date 2021/2/25 20:32
 */
public interface StreamXmlTag extends XmlTag {
    /**
     * 转换操作
     *
     * @return xml String
     */
    default String handToXml() {
        XStream xStream = StreamTransformer.getStream(this);
        if (xStream == null) {
            return null;
        }
        return xStream.toXML(this);
    }
}
