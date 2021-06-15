package registrarActualizarPropiedad.pantallaRegistroActualizacionPropiedad;

import java.awt.Toolkit;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DTOs.DtoPersona;
import DTOs.DtoPropiedad;
import accionesBD.ActualizarCliente;
import accionesBD.ActualizarControlDePagos;
import accionesBD.ActualizarPropiedad;
import accionesBD.ConsultarCliente;
import accionesBD.ConsultarPropiedad;
import accionesBD.RegistrarCliente;
import accionesBD.RegistrarControlDePagos;
import accionesBD.RegistrarPropiedad;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ControladorPantallaRegistroActualizacionPropiedad {
	
	@FXML
    private AnchorPane miAnchorPane;

	@FXML
    private Label labelTitulo;

    @FXML
    private TextField textCodigoPropiedad;

    @FXML
    private Line lineCodigoPropiedad;

    @FXML
    private Label labelCodigoPropiedad;

    @FXML
    private TextField textNoDeFolio;

    @FXML
    private Line lineNoDeFolio;

    @FXML
    private Label labelNoDeFolio;

    @FXML
    private TextField textArea;

    @FXML
    private Line lineArea;

    @FXML
    private Label labelArea;

    @FXML
    private TextField textNoDeEscritura;

    @FXML
    private Line lineNoDeEscritura;

    @FXML
    private Label labelNoDeEscritura;

    @FXML
    private TextField textValorPropiedad;

    @FXML
    private Line lineValorPropiedad;

    @FXML
    private Label labelValorPropiedad;

    @FXML
    private TextField textInteres;

    @FXML
    private Line lineInteres;

    @FXML
    private Label labelIntres;

    @FXML
    private TextField textNoDeFinca;

    @FXML
    private Line lineNoDeFinca;

    @FXML
    private Label labelNoDeFinca;

    @FXML
    private TextField textNoDeLibro;

    @FXML
    private Line lineNoDeLibro;

    @FXML
    private Label labelNoDeLibro;

    @FXML
    private TextField textNoDeCatastro;

    @FXML
    private Line lineNoDeCatastro;

    @FXML
    private Label labelNoDeCatastro;

    @FXML
    private TextField textCuotas;

    @FXML
    private Line lineCuotas;

    @FXML
    private Label labelCuotas;

    @FXML
    private TextField textDpi;

    @FXML
    private Line lineDpi;

    @FXML
    private Label labelDpi;

    @FXML
    private ComboBox<String> comboDia;

    @FXML
    private ComboBox<String> comboMes;

    @FXML
    private ComboBox<String> comboAnio;

    @FXML
    private Label labelFechaEscritura;

    @FXML
    private Line lineDia;

    @FXML
    private Line lineMes;

    @FXML
    private Line lineAnio;

    @FXML
    private Rectangle rectangleLimpiar;

    @FXML
    private Label btnLimpiar;

    @FXML
    private Rectangle rectangleGuardar;

    @FXML
    private Label btnGuardar;

    @FXML
    private Line lineInfRegresar;

    @FXML
    private Label labelRegresar;
    
    // Variables
    private Line lineNoFocus = new Line();
	private TextField sinDatos = new TextField();
	private Label sinDatosLabel = new Label();
	private String codigoPropiedadAnterior;
	
	private String codigoPropiedadAntControlDePagos;
	private String interesAntPagos;
	
	// Expresiones Regulares:
	private final String soloNumeros = "^[0-9]*[0-9][0-9]*$";

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
    void clickRegresar(MouseEvent event) throws IOException {
    	// Cargamos la escena    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/registrarActualizarPropiedad/propiedadFXML.fxml"));
    	Parent root = (Parent)loader.load();
    	Scene escena = labelRegresar.getScene();
    	
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

    /*============================== TECLA ENTER EN LOS TEXTFIELD ==============================*/
    @FXML
    void enterArea(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterCodigoPropiedad(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterCuotas(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterDpi(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterInteres(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterNoDeCatastro(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterNoDeEscritura(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterNoDeFinca(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterNoDeFolio(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterNoDeLibro(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    @FXML
    void enterValorPropiedad(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		guardarDatos();
       }
    }

    /*============================== EFECTO HOVER ==============================*/
    @FXML
    void hoverAnio(MouseEvent event) {
    	lineAnio.setStroke(Color.web("#77a6ce"));
    }

    @FXML
    void hoverDia(MouseEvent event) {
    	lineDia.setStroke(Color.web("#77a6ce"));
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
    void hoverMes(MouseEvent event) {
    	lineMes.setStroke(Color.web("#77a6ce"));
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
    void noHoverAnio(MouseEvent event) {
    	lineAnio.setStroke(Color.WHITE);
    }

    @FXML
    void noHoverDia(MouseEvent event) {
    	lineDia.setStroke(Color.WHITE);
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
    void noHoverMes(MouseEvent event) {
    	lineMes.setStroke(Color.WHITE); 
    }

    @FXML
    void noHoverRegresar(MouseEvent event) {
    	animarLine(lineInfRegresar, 0);    	
    	animarLabel(labelRegresar, 0);
    }

    /*============================== ELEMENTOS SELECCIONADOS ==============================*/
    @FXML
    void pressLabelArea(MouseEvent event) {
    	requestedTextFields(textArea, labelArea, lineArea);
    }

    @FXML
    void pressLabelCodigoPropiedad(MouseEvent event) {
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    }

    @FXML
    void pressLabelCuotas(MouseEvent event) {
    	requestedTextFields(textCuotas, labelCuotas, lineCuotas);
    }

    @FXML
    void pressLabelDpi(MouseEvent event) {
    	if(!textDpi.isEditable()) {
    		editarDpiActualizacion();
    	}
    	requestedTextFields(textDpi, labelDpi, lineDpi);
    }

    @FXML
    void pressLabelFechaEscritura(MouseEvent event) {
    	// requestedTextFields(textFechaEscritura, labelFechaEscritura, lineFechaEscritura);
    }

    @FXML
    void pressLabelInteres(MouseEvent event) {
    	requestedTextFields(textInteres, labelIntres, lineInteres);
    }

    @FXML
    void pressLabelNoDeCatastro(MouseEvent event) {
    	requestedTextFields(textNoDeCatastro, labelNoDeCatastro, lineNoDeCatastro);
    }

    @FXML
    void pressLabelNoDeEscritura(MouseEvent event) {
    	requestedTextFields(textNoDeEscritura, labelNoDeEscritura, lineNoDeEscritura);
    }

    @FXML
    void pressLabelNoDeFinca(MouseEvent event) {
    	requestedTextFields(textNoDeFinca, labelNoDeFinca, lineNoDeFinca);
    }

    @FXML
    void pressLabelNoDeFolio(MouseEvent event) {
    	requestedTextFields(textNoDeFolio, labelNoDeFolio, lineNoDeFolio);
    }

    @FXML
    void pressLabelNoDeLibro(MouseEvent event) {
    	requestedTextFields(textNoDeLibro, labelNoDeLibro, lineNoDeLibro);
    }

    @FXML
    void pressLabelValorPropiedad(MouseEvent event) {
    	requestedTextFields(textValorPropiedad, labelValorPropiedad, lineValorPropiedad);
    }

    @FXML
    void pressTextArea(MouseEvent event) {
    	requestedTextFields(textArea, labelArea, lineArea);
    }

    @FXML
    void pressTextCodigoPropiedad(MouseEvent event) {
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    }

    @FXML
    void pressTextCuotas(MouseEvent event) {
    	requestedTextFields(textCuotas, labelCuotas, lineCuotas);
    }

    @FXML
    void pressTextDpi(MouseEvent event) {
    	if(!textDpi.isEditable()) {
    		editarDpiActualizacion();
    	}
    	requestedTextFields(textDpi, labelDpi, lineDpi);
    }

    @FXML
    void pressTextInteres(MouseEvent event) {
    	requestedTextFields(textInteres, labelIntres, lineInteres);
    }

    @FXML
    void pressTextNoDeCatastro(MouseEvent event) {
    	requestedTextFields(textNoDeCatastro, labelNoDeCatastro, lineNoDeCatastro);
    }

    @FXML
    void pressTextNoDeEscritura(MouseEvent event) {
    	requestedTextFields(textNoDeEscritura, labelNoDeEscritura, lineNoDeEscritura);
    }

    @FXML
    void pressTextNoDeFinca(MouseEvent event) {
    	requestedTextFields(textNoDeFinca, labelNoDeFinca, lineNoDeFinca);
    }

    @FXML
    void pressTextNoDeFolio(MouseEvent event) {
    	requestedTextFields(textNoDeFolio, labelNoDeFolio, lineNoDeFolio);
    }

    @FXML
    void pressTextNoDeLibro(MouseEvent event) {
    	requestedTextFields(textNoDeLibro, labelNoDeLibro, lineNoDeLibro);
    }

    @FXML
    void pressTextValorPropiedad(MouseEvent event) {
    	requestedTextFields(textValorPropiedad, labelValorPropiedad, lineValorPropiedad);
    }

    /*============================== ELEMENTOS TABULADOS O CON FOCUS ==============================*/
    @FXML
    void textTypedArea(KeyEvent event) {
    	requestedTextFields(textArea, labelArea, lineArea);    	
    	validacionTextFielTexto(event, textArea, soloNumeros, 10);
    }

    @FXML
    void textTypedCodigoPropiedad(KeyEvent event) {
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    	// validacionTextFielTexto(event, textCodigoPropiedad, soloTexto, 30); Sin validación
    }

    @FXML
    void textTypedCuotas(KeyEvent event) {
    	requestedTextFields(textCuotas, labelCuotas, lineCuotas);  	
    	validacionTextFielTexto(event, textCuotas, soloNumeros, 10);
    }

    @FXML
    void textTypedDpi(KeyEvent event) {
    	if(!textDpi.isEditable()) {
    		editarDpiActualizacion();
    	}
    	requestedTextFields(textDpi, labelDpi, lineDpi);  	
    	validacionTextFielTexto(event, textDpi, soloNumeros, 14);
    }

    @FXML
    void textTypedInters(KeyEvent event) {
    	requestedTextFields(textInteres, labelIntres, lineInteres);
    	validacionTextFielTexto(event, textInteres, soloNumeros, 3);
    }

    @FXML
    void textTypedNoDeCatastro(KeyEvent event) {
    	requestedTextFields(textNoDeCatastro, labelNoDeCatastro, lineNoDeCatastro);
    	validacionTextFielTexto(event, textNoDeCatastro, soloNumeros, 20);
    }

    @FXML
    void textTypedNoDeEscritura(KeyEvent event) {
    	requestedTextFields(textNoDeEscritura, labelNoDeEscritura, lineNoDeEscritura);
    	validacionTextFielTexto(event, textNoDeEscritura, soloNumeros, 20);
    }

    @FXML
    void textTypedNoDeFinca(KeyEvent event) {
    	requestedTextFields(textNoDeFinca, labelNoDeFinca, lineNoDeFinca);
    	validacionTextFielTexto(event, textNoDeFinca, soloNumeros, 20);
    }

    @FXML
    void textTypedNoDeFolio(KeyEvent event) {
    	requestedTextFields(textNoDeFolio, labelNoDeFolio, lineNoDeFolio);
    	validacionTextFielTexto(event, textNoDeFolio, soloNumeros, 20);
    }

    @FXML
    void textTypedNoDeLibro(KeyEvent event) {
    	requestedTextFields(textNoDeLibro, labelNoDeLibro, lineNoDeLibro);
    	validacionTextFielTexto(event, textNoDeLibro, soloNumeros, 20);
    }

    @FXML
    void textTypedValorPropiedad(KeyEvent event) {
    	requestedTextFields(textValorPropiedad, labelValorPropiedad, lineValorPropiedad);
    	validacionTextFielTexto(event, textValorPropiedad, soloNumeros, 20);
    }
    
    /*============================== MÉTODOS ==============================*/
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
    
    public void validacionTextFielTexto(KeyEvent event, TextField pText, String expRegular, int max) {
    	if(pText.getText().length() >= max || !event.getCharacter().matches(expRegular)) {
			event.consume();
			Toolkit.getDefaultToolkit().beep(); // Sonido de advertencia
		}	
    }
    
    /**
     * Ingresa datos de la fecha a un comboBox:
     */
    public void ingresarDatosComboBox() {
    	comboDia.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
    	comboMes.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    	comboAnio.getItems().addAll("2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900", "1899", "1898", "1897", "1896", "1895", "1894", "1893", "1892", "1891", "1890");
    	comboDia.setValue("");
    	comboMes.setValue("");
    	comboAnio.setValue("");
    }  
    
    /**
     * Resetea los campos y posiciona los labels.
     */
    public void limpiarCampos() {
    	// TextFields:
    	textCodigoPropiedad.setText("");
    	textNoDeFinca.setText("");
    	textNoDeFolio.setText("");
    	textNoDeLibro.setText("");
    	textArea.setText("");
    	textNoDeCatastro.setText("");
    	textNoDeEscritura.setText("");
    	textValorPropiedad.setText("");
    	textCuotas.setText("");
    	textInteres.setText("");
    	textDpi.setText("");
    	
    	// ComboBoxes:
    	comboDia.setValue("");
    	comboMes.setValue("");
    	comboAnio.setValue("");
    	
    	// Reposicionamos los labels y el puntero al inicio :
    	requestedTextFields(textArea, labelArea, lineArea);
    	requestedTextFields(textCuotas, labelCuotas, lineCuotas);
    	requestedTextFields(textDpi, labelDpi, lineDpi);
    	requestedTextFields(textInteres, labelIntres, lineInteres);
    	requestedTextFields(textNoDeCatastro, labelNoDeCatastro, lineNoDeCatastro);
    	requestedTextFields(textNoDeEscritura, labelNoDeEscritura, lineNoDeEscritura);
    	requestedTextFields(textNoDeFinca, labelNoDeFinca, lineNoDeFinca);
    	requestedTextFields(textNoDeFolio, labelNoDeFolio, lineNoDeFolio);
    	requestedTextFields(textNoDeLibro, labelNoDeLibro, lineNoDeLibro);
    	requestedTextFields(textValorPropiedad, labelValorPropiedad, lineValorPropiedad);
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad);
    }
    
    /*============================== GUARDAR DATOS EN LA BASE DE DATOS ==============================*/
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
     * Verifica que cada campo tenga al menos un dato para ser guardado.
     * @return String menor de 27, no hay errores.
     */
    public String datosLlenados() {
    	// Retornamos el mensaje de error:
    	String mensaje = "Por favor, llene el campo: ";
    	
    	// TextFields:
    	labelCodigoPropiedad.setTextFill(Color.WHITE);
    	labelNoDeFinca.setTextFill(Color.WHITE);
    	labelNoDeFolio.setTextFill(Color.WHITE);
    	labelNoDeLibro.setTextFill(Color.WHITE);
    	labelArea.setTextFill(Color.WHITE);
    	labelNoDeCatastro.setTextFill(Color.WHITE);
    	labelNoDeEscritura.setTextFill(Color.WHITE);
    	labelValorPropiedad.setTextFill(Color.WHITE);
    	labelCuotas.setTextFill(Color.WHITE);
    	labelIntres.setTextFill(Color.WHITE);
    	labelDpi.setTextFill(Color.WHITE);
    	
    	// Campo Código de propiedad:
    	if(textCodigoPropiedad.getText().isEmpty()) {
    		return mensaje += aplicarError("Código de propiedad.", labelCodigoPropiedad, textCodigoPropiedad, lineCodigoPropiedad, Color.RED);	    		
    	}
    	
    	// Número de fínca
    	if(textNoDeFinca.getText().isEmpty()) {
    		return mensaje += aplicarError("No. de finca.", labelNoDeFinca, textNoDeFinca, lineNoDeFinca, Color.RED);    		
    	
    	}
    	
    	// No. de folio:
    	if(textNoDeFolio.getText().isEmpty()) {
    		return mensaje += aplicarError("No. de folio.", labelNoDeFolio, textNoDeFolio, lineNoDeFolio, Color.RED);	    		
    	}
    	
    	// No. de libro:
    	if(textNoDeLibro.getText().isEmpty()) {
    		return mensaje += aplicarError("No. de libro.", labelNoDeLibro, textNoDeLibro, lineNoDeLibro, Color.RED);
    	}
    	
    	// Área:
    	if(textArea.getText().isEmpty()) {
    		return mensaje += aplicarError("Área.", labelArea, textArea, lineArea, Color.RED);
    	}
    	
    	// No. de catastro
    	if(textNoDeCatastro.getText().isEmpty()) {
    		return mensaje += aplicarError("No. de catastro.", labelNoDeCatastro, textNoDeCatastro, lineNoDeCatastro, Color.RED);
    	}
    	
    	// No. de escritura:
    	if(textNoDeEscritura.getText().isEmpty()) {
    		return mensaje += aplicarError("No. de escritura.", labelNoDeEscritura, textNoDeEscritura, lineNoDeEscritura, Color.RED);
    	}
    	
    	// Valor de propiedad:
    	if(textValorPropiedad.getText().isEmpty()) {
    		return mensaje += aplicarError("Valor de la propiedad.", labelValorPropiedad, textValorPropiedad, lineValorPropiedad, Color.RED);
    	}
    	
    	// Cantidad de cuotas:
    	if(textCuotas.getText().isEmpty()) {
    		return mensaje += aplicarError("Cantidad de cuotas.", labelCuotas, textCuotas, lineCuotas, Color.RED);
    	}
    	
    	// Porcentaje de interés:
    	if(textInteres.getText().isEmpty()) {
    		return mensaje += aplicarError("Porcentaje de interés.", labelIntres, textInteres, lineInteres, Color.RED);
    	}
    	
    	// Dpi:
    	if(textDpi.getText().isEmpty()) {
    		return mensaje += aplicarError("Dpi del cliente.", labelDpi, textDpi, lineDpi, Color.RED);
    	}
    	
    	// Día
    	if(comboDia.getValue().equals("")) {
    		return mensaje += "Fecha de escritura, opción \"Día\".";
    	}
    	
    	// Mes
    	if(comboMes.getValue().equals("")) {
    		return mensaje += "Fecha de escritura, opción \"Mes\".";
    	}
    	
    	// Año
    	if(comboAnio.getValue().equals("")) {
    		return mensaje += "Fecha de escritura, opción \"Año\".";
    	}
    	
    	// Todos los campos están llenos:
    	return mensaje;
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
           	throw new IllegalArgumentException("Año inválido.");
	    } 
	    
	    // Obtenemos dato:
	    LocalDate today = LocalDate.of(year, month, dayOfMonth);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    System.out.println(formatter.format(today)); // 01/01/2016	
	    
	    return String.valueOf(formatter.format(today));
	}
    
    /**
	 * Ingresa los datos que se encuentran en los campos a un DtoPropiedad.
	 * @param miPropiedad Setea los datos.
	 * @return Retorna el conjunto de datos ingresados.
	 */
    public DtoPropiedad setDatosDtoPropiedad(DtoPropiedad miPropiedad) {
    	try {
    		miPropiedad.setCodigo_propiedad(textCodigoPropiedad.getText());
    		miPropiedad.setNumero_de_finca(textNoDeFinca.getText());
    		miPropiedad.setNumero_de_folio(textNoDeFolio.getText());
    		miPropiedad.setNumero_de_libro(textNoDeLibro.getText());
    		miPropiedad.setArea(textArea.getText());
    		miPropiedad.setNumero_de_catastro(textNoDeCatastro.getText());
    		miPropiedad.setNumero_de_escritura(textNoDeEscritura.getText());
    		miPropiedad.setValor_de_la_propiedad(textValorPropiedad.getText());
    		miPropiedad.setCantidad_de_cuotas(textCuotas.getText());
    		miPropiedad.setPorcentaje_de_interes(textInteres.getText());
    		miPropiedad.setDpi_cliente(textDpi.getText());
    		
    	}
    	catch(Exception e) {
    		String errorMensaje = "Por favor, ingrese datos en los campos faltantes.";     		
        	Alert alert = new Alert(AlertType.ERROR, errorMensaje + e.getMessage(), ButtonType.OK);
        	alert.setHeaderText(null);
           	alert.setTitle("Error");    	    
           	alert.showAndWait();
           	System.out.println(e.getMessage());
    		return null;
    	}    
    	try {    	
	    	miPropiedad.setFecha_de_escritura(
	    			formato_fecha(String.valueOf(comboDia.getValue()), 
	    						  String.valueOf(comboMes.getValue()),
	    						  String.valueOf(comboAnio.getValue())));
    	}catch(Exception e) {
    		String errorMensaje = "Por favor, ingrese una fecha de inscripción válida.";     		
        	Alert alert = new Alert(AlertType.ERROR, errorMensaje + e.getMessage(), ButtonType.OK);
        	alert.setHeaderText(null);
           	alert.setTitle("Error");    	    
           	alert.showAndWait();
           	System.out.println(e.getMessage());
           	return null;
    	}
    	
    	// Si todo está bien, regresamos el Dto:
		return miPropiedad;
    }
    
    /**
     * Guarda los datos o cambios dentro de la base de datos tras varias verificaciones.
     */
    public void guardarDatos() {
    	// Verificamos si se trata de una actualización o simplemente de un guardado
    	String mensajeAlerta = labelTitulo.getText().equals("ACTUALIZACIÓN DE DATOS" ) ? 
    			"¿Está seguro de querer actualizar los datos de la propiedad?" : 
    			"¿Está seguro de guardar los datos de la propiedad?";
    	
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
    	
    	// Instanciamos un objeto de la clase DtoPropiedad
    	DtoPropiedad miPropiedad = new DtoPropiedad();
    	
    	// Seteamos los datos dentro del dto:
    	miPropiedad = setDatosDtoPropiedad(miPropiedad);	
    	if(miPropiedad == null) {
    		return;
    	}
    	
    	// Si se trata de una actualización, ejecutamos:
    	if(labelTitulo.getText().equals("ACTUALIZACIÓN DE DATOS")) {
    		
    		ActualizarPropiedad actualizar = new ActualizarPropiedad();
    		if(actualizar.yaExisteCodigoPropiedad(miPropiedad, codigoPropiedadAnterior)) {
    			errorMensajeCampo = "El Código de Propiedad ingresado ya existe dentro de la base de datos.";     		
            	alert = new Alert(AlertType.ERROR, errorMensajeCampo, ButtonType.OK);
               	alert.setTitle("Error");  
               	alert.setHeaderText(null);
               	alert.showAndWait();    		
               	return;
    		}
    		actualizar.actualizarDatos(miPropiedad, codigoPropiedadAnterior);
    		
    		// Creamos un registro para el control de pagos para este cliente y propiedad.
    		if(!textCodigoPropiedad.getText().equals(codigoPropiedadAntControlDePagos)
    			|| !(textInteres.getText().equals(interesAntPagos))) {
    			ActualizarControlDePagos controlDePagos = new ActualizarControlDePagos();
    			// El interés ya no se usa:
    			controlDePagos.actualizarControlDePagos(textCodigoPropiedad.getText(), 
    					textInteres.getText(), codigoPropiedadAntControlDePagos);    			
    		}    		
    		
    		return;
    	}
    	
    	// Si se trata de un registro, ejecutamos:
    	//Instanciamos un objeto para acceder a las acciones de registro:
		RegistrarPropiedad registrar = new RegistrarPropiedad();
		registrar.setMiPropiedad(miPropiedad);
		
		//	Verificamos que no exista el cui
		if (registrar.yaExisteCodigoPropiedad()) {
			errorMensajeCampo = "El código de propiedad ingresado ya existe dentro de la base de datos.";
			alert = new Alert(AlertType.ERROR, errorMensajeCampo, ButtonType.OK);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
	
		// 	Verificamos que no exista el cui
		if(!registrar.yaExisteDPI()) {
			errorMensajeCampo = "El DPI del cliente ingresado no existe dentro de la base de datos.";     		
        	alert = new Alert(AlertType.ERROR, errorMensajeCampo, ButtonType.OK);
           	alert.setTitle("Error");  
           	alert.setHeaderText(null);
           	alert.showAndWait();    		
           	return;
		}
	
		// Finalmente registramos los datos en la base de datos:		
		registrar.registrar();
		
		// Creamos un registro para el control de pagos para este cliente y propiedad.
		RegistrarControlDePagos controlDePagos = new RegistrarControlDePagos();
		controlDePagos.crearRegistro(textDpi.getText(), textCodigoPropiedad.getText(), textInteres.getText());
		
		limpiarCampos();	
		return;
    }
    
    
    /*============================== INICIALIZADOR ==============================*/
    @FXML
    void initialize() {      	
    	// Ingresa los datos de una fecha a los comboBoxes de día, mes y año.
    	ingresarDatosComboBox();  
    } 
    
    /*============================== PANTALLA DE ACTUALIZACIÓN ==============================*/
    /**
     * Antes de mostrar la pantalla, se le pide al usuario que ingrese un Código de propiedad para consultar a 
     * la propiedad.
     * @return Falso si no encuentra el código o cancela la búsqueda. True si lo encuentra.
     */
    public boolean consultarCodigoPropiedadActualizacion() {
    	TextInputDialog dialog = new TextInputDialog("Código de propiedad");
    	dialog.setTitle("Actualizar");
    	dialog.setHeaderText(null);
    	dialog.setContentText("Ingrese el código de la propiedad:");
    	
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
     * Consulta la base de datos según el código ingresado.
     * @param pCodigo Parámetro para hacer la consulta.
     */
    public boolean obtenerDatosActualizar(String pCodigo) {
    	// Consultamos la base de datos con el código que ingresó el usuario:
		ConsultarPropiedad consultar = new ConsultarPropiedad();		
		
		// Instanciamos una lista de dtoPersona:
		List<DtoPropiedad> miActualizacion = new ArrayList<>();
		
		// Obtenemos los datos. Si está vacía, quiere decir que no encontró coincidencias con 
		// DPI ingresado.
		miActualizacion = consultar.obtenerDatos(pCodigo);
		if(miActualizacion.isEmpty()) {			
			return false;
		};
		
		// Seteamos los datos en los TextFields
		labelTitulo.setText("ACTUALIZACIÓN DE DATOS");
		labelRegresar.setLayoutY(0);
		lineInfRegresar.setLayoutY(0);
		setearDatosActualizacion(miActualizacion);	
		codigoPropiedadAnterior = textCodigoPropiedad.getText();
		codigoPropiedadAntControlDePagos = textCodigoPropiedad.getText();
		interesAntPagos = textInteres.getText();
		textDpi.setEditable(false);
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
    public void setearDatosActualizacion(List<DtoPropiedad> miActualizacion) {
    	// TextFields:
    	textCodigoPropiedad.setText(miActualizacion.get(0).getCodigo_propiedad());
    	textNoDeFinca.setText(miActualizacion.get(0).getNumero_de_finca());
    	textNoDeFolio.setText(miActualizacion.get(0).getNumero_de_folio());
    	textNoDeLibro.setText(miActualizacion.get(0).getNumero_de_libro());
    	textArea.setText(miActualizacion.get(0).getArea());
    	textNoDeCatastro.setText(miActualizacion.get(0).getNumero_de_catastro());
    	textNoDeEscritura.setText(miActualizacion.get(0).getNumero_de_escritura());
    	textValorPropiedad.setText(miActualizacion.get(0).getValor_de_la_propiedad());
    	textCuotas.setText(miActualizacion.get(0).getCantidad_de_cuotas());
    	textInteres.setText(miActualizacion.get(0).getPorcentaje_de_interes());
    	textDpi.setText(miActualizacion.get(0).getDpi_cliente());
    	
    	// ComboBoxes   
    	// Declaramos el iterador y recorremos el ArrayList
    	List<Integer> nacimiento = extraerNumeros(miActualizacion.get(0).getFecha_de_escritura());    	
    	Iterator<Integer> miIterator = nacimiento.iterator();
    	
    	int contador = 0;
    	
    	while(miIterator.hasNext()) {	    	
    		Integer elemento = miIterator.next();    		
    		if(contador == 0) {    		
    			comboDia.setValue(String.valueOf(elemento));    			
    		}    		
    		else if(contador == 1) {
    			comboMes.setValue(String.valueOf(elemento)); 
    			//comboMes.setSelectedIndex(elemento-1);    			
    		}else if(contador == 2) {
    			comboAnio.setValue(String.valueOf(elemento)); 
    			//comboAnio.setSelectedIndex(elemento-1);    			
    		}    		
    		contador++;    		
    	}
    	
    	// Posicionamos los labels:
    	requestedTextFields(textArea, labelArea, lineArea);
    	requestedTextFields(textCuotas, labelCuotas, lineCuotas);
    	requestedTextFields(textDpi, labelDpi, lineDpi);
    	requestedTextFields(textInteres, labelIntres, lineInteres);
    	requestedTextFields(textNoDeCatastro, labelNoDeCatastro, lineNoDeCatastro);
    	requestedTextFields(textNoDeEscritura, labelNoDeEscritura, lineNoDeEscritura);
    	requestedTextFields(textNoDeFinca, labelNoDeFinca, lineNoDeFinca);
    	requestedTextFields(textNoDeFolio, labelNoDeFolio, lineNoDeFolio);
    	requestedTextFields(textNoDeLibro, labelNoDeLibro, lineNoDeLibro);
    	requestedTextFields(textValorPropiedad, labelValorPropiedad, lineValorPropiedad);
    	requestedTextFields(textCodigoPropiedad, labelCodigoPropiedad, lineCodigoPropiedad); 
    }
    
    /**
     * Le informa al usuario que debe ir al módulo de actualización de clientes para editar el DPI.
     */
    public void editarDpiActualizacion() {
    	String mensaje = "Si desea editar el DPI del cliente, diríjase al módulo \"Actualización de clientes\".";
    	Alert alert = new Alert(AlertType.INFORMATION, mensaje, ButtonType.OK);
       	alert.setTitle("Editar DPI");  
       	alert.setHeaderText(null);
       	alert.showAndWait(); 
    }
}
