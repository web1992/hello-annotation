package com.web.thread.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author
 * 
 */
public class ReadThread extends Thread {

	private final int BUFF_LEN = 256;
	private long start;
	private long end;
	private RandomAccessFile raf;
	private String keywords;
	private int curCount = 0;
	/**
	 * jdk1.5
	 */
	private CountDownLatch doneSignal;

	public ReadThread(long start, long end, RandomAccessFile raf,
			String keywords, CountDownLatch doneSignal) {
		this.start = start;
		this.end = end;
		this.raf = raf;
		this.keywords = keywords;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			raf.seek(start);
			//
			long contentLen = end - start;
			//
			long times = contentLen / BUFF_LEN + 1;
			System.out.println(this.toString() + "" + times);
			byte[] buff = new byte[BUFF_LEN];
			int hasRead = 0;
			String result = null;
			for (int i = 0; i < times; i++) {
				//
				hasRead = raf.read(buff);
				//
				if (hasRead < 0) {
					break;
				}
				result = new String(buff, "gb2312");
				// / System.out.println(result);
				int count = this.getCountByKeywords(result, keywords);
				if (count > 0) {
					this.curCount += count;
				}
			}

			KeyWordsCount kc = KeyWordsCount.getCountObject();

			kc.addCount(this.curCount);

			doneSignal.countDown();// current thread finished! noted by latch
									// object!
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	public int getCountByKeywords(String statement, String key) {
		return statement.split(key).length - 1;
	}

	public int getCurCount() {
		return curCount;
	}

	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}

	public CountDownLatch getDoneSignal() {
		return doneSignal;
	}

	public void setDoneSignal(CountDownLatch doneSignal) {
		this.doneSignal = doneSignal;
	}
}