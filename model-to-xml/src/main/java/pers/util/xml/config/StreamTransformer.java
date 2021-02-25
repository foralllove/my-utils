package pers.util.xml.config;

import com.thoughtworks.xstream.XStream;
import lombok.Data;
import pers.util.xml.api.StreamXmlTag;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：StreamTransformer
 *
 * @author 归墟
 * @date 2021/2/25 20:50
 */
public class StreamTransformer {
    private static final Map<Class<?>, XStream> CLASS_2_STREAM_INSTANCE = new HashMap<>();

    public static Map<Class<?>, XStream> getClass2StreamInstance() {
        return CLASS_2_STREAM_INSTANCE;
    }

    public static void registerClass(Class<?> clz) {
        XStream xstream = XStreamInitializer.getInstance();

        xstream.processAnnotations(clz);
        xstream.processAnnotations(getInnerClasses(clz));
        CLASS_2_STREAM_INSTANCE.put(clz, xstream);
    }
}
