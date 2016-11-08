package com.lsengin.grf.core;

import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.lsengin.debug.HTMLStatisticWriter;
import com.lsengin.debug.Profiler;
import com.lsengin.grf.model.RawModel;

public class Scene implements GLEventListener{
	
	protected Renderer r = null;
	
	public Scene(Renderer r){
		this.r = r;
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		r.render((GL2ES3) arg0.getGL());
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		//TODO  remove this
				Profiler.printAll(new HTMLStatisticWriter());
				Profiler.stop();
				System.out.println("Close");
		System.exit(0);
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		r.init((GL2ES3) arg0.getGL());
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public void addComponent(RawModel rm){
		this.r.addModel(rm);
	}

}
