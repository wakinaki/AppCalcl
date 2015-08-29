package com.wak.appcalc.client;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;
import com.google.appengine.api.datastore.Entity;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Logo { 


	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	@Column(name="fecha")  
	private String fecha;

	@Persistent
	@Column(name="decimal")  
	private String decimal;

	@Persistent
	@Column(name="binario")  
	private String binario;
	
	public Logo(final String fecha, final String decimal, final String binario)
	{
		this.fecha = fecha;
		this.decimal = decimal;
		this.binario = binario;
	}
	
	
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
	
	public void setId(Long id)
	{
		this.id = id;
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


