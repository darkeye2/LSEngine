package com.lsengin.debug;

public class StatValue {
	private long n = 1;
	private double avg = 0;
	
	private long max = Long.MIN_VALUE;
	private long min = Long.MAX_VALUE;
	
	private long first = Long.MIN_VALUE;
	private long last = 0;
	
	public boolean push(long x){
		//update first / last
		if(first == Long.MIN_VALUE){
			first = x;
		}
		last = x;
		
		//calculate avg
		avg += ((x-avg)/(double)n);
		n++;
		
		//fix max and min value
		max = Math.max(max, x);
		min = Math.min(min, x);
		
		//return if value is an outlier
		if(n < 10){
			return false;
		}
		return x>(avg*1.5);
	}
	
	public long getCount(){
		return n-1;
	}
	
	public long getMax() {
		return max;
	}

	public long getMin() {
		return min;
	}

	public long getFirst() {
		return first;
	}

	public long getLast() {
		return last;
	}

	public double getAvg(){
		return avg;
	}
}
