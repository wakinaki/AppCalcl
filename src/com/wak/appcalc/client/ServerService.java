package com.wak.appcalc.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wak.appcalc.client.Logo;
/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("server")
public interface ServerService extends RemoteService {

	String dameBinario(String decimal) throws IllegalArgumentException;
	
	List<Log> cargaDatosJPA() throws IllegalArgumentException;
	Logo[] cargaDatosJDO() throws IllegalArgumentException;
	
	String[] guardaDatos(String decimal, String binario, String fecha) throws IllegalArgumentException; 

	String guardaDatosJPA(String decimal, String binario, String fecha) throws IllegalArgumentException; 

	String guardaDatosJDO(String decimal, String binario, String fecha) throws IllegalArgumentException; 

}


