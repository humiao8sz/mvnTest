package classloader;

import java.util.Date;

public class TTT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = new Integer(128);
		int j = new Integer(128);
		System.out.println(TTT.class.getClassLoader());
	}
	
	public static int cc(int i){
		for(;i<4;){
			System.out.println(cc(++i));
		}
		return i;
	}
}
