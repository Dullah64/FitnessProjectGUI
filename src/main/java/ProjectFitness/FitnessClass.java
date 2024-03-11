package ProjectFitness;
/**
 * Represents a fitness class offered by a gym.
 * Provides constructors for creating classes with all attributes or essential attributes only.
 *
 * @author Nikhilesh Maddurin , Abdullah Qayyum
 *
 */
public class FitnessClass {
    private Offer classInfo;
    private Instructor instructor;
    private Location studio;
    private Time time;
    private MemberList members;
    private MemberList guests;

    /**
     * Retrieves the offer associated with the class session.
     *
     * @return the offer associated with the class session
     */
    public Offer getoffer() {
        return this.classInfo;
    }

    /**
     * Retrieves the instructor of the class session.
     *
     * @return the instructor of the class session
     */
    public Instructor getIns() {
        return instructor;
    }

    /**
     * Retrieves the time of the class session.
     *
     * @return the time of the class session
     */
    public Time gettime() {
        return this.time;
    }

    /**
     * Retrieves the location of the class session.
     *
     * @return the location of the class session
     */
    public Location getLocation() {
        return this.studio;
    }

    /**
     * Retrieves the list of members attending the class session.
     *
     * @return the list of members attending the class session
     */
    public MemberList getmembers() {
        return this.members;
    }

    /**
     * Retrieves the list of guests attending the class session.
     *
     * @return the list of guests attending the class session
     */
    public MemberList getguests() {
        return this.members;
    }

    /**
     * Constructs a fitness class with essential attributes only.
     *
     * @param classInfo  the offer type of the class
     * @param instructor the instructor teaching the class
     * @param time       the time of the class
     * @param studio     the location of the class
     */
    public FitnessClass(Offer classInfo, Instructor instructor, Time time , Location studio) {
        this.classInfo = classInfo;
        this.instructor = instructor;
        this.studio = studio;
        this.time = time;
        this.members = new MemberList();
        this.guests = new MemberList();
    }


    /**
     * Returns a string representation of the class session.
     *
     * @return a string containing details about the class session, including class information, instructor name,
     * time (hour and minute), and studio name
     */
    @Override
    public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(classInfo).append(" - ").append(instructor.name()).append(", ")
                    .append(time.gethour()).append(": ").append(time.getmin()).append(", ").append(studio.name());
            return sb.toString();
        }

    }


