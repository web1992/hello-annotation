package com.web.enums;
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
		ColorEnum[] colorEnums= ColorEnum.values();
		//TODO 枚举类型中通过字段 code  获取这个code 对应的枚举值[对象]
		for(ColorEnum colorEnum:colorEnums){
			if(code.equals(colorEnum.code)){
				return colorEnum;
			}
		}
		return null;
	}
	
	
	private ColorEnum(String code, String desc) {
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
