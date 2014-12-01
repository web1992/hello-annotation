package com.web.annotation;

import java.lang.reflect.Method;

public class MainAnnotation {

	public static void main(String[] args) {

		String clazzName="com.test.annotation.JavaCommunity";
		
		try {
			Class<?> clazz =Class.forName(clazzName);
			clazz.newInstance();
			
			Method[] methods=clazz.getMethods();
			boolean flag=clazz.isAnnotationPresent(Description.class);
			if(flag){
				Description description=clazz.getAnnotation(Description.class);
				String value=description.value();
				System.out.println("Descirption value = "+value);
			}
			
		
				for(Method m:methods){
					boolean methodFlag=m.isAnnotationPresent(Name.class);
					if(methodFlag){
					Name name=m.getAnnotation(Name.class);
					System.out.println("originnate = "+name.originnate()+" community = "+name.community());
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public  void test(){
		
	}

}
