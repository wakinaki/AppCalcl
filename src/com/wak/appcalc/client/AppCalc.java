package com.wak.appcalc.client;




import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.wak.appcalc.client.Calculadora;
import com.wak.appcalc.client.LogoGrid;
import com.wak.appcalc.client.LogoGrid.GridProperties;

import com.wak.appcalc.client.Logo;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppCalc implements IsWidget, EntryPoint {
	
	private PortalLayoutContainer portal;
	
	public Long nextId = 1L;
	protected GridProperties gridProperties = GWT.create(GridProperties.class);
//	protected SimpleComboBox<String> combo;
	public ListStore<Logo> listStore = new  ListStore<Logo>(gridProperties.id());
	private Widget calculadora;
	private Widget logoGrid;
	

	
	
	  @Override
	  public Widget asWidget() {
	    if (portal == null) {
	
	    	/*
	    	combo = new SimpleComboBox<String>(new StringLabelProvider<String>());
	    	combo.add("JDO");
	    	combo.add("JPA");
	    	combo.add("Low level API");
	        combo.setAllowBlank(false);
	        combo.setEditable(false);
	        combo.setAllowTextSelection(false);
	        combo.setTriggerAction(TriggerAction.ALL);
	        combo.setValue("JDO");
	       
	          ( ValueChangeEvent<String> event ) {
        Info.display( "Valor cambiado", event.getValue() );
    }
	        */
	    	
	     calculadora = new Calculadora().createCalc(this);
	     logoGrid = new LogoGrid().createGrid(this);
	    	
	      Portlet portlet1 = new Portlet();
	      portlet1.setHeadingText("Calculadora");
	      portlet1.setResize(false);
	      portlet1.add(calculadora);
	      portlet1.setHeight(540);
	      
	      Portlet portlet2 = new Portlet();
	      portlet2.setHeadingText("Registro");
	      portlet2.add(logoGrid);
	      portlet2.setHeight(250);

	      /*Portlet portlet3 = new Portlet();
	      portlet3.setHeadingText("Proveedor");
	      portlet3.add(combo);
	      //portlet3.setHeight(250);
	*/
	      
	      portal = new PortalLayoutContainer(3);
	      portal.getElement().getStyle().setBackgroundColor("white");
	      portal.setColumnWidth(0, 350);
	      portal.setColumnWidth(1, 400);
	      portal.add(portlet1, 1);
	      portal.add(portlet2, 0);
	      //portal.add(portlet3, 0);
	      
	      
	    }
	 
	    return portal;
	  }
	 
	    
	 
	  @Override
	  public void onModuleLoad() {
	    RootPanel.get().add(this);
	  }
	 

	 
	 
	 
	}