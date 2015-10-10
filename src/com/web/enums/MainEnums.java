package com.web.enums;

public class MainEnums {

	public static void main(String[] args) {

		// will throw error
//		ColorEnum colorEnum=ColorEnum.valueOf("123");
		ColorEnum colorEnum=ColorEnum.codeOf("1");
		System.out.println("ColorEnum= "+colorEnum);
		System.out.println("desc= "+colorEnum.getDesc());
		System.out.println("code= "+colorEnum.getCode());
	}

}
