package com.liuzemin.server.framework.model.annotation;

import java.lang.annotation.*;

/**
 * @author ：FengYi
 * @date ：Created in 2019-09-21 11:21
 * 统计浏览量
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageView {
    String description() default "";
}
