package jgoodman.GraphicsDemo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
	   public static Scene scene;
	   @Override
	   
	   public void start(Stage stage) throws IOException {
		   FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
	       Parent p = fxmlLoader.load();
	       scene = new Scene(p);
	       stage.setScene(scene);
	       stage.show();
	       PrimaryController controller = (PrimaryController) fxmlLoader.getController();
	       
	       //Add key press event handler to the scene here
	       scene.setOnKeyPressed(controller::handleKeyPress);
	       
	   }
	   
	   public static void main(String[] args) {
	       launch();
	   }
	}












