package com.lsengin.grf.shader;

import java.util.Arrays;

import com.jogamp.opengl.GL2ES3;

public class GLProgramm {
	protected String name = "";
	protected String uName = "";
	protected int id = 0;
	
	protected GLShader[] shaders = null;
	protected GLCompilerLog log = null;
	
	public GLProgramm(GLShader[] shaders, String name){
		Arrays.sort(shaders);
		this.shaders = shaders;
		this.name = name;
	}
	
	public GLProgramm(String shaderPath, String[] shaderNames, int[] shaderTypes, String progName){
		if(shaderNames.length == shaderTypes.length){
			shaders = new GLShader[shaderNames.length];
			for(int i = 0; i < shaderNames.length; i++){
				shaders[i] = new GLShader(shaderPath, shaderNames[i], shaderTypes[i]);
			}
		}
		
		this.name = progName;
		Arrays.sort(shaders);
	}
	
	public void bindAttribute(GL2ES3 gl, int attribute, String attrName){
		gl.glBindAttribLocation(id, attribute, attrName);
	}
	
	public GLProgramm init(GL2ES3 gl){
		return GLProgramManager.loadProgram(gl, this);
	}
	
	protected void createUniqueName(){
		String s = "";
		for(int i = 0; i < shaders.length; i++){
			s += shaders[i].getId();
			if((i+1)<shaders.length){
				s += "_";
			}
		}
		
		System.out.println("Unique ID: "+s);
		this.uName = s;
	}
	
	protected void createCompilerLog(){
		this.log = new GLCompilerLog(shaders);
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getUniqueName(){
		return this.uName;
	}
	
	public GLCompilerLog getLog(){
		return this.log;
	}
}
