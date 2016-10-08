package com.lsengin.grf.core;

public interface WindowStateListener {
	public void windowCloseNotify();
	public void windowClosed();
	public void windowGainedFocus();
	public void windowLostFocus();
	public void windowMoved();
}
