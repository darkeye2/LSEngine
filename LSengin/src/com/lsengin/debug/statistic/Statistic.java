package com.lsengin.debug.statistic;

import java.util.List;

import com.lsengin.debug.MeasureRequest;

public class Statistic {
	private String name = "";
	private DefaultPackage defPackage = new DefaultPackage("default");
	
	public Statistic(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void add(MeasureRequest mr){
		defPackage.add(mr);
	}
	
	public StatisticEntry getDefaultPackage(){
		return defPackage;
	}
	
	public List<StatisticEntry> getAllEntries(){
		return defPackage.getAll();
	}

}