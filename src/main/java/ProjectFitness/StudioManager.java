package ProjectFitness;

import java.io.File;
import java.io.IOException;

public class StudioManager {

    public static void run() throws IOException {
        File file = new File("R:\\\\Project3\\\\FitnessProjectGUI\\\\src\\\\main\\\\java\\\\ProjectFitness\\\\MemberList.java");
        MemberList members = new MemberList();
        System.out.println("List of members loaded");
        members.load(file);
        System.out.println("- End of list\n");

        System.out.println("Fitness classes loaded");
        File file2 = new File("R:\\Fitness Club Project\\FitnessProject\\src\\ProjectFitness\\classSchedule.txt");
        Schedule schedule = new Schedule();
        schedule.load(file2);
    }

    public static void main(String[] args) throws IOException {
        run();
    }
}
