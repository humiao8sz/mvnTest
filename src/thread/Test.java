package thread;

import java.util.concurrent.CountDownLatch;
public class Test {
	public final static CountDownLatch l = new CountDownLatch(1);
	public static void main(String args[]) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				synchronized(l){
					try{
						l.wait(10000L);
						System.out.println(222);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				//l.countDown();
			}
		});
		thread.start();
		Thread.sleep(500L);
		//l.await();
		synchronized(l){
			System.out.println(111);
		}
	}
}
