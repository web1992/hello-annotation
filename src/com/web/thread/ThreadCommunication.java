package com.web.thread;

/**
 * 
 * 
 * @ClassName: ThreadCommunication
 * 
 * @Description: 线程之间的相互通信技术
 * 
 * @author: web1992
 * 
 * @date: 2014年12月7日 下午3:51:54
 */
public class ThreadCommunication {

	// 子线程循环10次，接着主线程循环20次，接着子线程循环10次，
	// 然后回到主线程循环20次，如此往返执行50次，
	// 分析： 把要做的事情抽取为业务对象，子线程的业务就是循环10次，
	// 主线程的任务就是循环20次，
	// 【涉及到的技术，线程同步&线程通信】

	public static void main(String[] args) {
		final Business business = new Business();
		
		// 执行子线程的业务
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int count = 1; count <= 50; count++) {
					business.subThreadBusiness();
				}
			}
		}).start();

		
		// 执行主线程的业务
		for (int count = 1; count <= 50; count++) {
				business.mainThreadBussiness();
		}

	}

}

/**
 * 
 * 
 * @ClassName: Business
 * 
 * @Description: 
 * 
 * @author: web
 * 
 * @date: 2014年12月7日 下午4:41:28
 * 
 * @throws IllegalMonitorStateException
 *         如果当前线程不是此对象监视器的所有者
 */
class Business {

	// 互斥锁，业务1在执行的时候， 不会被业务2打断
	final Object lock = new Object();

	// 子线程是否有执行权
	boolean isSubDo = true;

	// 业务1 ，子线程循环10次
	public void subThreadBusiness() {

		synchronized (lock) { // 括号中的lock就是同步监视器
			while (!isSubDo) {
				try {
					lock.wait();// 子线程没有执行权，就等待
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println("sub  thread do ..." + i);
			}
			isSubDo = false;// 子线程实现完毕，主线程拥有执行权
			lock.notify();
		}
		// lock.notify();

	}

	// 业务2，主线程循环20次
	public void mainThreadBussiness() {

		synchronized (lock) {
			while (isSubDo) {
				try {
					lock.wait();
					
					// isSubDo=true
					// 应该是子线程执行业务，CPU切换到了主线程，那么主线程就需要等待，
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 1; j <= 20; j++) {
				System.out.println("main thread do ..." + j);
			}
			isSubDo = true;// 主线程执行完毕，子线程拥有执行权
			lock.notify();
		}
		// lock.notify();
		// 如果此代码在 synchronized 的同步代码块外部，
		// 对象lock中的代码已经在 wait ，失去了CPU的执
		// 行权，无法执行 lock.notify() ，唤醒其他线
		// 出现死锁程的操作,

	}

}
