package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controlDePagos.ControladorControlDePagos;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import registrarActualizarPropiedad.pantallaRegistroActualizacionPropiedad.ControladorPantallaRegistroActualizacionPropiedad;
import reportes.ReporteClientesMorosos;
import reportes.ReporteClientesPorDpi;
import reportes.ReporteGeneralClientes;
import reportes.ReporteGeneralPropiedades;
import reportes.ReportePagosPendientes;
import reportes.ReportePagosPorCodigo;
import reportes.ReportePropiedadPorCodigo;
import reportes.ReportePropiedadPorDpi;

public class Controller {
	
	@FXML
    private VBox miVBox;	

    @FXML
    private AnchorPane miAnchorPane;
	
	@FXML
    private ImageView fondoImagen;	
	
	@FXML
    private Label labelF;

    @FXML
    private Label labelNum1;
    
    @FXML
    private Label labelSubTitIzquierdo;
    
    @FXML
    private Label labelSubTitDerecho;
    
    @FXML
    private Line lineaTituloIzquierdo;
    
    @FXML
    private Line lineaTituloDerecho;    
	
	@FXML
    private TextFlow textRegistroClientes;

    @FXML
    private Line lineaHorizontalInferior;

    @FXML
    private Line lineaVerticalInferior;

    @FXML
    private Line lineaHorizontalInferior2;

    @FXML
    private Line lineaVerticalInferior2;

    @FXML
    private Line lineaHorizontalSuperior;

    @FXML
    private Line lineaVerticalSuperior2;

    @FXML
    private Line lineaHorizontalSuperior2;

    @FXML
    private Line lineaVerticalSuperior;

    @FXML
    private TextFlow textRegistroPropiedad;

    @FXML
    private TextFlow textTraslado;

    @FXML
    private TextFlow textReportes;

    @FXML
    private Line lineaHorizontalInferior1;

    @FXML
    private Line lineaVerticalInferior1;

    @FXML
    private Line lineaHorizontalInferior21;

    @FXML
    private Line lineaVerticalInferior21;

    @FXML
    private Line lineaHorizontalSuperior1;

    @FXML
    private Line lineaVerticalSuperior21;

    @FXML
    private Line lineaHorizontalSuperior21;

    @FXML
    private Line lineaVerticalSuperior1;

    @FXML
    private Line lineaHorizontalInferior3;

    @FXML
    private Line lineaVerticalInferior3;

    @FXML
    private Line lineaHorizontalInferior22;

    @FXML
    private Line lineaVerticalInferior22;

    @FXML
    private Line lineaHorizontalSuperior3;

    @FXML
    private Line lineaVerticalSuperior22;

    @FXML
    private Line lineaHorizontalSuperior22;

    @FXML
    private Line lineaVerticalSuperior3;

    @FXML
    private Line lineaHorizontalInferior4;

    @FXML
    private Line lineaVerticalInferior4;

    @FXML
    private Line lineaHorizontalInferior23;

    @FXML
    private Line lineaVerticalInferior23;

    @FXML
    private Line lineaHorizontalSuperior4;

    @FXML
    private Line lineaVerticalSuperior23;

    @FXML
    private Line lineaHorizontalSuperior23;

    @FXML
    private Line lineaVerticalSuperior4;
    
    @FXML
    private Rectangle rectangleSalir;

    @FXML
    private Label labelSalir;

    @FXML
    private Rectangle rectangleHecho;

    @FXML
    private Label labelHecho;
    
    /*============================== ELEMENTOS SIDE BAR: REPORTES ==============================*/
    @FXML
    private AnchorPane sideBarReportes;
    
    @FXML
    private Rectangle rectangleSideReportes;
    
    @FXML
    private Label labelSideReportes;
    
    @FXML
    private Rectangle rectangleReporteGeneralClientes;

    @FXML
    private Rectangle rectangleReporteClientePorDpi;

    @FXML
    private Rectangle rectangleReporteGeneralPropiedad;

    @FXML
    private Rectangle rectangleReportePropiedadPorCodigo;

    @FXML
    private Rectangle rectangleReportePropiedadPorDpi;

    @FXML
    private Rectangle rectangleReporteDePagosPorCodigo;

    @FXML
    private Rectangle rectangleReporteDePagosPendientes;

    @FXML
    private Rectangle rectangleReporteDeClientesMorosos;

    @FXML
    private Label labelTitulo;

    @FXML
    private Line lineFinal;

    @FXML
    private Line lineFinal1;

    @FXML
    private Line lineFinal11;

    @FXML
    private Line lineFinal12;

    @FXML
    private Label labelReporteDpiCliente;

    @FXML
    private Line lineFinal111;

    @FXML
    private Line lineFinal13;

    @FXML
    private Label labelReporteGeneralPropiedades;

    @FXML
    private Line lineFinal112;

    @FXML
    private Line lineFinal14;

    @FXML
    private Label labelReportePropiedadPorCodigo;

    @FXML
    private Line lineFinal113;

    @FXML
    private Line lineFinal15;

    @FXML
    private Label labelReportePropiedadPorDpi;

    @FXML
    private Line lineFinal114;

    @FXML
    private Line lineFinal16;

    @FXML
    private Label labelReporteDePagosPorCodigoPropiedad;

    @FXML
    private Line lineFinal115;

    @FXML
    private Line lineFinal17;

    @FXML
    private Label labelReporteDePagosPendientes;

