package com.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 

 * @ClassName: Name

 * @Description: name @

 * @author: web

 * @date: 2014年11月30日 下午9:12:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Name {

		public String originnate() default "";
		public String community() default ""; 
	
}
