package loginPantalla;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import principal.Controller;

public class ControladorLogin {
	
	private final String USER = "admin";
	private final String PASS = "admin123"; 
	
	@FXML
    private AnchorPane anchoPrincipal;
	
	@FXML
    private AnchorPane miAnchorPane;
	
	@FXML
    private Button btnVer;

    @FXML
    private TextField textUsuario;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Rectangle rectangleEntrar;

    @FXML
    private Label btnEntrar;
    
    /*============================== ÁREA DE CLICK DE BOTONES ==============================*/
    @FXML
    void clickEntrar(MouseEvent event) throws IOException {    	
    	if(comprobarUserPass()) {
    		efectoFadeFondo();
       }    	
    }
    
    @FXML
    void clickVer(ActionEvent event) {
    	// Mostramos al usuario por medio de una alerta (SubClase de Dialog) el usuario y
    	// contraseña para acceder.
    	String mensaje = "Usuario: admin\nContraseña: admin123";
    	Alert alert = new Alert(AlertType.NONE, mensaje, ButtonType.OK);
    	alert.setTitle("Soporte");
    	alert.showAndWait();
    }

    /*============================== EFECTOS HOVER ==============================*/
    @FXML
    void hoverEntrar(MouseEvent event) {
    	// Cambia el color del rectángulo cuando el mouse se posiciona encima.
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleEntrar.fillProperty(),Color.web("#f800f8")); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }

    /*============================== EFECTOS NO HOVER ==============================*/
    @FXML
    void noHoverEntrar(MouseEvent event) {
    	// Cuanto el mouse se sale del botón Entrar, vuelve a su color original.
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(rectangleEntrar.fillProperty(),Color.rgb(0, 0, 0,0)); 	
    	KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    }    

    /*============================== EVENTO TECLAS: ENTER ==============================*/
    @FXML
    void teclaEnterPass(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER) && comprobarUserPass()) {
    		efectoFadeFondo();
       }
    }

    @FXML
    void teclaEnterUser(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER) && comprobarUserPass()) {
    		efectoFadeFondo();
       }
    }
    
    /*============================== MÉTODOS ==============================*/
    /**
     * Quitamos el fondo con animación fade para dejar solo un fondo negro y la bienvenida.
     */
    private void efectoFadeFondo() {
    	FadeTransition aparecer = new FadeTransition(Duration.millis(1500), miAnchorPane);
	    aparecer.setFromValue(1);
	    aparecer.setToValue(0.0);
	    aparecer.setOnFinished((ActionEvent e) -> {
	    	cargarMenuPrincipal();
	    });
	    aparecer.play();
    }
    
    /**
     * Carga la escena del menú principal para se mostrada después del fader.
     */
    private void cargarMenuPrincipal() {
    	Parent root;
		try {	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/pruebaFXML.fxml"));	    	
			root = (Parent)loader.load();			
			Scene escena = new Scene(root);
	    	Stage  curStage = (Stage)anchoPrincipal.getScene().getWindow();	    	
	    	Controller controller = (Controller)loader.getController(); // Inicializamos los elementos.	    
	    	controller.iniciarElementos(); 
	    	curStage.setScene(escena); // Mostramos la escena.
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    }    
    
    /**
     * Comprueba el usuario y contraseña ingresada por el usuario y así darle acceso a la aplicación.
     * @return True: usuario y contraseña válidas. False: Usuario o contraseña inválidas. 
     */
    private boolean comprobarUserPass() {    	
    	if(USER.equals(textUsuario.getText()) && PASS.equals(new String(textPassword.getText()))) {
    		return true;
    	}
    	String mensaje = "Usuario o contraseña inválidas.";
    	Alert alert = new Alert(AlertType.ERROR, mensaje, ButtonType.OK);
    	alert.setTitle("Error de login");
    	alert.showAndWait(); 
    	textUsuario.setText("");
    	textPassword.setText("");
    	textUsuario.requestFocus();
    	return false;
    }
}