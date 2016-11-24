package com.lsengin.math;

import java.util.Arrays;

import com.lsengin.math.interfaces.IRandomNumberGenerator;

/**
 * ISAAC Random Number generator
 * 
 * Code from https://rosettacode.org/wiki/The_ISAAC_Cipher#Java
 * 
 * Comments and Details see https://rosettacode.org/wiki/The_ISAAC_Cipher#Java
 * 
 */

public class ISAACRandom implements IRandomNumberGenerator {

	private int aa, bb, cc;

	private final int[] randResult = new int[256]; // output of last generation
	private int valuesUsed; // the number of values already used up from
							// randResult

	// internal generator state
	private final int[] mm = new int[256];

	public ISAACRandom() {
		this(System.currentTimeMillis());
	}

	public ISAACRandom(long seed) {
		setSeed(seed);
	}

	public void setSeed(long seed) {
		int[] arraySeed = new int[256];
		arraySeed[0] = (int) (seed & 0xFFFFFFFF);
		arraySeed[1] = (int) (seed >>> 32);
		init(arraySeed);
	}

	private static void mix(int[] s) {
		s[0] ^= s[1] << 11;
		s[3] += s[0];
		s[1] += s[2];
		s[1] ^= s[2] >>> 2;
		s[4] += s[1];
		s[2] += s[3];
		s[2] ^= s[3] << 8;
		s[5] += s[2];
		s[3] += s[4];
		s[3] ^= s[4] >>> 16;
		s[6] += s[3];
		s[4] += s[5];
		s[4] ^= s[5] << 10;
		s[7] += s[4];
		s[5] += s[6];
		s[5] ^= s[6] >>> 4;
		s[0] += s[5];
		s[6] += s[7];
		s[6] ^= s[7] << 8;
		s[1] += s[6];
		s[7] += s[0];
		s[7] ^= s[0] >>> 9;
		s[2] += s[7];
		s[0] += s[1];
	}

	private void init(int[] seed) {
		if (seed != null && seed.length != 256) {
			seed = Arrays.copyOf(seed, 256);
		}
		aa = bb = cc = 0;
		int[] initState = new int[8];
		Arrays.fill(initState, 0x9e3779b9); // the golden ratio

		for (int i = 0; i < 4; i++) {
			mix(initState);
		}

		for (int i = 0; i < 256; i += 8) {
			if (seed != null) {
				for (int j = 0; j < 8; j++) {
					initState[j] += seed[i + j];
				}
			}
			mix(initState);
			for (int j = 0; j < 8; j++) {
				mm[i + j] = initState[j];
			}
		}

		if (seed != null) {
			for (int i = 0; i < 256; i += 8) {
				for (int j = 0; j < 8; j++) {
					initState[j] += mm[i + j];
				}

				mix(initState);

				for (int j = 0; j < 8; j++) {
					mm[i + j] = initState[j];
				}
			}
		}

		valuesUsed = 256; // Make sure generateMoreResults() will be called by
							// the next next() call.
	}
	
	private void generateMoreResults() {
		cc++;
		bb += cc;
 
		for (int i=0; i<256; i++) {
			int x = mm[i];
			switch (i&3) {
			case 0:
				aa = aa^(aa<<13);
				break;
			case 1:
				aa = aa^(aa>>>6);
				break;
			case 2:
				aa = aa^(aa<<2);
				break;
			case 3:
				aa = aa^(aa>>>16);
				break;
			}
			aa = mm[i^128] + aa;
			int y = mm[i] = mm[(x>>>2) & 0xFF] + aa + bb;
			randResult[i] = bb = mm[(y>>>10) & 0xFF] + x;
		}
 
		valuesUsed = 0;
	}
	
	protected int next(int bits) {
		if (valuesUsed == 256) {
			generateMoreResults();
			assert(valuesUsed == 0);
		}
		int value = randResult[valuesUsed];
		valuesUsed++;
		return value >>> (32-bits);
	}

	@Override
	public double nextDouble() {
		return (((long)(next(26)) << 27) + next(27)) / (double)(1L << 53);
	}

	@Override
	public float nextFloat() {
		return next(24) / ((float)(1 << 24));
	}

	@Override
	public int nextInt() {
		return this.next(32);
	}

	@Override
	public long nextLong() {
		return ((long)(next(32)) << 32) + next(32);
	}

	@Override
	public boolean nextBoolean() {
		return next(1) != 0;
	}

	@Override
	public boolean nextBoolean(float f) {
		if (f < 0.0f || f > 1.0f)
			throw new IllegalArgumentException("probability must be between 0.0 and 1.0 inclusive.");
		if (f == 0.0f)
			return false;
		else if (f == 1.0f)
			return true;
		
		return (this.nextFloat() < f);
	}

	@Override
	public char nextChar() {
		long unsignedNext = nextInt() & 0xFFFFFFFFL;
		return (char) (unsignedNext % 95 + 32);
	}

	@Override
	public short nextShort() {
		return (short) this.next(16);
	}

}
