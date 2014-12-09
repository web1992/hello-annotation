package com.web.jdk8;

import java.util.HashMap;
import java.util.Map;

public class MainThreadv8 {

	
	public static void main(String[] args) {
		//final String a="123";
		String a="123";
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("a= "+a);
			}
		}).start();
		
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("1","21");
		map.put("2","21");
		map.put("3","21");
		System.out.println(map);
	}
	
}
