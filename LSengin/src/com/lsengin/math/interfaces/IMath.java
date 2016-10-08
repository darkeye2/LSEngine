package com.lsengin.math.interfaces;

public interface IMath {
	static final float EPSILON = Float.MIN_VALUE;
	static final float MAX_REAL = Float.MAX_VALUE;
	static final float PI = (float)(4.0*Math.atan(1.0));
	static final double PI_d = (4.0*Math.atan(1.0));;
	static final float TWO_PI = PI*2;
	static final float HALF_PI = PI/2;
	static final float INV_PI = 1/PI;
	static final float INV_TWO_PI = 1/TWO_PI;
	static final float DEG_TO_RAD = 0;
	static final float RAD_TO_DEG = 0; 
}
