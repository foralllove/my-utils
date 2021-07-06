package pers.util.xml.annotation;

import java.lang.annotation.*;

/**
 * Dom4jFieldXml
 *
 * @author: hh
 * @since: 2021/7/6 1:29
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dom4jFieldXml {

    /**
     * 名称
     * @return 返回
     */
    String name() default "";

    /**
     * 字段属性
     * @return 属性
     */
    Class<?> attribute() default void.class;
}
