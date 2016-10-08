package com.lsengin.grf.shader;

import com.jogamp.opengl.GL2ES3;
import com.lsengin.grf.util.WeakValue;
import com.lsengin.grf.util.WeakValueMap;

public class GLProgramMap extends WeakValueMap<String, GLProgramm> {
	
	protected void clearReferences(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void clearReferences(GL2ES3 gl){
		WeakValue<String,GLProgramm> wk;
		
		while((wk = (WeakValue<String,GLProgramm>)queue.poll()) != null){
			map.remove(wk.getKey());
			for(GLShader s : wk.get().shaders){
				gl.glDetachShader(wk.get().id, s.id);
			}
			gl.glDeleteProgram(wk.get().getID());
			wk = null;
		}
	}

}
