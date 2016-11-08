package com.lsengin.res;


public class Dependency{
	
	/**
	 * Resource can not be used, if this dependency is 
	 * not available. {@value #PRIORITY_CRITICAL}}
	 */
	public final static int PRIORITY_CRITICAL = 0x00;
	
	/**
	 * Resource should not be used, if this dependency is 
	 * not available. {@value #PRIORITY_NECESSARY}}
	 */
	public final static int PRIORITY_NECESSARY = 0x01;
	
	/**
	 * Resource can be used but a warning should be
	 * displayed. {@value #PRIORITY_NORMAL}}
	 */
	public final static int PRIORITY_NORMAL = 0x02;
	
	/**
	 * Resource can be used without this dependency but
	 * it should be loaded if available. {@value #PRIORITY_IGNORE}}
	 */
	public final static int PRIORITY_IGNORE = 0x00;
	
	
	
	private int dependencyPriority = PRIORITY_NORMAL;
	private int resourceID = 0;
	private String resourceName = "";
	private int resourceType = 0;
	
	
	public Dependency(int resId, String resName, int resType, int depPrio) {
		
		this.resourceID = resId;
		this.resourceName = resName;
		this.resourceType = resType;
		this.dependencyPriority = depPrio;
	}
	
	public Dependency(int resId, String resName, int resType) {
		
		this(resId, resName, resType, PRIORITY_NORMAL);
	}
	
	public Dependency(Resource res, int depPrio) {
		
		this(res.getId(), res.getName(), res.getType(), depPrio);
	}
	
	public Dependency(Resource res) {
		
		this(res, PRIORITY_NORMAL);
	}
	
	
	/**
	 * Get the priority level of this dependency.
	 * 
	 * @return dependency priority constant
	 */
	public int getDependencyPriority() {
		return dependencyPriority;
	}

	/**
	 * Get the id of the resource, this
	 * dependency is pointing at.
	 * 
	 * @return id of dependent resource
	 */
	public int getResourceId() {
		return resourceID;
	}

	/**
	 * Get the name of the resource, this
	 * dependency is pointing at.
	 * 
	 * @return name of dependent resource
	 */

	public String getResourceName() {
		return resourceName;
	}

	
	/**
	 * Get the type of the resource, this
	 * dependency is pointing at.
	 * 
	 * @return type of dependent resource as constant
	 */

	public int getResourceType() {
		return resourceType;
	}
	
}
