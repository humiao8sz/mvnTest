package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
public class Test {
	public final static CountDownLatch l = new CountDownLatch(1);
	public static void main(String args[]) throws InterruptedException {
/*		Thread thread = new Thread(new Runnable() {
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
		}*/
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		map.put(1, 1.0);
		map.put(1, 2.0);
		map.put(1, 3.0);
		map.put(1, 4.0);
		map.put(1, 5.0);
		for(int type : map.keySet()) {
			map.put(type, map.get(type)*10);
		}
	}
}
