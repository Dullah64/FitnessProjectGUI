module org.example.fitnessprojectgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens ProjectFitness to javafx.fxml;
    exports ProjectFitness;
}