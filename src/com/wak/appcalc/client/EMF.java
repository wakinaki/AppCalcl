package com.wak.appcalc.client;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {

	 private static final EntityManagerFactory emfInstance =
		        Persistence.createEntityManagerFactory("myLog");

		    private EMF() {}

		    public static EntityManagerFactory get() {
		        return emfInstance;
		    }
}
