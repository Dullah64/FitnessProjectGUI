package ProjectFitness;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudioManagerMain extends Application {
    @Override
    // GUI is event driven
    public void start(Stage stage) throws IOException { // Create Stage

        FXMLLoader fxmlLoader = new FXMLLoader(StudioManagerController.class.getResource("StudioManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Fitness Club"); // Title
        stage.setScene(scene);
        stage.show(); // Show the Screen
    }

    public static void main(String[] args) {
        launch();
    }
}