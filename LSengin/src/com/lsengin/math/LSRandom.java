package com.lsengin.math;

import com.lsengin.math.interfaces.IRandomNumberGenerator;

public class LSRandom{
	private static IRandomNumberGenerator rng = new MersenneTwister();
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	public static void setRNG(IRandomNumberGenerator rng){
		LSRandom.rng = rng;
	}
	
	public static double random(){
		return nextDouble();
	}
	
	public static int random(int min, int max){
		return (int) Math.round(random()*(max-min)+min);
	}
	
	public static long random(long min, long max){
		return Math.round(random()*(max-min)+min);
	}
	
	public static String randomString(int length, String alphabet){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<length; i++){
			sb.append(alphabet.charAt(random(0, alphabet.length())));
		}
		
		return sb.toString();
	}
	
	public static String randomString(int length){
		return randomString(length, ALPHABET);
	}

	public static double nextDouble() {
		return rng.nextDouble();
	}

	public static float nextFloat() {
		return rng.nextFloat();
	}

	public static int nextInt() {
		return rng.nextInt();
	}

	public static long nextLong() {
		return rng.nextLong();
	}

	public static boolean nextBoolean() {
		return rng.nextBoolean();
	}

	public static boolean nextBoolean(float f) {
		return rng.nextBoolean(f);
	}

	public static char nextChar() {
		return rng.nextChar();
	}

	public static short nextShort() {
		return rng.nextShort();
	}

}
