package com.lsengin.grf.texture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.TextureIO;

public final class GLTextureManager {
	// Texture map
	protected static GLTextureMap textures = new GLTextureMap();

	public static GLTexture getTexture(String name) {
		if (textures.containsKey(name)) {
			return textures.get(name);
		}
		return null;
	}

	public static void cleanup(GL2ES3 gl) {
		textures.clearReferences(gl);
	}

	public static GLTexture loadTexture(GL2ES3 gl, GLTexture tex) {
		cleanup(gl);
		GLTexture p = getTexture(tex.getName());
		if (p != null){
			if(p.getTexture() != null){
				return p;
			}
		}

		InputStream in = null;
		if(tex.resource){
			if(tex.fileName.startsWith("/")){
				in = GLTextureManager.class.getResourceAsStream(tex.fileName);
			}else{
				in = GLTextureManager.class.getResourceAsStream("/img/"+tex.fileName);
			}
		}else{
			try {
				in = new FileInputStream((new File(tex.fileName)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try {
			tex.texture = TextureIO.newTexture(in, false, tex.textureFileType);
		} catch (GLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		textures.put(tex.getFileName(), tex);

		return tex;
	}
}
