package com.lsengin.grf.model.loader;

import com.lsengin.grf.model.RawModelData;

public abstract class ModelLoader {
	
	public abstract RawModelData load(String path);
}
