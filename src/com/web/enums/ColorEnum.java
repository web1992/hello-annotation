package com.web.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 

 * @ClassName: ColorEnum

 * @Description: 枚举类的用法实例

 * @author: web

 * @date: 2014年12月2日 下午11:55:04
 */
public enum ColorEnum {
		
	// add enums here
	
	YELLOWY("0","黄色"),
	RED("1","红色"),
	BLUE("2","蓝色"),
	BLACK("3","黑色")
	;
	
	private String code;
	private String desc;
	
	/**
	 * 
	 * @param code
	 * @return
	 * @description 通过code获取枚举对象
	 */
	public static ColorEnum codeOf(String code){
		// 枚举类型中通过字段 code  获取这个code 对应的枚举值[对象]
		if(!map.containsKey(code)){
			throw  new IllegalArgumentException(" ColorEnum not have this code.");
		}
		return map.get(code);
	}
	private final static Map<String,ColorEnum> map=new HashMap<String, ColorEnum>();

	static {
		for(ColorEnum colorEnum:ColorEnum.values()){
			map.put(colorEnum.code,colorEnum);
		}
	}


	 ColorEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

	
}
