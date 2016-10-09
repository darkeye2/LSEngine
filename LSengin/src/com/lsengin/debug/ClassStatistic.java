package com.lsengin.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassStatistic extends StatisticEntry{
	protected String name = "";
	protected HashMap<String, MethodStatistic> methods = new HashMap<String, MethodStatistic>();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticEntry> getAll() {
		ArrayList<StatisticEntry> entries = new ArrayList<StatisticEntry>();
		entries.add(this);
		entries.addAll(methods.values());

		return entries;
	}

	@Override
	public void add(MeasureRequest mr) {
		// TODO Auto-generated method stub
		
	}

}
