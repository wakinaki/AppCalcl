package com.wak.appcalc.server;

import com.wak.appcalc.client.ServerService;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

public String dameBinario(String decimal) throws IllegalArgumentException {
		
		
		String binario = Integer.toBinaryString(Integer.parseInt(decimal));
		return binario;  
		
		
		
	}
	
	public String guardaDatos(String decimal, String binario, String fecha) throws IllegalArgumentException {
Entity e = new Entity("Binarios");

		String registros = "";
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		e.setProperty("Fecha",fecha);
		e.setProperty("Decimal", decimal);
		e.setProperty("Binario", binario);
		
		ds.put(e);
		
		Query q = new Query("Binarios");
		PreparedQuery pq = ds.prepare(q);
		for (Entity dato:pq.asIterable())
		{
			String tFecha = dato.getProperty("Fecha").toString();
			String tDecimal = dato.getProperty("Decimal").toString();
			String tBinario = dato.getProperty("Binario").toString();
			
			registros = tFecha +" " + tDecimal +" " + tBinario +"\n";
		}
		
		return registros;
	}

	
	
}
