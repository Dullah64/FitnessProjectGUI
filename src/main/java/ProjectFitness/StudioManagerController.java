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
import java.time.LocalDate;
import java.util.Scanner;


public class StudioManagerController {
    MemberList members = new MemberList();
    Schedule schedule = new Schedule();

    @FXML
    private TextField firstnameinput;
    @FXML
    private TextField lastnameinput;
    @FXML
    private Label memberLabel;
    @FXML
    private Button cancelmembership;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TextField guest;
    @FXML
    private Label guestPass;
    @FXML
    private Button addmember;
    @FXML
    private TextArea commandline;

    String fname;
    String lname;
    String dob;
    int numberofGuestPasses;

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
                System.out.println(fname + " " + lname + " removed.");
            } else {
                System.out.println(fname + " " + lname + " not in database.");
            }

        }


        public void addmember(ActionEvent event){ // Add conditions including guest part..
            fname = firstnameinput.getText();
            lname = lastnameinput.getText();
            dob = DatePicker.getValue().toString();

            // Set Conditions and Give Boundries to it..
            String numberGuest = guest.getText(); // Number of guest Passes..





            Date dob1 = parseDateOfBirth(dob);
            Location loc = Location.valueOf(getHomeLocation().toUpperCase());
            Date exp = new Date(4,14,2024);
            Profile profile = new Profile(fname, lname, dob1);

            if (!isOldEnough(dob1)) {
                System.out.println("DOB " + dob1 + ": must be 18 or older to join!");
            }

            if (getMemberShipType().equals("Basic")){
                Basic basic = new Basic(profile,exp,loc);
                members.add(basic);
                System.out.println("Member Added");
            }
            if (getMemberShipType().equals("Family")){
                Family family = new Family(profile,exp,loc);
                members.add(family);
            }
            if (getMemberShipType().equals("Premium")){
                Premium premium = new Premium(profile,exp,loc);
                members.add(premium);
            }

    }
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

    @FXML
    private TextArea resultText;
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
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private DatePicker datepicker;

    @FXML
    public RadioButton Bridgewater, Somerville, Piscataway, Franklin, Edison;
    public String gethomelocation() {
        if (Bridgewater.isSelected()) {
            return "BridgeWater";
        }
        if (Somerville.isSelected()) {
            return "Somerville";
        }
        if (Piscataway.isSelected()) {
            return "Piscataway";
        }
        if (Franklin.isSelected()) {
            return "Franklin";
        }
        return "Edison";
    }
    public String getclass(){
        if(CardioButton.isSelected()){
            return "Cardio";
        }
        if(PilatesButton.isSelected()){
            return "Pilates";
        }
        return "Spinning";
    }
    public String getInstructor(){
        if(EmmaButton.isSelected()){
            return "Emma";
        }
        if(JenniferButton.isSelected()){
            return "Jennifer";
        }
        if(KimButton.isSelected()){
            return "Kim";
        }
        if(DeniseButton.isSelected()){
            return "Denise";
        }
        return "Davis";
    }

    public void addmembertoclass(){
        fname = firstname.getText();
        lname = lastname.getText();
        dob = datepicker.getValue().toString();

        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        FitnessClass[] schArray = schedule.getclasses();
        Date dob1 = parseDateOfBirth(dob);
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        for (int i = 0; i <schArray.length; i++) {
            if (schArray[i].getoffer().name().equals(getclass().toUpperCase()) &&
                    schArray[i].getIns().name().equals(getInstructor().toUpperCase())) {
                if (schArray[i].getmembers().contains(member)) {
                    resultText.setText(fname + " " + lname + " is already registered for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase());
                    return;
                } else {
                    schArray[i].getmembers().add(member);
                    resultText.setText(fname + " " + lname + " is registered for " + getclass().toUpperCase() +
                            " with " + getInstructor().toUpperCase() + " at " + gethomelocation().toUpperCase());
                    return;
                }
            }
        }
        resultText.setText("Fitness class not on class schedule");
    }
    public void removememberfromclass(){
        fname = firstname.getText();
        lname = lastname.getText();
        dob = datepicker.getValue().toString();

        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        FitnessClass[] schArray = schedule.getclasses();
        Date dob1 = parseDateOfBirth(dob);
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);

        for (int i = 0; i < schArray.length; i++) {
            if (schArray[i].getmembers().contains(member)) {
                schArray[i].getmembers().remove(member);
                resultText.setText(fname + " " + lname + " is unregistered from the class");
                return;
            }
        }
        resultText.setText(fname + " " + lname + " is not registered for any classes");
    }
    public void addguest(){
        fname = firstname.getText();
        lname = lastname.getText();
        dob = datepicker.getValue().toString();

        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        FitnessClass[] schArray = schedule.getclasses();
        Date dob1 = parseDateOfBirth(dob);
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        for (int i = 0; i <schArray.length; i++) {
            if (schArray[i].getoffer().name().equals(getclass().toUpperCase()) &&
                    schArray[i].getIns().name().equals(getInstructor().toUpperCase())) {
                if (schArray[i].getguests().contains(member)) {
                    resultText.setText(fname + " " + lname + " is already registered as a guest for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase());
                    return;
                } else {
                    schArray[i].getguests().add(member);
                    System.out.println(fname + " " + lname + " is registered as a guest for " + getclass().toUpperCase() +
                            " with " + getInstructor().toUpperCase() + " at " + gethomelocation().toUpperCase());
                    return;
                }
            }
        }
        System.out.println("Fitness class not on class schedule");
    }
    public void removeguest(){
        fname = firstname.getText();
        lname = lastname.getText();
        dob = datepicker.getValue().toString();

        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        FitnessClass[] schArray = schedule.getclasses();
        Date dob1 = parseDateOfBirth(dob);
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        for (int i = 0; i <schArray.length; i++) {
            if (schArray[i].getoffer().name().equals(getclass().toUpperCase()) &&
                    schArray[i].getIns().name().equals(getInstructor().toUpperCase())) {
                if (schArray[i].getguests().contains(member)) {
                    schArray[i].getguests().remove(member);
                    resultText.setText(fname + " " + lname + " is unregistered as a guest for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase());
                    return;
                } else {
                    resultText.setText(fname + " " + lname + " is not registered as a guest for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase());
                    return;
                }
            }
        }
        System.out.println("Fitness class not on class schedule");
    }
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
