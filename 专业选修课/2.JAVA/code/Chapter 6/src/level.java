import java.util.Arrays;
import java.util.Scanner;

/**
 * Time:2021.11.27
 * 6.1
 */
public class level {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of students:");
        int numStudents = input.nextInt();
        int[] scores = new int[numStudents];
        System.out.println("Enter 4 scores:");
        for (int i = 0; i < numStudents; i++) {
            scores[i] = input.nextInt();
        }
        int best = Arrays.stream(scores).max().getAsInt();
        for (int i = 0; i < numStudents; i++) {
            char level;
            if (scores[i] >= best - 10) {
                level = 'A';
            } else if (scores[i] >= best - 20) {
                level = 'B';
            } else if (scores[i] >= best - 30) {
                level = 'C';
            } else if (scores[i] >= best - 40) {
                level = 'D';
            } else {
                level = 'F';
            }
            System.out.printf("Student %d score is %d and grade is %c\n", i, scores[i], level);
        }
    }
}
