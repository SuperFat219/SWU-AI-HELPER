import java.util.Scanner;

/**
 * Time:2021.11.20
 * 4.30
 */
public class interest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double money = input.nextDouble();
        double rate = input.nextDouble();
        int month = input.nextInt();
        double fixed_money = money;
        double monthlyRate = rate / 12;
        money = 0;
        for (int i = 0; i < month; i++) {
            money += fixed_money;
            money = money * (1 + monthlyRate);
        }
        System.out.printf("After %d months , the money on your account is %.3f", month, money);
    }
}
