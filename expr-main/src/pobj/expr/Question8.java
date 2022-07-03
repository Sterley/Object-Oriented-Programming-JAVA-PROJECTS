package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {
	
	private static Map<String, Integer> env1 = new HashMap<String, Integer>();
	private static Map<String, Integer> env2 = new HashMap<String, Integer>();
	private static Map<String, Integer> env3 = new HashMap<String, Integer>();
	
	
	public static Map<String, Integer> env1(){
		return env1;
	}
	
	public static Map<String, Integer> env2(){
		env2.put("x", 10);
		env2.put("y", 20);
		return env2;
	}
	
	public static Map<String, Integer> env3(){
		env3.put("z", 9);
		return env3;
	}
	
}
