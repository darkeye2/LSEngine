package com.lsengin.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import com.lsengin.debug.statistic.Statistic;
import com.lsengin.debug.statistic.io.IStatisticWriter;

public class Profiler {
	//protected static ArrayList<MeasureRequest> requests = new ArrayList<MeasureRequest>();
	//private static Object lock = new Object();
	
	protected static ConcurrentLinkedQueue<MeasureRequest> requests = 
			new ConcurrentLinkedQueue<MeasureRequest>();
	
	protected static HashMap<String, Statistic> statistics = new HashMap<String, Statistic>();
	protected volatile static Statistic activeStatistic = null;
	
	private static volatile boolean run = false;
	private static volatile boolean ready = false;
	
	static{
		statistics.put("default", new Statistic("Default Statistic"));
		activeStatistic = statistics.get("default");		
	}
	
	public static void start(){
		if(Debugger.DEBUG){
			run = true;
			Thread th = new Thread(new Profiler.ProfilerWorker());
			th.start();
		}
	}
	
	public static void stop(){
		run = false;
	}
	
	public static void startMeasure(Class<?> owner, String methodName){
		if(Debugger.DEBUG)
			addRequest(new MeasureRequest(owner, methodName, MeasureRequest.MEASURE_START));
	}
	
	public static void stopMeasure(Class<?> owner, String methodName){
		if(Debugger.DEBUG)
			addRequest(new MeasureRequest(owner, methodName, MeasureRequest.MEASURE_STOP));
	}
	
	public static void count(Class<?> owner, String methodName){
		if(Debugger.DEBUG)
			addRequest(new MeasureRequest(owner, methodName, MeasureRequest.MEASURE_COUNT));
	}
	
	public static Statistic setStatistic(String name){
		if(!Debugger.DEBUG)
			return null;
		
		Statistic s = statistics.get(name);
		if(s == null){
			s = new Statistic(name+" Statistic");
			statistics.put(name, s);
		}
		
		activeStatistic = s;
		
		return s;
	}
	
	private static void addRequest(MeasureRequest rq){
		/*synchronized(lock){
			requests.add(rq);
		}*/
		requests.add(rq);
	}
	
	private static MeasureRequest getRequest(){
		/*synchronized(lock){
			//return requests.poll();
			return requests.remove(0);
		}*/
		return requests.poll();
	}
	
	public static void printAll(IStatisticWriter sw){
		if(!Debugger.DEBUG)
			return;
		
		while(!ready){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(Statistic s : statistics.values()){
			sw.print(s);
		}
	}
	
	
	
	
	private static class ProfilerWorker implements Runnable{
		@Override
		public void run() {
			while(run){
				handleRequests();
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			boolean openRequestsFound = false;
			
			do{
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(requests.size() > 0){
					openRequestsFound = true;
				}
				
				handleRequests();
				
			}while(openRequestsFound);
			
			ready = true;
		}
		
		private void handleRequests(){
			while(requests.size() > 0){
				if(activeStatistic  != null){
					activeStatistic.add(getRequest());
				}
			}
		}
		
	}
	
}
