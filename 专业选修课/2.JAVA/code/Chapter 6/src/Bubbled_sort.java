import java.util.Arrays;
import java.util.Scanner;

/**
 * Time:2021.11.27
 * 6.18 冒泡排序
 */
class Bubbled_sort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] numbers = new double[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = input.nextDouble();
        }
        bubbled_sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
    public static void bubbled_sort(double[] numbers){
        int length = numbers.length;
        for (int i=0;i<length-1;i++){
            for (int j=0;j<length-i-1;j++){
                if (numbers[j]>numbers[j+1]){
                    double temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
    }
}
