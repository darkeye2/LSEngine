package com.lsengin.res;

public class Resource {
	
	/**
	 * Resource is a sound file. {@value #RES_SOUND}}
	 */
	public final static int RES_SOUND = 0x00;
	
	/**
	 * Resource is an image file. {@value #RES_IMAGE}}
	 */
	public final static int RES_IMAGE = 0x01;
	
	/**
	 * Resource is a text file. {@value #RES_TEXT}}
	 */
	public final static int RES_TEXT = 0x02;
	
	/**
	 * Resource is a model file. {@value #RES_MODEL}}
	 */
	public final static int RES_MODEL = 0x03;
	
	/**
	 * Resource is a 3D model file. {@value #RES_MODEL_3D}}
	 */
	public final static int RES_MODEL_3D = 0x04;
	
	/**
	 * Resource is a 2D model file. {@value #RES_MODEL_2D}}
	 */
	public final static int RES_MODEL_2D = 0x05;
	
	/**
	 * Resource is a localization file. {@value #RES_LOCALIZATION}}
	 */
	public final static int RES_LOCALIZATION = 0x06;
	
	/**
	 * Resource is a material file. {@value #RES_MATERIAL}}
	 */
	public final static int RES_MATERIAL = 0x07;
	
	/**
	 * Resource is a video file. {@value #RES_VIDEO}}
	 */
	public final static int RES_VIDEO = 0x08;
	
	/**
	 * Resource is of other or unknown type. {@value #RES_OTHER}}
	 */
	public final static int RES_OTHER = 0x30;
	
	
	/**
	 * Resource will be loaded at start and unloaded 
	 * on application close. {@value #RES_LIFETIME_LSR}}<br>
	 * Use this for persistent resources.
	 */
	public final static int RES_LIFETIME_LSR = 0x50;
	
	/**
	 * Resource will be loaded when it is requested first
	 * and unloaded, when not used anymore. {@value #RES_LIFETIME_LRR}}<br>
	 * Use this for fast loading resources, to load them only,
	 * when they are used.
	 */
	public final static int RES_LIFETIME_LRR= 0x51;
	
	/**
	 * Resource will be loaded when requested and unloaded
	 * after not used for a particular time. {@value #RES_LIFETIME_UTR}}<br>
	 * Use for fast loading resources, when they are used often. 
	 * See also {@link #RES_LIFETIME_LRR}}
	 */
	public final static int RES_LIFETIME_UTR = 0x52;
	
	/**
	 * Resource will be loaded on manual request and unloaded when
	 * calling the clear method. {@value #RES_LIFETIME_UCR}}<br>
	 * Use for long loading resources on level begin. Don´t forget
	 * to call clear after the level, else the resource will stay
	 * in memory.
	 */
	public final static int RES_LIFETIME_UCR = 0x53;
	
	
	
	//resource attributes
	private int resId = -1;
	private String resName = "Untiteld Resource";
	private int resType = RES_OTHER;
	private String location = "";
	private String fileName = "";
	
	
	public Resource() {
		
		//create an empty resource
	}
	
	public Resource(int id, int type, String name) {
		
		//TODO
	}
	
	public int getId() {
		
		return this.resId;
	}
	
	public String getName() {
		
		return this.resName;
	}
	
	public int getType() {
		
		return this.resType;
	}

}
