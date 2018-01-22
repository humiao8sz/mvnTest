package classloader;

public class UpdateUtilTwoUtil implements IupdateUtil {
	public static volatile IupdateUtil instance;
	
	public static IupdateUtil getInstance() {
		if(instance == null){
			synchronized(UpdateUtilTwoUtil.class){
				if(instance == null){
					UpdateUtilTwoUtil.instance = new UpdateUtilTwoUtil();
				}
			}
		}
		return instance;
	}
	
	public static void setInstance(IupdateUtil instance) {
		UpdateUtilTwoUtil.instance = instance;
	}
	
	@Override
	public void sing() {
		System.out.println("22222222222222222222222");
	}

}
