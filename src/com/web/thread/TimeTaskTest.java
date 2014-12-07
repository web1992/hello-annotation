package com.web.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTaskTest {

	public static int count=0;
	public static void main(String[] args) throws Exception {
		// 定时间基本用法
		//testTimerTask();
		
		
		
		//定时器的 "交替工作"，一个3秒后工作，一个2秒后工作
		class MyTimerTask extends TimerTask{
			@Override
			public void run(){
				count=((count+1)%2);// 0/1 切换
				System.out.println("bombing ... threadName ="+Thread.currentThread().getName());
				new Timer().schedule(new MyTimerTask(), 2000+count*1000);
			}
			
		}
		new Timer().schedule(new MyTimerTask(),0);
		// end 
		
		//打印系统时间秒数 0-60 
		while(true){
			Thread.sleep(1000); 
			@SuppressWarnings("deprecation")
			int seconds=new java.util.Date().getSeconds();
			System.out.println(" threadName="+Thread.currentThread().getName()+ " "+seconds);
		}
	}

	
	//做为一个子线程一直在运行
	public static void testTimerTask(){
		long begintTime =5000;//在主线程5秒后运行
		long cycleTime =3000;//每3秒循环一次
		new Timer().schedule(new TimerTask(){
			public void run() {
				
				System.out.println("bombing ... threadName = "+Thread.currentThread().getName());
			};
		}, begintTime,cycleTime);
		
	}
	

}



