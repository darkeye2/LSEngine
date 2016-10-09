package com.lsengin.debug;

public class MeasureRequest {
	public static final int TIME_DEVISION_FACTOR = 1000000;
	public static final int MEASURE_START = 0;
	public static final int MEASURE_STOP = 1;
	public static final int MEASURE_COUNT = 2;
	
	public String className = "";
	public String methodName = "";
	public long time;
	public int type;
	
	public MeasureRequest(Class<?> c, String methodName, int type){
		this.className = c.getCanonicalName();
		this.methodName = methodName;
		this.type = type;
		this.time = System.nanoTime();
	}
	
	public String getQName(){
		return className+"."+methodName+"()";
	}
	
	public String getMethodName(){
		return this.methodName;
	}
	
	public String getClassName(){
		return className;
	}
}
