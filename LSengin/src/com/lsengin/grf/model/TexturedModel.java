package com.lsengin.grf.model;

import com.jogamp.opengl.GL2ES3;
import com.lsengin.grf.texture.GLTexture;

public class TexturedModel extends RawModel {
	protected GLTexture tex = null;

	public TexturedModel(RawModelData rmd, GLTexture tex) {
		super(rmd);
		this.tex = tex;
	}
	
	public GLTexture getTexture(){
		return tex;
	}
	
	public void init(GL2ES3 gl){
		super.init(gl);
		tex.init(gl);
	}

}
