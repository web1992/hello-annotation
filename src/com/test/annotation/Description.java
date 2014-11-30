package com.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 

 * @ClassName: Description

 * @Description: description @

 * @author: web

 * @date: 2014年11月30日 下午9:09:18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Description {
	
	public String value() default "i` am description "; 

}
