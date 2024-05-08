package ProjectFitness;
/**
 * Represents a family membership at a fitness club.
 * Extends the {@link Member} class.
 *
 * @author Abdullah Qayyum
 */
public class Family extends Member {
    private boolean guest;

    /**
     * Retrieves the guest status of the object.
     *
     * @return true if the object is a guest, false otherwise
     */
    public boolean getguest(){
        return this.guest;
    }

    public static final double BILLED_QUARTERLY_FAMILY = 3;
    public static final double MONTHLY_FEE = 49.99;
    public static final int NUMBER_GUEST_PASS = 1;

    /**
     * Constructs a Family membership with the given profile, expiration date, home studio, and guest status.
     *
     * @param profile the profile of the member
     * @param expire the expiration date of the membership
     * @param homeStudio the location of the home studio
     */
    public Family(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
        this.guest = true;
    }

    /**
     * Sets the guest status of the object.
     *
     * @param b1 the boolean value to set as the guest status
     */
    public void setGuest(boolean b1){
        this.guest = b1;

    }

    /**
     * Calculates the bill for the family membership.
     *
     * @return the bill amount
     */
    @Override
    public double bill() {
        return BILLED_QUARTERLY_FAMILY * MONTHLY_FEE; // return the next due amount
    }

    /**
     * Returns a string representation of the membership object.
     *
     * @return a string containing details about the membership, including member's name, date of birth, membership expiration date,
     * location, membership type, and remaining guest passes
     */
    @Override
    public String toString() {
        if (getexp().getYear() == 2024 && getexp().getMonth() == 1 || getexp().getYear() <= 2023) {
            return
                    getProfile().getfname() + " "+ getProfile().getlname() + " : " + getProfile().getdob() +
                            ", Membership expired " + getexp() +
                            ", Location: " + getlocation().name() + ", 0" + getlocation().getZipCode() + ", " + getlocation().getCounty() +
                            ", " + "(Family)" + " guest-pass remaining: " +" not eligible";
        } else {
            if(guest) {
                return

                        getProfile().getfname() +" "+ getProfile().getlname() + " : " + getProfile().getdob() +
                                ", Membership expires " + getexp() +
                                ", Location: " + getlocation().name() + ", 0" + getlocation().getZipCode() + ", " + getlocation().getCounty() +
                                ", " + "(Family)" + " guest-pass remaining: " + NUMBER_GUEST_PASS;

            } else {
                return
                getProfile().getfname() + " " + getProfile().getlname() + " : " + getProfile().getdob() +
                        ", Membership expires " + getexp() +
                        ", Location: " + getlocation().name() + ", 0" + getlocation().getZipCode() + ", " + getlocation().getCounty() +
                        ", " + "(Family)" + " guest-pass remaining: " + "0";


            }
        }
    }
}
