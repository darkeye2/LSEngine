package com.lsengin.grf.core;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.newt.Display;
import com.jogamp.newt.MonitorDevice;
import com.jogamp.newt.MonitorMode;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public class RenderWindow implements WindowListener{
	protected static int screenIdx = 0;
	
	//window and animator
	private GLWindow glWindow = null;
	private FPSAnimator animator = null;
	
	//window listener
	protected ArrayList<WindowStateListener> windowStateListener = new ArrayList<WindowStateListener>();
	protected Object windowSLLock = new Object();
	
	//scene graph
	protected Scene defaultSG = null;
	protected Scene curSG = null;
	
	//renderer
	protected Renderer renderer = new Renderer();
	
	//window properties
	protected String titel = "Window Test";
	protected boolean undecorated = false;
	protected boolean alwaysOnTop = false;
	protected boolean fullscreen = false;
	protected boolean mouseVisible = true;
	protected boolean mouseConfined = false;
	protected boolean showFPS = true;
	
	//resolution
	protected MonitorMode curMMode = null;
	protected MonitorMode recommendedMMode = null;
	
	//jogl
	private Display display = null;
	private Screen screen = null;
	private GLProfile glProfile = null;
	
	public RenderWindow(int w, int h, String glV){
		//get screen
		display = NewtFactory.createDisplay(null);
		screen = NewtFactory.createScreen(display, screenIdx);

		//create gl profile
		if(GLProfile.isAvailable(glV)){
			glProfile = GLProfile.get(glV);
		}else{
			glProfile = GLProfile.getDefault();
		}
		
		//create capabilities
		GLCapabilities glCapabilities = new GLCapabilities(glProfile);
		glCapabilities.setAlphaBits(2);
		glCapabilities.setSampleBuffers(true);
		glCapabilities.setNumSamples(4);
		
		//create window
		glWindow = GLWindow.create(screen, glCapabilities);
		glWindow.addWindowListener(this);
		glWindow.setVisible(true);
		getResolutions();
		
		//init animator
		initAnimator();
		
		//init scene graph
		initSG();
	}
	
	public RenderWindow(int w, int h){
		this(w, h, "GL4ES3");
	}
	
	
	protected void initSG(){
		this.defaultSG = createSceneGraph();
		this.setSceneGraph(defaultSG);
	}
	
	public void setSceneGraph(Scene s){
		this.curSG = s;
		this.glWindow.addGLEventListener(curSG);
	}
	
	public Scene createSceneGraph(){
		Scene s = new Scene(renderer);
		return s;
	}
	
	public Scene getDefaultSceneGraph(){
		return this.defaultSG;
	}
	
	protected void initAnimator(){
		this.animator = new FPSAnimator(glWindow, 60);
		this.animator.setFPS(60);
		this.animator.setUpdateFPSFrames(300, null);
		this.animator.start();
	}
	
	public String getGLVersion(){
		return glProfile.getImplName();
	}
	
	public List<MonitorMode> getResolutions(){
		if(glWindow != null){
			MonitorDevice monitor = glWindow.getMainMonitor();
			List<MonitorMode> modes = monitor.getSupportedModes();
			
			this.recommendedMMode = monitor.getOriginalMode();
			return modes;
		}
		return null;
	}
	
	
	
	public void addWindowStateListener(WindowStateListener wsl){
		synchronized(windowSLLock){
			this.windowStateListener.add(wsl);
		}
	}
	
	public void removeWindowStateListener(WindowStateListener wsl){
		synchronized(windowSLLock){
			if(windowStateListener.contains(wsl)){
				this.windowStateListener.remove(wsl);
			}
		}
	}
	
	@Override
	public void windowDestroyNotify(WindowEvent arg0) {
		synchronized(windowSLLock){
			for(WindowStateListener wsl : this.windowStateListener){
				wsl.windowCloseNotify();
			}
		}
	}
	
	@Override
	public void windowDestroyed(WindowEvent arg0) {
		synchronized(windowSLLock){
			for(WindowStateListener wsl : this.windowStateListener){
				wsl.windowClosed();
			}
		}
	}
	
	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		synchronized(windowSLLock){
			for(WindowStateListener wsl : this.windowStateListener){
				wsl.windowGainedFocus();
			}
		}
	}
	
	@Override
	public void windowLostFocus(WindowEvent arg0) {
		synchronized(windowSLLock){
			for(WindowStateListener wsl : this.windowStateListener){
				wsl.windowLostFocus();
			}
		}
	}
	
	@Override
	public void windowMoved(WindowEvent arg0) {
		synchronized(windowSLLock){
			for(WindowStateListener wsl : this.windowStateListener){
				wsl.windowMoved();
			}
		}
	}
	
	@Override
	public void windowRepaint(WindowUpdateEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowResized(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	
	public static void main(String[] args){
		new RenderWindow(0, 0);
	}

}
