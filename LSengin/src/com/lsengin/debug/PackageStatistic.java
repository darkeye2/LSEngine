package com.lsengin.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PackageStatistic extends StatisticEntry{
	protected HashMap<String, StatisticEntry> children = new HashMap<String, StatisticEntry>();
	
	protected int containsClasses = 0;
	
	public PackageStatistic(String name){
		super(name);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticEntry> getAll() {
		ArrayList<StatisticEntry> entries = new ArrayList<StatisticEntry>();
		entries.add(this);
		for(StatisticEntry se : children.values()){
			entries.addAll(se.getAll());
		}
		
		return entries;
	}

	@Override
	public void add(MeasureRequest mr) {
		String[] path = mr.getClassName().split("\\.");
		update(mr);
		
		if(!children.containsKey(path[0])){
			StatisticEntry e = null;
			
			if(path.length > 1 && !Character.isUpperCase(path[0].codePointAt(0))){
				e = new PackageStatistic(path[0]);
			}else{
				this.containsClasses++;
				e = new ClassStatistic(path[0]);
			}
			e.setParent(this);
			children.put(path[0], e);
		}
		
		mr.className = join(path, ".", 1);
		children.get(path[0]).add(mr);
	}

	@Override
	public List<StatisticEntry> getChildren() {
		return new ArrayList<StatisticEntry>(children.values());
	}

}
