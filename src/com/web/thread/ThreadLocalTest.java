package com.web.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 

 * @ClassName: ThreadLocalTest

 * @Description: 实现线程范围内的共享变量
 * 
 * @see ThreadLocal

 * @author: web

 * @date: 2014年12月7日 下午6:09:45
 */
public class ThreadLocalTest {

	static Map<Thread,Integer> threadData=new HashMap<Thread,Integer>();
	
	public static void main(String[] args) {
		for(int i=1;i<=2;i++){
			new ThreadLocalTest().init();
		}

	}

	private void init(){
		final int data=new Random().nextInt();// jdk 8.0 内部类访问外部类变量不需要加 final 关键字
		new Thread(
				new Runnable(){
					public void run() {
						threadData.put(Thread.currentThread(),data);
						System.out.println(Thread.currentThread().getName()+"put data= " +data);
						new A().get();//A  中只获取线程1中的数据
						new B().get();//B  中只获取线程2中的数据
					};
				}
	    ).start();
	}
	
	static class A{
		
		public void get(){
			int data=threadData.get(Thread.currentThread());
			System.out.println("Data form A "+Thread.currentThread()+"data :"+data);
		}
	}

	static class B{
		
		public void get(){
			int data=threadData.get(Thread.currentThread());
			System.out.println("Data form B "+Thread.currentThread()+"data :"+data);
		}
	}
}



