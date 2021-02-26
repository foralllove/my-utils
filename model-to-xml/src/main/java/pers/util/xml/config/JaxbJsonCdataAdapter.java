package pers.util.xml.config;

import com.alibaba.fastjson.JSON;
import pers.util.xml.constant.CdataConstant;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 描述：JaxbJsonCdataAdapter
 *
 * @author 归墟
 * @date 2021/2/24 11:19
 */
public class JaxbJsonCdataAdapter extends XmlAdapter<String, Object> {

    @Override
    public Object unmarshal(String v) {
        return JSON.parseObject(v, Object.class);
    }

    @Override
    public String marshal(Object v) {
        return CdataConstant.PRE + JSON.toJSONString(v) + CdataConstant.SUFFIX;
    }
}

