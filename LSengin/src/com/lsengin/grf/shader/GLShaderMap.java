package com.lsengin.grf.shader;

import com.jogamp.opengl.GL2ES3;
import com.lsengin.grf.util.WeakValue;
import com.lsengin.grf.util.WeakValueMap;

public class GLShaderMap extends WeakValueMap<String, GLShader> {
	
	protected void clearReferences(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void clearReferences(GL2ES3 gl){
		WeakValue<String,GLShader> wk;
		
		while((wk = (WeakValue<String,GLShader>)queue.poll()) != null){
			map.remove(wk.getKey());
			wk.get().destroy(gl);
			wk = null;
		}
	}

}