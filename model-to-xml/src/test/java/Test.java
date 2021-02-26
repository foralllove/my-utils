import pers.util.xml.api.XmlTag;
import pers.util.xml.model.JaxbXmlDemo;
import pers.util.xml.model.StreamXmlDemo;

import java.util.Date;

/**
 * 描述：Test
 *
 * @author 归墟
 * @date 2021/2/26 9:48
 */

public class Test {
    public static void main(String[] args) {
        XmlTag jaxbXmlDemo = new JaxbXmlDemo("555","gteini","haode");
        XmlTag streamXmlDemo = new StreamXmlDemo("ggg","twsaeta","gae");
        System.out.println(jaxbXmlDemo.toXml());
        System.out.println(streamXmlDemo.toXml());
    }
}
