
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

/**
 * This class represents the controller for the Studio Manager application.
 * It handles user interactions with the GUI and manages the underlying data.
 *
 * @author Abdullah Qayyum
 */
public class StudioManagerController {
    MemberList members = new MemberList();
    Schedule schedule = new Schedule();

    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private Button cancelMembership;
    @FXML
    private DatePicker datePicker1;
    @FXML
    private TextArea guest;
    @FXML
    private Button addMember;
    @FXML
    private TextArea commandLine2; // for the output area in memebership tab..
    @FXML
    private Button loadMembers; // to load the member list in the membershiptab
    @FXML
    private TextArea commandLine1; //
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
    @FXML
    private Button rMembers;
    @FXML
    private Button rGuest;
    @FXML
    private TextField fName1;
    @FXML
    private TextField lName1;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private TextArea classText;
    private Button aMember;
    @FXML
    private Button aGuest;
    @FXML
    private Button saButton;
    @FXML
    private TextArea guestRemaining;

    String fname;
    String lname;
    String fname1;
    String lname1;

    @FXML   // Getting MemberShip Type Radio Buttons
    private RadioButton rbbasic, rbfamily, rbpremium;

    /**
     * Retrieves the membership type based on the selected radio button.
     * @return A string representing the membership type.
     */
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

    /**
     * Retrieves the home location based on the selected radio button.
     * @return A string representing the selected home location.
     */
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

