package com.lsengin.grf.core;

import java.util.ArrayList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES3;
import com.lsengin.debug.Debugger;
import com.lsengin.debug.Profiler;
import com.lsengin.grf.model.RawModel;
import com.lsengin.grf.shader.GLProgramm;
import com.lsengin.grf.shader.GLShader;
import com.lsengin.grf.util.Semantic;

public class Renderer {
	
	protected ArrayList<RawModel> models = new ArrayList<RawModel>();
	
	//TODO test
	public GLProgramm prog = null;
	
	public void init(GL2ES3 gl){
		if(Debugger.DEBUG)
			Profiler.startMeasure(this.getClass(), "init");
		
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		prog = new GLProgramm("/shader/", new String[]{ "vertex1", "fragment1"}, 
				new int[]{GLShader.VERTEX, GLShader.FRAGMENT}, "firstProg");
		prog.init(gl);
		System.out.println(prog.getLog().toString());
		System.out.println("Program: "+prog.getID()+" Name: "+prog.getName()+" Info: "+prog.getUniqueName());
		
		if(Debugger.DEBUG)
			Profiler.stopMeasure(this.getClass(), "init");
	}
	
	public void render(GL2ES3 gl){
		if(Debugger.DEBUG)
			Profiler.startMeasure(this.getClass(), "render");
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glUseProgram(prog.getID());
		//prog.bindAttribute(gl, Semantic.Attr.POSITION, "position");
		for(RawModel m : models){
			if(!m.isInitialized())
				m.init(gl);
			
			gl.glBindVertexArray(m.getVAO());
			gl.glEnableVertexAttribArray(Semantic.Attr.POSITION);
			gl.glDrawElements(GL.GL_TRIANGLES, (int) m.getVertexCount(), GL.GL_UNSIGNED_INT, 0);
			gl.glDisableVertexAttribArray(Semantic.Attr.POSITION);
			gl.glBindVertexArray(0);
		}
		
		if(Debugger.DEBUG)
			Profiler.stopMeasure(this.getClass(), "render");
	}
	
	public void addModel(RawModel rm){
		if(Debugger.DEBUG)
			Profiler.startMeasure(this.getClass(), "addModel");
		this.models.add(rm);
		if(Debugger.DEBUG)
			Profiler.stopMeasure(this.getClass(), "addModel");
	}

}
