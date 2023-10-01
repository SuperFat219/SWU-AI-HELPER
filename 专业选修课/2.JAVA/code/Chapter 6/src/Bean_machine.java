import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Time:2021.11.27
 * 6.21
 */
public class Bean_machine {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of balls to drop:");
        int balls = input.nextInt();
        System.out.print("Enter the number of slots in the bean machine:");
        int slotsNum = input.nextInt();

        int[] slots = new int[slotsNum + 1];
        for (int i = 0; i < balls; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < slotsNum-1; j++) {
                boolean p = r.nextBoolean();
                if (p) {
                    left++;
                    System.out.print("L");
                } else {
                    right++;
                    System.out.print("R");
                }
            }
            slots[right]++;
            System.out.print("\n");
        }
        print(slots,slotsNum);
//        System.out.println(Arrays.toString(slots));
    }
    public static void print(int[] slots,int slotsNum){
        int max_balls = Arrays.stream(slots).max().getAsInt();
        for (int i=max_balls;i>=1;i--){
            for (int j=0;j<slotsNum;j++){
                if (slots[j] >= i){
                    System.out.print("0");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
