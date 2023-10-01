import java.util.Scanner;

/**
 * Time:2021.11.27
 * 6.5
 */
public class different_number {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[10];
        System.out.println("Enter ten numbers:");
        int countInput =0;
        for (int i=0;i<10;i++)
        {
            int num = input.nextInt();
            if (!search(num,numbers,countInput)){
                numbers[countInput++]=num;
            }
        }
        System.out.print("The distinct numbers are:");
        for (int i=0;i<countInput;i++)
        {
            System.out.printf("%d ",numbers[i]);
        }
    }
    public static boolean search(int num, int[] numbers, int length) {
        for (int j =0;j<length;j++) {
            if (numbers[j] == num) {
                return true;
            }
        }
        return false;
    }
}
