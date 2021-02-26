package pers.util.xml.config;

import com.thoughtworks.xstream.converters.basic.StringConverter;
import lombok.NoArgsConstructor;
import pers.util.xml.constant.CdataConstant;

/**
 * 描述：StreamStringCdataConverter
 *
 * @author 归墟
 * @date 2021/2/25 20:38
 */
@NoArgsConstructor
public class StreamStringCdataConverter extends StringConverter {

    @Override
    public String toString(Object obj) {
        return CdataConstant.PRE + super.toString(obj) + CdataConstant.SUFFIX;
    }
}
