package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Prueba extends Application {
	
	private double ancho;
	private double largo;
	
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public void start(Stage primaryStage) throws Exception {
		 
		    // Group group = new Group(btn);
		    // Scene scene = new Scene(group, 600, 600);
		    
		 // Así se incluye un archivo fxml creado desde el editor:
		
			// CORRECTO
		    // Parent root = FXMLLoader.load(getClass().getResource("../loginPantalla/loginFXML.fxml"));
		
			// HICIMOS ESTA PRUEBA PARA EVITAR LAS ANIMACIONES E IR DIRECTAMENTE 
			Parent root = FXMLLoader.load(getClass().getResource("../salirPantalla/salirFXML.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("../registrarActualizarPropiedad/propiedadFXML.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("pruebaFXML.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/registrarActualizarCliente/pantallaRegistrarCliente/registrarClienteFXML.fxml"));
		    
			// Scene scene = new Scene(root, 600, 600);
		    Scene scene = new Scene(root);
		    
		    /*
		    
		 Image image = new Image("principal/fondoEditado.png");
		 ImageView iv1 = new ImageView();
	     iv1.setImage(image);
	     
	     	     
         scene.setFill(Color.BLACK);
         HBox box = new HBox();
         iv1.fitHeightProperty().bind(box.heightProperty());
         iv1.setPreserveRatio(true);
         box.getChildren().add(iv1);
         group.getChildren().add(box);
         
		 
       //Fade in transition
         
		    FadeTransition fadeInTransition = new FadeTransition(Duration.millis(2000), box);
		    fadeInTransition.setFromValue(0.0);
		    fadeInTransition.setToValue(1);		    
		    fadeInTransition.play();
		  
		    //Fade out transition
		    /*
		    FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(1500), iv1);
		    fadeOutTransition.setFromValue(1.0);
		    fadeOutTransition.setToValue(0.0);
		    fadeOutTransition.play();
		    */
		    
		  
		    
		    //primaryStage.setTitle("Bienes Inmuebles: F1 LM");
		    //primaryStage.setWidth(415);
		    //primaryStage.setHeight(200);		    
		    primaryStage.setScene(scene); 
		    //primaryStage.sizeToScene(); 
		    primaryStage.show(); 
		    
    }
		
    public static void main(String[] args) {
        Application.launch(args);
    }

}
