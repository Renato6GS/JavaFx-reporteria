package controlDePagos;

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
import accionesBD.ConsultarCliente;
import accionesBD.ConsultarControlDePagos;
import accionesBD.ConsultarPropiedad;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

public class ControladorControlDePagos {

	@FXML
    private AnchorPane miAnchorPane;

    @FXML
    private Label labelTitulo;

    @FXML
    private TableView<ModeloDatos> tableViewDatos;

    @FXML
    private TableColumn<?, ?> columnCuota;

    @FXML
    private TableColumn<?, ?> columnValor;

    @FXML
    private TableColumn<?, ?> columnFecha;

    @FXML
    private TableColumn<?, ?> columnInteres;

    @FXML
    private TableColumn<?, ?> columnPendiente;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelApellidos;

    @FXML
    private Label labelValorTotal;

    @FXML
    private Label labelTotalPagado;

    @FXML
    private Label labelUltimoPago;

    @FXML
    private Label labelMeseMora;

    @FXML
    private Rectangle rectangleAbonar;

    @FXML
    private Label labelAbonar;
    
    @FXML
    private Label labelTotalCuotasAPagar;

    @FXML
    private ImageView botonSalir;
    
    // Variables
    private int mesesMora;
    private DtoPersona miClienteGlobal;
    private DtoPropiedad miPropiedadGlobal;
    private DtoControlDePagos miControlGlobal;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private String numeroDeCuotaGlobal;
    

    /*============================== ÁREA DE CLICK DE BOTONES ==============================*/
	@FXML
    void clickAbonar(MouseEvent event) {
		mostrarAbono(calcularPago());
    }
	
    @FXML
    void clickSalir(MouseEvent event) throws IOException {
    	cargarEscena("/principal/pruebaFXML.fxml", -1);
    }
    
    /*============================== EFECTO HOVER ==============================*/
    @FXML
    void hoverAbonar(MouseEvent event) {
    	colorFondoBoton(rectangleAbonar, "#f800f8", 200);
    }

    /*============================== EFECTO NO HOVER ==============================*/
    @FXML
    void noHoverAbonar(MouseEvent event) {
    	colorFondoBoton(rectangleAbonar, "#FFF", 200);
    }

    /*============================== MÉTODOS ==============================*/
    /**
     * Muestra una nueva escena.
     * @param ruta Dirección de la nueva escena a mostrar.
     * @param direccion Animación.
     * @throws IOException
     */
    private void cargarEscena(String ruta, int direccion) throws IOException {
    	// Cargamos la escena    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
    	Parent root = (Parent)loader.load();
    	Scene escena = botonSalir.getScene();
    	
    	// Obtenemos y añadimos la escena:
    	root.translateXProperty().set(escena.getWidth() * direccion);
    	miAnchorPane.getChildren().add(root);
    	
    	// Mostramos la escena con una línea de tiempo:
    	Timeline timeLine = new Timeline();
    	KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
    	timeLine.getKeyFrames().add(kf);
    	timeLine.play();
    }
    
    /**
     * Consulta los pagos de acuerdo al DPI y Código de la propiedad ingresadas.
     * @return false si se cancela o no se encontró coincidencias.
     */
    public boolean consultarPagos() {
    	// Pedimos el DPI
    	TextInputDialog dialog = new TextInputDialog("DPI");    	
    	dialog.setTitle("Control de pagos");
    	dialog.setHeaderText(null);
    	dialog.setContentText("Ingrese el DPI del cliente:");
    	
    	// Traditional way to get the response value.
    	Optional<String> res = dialog.showAndWait();
    	
    	// Pedimos el código de la propiedad
    	TextInputDialog dialogC = new TextInputDialog("Código de propiedad");    	
    	dialogC.setTitle("Control de pagos");
    	dialogC.setHeaderText(null);
    	dialogC.setContentText("Ingrese el código de la propiedad:");
    	
    	// Traditional way to get the response value.
    	Optional<String> resC = dialogC.showAndWait();
    	    	
    	// Si no cancela, se realiza la consulta:
    	if (res.isPresent() && resC.isPresent()){
    		System.out.println("Prueba: " + res.get());
    		return obtenerDatos(res.get(), resC.get());    		
    	} else {    	
    		return false;
    	}
    }
    
