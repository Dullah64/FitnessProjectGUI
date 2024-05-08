package ProjectFitness;
import java.util.Objects;

/**
 * The Member class represents a fitness club member.
 * It contains information about the member's profile, membership expiration date, and home studio location.
 *
 * @author Abdullah Qayyum
 */
public class Member implements Comparable<Member> {
    private Profile profile;
    private Date expire;
    private Location homeStudio;

    /**
     * Constructs a Member object with the specified profile, expiration date, and home studio location.
     *
     * @param profile     The profile of the member.
     * @param expire      The expiration date of the membership.
     * @param homeStudio  The home studio location of the member.
     */
    public Member(Profile profile, Date expire, Location homeStudio) {
        this.profile = profile;
        this.expire = expire;
        this.homeStudio = homeStudio;
    }

    /**
     * Constructs a member with a profile and a home studio location.
     * @param profile The profile of the member.
     * @param homeStudio The home studio location of the member.
     */
    public Member(Profile profile, Location homeStudio) {
        this.profile = profile;
        this.homeStudio = homeStudio;
    }

    /**
     * Constructs a member with a profile only.
     * @param profile The profile of the member.
     */
    public Member(Profile profile) { // Overloading...
        this.profile = profile;
    }

    /**
     * Gets the home studio location of the member.
     * @return The home studio location.
     */
    public Location getlocation() {
        return this.homeStudio;
    }

    /**
     * Gets the profile of the member.
     * @return The profile.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Gets the expiration date of the membership.
     * @return The expiration date.
     */
    public Date getexp() {
        return this.expire;
    }


    /**
     * Calculates the bill amount for the member.
     * Subclasses should override this method to provide specific billing calculations.
     *
     * @return The bill amount for the member.
     */
    public double bill() {
        return 0.0; // return the next due amount
    }


    /**
     * Compares this member with the specified member for order.
     * Members are compared based on their profile, expiration date, and home studio location.
     *
     * @param otherMember The member to be compared.
     * @return A negative integer, zero, or a positive integer as this member is less than, equal to,
     *         or greater than the specified member.
     */
    @Override
    public int compareTo(Member otherMember) {
        if (otherMember == null)
            return 1; // Null check

        // Compare all three attributes for equality
        boolean profilesEqual = Objects.equals(this.profile, otherMember.profile);
       // boolean expiresEqual = Objects.equals(this.expire, otherMember.expire);
        boolean homeStudiosEqual = Objects.equals(this.homeStudio, otherMember.homeStudio);

        if (profilesEqual &&  homeStudiosEqual) {
            return 0;
        }
        return -1;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two members are considered equal if they have the same profile, expiration date, and home studio location.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member member) {
            return member.profile.equals(this.profile);
        }
        return false;
    }

    /**
     * Returns a string representation of the member.
     *
     * @return A string representation of the member.
     */
    @Override
   public String toString() {
        return "Member{" +
                profile.getfname() + " " + profile.getlname() + " : " + profile.getdob() +
                ", Membership expires " + expire.toString() +
               ", Location: " + homeStudio.name() + ", 0" + homeStudio.getZipCode() + ", " + homeStudio.getCounty() +
                '}';
    }
}

