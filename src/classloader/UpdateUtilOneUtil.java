package classloader;

public class UpdateUtilOneUtil implements IupdateUtil {
	public static volatile IupdateUtil instance;
	
	public static IupdateUtil getInstance() {
		if(instance == null){
			synchronized(UpdateUtilOneUtil.class){
				if(instance == null){
					UpdateUtilOneUtil.instance = new UpdateUtilOneUtil();
				}
			}
		}
		return instance;
	}


	public static void setInstance(IupdateUtil instance) {
		UpdateUtilOneUtil.instance = instance;
	}

	@Override
	public void sing() {
		//System.out.println("1111111111111111111111111");
	}

}
