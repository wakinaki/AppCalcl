package com.wak.appcalc.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("server")
public interface ServerService extends RemoteService {
	String dameBinario(String decimal) throws IllegalArgumentException;
	
	String guardaDatos(String decimal, String binario, String fecha) throws IllegalArgumentException;

}


