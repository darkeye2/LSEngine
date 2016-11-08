package com.lsengin.math;

import com.lsengin.math.interfaces.IRandomNumberGenerator;

public strictfp class MersenneTwister implements IRandomNumberGenerator {

	private static final int N = 624;
	private static final int M = 397;
	private static final int MATRIX_A = 0x9908b0df;
	private static final int UPPER_MASK = 0x80000000;
	private static final int LOWER_MASK = 0x7fffffff;

	private static final int TEMPERING_MASK_B = 0x9d2c5680;
	private static final int TEMPERING_MASK_C = 0xefc60000;

	private int mt[];
	private int mti;
	private int mag01[];

	public MersenneTwister() {
		this(System.currentTimeMillis());
	}

	public MersenneTwister(long seed) {
		setSeed(seed);
	}

	public void setSeed(long seed) {
		mt = new int[N];

		mag01 = new int[2];
		mag01[0] = 0x0;
		mag01[1] = MATRIX_A;

		mt[0] = (int) (seed & 0xffffffff);
		for (mti = 1; mti < N; mti++) {
			mt[mti] = (1812433253 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30)) + mti);
		}
	}

	public int nextInt() {
		int y;

		if (mti >= N) {
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11;
		y ^= (y << 7) & TEMPERING_MASK_B;
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18);

		return y;
	}

	public short nextShort() {
		int y;

		if (mti >= N) {
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11;
		y ^= (y << 7) & TEMPERING_MASK_B;
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18);

		return (short) (y >>> 16);
	}

	public char nextChar() {
		int y;

		if (mti >= N) {
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11;
		y ^= (y << 7) & TEMPERING_MASK_B;
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18);

		return (char) (y >>> 16);
	}

	public boolean nextBoolean() {
		int y;

		if (mti >= N) {
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11;
		y ^= (y << 7) & TEMPERING_MASK_B;
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18);

		return (boolean) ((y >>> 31) != 0);
	}

	public boolean nextBoolean(float probability) {
		int y;

		if (probability < 0.0f || probability > 1.0f)
			throw new IllegalArgumentException("probability must be between 0.0 and 1.0 inclusive.");
		if (probability == 0.0f)
			return false;
		else if (probability == 1.0f)
			return true;
		if (mti >= N) {
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11;
		y ^= (y << 7) & TEMPERING_MASK_B;
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18);

		return (y >>> 8) / ((float) (1 << 24)) < probability;
	}

	public double nextDouble() {
		int y;
		int z;

		if (mti >= N) {
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11;
		y ^= (y << 7) & TEMPERING_MASK_B;
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18);

		if (mti >= N) {
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			for (; kk < N - 1; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			z = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (z >>> 1) ^ mag01[z & 0x1];

			mti = 0;
		}

		z = mt[mti++];
		z ^= z >>> 11;
		z ^= (z << 7) & TEMPERING_MASK_B;
		z ^= (z << 15) & TEMPERING_MASK_C;
		z ^= (z >>> 18);

		return ((((long) (y >>> 6)) << 27) + (z >>> 5)) / (double) (1L << 53);
	}

	@Override
	public float nextFloat() {
		int y;

		if (mti >= N) 
		{
			int kk;
			final int[] mt = this.mt; 
			final int[] mag01 = this.mag01; 

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11;
		y ^= (y << 7) & TEMPERING_MASK_B; 
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18); 

		return (y >>> 8) / ((float) (1 << 24));
	}

	public long nextLong() {
		int y;
		int z;

		if (mti >= N)
		{
			int kk;
			final int[] mt = this.mt;
			final int[] mag01 = this.mag01;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; 
		y ^= (y << 7) & TEMPERING_MASK_B;
		y ^= (y << 15) & TEMPERING_MASK_C;
		y ^= (y >>> 18); 

		if (mti >= N)
		{
			int kk;
			final int[] mt = this.mt; 
			final int[] mag01 = this.mag01; 

			for (kk = 0; kk < N - M; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			for (; kk < N - 1; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			z = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (z >>> 1) ^ mag01[z & 0x1];

			mti = 0;
		}

		z = mt[mti++];
		z ^= z >>> 11; 
		z ^= (z << 7) & TEMPERING_MASK_B;
		z ^= (z << 15) & TEMPERING_MASK_C;
		z ^= (z >>> 18); 

		return (((long) y) << 32) + (long) z;
	}

}
