package com.wak.appcalc.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;


public class LogoGrid {

	public interface GridProperties extends PropertyAccess<Logo> {
	      ModelKeyProvider<Logo> id();
	      ValueProvider<Logo, String> fecha();
	      ValueProvider<Logo, String> decimal();
	      ValueProvider<Logo, String> binario();
	      
	    }
		  
	    // Setup the property access definitions for the values for the grid columns
	    public static GridProperties gridProperties = GWT.create(GridProperties.class);
	      
	  public Widget createGrid() {
		// Setup the ListStore.
	      ListStore<Logo> listStore = new ListStore<Logo>(gridProperties.id());
		
	      // Setup the grid columns
	      ColumnConfig<Logo, String> nameCol = new ColumnConfig<Logo, String>(gridProperties.fecha(), 10, "Fecha");
	      ColumnConfig<Logo, String> nameCol1 = new ColumnConfig<Logo, String>(gridProperties.decimal(), 10, "Decimal");
	      ColumnConfig<Logo, String> nameCol2 = new ColumnConfig<Logo, String>(gridProperties.binario(), 10, "Binario");
	      List<ColumnConfig<Logo, ?>> columns = new ArrayList<ColumnConfig<Logo, ?>>();
	      columns.add(nameCol);
	      columns.add(nameCol1);
	      columns.add(nameCol2);
	      ColumnModel<Logo> columnModel = new ColumnModel<Logo>(columns);
		
	      // Setup the grid view for styling
	      GridView<Logo> gridView = new GridView<Logo>();
	      gridView.setAutoExpandColumn(nameCol);
		    
	      // Setup the grid
	      Grid<Logo> grid = new Grid<Logo>(listStore, columnModel, gridView);
	      // Setup a size if not depending on the parent container to give it a size. 
	      grid.setPixelSize(300, 200);
	 
	    return grid;
	  }
}
