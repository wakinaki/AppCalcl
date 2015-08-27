package com.wak.appcalc.client;

import com.google.appengine.api.datastore.Key;
import com.ibm.icu.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Key id;
	private String fecha;
	private String decimal;
	private String binario;
	
	public void Guardar(String decimal, String binario)
	{
		Date date = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.fecha = ff.format(date).toString();
		this.decimal = decimal;
		this.binario = binario;
		
		
		
	}
	

}
