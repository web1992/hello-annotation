package com.web.annotation;

import java.lang.reflect.Method;
/**
 * 

 * @ClassName: MainAnnotation

 * @Description: 1. 使用反射
 * 			     2. 获取类上面的注解 [Class]
 * 				 3. 获取方法上面的注解[获取Method]
 * 			     4. 获取字段上面的注解[Filed]

 * @author: web

 * @date: 2014年12月2日 下午11:39:25
 * @category annotation 
 */
public class MainAnnotation {

	public static void main(String[] args) {

		String clazzName="com.web.annotation.JavaCommunity";
		
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
