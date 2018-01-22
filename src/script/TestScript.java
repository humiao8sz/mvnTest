package script;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class TestScript {
	public static void main(String args[]) {
/*		ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
		ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");
		try{
			defineScriptFunction(jsEngine);
		}catch(ScriptException e){
			e.printStackTrace();
		}
		
		System.out.println("Native Sec Start: "+System.currentTimeMillis());
		for(int i = 0;i<1;i++){
			javaMethod();
		}
		System.out.println("Native Sec End: "+System.currentTimeMillis());

		System.out.println("Secript Sec Start: "+System.currentTimeMillis());
		try{
			for(int i = 0;i<1;i++){
				invokeScriptFunctionFromEngine(jsEngine);
			}
		}catch(ScriptException e){
			e.printStackTrace();
		}*/
	}

	private static void defineScriptFunction(ScriptEngine engine) throws ScriptException {
		engine.eval("function sayHello(name) {" + "  var a = 0;  for(var i =0;i<1000000;i++){a = a+i};" + "}");
	}

	private static void invokeScriptFunctionFromEngine(ScriptEngine engine) throws ScriptException {
		engine.eval("sayHello('World!')");
	}
	
	private static void javaMethod() {
		int a = 0;  
		for(int i =0;i<1000000;i++){
			a = a + i;
		}
	}
}
