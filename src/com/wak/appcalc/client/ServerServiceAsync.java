package com.wak.appcalc.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ServerService</code>.
 */
public interface ServerServiceAsync {
	void dameBinario(String decimal, String fecha, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	
}
