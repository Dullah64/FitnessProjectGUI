package ProjectFitness;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class StudioManager {

    public void run() throws IOException {
        File file = new File("R:\\Fitness Club Project\\FitnessProject\\src\\ProjectFitness\\memberList.txt");
        MemberList members = new MemberList();
        System.out.println("List of members loaded");
        members.load(file);
        System.out.println("- End of list\n");

        System.out.println("Fitness classes loaded");
        File file2 = new File("R:\\Fitness Club Project\\FitnessProject\\src\\ProjectFitness\\classSchedule.txt");
        Schedule schedule = new Schedule();
        schedule.load(file2);
        System.out.println("- End of list\n");

        System.out.println("Studio Manager is up and running....");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String memType = parts[0];

            switch (memType) {
                case "AB":
                    processABMembership(parts, members);
                    break;
                case "AP":
                    processAPMembership(parts, members);
                    break;
                case "AF":
                    processAFMembership(parts, members);
                    break;
                case "C":
                    processCancelMembership(parts, members);
                    break;
                case "PM":
                    members.printByMember();
                    break;
                case "PC":
                    members.printByCounty();
                    break;
                case "PF":
                    members.printFees();
                    break;
                case "S":
                    System.out.println("Did not Finish");
                    //displayClassSchedule(schedule);
                    break;
                case "R":
                    registerForMember(parts, members, schedule);
                    break;
                case "U":
                    unregisterFromMember(parts, schedule);
                    break;
                case "RG":
                    registerAsGuest(parts, members, schedule);
                    break;
                case "UG":
                    unregisterGuest(parts, schedule);
                    break;
                case "Q":
                    return; // Terminate the program
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private void processABMembership(String[] parts, MemberList members) {
        if (parts.length != 5) {
            System.out.println("Not Enough Tokens");
            return;
        }
        String fname = parts[1];
        String lname = parts[2];
        String dob = parts[3];
        String loc = parts[4];
        Date dob1 = parseDateOfBirth(dob);
        if (dob1 == null || !dob1.isValid()) {
            System.out.println("DOB " + dob1 + ": invalid calendar date!");
            return;
        }
        if (!isOldEnough(dob1)) {
            System.out.println("DOB " + dob1 + ": must be 18 or older to join!");
            return;
        }
        Profile profile = new Profile(fname, lname, dob1);
        if (!Location.existsByName(loc.toUpperCase().toUpperCase())) {
            System.out.println("Invalid studio location");
            return;
        }
        Location location = Location.valueOf(loc.toUpperCase());
        Date expire = new Date(3, 26, 2024); // Example expiration date
        Basic basic1 = new Basic(profile, expire, location);

        if (members.contains(basic1)) {
            System.out.println(fname + " " + lname + " is already in the member database!");
        } else {
            members.add(basic1);
            System.out.println(basic1.getProfile().getfname() + " " + basic1.getProfile().getlname() + " added");
        }
    }

    // Implement other private methods like processAPMembership, processAFMembership, processCancelMembership, registerForMember, unregisterFromMember, registerAsGuest, unregisterGuest

    private boolean isOldEnough(Date dob) {
        // Check if the member is 18 or older
        Date eighteenYearsAgo = new Date(dob.getMonth(), dob.getDay(), dob.getYear() + 18);
        Date today = new Date(2, 25, 2024); // Current date
        return today.compareTo(eighteenYearsAgo) >= 0;
    }

    private Date parseDateOfBirth(String dob) {
        String[] dobparts = dob.split("/");
        int dobmonth = Integer.parseInt(dobparts[0]);
        int dobday = Integer.parseInt(dobparts[1]);
        int dobyear = Integer.parseInt(dobparts[2]);
        return new Date(dobmonth, dobday, dobyear);
    }

    private void processAPMembership(String[] parts, MemberList members) {
        // Implement processAPMembership method based on your requirements
        if (parts.length != 5) {
            System.out.println("Not Enough Tokens");
            return;
        }
        String fname = parts[1];
        String lname = parts[2];
        String dob = parts[3];
        String loc = parts[4];
        if (dob.contains("x")){
            System.out.println("DOB " + dob + " is Invalid");
            return;
        }
        Date dob1 = parseDateOfBirth(dob);
        if (dob1 == null || !dob1.isValid()) {
            System.out.println("DOB " + dob1 + ": invalid calendar date!");
            return;
        }
        if (!isOldEnough(dob1)) {
            System.out.println("DOB " + dob1 + ": must be 18 or older to join!");
            return;
        }
        Profile profile = new Profile(fname, lname, dob1);
        if (!Location.existsByName(loc.toUpperCase())) {
            System.out.println("Invalid studio location");
            return;
        }
        Location location = Location.valueOf(loc.toUpperCase());
        Date expire = new Date(3, 26, 2024); // Example expiration date
        Premium premium = new Premium(profile, expire, location);

        if (members.contains(premium)) {
            System.out.println(fname + " " + lname + " is already in the member database!");
        } else {
            members.add(premium);
            System.out.println(premium.getProfile().getfname() + " " + premium.getProfile().getlname() + " added");
        }
    }

    private void processAFMembership(String[] parts, MemberList members) {
        if (parts.length != 5) {
            System.out.println("Not Enough Tokens");
            return;
        }
        String fname = parts[1];
        String lname = parts[2];
        String dob = parts[3];
        String loc = parts[4];
        if (dob.contains("x")){
            System.out.println("DOB " + dob + " is Invalid");
            return;
        }
        Date dob1 = parseDateOfBirth(dob);

        if (dob1 == null || !dob1.isValid()) {
            System.out.println("DOB " + dob1 + ": invalid calendar date!");
            return;
        }
        if (!isOldEnough(dob1)) {
            System.out.println("DOB " + dob1 + ": must be 18 or older to join!");
            return;
        }
        Profile profile = new Profile(fname, lname, dob1);

        if (!Location.existsByName(loc.toUpperCase())) {
            System.out.println("Invalid studio location");
            return;
        }
        Location location = Location.valueOf(loc.toUpperCase());
        Date expire = new Date(3, 26, 2024); // Example expiration date
        Family family = new Family(profile, expire, location);

        if (members.contains(family)) {
            System.out.println(fname + " " + lname + " is already in the member database!");
        } else {
            members.add(family);
            System.out.println(family.getProfile().getfname() + " " + family.getProfile().getlname() + " added");
        }
    }


    private void processCancelMembership(String[] parts, MemberList members) {
        // Implement processCancelMembership method based on your requirements
        if (parts.length != 4) {
            System.out.println("Not Enough Tokens");
            return;
        } else {
            String fname = parts[1];
            String lname = parts[2];
            String dob = parts[3];
            if (dob.contains("x")){
                System.out.println("DOB " + dob + " is Invalid");
                return;
            }
            Date dob1 = parseDateOfBirth(dob);

            Profile profile = new Profile(fname, lname, dob1);
            Member member = new Member(profile);
            if (members.remove(member)) {
                System.out.println(fname + " " + lname + " removed.");
            } else {
                System.out.println(fname + " " + lname + " not in database.");
            }
        }
    }

    private void registerForMember(String[] parts, MemberList members, Schedule schedule) {
        // Implement registerForMember method based on your requirements
        if (parts.length != 7) {
            System.out.println("Not Enough Tokens");
            return;
        } else {
            String clas = parts[1];
            String fnameins = parts[2];
            String locIns = parts[3];
            String fname = parts[4];
            String lname = parts[5];
            String dob = parts[6];
            Date dob1 = parseDateOfBirth(dob);
            Profile pro = new Profile(fname, lname, dob1);
            Member member = new Member(pro);
            FitnessClass[] schArray = schedule.getclasses();

            for (int i = 0; i <schArray.length; i++) {
                if (schArray[i].getoffer().name().equals(clas.toUpperCase()) &&
                        schArray[i].getIns().name().equals(fnameins.toUpperCase())) {
                    if (schArray[i].getmembers().contains(member)) {
                        System.out.println(fname + " " + lname + " is already registered for " +
                                clas.toUpperCase() + " with " + fnameins.toUpperCase());
                        return;
                    } else {
                        schArray[i].getmembers().add(member);
                        System.out.println(fname + " " + lname + " is registered for " + clas.toUpperCase() +
                                " with " + fnameins.toUpperCase() + " at " + locIns.toUpperCase());
                        return;
                    }
                }
            }
            System.out.println("Fitness class not on class schedule");
        }
    }

    private void unregisterFromMember(String[] parts, Schedule schedule) {
        // Implement unregisterFromMember method based on your requirements
        if (parts.length != 4) {
            System.out.println("Not Enough Tokens");
            return;
        } else {
            String fname = parts[1];
            String lname = parts[2];
            String dob = parts[3];
            Date dob1 = parseDateOfBirth(dob);
            Profile pro = new Profile(fname, lname, dob1);
            Member member = new Member(pro);
            FitnessClass[] schArray = schedule.getclasses();

            for (int i = 0; i < schArray.length; i++) {
                if (schArray[i].getmembers().contains(member)) {
                    schArray[i].getmembers().remove(member);
                    System.out.println(fname + " " + lname + " is unregistered from the class");
                    return;
                }
            }
            System.out.println(fname + " " + lname + " is not registered for any classes");
        }
    }

    private void registerAsGuest(String[] parts, MemberList members, Schedule schedule) {
        // Implement registerAsGuest method based on your requirements
        if (parts.length != 7) {
            System.out.println("Not Enough Tokens");
            return;
        } else {
            String clas = parts[1];
            String fnameins = parts[2];
            String locIns = parts[3];
            String fname = parts[4];
            String lname = parts[5];
            String dob = parts[6];
            Date dob1 = parseDateOfBirth(dob);
            Profile pro = new Profile(fname, lname, dob1);
            Member member = new Member(pro);
            FitnessClass[] schArray = schedule.getclasses();

            for (int i = 0; i <schArray.length; i++) {
                if (schArray[i].getoffer().name().equals(clas.toUpperCase()) &&
                        schArray[i].getIns().name().equals(fnameins.toUpperCase())) {
                    if (schArray[i].getguests().contains(member)) {
                        System.out.println(fname + " " + lname + " is already registered as a guest for " +
                                clas.toUpperCase() + " with " + fnameins.toUpperCase());
                        return;
                    } else {
                        schArray[i].getguests().add(member);
                        System.out.println(fname + " " + lname + " is registered as a guest for " + clas.toUpperCase() +
                                " with " + fnameins.toUpperCase() + " at " + locIns.toUpperCase());
                        return;
                    }
                }
            }
            System.out.println("Fitness class not on class schedule");
        }
    }

    private void unregisterGuest(String[] parts, Schedule schedule) {
        // Implement unregisterGuest method based on your requirements
        if (parts.length != 7) {
            System.out.println("Not Enough Tokens");
            return;
        } else {
            String clas = parts[1];
            String fnameins = parts[2];
            String locIns = parts[3];
            String fname = parts[4];
            String lname = parts[5];
            String dob = parts[6];
            Date dob1 = parseDateOfBirth(dob);
            Profile pro = new Profile(fname, lname, dob1);
            Member member = new Member(pro);
            FitnessClass[] schArray = schedule.getclasses();
            Location loc2 = Location.valueOf(locIns.toUpperCase());

            for (int i = 0; i <schArray.length; i++) {
                if (schArray[i].getoffer().name().equals(clas.toUpperCase()) &&
                        schArray[i].getIns().name().equals(fnameins.toUpperCase())) {
                    if (schArray[i].getguests().contains(member)) {
                        schArray[i].getguests().remove(member);
                        System.out.println(fname + " " + lname + " is unregistered as a guest for " +
                                clas.toUpperCase() + " with " + fnameins.toUpperCase());
                        return;
                    } else {
                        System.out.println(fname + " " + lname + " is not registered as a guest for " +
                                clas.toUpperCase() + " with " + fnameins.toUpperCase());
                        return;
                    }
                }
            }
            System.out.println("Fitness class not on class schedule");
        }
    }
    private void displayClassSchedule(Schedule schedule) {
        FitnessClass[] schArray = schedule.getclasses();

        System.out.println("Class Schedule with Current Attendees:");
        for (FitnessClass fitnessClass : schArray) {
            System.out.println("Class Name: " + fitnessClass.getoffer().name());
            System.out.println("Instructor: " + fitnessClass.getIns().name());
            System.out.println("Time: " + fitnessClass.gettime().gethour() + ":" +
                    fitnessClass.gettime().getmin());
            System.out.println("Attendees:");
            displayMembers(fitnessClass.getmembers(), false);
            displayMembers(fitnessClass.getguests(), true);
            System.out.println();
        }
    }

    private void displayMembers(MemberList members, boolean isGuest) {
        if (members != null && members.getsize() > 0) {
            for (int i = 0; i < members.getsize(); i++) {
                String memberType = isGuest ? " (Guest)" : "";
                System.out.println("- " + members.getmembers()[i].getProfile().getfname() + " " +
                        members.getmembers()[i].getProfile().getlname() + memberType);
            }
        }
    }

}