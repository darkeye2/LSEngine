package com.lsengin.debug;

import java.util.List;

public abstract class StatisticEntry {
	protected StatValue value = new StatValue();
	protected String name = "";
	protected long count = 0;
	
	protected long startTime = 0;
	protected boolean started = false;
	
	protected long firstCallTimestamp = 0;
	protected long lastCallTimestamp = 0;
	
	protected StatisticEntry parent = null;
	
	public StatisticEntry(String name){
		this.name = name;
	}
	
	public void setParent(StatisticEntry p){
		this.parent = p;
	}
	
	public void update(MeasureRequest r){
		if(this.firstCallTimestamp == 0){
			this.firstCallTimestamp = r.time;
		}
		this.lastCallTimestamp = r.time;
		
		switch(r.type){
		case MeasureRequest.MEASURE_COUNT:
			count ++;
			break;
		case MeasureRequest.MEASURE_START:
			this.startTime = r.time;
			started = true;
			break;
		case MeasureRequest.MEASURE_STOP:
			if(this.started){
				started = false;
				value.push(r.time-startTime);
			}
			break;
		}
	}
	
	public long getCount(){
		if(count > 0){
			return count;
		}
		return value.getCount();
	}
	
	public String getFullPath(){
		if(parent == null){
			return this.name;
		}
		
		return parent.getFullPath()+"."+this.name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public long getFirstCallInMs(){
		return value.getFirst()/MeasureRequest.TIME_DEVISION_FACTOR;
	}
	
	public long getFirstCallTimeInMs(){
		return this.firstCallTimestamp/MeasureRequest.TIME_DEVISION_FACTOR;
	}
	
	public long getLastCallInMs(){
		return value.getLast()/MeasureRequest.TIME_DEVISION_FACTOR;
	}
	
	public long getLastCallTimeInMs(){
		return this.lastCallTimestamp/MeasureRequest.TIME_DEVISION_FACTOR;
	}
	
	public float getAvgDurationInMs(){
		return (float) (value.getAvg()/MeasureRequest.TIME_DEVISION_FACTOR);
	}
	
	public long getMaxDurationInMs(){
		return value.getMax()/MeasureRequest.TIME_DEVISION_FACTOR;
	}
	
	public long getMinDurationInMs(){
		return value.getMin()/MeasureRequest.TIME_DEVISION_FACTOR;
	}
	
	protected static String join(String[] arr, String sep, int offset){
		String ret = "";
		for(int i = offset; i<arr.length; i++){
			ret += arr[i];
			if(i+1 < arr.length){
				ret += sep;
			}
		}
		
		return ret;
	}
	
	public abstract String toString();
	public abstract List<StatisticEntry> getAll();
	public abstract List<StatisticEntry> getChildren();
	public abstract void add(MeasureRequest mr);

}
