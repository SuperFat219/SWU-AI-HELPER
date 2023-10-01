import java.util.Random;
import java.util.Scanner;

/**
 * Time:2021.11.20
 * 4.34
 */
public class guess {
    public static void main(String[] args) {
        Random r = new Random();

        String[] string = new String[3];
        string[0] = "scissor";
        string[1] = "rock";
        string[2] = "paper";
        int computer_win_times = 0;
        int you_win_times = 0;
        while (computer_win_times <= 2 && you_win_times <= 2) {
            int computer = r.nextInt(3);
            System.out.println("scissor(0),rock(1),paper(2)");
            Scanner input = new Scanner(System.in);
            int num = input.nextInt();
            while (num > 2 || num < 0) {
                System.out.println("请重新输入：");
                num = input.nextInt();
            }
            if (computer == num) {
                System.out.println("The computer is " + string[computer] + " .You are " + string[num] + " too.It is a draw.");
            } else if (computer == 1 && num == 0) {
                computer_win_times += 1;
                System.out.println("The computer is " + string[computer] + " .You are " + string[num] + " .You lose!");
            } else if (computer == 0 && num == 1) {
                you_win_times += 1;
                System.out.println("The computer is " + string[computer] + " .You are " + string[num] + " .You win!");
            } else if (computer == 2 && num == 0) {
                you_win_times += 1;
                System.out.println("The computer is " + string[computer] + " .You are " + string[num] + " .You win!");
            } else if (computer == 0) {
                computer_win_times += 1;
                System.out.println("The computer is " + string[computer] + " .You are " + string[num] + " .You lose!");
            } else if (computer == 1) {
                you_win_times += 1;
                System.out.println("The computer is " + string[computer] + " .You are " + string[num] + " .You win!");
            } else {
                computer_win_times += 1;
                System.out.println("The computer is " + string[computer] + " .You are " + string[num] + " .You lose!");
            }
        }
        if (computer_win_times > 2) {
            System.out.println("Computer Win!");
        } else {
            System.out.println("You Win!");
        }

    }
}
