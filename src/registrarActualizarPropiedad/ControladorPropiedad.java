package registrarActualizarPropiedad;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import principal.Controller;
import registrarActualizarCliente.pantallaRegistrarCliente.RegistrarClienteControlador;
import registrarActualizarPropiedad.pantallaRegistroActualizacionPropiedad.ControladorPantallaRegistroActualizacionPropiedad;

public class ControladorPropiedad {

	@FXML
    private AnchorPane miAnchorPanePropiedad;

    @FXML
    private Label labelRegresar;
    
    @FXML
    private Line lineaSup1;

    @FXML
    private Line lineaSup2;

    @FXML
    private Line lineaSup3;

    @FXML
    private Line lineaSup4;

    @FXML
    private Line lineaSup5;

    @FXML
    private Label labelRegistrar;

    @FXML
    private Line lineaInf5;

    @FXML
    private Line lineaInf4;

    @FXML
    private Line lineaInf3;

    @FXML
    private Line lineaInf2;

    @FXML
    private Line lineaInf1;

    @FXML
    private Label labelActualizar;

    @FXML
    void clickRegresar(MouseEvent event) throws IOException {
    	cargarEscena("/principal/pruebaFXML.fxml", -1);
    }

    @FXML
    void clickActualizar(MouseEvent event) throws IOException {
    	cargarEscena("/registrarActualizarPropiedad/pantallaRegistroActualizacionPropiedad/registroActualizacionPropiedadFXML.fxml", "abajo", labelActualizar);
    }

    @FXML
    void clickRegistrar(MouseEvent event) throws IOException {
    	cargarEscena("/registrarActualizarPropiedad/pantallaRegistroActualizacionPropiedad/registroActualizacionPropiedadFXML.fxml", "arriba", labelRegistrar);
    }

    /*============================== EFECTOS HOVER ==============================*/
    @FXML
    void hoverActualizar(MouseEvent event) {
    	moverLineas(lineaInf2, -124);
    	moverLineas(lineaInf3, -134);
    	moverLineas(lineaInf4, -144);
    	moverLineas(lineaInf5, -164);
    	moverLabelY(labelActualizar, -22);
    }

    @FXML
    void hoverRectangleRegresar(MouseEvent event) {
    	moverLabelX(labelRegresar, +20);
    }

    @FXML
    void hoverRegistrar(MouseEvent event) {
    	moverLineas(lineaSup2, 124);
    	moverLineas(lineaSup3, 134);
    	moverLineas(lineaSup4, 144);
    	moverLineas(lineaSup5, 164);
    	moverLabelY(labelRegistrar, 22);
    }

    /*============================== EFECTOS NO HOVER ==============================*/
    @FXML
    void noHoverActualizar(MouseEvent event) {
    	moverLineas(lineaInf2, 0);
    	moverLineas(lineaInf3, 0);
    	moverLineas(lineaInf4, 0);
    	moverLineas(lineaInf5, 0);
    	moverLabelY(labelActualizar, 0);
    }

    @FXML
    void noHoverRectangleRegresar(MouseEvent event) {
    	moverLabelX(labelRegresar, 0);
    }

    @FXML
    void noHoverRegistrar(MouseEvent event) {
    	moverLineas(lineaSup2, 3);
    	moverLineas(lineaSup3, 3);
    	moverLineas(lineaSup4, 3);
    	moverLineas(lineaSup5, 3);
    	moverLabelY(labelRegistrar, 2);
    }
    
    /*============================== M??TODOS ==============================*/
    /**
     * Da animaci??n a las l??neas de arriba y abajo.
     * @param pLinea La l??nea quien sufrir?? la animaci??n.
     * @param pos Nueva posici??n.
     */
    private void moverLineas(Line pLinea, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLinea);
    	transicion.setToY(pos);
    	transicion.play();
    }
    
    /**
     * Da animaci??n a los labels en direcci??n de la Y.
     * @param pLabel Label quien sufrir?? la animaci??n.
     * @param pos Nueva posici??n.
     */
    private void moverLabelY(Label pLabel, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLabel);
    	transicion.setToY(pos);
    	transicion.play();
    }
    
    /**
     * Da animaci??n a los labels en direcci??n de la X.
     * @param pLabel Label quien sufrir?? la animaci??n.
     * @param pos Nueva posici??n.
     */
    private void moverLabelX(Label pLabel, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLabel);
    	transicion.setToX(pos);
    	transicion.play();
    }
    
    /**
     * Muestra una nueva escena.
     * @param ruta Direcci??n de la nueva escena a mostrar.
     * @param direccion Animaci??n.
     * @throws IOException
     */
    private void cargarEscena(String ruta, int direccion) throws IOException {
    	// Cargamos la escena    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
    	Parent root = (Parent)loader.load();
    	Scene escena = labelRegresar.getScene();
    	
    	// Obtenemos y a??adimos la escena:
    	root.translateXProperty().set(escena.getWidth() * direccion);
    	miAnchorPanePropiedad.getChildren().add(root);
    	
    	// Mostramos la escena con una l??nea de tiempo:
    	Timeline timeLine = new Timeline();
    	KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
    	KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
    	timeLine.getKeyFrames().add(kf);
    	timeLine.play();
    }
    
    /**
     * Carga una nueva escena.
     * @param ruta Direcci??n de la nueva escena a mostrar.
     * @param direccion Animaci??n de la escena.
     * @param pBoton Label quien recib el click.
     * @throws IOException
     */
    private void cargarEscena(String ruta, String direccion, Label pBoton) throws IOException {
    	// Cargamos la escena    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
    	Parent root = (Parent)loader.load();
    	Scene escena = pBoton.getScene();
    	
    	// Obtenemos y a??adimos la escena:
    	if(direccion.equals("arriba")) {
    		root.translateYProperty().set(-escena.getHeight());
    	}else if(direccion.equals("abajo")) {
    		
    		root.translateYProperty().set(escena.getHeight());					
	    	Stage  curStage = (Stage)miAnchorPanePropiedad.getScene().getWindow();	

	    	// Accedemos a su controlador para ingresar el DPI y realizar la consulta:
	    	ControladorPantallaRegistroActualizacionPropiedad controller = (ControladorPantallaRegistroActualizacionPropiedad)loader.getController(); // Inicializamos los elementos.	    

	    	// Si retorna falso, es porque se cancel?? la b??squeda:
	    	if(!controller.consultarCodigoPropiedadActualizacion()) {
	    		return;
	    	}    	
	    	curStage.setScene(escena); // Mostramos la escena.
	    	
	    	
    	}else if(direccion.equals("derecha")) {
    		root.translateXProperty().set(escena.getWidth());    		
    	}else {
    		System.out.println("No se obtuvo la direcci??n correctamente.");
    		return;
    	}
    	miAnchorPanePropiedad.getChildren().add(root);    	
    	
    	// Mostramos la escena con una l??nea de tiempo:
    	Timeline timeLine = new Timeline();
    	KeyValue kv;
    	if(direccion.equals("derecha")) {
    		kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
    	}else {
    		kv = new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);
    	}    	
    	KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
    	timeLine.getKeyFrames().add(kf);
    	timeLine.play();
    }

}