    /**
     * Consulta la base de datos según el DPI y código de propiedad ingresados.
     * @param pDPI Dpi del cliente.
     * @param pCodigo Código de la propiedad.
     */
    public boolean obtenerDatos(String pDPI, String pCodigo) {
    	// Instanciamos un objeto para consultar a la base de datos:
		ConsultarControlDePagos consultar = new ConsultarControlDePagos();		
		
		// Instanciamos una lista de dtoControlDePagos:
		List<DtoControlDePagos> misDatos = new ArrayList<>();
		
		// Obtenemos los datos. Si está vacía, quiere decir que no encontró coincidencias con 
		// DPI o código ingresados.
		misDatos = consultar.obtenerDatos(pDPI, pCodigo);
		if(misDatos.isEmpty()) {			
			return false;
		};
		
		// Mostramos los datos
		setearDatos(misDatos);
		return true;
    }
    
    /**
     * Setea los datos y los muestra en pantalla. También consulta a las demás tablas.
     * @param pagos Recibe los datos a mostrar.
     */
    public void setearDatos(List<DtoControlDePagos> pagos) {
    	// Consultamos al cliente
    	ConsultarCliente consultarCliente = new ConsultarCliente();
    	
    	// Instanciamos una lista de dtoPersona:
		List<DtoPersona> cliente = new ArrayList<>();

		// Obtenemos los datos.
		cliente = consultarCliente.obtenerDatos(pagos.get(0).getDpi_cliente());
    	
    	// Consultamos propiedad:
    	ConsultarPropiedad consultarPropiedad = new ConsultarPropiedad();
    	
    	// Instanciamos una lista de dtoPersona:
		List<DtoPropiedad> propiedad = new ArrayList<>();

		// Obtenemos los datos.
		propiedad = consultarPropiedad.obtenerDatos(pagos.get(0).getCodigo_propiedad());
		
		// Seteamos los datos en el recuadro superior:
		labelNombre.setText("Nombre: " + cliente.get(0).getNombre());
		labelApellidos.setText("Apellidos: " + cliente.get(0).getApellidos());
		labelValorTotal.setText("Valor de la propiedad: Q. " + df.format(Double.parseDouble(propiedad.get(0).getValor_de_la_propiedad())));
//		labelTotalPagado.setText("Total pagado: Q." + df.format(Double.parseDouble(pagos.get(0).getTotal_pagado().get(pagos.get(0).getTotal_pagado().size()-1).replace("}",""))));
		labelUltimoPago.setText("Último pago realizado: " + pagos.get(0).getFecha_de_pago().get(pagos.get(0).getFecha_de_pago().size()-1).replace("}","").replace("{", ""));
		
		// Seteamos total de cutoas a pagar:
		labelTotalCuotasAPagar.setText("Total de cuotas a pagar: " + propiedad.get(0).getCantidad_de_cuotas());
		
		// Cálculamos mora:
		// La mora lo calculos con la fecha del último pago respecto al día actual:
		// Declaramos el iterador y recorremos el ArrayList
//    	List<Integer> mora = extraerNumeros(pagos.get(0).getFecha_de_pago().get(pagos.get(0).getFecha_de_pago().size()-1).replace("}",""));    	
    	List<Integer> mora = extraerNumeros(propiedad.get(0).getFecha_de_adquisicion());    	
    	Iterator<Integer> miIterator = mora.iterator();
    	
    	// Contador que nos ayudará a obtener los días, meses y años.
    	int contador = 0;

    	// Obtenemos la fecha actual del sistema:
    	Calendar hoy = Calendar.getInstance();
    	
    	// Recorremos el iterador:
    	while(miIterator.hasNext()) {
    		// Elemento que almacena la fecha del último pago:
    		Integer elemento = miIterator.next();    

    		// Restamos fecha actual con la fecha del último pago:
    		if(contador == 0) {    		
    			hoy.add(Calendar.DATE, -elemento);
    		}    		
    		else if(contador == 1) {
    			hoy.add(Calendar.MONTH, -elemento);
    		}else if(contador == 2) {
    			hoy.add(Calendar.YEAR, -elemento);
    		}    		
    		contador++;    		
    	}    	
//    	DateFormat formato_fecha = new SimpleDateFormat("dd/MM/yyyy");
//		System.out.println(formato_fecha.format(hoy.getTime()));
		
    	// Obtenemos los meses y años por individuales con localDate
		LocalDate localDate = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int meses = localDate.getMonthValue();
		int anio = localDate.getYear();
		
		// Si se debe más de un año, sumamos 12 cuotas
		System.out.println("MESEEEEEES: " + meses);
		meses = anio == 0 ? meses : meses+(12*anio);
		System.out.println("MESES 2: " + meses);
		System.out.println("AÑOSS: " + anio);
		meses = meses == -1 ? 0 : meses;
		System.out.println(meses);
		
		//labelMeseMora.setText("Meses de mora: " + String.valueOf(meses) + " cuotas.");
		
		// Antes de asignar las cuotas que se deben, debemos calcular el número de cuotas pagadas.
		
		// Nos ayuda a calcular el números de cuotas (Solo para uso del cálculo del pago)
		int cuota_a_pagar = 0;
		
		// Obtenemos el total pagado:
//		double totalPagado = Double.parseDouble(pagos.get(0).getTotal_pagado().get(pagos.get(0).getTotal_pagado().size()-1).replace("}",""));
		double totalPagado = 0;
		
		// Creamos una lista modelo para ser ingresado en la tabla:
		List<ModeloDatos> modelo = new ArrayList<>();
		
		for(int i = 0; i < pagos.get(0).getNo_cuota_pagada().size(); i++) {			
			
			// Creanos un modelo para recibir los datos:
			ModeloDatos miModelo = new ModeloDatos();
			
			// Seteamos los datos.
			miModelo.setNo(pagos.get(0).getNo_cuota_pagada().get(i).replace("{", "").replace("}", ""));
			miModelo.setValor("Q. " + pagos.get(0).getTotal_pagado().get(i).replace("{", "").replace("}", ""));
			miModelo.setFecha(pagos.get(0).getFecha_de_pago().get(i).replace("{", "").replace("}", ""));
			miModelo.setInteres(pagos.get(0).getInteres().get(i).replace("{", "").replace("}", "") + "%");
			miModelo.setPendiente("No");
						
			// Si el número de cuota es menor a 3 dígitos, quiere decir que se trata de un pago de mora
			// Lo que se busca es guardar último pago de cuota:
			numeroDeCuotaGlobal = miModelo.getNo().length() < 4 ? miModelo.getNo() : numeroDeCuotaGlobal;
			
			// Guardamos el total pagado para mostrar el total después:			
			totalPagado = Double.parseDouble(miModelo.getValor().substring(2)) + totalPagado;
			
			// Si el primer dato está vacío, se trata del pago de la primera cuota:
			if(pagos.get(0).getFecha_de_pago().get(0).equals("{\"\"}")) { break; }
			
			cuota_a_pagar = miModelo.getNo().length() < 4 ? cuota_a_pagar+1 : cuota_a_pagar;
			
			// Agregamos el modelo a la lista modelo:
			modelo.add(miModelo);
		}
		
		// Ya teniendo el número de cuotas pagadas, pasamos a calcular ahora sí las moras:
		meses = meses - Integer.parseInt(numeroDeCuotaGlobal) + 1;
		labelMeseMora.setText(meses >= 0 ? "Meses de mora: " + String.valueOf(meses) + " cuotas." : 
							  "Meses de mora: Lleva " + String.valueOf(meses*-1+1) + " cuotas adelantadas.");
		meses = meses >= 0 ? meses : 0;
		
		// Una vez calculadas las moras, sumanos 1 al total para ser registrado en la tabla
		// al momento de abonar:
		if(!pagos.get(0).getFecha_de_pago().get(0).equals("{\"\"}")) {
			numeroDeCuotaGlobal = String.valueOf(Integer.parseInt(numeroDeCuotaGlobal)+1);			
		}
		System.out.println(numeroDeCuotaGlobal);		
		
		// Agregamos un último modelo el cual sería el nuevo a pagar:
		cuota_a_pagar++;
		ModeloDatos miUltimoModelo = new ModeloDatos();
		miUltimoModelo.setNo(String.valueOf(cuota_a_pagar));
		miUltimoModelo.setValor("Q. -");
		miUltimoModelo.setFecha("--/--/----");
		miUltimoModelo.setInteres(propiedad.get(0).getPorcentaje_de_interes() + "%");
		miUltimoModelo.setPendiente("Sí");		
		modelo.add(miUltimoModelo);
		
		// Mostramos el total pagado que lleva el cliente (encabezado):
		labelTotalPagado.setText("Total pagado: Q." + df.format(totalPagado));
	
		// Creamos una lista observable para ser ingresada en la tabla.
		ObservableList<ModeloDatos> data = FXCollections.observableArrayList(
				modelo
		);
		
		// Hacemos con los atributos de la clase y las columnas de la tabla:
		columnCuota.setCellValueFactory(
				new PropertyValueFactory<>("No")
		);
		columnValor.setCellValueFactory(
				new PropertyValueFactory<>("Valor")
				);
		columnFecha.setCellValueFactory(
				new PropertyValueFactory<>("Fecha")
				);
		columnInteres.setCellValueFactory(
				new PropertyValueFactory<>("Interes")
				);
		columnPendiente.setCellValueFactory(
				new PropertyValueFactory<>("Pendiente")
				);
		
		// Ingresamos los datos a la tabla.
		tableViewDatos.setItems(data);
		
		// Seteamos los datos consultados a variables globales para ser utilizados:
		miClienteGlobal = cliente.get(0);
	    miPropiedadGlobal = propiedad.get(0);
	    miControlGlobal = pagos.get(0);
	    mesesMora = meses;
    }
    
    
    /**
	 * Recorre un String y extrae cualquier número y los guarda en una lista.
	 * @param cadena Parámetro a recorrer.
	 * @return Una lista de todos los números encontrados en la cadena.
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
	 * Calcula la cuota a pagar por mes.
	 * @return Retorna la cuota a pagar.
	 */
	public double calcularPago() {
		return ((Double.parseDouble(miPropiedadGlobal.getValor_de_la_propiedad())/Integer.parseInt(miPropiedadGlobal.getCantidad_de_cuotas())) 
				* (1 + (Double.parseDouble(miPropiedadGlobal.getPorcentaje_de_interes())/100)));
	}
	
