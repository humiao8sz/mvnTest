package classloader;  
  
import java.lang.reflect.Method;  
  
public class ClassIdentity {  
  
    public static void main(String[] args) {  
        new ClassIdentity().testClassIdentity2();  
    }  
  
    public void testClassIdentity() {
        String classDataRootPath = "D:\\WorkSpace\\Test\\bin";  
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);  
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);  
        String className = "com.example.Sample";  
        try {
            Class<?> class1 = fscl1.loadClass(className);  // 加载Sample类  
            Object obj1 = class1.newInstance();  // 创建对象  
            
            Class<?> class2 = fscl2.loadClass(className);  
            Object obj2 = class2.newInstance();  
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);  
            setSampleMethod.invoke(obj1, obj2);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }   
    public void testClassIdentity2() {
    	
    	
    	String classDataRootPath = "D:\\WorkSpace\\Test\\bin";
    	FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        String basicClassName = "com.example.CalculatorBasic";  
        try {
            Class<?> clazz = fscl1.loadClass(basicClassName);  // 加载一个版本的类  
            ICalculator calculator = (ICalculator) clazz.newInstance();  // 创建对象  
            System.out.println(calculator.getVersion());
            System.out.println(clazz.getClassLoader());
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
    } 
}  