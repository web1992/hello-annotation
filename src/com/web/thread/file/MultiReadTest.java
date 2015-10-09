package com.web.thread.file;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class MultiReadTest {

	/**
	 * ���̶߳�ȡ�ļ�����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int DOWN_THREAD_NUM = 10;// ��10���߳�ȥ��ȡָ���ļ�
		final String OUT_FILE_NAME = "C:\\Users\\web\\Downloads\\a.sql";
		final String keywords = "INSERT";
		// jdk1.5�̸߳����࣬�����̵߳ȴ��������߳�ִ����Ϻ�ʹ�õ��࣬
		// ����һ������������Լ�д��ʱ�������˽����������
		CountDownLatch doneSignal = new CountDownLatch(DOWN_THREAD_NUM);
		RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
		try {
			long length = new File(OUT_FILE_NAME).length();
			System.out.println("file total szie" + length + " byte");
			// ÿ�߳�Ӧ�ö�ȡ���ֽ���
			long numPerThred = length / DOWN_THREAD_NUM;
			System.out.println("every thread read " + numPerThred + " byte");
			// �����ļ�������ʣ�µ�����
			long left = length % DOWN_THREAD_NUM;
			for (int i = 0; i < DOWN_THREAD_NUM; i++) {
				// Ϊÿ���̴߳�һ����������һ��RandomAccessFile����

				// ��ÿ���̷ֱ߳����ȡ�ļ��Ĳ�ͬ����
				outArr[i] = new RandomAccessFile(OUT_FILE_NAME, "rw");
				if (i != 0) {
					//
					// isArr[i] = new FileInputStream("d:/�¸ҵ���.rmvb");
					// ��ָ������ļ��������RandomAccessFile����

				}
				if (i == DOWN_THREAD_NUM - 1) {
					// //���һ���̶߳�ȡָ��numPerThred+left���ֽ�
					// System.out.println("��"+i+"���̶߳�ȡ��"+i * numPerThred+"��"+((i
					// + 1) * numPerThred+ left)+"��λ��");
					new ReadThread(i * numPerThred, (i + 1) * numPerThred
							+ left, outArr[i], keywords, doneSignal).start();
				} else {
					// ÿ���̸߳����ȡһ����numPerThred���ֽ�
					// System.out.println("��"+i+"���̶߳�ȡ��"+i * numPerThred+"��"+((i
					// + 1) * numPerThred)+"��λ��");
					new ReadThread(i * numPerThred, (i + 1) * numPerThred,
							outArr[i], keywords, doneSignal).start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// finally{
		//
		// }
		// ȷ�������߳�������ɣ���ʼִ�����̵߳Ĳ���
		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ������Ҫ�����жϣ�������read�����߳�ȫ��ִ���ꡣ
		KeyWordsCount k = KeyWordsCount.getCountObject();
		// Map<String,Integer> resultMap = k.getMap();
		System.out.println("the key word apperaper count " + k.getCount());
	}

}