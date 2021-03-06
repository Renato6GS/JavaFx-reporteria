package trasladoPropiedad;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DTOs.DtoControlDePagos;
import DTOs.DtoPersona;
import DTOs.DtoPropiedad;
import accionesBD.ActualizarControlDePagos;
import accionesBD.ActualizarPropiedad;
import accionesBD.ActualizarRegistroTraslados;
import accionesBD.ConsultarCliente;
import accionesBD.ConsultarControlDePagos;
import accionesBD.ConsultarPropiedad;
import controlDePagos.ModeloDatos;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import reportes.ReporteTraslados;

public class ControladorTrasladoPropiedad {

    @FXML
    private AnchorPane miAnchorPaneTraslado;

    @FXML
    private Label labelTitulo;

    @FXML
    private TextField textCodigoPropiedad;

    @FXML
    private Line lineCodigoPropiedad;

    @FXML
    private Label labelCodigoPropiedad;

    @FXML
    private TextField textDpiReceptor;

    @FXML
    private Line lineDpiReceptor;

    @FXML
    private Label labelDpiReceptor;

    @FXML
    private TextField textDpiEmisor;

    @FXML
    private Line lineDpiEmisor;

    @FXML
    private Label labelDpiEmisor;

    @FXML
    private Line lineCodigoPropiedad12;

    @FXML
    private Rectangle rectangleBuscar;

    @FXML
    private Label labelBuscar;

    @FXML
    private Label labelDatosPropiedad;

    @FXML
    private Line lineDatosDeLaPropiedad;

    @FXML
    private Line lineFinal;

    @FXML
    private Rectangle rectangleTrasladar;

    @FXML
    private Label labelTrasladar;

    @FXML
    private Label labelHistorial;

    @FXML
    private Label labelPropietario;

    @FXML
    private Label labelValor;

    @FXML
    private Label labelInteres;

    @FXML
    private Label labelTotalCuotas;

    @FXML
    private Label labelTotalPagado;

    @FXML
    private Label labelMoras;

    @FXML
    private Label labelCuotasPagadas;

    @FXML
    private ImageView botonSalir;
    
    // 	Variables
    private Line lineNoFocus = new Line();
	private TextField sinDatos = new TextField();
	private Label sinDatosLabel = new Label();
	private static DecimalFormat df = new DecimalFormat("0.00");
	private String numeroDeCuotaGlobal;
	
	private String antiguoPropitarioTraslado;
	private String codigoPropiedadTraslado;
	private String nuevoPropietarioTraslado;

    /*============================== ??REA DE CLICK DE BOTONES ==============================*/
    @FXML
    void clickRegresar(MouseEvent event) throws IOException {
    	cargarEscena("/principal/pruebaFXML.fxml", 1);
    }
    
    @FXML
    void clickBuscar(MouseEvent event) {
    	obtenerDatos(textDpiEmisor.getText(), textCodigoPropiedad.getText(), textDpiReceptor.getText());
    }

    @FXML
    void clickHistorial(MouseEvent event) {
    	crearReporteHistorialTraslados();
    }

    @FXML
    void clickTrasladar(MouseEvent event) {
    	trasladarPropiedad(textDpiEmisor.getText(), textCodigoPropiedad.getText(), textDpiReceptor.getText());
    }

