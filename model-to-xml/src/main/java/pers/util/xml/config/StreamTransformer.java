package pers.util.xml.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;
import java.util.*;

import static pers.util.xml.constant.CdataConstant.PRE;
import static pers.util.xml.constant.CdataConstant.SUFFIX;

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

    public static synchronized XStream registerClass(Class<?> clz) {
        return registerClass(clz, new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out, this.getNameCoder()) {

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (text.startsWith(PRE) && text.endsWith(SUFFIX)) {
                            writer.write(text);
                        } else {
                            super.writeText(writer, text);
                        }
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }
                };
            }
        });

    }

    public static synchronized XStream registerClass(Class<?> clz, XppDriver xppDriver) {
        XStream xstream = new XStream(new PureJavaReflectionProvider(), xppDriver);
        xstream.processAnnotations(clz);
        xstream.processAnnotations(getInnerClasses(clz));
        CLASS_2_STREAM_INSTANCE.put(clz, xstream);
        return xstream;
    }

    public static XStream getStream(Object obj) {
        XStream xStream = CLASS_2_STREAM_INSTANCE.get(obj.getClass());
        if (xStream == null) {
            //类锁
            synchronized (StreamTransformer.class) {
                xStream = CLASS_2_STREAM_INSTANCE.get(obj.getClass());
                if (xStream == null) {
                    xStream = registerClass(obj.getClass());
                }
            }

        }
        return xStream;
    }

    /**
     * 获取包含class
     *
     * @param clz 类名
     * @return 包含列表
     */
    private static Class<?>[] getInnerClasses(Class<?> clz) {
        Class<?>[] innerClasses = clz.getClasses();
        List<Class<?>> result = new ArrayList<>(Arrays.asList(innerClasses));

        for (Class<?> inner : innerClasses) {
            Class<?>[] innerClz = getInnerClasses(inner);
            result.addAll(Arrays.asList(innerClz));
        }

        return result.toArray(new Class<?>[0]);
    }
}
