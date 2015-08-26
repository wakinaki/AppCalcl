package com.wak.appcalc.server;

import com.wak.appcalc.client.ServerService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

	public String dameBinario(String decimal) throws IllegalArgumentException {
		
		return Integer.toBinaryString(Integer.parseInt(decimal));
		
	}
	
	
}
