package com.wak.appcalc.client;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;




@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Logo implements Serializable { 

   
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


