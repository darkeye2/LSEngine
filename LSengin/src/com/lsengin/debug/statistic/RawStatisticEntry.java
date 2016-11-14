package com.lsengin.debug.statistic;

import java.util.HashMap;

public class RawStatisticEntry {
	
	private String name = "";
	private HashMap<String, StatisticEntry> subentries = new HashMap<String, StatisticEntry>();
	
	public RawStatisticEntry(String name){
		
		this.name = name;
	}
	
	

}
