package registrarActualizarCliente;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import principal.Controller;
import registrarActualizarCliente.pantallaRegistrarCliente.RegistrarClienteControlador;

public class ControladorCliente {
	
	@FXML
	private AnchorPane miAnchorPaneCliente;
	
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
    private Label btnRegresar;
    
    /*============================== ÁREA DE CLICK DE BOTONES ==============================*/
    @FXML
    void clickActualizar(MouseEvent event) throws IOException {    	
		nuevaEscena("/registrarActualizarCliente/pantallaRegistrarCliente/registrarClienteFXML.fxml", "abajo", labelActualizar);
    	
    }

    @FXML
    void clickRegistrar(MouseEvent event) throws IOException {
    	nuevaEscena("/registrarActualizarCliente/pantallaRegistrarCliente/registrarClienteFXML.fxml", "arriba", labelRegistrar);
    	//nuevaEscena("/principal/pruebaFXML.fxml", "derecha", btnRegresar);
    }

    @FXML
    void clickRegresar(MouseEvent event) throws IOException {    
    	nuevaEscena("/principal/pruebaFXML.fxml", "derecha", btnRegresar);
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
    void hoverRegistrar(MouseEvent event) {    	
    	moverLineas(lineaSup2, 124);
    	moverLineas(lineaSup3, 134);
    	moverLineas(lineaSup4, 144);
    	moverLineas(lineaSup5, 164);
    	moverLabelY(labelRegistrar, 22);
    }
    
    @FXML
    void hoverRegresar(MouseEvent event) {
    	moverLabelX(btnRegresar, -20);
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
    void noHoverRegistrar(MouseEvent event) {
    	moverLineas(lineaSup2, 3);
    	moverLineas(lineaSup3, 3);
    	moverLineas(lineaSup4, 3);
    	moverLineas(lineaSup5, 3);
    	moverLabelY(labelRegistrar, 2);
    }
    
    @FXML
    void noHoverRegresar(MouseEvent event) {
    	moverLabelX(btnRegresar, 0);
    }
    
    /*============================== MÉTODOS ==============================*/
    /**
     * Da animación a las líneas de arriba y abajo.
     * @param pLinea La línea quien sufrirá la animación.
     * @param pos Nueva posición.
     */
    private void moverLineas(Line pLinea, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLinea);
    	transicion.setToY(pos);
    	transicion.play();
    }
    
    /**
     * Da animación a los labels en dirección de la Y.
     * @param pLabel Label quien sufrirá la animación.
     * @param pos Nueva posición.
     */
    private void moverLabelY(Label pLabel, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLabel);
    	transicion.setToY(pos);
    	transicion.play();
    }
    
    /**
     * Da animación a los labels en dirección de la X.
     * @param pLabel Label quien sufrirá la animación.
     * @param pos Nueva posición.
     */
    private void moverLabelX(Label pLabel, int pos) {
    	TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.4), pLabel);
    	transicion.setToX(pos);
    	transicion.play();
    }
    
    /**
     * Según la ruta, nos mostrará una nueva escena.
     * @param ruta Nos redirije ya sea a registrar cliente, actualizar cliente o al menú principal.
     * @param direccion De que lado aparecera la escena.
     * @throws IOException
     */
    private void nuevaEscena(String ruta, String direccion, Label pBoton) throws IOException {
    	// Cargamos la escena    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
    	Parent root = (Parent)loader.load();
    	Scene escena = pBoton.getScene();
    	
    	// Obtenemos y añadimos la escena:
    	if(direccion.equals("arriba")) {
    		root.translateYProperty().set(-escena.getHeight());
    	}else if(direccion.equals("abajo")) {
    		root.translateYProperty().set(escena.getHeight());					
	    	Stage  curStage = (Stage)miAnchorPaneCliente.getScene().getWindow();	

	    	// Accedemos a su controlador para ingresar el DPI y realizar la consulta:
	    	RegistrarClienteControlador controller = (RegistrarClienteControlador)loader.getController(); // Inicializamos los elementos.	    

	    	// Si retorna falso, es porque se canceló la búsqueda:
	    	if(!controller.consultarDPIActualizacion()) {
	    		return;
	    	}    	
	    	curStage.setScene(escena); // Mostramos la escena.
	    	
    	}else if(direccion.equals("derecha")) {
    		root.translateXProperty().set(escena.getWidth());    		
    	}else {
    		System.out.println("No se obtuvo la dirección correctamente.");
    		return;
    	}
    	miAnchorPaneCliente.getChildren().add(root);    	
    	
    	// Mostramos la escena con una línea de tiempo:
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
