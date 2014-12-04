package com.web.thread;

public class ThreadFlag extends Thread {
	
	public volatile boolean exit =false;
	public void run(){
		while(!exit){
			System.out.println("do something ...");
		}
	}

	
	public static void main(String[] args) throws Exception {
		ThreadFlag threadFlag=new ThreadFlag();
		threadFlag.start();
		Thread.sleep(5000);
		threadFlag.exit=true;
		threadFlag.join();
		System.out.println("end ...");
	}
}
