package ProjectFitness;
/**
 * Represents a date with year, month, and day attributes.
 * Provides methods to check if the date is valid and to compare dates.
 *
 * @author Abdullah Qayyum
 */
public class Date implements Comparable<Date> {

    private final int year;
    private final int month;
    private final int day;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    public static final int DAYS_IN_31_DAYS_MONTHS = 31;
    public static final int DAYS_IN_30_DAYS_MONTHS = 30;
    public static final int DAYS_IN_29_DAYS_MONTHS = 29;
    public static final int DAYS_IN_28_DAYS_MONTHS = 28;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int MIN_YEAR = 1900;
    public static final int MAX_YEAR = 2024;
    public static final int MIN_DAYS = 1;

    /**
     * Constructs a Date object with the given year, month, and day.
     *
     * @param year  the year
     * @param month the month (1-12)
     * @param day   the day
     */
    public Date(int month, int day, int year) {  // change this if you can to a "mm/dd/yyyy" String Format
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Gets the year of the date.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the month of the date.
     *
     * @return the month (1-12)
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day of the date.
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Compares this date with another date.
     *
     * @param date the date to compare with
     * @return a negative integer, zero, or a positive integer if this date is less than, equal to, or greater than the specified date
     */
    @Override
    public int compareTo(Date date) {
        if (date == null) {
            return 1;
        }
        int yearComparison = Integer.compare(this.getYear(), date.getYear());
        if (yearComparison != 0) {
            return yearComparison;
        }
        int monthComparison = Integer.compare(this.getMonth(), date.getMonth());
        if (monthComparison != 0) {
            return monthComparison;
        }
        return Integer.compare(this.getDay(), date.getDay());
    }

    /**
     * Checks if the date is a valid calendar date according to the Gregorian calendar rules.
     *
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid() {
        if (this.getYear() >= MIN_YEAR && this.getYear() <= MAX_YEAR) {
            if (this.getYear() == 2024 && this.getMonth() == 1 && this.getDay() > 12) {
                return false;
            }
            if (this.getYear() == 2024 && this.getMonth()>1) {
                return false;
            }
            if (month == JANUARY || month == MARCH ||
                    month == MAY || month == JULY ||
                    month == AUGUST || month == OCTOBER ||
                    month == DECEMBER) {
                return this.getDay() >= MIN_DAYS && this.getDay() <= DAYS_IN_31_DAYS_MONTHS;
            }
            if (month == APRIL || month == JUNE ||
                    month == SEPTEMBER || month == NOVEMBER) {
                return this.getDay() >= MIN_DAYS && this.getDay() <= DAYS_IN_30_DAYS_MONTHS;
            }
            if (month == FEBRUARY) {
                if (this.getDay() >= MIN_DAYS & this.getDay() <= DAYS_IN_29_DAYS_MONTHS) {
                    if (this.getDay() == DAYS_IN_29_DAYS_MONTHS) {
                        return (this.getYear() % QUADRENNIAL == 0 && this.getYear() % CENTENNIAL == 0 &&
                                this.getYear() % QUATERCENTENNIAL == 0) ||
                                (this.getYear() % QUADRENNIAL == 0 && this.getYear() % CENTENNIAL != 0);

                    }
                    if (this.getDay() == DAYS_IN_28_DAYS_MONTHS) {
                        return (this.getYear() % QUADRENNIAL != 0) || (this.getYear() % QUADRENNIAL == 0 &&
                                this.getYear() % CENTENNIAL == 0 && this.getYear() % QUATERCENTENNIAL != 0);
                    }
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Compares this Date object to another object for equality.
     *
     * @param object the object to compare to
     * @return true if the specified object is equal to this Date object, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object instanceof Date) {
            Date date2 = (Date) object;
            return this.getDay() == date2.getDay() && this.getMonth() == date2.getMonth()
                    && this.getYear() == date2.getYear();
        }

        return false;
    }
    /**
     * Returns a string representation of this Date object.
     *
     * @return a string containing the month, day, and year of this Date object in the format "MM/DD/YYYY"
     */
    @Override
    public String toString() {
        return this.getMonth() + "/" + this.getDay() + "/" + this.getYear();
    }


}
