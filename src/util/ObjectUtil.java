package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtil {

	public static void main(String[] args) throws IOException{
		ObjectUtil util = new ObjectUtil();
		File file = new File("Test.txt");
		//建立输出字节流
/*		FileOutputStream fos = new FileOutputStream(file);
		User user = new User();
		user.setName("n1");
		fos.write(util.toByteArray(user));
		fos.close();*/
		FileInputStream fis = new FileInputStream(file);
		byte[] a = new byte[(int)file.length()];
		fis.read(a);
		User user = (User)(util.toObject(a));
		System.out.println(user.getName());
		System.out.println(user.getSex());
	}
	
	/**
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				oos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				bos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * 数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public Object toObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try{
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}finally{
			try{
				ois.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				bis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return obj;
	}
}
