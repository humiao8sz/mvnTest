package promise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestPromise {
	public static List<DefaultPromise<Integer>> oList = new ArrayList<DefaultPromise<Integer>>();
	
	public static void main(String[] args){
		DefaultPromise<Integer> promise = new DefaultPromise<Integer>();
		oList.add(promise);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					Thread.sleep(3000L);
					oList.get(0).trySuccess(new Integer(1));
					//oList.get(0).tryFailure(new Exception("11111111"));
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		thread.start();
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					try{
						System.out.println("---------------"+oList.get(0).get());
					}catch(ExecutionException e){
						e.printStackTrace();
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		thread2.start();
		promise.cancel(true);
		
		promise.addListener(new GenericFutureListener<Future<Integer>>() {
			@Override
			public void operationComplete(Future<Integer> future) throws Exception {
				System.out.println("+++++++++++++"+future.get());
			}
		});
		
		promise.addListener(new GenericFutureListener<Future<Integer>>() {
			@Override
			public void operationComplete(Future<Integer> future) throws Exception {
				System.out.println("+++++++++++++"+future.get());
			}
		});
	}
}