	/*============================== TECLA ENTER EN LOS TEXTFIELD ==============================*/
    @FXML
    void enterCodigoPropiedad(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
        	obtenerDatos(textDpiEmisor.getText(), textCodigoPropiedad.getText(), textDpiReceptor.getText());       
        }
    }

    @FXML
    void enterDpiEmisor(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
        	obtenerDatos(textDpiEmisor.getText(), textCodigoPropiedad.getText(), textDpiReceptor.getText());       
        }
    }
    
    @FXML
    void enterDpiReceptor(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
        	obtenerDatos(textDpiEmisor.getText(), textCodigoPropiedad.getText(), textDpiReceptor.getText());       
        }
    }

    /*============================== EFECTO HOVER ==============================*/
    @FXML
    void hoverBuscar(MouseEvent event) {
    	colorFondoBoton(rectangleBuscar, "#f800f8", 200);
    }

    @FXML
    void hoverHistorial(MouseEvent event) {
//    	colorFondoBoton(rectangleHistorial, "#f800f8", 200);
    }

    @FXML
    void hoverTrasladar(MouseEvent event) {
    	colorFondoBoton(rectangleTrasladar, "#f800f8", 200);
    }

    /*============================== EFECTO NO HOVER ==============================*/
    @FXML
    void noHoverBuscar(MouseEvent event) {
    	colorFondoBoton(rectangleBuscar, "#FFF", 200);
    }

    @FXML
    void noHoverHistorial(MouseEvent event) {
    }

    @FXML
    void noHoverTrasladar(MouseEvent event) {
    	colorFondoBoton(rectangleTrasladar, "#FFF", 200);
    }

    /*============================== ELEMENTOS SELECCIONADOS ==============================*/
    @FXML
    void pressLabelCodigoPropiedad(MouseEvent event) {
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    }

    @FXML
    void pressLabelDpiEmisor(MouseEvent event) {
    	requestedTextFields(textDpiEmisor, labelDpiEmisor, lineDpiEmisor);
    }

    @FXML
    void pressLabelDpiReceptor(MouseEvent event) {
    	requestedTextFields(textDpiReceptor, labelDpiReceptor, lineDpiReceptor);
    }

    @FXML
    void pressTextCodigoPropiedad(MouseEvent event) {
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    }

    @FXML
    void pressTextDpiEmisor(MouseEvent event) {
    	requestedTextFields(textDpiEmisor, labelDpiEmisor, lineDpiEmisor);
    }

    @FXML
    void pressTextDpiReceptor(MouseEvent event) {
    	requestedTextFields(textDpiReceptor, labelDpiReceptor, lineDpiReceptor);
    }

    /*============================== ELEMENTOS TABULADOS O CON FOCUS ==============================*/
    @FXML
    void textTypedCodigoPropiedad(KeyEvent event) {
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    }

    @FXML
    void textTypedDpiEmisor(KeyEvent event) {
    	requestedTextFields(textDpiEmisor, labelDpiEmisor, lineDpiEmisor);
    }

    @FXML
    void textTypedDpiReceptor(KeyEvent event) {
    	requestedTextFields(textDpiReceptor, labelDpiReceptor, lineDpiReceptor);
    }

    /**
     * Permite el cambio de escena.
     * @param ruta De la nueva escena.
     * @param direccion Direcci??n de la animaci??n del intercambio de escena.
     * @throws IOException
     */
    private void cargarEscena(String ruta, int direccion) throws IOException {
    	// Cargamos la escena    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
    	Parent root = (Parent)loader.load();
    	Scene escena = botonSalir.getScene();
    	
    	// Obtenemos y a??adimos la escena:
    	root.translateXProperty().set(escena.getWidth() * direccion);
    	miAnchorPaneTraslado.getChildren().add(root);
    	
    	// Mostramos la escena con una l??nea de tiempo:
    	Timeline timeLine = new Timeline();
    	KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
    	timeLine.getKeyFrames().add(kf);
    	timeLine.play();
    }
    
    /**
     * Anima verticalmente los label de los input.
     * @param pText Realiza un requesteFocus una vez seleccionada el label.
     * @param pLabel Reciben un requesteFocus para que pueda escribir el usuario.
     */
    private void requestedTextFields(TextField pText, Label pLabel, Line pLine) {    	
    	// Se eval??an al TextField anterior para ver si contiene texto o no y devolver el label.
    	if(sinDatos.getText().isEmpty()) {
    		animarLabel(sinDatosLabel, 0);
    	}
    	sinDatos = pText;
    	sinDatosLabel = pLabel;
    	
    	// Se le quita el focus anterior al line anterior.
    	lineNoFocus.setStroke(Color.WHITE);
    	lineNoFocus = pLine;
    	    	
    	// Se anima el label, se ingresa el focus y se pone de color azul el line.
    	animarLabel(pLabel, -32);
    	pText.requestFocus();
    	pLine.setStroke(Color.web("#77a6ce")); 	
    }
    
    /**
     * Anima los Labels en el eje Y.
     * @param pLabel Label quien sufrir?? la animaci??n.
     * @param pos Nueva posici??n del label tras terminada la animaci??n.
     */
    private void animarLabel(Label pLabel, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLabel);
    	transicion.setToY(pos);
    	transicion.play();
    }
    
    /**
	 * Recorre un String y extrae cualquier n??mero y los guarda en una lista.
	 * @param cadena Par??metro a recorrer.
	 * @return Una lista de todos los n??meros encontrados en la cadena.
	 */
	List<Integer> extraerNumeros(String cadena) {
	      List<Integer> todosLosNumeros = new ArrayList<Integer>();
	      Matcher coincidencias = Pattern.compile("\\d+").matcher(cadena);
	      while (coincidencias.find()) { 
	        todosLosNumeros.add(Integer.parseInt(coincidencias.group()));
	      } 
	      return todosLosNumeros;
	 }	
    
    /**
     * Cambia el color del rect??ngulo cuando el mouse se posiciona encima o sale del ella.
     * @param pRectangle Pinta el rect??ngulo del bot??n.
     * @param pColor Nuevo color del fondo.
     * @param pMilisegundos Tiempo de la transici??n.
     */
    private void colorFondoBoton(Rectangle pRectangle, String pColor, int pMilisegundos) {    	
    	Timeline timeline = new Timeline();    
    	KeyValue kv;
    	
    	if(pColor.equals("#FFF")) {
    		kv = new KeyValue(pRectangle.fillProperty(),Color.rgb(0, 0, 0,0));	
    	}else if(pColor.equals("Radio")) {
    		kv = new KeyValue(pRectangle.fillProperty(),Color.rgb(118, 165, 204, 0.5));			
    	}else {
    		kv = new KeyValue(pRectangle.fillProperty(),Color.web(pColor));
    	}
    	/*
    	KeyValue kv = pColor.equals("#FFF") ? new KeyValue(pRectangle.fillProperty(),Color.rgb(0, 0, 0,0)) 
    										: new KeyValue(pRectangle.fillProperty(),Color.web(pColor));
    	*/
    	KeyFrame kf = new KeyFrame(Duration.millis(pMilisegundos), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();    	
    }
    
    /**
     * Revisa los 3 textFiels que est??n llenos antes de realizar la consulta.
     * @param args Vector de strings que averigua si los campos contienen texto.
     * @return
     */
    public boolean textLlenos(String ...args) {
    	for(int i = 0; i < args.length; i++) {
    		if(args[i].isEmpty()) {
            	alertaError("Por favor, llene los campos faltanes.");
            	return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Consulta la base de datos seg??n el DPI y c??digo de propiedad ingresados.
     * @param pDPI Dpi del cliente.
     * @param pCodigo C??digo de la propiedad.
     */
    public void obtenerDatos(String dpiPropietario, String pCodigo, String dpiNuevoPropietario) {
    	
    	// Revisamos que los textFields contienen texto:
    	if(!textLlenos(dpiPropietario, pCodigo, dpiNuevoPropietario)) {
    		return;
    	}
    	
    	// Instanciamos un objeto para consultar a la base de datos:
		ConsultarControlDePagos consultar = new ConsultarControlDePagos();		
		
		// Instanciamos una lista de dtoControlDePagos:
		List<DtoControlDePagos> propietarioActual = new ArrayList<>();
		
		// Obtenemos los datos. Si est?? vac??a, quiere decir que no encontr?? coincidencias con 
		// DPI o c??digo ingresados.
		propietarioActual = consultar.obtenerDatos(dpiPropietario, pCodigo);
		if(propietarioActual.isEmpty()) {			
			return;
		};
		
		// Ahora revisamos s?? el dpi del nuevo propietario existe:
    	ConsultarCliente consultarClienteNuevo = new ConsultarCliente();
    	
    	// Instanciamos una lista de dtoPersona:
		List<DtoPersona> clienteNuevo = new ArrayList<>();

		// Obtenemos los datos para ver s?? existe este segundo propietario:
		clienteNuevo = consultarClienteNuevo.obtenerDatos(dpiNuevoPropietario);
		if(clienteNuevo.isEmpty()) {
			return;
		}	
		
		// Mostramos los datos
		setearDatos(propietarioActual);
		nuevoPropietarioTraslado = dpiNuevoPropietario;
		return;
    }
    
    /**
     * Tras realizar la consult y obtener los datos, los ingresa en los labels inferiores.
     * @param pagos Par??metro para consultar el control de pagos.
     */
    public void setearDatos(List<DtoControlDePagos> pagos) {
    	// Consultamos al cliente actual
    	ConsultarCliente consultarCliente = new ConsultarCliente();
    	
    	// Instanciamos una lista de dtoPersona:
		List<DtoPersona> propietarioActual = new ArrayList<>();

		// Obtenemos los datos.
		propietarioActual = consultarCliente.obtenerDatos(pagos.get(0).getDpi_cliente());
    	
    	// Consultamos propiedad:
    	ConsultarPropiedad consultarPropiedad = new ConsultarPropiedad();
    	
    	// Instanciamos una lista de dtoPersona:
		List<DtoPropiedad> propiedad = new ArrayList<>();

		// Obtenemos los datos.
		propiedad = consultarPropiedad.obtenerDatos(pagos.get(0).getCodigo_propiedad());
		
		// Ingresamos los datos en los labels:
		labelPropietario.setText(propietarioActual.get(0).getNombre());
		labelValor.setText("Q. " + df.format(Double.parseDouble(propiedad.get(0).getValor_de_la_propiedad())));
		labelInteres.setText(propiedad.get(0).getPorcentaje_de_interes() + "%");
		labelTotalCuotas.setText(propiedad.get(0).getCantidad_de_cuotas());
		
		// C??lculamos mora:
		// La mora lo calculos con la fecha del ??ltimo pago respecto al d??a actual:
		// Declaramos el iterador y recorremos el ArrayList
		List<Integer> mora = extraerNumeros(propiedad.get(0).getFecha_de_adquisicion());
		Iterator<Integer> miIterator = mora.iterator();

		// Contador que nos ayudar?? a obtener los d??as, meses y a??os.
		int contador = 0;

		// Obtenemos la fecha actual del sistema:
		Calendar hoy = Calendar.getInstance();

		// Recorremos el iterador:
		while (miIterator.hasNext()) {
			// Elemento que almacena la fecha del ??ltimo pago:
			Integer elemento = miIterator.next();

			// Restamos fecha actual con la fecha del ??ltimo pago:
			if (contador == 0) {
				hoy.add(Calendar.DATE, -elemento);
			} else if (contador == 1) {
				hoy.add(Calendar.MONTH, -elemento);
			} else if (contador == 2) {
				hoy.add(Calendar.YEAR, -elemento);
			}
			contador++;
		}

		// Obtenemos los meses y a??os por individuales con localDate
		LocalDate localDate = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int meses = localDate.getMonthValue();
		int anio = localDate.getYear();

		// Si se debe m??s de un a??o, sumamos 12 cuotas
		meses = anio == 0 ? meses : meses + (12 * anio);
		meses = meses == -1 ? 0 : meses;
		System.out.println(meses);

		// Nos ayuda a calcular el n??meros de cuotas (Solo para uso del c??lculo del
		// pago)
		int cuota_a_pagar = 0;

		// Obtenemos el total pagado:
		double totalPagado = 0;

		// Creamos una lista modelo para ser ingresado en la tabla:
		List<ModeloDatos> modelo = new ArrayList<>();

		// Obtenemos los datos y los seteamos al modelo:
		for (int i = 0; i < pagos.get(0).getNo_cuota_pagada().size(); i++) {
			// Creanos un modelo para recibir los datos:
			ModeloDatos miModelo = new ModeloDatos();

			// Seteamos los datos.
			miModelo.setNo(pagos.get(0).getNo_cuota_pagada().get(i).replace("{", "").replace("}", ""));
			miModelo.setValor("Q. " + pagos.get(0).getTotal_pagado().get(i).replace("{", "").replace("}", ""));
			miModelo.setFecha(pagos.get(0).getFecha_de_pago().get(i).replace("{", "").replace("}", ""));
			miModelo.setInteres(pagos.get(0).getInteres().get(i).replace("{", "").replace("}", "") + "%");
			miModelo.setPendiente("No");

			// Si el n??mero de cuota es menor a 3 d??gitos, quiere decir que se trata de un
			// pago de mora
			// Lo que se busca es guardar ??ltimo pago de cuota:
			numeroDeCuotaGlobal = miModelo.getNo().length() < 4 ? miModelo.getNo() : numeroDeCuotaGlobal;

			// Guardamos el total pagado para mostrar el total despu??s:
			cuota_a_pagar = miModelo.getNo().length() < 4 ? cuota_a_pagar + 1 : cuota_a_pagar;
			totalPagado = Double.parseDouble(miModelo.getValor().substring(2)) + totalPagado;

			// Agregamos el modelo a la lista modelo:
			modelo.add(miModelo);
		}

		// Ya teniendo el n??mero de cuotas pagadas, pasamos a calcular ahora s?? las
		// moras:
		meses = meses - Integer.parseInt(numeroDeCuotaGlobal) + 1;
		labelMoras.setText(meses >= 0 ? String.valueOf(meses) + " cuotas."
				: "Lleva " + String.valueOf(meses * -1 + 1) + " cuotas adelantadas.");
		meses = meses >= 0 ? meses : 0;

		// Mostramos el total pagado y el total de cuotas pagadas:
		labelTotalPagado.setText("Total pagado: Q." + df.format(totalPagado));	
		labelCuotasPagadas.setText(numeroDeCuotaGlobal);
		
		// Tambi??n ingresamos los datos a variable globales s?? quiere trasladar la propiedad:
		antiguoPropitarioTraslado = pagos.get(0).getDpi_cliente();
		codigoPropiedadTraslado = pagos.get(0).getCodigo_propiedad();
		
		// Mostramos el bot??n de traslado:
		labelTrasladar.setVisible(true);
		rectangleTrasladar.setVisible(true);
		FadeTransition labelAparecer = new FadeTransition(Duration.millis(500), labelTrasladar);
		labelAparecer.setFromValue(0.0);
		labelAparecer.setToValue(1);		    
		labelAparecer.play();	
	    FadeTransition rectangleAparecer = new FadeTransition(Duration.millis(500), rectangleTrasladar);
	    rectangleAparecer.setFromValue(0.0);
	    rectangleAparecer.setToValue(1);		    
	    rectangleAparecer.play();
    }
    
    /**
     * Muestra una alerta con un mensaje de error.
     * @param mensajeError Texto explicativo del error.
     */
    public void alertaError(String mensajeError) {
    	Alert alert = new Alert(AlertType.ERROR, mensajeError, ButtonType.OK);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.showAndWait(); 
    }
    
    /**
     * Traslada una propiead a su nuevo due??o.
     * @param propietarioActual Antiguo due??o.
     * @param codigoPropiedad Propiedad.
     * @param propietarioNuevo Nuevo due??o.
     */
    private void trasladarPropiedad(String propietarioActual, String codigoPropiedad, String propietarioNuevo) {
	
    	String mensaje = "??Est?? seguro de trasladar la propiedad con c??digo: " + codigoPropiedad +
    			" perteneciente al propietario " + propietarioActual + " al nuevo propietario con "
    			+ "n??mero de DPI " + propietarioNuevo + "\n\nTenga en cuenta que todo lo realizado "
    			+ "por el antiguo propietario pasar?? en manos del nuevo, incluyento deudas.";
    	
    	Alert alert = new Alert(AlertType.WARNING, mensaje, ButtonType.YES, ButtonType.NO);
    	alert.setTitle("Informaci??n del traslado");
    	alert.setHeaderText(null);
    	Optional<ButtonType> result = alert.showAndWait();

    	// Si la respuesta en No, terminamos el proceso.
		if (result.get() == ButtonType.NO) {
			return;
		}
    	
		// Si eligi?? s??, seguimos con el proceso de traslado:
		ActualizarControlDePagos trasladar = new ActualizarControlDePagos();
		
		// Trasladadmos:
		if(!trasladar.actualizarTrasladoPropiedad(propietarioActual, codigoPropiedad, propietarioNuevo)) {
			return;
		};
		
		// Si no hubo errores, actualizamos el registro de traslados:
		ActualizarRegistroTraslados actualizarHistorial = new ActualizarRegistroTraslados();

		// Obtenemos la fecha actual:
		Date date = new Date();
		DateFormat formato_fecha = new SimpleDateFormat("dd/MM/yyyy");
		
		// Guardamos los datos
		actualizarHistorial.guardarTraslado(propietarioActual, codigoPropiedad, propietarioNuevo, formato_fecha.format(date));
		
		// Actualizamos el dpi dentro de la tabla de propiedades:
		ActualizarPropiedad actualizarDpi = new ActualizarPropiedad();
		actualizarDpi.actualizarDpiCliente(propietarioNuevo, propietarioActual);
		
		// Reestablecemos la pantalla:
		limpiarDatos();
	}
    
    /**
     * Limpia los datos antiguos y reestablece la pantalla.
     */
    public void limpiarDatos() {
    	labelPropietario.setText("");
    	labelValor.setText("");
    	labelInteres.setText("");
    	labelTotalCuotas.setText("");
    	labelTotalPagado.setText("");
    	labelMoras.setText("");
    	labelCuotasPagadas.setText("");
    	labelTrasladar.setVisible(false);
    	rectangleTrasladar.setVisible(false);
    	textCodigoPropiedad.setText("");
    	textDpiReceptor.setText("");
    	textDpiEmisor.setText("");
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    	requestedTextFields(textDpiReceptor, labelDpiReceptor, lineDpiReceptor);
    	requestedTextFields(textDpiEmisor, labelDpiEmisor, lineDpiEmisor);
    }
    
    /**
     * Genera un reporte de todos los traslados que se han hecho.
     */
    public void crearReporteHistorialTraslados() {
    	ReporteTraslados reporte = new ReporteTraslados();
    	reporte.crearReporteTraslados();
    }
    
    /*============================== INICIALIZADOR ==============================*/   
    @FXML
    void initialize() throws InterruptedException { 
    	labelTrasladar.setVisible(false);
		rectangleTrasladar.setVisible(false);
    }

}
