package ProjectFitness;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;


public class StudioManagerController {
    MemberList members = new MemberList();
    Member[] membersarray = members.getmembers();
    Schedule schedule = new Schedule();

    @FXML
    private TextField firstnameinput;
    @FXML
    private TextField lastnameinput;
    @FXML
    private Label memeberLabel;
    @FXML
    private Button cancelmemebership;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TextField guest;
    @FXML
    private Label guestPass;
    @FXML
            private Button addmember;
    @FXML
    private TextArea commandline2; // for the output area in memebership tab..
    @FXML
    private Button loadMembers; // to load the member list in the membershiptab
    String fname;
    String lname;
    String dob;
    int numberGuest;

    @FXML   // Getting MemberShip Type Radio Buttons
    private RadioButton rbbasic, rbfamily, rbpremium;
    public String getMemberShipType() {
        if (rbbasic.isSelected()) {
            return "Basic";
        }
        if (rbfamily.isSelected()) {
            return "Family";
        }
        return "Premium";
    }

    @FXML // Getting HomeStudio Location..
    private RadioButton rbBridgeWater, rbSomerville, rbPiscataway, rbFranklin, rbEdison;
    public String getHomeLocation() {
        if (rbBridgeWater.isSelected()) {
            return "BridgeWater";
        }
        if (rbSomerville.isSelected()) {
            return "Somerville";
        }
        if (rbPiscataway.isSelected()) {
            return "Piscataway";
        }
        if (rbFranklin.isSelected()) {
            return "Franklin";
        }
        return "Edison";
    }


    public void cancelmembership(ActionEvent event) {
        fname = firstnameinput.getText();
        lname = lastnameinput.getText();
        dob = DatePicker.getValue().toString();
        Date dob1 = parseDateOfBirth(dob);
            Profile profile = new Profile(fname, lname, dob1);
            Member member = new Member(profile);
            if (members.remove(member)) {
                commandline2.appendText(fname + " " + lname + " removed." + "\n");
            } else {
                commandline2.appendText(fname + " " + lname + " not in database." +"\n");
            }

        }


        public void addmember(ActionEvent event) { // Add conditions including guest part..
            fname = firstnameinput.getText();
            lname = lastnameinput.getText();
            dob = DatePicker.getValue().toString();

            // Set Conditions and Give Boundries to it..
            String numberGuest = guest.getText(); // Number of guest Passes..


            Date dob1 = parseDateOfBirth(dob);
            Location loc = Location.valueOf(getHomeLocation().toUpperCase());
            Date exp = new Date(4, 14, 2024);
            Profile profile = new Profile(fname, lname, dob1);

            if (!isOldEnough(dob1)) {
                commandline2.appendText("DOB " + dob1 + ": must be 18 or older to join!" + "\n");
            }

            if (getMemberShipType().equals("Basic")) {
                Basic basic = new Basic(profile, exp, loc);
                members.add(basic);
                commandline2.appendText("Member Added" + "\n");
            }
            if (getMemberShipType().equals("Family")) {
                Family family = new Family(profile, exp, loc);
                members.add(family);
            }
            if (getMemberShipType().equals("Premium")) {
                Premium premium = new Premium(profile, exp, loc);
                members.add(premium);
            }
        }
        @FXML
            public void loadMembers(ActionEvent event) throws IOException {  // MemberList Button
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Members File");

                // Show open file dialog
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                File selectedFile = fileChooser.showOpenDialog(stage);
                if (selectedFile != null) {

                    members.load(selectedFile);
                    //  Output Members
                    for (int i = 0; i < members.getsize(); i++){ // change this magic number
                        commandline2.appendText(membersarray[i].toString() + "\n");}
                } else {

                    System.out.println("No file selected.");
                }






            }

            // Do it here
        private Date parseDateOfBirth (String dob){
            String[] dobparts = dob.split("-");
            int dobmonth = Integer.parseInt(dobparts[0]);
            int dobday = Integer.parseInt(dobparts[1]);
            int dobyear = Integer.parseInt(dobparts[2]);
            return new Date(dobmonth, dobday, dobyear);
        }
    private boolean isOldEnough(Date dob) {
        // Check if the member is 18 or older
        Date eighteenYearsAgo = new Date(dob.getMonth(), dob.getDay(), dob.getYear() + 18);
        Date today = new Date(3, 14, 2024); // Current date
        return today.compareTo(eighteenYearsAgo) >= 0;
    }






























    private Schedule schedule1;
    private MemberList member;
    @FXML
    private TextArea resultText;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dob1;
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
