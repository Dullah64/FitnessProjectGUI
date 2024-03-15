package ProjectFitness;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
/**
 * The MemberList class represents a fitness club member.
 * It contains information about the member's profile, membership expiration date, and home studio location.
 *
 * @author Nikhilesh Madduri , Abdullah Qayyum
 */
public class MemberList {
    private Member[] members; //holds Basic, Family, or Premium objects
    private int size; //number of objects in the array
    private static final int INITIAL_ARRAY_SIZE = 4;

    /**
     * Constructs a member list with an initial size and initializes the array to store members.
     */
    public MemberList() {
        this.size = INITIAL_ARRAY_SIZE;
        this.members = new Member[this.size];
    }

    /**
     * Gets the current size of the member list.
     * @return The current size of the member list.
     */
    public int getsize(){
        return this.size;
    }

    /**
     * Gets the array of members.
     * @return The array of members.
     */
    public Member[] getmembers(){
        return members;
    }

    /**
     * Finds the index of the specified member in the member list.
     * @param member The member to find.
     * @return The index of the member if found, otherwise -1.
     */
    private int find(Member member) {
        if (this.members[0]== null){
            return -1;
        }
        for (int i = 0; i < this.size - 1; i++) {
            if (this.members[i].equals(member)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the specified member in the member list.
     * @param member The member to find.
     * @return The index of the member if found, otherwise -1.
     */
    private int find2(Member member) {
        for (int i = 0; i < this.size; i++) {
            if (this.members[i] != null && this.members[i].equals(member)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases the capacity of the member list by a specified amount.
     */
    private void grow() {
        Member[] tempArray = new Member[this.size];
        for (int i = 0; i < this.size; i++) {
            tempArray[i] = this.members[i];
        }
        this.members = new Member[this.size + INITIAL_ARRAY_SIZE];
        for (int i = 0; i < tempArray.length; i++) {
            this.members[i] = tempArray[i];
        }
        this.size += INITIAL_ARRAY_SIZE;
    }

    /**
     * Checks if the member list contains the specified member.
     * @param member The member to check.
     * @return true if the member is found in the list, otherwise false.
     */
    public boolean contains(Member member) {
        for (int i = 0; i < this.size; i++) {
            if (this.members[i] != null && this.members[i].equals(member)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a member to the end of the member list.
     * @param member The member to add.
     * @return true if the member is successfully added, false if the member already exists or the list is full.
     */
    public boolean add(Member member) { //add to end of array
        for (int i = 0; i < this.size; i++) {
            if (member.equals(this.members[i])) {
                return false;
            }
        }
        if (this.members[this.size - 1] == null) {
            for (int j = 0; j < this.size; j++) {
                if (this.members[j] == null) {
                    this.members[j] = member;
                    return true;
                }
            }
        } else {
            grow();
            this.members[this.size - INITIAL_ARRAY_SIZE] = member;
            return true;
        }
        return false;
    }

    /**
     * Removes the specified member from the member list by shifting the elements up.
     * @param member The member to remove.
     * @return true if the member is successfully removed, false if the member is not found.
     */
    public boolean remove(Member member) { // //shift up to remove
        int memberIndex = find2(member);
        if (memberIndex != -1) {
            this.members[memberIndex] = null;
            while (memberIndex < this.size - 1 && this.members[memberIndex + 1] != null) {
                this.members[memberIndex] = this.members[memberIndex + 1];
                memberIndex++;
            }
            return true;
        }
        return false;
    }


    /**
     * Loads member data from a text file.
     * @param file The file to load data from.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void load(File file) throws IOException { //from the text file
        int count = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String memType = parts[0]; // Assuming membership type is in parts[1]
            String fname = parts[1];
            String lname = parts[2];
            String dob = parts[3];
            String [] dobparts = dob.split("/");
            int dobmonth = Integer.parseInt(dobparts[0]);
            int dobday = Integer.parseInt(dobparts[1]);
            int dobyear = Integer.parseInt(dobparts[2]);
            String exp = parts[4];
            String [] expparts = exp.split("/");
            int expmonth = Integer.parseInt(expparts[0]);
            int expday = Integer.parseInt(expparts[1]);
            int expyear = Integer.parseInt(expparts[2]);
            String loc = parts[5];

            if (memType.equals("B")){
                Date dob1 = new Date(dobmonth,dobday,dobyear);
                Date expire = new Date(expmonth,expday,expyear);
                Profile profile = new Profile(fname,lname, dob1);
                Location location = Location.valueOf(loc);
                Basic basic1 = new  Basic(profile,expire,location);
                add(basic1);

            }
            if (memType.equals("P")){
                Date dob1 = new Date(dobmonth,dobday,dobyear);
                Date expire = new Date(expmonth,expday,expyear);
                Profile profile = new Profile(fname,lname, dob1);
                Location location = Location.valueOf(loc);
                Premium pre1 = new Premium(profile,expire,location);
                add(pre1);
            }
            if (memType.equals("F")){
                Date dob1 = new Date(dobmonth,dobday,dobyear);
                Date expire = new Date(expmonth,expday,expyear);
                Profile profile = new Profile(fname,lname, dob1);
                Location location = Location.valueOf(loc);
                Family family1 = new Family(profile,expire,location);
                add(family1);

            }
            count = count + 1;
        }
        for (int i = 0; i <this.size; i++){
            System.out.println(this.members[i]);        }
    }


    /**
     * Prints the members sorted by county and then by zip code.
     */
    public void printByCounty() {  //sort by county then zip code
        if (this.members[0] == null) {
            System.out.println("Collections is empty");
            return;
        }
        for (int i = 1; i < this.size; i++) {
            for (int j = i; j > 0; j--) {
                if (this.members[j] != null && this.members[j].getlocation().getCounty().compareTo(this.members[j - 1].getlocation().getCounty()) < 0) {
                    Member temp = this.members[j - 1];
                    this.members[j - 1] = this.members[j];
                    this.members[j] = temp;
                } else if (this.members[j] != null && this.members[j].getlocation().getCounty().compareTo(this.members[j - 1].getlocation().getCounty()) == 0) {
                    if (this.members[j].getlocation().getZipCode() < this.members[j].getlocation().getZipCode()) {
                        Member temp = this.members[j - 1];
                        this.members[j - 1] = this.members[j];
                        this.members[j] = temp;
                    }
                }
            }
        }

        System.out.println("*Members sorted by county/zip code*");
        for (int i = 0; i < this.size; i++) {
            if (this.members[i] != null) {
                System.out.println(this.members[i]);
            }

        }
    }

    /**
     * Prints the members sorted by profile
     */
    public void printByMember() { // Sort by member profile
        if (this.size == 0) {
            System.out.println("Collections is empty");
            return;
        }

        for (int i = 1; i < this.size; i++) {
            for (int j = i; j > 0 && this.members[j] != null && this.members[j - 1] != null; j--) {
                if (this.members[j].getProfile().compareTo(this.members[j - 1].getProfile()) < 0) {
                    Member temp = this.members[j - 1];
                    this.members[j - 1] = this.members[j];
                    this.members[j] = temp;
                }
            }
        }

        System.out.println("*Members sorted by profile code*");
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.members[i] != null) {
                System.out.println(this.members[i]);
            }
        }
    }

    /**
     * Prints the members fees as is with the next due amount
     */
    public void printFees() { //print the array as is with the next due amounts
        for (int i = 0; i < this.size; i++){
            if (this.members[i]== null){
                break;
            }
            System.out.println(this.members[i]+ " " + "[next due: $" + this.members[i].bill() + "]");
        }

    }

}
