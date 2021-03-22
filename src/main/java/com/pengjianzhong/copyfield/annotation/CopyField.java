package com.pengjianzhong.copyfield.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author pengjianzhong
 * @date 2020/12/28 15:28
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyField {

    /**
     * 转换时属性名称
     * @author pengjianzhong
     * @date 2021/1/6 10:44
     * @return {@link String}
     **/
    String name() default "";

    /**
     * 转换器
     * @author pengjianzhong
     * @date 2021/1/6 10:44
     * @return {@link String}
     **/
    String convert() default "";
}