    @FXML
    private Line lineFinal116;

    @FXML
    private Line lineFinal18;

    @FXML
    private Label labelClientesMorosos;

    @FXML
    private Line lineFinal117;

    @FXML
    private Rectangle rectangleSideFuera;

    @FXML
    private Label labelReporteGeneralClientes;
    
    /*============================== AREA DE CLICK DE BOTONES ==============================*/
    @FXML
    void clickSideReportes(MouseEvent event) {
    	desplegarSideBarReportes();
    }
    
    @FXML
    void clickSideRectangleFuera(MouseEvent event) {
    	devolverSideBarReportes();
    }
       
    @FXML
    void clickHechoPor(MouseEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/hechoPorPantalla/hechoPorFXML.fxml"));
    	miAnchorPane.getChildren().add(root);   
    }
    
    @FXML
    void clickSalir(MouseEvent event) throws IOException {    	
    	// Cargamos la escena y la mostramos. No efectos.
    	Parent root = FXMLLoader.load(getClass().getResource("/salirPantalla/salirFXML.fxml"));
    	miAnchorPane.getChildren().add(root);    	
    }

    @FXML
    void registrarCliente(MouseEvent event) throws IOException {
    	// Cargamos la escena
    	Parent root = FXMLLoader.load(getClass().getResource("/registrarActualizarCliente/registrarActualizarCliente.fxml"));
    	Scene escena = textRegistroClientes.getScene();    	
    	
    	// Obtenemos y añadimos la escena:
    	root.translateXProperty().set(-escena.getWidth());
    	miAnchorPane.getChildren().add(root);
    	
    	// Mostramos la escena con una línea de tiempo:
    	Timeline timeLine = new Timeline();
    	KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
    	timeLine.getKeyFrames().add(kf);
    	timeLine.play();
    }
    
    @FXML
    void registrarPropiedad(MouseEvent event) throws IOException {
    	mostrarEscena("/registrarActualizarPropiedad/propiedadFXML.fxml", 1);
    }
    
    @FXML
    void clickReportes(MouseEvent event) throws IOException {
    	mostrarEscena("/controlDePagos/controDePagosFXML.fxml", 1);
    }
    
    @FXML
    void clickReporteClientesMorosos(MouseEvent event) {
    	reporteDeClientesMorosos();
    }

    @FXML
    void clickReporteDePagosPendientes(MouseEvent event) {
    	reporteDePagosPendientes();
    }

    @FXML
    void clickReporteDePagosPorCodigoPropiedad(MouseEvent event) {
    	reporteDePagosPorCodigoPropiedad();
    }

    @FXML
    void clickReporteDpiCliente(MouseEvent event) {
    	reportePorDpiCliente();
    }

    @FXML
    void clickReporteGeneralClientes(MouseEvent event) {
    	reporteGeneralClientes();
    }

    @FXML
    void clickReporteGeneralPropiedades(MouseEvent event) {
    	reporteGeneralPropiedades();
    }

    @FXML
    void clickReportePropiedadPorCodigo(MouseEvent event) {
    	reportePropiedadesPorCodigoPropiedad();
    }

    @FXML
    void clickReportePropiedadPorDpi(MouseEvent event) {
    	reportePropiedadesPorDpi();
    }

    @FXML
    void clickTrasladoPropiedad(MouseEvent event) throws IOException {
    	mostrarEscena("/trasladoPropiedad/trasladoPropiedadFXML.fxml", -1);
    }
    
