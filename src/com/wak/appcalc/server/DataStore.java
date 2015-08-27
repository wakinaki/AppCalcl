package com.wak.appcalc.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


@SuppressWarnings("serial")
public class DataStore extends HttpServlet  {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = new Entity("Binarios");
		
		e.setProperty("Decimal", req.getParameter("decimal") );
		e.setProperty("Binario", req.getParameter("binario"));
		
		ds.put(e);
		
		
	}
	
}
