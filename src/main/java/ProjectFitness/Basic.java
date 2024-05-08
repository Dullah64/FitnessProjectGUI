package ProjectFitness;
/**
 * Represents a basic member at a fitness club.
 * Extends the {@link Member} class.
 *
 * @author Abdullah Qayyum
 */
public class Basic extends Member {
    private int numClasses;

    /**
     * Monthly fee for basic membership.
     */
    public static final double MONTHLY_FEE_BASIC = 39.99;

    /**
     * Fee for extra classes beyond the included number of classes.
     */
    public static final double EXTRA_CLASS_FEE = 10;

    /**
     * Number of classes included without extra charge.
     */
    public static final int NUMBER_OF_CLASSES = 4;

    /**
     * Constructs a new Basic object with the given profile, expiration date, and home studio location.
     *
     * @param profile the profile associated with the Basic membership
     * @param expire the expiration date of the Basic membership
     * @param homeStudio the location of the home studio for the Basic membership
     */
    public Basic(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
        this.numClasses = 0;
    }

    /**
     * Retrieves the number of classes associated with this membership.
     *
     * @return the number of classes associated with this membership
     */
    public int getnumclasses() {
        return this.numClasses;
    }

    /**
     * Calculates the bill for the basic member.
     *
     * @return the bill amount
     */
    @Override
    public double bill() {
        if (this.numClasses <= NUMBER_OF_CLASSES) {
            return MONTHLY_FEE_BASIC;
        } else {
            double extraClasses = this.numClasses - NUMBER_OF_CLASSES;
            return MONTHLY_FEE_BASIC + EXTRA_CLASS_FEE * extraClasses;
        }
    }

    /**
     * Returns a string representation of the Basic membership object.
     *
     * @return a string containing details about the Basic membership, including member's name, date of birth, membership expiration date,
     * location, and the number of classes attended
     */
    @Override
    public String toString() {
        return getProfile().getfname() + " " + getProfile().getlname() + " : " +
                getProfile().getdob() + ", Membership expires " + getexp() +
                ", Location: " + getlocation().name() + ", 0" + getlocation().getZipCode() +
                ", " + getlocation().getCounty() + ", (Basic) number of classes attended: " +
                this.numClasses;
        }

    }

