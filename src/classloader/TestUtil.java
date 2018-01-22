package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestUtil {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
		UpdateUtilOneUtil.getInstance().sing();
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try{
					Class<?> oldClass = Class.forName("classloader.UpdateUtilOneUtil");
					Class<?> newClass = Class.forName("classloader.UpdateUtilTwoUtil");
					Class<?> interfaceClass = Class.forName("classloader.IupdateUtil");
					Method method = oldClass.getMethod("setInstance",interfaceClass);
					method.invoke(oldClass,newClass.newInstance());
					UpdateUtilOneUtil.getInstance().sing();									
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
