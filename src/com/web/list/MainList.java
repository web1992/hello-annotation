package com.web.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * 
 * Copyright © 2014. All rights reserved.
 * 
 *
 * 
 * @Title: MainList.java
 * 
 * @Prject: hello-annotation
 * 
 * @Package: com.web.list
 * 
 * @Description: 集合排序测试
 * 
 * @author: erbao.wang
 * 
 * @date: 2014年12月9日 上午9:45:09
 * 
 * @version: V1.0
 */
public class MainList {

	public static void main(String[] args) {

		sortList();

		forList();

	}

	/**
	 * 在使用for each 遍历使用，底层仍然使用的是 Iterator 遍历 会调用 list.iterator(),若list
	 * 为空，则抛出空指针异常 <br />
	 * FIXME [在使用数组，List，Map 时最好用new 赋值，从而避免空指针异常的发生]
	 */
	private static void forList() {

		List<String> list = new ArrayList<String>();
		// for(String str:list){
		// System.out.println(str);
		// }

		//
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("end .");

	}

	public static void sortList() {
		// 字符串数组排序，根据字符串的长度排序[升序]
		List<String> list = new ArrayList<String>();
		list.add("dd");
		list.add("ddd2");
		list.add("dddd33");
		list.add("dddd121");

		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.length() - arg1.length();
			}

		});
		System.out.println(list.toString());
	}

}
