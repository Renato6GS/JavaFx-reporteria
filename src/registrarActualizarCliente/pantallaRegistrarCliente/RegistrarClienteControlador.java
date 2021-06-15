package registrarActualizarCliente.pantallaRegistrarCliente;

import java.awt.Toolkit;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DTOs.DtoPersona;
import accionesBD.ActualizarCliente;
import accionesBD.ActualizarControlDePagos;
import accionesBD.ActualizarPropiedad;
import accionesBD.ConsultarCliente;
import accionesBD.RegistrarCliente;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RegistrarClienteControlador {
	
	
	@FXML
	private AnchorPane miAnchorPane;

	@FXML
	private TextField textNombre;

	@FXML
	private Line lineNombre;

	@FXML
	private Label labelNombre;

	@FXML
	private Line lineNombreVertical;

	@FXML
	private Label labelRegresar;

	@FXML
	private Line lineInfRegresar;

	@FXML
	private Label labelRegresarFijo;

	@FXML
	private TextField textApellidos;

	@FXML
	private Line lineApellidos;

	@FXML
	private Label labelApellidos;

	@FXML
	private Line lineApellidosVertical;

	@FXML
	private TextField textDPI;

	@FXML
	private Line lineDPI;

	@FXML
	private Label labelDPI;

	@FXML
	private TextField textNit;

	@FXML
	private Line lineNit;

	@FXML
	private Label labelNit;

	@FXML
	private ComboBox<String> comboDiaNac;

	@FXML
	private ComboBox<String> comboMesNac;

	@FXML
	private ComboBox<String> comboAnioNac;

	@FXML
	private Label labelFechaNac;

	@FXML
	private Line lineDia;

	@FXML
	private Line lineMes;

	@FXML
	private Line lineAnio;

	@FXML
	private TextField textEdad;

	@FXML
	private Line lineEdad;

	@FXML
	private Label labelEdad;

	@FXML
	private Label labelHombre;

	@FXML
	private Label labelMujer;

	@FXML
	private Circle circleHombre;

	@FXML
	private Circle circleMujer;

	@FXML
	private Rectangle rectangleHombre;

	@FXML
	private Rectangle rectangleMujer;

	@FXML
	private Rectangle rectangleSeleccionSexo;

	@FXML
	private TextField textDireccion;

	@FXML
	private Line lineDireccion;

	@FXML
	private Label labelDireccion;

	@FXML
	private TextField textTelefono;

	@FXML
	private Line lineTelefono;

	@FXML
	private Label labelTelefono;

	@FXML
	private TextField textCorreo;

	@FXML
	private Line lineCorreo;

	@FXML
	private Label labelCorreo;

	@FXML
	private Rectangle rectangleGuardar;

	@FXML
	private Label btnGuardar;

	@FXML
	private Rectangle rectangleLimpiar;

	@FXML
	private Label btnLimpiar;

	@FXML
	private Label labelTitulo;
	    
	private Line lineNoFocus = new Line();
	private TextField sinDatos = new TextField();
	private Label sinDatosLabel = new Label();
	private String sexoFinal = "";
	private String dpiAnterior;
	
	// Expresiones Regulares:
	private final String soloTexto = "[a-zA-ZÀ-ÿ\\\\u00f\\\\u00d\\s]";
	private final String soloNumeros = "^[0-9]*[0-9][0-9]*$";
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	    
    /*============================== ÁREA DE CLICK DE BOTONES ==============================*/    
	@FXML
	void clickGuardar(MouseEvent event) {
		guardarDatos();
	}

	@FXML
	void clickLimpiar(MouseEvent event) {
		Alert alert = new Alert(AlertType.WARNING, "¿Está seguro de limpiar los datos en pantalla?", ButtonType.YES,
				ButtonType.NO);
		alert.setTitle("Limpiar datos");
		alert.setHeaderText(null);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.YES) {
			limpiarCampos();
		}
	}    	
	    
	@FXML
	void clickRadioHombre(MouseEvent event) {
		transicionLabel(labelHombre, -14);
		transicionLabel(labelMujer, 0);
		transicionRectangle(rectangleSeleccionSexo, -140);
		sexoFinal = "Masculino";
	}

	@FXML
	void clickRadioMujer(MouseEvent event) {
		transicionLabel(labelHombre, 0);
		transicionLabel(labelMujer, 8);
		transicionRectangle(rectangleSeleccionSexo, 0);
		sexoFinal = "Femenino";
	}
	    
    @FXML
    void clickRegresar(MouseEvent event) throws IOException {
    	// Cargamos la escena    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/registrarActualizarCliente/registrarActualizarCliente.fxml"));
    	Parent root = (Parent)loader.load();
    	Scene escena = labelRegresarFijo.getScene();
    	
    	// Obtenemos y añadimos la escena:    	
    	//root.translateYProperty().set(-escena.getHeight());    	
    	root.translateYProperty().set( labelTitulo.getText().equals("ACTUALIZACIÓN DE DATOS") ? -escena.getHeight() : escena.getHeight() );   	
    	miAnchorPane.getChildren().add(root);
    	
    	// Mostramos la escena con una línea de tiempo:
    	Timeline timeLine = new Timeline();
    	KeyValue kv;    	
    	kv = new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);    	  	
    	KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
    	timeLine.getKeyFrames().add(kf);
    	timeLine.play();
    }

    /*============================== EFECTO HOVER ==============================*/
    @FXML
    void hoverDia(MouseEvent event) {
    	lineDia.setStroke(Color.web("#77a6ce"));
    }
    
    @FXML
    void hoverMes(MouseEvent event) {
    	lineMes.setStroke(Color.web("#77a6ce"));
    }
    
    @FXML
    void hoverAnio(MouseEvent event) {
    	lineAnio.setStroke(Color.web("#77a6ce"));
    }
    
    @FXML
    void hoverGuardar(MouseEvent event) {    	
    	colorFondoBoton(rectangleGuardar, "#f800f8", 200);
    }

    @FXML
    void hoverLimpiar(MouseEvent event) {
    	colorFondoBoton(rectangleLimpiar, "#f800f8", 200);
    }
    
    @FXML
    void hoverRadioHombre(MouseEvent event) {
    	colorFondoBoton(rectangleHombre, "Radio", 200);
    }

    @FXML
    void hoverRadioMujer(MouseEvent event) {
    	colorFondoBoton(rectangleMujer, "Radio", 200);
    }
    
    @FXML
    void hoverRegresar(MouseEvent event) {
    	if(labelTitulo.getText().equals("ACTUALIZACIÓN DE DATOS")) {
    		animarLine(lineInfRegresar, +10);    	
        	animarLabel(labelRegresar, +10);
        	return;
    	}
    	animarLine(lineInfRegresar, -15);    	
    	animarLabel(labelRegresar, -15);
    }

    /*============================== EFECTO NO HOVER ==============================*/
    @FXML
    void noHoverDia(MouseEvent event) {
    	lineDia.setStroke(Color.WHITE);   
    }
    
    @FXML
    void noHoverMes(MouseEvent event) {
    	lineMes.setStroke(Color.WHITE);   
    }
    
    @FXML
    void noHoverAnio(MouseEvent event) {
    	lineAnio.setStroke(Color.WHITE);   
    }
    
    @FXML
    void noHoverGuardar(MouseEvent event) {
    	colorFondoBoton(rectangleGuardar, "#FFF", 200);
    }

    @FXML
    void noHoverLimpiar(MouseEvent event) {
    	colorFondoBoton(rectangleLimpiar, "#FFF", 200);
    }
    
    @FXML
    void noHoverRadioHombre(MouseEvent event) {
    	colorFondoBoton(rectangleHombre, "#FFF", 200);
    }

    @FXML
    void noHoverRadioMujer(MouseEvent event) {
    	colorFondoBoton(rectangleMujer, "#FFF", 200);
    }
    
    @FXML
    void noHoverRegresar(MouseEvent event) {    	
    	animarLine(lineInfRegresar, 0);    	
    	animarLabel(labelRegresar, 0);
    }

    /*============================== ELEMENTOS SELECCIONADOS ==============================*/
    @FXML
    void pressLabelCorreo(MouseEvent event) {
    	requestedTextFields(textCorreo, labelCorreo, lineCorreo);
    }
    
    @FXML
    void pressLabelApellidos(MouseEvent event) {
    	requestedTextFields(textApellidos, labelApellidos, lineApellidos);    	
    }
    
    @FXML
    void pressLabelDireccion(MouseEvent event) {
    	requestedTextFields(textDireccion, labelDireccion, lineDireccion);
    }

    @FXML
    void pressLabelEdad(MouseEvent event) {
    	// requestedTextFields(textEdad, labelEdad, lineEdad);
    }

    @FXML
    void pressLabelNit(MouseEvent event) {
    	requestedTextFields(textNit, labelNit, lineNit);
    }
    
    @FXML
    void pressLabelNombre(MouseEvent event) {  
    	requestedTextFields(textNombre, labelNombre, lineNombre);
    }    
    
    @FXML
    void pressLabelDPI(MouseEvent event) {
    	requestedTextFields(textDPI, labelDPI, lineDPI);
    }
    
    @FXML
    void pressLabelTelefono(MouseEvent event) {
    	requestedTextFields(textTelefono, labelTelefono, lineTelefono);
    }
    
    @FXML
    void pressTextApellidos(MouseEvent event) {
    	requestedTextFields(textApellidos, labelApellidos, lineApellidos);
    }

    @FXML
    void pressTextCorreo(MouseEvent event) {
    	requestedTextFields(textCorreo, labelCorreo, lineCorreo);
    }

    @FXML
    void pressTextDPI(MouseEvent event) {
    	requestedTextFields(textDPI, labelDPI, lineDPI);
    }

    @FXML
    void pressTextDireccion(MouseEvent event) {
    	requestedTextFields(textDireccion, labelDireccion, lineDireccion);
    }

    @FXML
    void pressTextEdad(MouseEvent event) {
    	// requestedTextFields(textEdad, labelEdad, lineEdad);
    }

    @FXML
    void pressTextNit(MouseEvent event) {
    	requestedTextFields(textNit, labelNit, lineNit);
    }

    @FXML
    void pressTextNombre(MouseEvent event) {
    	requestedTextFields(textNombre, labelNombre, lineNombre);
    }

    @FXML
    void pressTextTel(MouseEvent event) {
    	requestedTextFields(textTelefono, labelTelefono, lineTelefono);
    }
    
    /*============================== TECLA ENTER EN LOS TEXTFIELD ==============================*/
    @FXML
    void enterNombre(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }
    
    @FXML
    void enterApellidos(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterCorreo(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterDPI(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterDireccion(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterNit(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterTelefono(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }
   
    /*============================== ELEMENTOS TABULADOS O CON FOCUS ==============================*/
    @FXML
    void textTypedNombre(KeyEvent event) {
    	requestedTextFields(textNombre, labelNombre, lineNombre);    	
    	validacionTextFielTexto(event, textNombre, soloTexto, 75);	
    }
    
    @FXML
    void textTypedApellidos(KeyEvent event) {
    	requestedTextFields(textApellidos, labelApellidos, lineApellidos);
    	validacionTextFielTexto(event, textApellidos, soloTexto, 75);	
    }

    @FXML
    void textTypedCorreo(KeyEvent event) {
    	requestedTextFields(textCorreo, labelCorreo, lineCorreo);
    	validarLimite(event, textCorreo, 75);	
    }

    @FXML
    void textTypedDPI(KeyEvent event) {
    	requestedTextFields(textDPI, labelDPI, lineDPI);
    	validacionTextFielTexto(event, textDPI, soloNumeros, 13);
    }

    @FXML
    void textTypedDireccion(KeyEvent event) {
    	requestedTextFields(textDireccion, labelDireccion, lineDireccion);
    	validarLimite(event, textDireccion, 75);	
    }

    @FXML
    void textTypedEdad(KeyEvent event) {
    	// requestedTextFields(textEdad, labelEdad, lineEdad);
    }

    @FXML
    void textTypedNit(KeyEvent event) {
    	requestedTextFields(textNit, labelNit, lineNit);    	
    	validacionTextFielTexto(event, textNit, soloNumeros, 12);	
    }

    @FXML
    void textTypedTel(KeyEvent event) {
    	requestedTextFields(textTelefono, labelTelefono, lineTelefono);
    	validacionTextFielTexto(event, textTelefono, soloNumeros, 8);
    }
    
    /*============================== MÉTODOS ==============================*/
    /**
     * Anima las lineas en el eje Y.
     * @param pLine Línea quien sufrirá la animación.
     * @param pos Nueva posición tras terminada la animación.
     */
    private void animarLine(Line pLine, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLine);
    	transicion.setToY(pos);
    	transicion.play();
    }
    
    /**
     * Anima los Labels en el eje Y.
     * @param pLabel Label quien sufrirá la animación.
     * @param pos Nueva posición del label tras terminada la animación.
     */
    private void animarLabel(Label pLabel, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLabel);
    	transicion.setToY(pos);
    	transicion.play();
    }
    
    /**
     * Anima verticalmente los label de los input.
     * @param pText Realiza un requesteFocus una vez seleccionada el label.
     * @param pLabel Reciben un requesteFocus para que pueda escribir el usuario.
     */
    private void requestedTextFields(TextField pText, Label pLabel, Line pLine) {    	
    	// Se evalúan al TextField anterior para ver si contiene texto o no y devolver el label.
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
    
    private void animarEdad(TextField pText, Label pLabel, Line pLine) {
    	// Se evalúan al TextField anterior para ver si contiene texto o no y devolver el label.
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
    	pLine.setStroke(Color.web("#77a6ce")); 
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
     * Ingresa datos de la fecha a un comboBox:
     */
    public void ingresarDatosComboBox() {
    	comboDiaNac.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
    	comboMesNac.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    	comboAnioNac.getItems().addAll("2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900", "1899", "1898", "1897", "1896", "1895", "1894", "1893", "1892", "1891", "1890");	
    	comboDiaNac.setValue("Día");
    	comboMesNac.setValue("Mes");
    	comboAnioNac.setValue("Año");
    }    
    
    /**
     * Cálcula la edad a partir de la fecha ingresada en los ComboBoxes y la hora del sistema.
     * @param fechNac Día de la fecha de nacimiento.
     * @param mesNac Mes de la fecha de nacimiento.
     * @param anioNac Año de la fecha de nacimiento.
     */
    public void calcularEdad( int fechNac, int mesNac, int anioNac) {
    	
    	if(fechNac != (int)fechNac || mesNac != (int)mesNac || anioNac != (int)anioNac) {
    		String mensaje = "Día, mes o año inválidos.";
        	Alert alert = new Alert(AlertType.ERROR, mensaje, ButtonType.OK);
        	alert.setTitle("Error");
        	alert.showAndWait(); 
        	return;
    	}
    	
    	// Variables locales:    			
    	int respFech , respMes;    		    
    	Calendar c = Calendar.getInstance();    	
    	int anioAct = c.get(Calendar.YEAR),  // Año actual.    	   	
    	mesAct  = c.get(Calendar.MONTH) + 1, // Mes actual.    		    
   		fechAct = c.get(Calendar.DATE);  // Día acutal.
    	String unirD, unirM, unirTodo;
    	
    	if ( fechAct < fechNac  )    		  
    		{   //En caso de ser menor la fecha actual que el nacimiento    		
    			fechAct = fechAct + 30; // Se le suma los 30 días (1 mes) a la fecha actual    		    
    			mesAct = mesAct - 1; // Se le resta un mes (30 días) al mes actual    		    
    			respFech =  fechAct - fechNac; //Se le resta fecha nacimiento al actual    		    
    		}    		
    	else //En caso de ser mayor la fecha actual que el nacimiento    	
    		respFech =  fechAct - fechNac;  //Se le resta fecha nacimiento al actual   	
    	
    	if( mesAct < mesNac )    	
    	{   //En caso de ser menor el mes actual que el nacimiento    	
    		mesAct = mesAct + 12; // Se le suma los 12 meses (1 año) al mes actual    		
    		anioAct = anioAct - 1 ; // Se le resta 1 año ( 12 meses) al año actual    		
    		respMes = mesAct - mesNac; //Se le resta año nacimiento al actual    		
    	}
    	
    	else //En caso de ser mayor el mes actual que el nacimiento    	
    		respMes = mesAct - mesNac; //Se le resta año nacimiento al actual    		   	
    	
    	// Concatenación para poder ser guardada en las tablas de sql en un solo campo:    	
    	unirTodo = String.valueOf((anioAct-anioNac));    	
    	unirM = String.valueOf(respMes);    	
    	unirD = String.valueOf(respFech);
    	    	
    	// Armamos los enunciados plurares o singulares:    	
    	String aniosCon, mesesY, dias;		    	
    	aniosCon = Integer.parseInt((unirTodo)) == 1 ? " año con " : " años con ";    	
    	mesesY   = Integer.parseInt((unirM)) == 1 ? " mes y " : " meses y ";    	
    	dias     = Integer.parseInt((unirD)) == 1 ? " día." : " días.";    	
    	    	
    	// Obtenemos finalmente la edad de la persona:    	
    	unirTodo = unirTodo + aniosCon + unirM + mesesY + unirD + dias;		
    	
    	// Retornamos la edad:   
    	animarEdad(textEdad, labelEdad, lineEdad);
    	textEdad.setText(unirTodo);
    }
    
    
    /**
     * Agrega un listener a los ComboBox de día, mes y año para cálcular la edad.
     * @param pCombo Recibe un listener cuando cambia su valor.
     */
    public void listenerComboBox(ComboBox<String> pCombo) {    	
    	// En caso de problema, cambiar este método por 3 métodos para día, mes y anio: 
    	pCombo.setEditable(true);
    	pCombo.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                //System.out.println(" Text Changed to  " + newValue + ")\n");
            	//System.out.println("Text changed");
            	// System.out.println(comboDiaNac.getValue());
            	
            	if(!comboDiaNac.getValue().isEmpty() && !comboMesNac.getValue().isEmpty() && 
            		!comboAnioNac.getValue().isEmpty()) {
            		try {            		
	            		calcularEdad(Integer.parseInt(String.valueOf(comboDiaNac.getValue())),
	        					Integer.parseInt(String.valueOf(comboMesNac.getValue())),
	        					Integer.parseInt(String.valueOf(comboAnioNac.getValue())));
	            		}
            		catch(Exception e) {
            			// Al principio dará error, pero si selecicona números ya no se mostrará:
            			// Esto es porque no se utiliza un placeHolder para los comboBox
            			// Y por lo tanto está convirtiendo el valor que tiene, el cual es un String
            			System.out.println("Error de conversión: " + e.getMessage());
            		}
            	}
            }
        });
    }
    
    /**
     * Cambia de posición el label de los RadioButtons
     * @param pLabel Label Hombre o Mujer.
     * @param pos Nueva posición transcurrida la transición.
     */
    public void transicionLabel(Label pLabel, int pos) {
    	pLabel.setLayoutX(pLabel.getLayoutX());
	    TranslateTransition mover3 = new TranslateTransition(Duration.seconds(0.5), pLabel);
	    mover3.setToX(pos);	    
	    mover3.play();
    }
    
    /**
     * Posiciona un Rectangle en la opción seleccionada del sexo.
     * @param pRectangle Se posiciona encima de la opción seleccionada.
     * @param pos Nueva posición transucurrida la transición.
     */
    public void transicionRectangle(Rectangle pRectangle, int pos) {
    	// Mostramos el Rectangle:
    	//rectangleSeleccionSexo.setOpacity(1);
    	pRectangle.setOpacity(1);

    	// Animamos:
    	pRectangle.setLayoutX(pRectangle.getLayoutX());
	    TranslateTransition mover = new TranslateTransition(Duration.seconds(0.5), pRectangle);
	    mover.setToX(pos);	    
	    mover.play();
    	
    	// Muestra el color del rectángulo una vez seleccionada una opción: 
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(pRectangle.fillProperty(),Color.web("#f800f8")); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    	
    }
    
    public void validacionTextFielTexto(KeyEvent event, TextField pText, String expRegular, int max) {
    	if(pText.getText().length() >= max || !event.getCharacter().matches(expRegular)) {
			event.consume();
			Toolkit.getDefaultToolkit().beep(); // Sonido de advertencia
		}	
    }
    
    public void validarCorreo(KeyEvent event, TextField pText, String expRegular, int max) {
    	if(pText.getText().length() >= max || event.getCharacter().matches(expRegular)) {
			event.consume();
			Toolkit.getDefaultToolkit().beep(); // Sonido de advertencia
		}	
    }
    
    public void validarLimite(KeyEvent event, TextField pText, int max) {
    	if(pText.getText().length() >= max) {
			event.consume();
			Toolkit.getDefaultToolkit().beep(); // Sonido de advertencia
		}	
    }
    
    /**
	 * Le da un formato a la fecha de tipo dd/MM/yyyy y verfica que sea válida.
	 * @param dia
	 * @param mes
	 * @param anio
	 * @return Regresa la fecha de tipo LocalDate.
	 */
	public String formato_fecha(String dia, String mes, String anio) {		
		// Convertimos los datos a int:
		int year = Integer.parseInt(anio);                // año
	    int month = Integer.parseInt(mes);               // mes [1,...,12]
	    int dayOfMonth = Integer.parseInt(dia);         // día [1,...,31]

	    if (year < 1000) {
	    	/*
	    	String errorMensaje = "Por favor, ingrese una fecha de nacimiento válida.";     		
        	Alert alert = new Alert(AlertType.ERROR, errorMensaje, ButtonType.OK);
        	alert.setHeaderText(null);
           	alert.setTitle("Error");    	    
           	alert.showAndWait(); 
           	*/
           	throw new IllegalArgumentException("Año inválido.");
	    } 
	    
	    // Obtenemos dato:
	    LocalDate today = LocalDate.of(year, month, dayOfMonth);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    System.out.println(formatter.format(today)); // 01/01/2016	
	    
	    return String.valueOf(formatter.format(today));
	}
	
	/**
	 * Limpia todos los campos para ingresar nuevos datos.
	 */
	public void limpiarCampos() {
		// TextFields
		textNombre.setText("");
		textApellidos.setText("");
		textDPI.setText("");
		textNit.setText("");
		textEdad.setText("");
		textTelefono.setText("");
		textDireccion.setText("");
		textCorreo.setText("");
		
		// ComboBoxes
		comboDiaNac.setValue("Día");
		comboMesNac.setValue("Mes");
		comboAnioNac.setValue("Año");
		
		// "RadioButton"
		rectangleSeleccionSexo.setOpacity(0);
		transicionLabel(labelHombre, 0);
        transicionLabel(labelMujer, 8);   
		
		// Posiciones de los Labels    	 	
    	requestedTextFields(textCorreo, labelCorreo, lineCorreo);
    	requestedTextFields(textApellidos, labelApellidos, lineApellidos);
    	requestedTextFields(textDireccion, labelDireccion, lineDireccion);
    	requestedTextFields(textEdad, labelEdad, lineEdad);
    	requestedTextFields(textNit, labelNit, lineNit);
    	requestedTextFields(textDPI, labelDPI, lineDPI);
    	requestedTextFields(textTelefono, labelTelefono, lineTelefono);
    	requestedTextFields(textNombre, labelNombre, lineNombre);    	
	}
        
    
    /*============================== GUARDAR DATOS ==============================*/
	/**
	 * Ingresa los datos que se encuentran en los campos a un DtoPersona.
	 * @param miPersona Setea los datos.
	 * @return Retorna el conjunto de datos ingresados.
	 */
    public DtoPersona setDatosDtoPersona(DtoPersona miPersona) {
    	miPersona.setNombre(textNombre.getText());
    	miPersona.setApellidos(textApellidos.getText());
    	miPersona.setDPI(textDPI.getText());
    	miPersona.setNIT(textNit.getText());
    	try {    	
	    	miPersona.setFecha_de_nacimiento(
	    			formato_fecha(String.valueOf(comboDiaNac.getValue()), 
	    						  String.valueOf(comboMesNac.getValue()),
	    						  String.valueOf(comboAnioNac.getValue())));
    	}catch(Exception e) {
    		String errorMensaje = "Por favor, ingrese una fecha de nacimiento válida.";     		
        	Alert alert = new Alert(AlertType.ERROR, errorMensaje, ButtonType.OK);
        	alert.setHeaderText(null);
           	alert.setTitle("Error");    	    
           	alert.showAndWait();
           	System.out.println(e.getMessage());
           	return null;
    	}
    	miPersona.setEdad(textEdad.getText());
    	miPersona.setSexo(sexoFinal);
    	miPersona.setDireccion(textDireccion.getText());
    	miPersona.setTelefono(Integer.parseInt(textTelefono.getText()));
    	miPersona.setCorreo(textCorreo.getText());    	
    	return miPersona;
    }
    
    /**
     * Un campo dentro del formulario no se encuentra contiene algún error.
     * @param pMensaje Mensaje del error.
     * @param pLabel Le setea un color y anima su Focus.
     * @param pText Añade un Focus al TextField con el error.
     * @param pLine Añade un Focus al Line cambiando su color.
     * @param pColor Color a añadir al Label.
     * @return
     */
    public String aplicarError(String pMensaje, Label pLabel, TextField pText, Line pLine, Color pColor) {
    	pLabel.setTextFill(pColor);
    	requestedTextFields(pText,pLabel, pLine);
    	return pMensaje;
    }
    
    /**
     * Se revisa campo por campo que datos se encuentra vacío.
     * @return Un mensaje mayor a 27 caractéres significa que hay un error.
     */
    public String datosLlenados() {
    	
    	// Retornamos el mensaje de error:
    	String mensaje = "Por favor, llene el campo: ";
    	
    	// Reestablecemos el color:
    	labelNombre.setTextFill(Color.WHITE);
    	labelApellidos.setTextFill(Color.WHITE);
    	labelDPI.setTextFill(Color.WHITE);
    	labelNit.setTextFill(Color.WHITE);
    	labelEdad.setTextFill(Color.WHITE);
    	labelDireccion.setTextFill(Color.WHITE);
    	labelTelefono.setTextFill(Color.WHITE);
    	labelCorreo.setTextFill(Color.WHITE);
    	circleHombre.setStroke(Color.WHITE);
		circleMujer.setStroke(Color.WHITE);
		labelHombre.setTextFill(Color.WHITE);
		labelMujer.setTextFill(Color.WHITE);
    	
    	// Campo Nombre:
    	if(textNombre.getText().isEmpty()) {
    		return mensaje += aplicarError("Nombre.", labelNombre, textNombre, lineNombre, Color.RED);	    		
    	}
    	
    	// Apellidos:
    	if(textApellidos.getText().isEmpty()) {
    		return mensaje += aplicarError("Apellidos.", labelApellidos, textApellidos, lineApellidos, Color.RED);    		
    	
    	}
    	
    	// DPI:
    	if(textDPI.getText().isEmpty()) {
    		return mensaje += aplicarError("DPI.", labelDPI, textDPI, lineDPI, Color.RED);	    		
    	}
    	
    	// Nit:
    	if(textNit.getText().isEmpty()) {
    		return mensaje += aplicarError("Nit.", labelNit, textNit, lineNit, Color.RED);
    	}
    	
    	// Edad:
    	if(textEdad.getText().isEmpty()) {    		
    		labelEdad.setTextFill(Color.RED);
    		return mensaje += "Edad seleccionando su fecha de nacimiento.";
    	}
    	
    	// Sexo:
    	if(sexoFinal.isEmpty()) {
    		circleHombre.setStroke(Color.RED);
    		circleMujer.setStroke(Color.RED);
    		labelHombre.setTextFill(Color.RED);
    		labelMujer.setTextFill(Color.RED);
    		return mensaje += "Sexo, seleccione una opción hombre o mujer.";
    	}
    	
    	// Dirección:
    	if(textDireccion.getText().isEmpty()) {
    		return mensaje += aplicarError("Direccion.", labelDireccion, textDireccion, lineDireccion, Color.RED);
    	}
    	
    	// Teléfono:
    	if(textTelefono.getText().isEmpty()) {
    		return mensaje += aplicarError("Teléfono.", labelTelefono, textTelefono, lineTelefono, Color.RED);
    	}
    	
    	// Correo
    	if(textCorreo.getText().isEmpty()) {
    		return mensaje += aplicarError("Correo electrónico.", 
    				labelCorreo, textCorreo, lineCorreo, Color.RED);
    	}    	
    	
    	// Retornamos el mensaje si no hay errores:
    	return mensaje;
    }
    
    /**
     * Valida el formato del correo ingresado por el usuario.
     * @param pCorreo Verifica que tenga el formato xxx@xx.xx
     * @return
     */
    public boolean validarCorreo(String pCorreo) {
    	Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(pCorreo);		
		return matcher.find();	
    }
    
    /**
     * Guarda los datos dentro de la base de datos luego de varias validaciones.
     */
    public void guardarDatos() {    	
    	
    	// Verificamos si se trata de una actualización o simplemente de un guardado
    	String mensajeAlerta = labelTitulo.getText().equals("ACTUALIZACIÓN DE DATOS" ) ? 
    			"¿Está seguro de querer actualizar los datos del ciente?" : 
    			"¿Está seguro de guardar los datos del cliente?";
    	
    	// Antes de guardar, preguntamos al usuario si está seguro:
    	Alert alert = 
    	        new Alert(AlertType.WARNING, 
    	            mensajeAlerta,
    	             ButtonType.YES,
    	             ButtonType.NO);
    	alert.setTitle("Guardar datos");
    	alert.setHeaderText(null);
    	Optional<ButtonType> resultado = alert.showAndWait();

    	if (resultado.get() != ButtonType.YES) {
    		return;
    	}
    	
    	// Revisaremos que cada campo haya sido llenado:
    	String errorMensajeCampo;
    	if((errorMensajeCampo = datosLlenados()).length() > 27) {
    		//String mensaje = "Por favor, llene todos los datos que se le solicita.";
        	alert = new Alert(AlertType.ERROR, errorMensajeCampo, ButtonType.OK);
        	alert.setTitle("Error");
        	alert.setHeaderText(null);
        	alert.showAndWait(); 
        	return;
    	}
    	
    	// Validamos el formato del correo:
    	if(!validarCorreo(textCorreo.getText())){
    		errorMensajeCampo = "Formato inválido. Escriba el correo con el siguiente formato: ejemplo@xx.xx";    		
    		errorMensajeCampo = aplicarError(errorMensajeCampo, labelCorreo, textCorreo, lineCorreo, Color.RED);	    		
        	alert = new Alert(AlertType.ERROR, errorMensajeCampo, ButtonType.OK);
           	alert.setTitle("Error");
           	alert.setHeaderText(null);
           	alert.showAndWait();    		
           	return;    		
    	}
    	labelCorreo.setTextFill(Color.WHITE);
    	
    	// Guardamos los datos en un DTO:
    	DtoPersona miPersona = new DtoPersona();
    		
    	// Seteamos los datos dentros
    	miPersona = setDatosDtoPersona(miPersona);	
    	if(miPersona == null) {
    		return;
    	} 
    	
    	// Si se trata de una actualización, ejecutamos:
    	if(labelTitulo.getText().equals("ACTUALIZACIÓN DE DATOS")) {
    		ActualizarCliente actualizar = new ActualizarCliente();
    		if(actualizar.yaExisteDPI(miPersona, dpiAnterior)) {
    			errorMensajeCampo = "El DPI ingresado ya existe dentro de la base de datos.";     		
            	alert = new Alert(AlertType.ERROR, errorMensajeCampo, ButtonType.OK);
               	alert.setTitle("Error");  
               	alert.setHeaderText(null);
               	alert.showAndWait();    		
               	return;
    		}
    		actualizar.actualizarDatos(miPersona, dpiAnterior);
    		
    		// Actualizamos el campo DPI en la tabla propiedades si es que es diferente:
    		if(!dpiAnterior.equals(miPersona.getDPI())) {
    			ActualizarPropiedad actualizarDpi = new ActualizarPropiedad();
        		actualizarDpi.actualizarDpiCliente(miPersona.getDPI(), dpiAnterior);
        		
        		ActualizarControlDePagos controlDePagos = new ActualizarControlDePagos();
    			controlDePagos.actualizarControlDePagosDPI(miPersona.getDPI(), dpiAnterior);
    		}
    		
    		return;
    	}
    	
    	// Si se trata de un registro, ejecutamos:
    	//Instanciamos un objeto para acceder a las acciones de registro:
		RegistrarCliente registrar = new RegistrarCliente();
		registrar.setMiPersona(miPersona);
	
		// 	Verificamos que no exista el cui
		if(registrar.yaExisteDPI()) {
			errorMensajeCampo = "El DPI ingresado ya existe dentro de la base de datos.";     		
        	alert = new Alert(AlertType.ERROR, errorMensajeCampo, ButtonType.OK);
           	alert.setTitle("Error");  
           	alert.setHeaderText(null);
           	alert.showAndWait();    		
           	return;
		}
	
		// Finalmente registramos los datos en la base de datos:		
		registrar.registrar();
		limpiarCampos();
		return;
    }
    
    /*============================== INICIALIZADOR ==============================*/
    @FXML
    void initialize() {  
    	
    	// Ingresa los datos de una fecha a los comboBoxes de día, mes y año.
    	ingresarDatosComboBox();
    	
    	// Agrega un listener a los comboBoxes de día, mes y año para cálcular la edad.
    	listenerComboBox(comboDiaNac);
    	listenerComboBox(comboMesNac);
    	listenerComboBox(comboAnioNac);    
    }    
    
    /*============================== PANTALLA DE ACTUALIZACIÓN ==============================*/
    /**
     * Antes de mostrar la pantalla, se le pide al usuario que ingrese un DPI para consultar al 
     * cliente.
     * @return Falso si no encuentra el DPI o cancela la búsqueda. True si encuentra al cliente.
     */
    public boolean consultarDPIActualizacion() {
    	TextInputDialog dialog = new TextInputDialog("DPI");
    	dialog.setTitle("Actualizar");
    	dialog.setHeaderText(null);
    	dialog.setContentText("Ingrese el No. de DPI del cliente:");
    	
    	// Traditional way to get the response value.
    	Optional<String> res = dialog.showAndWait();
    	    	
    	// Si no cancela, se realiza la consulta:
    	if (res.isPresent()){
    		System.out.println("Prueba: " + res.get());
    		return obtenerDatosActualizar(res.get());    		
    	}
    	
    	// Si llega hasta este punto, es porque se presionó cancelar.
    	return res.isPresent();

    	// lambda expression:
    	//res.ifPresent(name -> System.out.println("Your name: " + name));
    }
    
    /**
     * Consulta la base de datos según el DPI ingresado.
     * @param pDpi Parámetro para hacer la consulta.
     */
    public boolean obtenerDatosActualizar(String pDpi) {
    	// Consultamos la base de datos con el DPI que ingresó el usuario:
		ConsultarCliente consultar = new ConsultarCliente();		
		
		// Instanciamos una lista de dtoPersona:
		List<DtoPersona> miActualizacion = new ArrayList<>();
		
		// Obtenemos los datos. Si está vacía, quiere decir que no encontró coincidencias con 
		// DPI ingresado.
		miActualizacion = consultar.obtenerDatos(pDpi);
		if(miActualizacion.isEmpty()) {			
			return false;
		};
		
		// Seteamos los datos en los TextFields
		labelTitulo.setText("ACTUALIZACIÓN DE DATOS");
		labelRegresarFijo.setLayoutY(0);
		labelRegresar.setLayoutY(0);
		lineInfRegresar.setLayoutY(0);
		setearDatosActualizacion(miActualizacion);	
		dpiAnterior = textDPI.getText();
		return true;
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
	 * Ingresa los datos consultados a los TextFields
	 * @param miActualizacion Lista de datos a ingresar.
	 */
    public void setearDatosActualizacion(List<DtoPersona> miActualizacion) {
    	// TextFields    			
    	textNombre.setText(miActualizacion.get(0).getNombre());    	
    	textApellidos.setText(miActualizacion.get(0).getApellidos());    	
    	textDPI.setText(miActualizacion.get(0).getDPI());    	
    	textNit.setText(miActualizacion.get(0).getNIT());    	
    	textEdad.setText(miActualizacion.get(0).getEdad());    	
    	textTelefono.setText(String.valueOf(miActualizacion.get(0).getTelefono()));    	
    	textDireccion.setText(miActualizacion.get(0).getDireccion());    	
    	textCorreo.setText(miActualizacion.get(0).getCorreo());    	
    	
    	// ComboBoxes    
    	// Fechas:
    			
    	// Declaramos el iterador y recorremos el ArrayList
    	List<Integer> nacimiento = extraerNumeros(miActualizacion.get(0).getFecha_de_nacimiento());    	
    	Iterator<Integer> miIterator = nacimiento.iterator();
    	
    	int contador = 0;
    	
    	while(miIterator.hasNext()) {	    	
    		Integer elemento = miIterator.next();    		
    		if(contador == 0) {    		
    			comboDiaNac.setValue(String.valueOf(elemento));    			
    		}    		
    		else if(contador == 1) {
    			comboMesNac.setValue(String.valueOf(elemento)); 
    			//comboMes.setSelectedIndex(elemento-1);    			
    		}else if(contador == 2) {
    			comboAnioNac.setValue(String.valueOf(elemento)); 
    			//comboAnio.setSelectedIndex(elemento-1);    			
    		}    		
    		contador++;    		
    	}
    	
    	// "RadioButton"
    	rectangleSeleccionSexo.setOpacity(1);
    	if(miActualizacion.get(0).getSexo().equals("Masculino")) {
    		transicionLabel(labelMujer, 0);
	    	transicionRectangle(rectangleSeleccionSexo, -140);
	    	sexoFinal = "Masculino";
    	}else {
    		transicionLabel(labelMujer, 8);
	    	transicionRectangle(rectangleSeleccionSexo, 0);
	    	sexoFinal = "Femenino";
    	} 
    	
    	// Posiciones de los Labels    	
    	requestedTextFields(textCorreo, labelCorreo, lineCorreo);    	
    	requestedTextFields(textApellidos, labelApellidos, lineApellidos);    	
    	requestedTextFields(textDireccion, labelDireccion, lineDireccion);    	
    	requestedTextFields(textEdad, labelEdad, lineEdad);    	
    	requestedTextFields(textNit, labelNit, lineNit);    	
    	requestedTextFields(textDPI, labelDPI, lineDPI);    	
    	requestedTextFields(textTelefono, labelTelefono, lineTelefono);    	
    	requestedTextFields(textNombre, labelNombre, lineNombre);  
    }
}