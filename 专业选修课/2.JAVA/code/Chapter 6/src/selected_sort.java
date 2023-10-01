import java.util.Arrays;
import java.util.Scanner;

/**
 * Time:2021.11.27
 * 6.17 选择排序
 */
public class selected_sort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] numbers = new double[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = input.nextDouble();
        }
        Selected_sort(numbers);
        System.out.println(Arrays.toString(numbers));

    }

    public static void Selected_sort(double[] numbers) {
        int length = numbers.length;
        for (int i = 0; i < length - 1; i++) {
            int max_index = length - i - 1;
            for (int j = 0; j < length - i - 1; j++) {
                if (numbers[j] > numbers[max_index]) {
                    max_index = j;
                }
                if (max_index != length - i - 1) {
                    double temp = numbers[max_index];
                    numbers[max_index] = numbers[length - i - 1];
                    numbers[length - i - 1] = temp;
                }
            }
        }
//        return numbers;
    }
}