	/**
	 * Si no hay moras, muestra el detalle del pago y cuánto debe pagar.
	 * @param cantidad_a_pagar Resultado de la función calcularPago()
	 */
	public void mostrarAbono(double cantidad_a_pagar) {
		
		// Verificamos si ya se pagaron todas la cuotas:
		int cantidadTotalCuotas = Integer.parseInt(miPropiedadGlobal.getCantidad_de_cuotas());
		int cantidadCuotasPagada = Integer.parseInt(numeroDeCuotaGlobal);
		if (cantidadCuotasPagada > cantidadTotalCuotas) {
			totalDeCuotasYaPagadas();
			return;
		}		
				
		// Se verifica que no tenga moras, de lo contrario no podrá ejecutar pagos.
		if(mesesMora != 0) {
			alertaMora();
			return;
		}
		
		// Se muestra una alerta con el detalle del pago.
		String informacionDelPago = "Detalles del pago: \nCantidad a pagar: " + String.valueOf(df.format(cantidad_a_pagar))
		 							+ "\nCon código de propiedad: " + miPropiedadGlobal.getCodigo_propiedad()
		 							+ "\nQue se encuentra a nombre de: " + miClienteGlobal.getNombre()
		 							+ "\ncon el No. de DPI: " + miControlGlobal.getDpi_cliente()
		 							+ "\n¿Está seguro de proceder con el pago?";
		Alert alert = new Alert(AlertType.INFORMATION, informacionDelPago, ButtonType.YES, ButtonType.NO);
       	alert.setTitle("Información del pago");  
       	alert.setHeaderText(null);  
       	Optional<ButtonType> res = alert.showAndWait();

       	// Si el usuario está de acuerdo y presiona el botón "Sí", entonces se le informa que realizó un pago
       	// y se actualizar la base de datos.
       	if (res.get() == ButtonType.YES) {
       		
       		// Guardamos el pago en la base de datos:
       		guardarPago(cantidad_a_pagar);
       	}             	
	}
	
