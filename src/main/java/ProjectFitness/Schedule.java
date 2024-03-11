package ProjectFitness;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a schedule of fitness classes.
 * Manages loading class information from a file into an array of FitnessClass objects.
 * The class provides functionality to load class information from a file and store it
 * in an array of FitnessClass objects.
 *
 * @author Nikhilesh Madduri , Abdullah Qayyum
 */
public class Schedule {
        private FitnessClass[] classes;
        private int numClasses;
        public static final int NUMBER_OF_SCHEDULE = 15;
public int getNumClasses(){
        return this.numClasses;
        }
        public FitnessClass[] getclasses(){
        return this.classes;
        }
        /**
         * Constructs a Schedule object with the specified capacity.
         */
        public Schedule() {
                this.numClasses = NUMBER_OF_SCHEDULE;
                this.classes = new FitnessClass[numClasses];
        }

        /**
         * Loads class information from a file into the schedule.
         *
         * @param file the file containing class information
         * @throws IOException if an I/O error occurs while reading the file
         */
        public void load(File file) throws IOException {
                //file = new File("classSchedule.txt"); on Studio Manager class//
                Scanner scanner = new Scanner(file);
                int count = 0;

                while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] parts = line.split(" ");
                        Offer offer = Offer.valueOf(parts[0].toUpperCase());
                        Instructor ins = Instructor.valueOf(parts[1].toUpperCase());
                        Time time = Time.valueOf(parts[2].toUpperCase()); // Time...
                        Location studio = Location.valueOf(parts[3].toUpperCase());
                        FitnessClass obj = new FitnessClass(offer, ins, time, studio);
                        this.classes[count] = obj;
                        System.out.println(this.classes[count]);
                        count++;

                }
        }
}
