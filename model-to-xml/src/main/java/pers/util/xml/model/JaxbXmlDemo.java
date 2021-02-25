package pers.util.xml.model;

import com.alibaba.fastjson.JSON;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pers.util.xml.api.JaxbXmlTag;
import pers.util.xml.config.JaxbCdataAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 描述：JaxbXmlDemo
 *
 * @author 归墟
 * @date 2021/2/25 20:04
 */
@Data
@XmlRootElement(name = "root", namespace = "name-sp")
@Slf4j
public class JaxbXmlDemo implements JaxbXmlTag {
    @XmlElement(name = "id")
    @Getter(onMethod = @__(@XmlTransient))
    private String no;

    @XmlJavaTypeAdapter(value = JaxbCdataAdapter.class)
    @XmlElement(name = "msg")
    @Getter(onMethod = @__(@XmlTransient))
    private String text;

    private String token;

    @Override
    public String toXml() {
        try {
            return this.handToXml();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("失败===数据为{}", JSON.toJSONString(this));
        }
        return null;
    }

    @Override
    public NamespacePrefixMapper getNamespacePrefix() {
        return null;
    }
}
