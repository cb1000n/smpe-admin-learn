package com.zhang.annotation;

import java.lang.annotation.*;

/**
 * ClassName AnonymousAccess
 * Description TODO 类描述：用于标记匿名访问方法
 *
 * @author ZhangRenjie
 * Date  2020/12/24 15:01
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}