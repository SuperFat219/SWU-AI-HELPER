import java.util.Arrays;
import java.util.Scanner;

/**
 * Time:2021.11.27
 * 6.3
 */
public class count {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the integers between 1 and 100");
        int[] number = new int[20];
        int countInput = 0;
        int num = input.nextInt();
        number[countInput] = num;
        while (num != 0) {
            num = input.nextInt();
            number[++countInput] = num;
        }
        Arrays.sort(number);
//        System.out.println(Arrays.toString(number));
        int[] have_searched = new int[20];
        int search_index = 0;
        int k = 0;
        for (; number[k] == 0; k++) {

        }
//        System.out.println(k);
//        System.out.println(countInput);
        for (; k < number.length; k++) {
            if (!search(number[k], have_searched)) {
                int result = countTimes(number[k], number, number.length);
                if (result > 1) {
                    System.out.printf("%d occurs %d times\n", number[k], result);
                } else {
                    System.out.printf("%d occurs %d time\n", number[k], result);
                }
            }
            have_searched[search_index++] = number[k];
        }

    }

    public static int countTimes(int num, int[] number, int length) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (number[i] == num) {
                count++;
            }
        }
        return count;
    }

    public static boolean search(int num, int[] have_searched) {
        for (int j : have_searched) {
            if (j == num) {
                return true;
            }
        }
        return false;
    }
}
