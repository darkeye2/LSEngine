package com.lsengin.grf.texture;

import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class GLTexture {
	public static final String TYPE_PNG = TextureIO.PNG;
	public static final String TYPE_JPG = TextureIO.JPG;
	public static final String TYPE_GIF = TextureIO.GIF;
	public static final String TYPE_TIFF = TextureIO.TIFF;
	
	public Texture texture = null;
	public String fileName = "";
	public String imageName = "";
	public String textureFileType = TextureIO.PNG;
	public boolean resource = true;
	
	public GLTexture(String filename, boolean fromResource, String filetype, String name){
		this.fileName = filename;
		this.resource = fromResource;
		this.textureFileType = filetype;
		this.imageName = name;
	}
	
	public GLTexture(String filename, boolean fromResource, String name){
		this(filename, fromResource, TextureIO.PNG, name);
	}
	
	public GLTexture(String filename, String name){
		this(filename, true, TextureIO.PNG, name);
	}
	
	public GLTexture init(GL2ES3 gl){
		return GLTextureManager.loadTexture(gl, this);
	}
	
	public Texture getTexture(){
		return this.texture;
	}
	
	public String getName(){
		return this.imageName;
	}
	
	public String getFileName(){
		return this.fileName+"."+textureFileType;
	}
}
