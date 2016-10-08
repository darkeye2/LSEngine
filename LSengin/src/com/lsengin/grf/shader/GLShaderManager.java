package com.lsengin.grf.shader;

import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.util.glsl.ShaderCode;

public class GLShaderManager {
	// shader map
	protected static GLShaderMap shaders = new GLShaderMap();

	public static GLShader getShader(String name) {
		if (shaders.containsKey(name)) {
			return shaders.get(name);
		}
		return null;
	}

	public static void cleanup(GL2ES3 gl) {
		shaders.clearReferences(gl);
	}

	public static GLShader loadShader(GL2ES3 gl, GLShader shader) {
		cleanup(gl);
		GLShader p = getShader(shader.getFilePath());
		if (p != null) {
			return p;
		}

		String sc = GLShaderManager.loadShader(gl, shader.getPath(), shader.getFileName(), shader.getType());
		int id = GLShaderManager.createShader(gl, sc, shader.getType());

		GLShaderLog log = new GLShaderLog(gl, id);

		shader.setLog(log);
		shader.id = id;

		shaders.put(shader.getFilePath(), shader);

		return shader;
	}

	protected static String loadShader(GL2ES3 gl, String path, String name, int type) {
		ShaderCode sc = ShaderCode.create(gl, type, GLShaderManager.class, path, null, name, "sl", null, true);
		sc.defaultShaderCustomization(gl, true, true);
		CharSequence[][] src = sc.shaderSource();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < src.length; i++){
			for(int j = 0; j < src[i].length; j++){
				sb.append(src[i][j]);
			}
		}

		return sb.toString();
	}

	protected static int createShader(GL2ES3 gl, String shaderSource, int type) {
		int shaderID = gl.glCreateShader(type);
		int[] tmp = new int[1];
		gl.glShaderSource(shaderID, 1, new String[]{shaderSource.toString()}, new int[]{shaderSource.length()}, 0);
		gl.glCompileShader(shaderID);
		gl.glGetShaderiv(shaderID, GL2ES3.GL_COMPILE_STATUS, tmp, 0);
		if (tmp[0] == GL2ES3.GL_FALSE) {
			gl.glGetShaderiv(shaderID, GL2ES3.GL_INFO_LOG_LENGTH, tmp, 0);
	        final int length = tmp[0];
	        String out = null;
	        if (length > 0) {
	            byte[] infoLog = new byte[length];
	            gl.glGetShaderInfoLog(shaderID, length, tmp, 0, infoLog, 0);
	            out = new String(infoLog);
	            System.out.println(out);
	        } 
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		return shaderID;
	}
}
