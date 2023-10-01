/**
 * Time:2021.11.20
 * 4.19
 */
public class Pyramid2 {
    public static void main(String[] args) {
        int num = 8;
        for (int i = 0; i <= num; i++) {
            for (int j = 0; j < (num - i); j++) {
                System.out.print("   ");
            }
            for (int k = 1; k <= i; k++) {
                System.out.printf("%.0f  ", Math.pow(2, k-1));
            }
            System.out.printf("%.0f",Math.pow(2,i));
            for (int k = i-1; k >= 0; k--) {
                System.out.printf("  %.0f", Math.pow(2, k));
            }
//            for (int j = 0; j < (num - i) * 3; j++) {
//                System.out.print("   ");
//            }
            System.out.print("\n");
        }
    }
}