package com.wak.appcalc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sun.javafx.scene.traversal.Direction;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppCalc implements IsWidget, EntryPoint {



	  public interface HtmlLayoutContainerTemplate extends XTemplates {
			@XTemplate("<table width=\"50%\" height=\"50%\" align=\"center\"><tbody>"
					+"<tr><td class=\"texto\" colspan=4 /></td>"
					+ "<tr><td class=\"cell0\" /><td class=\"cell1\" /><td class=\"cell2\" /><td class=\"cell3\" /></tr>"
					+ "<tr><td class=\"cell4\" /><td class=\"cell5\" /><td class=\"cell6\" /><td class=\"cell7\" /></tr>"
					+ "<tr><td class=\"cell8\" /><td class=\"cell9\" /><td class=\"cell10\" /><td class=\"cell11\" /></tr>"
					+ "<tr><td class=\"cell12\" /><td class=\"cell13\" /><td class=\"cell14\" /><td class=\"cell15\" /></tr>"
					+ "<tr><td class=\"cell16\" /><td class=\"cell17\" /><td class=\"cell18\" /><td class=\"cell19\" /></tr>"
					+ "</tbody></table>")
			SafeHtml getTemplate();
		  }

	  public enum Operadores {
		   NONE,SUM,RES,MULT,DIV 
		}

	  private Operadores lastOp = Operadores.NONE;
	  private FramedPanel widget;
	  private TextField txt1;
	  double valor = 0;
	  double memoria = 0;
	  boolean coma = false;
	  boolean nuevo = false;

	  private Double DameNumero()
	  {
		  return Double.parseDouble(txt1.getText().replace(",", "."));
	  }

	  private void PonerResultado(double numero)
	  {
		  if ( (memoria % 2) == 0) txt1.setText(Integer.toString((int)numero));
			else txt1.setText(Double.toString(numero).replace(".", ","));
	  }


	  private void Operacion()
	  {
		  try{

		   valor = DameNumero();

		   //Info.display("memoria2",Double.toString(memoria));

			switch(lastOp)
			{
			case NONE:
				Info.display("Log","Sin una operacion");
				break;
			case SUM:
				memoria += valor;
				break;
			case MULT:
				memoria *= valor;
				break;
			case DIV:
				memoria /= valor;
				break;
			case RES:
				memoria -= valor;
				break;

			}

			PonerResultado(memoria);

		  }catch(Exception err){
			  Info.display("Error",err.getMessage());
		  }

	  }

	  private void Operar(Operadores operador)
	  {
		  lastOp = operador;

		  if (!nuevo)
		  {	  
			  memoria = DameNumero();
			  nuevo = true;
			  valor = 0;

		  }
	  }



	  private void Pulsar(String tecla) 
	  {

		  switch(tecla)
		  {
		  case "C":
			  if (txt1.getText().equals("0"))
			  {
				  memoria = 0;
				  nuevo = true;
			  }
			  txt1.setText("0");
			  break;
		  case "+":
			  Operar(Operadores.SUM);
			  break;
		  case "X":
			  Operar(Operadores.MULT);
			  break;

		  case "/":
			  Operar(Operadores.DIV);
			  break;

		  case "-":
			  Operar(Operadores.RES);
			  break;

		  case ",":

			  if (coma) Info.display("Log","Coma ya pulsda");
			  else txt1.setText(txt1.getText()+",");
			  coma = true;
			  break;

		  case "+/-":
			  double numero = DameNumero();
			  numero *= -1;
			  PonerResultado(numero);
			  break;
		  case "=":
			  Operacion();
			  break;

		  default:
			 if (txt1.getText().equals("0") || nuevo) 
				 {
				 txt1.setText("");
				 nuevo = false;
				 }
			 txt1.setText(txt1.getText() + tecla);
			 break;

		  }
	  }


	  SelectHandler selectHandler = new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
			  //Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");

			  Pulsar(((TextButton) event.getSource()).getText() );
			}
		  };


		  @Override
		  public Widget asWidget() {
			if (widget == null) {

				txt1 = new TextField();
				txt1.setReadOnly(true);
				txt1.setPixelSize(100, 20);
//		        txt1.inputEl.setStyle("text-align", "right");
				txt1.setText("0");

				HtmlLayoutContainerTemplate templates = GWT.create(HtmlLayoutContainerTemplate.class);

				  HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(templates.getTemplate());
				  htmlLayoutContainer.add(txt1, new HtmlData(".texto"));  

			  String[] textos = new  String[]{"C","+/-","%","/","7","8","9","X","4","5","6","-","1","2","3","+","0","BIN",",","="};

			  for (int i=0;i<textos.length;i++)
			  {
				  TextButton button = new TextButton(textos[i], selectHandler);
				  button.setPixelSize(80, 80);
				  htmlLayoutContainer.add(button, new HtmlData(".cell" + Integer.toString(i)));
//			      button.getElement().addClassName("button1");	
			  }


			  widget = new FramedPanel();
			  widget.addStyleName("margin-10");
			  widget.setHeadingText("Calculadora");
			  widget.setPixelSize(400, 500);
			  widget.setCollapsible(true);
			  widget.setWidget(htmlLayoutContainer);
			  widget.setPosition(20, 20);
			}

			return widget;
		  }

		  @Override
		  public void onModuleLoad() {
			  //GXT.setDefaultTheme(Theme.GRAY, true) ;
			RootPanel.get().add(asWidget());
		  }


}
