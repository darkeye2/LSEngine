package com.lsengin.math;

import com.lsengin.math.interfaces.IMath;
import com.lsengin.math.interfaces.IRandomNumberGenerator;

public class LSMath implements IMath{
	
	public static IRandomNumberGenerator getMersenneTwisterRNG(){
		
		return new MersenneTwister();
	}
	
	public static IRandomNumberGenerator getISAACRNG(){
		
		return new ISAACRandom();
	}
	
	

}
