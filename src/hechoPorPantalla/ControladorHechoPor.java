package hechoPorPantalla;

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

public class ControladorHechoPor {
	
	@FXML
    private AnchorPane miAnchorPane;

    @FXML
    private Rectangle rectangleOk;

    @FXML
    private Label labelOk;

    /*============================== ÁREA DE CLICK DE BOTONES ==============================*/
    @FXML
    void clickOk(MouseEvent event) throws IOException {
    	abrirEscena("/principal/pruebaFXML.fxml");
    }

    /*============================== EFECTOS HOVER ==============================*/
    @FXML
    void hoverOk(MouseEvent event) {
    	efectoHover(rectangleOk);
    }

    /*============================== EFECTOS NO HOVER ==============================*/
    @FXML
    void noHoverOk(MouseEvent event) {
    	efectoNoHover(rectangleOk);
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
