package com.wak.appcalc.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wak.appcalc.client.Logo;
/**
 * The async counterpart of <code>ServerService</code>.
 */
public interface ServerServiceAsync {
	void dameBinario(String decimal, AsyncCallback<String> callback) throws IllegalArgumentException;

	void cargaDatosJPA(AsyncCallback<List<Log> > callback) throws IllegalArgumentException;	
	void cargaDatosJDO(AsyncCallback<Logo[]> callback) throws IllegalArgumentException;

	void guardaDatos(String decimal, String binario, String fecha, AsyncCallback<String[]> callback) throws IllegalArgumentException ;
	void guardaDatosJPA(String decimal, String binario, String fecha, AsyncCallback<String> callback) throws IllegalArgumentException ;
	void guardaDatosJDO(String decimal, String binario, String fecha, AsyncCallback<String> callback) throws IllegalArgumentException ;

	
}
