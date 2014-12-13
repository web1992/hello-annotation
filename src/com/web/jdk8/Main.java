package com.web.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	// java develop kit v8 新特性测试
	
//		private static String a=String::trim;
	
	public static void main(String[] args) {
	
		init();
		
		
	}
	
	private static void init(){
		List<String> names = new ArrayList<String>();
	/*	Collections.sort(names,
				(Comparator<? super String>) (String a, String b) -> {
					return b.compareTo(a);
				});

		Collections.sort(names, (a, b) -> ((String) b).compareTo((String) a));*/
		
		
		
		
	}
}

interface IMessage{
	
	public int getId();
	//static default  不能共存
//	public default String getName(){
//		return "";
//	}
	
	public abstract String getAddress();
		
	
	
}
