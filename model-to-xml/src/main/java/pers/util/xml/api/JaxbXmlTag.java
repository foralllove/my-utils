package pers.util.xml.api;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import pers.util.xml.config.JaxbCdataHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.lang.reflect.Proxy;


/**
 * 描述：XmlTag
 *
 * @author 归墟
 * @date 2021/2/24 10:42
 */
public interface JaxbXmlTag extends XmlTag{
    /**
     * 处理转换
     *
     * @return f返回
     * @throws Exception 异常
     */
    default String handToXml() throws Exception {
        StringWriter writer = new StringWriter();
        XMLStreamWriter streamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
        //使用动态代理模式, 对streamWriter功能进行干涉调整
        XMLStreamWriter cdataStreamWriter = (XMLStreamWriter) Proxy.newProxyInstance(
                streamWriter.getClass().getClassLoader(),
                streamWriter.getClass().getInterfaces(),
                new JaxbCdataHandler(streamWriter)
        );
        JAXBContext context = JAXBContext.newInstance(this.getClass());
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        mar.setProperty(Marshaller.JAXB_ENCODING, ENCODING);
        //自定义
        NamespacePrefixMapper mapper = getNamespacePrefix();
        if (mapper != null) {
            mar.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);
        }

        mar.marshal(this, cdataStreamWriter);
        return writer.toString();
    }

    /**
     * 自动以根号 命名空间 前缀
     *
     * @return 前缀消息
     */
    NamespacePrefixMapper getNamespacePrefix();

    /**
     * 默认utf-8编码
     */
    String ENCODING = "UTF-8";
}
