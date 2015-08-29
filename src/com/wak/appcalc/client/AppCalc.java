package com.wak.appcalc.client;



import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;

import com.wak.appcalc.client.Calculadora;
import com.wak.appcalc.client.LogoGrid;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppCalc implements IsWidget, EntryPoint {

	private PortalLayoutContainer portal;
	 
	  @Override
	  public Widget asWidget() {
	    if (portal == null) {
	      Portlet portlet1 = new Portlet();
	      portlet1.setHeadingText("Calculadora");
	      portlet1.add(new Calculadora().createCalc());
	      portlet1.setHeight(500);
	      //configPanel(portlet1);
	      
	      Portlet portlet2 = new Portlet();
	      portlet2.setHeadingText("Registro");
	      portlet2.add(new LogoGrid().createGrid());
	      portlet2.setHeight(250);
	      //configPanel(portlet2);
	 
	    
	 
	      portal = new PortalLayoutContainer(3);
	      portal.getElement().getStyle().setBackgroundColor("white");
	      portal.setColumnWidth(0, .20);
	      portal.setColumnWidth(1, .20);
	      portal.add(portlet1, 1);
	      portal.add(portlet2, 0);
	    }
	 
	    return portal;
	  }
	 
	    
	 
	  @Override
	  public void onModuleLoad() {
	    RootPanel.get().add(this);
	  }
	 

	 
	 
	 
	}