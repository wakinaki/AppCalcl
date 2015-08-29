package com.wak.appcalc.server;

import com.wak.appcalc.client.ServerService;
import com.wak.appcalc.client.EMF;
import com.wak.appcalc.client.Log;
import com.wak.appcalc.client.Logo;
import com.wak.appcalc.client.PMF;

import java.util.Date;

import javax.jdo.PersistenceManager;
//import javax.jdo.PersistenceManagerFactory;
import javax.persistence.*;
//import java.util.List;

//import com.google.appengine.api.datastore.DatastoreService; 
//import com.google.appengine.api.datastore.DatastoreServiceFactory; 
//import com.google.appengine.api.datastore.Entity;
//import com.google.appengine.api.datastore.Query;
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

		
		String registros = "";
		/*
		
		Entity e = new Entity("Binarios");

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
		*/
		return registros;
	}

	public String guardaDatosJPA(String decimal, String binario, String fecha) throws IllegalArgumentException {
		
		String registros = "";
	
		 EntityManager em = EMF.get().createEntityManager();

	        Log log = new Log();
	        
	        log.setBinario(binario);
	        log.setDecimal(decimal);
	        log.setFecha(fecha);

	        try {
	            // retrieve all records on file
	            /*
	        	Query q = em.createQuery("select m from Employee m");
	            List<Log> lis = q.getResultList();
	            System.out.println("found:" + lis.size());
	            for (Log e: lis) {
	            	
	            registros += e.getBinario()+","+e.getDecimal()+e.getFecha() + "\n";
	            }
	            */
	            em.persist(log);
	            
	        } finally {
	            em.close();
	        }
	        
	    return registros ;
	}
	
	public String guardaDatosJDO(String decimal, String binario, String fecha) throws IllegalArgumentException {
	
		String registros = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Logo logo = new Logo(fecha,binario,decimal);
		
		logo.setBinario(binario);
		logo.setDecimal(decimal);
		logo.setFecha(fecha);
		
		pm.currentTransaction().begin();
		
	        try {
	            // retrieve all records on file
	            /*
	        	Query q = em.createQuery("select m from Employee m");
	            List<Log> lis = q.getResultList();
	            System.out.println("found:" + lis.size());
	            for (Log e: lis) {
	            	
	            registros += e.getBinario()+","+e.getDecimal()+e.getFecha() + "\n";
	            }
	            em.persist(log);
	            */
	        	
	        	pm.makePersistent(logo);
	        	pm.currentTransaction().commit();
	        } finally {
	        	if (pm.currentTransaction().isActive()) {
	                pm.currentTransaction().rollback();
	            }
	        	//pm.close();
	        }
		
		return registros;
	}
}
