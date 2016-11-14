package com.lsengin.debug.statistic.io;

import com.lsengin.debug.statistic.DefaultPackage;
import com.lsengin.debug.statistic.Statistic;

public interface IStatisticWriter {
	public void print(DefaultPackage dp);
	public void print(Statistic s);
}
