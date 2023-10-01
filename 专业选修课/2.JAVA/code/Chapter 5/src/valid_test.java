import java.util.Scanner;

/**
 * Time:2021.11.20
 * 5.31
 */
public class valid_test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long number = input.nextLong();
//        System.out.println(number);
//        long number = 4388576018402626;
        int even_sum = sumOfDoubleEvenPlace(number);
//        System.out.println(even_sum);
        int odd_sum = sumOfOddPlace(number);
//        System.out.println(odd_sum);
        int sum = even_sum + odd_sum;
        if (sum % 10 == 0) {
            System.out.println("Valid!");
        } else {
            System.out.println("Invalid!");
        }
    }

    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        int location = 1;
        while (number > 0) {
            if (location % 2 == 0) {
                int num = (int) (number % 10 * 2);
                if (num >= 10) {
                    sum += (num % 10);
                    num = num / 10;
                    sum += num;
                } else {
                    sum += num;
                }
                number = number / 10;
                location += 1;
            } else {
                number = number / 10;
                location += 1;
            }
        }
        return sum;
    }

    public static int sumOfOddPlace(long number) {
        int location = 1;
        int sum = 0;
        while (number > 0) {
            if (location % 2 != 0) {
                sum += number % 10;
                number = number / 10;
                location += 1;
            } else {
                number = number / 10;
                location += 1;
            }
        }
        return sum;
    }
}
