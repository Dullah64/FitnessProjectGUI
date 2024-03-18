package ProjectFitness;
/**
 * Enum representing different types of fitness class offers.
 * Includes Pilates, Spinning, and Cardio offers.
 *
 * @author Nikhilesh Madduri , Abdullah Qayyum
 *
 */
public enum Offer {
    PILATES,
    SPINNING,
    CARDIO;
    public static boolean classExists(String className) {
        for (Offer offer : Offer.values()) {
            if (offer.name().equalsIgnoreCase(className)) {
                return true; // Class exists
            }
        }
        return false; // Class does not exist
    }
}


