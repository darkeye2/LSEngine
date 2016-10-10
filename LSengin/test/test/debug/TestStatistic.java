package test.debug;

import com.lsengin.debug.ClassStatistic;
import com.lsengin.debug.DefaultPackage;
import com.lsengin.debug.HTMLStatisticWriter;
import com.lsengin.debug.Profiler;

public class TestStatistic {
	
	public static void main(String[] args){
		Profiler.setStatistic("TestStatistic");
		for(int i = 0; i < 30; i++){
			Profiler.startMeasure(TestStatistic.class, "render");
			try {
				Thread.sleep((long) (Math.random()*10+5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Profiler.stopMeasure(TestStatistic.class, "render");
		}
		
		for(int i = 0; i < 140; i++){
			Profiler.startMeasure(TestStatistic.class, "doSomething");
			try {
				Thread.sleep((long) (Math.random()*20+15));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Profiler.stopMeasure(TestStatistic.class, "doSomething");
		}
		
		for(int i = 0; i < 30; i++){
			Profiler.startMeasure(TestStatistic.class, "mix");
			try {
				Thread.sleep((long) (Math.random()*10+10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Profiler.stopMeasure(TestStatistic.class, "mix");
		}
		
		for(int i = 0; i < 140; i++){
			Profiler.startMeasure(DefaultPackage.class, "doSomething");
			try {
				Thread.sleep((long) (Math.random()*20+15));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Profiler.stopMeasure(DefaultPackage.class, "doSomething");
		}
		
		for(int i = 0; i < 30; i++){
			Profiler.startMeasure(ClassStatistic.class, "mix");
			try {
				Thread.sleep((long) (Math.random()*10+10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Profiler.stopMeasure(ClassStatistic.class, "mix");
		}
		
		Profiler.printAll(new HTMLStatisticWriter());
	}
}
