package pers.util.xml.annotation;

import java.lang.annotation.*;

/**
 * Dom4jXml
 *
 * @author: hh
 * @since: 2021/7/6 1:13
 */

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dom4jXml {

    /**
     * 名称
     * @return 返回
     */
    String name() default "";

    /**
     * xml 根属性 对象
     * @return 属性对象
     */
    Class<?> namespace() default void.class;

    /**
     * 字段属性
     * @return 属性
     */
    Class<?> attribute() default void.class;
}

