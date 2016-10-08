package com.lsengin.grf.model.writer;

import com.lsengin.grf.model.RawModelData;

public abstract class ModelWriter {

	public abstract boolean writeModel(RawModelData rmd, String filePath);
}
