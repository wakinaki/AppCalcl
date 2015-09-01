package com.wak.appcalc.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;

import com.wak.appcalc.client.Logo;


public class Calculadora {

	  //CALCULADORA 
    private final ServerServiceAsync serverService = GWT.create(ServerService.class);
    private String numero = "";
	private String binario = "";
	private String fecha = "";
	private AppCalc padre;
	private boolean operando = false;
	private TextButton botonCero;
	private int maxSize = 10;
	
	 public enum Operadores {
		   NONE,SUM,RES,MULT,DIV 
		}

	  private Operadores lastOp = Operadores.NONE;
	  private FieldLabel txt1;
	  double valor = 0;
	  double memoria = 0;
	  boolean coma = false;
	  boolean nuevo = false;
	  boolean percent = false;

	  public interface HtmlLayoutContainerTemplate extends XTemplates {
			@XTemplate("<table width=\"50%\" height=\"50%\" align=\"center\" valign=\"bottom\" >"
					+"<tr><td class=\"texto\" colspan=4 /></td></tr>"
					+ "<tr ><td class=\"cell0\" /><td class=\"cell1\" /><td class=\"cell2\" /><td class=\"cell3\" /></tr>"
					+ "<tr ><td class=\"cell4\" /><td class=\"cell5\" /><td class=\"cell6\" /><td class=\"cell7\" /></tr>"
					+ "<tr ><td class=\"cell8\" /><td class=\"cell9\" /><td class=\"cell10\" /><td class=\"cell11\" /></tr>"
					+ "<tr ><td class=\"cell12\" /><td class=\"cell13\" /><td class=\"cell14\" /><td class=\"cell15\" /></tr>"
					+ "<tr ><td class=\"cell16\" /><td class=\"cell17\" /><td class=\"cell18\" /><td class=\"cell19\" /></tr>"
					+ "</tbody></table>")
			SafeHtml getTemplate();
		  }



	  private Double DameNumero()
	  {
		  return Double.parseDouble(DameTexto().replace(",", "."));
	  }

	  private void PonerResultado(double numero)
	  {
		  String texto = "";
		  
		  if ( (memoria % 2) == 0)
			  {
			  	texto = Long.toString((long)numero);
			  	
			  	if (texto.length() > maxSize-1)
			  	{
			  		texto = NumberFormat.getScientificFormat().format(Double.parseDouble(texto));
			  	}
			  	
			  }
			else {
				
				texto = Double.toString(numero);
			  	
				if (texto.length() > maxSize-1)
			  	{
					String entero = texto.substring(0,texto.indexOf("."));
							{
								if (entero.length() > maxSize-5) 
								{
									texto = NumberFormat.getScientificFormat().format(Double.parseDouble(texto));
								}
								else 
								{
									texto = NumberFormat.getFormat("0.000").format(Double.parseDouble(texto));
								}
							}	
			  	}
				
				texto = texto.replace(".", ",");
			}

		  PonerTexto(texto);
		  
		  GWT.log(texto);
		  
	  }

	  private void Reset()
	  {
		  nuevo = true;
		  coma = false;
		  percent = false;
		  operando = false;
	  }
	  
	  
	  private void Operacion()
	  {
		  try{

			
			 
			  
			if (percent)
			{
				valor = valor * 100 / memoria;
				percent = false;
			}
			  
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
			
			GWT.log(Double.toString(memoria));
			PonerResultado(memoria);
			Reset();

		  }catch(Exception err){
			  
			  Info.display("Error",err.getMessage());
			  
		  }

	  }

	  private void Operar(Operadores operador)
	  {
		  lastOp = operador;

			 if ((int)valor == 0 )
				 {
				 	valor = (int)0;
				 	PonerResultado(valor);
				 }
		  
		  if (!nuevo)
		  {	  
			  memoria = DameNumero();
			  valor = 0;
			Reset();

		  }

		  operando = true;

	  }

