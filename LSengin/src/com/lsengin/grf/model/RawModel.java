package com.lsengin.grf.model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES3;
import com.lsengin.grf.util.Semantic;

public class RawModel {
	protected int[] objects = new int[Semantic.Object.SIZE];
	protected long vertexCount = 0;
	
	protected RawModelData modelData = null;
	protected boolean initialized = false;
	
	public RawModel(RawModelData rmd){
		this.modelData = rmd;
		initialized = false;
	}
	
	public RawModel(int vaoId, long vc){
		this.objects[Semantic.Object.VAO] = vaoId;
		this.vertexCount = vc;
		initialized = true;
	}
	
	
	
	
	
	public void init(GL2ES3 gl){
		if(!this.initialized){
			//create vbo
			gl.glGenBuffers(1, objects, Semantic.Object.VBO);
			
			//create vao
			gl.glGenVertexArrays(1, objects, Semantic.Object.VAO);
			
			//bind vao
			gl.glBindVertexArray(objects[Semantic.Object.VAO]);
			
			//bind vbo
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, objects[Semantic.Object.VBO]);
			
			//load data to vbo
			gl.glBufferData(GL.GL_ARRAY_BUFFER, modelData.positions.length*4, 
					FloatBuffer.wrap(modelData.positions), GL.GL_STATIC_DRAW);
			
			//add vbo to attribute list
			gl.glVertexAttribPointer(Semantic.Attr.POSITION, 3, GL.GL_FLOAT, false, 0, 0);
			
			//unbind vbo
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
			
			//create ibo
			gl.glGenBuffers(1, objects, Semantic.Object.IBO);
			
			//bind ibo
			gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, objects[Semantic.Object.IBO]);
			
			//load data to ibo
			gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, modelData.indices.length*4, 
					IntBuffer.wrap(modelData.indices), GL.GL_STATIC_DRAW);
			
			//set vertex lenth
			this.vertexCount = modelData.indices.length;
			
			//unbind vao
			gl.glBindVertexArray(0);
			
			//clear data
			this.modelData = null;
			
			//set init flag
			this.initialized = true;
		}
	}
	
	public void destruct(GL2ES3 gl){
		if(!initialized){
			return;
		}
		
		//delete vao
		if(objects[Semantic.Object.VAO] > 0){
			gl.glDeleteVertexArrays(1, objects, Semantic.Object.VAO);
		}
		
		//delete vbo
		if(objects[Semantic.Object.VBO] > 0){
			gl.glDeleteBuffers(1, objects, Semantic.Object.VBO);
		}
		
		//delete ibo
		if(objects[Semantic.Object.IBO] > 0){
			gl.glDeleteBuffers(1, objects, Semantic.Object.IBO);
		}
	}
	
	public int getVAO(){
		return this.objects[Semantic.Object.VAO];
	}
	
	public long getVertexCount(){
		return this.vertexCount;
	}
	
	public boolean isInitialized(){
		return this.initialized;
	}
	
	public RawModelData getRawData(){
		return this.modelData;
	}
}
