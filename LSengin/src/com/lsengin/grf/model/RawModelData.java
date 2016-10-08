package com.lsengin.grf.model;

public class RawModelData {
	/**
	 * Positions Format XYZ
	 * each position consists of 3 float values
	 * the order is x, y, z
	 * */
	public static final int POSITION_FORMAT_XYZ = 1;
	/**
	 * Positions Format XYZ
	 * each position consists of 4 float values
	 * the order is x, y, z, w
	 * */
	public static final int POSITION_FORMAT_XYZW = 2;
	/**
	 * Normals Format XYZ Normalized
	 * each normal consists of 3 float values
	 * the order is x, y, z
	 * the length is normalized
	 * */
	public static final int NORMAL_FORMAT_XYZ_N = 3;
	/**
	 * Normals Format XYZ Normalized
	 * each normal consists of 3 float values
	 * the order is x, y, z
	 * the length is not normalized
	 * */
	public static final int NORMAL_FORMAT_XYZ_UN = 4;
	
	public int posFormat = 1;
	public int normFormat = 3;
	
	public float[] positions = null;
	public float[] normals = null;
	public float[] texCoords = null;
	public float[] colors = null;
	public int[] indices = null;
}
