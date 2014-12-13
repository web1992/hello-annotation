package com.web.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.RandomAccessFile;


/**
 * 

 * Copyright © 2014. All rights reserved.

 *

 * @Title: MainCountDownLatch.java

 * @Prject: hello-annotation

 * @Package: com.web.io

 * @Description: 多线程读取文件

 * @author: erbao.wang

 * @date: 2014年12月13日 下午6:48:20

 * @version: V1.0
 */
public class MainCountDownLatch {

	//  思路：
	//  文件被分为 A，B，C，D 四部分
	//  四个线程 a,b,c，分别取读取文件
	//	01,创建多个线程去读取文件，
	//  02,在子线程进行读取文件时，主线程需要阻塞   [难点] 
	//  03,线程之间的通信，a线程读取[A部分]，b线程读取[B部分]，c线程读取[C部分 ]... [难点]
	//  04,end~
	
	
	
	public static void main(String[] args) throws Exception {
		
		String fileName="README.md";
		File file=new File(fileName);
		System.out.println("fileName = "+file.getName());
		System.out.println(file.getAbsolutePath());
		FileReader in=new FileReader(file);
		//文件的长度   字节数byte 
		long l=file.length();
		// 根据文件的长度，为每个线程分配各自的[工作量]，需要读取的字节数
		
		BufferedReader bufReader=new BufferedReader(in);
		
		
//		bufReader.r
		
		
		String str=null;
		while (null != (str=bufReader.readLine()) ){
			System.out.println(str);
		}
		
		
		
		// 关闭文件流
		if(null != in){in.close();}
		if(null != bufReader){bufReader.close();}
		
		
		
		
	}

}
