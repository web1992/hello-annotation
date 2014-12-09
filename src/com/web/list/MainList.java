package com.web.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 

 * Copyright © 2014. All rights reserved.

 *

 * @Title: MainList.java

 * @Prject: hello-annotation

 * @Package: com.web.list

 * @Description: 集合排序测试

 * @author: erbao.wang

 * @date: 2014年12月9日 上午9:45:09

 * @version: V1.0
 */
public class MainList {

	public static void main(String[] args) {
		
		
		// 字符串数组排序，根据字符串的长度排序[升序]
		List<String> list=new ArrayList<String>();
		list.add("dd");
		list.add("ddd2");
		list.add("dddd33");
		list.add("dddd121");
		
		Collections.sort(list, new Comparator<String>(){
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.length()-arg1.length();
			}
			
		});
		System.out.println(list.toString());
	}

}
