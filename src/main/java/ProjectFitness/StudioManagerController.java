package ProjectFitness;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudioManagerController extends Application  {

    @Override
//    It's the top-level container that holds everything.
//    It represents the entire window that you see on your screen
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StudioManagerController.class.getResource("StudioManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        //  A scene represents the content inside the window (or stage). It's what you see inside the window -
        //  all the buttons, text fields, images, etc. that make up the user interface
        stage.setTitle("FitnessClub");
        stage.setScene(scene);
        stage.show();



    }




    public static void main(String[] args) {
        launch();
    }
}
