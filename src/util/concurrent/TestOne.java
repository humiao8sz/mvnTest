package util.concurrent;

import java.util.concurrent.CountDownLatch;

public class TestOne {
	public static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public static void main(String[] args) {
		Thread aThread = new Thread(new Runnable() {
			@Override
			public void run() {
				countDownLatch.countDown();
				System.out.println("aaaaaaaaaaaaaaa");
			}
		});
		
		
		Thread bThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					Thread.currentThread().sleep(2000L);
					countDownLatch.await();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				System.out.println("bbbbbbbbbbbbbbb");
			}
		});
		aThread.start();
		bThread.start();
	}
}
