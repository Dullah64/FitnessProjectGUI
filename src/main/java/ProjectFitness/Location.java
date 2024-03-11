package ProjectFitness;
/**
 * The Location enum represents different locations of a fitness club.
 * Each location has a name, ZIP code, and county.
 *
 * @author Nikhilesh , Abdullah Qayyum
 */
public enum Location {
    BRIDGEWATER("08807", "Somerset County"),
    EDISON("08837", "Middlesex County"),
    FRANKLIN("08873", "Somerset County"),
    PISCATAWAY("08854", "Middlesex County"),
    SOMERVILLE("08876", "Somerset County");

    private final String zipCode;
    private final String county;

    /**
     * Constructs a Location enum with the specified name, ZIP code, and county.
     *
     * @param zipCode The ZIP code of the location.
     * @param county  The county of the location.
     */
    Location(String zipCode, String county) {
        this.zipCode = zipCode;
        this.county = county;
    }

    /**
     * Gets the ZIP code of the location.
     *
     * @return The ZIP code of the location.
     */
    public int getZipCode() {
        return Integer.parseInt(this.zipCode);
    }

    /**
     * Gets the county of the location.
     *
     * @return The county of the location.
     */
    public String getCounty() {
        return county;
    }

    public static boolean existsByName(String name) {
        for (Location location : Location.values()) {
            if (location.name().equalsIgnoreCase(name)) {
                return true; // Location with the given name exists
            }
        }
        return false; // Location with the given name does not exi
    }
}



