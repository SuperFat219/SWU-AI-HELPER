import java.util.Scanner;
/**
 * Time:2021.11.20
 * 4.17
 */
public class Pyramid {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();//7
        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < (num - i); j++) {
                System.out.print("   ");
            }
            for (int k = i; k > 1; k--) {
                System.out.printf("%d  ", k);
            }
            System.out.print("1");
            for (int k = 2; k <= i; k++) {
                System.out.printf("  %d", k);
            }
//            for (int j = 0; j < (num - i) * 3; j++) {
//                System.out.print("   ");
//            }
            System.out.printf("\n");
        }
    }
}
