package com.lsengin.grf.shader;

import java.util.ArrayList;

public class GLCompilerLog {
	private ArrayList<GLShaderLog> logs = new ArrayList<GLShaderLog>();
	private int programLength = 0;
	private boolean success = true;
	
	public GLCompilerLog(GLShader[] shaders){
		for(GLShader s : shaders){
			logs.add(s.getLog());
			programLength += s.getLog().getSrcLength();
			success = success && s.getLog().getStatus();
		}
	}
	
	public boolean containsError(){
		return success;
	}
	
	public String convertSize(int bytes){
		if(bytes < 100){
			return bytes+" byte";
		}
		
		//calculate
		int i = 0;
		float s = bytes;
		do{
			s /= 1024;
			i++;
		}while(s > 1023);
		
		//round
		s *= 100;
		s = Math.round(s);
		s /= 100;
		
		switch(i){
		case 0: return s+" byte";
		case 1: return s+" Kb";
		case 2: return s+" Mb";
		case 3: return s+" Gb";
		case 4: return s+" Tb";
		}
		return ""+bytes;
	}
	
	public String toString(){
		String out = "";
		
		out += "Programm with "+logs.size()+" Shaders ("+convertSize(programLength)+") was compiled ";
		out += (success?"without ":"with ")+"Shader Errors!\r\n";
		out += "------------------------------\r\n";
		for(GLShaderLog l : logs){
			if(!l.getStatus()){
				out += l.toString();
				out += "\r\n";
			}
		}
		out += "###################################\r\n\r\n";
		
		return out;
	}
}
