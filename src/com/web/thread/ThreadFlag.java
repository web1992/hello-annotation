package com.web.thread;
/**
 * 

 * Copyright © 2014. All rights reserved.

 *

 * @Title: ThreadFlag.java

 * @Prject: hello-annotation

 * @Package: com.web.thread

 * @Description: TODO

 * @author: erbao.wang

 * @date: 2014年12月5日 下午7:51:18

 * @version: V1.0
 */
public class ThreadFlag extends Thread {
	
	public volatile boolean exit =false;
	public void run(){
		while(!exit){
			System.out.println("do something ...");
		}
	}

	
	public static void main(String[] args) throws Exception {
		ThreadFlag thread=new ThreadFlag();
		thread.start();
		Thread.sleep(5000);
		thread.exit=true;
		thread.join();
		System.out.println("end ...");
	}
}
	