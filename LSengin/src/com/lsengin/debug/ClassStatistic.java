package com.lsengin.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassStatistic extends StatisticEntry{
	protected HashMap<String, StatisticEntry> children = new HashMap<String, StatisticEntry>();
	
	public ClassStatistic(String name) {
		super(name);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getFullClassCount() {
		return this.getClassCount();
	}

	@Override
	public int getFullPackageCount() {
		return 0;
	}

	@Override
	public int getFullMethodCount() {
		return this.getMethodCount();
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
		//System.out.println("Call add with: "+mr.getClassName());
		String[] path = mr.getClassName().split("\\.");
		update(mr);
		
		String key = "";
		if(path.length > 0 && path[0] != ""){
			key = path[0];
		}else{
			key = mr.getMethodName();
		}
		
		if(!children.containsKey(key)){
			StatisticEntry e = null;
			
			if(path.length > 0 && path[0] != ""){
				e = new ClassStatistic(path[0]);
				classes++;
			}else{
				e = new MethodStatistic(mr.methodName);
				methods++;
			}
			e.setParent(this);
			children.put(key, e);
		}
		
		mr.className = join(path, ".", 1);
		//System.out.println("Key: "+key+"; Inserted: "+children.containsKey(key));
		children.get(key).add(mr);
	}

	@Override
	public List<StatisticEntry> getChildren() {
		return new ArrayList<StatisticEntry>(children.values());
	}

}
