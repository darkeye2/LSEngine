package com.lsengin.debug;

import java.util.ArrayList;
import java.util.List;

public class MethodStatistic extends StatisticEntry{

	public MethodStatistic(String name) {
		super(name);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getFullClassCount() {
		return 0;
	}

	@Override
	public int getFullPackageCount() {
		return 0;
	}

	@Override
	public int getFullMethodCount() {
		return 0;
	}

	@Override
	public List<StatisticEntry> getAll() {
		ArrayList<StatisticEntry> entries = new ArrayList<StatisticEntry>();
		entries.add(this);
		return entries;
	}

	@Override
	public void add(MeasureRequest mr) {
		update(mr);
	}

	@Override
	public List<StatisticEntry> getChildren() {
		return new ArrayList<StatisticEntry>();
	}

}
