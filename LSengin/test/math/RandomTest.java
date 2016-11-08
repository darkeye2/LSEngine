package math;

import com.lsengin.debug.HTMLStatisticWriter;
import com.lsengin.debug.Profiler;
import com.lsengin.math.LSRandom;

public class RandomTest {

	public static void main(String[] args){
		Profiler.start();
		Profiler.setStatistic("Math Random");
		
		//generate 1000000 random doubles (measure each)
		for(int i = 0; i < 10000000; i++){
			double d;
			
			//standart random
			Profiler.startMeasure(Math.class, "random");
			d = Math.random();
			Profiler.stopMeasure(Math.class, "random");
			
			//ls random
			Profiler.startMeasure(LSRandom.class, "random");
			d = LSRandom.random();
			Profiler.stopMeasure(LSRandom.class, "random");
		}
		
		//generate 100 blocks of 1000 random doubles
		for(int i = 0; i < 10000; i++){
			double d;
			//System.out.println("index: "+i);
			
			//standart random
			Profiler.startMeasure(Math.class, "randomH");
			for(int j = 0; j< 10000; j++){
				d = Math.random();
			}
			Profiler.stopMeasure(Math.class, "randomH");
			
			//ls random
			Profiler.startMeasure(LSRandom.class, "randomH");
			for(int j = 0; j< 10000; j++){
				d = LSRandom.random();
			}
			Profiler.stopMeasure(LSRandom.class, "randomH");
		}
		
		Profiler.stop();
		Profiler.printAll(new HTMLStatisticWriter());
		
		
	}
}
