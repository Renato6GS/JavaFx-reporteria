package salirPantalla;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ControladorSalir {

	@FXML
    private AnchorPane miAnchorPane;
	
	@FXML
    private Rectangle rectangleSi;

    @FXML
    private Label labelSi;

    @FXML
    private Rectangle rectangleNo;

    @FXML
    private Label labelNo;

    /*============================== ÁREA DE CLICK DE BOTONES ==============================*/
    @FXML
    void clickNo(MouseEvent event) throws IOException {
    	// Cargamos la escena y la mostramos. No efectos.
    	//Parent root = FXMLLoader.load(getClass().getResource("/principal/pruebaFXML.fxml"));
    	//miAnchorPane.getChildren().add(root); 
    	abrirEscena("/principal/pruebaFXML.fxml");
    }

    @FXML
    void clickSi(MouseEvent event) throws IOException {
    	// Cargamos la escena y la mostramos. No efectos.
    	abrirEscena("/loginPantalla/loginFXML.fxml");    	
    }

    /*============================== EFECTOS HOVER ==============================*/
    @FXML
    void hoverNo(MouseEvent event) {
    	efectoHover(rectangleNo);
    }

    @FXML
    void hoverSi(MouseEvent event) {
    	efectoHover(rectangleSi);
    }

    /*============================== EFECTOS NO HOVER ==============================*/
    @FXML
    void noHoverNo(MouseEvent event) {
    	efectoNoHover(rectangleNo);
    }

    @FXML
    void noHoverSi(MouseEvent event) {
    	efectoNoHover(rectangleSi);
    }
    
    /*============================== MÉTODOS ==============================*/
    /**
     * Hover a los botones con un color de fondo.
     * @param pRectangle Es quien recibe el color.
     */
    private void efectoHover(Rectangle pRectangle) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(pRectangle.fillProperty(),Color.web("#f800f8")); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    /**
     * Cuando el mouse sale del rectangle, le quita el color de fondo.
     * @param pRectangle Es quien pierde su color de fondo.
     */
    private void efectoNoHover(Rectangle pRectangle) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(pRectangle.fillProperty(),Color.rgb(0, 0, 0,0)); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }
    
    /**
     * Abre una escena según el botón presionado.
     * @param ruta Puede ser a la ruta principal o al login.
     * @throws IOException
     */
    private void abrirEscena(String ruta) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource(ruta));
    	miAnchorPane.getChildren().add(root); 
    }

}