    /*============================== EFECTO HOVER ==============================*/
    @FXML
    void hoverRectangleSideReportes(MouseEvent event) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleSideReportes.fillProperty(),Color.web("#f800f8")); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    @FXML
    void hoverReporteClientesMorosos(MouseEvent event) {
    	colorFondoBoton(rectangleReporteDeClientesMorosos, "hover", 200);
    }

    @FXML
    void hoverReporteClientesPorDpi(MouseEvent event) {
    	colorFondoBoton(rectangleReporteClientePorDpi, "hover", 200);
    }

    @FXML
    void hoverReporteGeneralClientes(MouseEvent event) {
    	colorFondoBoton(rectangleReporteGeneralClientes, "hover", 200);
    }

    @FXML
    void hoverReporteGeneralPropiedades(MouseEvent event) {
    	colorFondoBoton(rectangleReporteGeneralPropiedad, "hover", 200);
    }

    @FXML
    void hoverReportePagosPendientes(MouseEvent event) {
    	colorFondoBoton(rectangleReporteDePagosPendientes, "hover", 200);
    }

    @FXML
    void hoverReportePagosPorCodigo(MouseEvent event) {
    	colorFondoBoton(rectangleReporteDePagosPorCodigo, "hover", 200);
    }

    @FXML
    void hoverReportePropiedadPorCodigo(MouseEvent event) {
    	colorFondoBoton(rectangleReportePropiedadPorCodigo, "hover", 200);
    }

    @FXML
    void hoverReportePropiedadPorDpi(MouseEvent event) {
    	colorFondoBoton(rectangleReportePropiedadPorDpi, "hover", 200);
    }
    
    @FXML
    void hoverRegistroCliente(MouseEvent event) {    	
    	agrandarLinea(lineaHorizontalInferior);
    	agrandarLinea(lineaHorizontalInferior2);
    	agrandarLinea(lineaHorizontalSuperior);
    	agrandarLinea(lineaHorizontalSuperior2);
    	
    	agrandarLinea(lineaVerticalSuperior);
    	agrandarLinea(lineaVerticalSuperior2);
    	agrandarLinea(lineaVerticalInferior);
    	agrandarLinea(lineaVerticalInferior2);
    }
    
    @FXML
    void hoverRegistroPropiedad(MouseEvent event) {
    	agrandarLinea(lineaHorizontalInferior22);
    	agrandarLinea(lineaHorizontalInferior3);
    	agrandarLinea(lineaHorizontalSuperior22);
    	agrandarLinea(lineaHorizontalSuperior3);
    	
    	agrandarLinea(lineaVerticalSuperior22);
    	agrandarLinea(lineaVerticalSuperior3);
    	agrandarLinea(lineaVerticalInferior22);
    	agrandarLinea(lineaVerticalInferior3);
    }

    @FXML
    void hoverReportes(MouseEvent event) {
    	agrandarLinea(lineaHorizontalInferior23);
    	agrandarLinea(lineaHorizontalInferior4);
    	agrandarLinea(lineaHorizontalSuperior23);
    	agrandarLinea(lineaHorizontalSuperior4);
    	
    	agrandarLinea(lineaVerticalSuperior23);
    	agrandarLinea(lineaVerticalSuperior4);
    	agrandarLinea(lineaVerticalInferior23);
    	agrandarLinea(lineaVerticalInferior4);    	
    }

    @FXML
    void hoverTraslado(MouseEvent event) {
    	agrandarLinea(lineaHorizontalInferior21);
    	agrandarLinea(lineaHorizontalInferior1);
    	agrandarLinea(lineaHorizontalSuperior21);
    	agrandarLinea(lineaHorizontalSuperior1);
    	
    	agrandarLinea(lineaVerticalSuperior21);
    	agrandarLinea(lineaVerticalSuperior1);
    	agrandarLinea(lineaVerticalInferior21);
    	agrandarLinea(lineaVerticalInferior1); 
    }
    
    @FXML
    void hoverRectangleHecho(MouseEvent event) {    	
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleHecho.fillProperty(),Color.web("#f800f8")); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();	    
    }

    @FXML
    void hoverRectangleSalir(MouseEvent event) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleSalir.fillProperty(),Color.web("#f800f8")); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    /*============================== EFECTOS NO HOVER ==============================*/
    @FXML
    void noHoverRectangleSideReportes(MouseEvent event) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleSideReportes.fillProperty(),Color.rgb(0, 0, 0,0)); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    @FXML
    void noHoverReporteClientesMorosos(MouseEvent event) {
    	colorFondoBoton(rectangleReporteDeClientesMorosos, "#FFF", 200);
    }

    @FXML
    void noHoverReporteClientesPorDpi(MouseEvent event) {
    	colorFondoBoton(rectangleReporteClientePorDpi, "#FFF", 200);
    }

    @FXML
    void noHoverReporteGeneralClientes(MouseEvent event) {
    	colorFondoBoton(rectangleReporteGeneralClientes, "#FFF", 200);
    }

    @FXML
    void noHoverReporteGeneralPropiedades(MouseEvent event) {
    	colorFondoBoton(rectangleReporteGeneralPropiedad, "#FFF", 200);
    }

    @FXML
    void noHoverReportePagosPendientes(MouseEvent event) {
    	colorFondoBoton(rectangleReporteDePagosPendientes, "#FFF", 200);
    }

    @FXML
    void noHoverReportePagosPorCodigo(MouseEvent event) {
    	colorFondoBoton(rectangleReporteDePagosPorCodigo, "#FFF", 200);
    }

    @FXML
    void noHoverReportePropiedadPorCodigo(MouseEvent event) {
    	colorFondoBoton(rectangleReportePropiedadPorCodigo, "#FFF", 200);
    }

    @FXML
    void noHoverReportePropiedadPorDpi(MouseEvent event) {
    	colorFondoBoton(rectangleReportePropiedadPorDpi, "#FFF", 200);
    }

    
    @FXML
    void noHoverRegistroCliente(MouseEvent event) {   
    	apequenarLinea(lineaHorizontalInferior);
    	apequenarLinea(lineaHorizontalInferior2);
    	apequenarLinea(lineaHorizontalSuperior);
    	apequenarLinea(lineaHorizontalSuperior2);   	
    	
    	apequenarLinea(lineaVerticalSuperior);
    	apequenarLinea(lineaVerticalSuperior2);
    	apequenarLinea(lineaVerticalInferior);
    	apequenarLinea(lineaVerticalInferior2);
    }
    
    @FXML
    void noHoverRegistroPropiedad(MouseEvent event) {
    	apequenarLinea(lineaHorizontalInferior22);
    	apequenarLinea(lineaHorizontalInferior3);
    	apequenarLinea(lineaHorizontalSuperior22);
    	apequenarLinea(lineaHorizontalSuperior3);
    	
    	apequenarLinea(lineaVerticalSuperior22);
    	apequenarLinea(lineaVerticalSuperior3);
    	apequenarLinea(lineaVerticalInferior22);
    	apequenarLinea(lineaVerticalInferior3); 
    }

    @FXML
    void noHoverReportes(MouseEvent event) {
    	apequenarLinea(lineaHorizontalInferior23);
    	apequenarLinea(lineaHorizontalInferior4);
    	apequenarLinea(lineaHorizontalSuperior23);
    	apequenarLinea(lineaHorizontalSuperior4);
    	
    	apequenarLinea(lineaVerticalSuperior23);
    	apequenarLinea(lineaVerticalSuperior4);
    	apequenarLinea(lineaVerticalInferior23);
    	apequenarLinea(lineaVerticalInferior4); 
    }

    @FXML
    void noHoverTraslado(MouseEvent event) {
    	apequenarLinea(lineaHorizontalInferior21);
    	apequenarLinea(lineaHorizontalInferior1);
    	apequenarLinea(lineaHorizontalSuperior21);
    	apequenarLinea(lineaHorizontalSuperior1);
    	
    	apequenarLinea(lineaVerticalSuperior21);
    	apequenarLinea(lineaVerticalSuperior1);
    	apequenarLinea(lineaVerticalInferior21);
    	apequenarLinea(lineaVerticalInferior1); 
    }
    
    @FXML
    void noHoverRectangleHecho(MouseEvent event) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleHecho.fillProperty(),Color.rgb(0, 0, 0,0)); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }

    @FXML
    void noHoverRectangleSalir(MouseEvent event) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleSalir.fillProperty(),Color.rgb(0, 0, 0,0)); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    /*============================== INICIALIZADOR ==============================*/   
    @FXML
    void initialize() throws InterruptedException { 
    	    	
    }
    
    /*============================== MÉTODOS ==============================*/
    /**
     * Nos permite iniciar los elementos con una animación.
     */
    public void iniciarElementos() {   	
    	// Hacemos aparecer el fondo:
    	FadeTransition fadeInTransition = new FadeTransition(Duration.millis(4000), fondoImagen);
	    fadeInTransition.setFromValue(0.0);
	    fadeInTransition.setToValue(1);		    
	    fadeInTransition.play();	
	    
	    // Traemos las opciones con animaciones:
	    efectoAparicion(textRegistroClientes);
	    	    
	    // Opción 2: Registro y actualización de propiedades:
	    efectoAparicionDerecho(textRegistroPropiedad);
	    
	    // Opción 3: Traslado de propiedades
	    efectoAparicion(textTraslado);
	    
	    // Opción 4: Generación de reportes
	    efectoAparicionDerecho(textReportes);	
	    
	    // Efecto a las líneas:
	    efectoAparicionLineas(lineaHorizontalInferior);
	    efectoAparicionLineas(lineaHorizontalInferior2);
	    efectoAparicionLineas(lineaHorizontalSuperior);
	    efectoAparicionLineas(lineaHorizontalSuperior2);    	
	    efectoAparicionLineas(lineaVerticalSuperior);
	    efectoAparicionLineas(lineaVerticalSuperior2);
	    efectoAparicionLineas(lineaVerticalInferior);
	    efectoAparicionLineas(lineaVerticalInferior2);
	    efectoAparicionLineas(lineaHorizontalInferior1);
	    efectoAparicionLineas(lineaVerticalInferior1);
	    efectoAparicionLineas(lineaHorizontalInferior21);
	    efectoAparicionLineas(lineaVerticalInferior21);
	    efectoAparicionLineas(lineaHorizontalSuperior1);
	    efectoAparicionLineas(lineaVerticalSuperior21);
	    efectoAparicionLineas(lineaHorizontalSuperior21);
	    efectoAparicionLineas(lineaVerticalSuperior1);
	    efectoAparicionLineas(lineaHorizontalInferior3);
	    efectoAparicionLineas(lineaVerticalInferior3);
	    efectoAparicionLineas(lineaHorizontalInferior22);
	    efectoAparicionLineas(lineaVerticalInferior22);
	    efectoAparicionLineas(lineaHorizontalSuperior3);
	    efectoAparicionLineas(lineaVerticalSuperior22);
	    efectoAparicionLineas(lineaHorizontalSuperior22);
	    efectoAparicionLineas(lineaVerticalSuperior3);
	    efectoAparicionLineas(lineaHorizontalInferior4);
	    efectoAparicionLineas(lineaVerticalInferior4);
	    efectoAparicionLineas(lineaHorizontalInferior23);
	    efectoAparicionLineas(lineaVerticalInferior23);
	    efectoAparicionLineas(lineaHorizontalSuperior4);
	    efectoAparicionLineas(lineaVerticalSuperior23);
	    efectoAparicionLineas(lineaHorizontalSuperior23);
	    efectoAparicionLineas(lineaVerticalSuperior4);
	    
	    // Botones inferiores
	    efectoAparicionRectangulo(rectangleSalir);
	    efectoAparicionRectangulo(rectangleHecho);
	    efectoAparicionRectangulo(rectangleSideReportes);  
	    efectoAparicionLabel(labelSalir);
	    efectoAparicionLabel(labelHecho);
	    efectoAparicionLabel(labelSideReportes);

	    // Titulos
	    efectoAparicionLabel(labelF);
	    efectoAparicionLabel(labelNum1);
	    efectoAparicionLabel(labelSubTitIzquierdo);
	    efectoAparicionLabel(labelSubTitDerecho);
	    efectoAparicionLineas(lineaTituloIzquierdo);
	    efectoAparicionLineas(lineaTituloDerecho);	 
    }
    
    /**
     * Crea el efecto de agrandado de las líneas cuando el punto esta encima. Es decir hover.
     * @param linea Agranda a la línea.
     */
    private void agrandarLinea(Line linea) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(linea.scaleXProperty(),1.5,Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    /**
     * Crea el efecto de apequeñar de las líneas cuando se sale el puntero. No hover.
     * @param linea Apequeña a la línea.
     */
    private void apequenarLinea(Line linea) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(linea.scaleXProperty(),1,Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    /**
     * Es un efecto de inicio que permite que los objetos aparezcan de izquierda a derecha.
     * @param pTextFlow Permite acceder a sus propiedades y así animarlo.
     */
    private void efectoAparicion(TextFlow pTextFlow) {
    	// Animamos de derecha a izquierda
    	pTextFlow.setLayoutX(pTextFlow.getLayoutX()+100);
	    TranslateTransition mover = new TranslateTransition(Duration.seconds(1.5), pTextFlow);
	    mover.setToX(-100);	    
	    SequentialTransition esperar = new SequentialTransition(
	    		new PauseTransition(Duration.millis(4000)), // Permite que se ejecuta primero el fondo.
	    		mover
	    );
	    esperar.play();
	    
	    // Le damos un efecto de fade:
	    FadeTransition aparecer = new FadeTransition(Duration.millis(1500), pTextFlow);
	    aparecer.setFromValue(0.0);
	    aparecer.setToValue(1);
	    SequentialTransition esperarAparecer = new SequentialTransition(
	    		new PauseTransition(Duration.millis(4000)), // Permite que se ejecuta primero el fondo.
	    		aparecer
	    );
	    esperarAparecer.play();
    }
    
    /**
     * Es un efecto de inicio que permite que los objetos aparezcan de izquierda a derecha. 
     * Solo aplica a opciones derechos.
     * @param pTextFlow Permite acceder a sus propiedades y así animarlo.
     */
    private void efectoAparicionDerecho(TextFlow pTextFlow) {
    	// Solo hacemos esto con las opciones de la derecha. Permite que la ventana no se reajuste.
	    TranslateTransition sacarDeLaPantalla1 = new TranslateTransition(Duration.seconds(1), pTextFlow);
	    sacarDeLaPantalla1.setToX(100);
	    sacarDeLaPantalla1.play();
	    
	    TranslateTransition mover = new TranslateTransition(Duration.seconds(1.5), pTextFlow);
	    mover.setToX(0); // Al poner 0 vuelve a su posición original. Hmm...    
	    SequentialTransition esperar = new SequentialTransition(
	    		new PauseTransition(Duration.millis(4000)), 
	    		mover
	    );
	    esperar.play();
	    
	    // Le damos un efecto de fade:
	    FadeTransition aparecer = new FadeTransition(Duration.millis(1500), pTextFlow);
	    aparecer.setFromValue(0.0);
	    aparecer.setToValue(1);
	    SequentialTransition esperarAparecer = new SequentialTransition(
	    		new PauseTransition(Duration.millis(4000)), // Permite que se ejecuta primero el fondo.
	    		aparecer
	    );
	    esperarAparecer.play();
    }
    
    /**
     * Crea el efecto de Fade a las líenas.
     * @param pLinea Le da la propiedad de Fade a la línea recibida.
     */
    private void efectoAparicionLineas(Line pLinea) {
    	// Le damos un efecto de fade:
	    FadeTransition aparecer = new FadeTransition(Duration.millis(1500), pLinea);
	    aparecer.setFromValue(0.0);
	    aparecer.setToValue(1);
	    SequentialTransition esperarAparecer = new SequentialTransition(
	    		new PauseTransition(Duration.millis(4000)), // Permite que se ejecuta primero el fondo.
	    		aparecer
	    );
	    esperarAparecer.play();
    }
    
    /**
     * Crea el efecto Fade a los rectángulos.
     * @param pRectangle Le da la propiedad de Fade al rectángulo recibido.
     */
    private void efectoAparicionRectangulo(Rectangle pRectangle) {
    	// Le damos un efecto de fade:
	    FadeTransition aparecer = new FadeTransition(Duration.millis(1500), pRectangle);
	    aparecer.setFromValue(0.0);
	    aparecer.setToValue(1);
	    SequentialTransition esperarAparecer = new SequentialTransition(
	    		new PauseTransition(Duration.millis(4000)), // Permite que se ejecuta primero el fondo.
	    		aparecer
	    );
	    esperarAparecer.play();
    }
    
    /**
     * Crea el efecto Fade a los rectángulos. 
     * @param pLabel Le da la propiedad de Fade al Label recibido.
     */
    private void efectoAparicionLabel(Label pLabel) {
    	// Le damos un efecto de fade:
	    FadeTransition aparecer = new FadeTransition(Duration.millis(1500), pLabel);
	    aparecer.setFromValue(0.0);
	    aparecer.setToValue(1);
	    SequentialTransition esperarAparecer = new SequentialTransition(
	    		new PauseTransition(Duration.millis(4000)), // Permite que se ejecuta primero el fondo.
	    		aparecer
	    );
	    esperarAparecer.play();
    }
    
    /**
     * Muestra la escena de acuerdo a la ruta dada.
     * @param ruta Redirecciona la nueva escena para mostrarla.
     * @param direccion De movimiento de animación.
     * @throws IOException
     */
    private void mostrarEscena(String ruta, int direccion) throws IOException {
    	// Cargamos la escena
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
//    	Parent root = (Parent)loader.load();
//    	Scene escena = textRegistroClientes.getScene();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
    	Parent root = (Parent)loader.load();
    	Scene escena = textRegistroClientes.getScene();
    	
    	// En el contol de pagos, solicitamos dpi del cliente y el código de la propiedad:
    	if(ruta.equals("/controlDePagos/controDePagosFXML.fxml")) {
    		
	    	Stage  curStage = (Stage)miAnchorPane.getScene().getWindow();	

	    	// Accedemos a su controlador para ingresar el DPI y realizar la consulta:
	    	ControladorControlDePagos controller = (ControladorControlDePagos)loader.getController(); // Inicializamos los elementos.	    

	    	// Si retorna falso, es porque se canceló la búsqueda:
	    	if(!controller.consultarPagos()) {
	    		return;
	    	}    	
	    	curStage.setScene(escena); // Mostramos la escena.	    	
	    	
    	}
    	
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
    
    /*============================== MÉTODOS PARA LOS REPORTES ==============================*/
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
    	}else  {
    		kv = new KeyValue(pRectangle.fillProperty(),Color.rgb(255,255,255,0.5));
    	}
    	KeyFrame kf = new KeyFrame(Duration.millis(pMilisegundos), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();    	
    }
    
    /**
     * Despliega el sideBar de reportes.
     */
    public void desplegarSideBarReportes() {
    	sideBarReportes.setVisible(true);
    	sideBarReportes.setLayoutX(-382);
	    TranslateTransition mover = new TranslateTransition(Duration.seconds(0.5), sideBarReportes);
	    mover.setToX(+382);	  
	    mover.play();
    }
    
    /**
     * Esconde el sideBar de reportes.
     */
    public void devolverSideBarReportes() {
    	TranslateTransition mover = new TranslateTransition(Duration.seconds(0.5), sideBarReportes);
	    mover.setToX(-532);	  
	    mover.play();
    }
    
    /**
     * Crea un reporte general de clientes.
     */
    public void reporteGeneralClientes() {
    	ReporteGeneralClientes reporte = new ReporteGeneralClientes();
    	reporte.crearReporteGeneralClientes();
    }
    
    /**
     * Crea un reporte de acuerdo a los DPIs de los clientes que ingrese el usuario.
     * Se utiliza el Query WHERE IN o $X en JRXML.
     */
    public void reportePorDpiCliente() {    
    	
    	// Le pedimos al usuario los DPIs que desea generar un reporte:
    	List<String> lista_dpi = new ArrayList<>();
    	lista_dpi = pedirDatosReportes(lista_dpi, "Ingrese el DPI del cliente: ", 
    			"¿Desea seguir ingresando DPIs?", "Reporte de cliente por DPI", "DPI");
    	
    	// Si está vacía, terminamos el proceso:
    	if(lista_dpi.isEmpty()) {
    		return;
    	}
    	
    	// Verificamos que los Dpis estén correctos:
    	ReporteClientesPorDpi reporte = new ReporteClientesPorDpi();
    	
    	// Evaluamos si los DPIs ingresados existen dentro de la base de datos:
    	for(int num_error = 0; num_error < lista_dpi.size(); num_error++) {
    		// Revisamos si exite el dpi ingresado:
    		if(reporte.existeDpi(lista_dpi.get(num_error))) {
    			continue; 			
        	};
        	
        	// Si llega aquí, el dpi ingresado no existe:
        	int opcion = alertaError("DPI RECHAZADO.\nEl DPI " + lista_dpi.get(num_error) + " no existe "
					+ "dentro de la base de datos.");
			
        	// Eliminar el dpi rechazado:
			if(opcion == 1) {
				lista_dpi.remove(num_error);
				num_error--; // Reducimos en 1 puesto que ahora su lugar es ocupado por otro.
				continue;
			}
			
			// Reingresar el dpi rechazado:
			if(opcion == 2) {
				lista_dpi.set(num_error, errorDato());
				num_error--; // Reducimos en 1 para que vuelva a ser evaluado.
				continue;
			}
			
			// Si se eligió la tercera opción, cancelamos el reporte:
			return;   
    	}    	
    	
    	// Creamos el reporte:
    	reporte.crearReportePorDpiCliente(lista_dpi);
    }
    
    /**
     * Genera un reporte de las propiedades.
     */
    public void reporteGeneralPropiedades() {
    	ReporteGeneralPropiedades reporte = new ReporteGeneralPropiedades();
    	reporte.crearReporteGeneralPropiedades();
    }
    
    /**
     * Genera un reporte de las propiedades de acuerdo a los código de propiedad ingresados.
     */
    public void reportePropiedadesPorCodigoPropiedad() {
    	// Le pedimos al usuario los DPIs que desea generar un reporte:
    	List<String> lista_propiedad = new ArrayList<>();
    	lista_propiedad = pedirDatosReportes(lista_propiedad, "Ingrese el Código de propiedad: ", 
    			"¿Desea seguir ingresando códigos de propiedad?", "Reporte de propiedades por "
    					+ "código de propiedad", "Código de propiedad");
    	
    	// Si está vacía, terminamos el proceso:
    	if(lista_propiedad.isEmpty()) {
    		return;
    	}
    	
    	// Verificamos que los Dpis estén correctos:
    	ReportePropiedadPorCodigo reporte = new ReportePropiedadPorCodigo();
    	
    	// Evaluamos si los DPIs ingresados existen dentro de la base de datos:
    	for(int num_error = 0; num_error < lista_propiedad.size(); num_error++) {
    		// Revisamos si exite el dpi ingresado:
    		if(reporte.existePropiedad(lista_propiedad.get(num_error))) {
    			continue; 			
        	};
        	
        	// Si llega aquí, el dpi ingresado no existe:
        	int opcion = alertaError("CÓDIGO DE PROPIEDAD RECHAZADA.\nEl "
        			+ "código de propiedad: " + lista_propiedad.get(num_error) + " no existe "
					+ "dentro de la base de datos.");
			
        	// Eliminar el dpi rechazado:
			if(opcion == 1) {
				lista_propiedad.remove(num_error);
				num_error--; // Reducimos en 1 puesto que ahora su lugar es ocupado por otro.
				continue;
			}
			
			// Reingresar el dpi rechazado:
			if(opcion == 2) {
				lista_propiedad.set(num_error, errorDato());
				num_error--; // Reducimos en 1 para que vuelva a ser evaluado.
				continue;
			}
			
			// Si se eligió la tercera opción, cancelamos el reporte:
			return;   
    	}    	
    	
    	// Creamos el reporte:
    	reporte.crearReportePropiedadPorCodigoPropiedad(lista_propiedad);
    }
    
    /**
     * Genera un reporte de las propiedades de acuerdo a los DPIs ingresados.
     */
    public void reportePropiedadesPorDpi() {
    	// Le pedimos al usuario los DPIs que desea generar un reporte:
    	List<String> lista_propiedad = new ArrayList<>();
    	lista_propiedad = pedirDatosReportes(lista_propiedad, "Ingrese el DPI: ", 
    			"¿Desea seguir ingresando DPIs?", "Reporte de propiedades por "
    					+ "DPI", "DPI");
    	
    	// Si está vacía, terminamos el proceso:
    	if(lista_propiedad.isEmpty()) {
    		return;
    	}
    	
    	// Verificamos que los Dpis estén correctos:
    	ReportePropiedadPorDpi reporte = new ReportePropiedadPorDpi();
    	
    	// Evaluamos si los DPIs ingresados existen dentro de la base de datos:
    	for(int num_error = 0; num_error < lista_propiedad.size(); num_error++) {
    		// Revisamos si exite el dpi ingresado:
    		if(reporte.existeDpi(lista_propiedad.get(num_error))) {
    			continue; 			
        	};
        	
        	// Si llega aquí, el dpi ingresado no existe:
        	int opcion = alertaError("DPI RECHAZADO.\nEl "
        			+ "DPI: " + lista_propiedad.get(num_error) + " no existe "
					+ "dentro de la base de datos o no cuenta con ninguna propiedad.");
			
        	// Eliminar el dpi rechazado:
			if(opcion == 1) {
				lista_propiedad.remove(num_error);
				num_error--; // Reducimos en 1 puesto que ahora su lugar es ocupado por otro.
				continue;
			}
			
			// Reingresar el dpi rechazado:
			if(opcion == 2) {
				lista_propiedad.set(num_error, errorDato());
				num_error--; // Reducimos en 1 para que vuelva a ser evaluado.
				continue;
			}
			
			// Si se eligió la tercera opción, cancelamos el reporte:
			return;   
    	}    	
    	
    	// Creamos el reporte:
    	reporte.crearReportePropiedadPorDpi(lista_propiedad);
    }
    
    /**
     * Genera un reporte de pagos de acuerdo a los códigos de propiedad ingresados.
     */
    public void reporteDePagosPorCodigoPropiedad() {
    	// Le pedimos al usuario los DPIs que desea generar un reporte:
    	List<String> lista_pagos = new ArrayList<>();
    	lista_pagos = pedirDatosReportes(lista_pagos, "Ingrese el código de propiedad: ", 
    			"¿Desea seguir ingresando códigos de propiedad?", "Reporte de pagos por "
    					+ "código de propiedad", "Código de propiedad");
    	
    	// Si está vacía, terminamos el proceso:
    	if(lista_pagos.isEmpty()) {
    		return;
    	}
    	
    	// Verificamos que los Dpis estén correctos:
    	ReportePagosPorCodigo reporte = new ReportePagosPorCodigo();
    	
    	// Evaluamos si los DPIs ingresados existen dentro de la base de datos:
    	for(int num_error = 0; num_error < lista_pagos.size(); num_error++) {
    		// Revisamos si exite el dpi ingresado:
    		if(reporte.existePropiedad(lista_pagos.get(num_error))) {
    			continue; 			
        	};
        	
        	// Si llega aquí, el dpi ingresado no existe:
        	int opcion = alertaError("CÓDIGO DE PROPIEDAD RECHAZADA.\nEl "
        			+ "código: " + lista_pagos.get(num_error) + " no existe "
					+ "dentro de la tabla de registro de control de pagos.");
			
        	// Eliminar el dpi rechazado:
			if(opcion == 1) {
				lista_pagos.remove(num_error);
				num_error--; // Reducimos en 1 puesto que ahora su lugar es ocupado por otro.
				continue;
			}
			
			// Reingresar el dpi rechazado:
			if(opcion == 2) {
				lista_pagos.set(num_error, errorDato());
				num_error--; // Reducimos en 1 para que vuelva a ser evaluado.
				continue;
			}
			
			// Si se eligió la tercera opción, cancelamos el reporte:
			return;   
    	}    	
    	
    	// Creamos el reporte:
    	reporte.crearReportePropiedadPorCodigoPropiedad(lista_pagos);
    }
    
    /**
     * Genera un reporte con los clientes que tienen pagos pendientes.
     */
    public void reporteDePagosPendientes() {
    	ReportePagosPendientes reporte = new ReportePagosPendientes();
    	reporte.crearReportePagosPendientes();
    }
    
    /**
     * Genera un reporte de todos los clientes e indica se este debe mora o no.
     */
    public void reporteDeClientesMorosos() {
    	ReporteClientesMorosos reporte = new ReporteClientesMorosos();
    	reporte.crearReporteClientesMorosos();
    }
    
    /**
     * Muestra un cuadro de diálogo en donde se le pide al usuario que ingrese datos para crear
     * un reporte.
     * @param lista Listado de todos los datos ingresados.
     * @param mensaje Instrucción.
     * @param mensajeNoSeguir Confirmación.
     * @param titulo Del cuadro de diálogo.
     * @param placeHolder Texto que aparece en el textField.
     * @return Lista de los datos ingresados.
     */
    public List<String> pedirDatosReportes(List<String> lista, String mensaje, String mensajeNoSeguir,
    		String titulo, String placeHolder) {    	
    	while(true) {
    		// Pedimos el dato:
	    	TextInputDialog dialog = new TextInputDialog(placeHolder);
	    	dialog.setTitle(titulo);
	    	dialog.setHeaderText(null);
	    	dialog.setContentText(mensaje);
	    	
	    	// Traditional way to get the response value.
	    	Optional<String> res = dialog.showAndWait();
	    	 	
	    	// Si no cancela, se realiza la consulta:
	    	if (res.isPresent()){
	    		System.out.println("Prueba: " + res.get());
	    		lista.add(res.get());
	    		
	    		// Boton para terminar el reporte:
	    		ButtonType terminar = new ButtonType("Generar reporte");
	    		
	    		Alert alert = new Alert(AlertType.INFORMATION, mensajeNoSeguir, ButtonType.YES, terminar);
	           	alert.setTitle(titulo);  
	           	alert.setHeaderText(null);  
	           	Optional<ButtonType> seguir = alert.showAndWait();

	           	// Generar reporte:
	           	if (seguir.get() == terminar) {	           		
	           		return lista.isEmpty() ? new ArrayList<>() : lista;
	           	}   	       		
	    	} else {
	    		// Botones:
	    		ButtonType terminar = new ButtonType("Generar reporte");
	        	ButtonType cancelar = new ButtonType("Cancelar reporte");
	        	ButtonType seguirRegistrando = new ButtonType("Seguir registrando");
	        	
	        	// Mostramos la alerta:
	    		Alert alert = new Alert(AlertType.INFORMATION, "¿Qué desea hacer?", terminar, cancelar, seguirRegistrando);
	    		alert.setTitle(titulo);  
	           	alert.setHeaderText(null);  
	           	Optional<ButtonType> seguir = alert.showAndWait();

	           	// Cancelar reporte:
	           	if (seguir.get() == cancelar) {
	           		System.out.println("Se canceló");
	           		return new ArrayList<>();
	           	}  	 
	           	
	           	// Generar reporte:
	           	if(seguir.get() == terminar) {
	           		return lista.isEmpty() ? new ArrayList<>() : lista;
	           	}	           	
	    	}
    	}   
    }   
    
    /**
     * Muestra un cuadro de diálogo para que el usuario pueda corregir su error.
     * @return El dato corregido.
     */
    public String errorDato() {   
    	
    	String datoCorregido = "";
    
    	// Pedimos el dato:
    	TextInputDialog dialog = new TextInputDialog("");
    	dialog.setTitle("Corrección de datos");
    	dialog.setHeaderText(null);
    	dialog.setContentText("Ingrese el dato a corregir: ");
    	
    	// Traditional way to get the response value.
    	Optional<String> res = dialog.showAndWait();
    	 	
    	// Si no cancela, se realiza la consulta:
    	if (res.isPresent()){
    		System.out.println("Prueba: " + res.get());
    		datoCorregido = res.get();
    	}
    	
    	return datoCorregido;
    }
    
    /**
     * Se le alerta al usuario de que hubo un error en el dato que ingresó.
     * @param mensaje Se le informa al usuario para que pueda actuar.
     * @return 1 = remover. 2 = Reintentar. 3 = Cancelar.
     */
    public int alertaError(String mensaje) {
    	// Botones:
		ButtonType remover = new ButtonType("Remover del reporte");
		ButtonType reintentar = new ButtonType("Reingresar");
    	ButtonType cancelar = new ButtonType("Cancelar reporte");
    	
    	// Mostramos la alerta:
		Alert alert = new Alert(AlertType.ERROR, mensaje + "\n\n¿Qué desea hacer?", remover, reintentar, cancelar);
		alert.setTitle("Dato rechazado");  
       	alert.setHeaderText(null);  
       	Optional<ButtonType> seguir = alert.showAndWait();

       	// Cancelar reporte:
       	if (seguir.get() == remover) {
       		return 1;
       	}  	
       	
       	if(seguir.get() == reintentar) {
       		return 2;
       	}
       	
       	return 3;
    }
}
