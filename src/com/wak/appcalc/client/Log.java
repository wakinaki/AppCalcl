package com.wak.appcalc.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Log { 


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private String fecha;
	private String decimal;
	private String binario;
	
	public Long getId() {
		return id;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getDecimal(){
		return decimal;
	}
	
	public String getBinario(){
		return binario;
	}
	
	public void setFecha(String fecha){
		this.fecha = fecha;
	}
	
	public void setDecimal(String decimal){
		this.decimal = decimal;
	}
	
	public void setBinario(String binario){
		this.binario = binario;
	}
	
}


