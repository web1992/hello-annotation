package com.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 

 * Copyright © 2014. All rights reserved.

 *

 * @Title: Config.java

 * @Prject: hello-annotation

 * @Package: com.web.annotation

 * @Description: TODO

 * @author: erbao.wang

 * @date: 2014年12月1日 上午10:40:52

 * @version: V1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Documented
public @interface Config {
	
	public String prop() default "";
	
}
