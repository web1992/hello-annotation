package com.web.thread;

/**
 * 
 * 
 * @ClassName: ThreadSyn
 * 
 * @Description: 线程同步技术
 * 				 如下，在主线程打印【ggggg】时，子线程是不能打扰的
 * 				一直到，主线程把 ggggg 输出完毕，
 * @author: web
 * 
 * @date: 2014年12月6日 下午5:04:12
 */
public class ThreadSyn {

	public static void main(String[] args) {

		// 线程同步代码，一块代码只能同时被一个线程来执行
		// synchronized 把关键字作用在方法上，方法属于一个对象
		// 实际等同于对这个对象加锁
		OutPrintName outPrintName = new OutPrintName();

		while (true) {
			outPrintName.print2("ggggg");

			new Thread(new Runnable() {
				@Override
				public void run() {
					outPrintName.print1("fffff");
				}
			}).start();
		}

	}

	static class OutPrintName {
		final Object lock = new Object();//对象锁，
		//final Object lock1 = new Object();//二个对象锁，不能实现线程同步的效果
		public void print1(String name) {
			synchronized (lock) {
				for (char cName : name.toCharArray()) {
					System.out.print(cName);
				}
				System.out.println();
			}

		}

		public void print2(String name) {
			synchronized (lock) {
				for (char cName : name.toCharArray()) {
					System.out.print(cName);
				}
				System.out.println();
			}

		}
	}

}
