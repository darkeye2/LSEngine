package com.lsengin.grf.shader;

import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.GL3ES3;

public class GLShader implements Comparable<GLShader>{
	public static final int VERTEX = GL3ES3.GL_VERTEX_SHADER;
	public static final int GEOMETRY = GL3ES3.GL_GEOMETRY_SHADER;
	public static final int TESS_CONTROL = GL3ES3.GL_TESS_CONTROL_SHADER;
	public static final int TESS_EVALUATION = GL3ES3.GL_TESS_EVALUATION_SHADER;
	public static final int FRAGMENT = GL3ES3.GL_FRAGMENT_SHADER;
	
	protected String name = "";
	protected String fileName = "";
	protected String shaderPath = "/shader/"; 
	protected int type;
	protected int id = 0;
	
	private GLShaderLog log = null;
	
	public GLShader(String path, String fileName, String shaderName, int type){
		this.shaderPath = path;
		this.name = shaderName;
		this.fileName = fileName;
		this.type = type;
	}
	
	public GLShader(String path, String fileName, int type){
		this(path, fileName, null, type);
	}
	
	public GLShader(String fileName, int type){
		this("", fileName, type);
	}
	
	public GLShader init(GL2ES3 gl){
		return GLShaderManager.loadShader(gl, this);
	}
	
	public void destroy(GL2ES3 gl){
		gl.glDeleteShader(this.id);
	}
	
	public int getId(){
		return id;
	}
	
	public String getFilePath(){
		return this.shaderPath+fileName;
	}
	
	public String getPath(){
		return this.shaderPath;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getType(){
		return this.type;
	}
	
	public GLShaderLog getLog(){
		return this.log;
	}
	
	public void setLog(GLShaderLog gll){
		this.log = gll;
	}

	@Override
	public int compareTo(GLShader arg0) {
		return type - arg0.type;
	}
}
