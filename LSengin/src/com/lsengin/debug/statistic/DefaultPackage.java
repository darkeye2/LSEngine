package com.lsengin.debug.statistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lsengin.debug.MeasureRequest;

public class DefaultPackage extends StatisticEntry {
	protected HashMap<String, StatisticEntry> children = new HashMap<String, StatisticEntry>();

	public DefaultPackage(String name) {
		super("");
	}
	
	public String getFullPath(){
		
		return "";
	}
	
	@Override
	public int getFullClassCount() {
		int fc = this.getClassCount(); 
		for(StatisticEntry e : children.values()){
			fc += e.getFullClassCount();
		}
		
		return fc;
	}

	@Override
	public int getFullPackageCount() {
		int fp = this.getPackageCount(); 
		for(StatisticEntry e : children.values()){
			fp += e.getFullPackageCount();
		}
		
		return fp;
	}

	@Override
	public int getFullMethodCount() {
		int fm = this.getMethodCount(); 
		for(StatisticEntry e : children.values()){
			fm += e.getFullMethodCount();
		}
		
		return fm;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticEntry> getAll() {
		ArrayList<StatisticEntry> entries = new ArrayList<StatisticEntry>();
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
				this.subpackages++;
			}else{
				e = new ClassStatistic(path[0]);
				this.classes++;
			}
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
