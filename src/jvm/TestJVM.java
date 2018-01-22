package jvm;

import java.util.Vector;

public class TestJVM {
	public static void main(String[] args) {
		/*
		 * byte[] b1 = new byte[1024*1024/2]; byte[] b2 = new byte[1024*1024*8];
		 * b2 = null; b2 = new byte[1024*1024*8];//进行一次新生代 GC System.gc();
		 */

		/*
		 * for (int i = 0; i < Integer.MAX_VALUE; i++) { String t =
		 * String.valueOf(i).intern();// 加入常量池 }
		 */

		Vector v = new Vector();
		for (int i = 0; i <= 10; i++) {
			byte[] b = new byte[1024 * 1024];
			v.add(b);
			System.out.println(i + "M is allocated");
		}
		System.out.println("Max memory:" + Runtime.getRuntime().maxMemory());
	}
}
