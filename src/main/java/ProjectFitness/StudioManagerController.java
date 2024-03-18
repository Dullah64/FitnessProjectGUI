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
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class StudioManagerController {
    MemberList members = new MemberList();
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
    private DatePicker DatePicker1;
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
    @FXML
    private TextArea commandline1;
    @FXML
    private Button loadSchedule;
    @FXML
    private Button locationView;
    @FXML
    private TextArea locationText;
    @FXML
    private TextArea demoMembersText;
    @FXML
    private Button sortCounty;
    @FXML
    private Button sortProfile;
    @FXML
    private Button dFees;
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
        LocalDate date = DatePicker1.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        if (members.remove(member)) {
            commandline2.appendText(fname + " " + lname + " " + dob1 + " removed." + "\n");
        } else {
            commandline2.appendText(fname + " " + lname + " " + dob1 + " not in database." + "\n");
        }

    }

    public void addmember(ActionEvent event) { // Add conditions including guest part..
        fname = firstnameinput.getText();
        lname = lastnameinput.getText();
        LocalDate date = DatePicker1.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        numberGuest = Integer.parseInt(guest.getText()); // Number of guest Passes..
        Location loc = Location.valueOf(getHomeLocation().toUpperCase());
        Date exp = new Date(4, 14, 2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);

        if (!isOldEnough(dob1)) {
            commandline2.appendText("DOB " + dob1 + ": must be 18 or older to join!" + "\n");
        }
        if (members.contains(member)) {
            commandline2.appendText("Member already in the database" + "\n");
            return;
        }

        //  Adding Members
        if (getMemberShipType().equals("Basic")) {
            if (numberGuest <= 0) {
                Basic basic = new Basic(profile, exp, loc);
                members.add(basic);
                commandline2.appendText(fname + " " + lname + " Added" + "\n");
                return;
            }
            commandline2.appendText("Guest Pass not eligible" + "\n");
        }
        if (getMemberShipType().equals("Family")) {
            if (numberGuest == 1) {
                Family family = new Family(profile, exp, loc);
                members.add(family);
                commandline2.appendText(fname + " " + lname + " Added" + "\n");
                return;
            }
            commandline2.appendText("Only 1 guest pass allowed" + "\n");
        }
        if (getMemberShipType().equals("Premium")) {
            if (numberGuest == 3) {
                Premium premium = new Premium(profile, exp, loc);
                members.add(premium);
                commandline2.appendText(fname + " " + lname + " Added" + "\n");
                return;
            }
            commandline2.appendText("No more than 3 or less guest passes allowed" + "\n");
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
            Member[] membersarray = members.getmembers();

            for (int i = 0; i < members.getsize(); i++) { // change this magic number
                commandline2.appendText(membersarray[i].toString() + "\n");
            }

        } else {

            System.out.println("No file selected.");
        }
    }

    public void loadSchedule(ActionEvent event) throws IOException {  // MemberList Button
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Schedule File");

        // Show open file dialog
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            schedule.load(selectedFile);
            FitnessClass[] classesarray = schedule.getclasses();
            //System.out.println(classesarray);
            for (FitnessClass fitnessClass : classesarray) { // change this magic number
                commandline1.appendText(fitnessClass.toString() + "\n");
            }

        } else {

            System.out.println("No file selected.");
        }
    }

    public void viewLocations(ActionEvent e) {
        for (Location location : Location.values()) {
            locationText.appendText(location.name() + " : " +
                    "Zip Code - 0" + location.getZipCode() + ", " +
                    "County - " + location.getCounty() + "\n");
        }

    }

    public void sortByCounty(ActionEvent e) {
        members.printByCounty();
        Member[] array = members.getmembers();
        StringBuilder sortedMembersText = new StringBuilder();
        sortedMembersText.append("*Members sorted by County*\n");
        for (int i = 0; i < members.getsize(); i++) {
            if (array[i] != null) {
                sortedMembersText.append(array[i]).append("\n");
            }
        }
        demoMembersText.appendText(sortedMembersText.toString());
    }

    public void sortByProfile(ActionEvent e) {
        members.printByMember();
        Member[] array = members.getmembers();
        StringBuilder sortedMembersText = new StringBuilder();
        sortedMembersText.append("*Members sorted by Profile*\n");
        for (int i = members.getsize() - 1; i >= 0; i--) {
            if (array[i] != null) {
                sortedMembersText.append(array[i]).append("\n");
            }
        }
        demoMembersText.appendText(sortedMembersText.toString());
    }

    public void ByFees(ActionEvent e) {
        members.printFees();
        Member[] array = members.getmembers();
        demoMembersText.appendText("*List of members with next dues*\n");
        for (int i = 0; i < members.getsize(); i++) {
            if (array[i] == null) {
                break;
            }
            demoMembersText.appendText(array[i] + " " + "[next due: $" + array[i].bill() + "]" + "\n");
        }
    }

    private Date parseDateOfBirth(String dob) {
        String[] dobparts = dob.split("/");
        int dobmonth = Integer.parseInt(dobparts[0].replaceAll("^0+(?!$)", ""));
        int dobday = Integer.parseInt(dobparts[1].replaceAll("^0+(?!$)", ""));
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
    @FXML private Button addmembertoclass;
    @FXML private Button removememberfromclass;
    @FXML private Button addguest;
    @FXML private Button removeguest;
    @FXML ToggleGroup toggleGroup = new ToggleGroup();
    @FXML ToggleGroup toggleGroup1 = new ToggleGroup();
    @FXML ToggleGroup toggleGroup2 = new ToggleGroup();
    //@FXML ToggleGroup toggleGroup3 = new ToggleGroup();
    @FXML private RadioButton RBBridgeWater, RBSomerville, RBPiscataway, RBFranklin, RBEdison;


    public String gethomelocation() {
        if (RBBridgeWater.isSelected()) {
            RBBridgeWater.setToggleGroup(toggleGroup);
            return "Bridgewater";
        }
        if (RBSomerville.isSelected()) {
            RBBridgeWater.setToggleGroup(toggleGroup);
            return "Somerville";
        }
        if (RBPiscataway.isSelected()) {
            RBPiscataway.setToggleGroup(toggleGroup);
            return "Piscataway";
        }
        if (RBFranklin.isSelected()) {
            RBFranklin.setToggleGroup(toggleGroup);
            return "Franklin";
        }
        RBEdison.setToggleGroup(toggleGroup);
        return "Edison";
    }
    public String getclass(){
        if(CardioButton.isSelected()){
            CardioButton.setToggleGroup(toggleGroup1);
            return "Cardio";
        }
        if(PilatesButton.isSelected()){
            PilatesButton.setToggleGroup(toggleGroup1);
            return "Pilates";
        }
        SpinningButton.setToggleGroup(toggleGroup1);
        return "Spinning";
    }
    public String getInstructor(){
        if(EmmaButton.isSelected()){
            EmmaButton.setToggleGroup(toggleGroup2);
            return "Emma";
        }
        if(JenniferButton.isSelected()){
            JenniferButton.setToggleGroup(toggleGroup2);
            return "Jennifer";
        }
        if(KimButton.isSelected()){
            KimButton.setToggleGroup(toggleGroup2);
            return "Kim";
        }
        if(DeniseButton.isSelected()){
            DeniseButton.setToggleGroup(toggleGroup2);
            return "Denise";
        }
        DavisButton.setToggleGroup(toggleGroup2);
        return "Davis";
    }
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private DatePicker datepicker;


    public void addmembertoclass(ActionEvent event) throws IOException {
        fname = firstname.getText();
        lname = lastname.getText();
        LocalDate date = datepicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        schedule.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\classSchedule.txt"));
        members.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\memberList.txt"));
        FitnessClass[] schArray = schedule.getclasses();
        Offer offer = Offer.valueOf(getclass().toUpperCase());
        Instructor ins = Instructor.valueOf(getInstructor().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        int attendance = 0;
        //int classes = schedule.getNumClasses();
        /*if(members)*/
        //Instructor instructor = Instructor.valueOf(getInstructor());
        if(!members.contains(member)){
            resultText.appendText(fname + " " + lname + " " + dateString +
                    " is not in the member database.\n");
            return;
        }
        for (int i = 0; i <schArray.length; i++) {
            Member[] m = members.getmembers();
            for (int j = 0; j < m.length; j++) {
                if (m[j] instanceof Basic && !gethomelocation().toUpperCase().equals(schArray[i].getLocation().name())) {
                    resultText.appendText(fname + " " + lname + " is attending a class at " +
                            schArray[i].getLocation().name().toUpperCase() + "- [BASIC] home studio at "
                            + gethomelocation().toUpperCase() + "\n");
                    return;
                }
                if (m[j].getexp().compareTo(exp) < 0 && m[j] != null) {
                    resultText.appendText(fname + " " + lname + " "+ dateString +" "+
                            " membership expired \n");
                    return;
                }
            }
            String zipcode = String.valueOf(schArray[i].getLocation().getZipCode());
            if (!getclass().toUpperCase().equals(schArray[i].getoffer().name())) {
                resultText.appendText(getclass() + "- Class name does not exist. \n");
                return;
            }
            if (!getInstructor().toUpperCase().equals(schArray[i].getIns().name())) {
                resultText.appendText(schArray[i].getIns().name().toUpperCase()
                        + " - Instructor does not exist. \n");
                return;
            }
            if (!gethomelocation().toUpperCase().equals(schArray[i].getLocation().name())) {
                resultText.appendText(schArray[i].getLocation().name() +
                        " - Invalid Studio Location. \n");
                return;
            }
            if (getclass().toUpperCase().equals(schArray[i].getoffer().name()) &&
                    getInstructor().toUpperCase().equals(schArray[i].getIns().name()) &&
                    gethomelocation().toUpperCase().equals(schArray[i].getLocation().name())) {
                resultText.appendText("Time conflict - " + fname + " " + lname
                        + " is in another class held at " + schArray[i].gettime() +
                        "- " + schArray[i].getIns().name().toUpperCase() + ", " +
                        schArray[i].gettime().toString() + ", " + schArray[i].getLocation().name().toUpperCase() + "\n");
                return;
            }
            if (getclass().toUpperCase().equals(schArray[i].getoffer().name()) &&
                    getInstructor().toUpperCase().equals(schArray[i].getIns().name())) {
                if (members.contains(member)) {
                    resultText.appendText(fname + " " + lname + " is already registered as a member for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase() + "\n");
                    return;
                } else {
                    schArray[i].getmembers().add(member);
                    schArray[i].addAttendee(member);
                    resultText.appendText(fname + " " + lname + " attendance recorded " +
                            getclass().toUpperCase() + "at " + schArray[i].getLocation().name().toUpperCase()
                            + ", " + zipcode + ", " + schArray[i].getLocation().getCounty().toUpperCase() + "\n");
                    return;
                }
            }
            resultText.appendText(schArray[i].getoffer().name().toUpperCase() +
                    " by" + schArray[i].getIns().name().toUpperCase() +
                    " does not exist at " + schArray[i].getLocation().name().toUpperCase() + "\n");
            return;
        }

    }
    public void removememberfromclass(ActionEvent event) throws IOException {
        fname = firstname.getText();
        lname = lastname.getText();
        LocalDate date = datepicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        schedule.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\classSchedule.txt"));
        members.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\memberList.txt"));
        FitnessClass[] schArray = schedule.getclasses();
        Offer offer = Offer.valueOf(getclass().toUpperCase());
        Instructor ins = Instructor.valueOf(getInstructor().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);

        for (int i = 0; i < schArray.length; i++) {
            String zipcode = String.valueOf(schArray[i].getLocation().getZipCode());
            if (members.contains(member)) {
                schArray[i].getmembers().remove(member);
                resultText.appendText(fname + " " + lname + " is removed from " +
                        schArray[i].getIns().name().toUpperCase() + ", " +
                        schArray[i].gettime().name() + ", " + schArray[i].getLocation().name() +
                        ", "+ zipcode + ", " + schArray[i].getLocation().getCounty());
                return;
            }
            resultText.setText(fname + " " + lname + " is not in " + schArray[i].getIns().name().toUpperCase() + ", " +
                    schArray[i].gettime().name() + ", " + schArray[i].getLocation().name() +
                    ", "+ zipcode + ", " + schArray[i].getLocation().getCounty());
            return;
        }
    }
    //copy code from addmembertoclass
    public void addguest(ActionEvent event) throws IOException {
        fname = firstname.getText();
        lname = lastname.getText();
        LocalDate date = datepicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        schedule.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\classSchedule.txt"));
        members.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\memberList.txt"));
        FitnessClass[] schArray = schedule.getclasses();
        Offer offer = Offer.valueOf(getclass().toUpperCase());
        Instructor ins = Instructor.valueOf(getInstructor().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        int attendance = 0;
        //int classes = schedule.getNumClasses();
        /*if(members)*/
        //Instructor instructor = Instructor.valueOf(getInstructor());
        if(!members.contains(member)){
            resultText.appendText(fname + " " + lname + " " + dateString +
                    " is not in the member database.\n");
            return;
        }
        for (int i = 0; i <schArray.length; i++) {
            Member[] m = members.getmembers();
            for (int j = 0; j < m.length; j++) {
                if (m[j] instanceof Basic && !gethomelocation().toUpperCase().equals(schArray[i].getLocation().name())) {
                    resultText.appendText(fname + " " + lname + " is attending a class at " +
                            schArray[i].getLocation().name().toUpperCase() + "- [BASIC] home studio at "
                            + gethomelocation().toUpperCase() + "\n");
                    return;
                }
                if (m[j].getexp().compareTo(exp) < 0 && m[j] != null) {
                    resultText.appendText(fname + " " + lname + " "+ dateString +" "+
                            " membership expired \n");
                    return;
                }
            }
            String zipcode = String.valueOf(schArray[i].getLocation().getZipCode());
            if (!getclass().toUpperCase().equals(schArray[i].getoffer().name())) {
                resultText.appendText(getclass() + "- Class name does not exist. \n");
                return;
            }
            if (!getInstructor().toUpperCase().equals(schArray[i].getIns().name())) {
                resultText.appendText(schArray[i].getIns().name().toUpperCase()
                        + " - Instructor does not exist. \n");
                return;
            }
            if (!gethomelocation().toUpperCase().equals(schArray[i].getLocation().name())) {
                resultText.appendText(schArray[i].getLocation().name() +
                        " - Invalid Studio Location. \n");
                return;
            }
            if (getclass().toUpperCase().equals(schArray[i].getoffer().name()) &&
                    getInstructor().toUpperCase().equals(schArray[i].getIns().name()) &&
                    gethomelocation().toUpperCase().equals(schArray[i].getLocation().name())) {
                resultText.appendText("Time conflict - " + fname + " " + lname
                        + " is in another class held at " + schArray[i].gettime() +
                        "- " + schArray[i].getIns().name().toUpperCase() + ", " +
                        schArray[i].gettime().toString() + ", " + schArray[i].getLocation().name().toUpperCase() + "\n");
                return;
            }
            if (getclass().toUpperCase().equals(schArray[i].getoffer().name()) &&
                    getInstructor().toUpperCase().equals(schArray[i].getIns().name())) {
                if (members.contains(member)) {
                    resultText.appendText(fname + " " + lname + " is already registered as a member for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase() + "\n");
                    return;
                } else {
                    schArray[i].getmembers().add(member);
                    schArray[i].addAttendee(member);
                    resultText.appendText(fname + " " + lname + " attendance recorded " +
                            getclass().toUpperCase() + "at " + schArray[i].getLocation().name().toUpperCase()
                            + ", " + zipcode + ", " + schArray[i].getLocation().getCounty().toUpperCase() + "\n");
                    return;
                }
            }
            resultText.appendText(schArray[i].getoffer().name().toUpperCase() +
                    " by" + schArray[i].getIns().name().toUpperCase() +
                    " does not exist at " + schArray[i].getLocation().name().toUpperCase() + "\n");
            return;
        }
    }
    //copy code from removememberfromclass
    public void removeguest(ActionEvent event) throws IOException {
        fname = firstname.getText();
        lname = lastname.getText();
        LocalDate date = datepicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        // Set Conditions and Give Boundries to it..
        String numberGuest = guest.getText(); // Number of guest Passes..
        Location loc = Location.valueOf(gethomelocation().toUpperCase());
        schedule.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\classSchedule.txt"));
        members.load(new File("C:\\Users\\great\\Desktop\\FitnessProjectGUI\\src\\main\\java\\ProjectFitness\\memberList.txt"));
        FitnessClass[] schArray = schedule.getclasses();
        Offer offer = Offer.valueOf(getclass().toUpperCase());
        Instructor ins = Instructor.valueOf(getInstructor().toUpperCase());
        Date exp = new Date(4,14,2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        for (int i = 0; i <schArray.length; i++) {
            if (getclass().toUpperCase().equals(schArray[i].getoffer().name()) &&
                    getInstructor().toUpperCase().equals(schArray[i].getIns().name())) {
                if (schArray[i].getguests().contains(member)) {
                    schArray[i].getguests().remove(member);
                    resultText.appendText(fname + " " + lname + " is unregistered as a guest for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase());
                    return;
                } else {
                    resultText.appendText(fname + " " + lname + " is not registered as a guest for " +
                            getclass().toUpperCase() + " with " + getInstructor().toUpperCase());
                    return;
                }
            }
        }
        System.out.println("Fitness class not on class schedule");
    }
    @FXML
    private Button showSchedule; // same thing as loadSchedule
    @FXML
    private Button  showAttendees; // a little tricky, need help

    public void displaySchedule() {
        int attendance = 0;
        String instance = "";
        for (FitnessClass fitnessClass: schedule.getclasses()) {
            for(Member member : fitnessClass.getAttendees().getmembers()){
                if(member instanceof Basic){
                    instance = "Basic";
                }
                if(member instanceof Family){
                    instance = "Family";
                }
                if(member instanceof Premium){
                    instance = "Premium";
                }
                attendance++;
            }
            for(Member member : fitnessClass.getAttendees().getmembers()) {
                System.out.println(fitnessClass.getoffer().name().toUpperCase()
                        + " - " + fitnessClass.getIns().name().toUpperCase() + ","
                        + ", " + fitnessClass.gettime().name() + ", " +
                        fitnessClass.getLocation().name());
                String zipcode = String.valueOf(member.getlocation().getZipCode());
                System.out.println(member.getProfile().getfname() + ":"
                        + member.getProfile().getlname() + ":" +
                        member.getProfile().getdob().toString() +
                        ", Membership expires " + member.getexp().toString() +
                        "Home Studio: " + member.getlocation().name().toUpperCase() +
                        ", " + zipcode + member.getlocation().getCounty().toUpperCase() +
                        ", (" + instance + ") number of classes attended: " +
                        attendance);
            }
        }
    }

    @FXML
    private Button showStudioLocations; // same thing as studioLocation

}
