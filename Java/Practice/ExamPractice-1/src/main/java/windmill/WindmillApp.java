package windmill;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * CS161 Final Exam Windmill App
 */
public class WindmillApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(WindmillApp.class.getResource("windmill.fxml"));
        Parent p = fxmlLoader.load();
    	scene = new Scene(p);
        stage.setScene(scene);
        stage.setTitle("Windmill");
        stage.show();
        WindmillController controller = (WindmillController) fxmlLoader.getController();
        scene.setOnKeyPressed(e -> {
        	controller.togglePlay(e);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}


























