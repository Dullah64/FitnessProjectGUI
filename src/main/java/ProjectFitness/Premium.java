package ProjectFitness;
/**
 * Represents a premium membership at a fitness club.
 * Extends the {@link Member} class.
 *
 * @author Nikhilesh Madduri , Abdullah Qayyum
 */
public class Premium extends Member {
    private int guestPass;
    public int getguestPass(){
        return this.guestPass;
    }
    public void setguestPass(){
        this.guestPass =-1;
    }

public static final int NUMBER_OF_GUESTS = 3;
    /**
     * Monthly fee for premium membership.
     */
    public static final double MONTHLY_FEE_PREMIUM = 59.99;

    /**
     * Number of months billed annually.
     */
    public static final int BILLED_ANNUALLY = 12;

    /**
     * Number of months for the one-month free trial.
     */
    public static final int ONE_MONTH_FREE_TRIAL = 1;
    /**
     * Constructs a Premium membership with the given profile, expiration date, home studio, and guest pass.
     *
     * @param profile the profile of the member
     * @param expire the expiration date of the membership
     * @param homeStudio the location of the home studio
     */
    public Premium(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);

    }

    /**
     * Constructs a premium member with a profile and sets the number of guest passes.
     * @param profile The profile of the premium member.
     */
    public Premium(Profile profile) {
        super(profile);
        this.guestPass = NUMBER_OF_GUESTS;
    }

    /**
     * Calculates the bill for the premium membership.
     *
     * @return the bill amount
     */
    @Override
    public double bill() {
        return MONTHLY_FEE_PREMIUM * (BILLED_ANNUALLY - ONE_MONTH_FREE_TRIAL); // return the next due amount
    }
    @Override
    /**
     * Checks for whether the expiration date is within the valid range (max 1/2024 and before)
     * If so, it will return if the Premium member's membership expired
     * if not, when it will expire
     * @return Complete information of the Premium member depending on Expiration date
     */
    public String toString() {
        if (getexp().getYear() == 2024 && getexp().getMonth() == 1 || getexp().getYear() <= 2023) {
        return
                getProfile().getfname() + " " + getProfile().getlname() + " : " + getProfile().getdob() +
                        ", Membership expired " + getexp() +
                        ", Location: " + getlocation().name() + ", 0" + getlocation().getZipCode() + ", " + getlocation().getCounty() +
                        ", " + "(Premium)" + " guest-pass remaining: " +" not eligible";
    }
        return
                getProfile().getfname() + " " +getProfile().getlname() + " : " + getProfile().getdob() +
                        ", Membership expires " + getexp() +
                        ", Location: " + getlocation().name() + ", 0" + getlocation().getZipCode() + ", " + getlocation().getCounty() +
                        ", " + "(Premium)" + " guest-pass remaining: " + this.guestPass;
}
}
