package ProjectFitness;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class StudioManagerController {
    private Schedule schedule;
    private MemberList member;
    @FXML
    private TextArea resultText;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField guest_pass;
    @FXML
    private RadioButton BasicButton;
    @FXML
    private RadioButton FamilyButton;
    @FXML
    private RadioButton PremiumButton;
    @FXML
    private RadioButton BridgewaterButton;
    @FXML
    private RadioButton SomervilleButton;
    @FXML
    private RadioButton PiscatawayButton;
    @FXML
    private RadioButton FranklinButton;
    @FXML
    private RadioButton EdisonButton;
    @FXML
    private RadioButton CardioButton;
    @FXML
    private RadioButton PilatesButton;
    @FXML
    private RadioButton SpinningButton;
    @FXML
    private RadioButton JenniferButton;
    @FXML
    private RadioButton DavisButton;
    @FXML
    private RadioButton KimButton;
    @FXML
    private RadioButton DeniseButton;
    @FXML
    private RadioButton EmmaButton;
    @FXML
    MenuItem showSchedule;
    @FXML
    MenuItem showAttendees;
    @FXML
    MenuItem showStudioLocations;
    @FXML
    MenuItem printByProfile;
    @FXML
    MenuItem printbyCountyZipcode;
    @FXML
    MenuItem printwithNextDues;


}