	/**
	 * Si el cliente tiene moras, se muestra el siguiente método.
	 */
	public void alertaMora() {
		
		// Cálculamo la mora que debe pagar el cliente.
		double calculoMoraAPagar = calcularPago()*5/100;
		double totalAPagarConMora = calculoMoraAPagar*mesesMora;
//		double totalAPagarConMora = calculoMoraAPagar*mesesMora+calcularPago();
	
		// Se muestra el detalle de la mora y se le pide al cliente si desea pagarla o no.
		String informacionMora = "El cliente con DPI: " + miControlGlobal.getDpi_cliente() 
				+ " cuenta con: " + mesesMora 
				+ " meses de mora."
				+ "\nPara poder ejecutar un abono a la última cuota, también deberá pagar la mora "
				+ "que debe.\nLa mora es de un 5% a la mensualidad por cada cuota que se deba."
				+ "\nLa suma a pagar por cada mora es de: \nQ. " + df.format(calculoMoraAPagar)
				+ "\nLa cantidad total a pagar debido a las moras es de: Q." + String.valueOf(df.format(totalAPagarConMora))
				+ "\nLa suma a pagar por las cuotas es de: Q." + df.format(calcularPago()*mesesMora)
				+ "\nEn total pagará: Q. " + String.valueOf(df.format(totalAPagarConMora+calcularPago()*mesesMora))
				+ "\n¿Desea proceder con el pago?";
		Alert alert = new Alert(AlertType.WARNING, informacionMora, ButtonType.YES, ButtonType.NO);
		alert.setTitle("Cliente moroso");
		alert.setHeaderText(null);
		Optional<ButtonType> res = alert.showAndWait();
		
		// Si sí desea pagarla, entonces se le pide que ingrese cuantas cuotas con mora desea pagar:
		if (res.get() == ButtonType.YES) {

			
			// Instanciamos un objeto para actualizar la mora:
			ActualizarControlDePagos controlDePagos = new ActualizarControlDePagos();

			// Obtenemos la fecha actual:
			Date date = new Date();
			DateFormat formato_fecha = new SimpleDateFormat("dd/MM/yyyy");

			// Cálculamos le interés sumando el interés base más el interés por la mora:
			String interesMasMora = String
					.valueOf(Double.parseDouble(miPropiedadGlobal.getPorcentaje_de_interes()) + 5);

			// Actualizamos los datos con mora:
			String descripcion = "Pago de " + String.valueOf(mesesMora) + " moras atrasadas.";
			if(!controlDePagos.actualizarPagosDeMora(miClienteGlobal.getDPI(), miPropiedadGlobal.getCodigo_propiedad(),
					formato_fecha.format(date), interesMasMora, String.valueOf(totalAPagarConMora), descripcion)) {
				return;
			}
			
			// Actualizamos los datos sin mora:
			// Cálculamos le interés;
			String interesBase = String.valueOf(Double.parseDouble(miPropiedadGlobal.getPorcentaje_de_interes()));

			// Actualizamos los datos:
			for(int i = 0; i < mesesMora; i++) {
				// Guardamos el pago:
				descripcion = numeroDeCuotaGlobal;				
				if (!controlDePagos.actualizarPagosDeMora(miClienteGlobal.getDPI(), miPropiedadGlobal.getCodigo_propiedad(),
						formato_fecha.format(date), interesBase, String.valueOf(df.format(calcularPago())),descripcion)) {
					return;
				}
				// Sumamos 1 por cada cuota que deba:
				numeroDeCuotaGlobal = String.valueOf(Integer.parseInt(numeroDeCuotaGlobal)+1);
			}
			
			// Si llega a este punto, se actualizó correctamente:
			String mensajePagoRealizado = "Pagos de mora y cuota efectuadas exitósamente.";
			Alert alertExito = new Alert(AlertType.INFORMATION, mensajePagoRealizado, ButtonType.OK);
			alertExito.setTitle("Éxito");
			alertExito.setHeaderText(null);
			alertExito.showAndWait();
			
			// Actualizamos pantalla:
			obtenerDatos(miClienteGlobal.getDPI(), miPropiedadGlobal.getCodigo_propiedad());	    		
		}	
    }
	
