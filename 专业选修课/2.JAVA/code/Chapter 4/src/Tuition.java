/**
 * Time:2021.11.20
 * 4.7
 */
public class Tuition {
    public static void main(String[] args) {
        double now_tuition = 10000;
        double increase_rate = 0.05;
        double ten_years_tuition = 10000;
        for (int i = 0; i < 10; i++) {
            ten_years_tuition = ten_years_tuition * (1 + increase_rate);
        }
        System.out.printf("十年后学费为%.2f\n", ten_years_tuition);
        double sum = 0;
        for (int i = 0; i < 4; i++) {
            ten_years_tuition = ten_years_tuition * (1 + increase_rate);
            sum += ten_years_tuition;
        }
        System.out.printf("从现在开始十年后，4年内总学费为%.2f", sum);
    }
}
