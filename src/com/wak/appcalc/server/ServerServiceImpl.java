package com.wak.appcalc.server;

import com.wak.appcalc.client.ServerService;
import com.wak.appcalc.client.EMF;
import com.wak.appcalc.client.Log;
//import com.wak.appcalc.client.Log;
import com.wak.appcalc.client.Logo;
import com.wak.appcalc.client.PMF;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.persistence.EntityManager;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.google.appengine.api.datastore.DatastoreService; 
import com.google.appengine.api.datastore.DatastoreServiceFactory; 
import com.google.appengine.api.datastore.Entity;




/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

	public String dameBinario(String decimal) throws IllegalArgumentException {
		
		String binario = Integer.toBinaryString(Integer.parseInt(decimal));
		return binario;  
		
	}
	
	public List<Log> cargaDatosJPA() throws IllegalArgumentException {
	
		// List<Log> lista;
		/*
		 EntityManager em = EMF.get().createEntityManager();

	        try {
	            Query q = em.createQuery("select m from Log m");
	            lista = q.getResultList();
	            //Log[] log = new Log[q];
	            
	            
	            //System.out.println("found:" + lis.size());
	            //for (Log e: lis) {
	            //registros += e.getBinario()+","+e.getDecimal()+e.getFecha() + "\n";
	            }
	            
	         finally {
	            em.close();
	        }
	        */
	    return null;

	}
	
	
	@SuppressWarnings("unchecked")
	public Logo[] cargaDatosJDO() throws IllegalArgumentException {
		
		List<Logo> lista = null;
		Logo[] logos = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		 try {
		
			Query pq = pm.newQuery(Logo.class);
			lista = (List<Logo>) pq.execute();
			
			logos = new Logo[lista.size()];
			
			for (int i=0; i< lista.size(); i++)
			{
			      logos[i] = lista.get(i);
			}
			
			
		 }
		 finally
		 {
			 pm.close();
		 }
		
		return logos;
		
	
	}

	/*
	public Logo[] cargaDatos(String catalogo) throws IllegalArgumentException {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query(catalogo);
		PreparedQuery pq = ds.prepare(q);
		FetchOptions option = FetchOptions.Builder.withLimit(Integer.MAX_VALUE);
		
		Logo[] logos = new Logo[pq.countEntities(option)];
		int i = 0;
		for (Entity dato:pq.asIterable())
		{
				
			 Logo logo = new Logo();
		      logo.setBinario(dato.getProperty("Binario").toString());
		      logo.setDecimal( dato.getProperty("Decimal").toString());
		      logo.setFecha(dato.getProperty("Fecha").toString());
		      logos[i] = logo;
		      i++;
		}
		
		return logos;
		
	
	}
	*/
	
	public String[] guardaDatos(String decimal, String binario, String fecha) throws IllegalArgumentException {

		
		Entity e = new Entity("Nomodel");

		Logo logo = new Logo();
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		e.setProperty("Fecha",fecha);
		e.setProperty("Decimal", decimal);
		e.setProperty("Binario", binario);
		
		logo.setBinario(fecha);
		logo.setDecimal(decimal);
		logo.setFecha(fecha);
		
		ds.put(e);
		
		return null;
	}

	public String guardaDatosJPA(String decimal, String binario, String fecha) throws IllegalArgumentException {
		
		
	
		 EntityManager em = EMF.get().createEntityManager();

	       Log log = new Log();
	        
	       log.setBinario(binario);
	       log.setDecimal(decimal);
	       log.setFecha(fecha);
	       
	       em.getTransaction().begin();

	        try {
	           
	            em.persist(log);
	        	em.getTransaction().commit();
	            
	        } finally {
	            em.close();
	        }
	        
	    return "" ;
	}
	
	public String guardaDatosJDO(String decimal, String binario, String fecha) throws IllegalArgumentException {
	
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Logo logo = new Logo();
		
		logo.setBinario(binario);
		logo.setDecimal(decimal);
		logo.setFecha(fecha);
		
		pm.currentTransaction().begin();
		
	        try {
	           
	        	
	        	pm.makePersistent(logo);
	        	pm.currentTransaction().commit();
	        } finally {
	        	pm.close();
	            }
		
		return "";
	}
}
