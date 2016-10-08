package com.lsengin.grf.texture;

import com.jogamp.opengl.GL2ES3;
import com.lsengin.grf.util.WeakValue;
import com.lsengin.grf.util.WeakValueMap;

public class GLTextureMap extends WeakValueMap<String, GLTexture> {
	
	protected void clearReferences(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void clearReferences(GL2ES3 gl){
		WeakValue<String,GLTexture> wk;
		
		while((wk = (WeakValue<String,GLTexture>)queue.poll()) != null){
			map.remove(wk.getKey());
			wk.get().getTexture().destroy(gl);
			wk = null;
		}
	}
	
	public void remove(String name){
		this.map.remove(name);
	}

}