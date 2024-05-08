package ProjectFitness;
/**
 * Enum representing different fitness class instructors.
 * Includes Jennifer, Kim, Denise, Davis, and Emma.
 *
 * @author Abdullah Qayyum
 */
public enum Instructor {
  JENNIFER,
  KIM,
  DENISE,
  DAVIS,
  EMMA;

  /**
   * Checks if an instructor with the specified name exists.
   * @param instructorName The name of the instructor to check.
   * @return true if the instructor exists, false otherwise.
   */
  public static boolean instructorExists(String instructorName) {
    for (Instructor instructor : Instructor.values()) {
      if (instructor.name().equalsIgnoreCase(instructorName)) {
        return true; // Instructor exists
      }
    }
    return false; // Instructor does not exist
  }

}
