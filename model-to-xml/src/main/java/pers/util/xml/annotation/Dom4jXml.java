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
     * 名称（xml中名称）
     * @return 返回
     */
    String name() default "";

    /**
     * xml 节点命名空间
     * @return 节点命名空间
     */
    Class<?> namespace() default void.class;

    /**
     * xml 节点属性
     * @return 属性
     */
    Class<?> attribute() default void.class;

    /**
     * 当前命名空间前缀
     * @return 前缀
     */
    String namespacePrefix() default "";

    /**
     * 当前命名空间url
     * @return url
     */
    String namespaceUrl() default "";
}

