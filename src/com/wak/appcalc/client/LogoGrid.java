package com.wak.appcalc.client;



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

	private AppCalc padre;
	
	public interface GridProperties extends PropertyAccess<Logo> {
	      ModelKeyProvider<Logo> id();
	      ValueProvider<Logo, String> fecha();
	      ValueProvider<Logo, String> decimal();
	      ValueProvider<Logo, String> binario();
	      
	    }
		  
	    // Setup the property access definitions for the values for the grid columns
	private GridProperties gridProperties;  
	
	      
	  public Widget createGrid(AppCalc tPadre) {
		// Setup the ListStore.
		  
		  padre = tPadre;
		  gridProperties = padre.gridProperties;
		  
	      ListStore<Logo> listStore = padre.listStore;
		
	      
	      // Setup the grid columns
	      ColumnConfig<Logo, String> nameCol = new ColumnConfig<Logo, String>(gridProperties.fecha(), 100, "Fecha");
	      ColumnConfig<Logo, String> nameCol1 = new ColumnConfig<Logo, String>(gridProperties.decimal(), 100, "Decimal");
	      ColumnConfig<Logo, String> nameCol2 = new ColumnConfig<Logo, String>(gridProperties.binario(), 100, "Binario");
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