    /**
     * Cancels the membership of a member based on the provided information.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param event The ActionEvent triggering the method.
     */
    @FXML
    public void cancelMembership(ActionEvent event) {
        fname = firstNameInput.getText();
        lname = lastNameInput.getText();
        LocalDate date = datePicker1.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);
        if (members.remove(member)) {
            commandLine2.appendText(fname + " " + lname + " removed." + "\n");
        } else {
            commandLine2.appendText(fname + " " + lname + " not in database." + "\n");
        }

    }

    /**
     * Adds a new member to the database based on the provided information.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param event The ActionEvent triggering the method.
     */
    @FXML
    public void addMember_(ActionEvent event) {
        fname = firstNameInput.getText();
        lname = lastNameInput.getText();
        LocalDate date = datePicker1.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        Location loc = Location.valueOf(getHomeLocation().toUpperCase());
        Date exp = new Date(4, 14, 2024);
        Profile profile = new Profile(fname, lname, dob1);
        Member member = new Member(profile);

        if (!isOldEnough(dob1)) {
            commandLine2.appendText("DOB " + dob1 + ": must be 18 or older to join!" + "\n");
            return;
        }
        if (members.contains(member)) {
            commandLine2.appendText(fname + lname + " already in the database" + "\n");
            return;
        }

        //  Adding Members
        if (getMemberShipType().equals("Basic")) {
                Basic basic = new Basic(profile, exp, loc);
                members.add(basic);
                guest.appendText("0 passes allowed" + "\n");
                commandLine2.appendText(fname + " " + lname + " Added" + "\n");
                return;
        }
        if (getMemberShipType().equals("Family")) {
                Family family = new Family(profile, exp, loc);
                members.add(family);
                guest.appendText("1 passes allowed"+ "\n");
                commandLine2.appendText(fname + " " + lname + " Added" + "\n");
                return;
        }
        if (getMemberShipType().equals("Premium")) {
                Premium premium = new Premium(profile, exp, loc);
                members.add(premium);
                guest.appendText("3 passes allowed"+ "\n");
                commandLine2.appendText(fname + " " + lname + " Added" + "\n");
                return;
        }
    }

    /**
     * Loads members from a selected file and displays them in the command line.
     * This method is triggered by clicking the "MemberList" button.
     * @param event The ActionEvent triggering the method.
     * @throws IOException If an I/O error occurs.
     */
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
                commandLine2.appendText(membersarray[i].toString() + "\n");
            }

        } else {

            commandLine2.appendText("No file selected.");
        }

    }

    /**
     * Loads fitness classes schedule from a selected file and displays them in the command line.
     * This method is triggered by clicking the "Load Schedule" button.
     * @param event The ActionEvent triggering the method.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void loadSchedule(ActionEvent event) throws IOException {  // MemberList Button
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Schedule File");

        // Show open file dialog
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            schedule.load(selectedFile);
            FitnessClass[] classesarray = schedule.getclasses();

            for (int i = 0; i < classesarray.length; i++) { // change this magic number
                commandLine1.appendText(classesarray[i].toString() + "\n");
            }

        } else {

            System.out.println("No file selected.");
        }


    }
    /**
     * Displays information about all available locations in the GUI.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
    public void viewLocations(ActionEvent e) {
        for (Location location : Location.values()) {
            locationText.appendText(location.name() + " : " +
                    "Zip Code - 0" + location.getZipCode() + ", " +
                    "County - " + location.getCounty() + "\n");
        }

    }

    /**
     * Sorts and displays members by county in the GUI.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
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

    /**
     * Sorts and displays members by profile in the GUI.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
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

    /**
     * Prints the list of members with next dues in the GUI.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
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

    @FXML
    private RadioButton sButton, pButton, cButton;

    /**
     * Retrieves the selected offer based on the state of radio buttons.
     * This method returns the selected offer as a string.
     * @return The selected offer ("SPINNING", "PILATES", or "CARDIO").
     */
    @FXML
    public String getOffer() {
        if (sButton.isSelected()) {
            return "SPINNING";
        }
        if (pButton.isSelected()) {
            return "PILATES";
        }
        return "CARDIO";
    }

    @FXML
    private RadioButton b1Button, s1Button, p1Button, f1Button, e1Button;

    /**
     * Retrieves the selected home location based on the state of radio buttons.
     * This method returns the selected home location as a string.
     * @return The selected home location ("BridgeWater", "Somerville", "Piscataway", "Franklin", or "Edison").
     */
    @FXML
    public String getHomeLocation2() {
        if (b1Button.isSelected()) {
            return "BridgeWater";
        }
        if (s1Button.isSelected()) {
            return "Somerville";
        }
        if (p1Button.isSelected()) {
            return "Piscataway";
        }
        if (f1Button.isSelected()) {
            return "Franklin";
        }
        return "Edison";
    }

    @FXML
    private RadioButton jButton, daButton, kButton, deButton, eBuuton;

    /**
     * Retrieves the selected instructor based on the state of radio buttons.
     * This method returns the selected instructor's name as a string.
     * @return The selected instructor's name ("JENNIFER", "DAVIS", "KIM", "DENISE", or "EMMA").
     */
    @FXML
    public String getInstructor() {
        if (jButton.isSelected()) {
            return "JENNIFER";
        }
        if (daButton.isSelected()) {
            return "DAVIS";
        }
        if (kButton.isSelected()) {
            return "KIM";
        }
        if (deButton.isSelected()) {
            return "DENISE";
        }
        return "EMMA";

    }

    /**
     * Removes a member from a fitness class based on the provided information.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
    public void removeMember(ActionEvent e) { // Done...
        fname1 = fName1.getText();
        lname1 = lName1.getText();
        LocalDate date = datePicker2.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        Profile profile = new Profile(fname1, lname1, dob1);
        Member member = new Member(profile);
        Offer offer = Offer.valueOf(getOffer().toUpperCase());
        Location loc = Location.valueOf(getHomeLocation2().toUpperCase());
        Instructor Ins = Instructor.valueOf(getInstructor().toUpperCase());

        FitnessClass[] schArray = schedule.getclasses();
        for (int i = 0; i < schArray.length; i++) {
            if (schArray[i].getMembers().contains(member) && schArray[i].getIns().equals(Ins) && schArray[i].getoffer().equals(offer) && schArray[i].getLocation().equals(loc)) {
                schArray[i].getMembers().remove(member);
                classText.appendText(fname1 + " " + lname1 + " is removed from "+Ins +" "+ schArray[i].gettime().gethour()+":"+
                        schArray[i].gettime().getmin()+", " +loc+ ", 0"+
                        schArray[i].getLocation().getZipCode()+ ", "+schArray[i].getLocation().getCounty() +"\n");
                return;
            }
        }
        classText.appendText(fname1 + " " + lname1 + " is not in "+Ins +" "+ offer +" "+loc+"\n");

    }

    /**
     * Removes a guest from a fitness class based on the provided information.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
    public void removeGuest(ActionEvent e) { // Done...
        fname1 = fName1.getText();
        lname1 = lName1.getText();
        LocalDate date = datePicker2.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        Profile profile = new Profile(fname1, lname1, dob1);
        Member member = new Member(profile);
        Offer offer = Offer.valueOf(getOffer().toUpperCase());
        Location loc = Location.valueOf(getHomeLocation2().toUpperCase());
        Instructor Ins = Instructor.valueOf(getInstructor().toUpperCase());
        FitnessClass[] schArray = schedule.getclasses();
        String county = null;
        for (int i = 0; i < schArray.length; i++) {
            if (schArray[i].getguests().contains(member) && schArray[i].getIns().equals(Ins) && schArray[i].getoffer().equals(offer) && schArray[i].getLocation().equals(loc)) {
                schArray[i].getguests().remove(member);
                classText.appendText(fname1 + " " + lname1 + "(guest) is removed from "+Ins +" "+ schArray[i].gettime().gethour()+":"+
                        schArray[i].gettime().getmin()+", " +loc+ ", 0"+
                        schArray[i].getLocation().getZipCode()+ ", "+schArray[i].getLocation().getCounty() +"\n");
                return;
            }
        }
        classText.appendText(fname1 + " " + lname1 + "(guest) is not in "+Ins +" "+ offer +" "+loc+"\n");
    }

    /**
     * Adds a member to a fitness class based on the provided information.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
    public void addMember(ActionEvent e) {
        fname1 = fName1.getText();
        lname1 = lName1.getText();
        LocalDate date = datePicker2.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        Profile profile = new Profile(fname1, lname1, dob1);
        Member member = new Member(profile);
        Offer offer = Offer.valueOf(getOffer().toUpperCase());
        Location loc = Location.valueOf(getHomeLocation2().toUpperCase());
        Instructor Ins = Instructor.valueOf(getInstructor().toUpperCase());
        Member[] array = members.getmembers();
        if (!(isOldEnough(dob1))) {
            classText.appendText("Must be 18 and older to Join" + "\n");
            return;
        }
        boolean b1 = members.contains(member); //True
        if (!b1) {
            classText.appendText(fname1 + " "+ lname +" is not in the member database" + "\n");
            return;
        }

        int index = members.find2(member);
        Member orgMember = array[index]; // Original Member
        Date exp = new Date(3, 19, 2024);
        if (orgMember.getexp().getYear() < exp.getYear() ||
                (orgMember.getexp().getYear() == exp.getYear() && orgMember.getexp().getMonth() < exp.getMonth()) ||
                (orgMember.getexp().getYear() == exp.getYear() && orgMember.getexp().getMonth() == exp.getMonth() && orgMember.getexp().getDay() <= exp.getDay())) {
            classText.appendText(fname1 +" "+lname1 + " Membership has Expired" + "\n");
            return;
        }
        FitnessClass[] schArray = schedule.getclasses();
        boolean found = false;

        for (FitnessClass fitnessClass : schArray) {
            if ((fitnessClass.getIns().equals(Ins) && fitnessClass.getoffer().equals(offer) && fitnessClass.getLocation().equals(loc))) {
                found = true;
                break;
            }
        }
        if (!found) {
            classText.appendText("Fitness Class not on Schedule" + "\n");
            return;
        }
        if (orgMember.getClass().getSimpleName().equals("Basic") && !(orgMember.getlocation().equals(loc))) {
            classText.appendText("Basic members can only attend the classes held at the home studio." + "\n");
            return;
        }
        Time classTime = null;
        for (int g=0; g< schArray.length; g++ ){
            if (schArray[g].getIns().equals(Ins)
                    && schArray[g].getoffer().equals(offer) && schArray[g].getLocation().equals(loc)){
                classTime = schArray[g].gettime();
            }
        }
        for (int h=0; h< schArray.length; h++ ){
            boolean b8 = schArray[h].getIns().equals(Ins) && schArray[h].getoffer().equals(offer) && schArray[h].getLocation().equals(loc);
            if (schArray[h].gettime().name().equals(classTime.name()) && schArray[h].getMembers().contains(member) && (!b8)){
                classText.appendText("Time Conflict: Member is already in another class being held at the same time " +
                        + schArray[h].gettime().gethour()+":"+
                        schArray[h].gettime().getmin()+ " - " + schArray[h].getIns()+", " +loc +"\n");
                return;
            }
        }



        boolean memberAdded = false;
        for (FitnessClass fitnessClass : schArray) {
            if (fitnessClass.getMembers().contains(member) && fitnessClass.getIns().equals(Ins)
                    && fitnessClass.getoffer().equals(offer) && fitnessClass.getLocation().equals(loc)) {
                classText.appendText(fname1+" "+lname1+ " Member is already added to "+Ins +" "+ fitnessClass.gettime().gethour()+":"+
                        fitnessClass.gettime().getmin()+", " +loc+ ", 0"+
                        fitnessClass.getLocation().getZipCode()+ ", "+fitnessClass.getLocation().getCounty() +"\n");
                return;
            }
        }

        for (FitnessClass fitnessClass : schArray) {
            if ((!memberAdded) && (fitnessClass.getIns().equals(Ins)
                    && fitnessClass.getoffer().equals(offer) && fitnessClass.getLocation().equals(loc))) {
                fitnessClass.getMembers().add(orgMember);
                classText.appendText(fname1+" "+lname1+ " Member is successfully added to " +Ins +" "+ fitnessClass.gettime().gethour()+":"+
                        fitnessClass.gettime().getmin()+", " +loc+ ", 0"+
                        fitnessClass.getLocation().getZipCode()+ ", "+fitnessClass.getLocation().getCounty() +"\n");
                return;
            }
        }
    }
    /**
     * Adds a guest to a fitness class based on the provided information.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
    public void addGuest(ActionEvent e) {
        fname1 = fName1.getText();
        lname1 = lName1.getText();
        LocalDate date = datePicker2.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = date.format(formatter);
        Date dob1 = parseDateOfBirth(dateString);
        Profile profile = new Profile(fname1, lname1, dob1);
        Member member = new Member(profile);
        Offer offer = Offer.valueOf(getOffer().toUpperCase());
        Location loc = Location.valueOf(getHomeLocation2().toUpperCase());
        Instructor Ins = Instructor.valueOf(getInstructor().toUpperCase());
        Member[] array = members.getmembers();
        boolean b1 = members.contains(member); //True
        if (!b1) { //garbar
            classText.appendText(fname1 +" "+ lname1 + "is not in the member database" + "\n");
        }

        int index = members.find2(member);
        Member orgMember = array[index]; // Original Member
        Date exp = new Date(3, 19, 2024);
        if (orgMember.getexp().getYear() < exp.getYear() ||
                (orgMember.getexp().getYear() == exp.getYear() && orgMember.getexp().getMonth() < exp.getMonth()) ||
                (orgMember.getexp().getYear() == exp.getYear() && orgMember.getexp().getMonth() == exp.getMonth() && orgMember.getexp().getDay() <= exp.getDay())) {
            classText.appendText("Membership has Expired" + "\n");
            return;
        }
        FitnessClass[] schArray = schedule.getclasses();
        boolean found = false;

        for (FitnessClass fitnessClass : schArray) {
            if ((fitnessClass.getIns().equals(Ins) && fitnessClass.getoffer().equals(offer) && fitnessClass.getLocation().equals(loc))) {
                found = true;
                break;
            }
        }
        if (!found) {
            classText.appendText("Fitness Class not on Schedule" + "\n");
            return;
        }
        if (orgMember.getClass().getSimpleName().equals("Basic") && !(orgMember.getlocation().equals(loc))) {
            classText.appendText("Basic members can only attend the classes held at the home studio." + "\n");
            return;
        }


        if (orgMember.getClass().getSimpleName().equals("Basic")) {
            classText.appendText("No guest Passes allowed for BASIC" + "\n");
            return;

        }
        if (orgMember.getClass().getSimpleName().equals("Family")) {
            Family family = (Family) orgMember;
            boolean b5 = family.getguest();
            if (!b5) {
                classText.appendText("Number of guest passes remaining is 0" + "\n");
                return;
            }
            boolean b2 = orgMember.getlocation().equals(loc);
            if (!b2) {
                classText.appendText("Home Studio location only for guest" + "\n");
                return;
            }

        }


        if (orgMember.getClass().getSimpleName().equals("Premium")) {
            Premium premium = (Premium) orgMember;
            if (premium.getguestPass() == 0) {
                classText.appendText("Number of guest passes remaining is 0" + "\n");
                return;
            }
            boolean b3 = orgMember.getlocation().equals(loc);
            if (!b3) {
                classText.appendText("Home Studio location only for guest" + "\n");
                return;
            }

        }


        boolean memberAdded = false;
        for (FitnessClass fitnessClass : schArray) {
            if (fitnessClass.getguests().contains(member) && fitnessClass.getIns().equals(Ins)
                    && fitnessClass.getoffer().equals(offer) && fitnessClass.getLocation().equals(loc)) {
                classText.appendText(fname1 +" "+ lname1+" Guest is already added to the class "+Ins +" "+ fitnessClass.gettime().gethour()+":"+
                        fitnessClass.gettime().getmin()+", " +loc+ ",  0"+
                        fitnessClass.getLocation().getZipCode()+ ", "+fitnessClass.getLocation().getCounty() +"\n");
                return;
            } else if ((!memberAdded) && fitnessClass.getIns().equals(Ins)
                    && fitnessClass.getoffer().equals(offer) && fitnessClass.getLocation().equals(loc)) {
                if (orgMember.getClass().getSimpleName().equals("Family")) {
                    Family family = (Family) orgMember;
                    if (family.getguest()) {
                        fitnessClass.getguests().add(orgMember);
                        family.setGuest(false);
                        guestRemaining.appendText("0"+ "\n");
                        classText.appendText(fname1+" " +lname1 +" Guest is successfully added to class "+Ins +" "+ fitnessClass.gettime().gethour()+":"+
                        fitnessClass.gettime().getmin()+", " +loc+ ",  0"+
                                fitnessClass.getLocation().getZipCode()+ ", "+fitnessClass.getLocation().getCounty() +"\n");
                        return;
                    } else{
                        classText.appendText("0 guest passes remaining" + "\n");
                        return;
                    }
                }
                if (orgMember.getClass().getSimpleName().equals("Premium") && ((Premium) orgMember).getguestPass()>=1) {
                    Premium premium = (Premium) orgMember;
                    if (premium.getguestPass()>=1) {
                        fitnessClass.getguests().add(orgMember);
                        premium.setguestPass();
                        String passes = String.valueOf(premium.getguestPass());
                        guestRemaining.appendText(passes+ "\n");
                        classText.appendText(fname1+" "+lname1+" Guest is successfully added to class "+Ins +" "+ fitnessClass.gettime().gethour()+":"+
                                fitnessClass.gettime().getmin()+", " +loc+ ",  0"+
                                fitnessClass.getLocation().getZipCode()+ ", "+fitnessClass.getLocation().getCounty() +"\n");
                        return;
                    } else {
                        classText.appendText("0 guest passes remaining" + "\n");
                        return;

                    }
                }

            }
        }
    }
    /**
     * Prints the attendees of each fitness class along with their membership details.
     * This method is triggered by an ActionEvent when a button is clicked.
     * @param e The ActionEvent triggering the method.
     */
    @FXML
    public void printAttendees(ActionEvent e) {
        FitnessClass[] schArray = schedule.getclasses();

        for (int i = 0; i < schArray.length; i++) {
            demoMembersText.appendText(schArray[i].toString() + "\n");
            demoMembersText.appendText("[Attendees]\n");

            if (schArray[i].getMembers().getmembers() != null && schArray[i].getguests().getmembers() != null) {
                Member[] members = schArray[i].getMembers().getmembers();
                if (members != null) {
                    for (int k = 0; k < members.length; k++) {
                        if (members[k] != null) {
                            demoMembersText.appendText(members[k].toString() + "\n");
                        }
                    }
                }
                // Printing guests if the list is not null
                Member[] guests = schArray[i].getguests().getmembers();
                if (guests != null) {
                    demoMembersText.appendText("GUESTS\n");
                    for (int l = 0; l < guests.length; l++) {
                        if (guests[l] != null) {
                            demoMembersText.appendText(guests[l].toString() + "\n");
                        }
                    }
                }
            }
        }
    }

    /**
     * Parses the date of birth string and returns a Date object.
     *
     * @param dob The date of birth string in the format "MM/dd/yyyy".
     * @return A Date object representing the parsed date of birth.
     */
    private Date parseDateOfBirth(String dob) {
        String[] dobparts = dob.split("/");
        int dobmonth = Integer.parseInt(dobparts[0].replaceAll("^0+(?!$)", ""));
        int dobday = Integer.parseInt(dobparts[1].replaceAll("^0+(?!$)", ""));
        int dobyear = Integer.parseInt(dobparts[2]);
        return new Date(dobmonth, dobday, dobyear);
    }

    /**
     * Checks if the member is 18 years old or older based on their date of birth.
     *
     * @param dob The date of birth of the member.
     * @return True if the member is 18 years old or older, false otherwise.
     */
    private boolean isOldEnough(Date dob) {
        // Check if the member is 18 or older
        Date eighteenYearsAgo = new Date(dob.getMonth(), dob.getDay(), dob.getYear() + 18);
        Date today = new Date(3, 14, 2024); // Current date
        return today.compareTo(eighteenYearsAgo) >= 0;
    }
}

