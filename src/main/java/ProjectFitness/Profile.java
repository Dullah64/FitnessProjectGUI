package ProjectFitness;

/**
 * The Profile class represents the profile of a fitness club member.
 * It contains information about the member's first name, last name, and date of birth.
 *
 * @author Abdullah Qayyum
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructs a Profile object with the specified first name, last name, and date of birth.
     *
     * @param firstName The first name of the member.
     * @param lastName  The last name of the member.
     * @param dob       The date of birth of the member.
     */
    // Abdullah Qayyum
    public Profile(String firstName, String lastName, Date dob) {
        this.fname = firstName;
        this.lname = lastName;
        this.dob = dob;
    }
    public Profile(String firstName, String lastName) {
        this.fname = firstName;
        this.lname = lastName;
    }
    public Profile(String firstName) {
        this.fname = firstName;
    }

    /**
     * Gets the first name of the member.
     *
     * @return The first name of the member.
     */
    public String getfname() {
        return this.fname;
    }

    /**
     * Gets the last name of the member.
     *
     * @return The last name of the member.
     */
    public String getlname() {
        return this.lname;
    }

    /**
     * Gets the date of birth of the member.
     *
     * @return The date of birth of the member.
     */
    public Date getdob() {
        return this.dob;
    }

    /**
     * Returns a string representation of the profile.
     *
     * @return A string representation of the profile.
     */
    @Override
    public String toString() {
        return "Profile{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dob=" + dob +
                '}';
    }

    /**
     * Compares this profile with the specified profile for order.
     * Profiles are compared based on first name, last name, and date of birth.
     *
     * @param pro The profile to be compared.
     * @return A negative integer, zero, or a positive integer as this profile is less than, equal to,
     *         or greater than the specified profile.
     */
    @Override
    public int compareTo(Profile pro) {
        if (pro.lname.compareTo(this.lname) != 0) {
            return pro.lname.compareTo(this.lname);
        }
        if (pro.fname.compareTo(this.fname) != 0) {
            return pro.fname.compareTo(this.fname);
        }
        return pro.dob.compareTo(this.getdob());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two profiles are considered equal if they have the same first name, last name, and date of birth.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile pro = (Profile) obj;
            return pro.getfname().equals(this.fname) &&
                    pro.getlname().equals(this.lname) &&
                   pro.getdob().equals(this.dob);
        }
        return false;
    }

    /**
     * Gets the first name of the member.
     *
     * @return The first name of the member.
     */
    public String getFname() {
        return fname;
    }

    /**
     * Gets the last name of the member.
     *
     * @return The last name of the member.
     */
    public String getLname() {
        return lname;
    }

    /**
     * Gets the date of birth of the member.
     *
     * @return The date of birth of the member.
     */
    public Date getDob() {
        return dob;
    }
    /**
     * parses the date of birth value
     * @param dob of birth String value
     * @return The parsed values added into a new Date object
     */
    private Date parseDateOfBirth(String dob) {
        String[] dobparts = dob.split("/");
        int dobmonth = Integer.parseInt(dobparts[0]);
        int dobday = Integer.parseInt(dobparts[1]);
        int dobyear = Integer.parseInt(dobparts[2]);
        return new Date(dobmonth, dobday, dobyear);
    }
}
