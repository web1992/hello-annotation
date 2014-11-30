package com.test.annotation;


@Description("我是Java 社区的Description ")
public class JavaCommunity {
		@Name(originnate="spring 李",community="org.spring")
		public String getName(){
			return "我是 spring";
		}
		@Name(originnate="hibernate 王",community="org.hibernate")
		public String getName2(){
			
			return "我是 hibernate  ";
		}
	
	
}
