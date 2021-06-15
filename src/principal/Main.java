/**
 * UNIVERSIDAD MARIANO GÁLVEZ DE GUATEMALA, ZACAPA
 * 
 * INGENIERÍA EN SISTEMAS
 * PROGRAMACIÓN II
 * 
 * Ing. Kevyn Aguilar Ramírez
 * 
 * Estudiante: Luis Renato Granados Ogáldez
 * Carnet    : 1190-19-4642
 * 
 * 31/10/20202
 * 
 */

package principal;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/loginPantalla/loginFXML.fxml"));
		Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image("/principal/icono.JPG"));
		primaryStage.setTitle("Bienes Inmuebles: F1 LM");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
