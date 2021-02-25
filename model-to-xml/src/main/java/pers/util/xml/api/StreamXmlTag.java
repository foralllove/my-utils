package pers.util.xml.api;

import com.thoughtworks.xstream.XStream;
import pers.util.xml.config.StreamTransformer;

import java.util.Map;

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
        Map<Class<?>, XStream> streamMap = StreamTransformer.getClass2StreamInstance();
        XStream xStream = streamMap.get(this.getClass());
        if(xStream == null){
            StreamTransformer.registerClass(this.getClass());
        }
        return xStream.toXML(this);
    }
}
