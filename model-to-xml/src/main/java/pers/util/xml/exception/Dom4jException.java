package pers.util.xml.exception;

/**
 * 描述：Dom4jException xml处理异常
 *
 * @author 归墟
 * @email huanghe@shzx.com
 * @date 2021/7/12 9:48
 * @company 数海掌讯
 */
public class Dom4jException extends RuntimeException {

    public Dom4jException() {
        super();
    }

    public Dom4jException(String message) {
        super(message);
    }

    public Dom4jException(String message, Throwable cause) {
        super(message, cause);
    }
}
