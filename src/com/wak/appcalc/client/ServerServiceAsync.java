package com.wak.appcalc.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ServerService</code>.
 */
public interface ServerServiceAsync {
	void dameBinario(String decimal, AsyncCallback<String> callback) throws IllegalArgumentException;
	void guardaDatos(String decimal, String binario, String fecha, AsyncCallback<String> callback) throws IllegalArgumentException ;
	void guardaDatosJPA(String decimal, String binario, String fecha, AsyncCallback<String> callback) throws IllegalArgumentException ;
	void guardaDatosJDO(String decimal, String binario, String fecha, AsyncCallback<String> callback) throws IllegalArgumentException ;

	
}
