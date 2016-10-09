package com.lsengin.debug;

import java.util.ArrayList;
import java.util.HashMap;

public class Profiler {
	protected static ArrayList<MeasureRequest> requests = new ArrayList<MeasureRequest>();
	private static Object lock = new Object();
	
	protected static HashMap<String, Statistic> statistics = new HashMap<String, Statistic>();
	protected volatile static Statistic activeStatistic = null;
	
	private static volatile boolean run = true;
	
	static{
		statistics.put("default", new Statistic("Default Statistic"));
		activeStatistic = statistics.get("default");
		
		if(Debugger.DEBUG){
			Thread th = new Thread(new Profiler.ProfilerWorker());
			th.start();
		}
	}
	
	public static void startMeasure(Class<?> owner, String methodName){
		addRequest(new MeasureRequest(owner, methodName, MeasureRequest.MEASURE_START));
	}
	
	public static void stopMeasure(Class<?> owner, String methodName){
		addRequest(new MeasureRequest(owner, methodName, MeasureRequest.MEASURE_STOP));
	}
	
	public static void count(Class<?> owner, String methodName){
		addRequest(new MeasureRequest(owner, methodName, MeasureRequest.MEASURE_COUNT));
	}
	
	public static Statistic setStatistic(String name){
		Statistic s = statistics.get(name);
		if(s == null){
			s = new Statistic(name+" Statistic");
			statistics.put(name, s);
		}
		
		activeStatistic = s;
		
		return s;
	}
	
	private static void addRequest(MeasureRequest rq){
		synchronized(lock){
			requests.add(rq);
		}
	}
	
	private static MeasureRequest getRequest(){
		synchronized(lock){
			return requests.remove(0);
		}
	}
	
	
	
	
	private static class ProfilerWorker implements Runnable{
		@Override
		public void run() {
			while(run){
				while(requests.size() > 0){
					if(activeStatistic  != null){
						activeStatistic.add(getRequest());
					}
				}
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
