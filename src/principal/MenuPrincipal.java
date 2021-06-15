package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javax.swing.JButton;
import java.awt.Button;

// Importación
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		/*
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(3));
		transition.setNode(label);
		
		transition.setToY(-100);
		
		ScaleTransition transition1 = new ScaleTransition(Duration.seconds(3), button);
		transition1.setToX(2);
		transition1.setToY(2);
		
		RotateTransition transition2 = new RotateTransition(Duration.seconds(3), button);
		transition2.setByAngle(360);
		*/
		/*ParallelTransition parallelTransition = new ParallelTransition(transition, transition1, transition2);		
		
		parallelTransition.setOnFinished((e)->{
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), imageView);
			fadeOut.setFromValue(1.0); // Opacidad
			fadeOut.setToValue(0.0);
			fadeOut.play();
		});
		*/
		// load the image
        // Image image = new Image("principal/fondoEditado.png");
		
		Prueba prueba = new Prueba();
		prueba.main(null);
	
		// Parent root = FXMLLoader.load(getClass().getResource("pruebaFXML.fxml"));
		

        // simple displays ImageView the image as is
        //ImageView iv1 = new ImageView();
        //iv1.setImage(image);
        
        /*
        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView iv2 = new ImageView();
        iv2.setImage(image);
        iv2.setFitWidth(100);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);

        // defines a viewport into the source image (achieving a "zoom" effect) and
        // displays it rotated
        ImageView iv3 = new ImageView();
        iv3.setImage(image);
        Rectangle2D viewportRect = new Rectangle2D(40, 35, 110, 110);
        iv3.setViewport(viewportRect);
        iv3.setRotate(90);

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        HBox box = new HBox();
        box.getChildren().add(iv1);
        box.getChildren().add(iv2);
        box.getChildren().add(iv3);
        root.getChildren().add(box);
        */
        /*
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), iv1);
		fadeOut.setFromValue(0.0); // Opacidad
		fadeOut.setToValue(1.0);
		fadeOut.play();
		*/
        /*
        stage.setTitle("ImageView");
        stage.setWidth(415);
        stage.setHeight(200);
        stage.setScene(scene); 
        stage.sizeToScene(); 
        stage.show();
        */
		
    }
		
	
	/*
	private void makeFadeOut() {
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.seconds(2));
		// fadeTransition.setNode(rootPane);
		fadeTransition.setFromValue(1); // Opacidad
		fadeTransition.setToValue(0);
		fadeTransition.play();
	}
	*/
	
}
