package ProjectFitness;
/**
 * Enum representing different time slots for fitness classes.
 * Includes MORNING, AFTERNOON, and EVENING time slots.
 * Each time slot is associated with a specific hour and minute.
 *
 * @author Abdullah Qayyum
 *
 */
public enum Time {
    MORNING(9, 30),
    AFTERNOON(14, 00),
    EVENING(18, 30);

    private int hour;
    private int min;
    public int gethour(){
        return this.hour;
    }
    public int getmin(){
        return this.min;
    }

    /**
     * Constructs a Time enum with the specified hour and minute.
     *
     * @param hour the hour value for the time slot
     * @param min  the minute value for the time slot
     */
    Time(int hour, int min) {
        this.hour = hour;
        this.min= min;
    }
}

