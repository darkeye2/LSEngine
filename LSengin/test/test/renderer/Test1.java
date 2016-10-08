package test.renderer;

import com.lsengin.grf.core.RenderWindow;
import com.lsengin.grf.core.Scene;
import com.lsengin.grf.model.RawModel;
import com.lsengin.grf.model.RawModelData;
import com.lsengin.grf.model.TexturedModel;
import com.lsengin.grf.texture.GLTexture;

public class Test1 {

	public static void main(String[] args) {
		RenderWindow rw = new RenderWindow(800, 600);
		Scene s = rw.getDefaultSceneGraph();
		
		GLTexture tex = new GLTexture("/texture/Texture.jpg", true, GLTexture.TYPE_JPG, "");
		RawModelData rmd = new RawModelData();
		rmd.positions = new float[]{
				-0.5f, 0.5f, 0,
				-0.5f, -0.5f, 0,
				0.5f, -0.5f, 0,
				0.5f, 0.5f, 0
		};
		rmd.indices = new int[]{
				0, 1, 3, 3, 1, 2
		};
		TexturedModel  rm = new TexturedModel(rmd, tex);
		s.addComponent(rm);
	}

}
