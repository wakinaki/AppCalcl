package com.wak.appcalc.server;

import com.wak.appcalc.client.ServerService;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

	public String dameBinario(String decimal, String fecha) throws IllegalArgumentException {
		
DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		String binario = Integer.toBinaryString(Integer.parseInt(decimal));

		Entity e = new Entity("Binarios");
		
		e.setProperty("Fecha",fecha);
		e.setProperty("Decimal", decimal);
		e.setProperty("Binario", binario);
		
		ds.put(e);
		
		return binario;
		
		
		
	}
	
	
}
