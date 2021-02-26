package pers.util.xml.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.util.xml.api.StreamXmlTag;
import pers.util.xml.config.StreamStringCdataConverter;


/**
 * 描述：StreamXmlDemo
 *
 * @author 归墟
 * @date 2021/2/25 20:30
 */
@XStreamAlias("root")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamXmlDemo implements StreamXmlTag {
    @XStreamAlias("id")
    private String no;

    @XStreamConverter(value = StreamStringCdataConverter.class)
    @XStreamAlias("msg")
    private String text;

    private String token;
    @Override
    public String toXml() {
        return this.handToXml();
    }
}