	  private void Actualizar()
	  {
		  Logo logo = new Logo();
		  logo.setId(padre.nextId);
		  logo.setBinario(binario);
		  logo.setDecimal(numero);
		  logo.setFecha(fecha);
		  
		  padre.listStore.add(logo);
		  
		  padre.nextId++;
				  
		 operando = false;
  		Reset();
	  }
	  
	  
	  private void Guardar()
	  {
		   fecha = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(new Date());
		 
		   serverService.guardaDatosJDO(numero, binario, fecha,new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					// Show the RPC error message to the user
					Info.display("Log","Fallo en el servicio web JDO al guardar el binario");
				}

				public void onSuccess(String result) {
					Actualizar();
					
				}	
			});
		   
	
	  }

	  private void Binario()
	  {

		  if (nuevo)
		  {
			  Guardar();
		  }
		  else 
		  {
			  binario = "";
			  numero = DameTexto();

			  if (numero.length()>9)
			  {
				  numero = numero.substring(0,9);
			  }

			  if (numero.contains(","))
			  {
				  numero = numero.substring(0,numero.indexOf(","));
			  }


			  serverService.dameBinario(numero, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						Info.display("Log","Fallo en el servicio web al extraer el binario");
					}

					public void onSuccess(String result) {
						binario = result;
						PonerTexto(binario);

						Guardar();
					}
				});
		  }
			  
		 


	  }

	  private void Pulsar(String tecla) 
	  {
		  
		  txt1.setVisible(false);
		  
		  switch(tecla)
		  {
		  case "C":
			  PonerTexto("0");
			  botonCero.setText("AC");
  			  Reset();
			  break;
			  
		  case "AC":
			  PonerTexto("0");
			  memoria = 0;
			  Reset();
			  break;
			  
		  case "%":
			  if (!operando)
			  {
				 Operar(Operadores.DIV);
				 valor = 100;
				 Operacion();
			  }
			  else
			  {
				  percent = true;
			  }
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

			  if (!coma) 
				  {
				  if (nuevo) {
					  PonerTexto("0,");
					  nuevo = false;
				  	}
				  else {
					  PonerTexto(DameTexto()+",");
				  	}
				  }
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

		  case "BIN":
			  
			  this.Binario();
			  break;

		  default:
			 if (DameTexto().equals("0") || nuevo)
				 {
				 PonerTexto("");
				 nuevo = false;
				 botonCero.setText("C");
				 }
			 String texto = DameTexto() + tecla;
			 if (texto.length() < maxSize)
			 {
				 PonerTexto(DameTexto() + tecla);
	  		     valor = DameNumero();	
				 
			 }
			 break;

		  }
		  
		  txt1.setVisible(true);

	  }


	  SelectHandler selectHandler = new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
			  //Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");

			  Pulsar(((TextButton) event.getSource()).getText() );
			}
		  };

    
private void PonerTexto(String texto){
	
	int fontSize = 50;

	StyleInjector.inject(".textAreaFontSize {font-size: " + fontSize +"px !important;font-style: bold !important; font-family: Arial !important;text-align: right;}");
	txt1.setStyleName("textAreaFontSize");
	txt1.setText(texto);

}
private String isNumber(String str)  
{  
  if(str.length() == 0) return "";	
	
  try  
  {  
    @SuppressWarnings("unused")
	double d = Double.parseDouble(str.replace(",", "."));  
  }  
  catch(NumberFormatException nfe)  
  {  
    return "0";  
  }  
  return str;  
}
private String DameTexto()
{
	
	return isNumber(txt1.getText());
}
		  
 public Widget createCalc(AppCalc tPadre)
 {
	 
	 	padre = tPadre;
	 	
	 	HtmlLayoutContainerTemplate templates = GWT.create(HtmlLayoutContainerTemplate.class);
	 	HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(templates.getTemplate());
	 	
	 	txt1 = new FieldLabel();
	 	txt1.setLabelSeparator("");
		PonerTexto("0");
	
		  serverService.cargaDatosJDO( new AsyncCallback<Logo[]>() {
				public void onFailure(Throwable caught) {
					// Show the RPC error message to the user
					Info.display("Log","Fallo en el servicio web al cargar el binario");
				}

				public void onSuccess(Logo[] result) {
					
					for (int i = 0; i < result.length; i++) {
					    Logo logo = result[i];
					    padre.listStore.add(logo);
					    
					}
					
				}	
			});
		
		 
	  htmlLayoutContainer.add(txt1, new HtmlData(".texto"));  

	  
		
	  
	  String[] textos = new  String[]{"AC","+/-","%","/","7","8","9","X","4","5","6","-","1","2","3","+","0","BIN",",","="};

	  for (int i=0;i<textos.length;i++)
	  {
		  TextButton button = new TextButton(textos[i], selectHandler);
		  button.setSize("80", "80");
		 
		  button.setPixelSize(80, 80);
		  String estilo = button.getStylePrimaryName();
		  StyleInjector.inject("." + estilo + "{font-size: 15px !important;}");
		  button.setStyleName(estilo);

		  htmlLayoutContainer.add(button, new HtmlData(".cell" + Integer.toString(i)));

		  if (textos[i].equals("AC"))
		  {
			  botonCero = button;
		  }
		  
	  }

	 
	  return htmlLayoutContainer;

 }
 
}
