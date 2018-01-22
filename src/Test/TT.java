package Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TT {
	public static List<Integer> list =Collections.synchronizedList(new LinkedList<Integer>());
	public static Map<Object,ConcurrentHashMap<Integer,Integer>> multimap = new ConcurrentHashMap<Object,ConcurrentHashMap<Integer,Integer>>();
	public static Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
/*		File root = new File("src/Test/hwy_android.json");
		String json = FileUtils.readFileToString(root, "utf-8");
		GameOprConfig opr = JSON.parseObject(json, GameOprConfig.class);*/
/*		double a = 0.0022666411222;
        DecimalFormat df = new DecimalFormat(".000000");
*/
/*		try{
			DecimalFormat df = new DecimalFormat(".000000");
			return;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			String[] luckStrings = "1_3333,6_1666,13_769,25_400".split(",");
			for (int i = 0; i < luckStrings.length; i++) {
				System.out.println(luckStrings[i].split("_")[0]+"         "+luckStrings[i].split("_")[1]);
			}	
		}*/
		String s = "60-78";
		String[] rateArr = s.split("\\-");
		System.out.println(rateArr[0]+"         "+rateArr[1]);
 /*     KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
        keyPairGen.initialize(1024);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        // 公钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        // 私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println(publicKey.getFormat());
        
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i<list.size();i++){
			//System.out.println(System.currentTimeMillis());
		}
*/		
//		System.out.println(System.currentTimeMillis());
/*		list.add(1);1484552739
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		TT tt = new TT();
		
		Thread t1 = new Thread(tt.new TestList(list));
		t1.start();

		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()){
			try{
				Thread.currentThread().sleep(600L);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
        	System.out.println("1111111111111111111111111111111111");
			iterator.remove();
		}*/
		
		
		
/*		// TODO Auto-generated method stub
		Map<Integer,PloyShopLimitVO> limitMap = new HashMap<Integer,PloyShopLimitVO>();
		limitMap.put(1, new MysPloyShopLimitVO());
		limitMap.put(2, new MysPloyShopLimitVO());
		limitMap.put(3, new MysPloyShopLimitVO());
		
		MysTT mysTT = new MysTT();
		mysTT.setO(limitMap);
		System.out.println((Map<Integer,PloyShopLimitVO>)mysTT.getO());
*/	
/*		Vector v=new Vector(10);
		for (int i=1;i<100; i++)
		{
			Object o=new Object();
			v.add(o);
			o=null;	
		}*/
/*		
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		TT tt = new TT();
		
		Thread t1 = new Thread(tt.new TestAddList(list));
		//t1.start();
		Thread t2 = new Thread(tt.new TestRemoveList(list));
		t2.start();*/
		/** MultiMAP **/
/*		System.gc();
		long total = Runtime.getRuntime().totalMemory(); // byte
		long m1 = Runtime.getRuntime().freeMemory();
		System.out.println("before:" + (total - m1));
		for(int i=0; i < 10000; i++){
			ConcurrentHashMap<Integer,Integer> m9 = new ConcurrentHashMap<Integer,Integer>();
			m9.put(1, 1);
			map.put(i, m9);
		}
		System.gc();
		long total1 = Runtime.getRuntime().totalMemory();
		long m2 = Runtime.getRuntime().freeMemory();
		System.out.println("after:" + (total1 - m2));*/
		//   240000  理论内存
		//  2068520 hashMAP占用
		// 13662648 concurrentHashMAP占用
		
		/** ARRAYLIST **/
/*		System.gc();
		long total = Runtime.getRuntime().totalMemory(); // byte
		long m1 = Runtime.getRuntime().freeMemory();
		System.out.println("before:" + (total - m1));
		for(int i=0; i < 10000; i++){
			list.add(i);
		}
		System.gc();
		long total1 = Runtime.getRuntime().totalMemory();
		long m2 = Runtime.getRuntime().freeMemory();
		System.out.println("after:" + (total1 - m2));*/
		//   80000  理论内存
		//  170000 ArrayList占用
		//  170000 Collections.synchronizedList(new ArrayList<Integer>())占用
		
		/** LINKEDLIST **/
/*		System.gc();
		long total = Runtime.getRuntime().totalMemory(); // byte
		long m1 = Runtime.getRuntime().freeMemory();
		System.out.println("before:" + (total - m1));
		for(int i=0; i < 10000; i++){
			list.add(i);
		}
		System.gc();
		long total1 = Runtime.getRuntime().totalMemory();
		long m2 = Runtime.getRuntime().freeMemory();
		System.out.println("after:" + (total1 - m2));*/
		//   80000  理论内存
		//  380000 LinkedList占用
		//  380000 Collections.synchronizedList(new LinkedList<Integer>())占用
		
		/** MAP **/
/*		System.gc();
		long total = Runtime.getRuntime().totalMemory(); // byte
		long m1 = Runtime.getRuntime().freeMemory();
		System.out.println("before:" + (total - m1));
		for(int i=0; i < 10000; i++){
			map.put(i, i);
		}
		System.gc();
		long total1 = Runtime.getRuntime().totalMemory();
		long m2 = Runtime.getRuntime().freeMemory();
		System.out.println("after:" + (total1 - m2));*/
		//   160000  理论内存
		//   600000 hashMAP占用
		
		//System.out.println("after:" + (1 << 30));
	}

	class TestAddList implements Runnable{
		private List<String> vos;
		public TestAddList(List<String> temp){
			vos = temp;
		}
		@Override
		public void run() {
	        if(vos!=null && !vos.isEmpty()){
	            while(true){
	            	vos.add("1");
	            	System.out.println("222222222222222222222222222");
	            	try{
						Thread.sleep(500L);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
	            }
	        }
		}
	}
	
	class TestRemoveList implements Runnable{
		private List<String> vos;
		public TestRemoveList(List<String> temp){
			vos = temp;
		}
		@Override
		public void run() {
	        if(vos!=null && !vos.isEmpty()){
	            Iterator<String> iterator = vos.iterator();
	            while(iterator.hasNext()){
	            	try{
						Thread.sleep(1000L);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					String s = iterator.next();
					vos.remove(s);
					iterator.remove();
					System.out.println("111111111111111111111111");
	            }
	        }
		}
	}
	
	
	class TestList implements Runnable{
		private List<Integer> vos;
		public TestList(List<Integer> temp){
			vos = temp;
		}
		@Override
		public void run() {
	        if(vos!=null && !vos.isEmpty()){
	            while(true){
	            	vos.add(new Integer((int)(System.currentTimeMillis()/1000000L)));
	            	System.out.println("222222222222222222222222222");
	            	try{
						Thread.sleep(500L);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
	            }
	        }
		}
	}

}
