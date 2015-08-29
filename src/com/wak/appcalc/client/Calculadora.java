package com.wak.appcalc.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class Calculadora {

	  //CALCULADORA 
    private final ServerServiceAsync serverService = GWT.create(ServerService.class);
    private String numero = "";
	private String binario = "";

	 public enum Operadores {
		   NONE,SUM,RES,MULT,DIV 
		}

	  private Operadores lastOp = Operadores.NONE;
	  private TextField txt1;
	  double valor = 0;
	  double memoria = 0;
	  boolean coma = false;
	  boolean nuevo = false;


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

	  protected void Guardar()
	  {
		  String fecha = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(new Date());

		  /*
			EntityManager em = EMF.get().createEntityManager();

			Log log = new Log();
			double x = Math.random();
			log.setBinario(binario);
			log.setDecimal(numero);
			log.setFecha(fecha);

			try {
				// retrieve all records on file
				Query q = em.createQuery("select m from Employee m");
				List<Log> lis = q.getResultList();
				System.out.println("found:" + lis.size());
				for (Log e: lis) {

					Info.display("Guardado",e.getBinario()+","+e.getDecimal()+e.getFecha());
				}

				// store current one
				em.persist(log);
			} finally {
				em.close();
			}
			System.out.println("bye!");
		  */

		  serverService.guardaDatosJDO(numero, binario, fecha,new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					// Show the RPC error message to the user
					Info.display("Log","Fallo en el servicio web al guardar el binario");
				}

				public void onSuccess(String result) {
					Info.display("Guardado",result);

				}
			});


	  }

	  private void Binario()
	  {

		  binario = "";
		  numero = txt1.getText();

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
					txt1.setText(binario);

					Guardar();
				}
			});


	  }

	  private void Pulsar(String tecla) 
	  {

		  switch(tecla)
		  {
		  case "C":
			  if (txt1.getText().equals("0"))
			  {
				  memoria = 0;
			  }
			  nuevo = true;
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

		  case "BIN":
			  this.Binario();
			  break;

		  default:
			 if ((txt1.getText().equals("0") || nuevo) && !coma)
				 {
				 txt1.setText("");
				 nuevo = false;
				 }
			 else
			 {
				 coma = false;
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

    
 public Widget createCalc()
 {
	 	
	 	HtmlLayoutContainerTemplate templates = GWT.create(HtmlLayoutContainerTemplate.class);
	 	HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(templates.getTemplate());
	 	 
	 	txt1 = new TextField();
		txt1.setReadOnly(true);
		txt1.setPixelSize(100, 20);
//        txt1.inputEl.setStyle("text-align", "right");
		txt1.setText("0");
		 
	  htmlLayoutContainer.add(txt1, new HtmlData(".texto"));  

	  String[] textos = new  String[]{"C","+/-","%","/","7","8","9","X","4","5","6","-","1","2","3","+","0","BIN",",","="};

	  for (int i=0;i<textos.length;i++)
	  {
		  TextButton button = new TextButton(textos[i], selectHandler);
		  button.setPixelSize(80, 80);
		  htmlLayoutContainer.add(button, new HtmlData(".cell" + Integer.toString(i)));
//	      button.getElement().addClassName("button1");	
	  }

	  /*
	  FramedPanel calc = new FramedPanel();
	  calc.addStyleName("margin-10");
	  calc.setHeadingText("Calculadora");
	  calc.setPixelSize(400, 500);
	  calc.setCollapsible(true);
	  calc.setWidget(htmlLayoutContainer);
	  calc.setPosition(20, 20);
		*/
	  return htmlLayoutContainer;

 }
 
}
