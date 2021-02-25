package pers.util.xml.config;

import pers.util.xml.constant.CdataConstant;

import javax.xml.stream.XMLStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述：CDataHandler
 *
 * @author 归墟
 * @date 2021/2/25 19:52
 */
public class JaxbCdataHandler implements InvocationHandler {

    private static Method gWriteCharactersMethod = null;

    static {
        try {
            gWriteCharactersMethod = XMLStreamWriter.class
                    .getDeclaredMethod("writeCharacters", char[].class, int.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private final XMLStreamWriter writer;

    public JaxbCdataHandler(XMLStreamWriter writer) {
        this.writer = writer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (gWriteCharactersMethod.equals(method)) {
            if (!(args[0] instanceof char[])) {
                return method.invoke(writer, args);
            }
            String text = new String((char[]) args[0]);
            if (text.startsWith(CdataConstant.PRE) && text.endsWith(CdataConstant.SUFFIX)) {
                writer.writeCData(text.substring(9, text.length() - 3));
                return null;
            }
        }
        return method.invoke(writer, args);
    }

}