	/**
	 * Verifica que la cadena ingresada sea un valor numérico.
	 * @param cadena Valor a convertir.
	 * @return Si es falso entonces no se trata de un número.
	 */
	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }	
	
	/**
	 * Si no se deben moras. Se agrega un pago sobre la cuota.
	 * @param cantidad_a_pagar Valor de la cuota más intereses.
	 */
	public void guardarPago(double cantidad_a_pagar) {
		// Instanciamos un objeto para actualizar la mora:
		ActualizarControlDePagos controlDePagos = new ActualizarControlDePagos();

		// Obtenemos la fecha actual:
		Date date = new Date();
		DateFormat formato_fecha = new SimpleDateFormat("dd/MM/yyyy");

		// Cálculamos le interés;
		String interesBase = String.valueOf(Double.parseDouble(miPropiedadGlobal.getPorcentaje_de_interes()));

		// Actualizamos los datos:
		String descripcion = numeroDeCuotaGlobal;
		
		// Si es igual a 1, quiere decir que se trata del primer pago:
		if(Integer.parseInt(descripcion) > 1) {
			if (!controlDePagos.actualizarPagosDeMora(miClienteGlobal.getDPI(), miPropiedadGlobal.getCodigo_propiedad(),
					formato_fecha.format(date), interesBase, String.valueOf(cantidad_a_pagar), descripcion)) {
				return;
			}			
		}else {
			if(!controlDePagos.actualizarPrimeraCuota(miClienteGlobal.getDPI(), miPropiedadGlobal.getCodigo_propiedad(), 
					formato_fecha.format(date), String.valueOf(cantidad_a_pagar))) {
				return;
			};
		}

		// Si llega a este punto, se actualizó correctamente:
		String mensajePagoRealizado = "Pago efectuado exitósamente.";
		Alert alertExito = new Alert(AlertType.INFORMATION, mensajePagoRealizado, ButtonType.OK);
		alertExito.setTitle("Éxito");
		alertExito.setHeaderText(null);
		alertExito.showAndWait();
		
		// Actualizamos pantalla:
		obtenerDatos(miClienteGlobal.getDPI(), miPropiedadGlobal.getCodigo_propiedad());
	}
	
	/**
     * Cambia el color del rectángulo cuando el mouse se posiciona encima o sale del ella.
     * @param pRectangle Pinta el rectángulo del botón.
     * @param pColor Nuevo color del fondo.
     * @param pMilisegundos Tiempo de la transición.
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
     * Muestra una alerta informando al cliente que ya pago todas sus cuotas.
     */
    public void totalDeCuotasYaPagadas() {
    	Alert confirmacion = new Alert(AlertType.INFORMATION, "¡¡¡Ya se pagaron todas la cuotas de la propiedad!!!", ButtonType.OK);
   		confirmacion.setTitle("Éxito");  
   		confirmacion.setHeaderText(null);
   		confirmacion.showAndWait();
    }
}