package com.liuzemin.server.framework.model.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSZLJSONS.class)
public @interface JSZLJSON {
	Class<?> type();
	String include() default "";
	String filter() default "";
}
