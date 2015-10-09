package com.web.string;

import java.text.MessageFormat;

public class MainString {

	public static void main(String[] args) {

		// String code = "dts_1";
		String code = "  ";
		// String code = null;
		String[] codes = null;

		// 为了程序的健壮性，在对字符串进行截取的操作时，
		// 对字符串是否为空做检查
		System.out.println("[" + code.trim() + "]");
		System.out.println("".equals(code.trim()));

		if (null != code && !"".equals(code.trim())) {
			codes = code.split("_");
			System.out.println(codes[1]);
		}


		MessageFormat.format("",1);
	}

}
