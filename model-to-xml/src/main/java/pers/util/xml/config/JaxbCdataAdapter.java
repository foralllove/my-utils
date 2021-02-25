package pers.util.xml.config;

import pers.util.xml.constant.CdataConstant;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 描述：CdataAdapter
 *
 * @author 归墟
 * @date 2021/2/24 11:19
 */
public class JaxbCdataAdapter extends XmlAdapter<String, String> {



    @Override
    public String unmarshal(String v) {
        return v;
    }

    @Override
    public String marshal(String v) {
        return CdataConstant.PRE + v + CdataConstant.SUFFIX;
    }
}

