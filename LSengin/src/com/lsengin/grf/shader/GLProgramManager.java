package com.lsengin.grf.shader;

import com.jogamp.opengl.GL2ES3;

public final class GLProgramManager {
	//programm map
	protected static GLProgramMap programs = new GLProgramMap();
	
	public static GLProgramm getProgram(String name){
		if(programs.containsKey(name)){
			return programs.get(name);
		}
		return null;
	}
	
	public static void cleanup(GL2ES3 gl){
		programs.clearReferences(gl);
		GLShaderManager.cleanup(gl);
	}
	
	public static GLProgramm loadProgram(GL2ES3 gl, GLProgramm prog){
		cleanup(gl);
		
		GLProgramm p = getProgram(prog.getName());
		if(p != null)
			return p;
		
		prog.id = gl.glCreateProgram();

		for (int i = 0; i < prog.shaders.length; i++) {
			prog.shaders[i] = prog.shaders[i].init(gl);
			System.out.println("Adding schader "+prog.shaders[i].id+" to Programm");
			gl.glAttachShader(prog.id, prog.shaders[i].id);
		}
		gl.glLinkProgram(prog.id);
		gl.glValidateProgram(prog.id);
		
		prog.createUniqueName();
		prog.createCompilerLog();
		
		programs.put(prog.getUniqueName(), prog);

		
		return prog;
	}

}
