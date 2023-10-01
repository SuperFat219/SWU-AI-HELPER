import java.util.Scanner;

/**
 * Time:2021.11.20
 * 4.28
 */
public class date {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year = input.nextInt();
        int day = input.nextInt();
        //String[] week = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        // 计算指定年份1月1号到
        StringBuilder str = new StringBuilder();
        String str2 = "";

        for (int month = 1; month <= 12; month++) {
            // 月份天数（不考虑闰年）
            int week = day % 7;
            switch (week) {
                case 1:
                    str2 = " is Monday";
                    break;
                case 2:
                    str2 = " is Tuesday";
                    break;
                case 3:
                    str2 = " is Wednesday";
                    break;
                case 4:
                    str2 = " is Thurday";
                    break;
                case 5:
                    str2 = " is Friday";
                    break;
                case 6:
                    str2 = " is Saturday";
                    break;
                case 0:
                    str2 = " is Sunday";
                    break;
            }
            switch (month) {
                case 1: {
                    day += 31;
                    str.append("January 1, ");
                }
                break;
                case 2: {
                    day += 28;
                    str.append("Febrary 1, ");
                }
                break;
                case 3: {
                    day += 31;
                    str.append("March 1, ");
                }
                break;
                case 4: {
                    day += 30;
                    str.append("April 1, ");
                }
                break;
                case 5: {
                    day += 31;
                    str.append("May 1, ");
                }
                break;
                case 6: {
                    day += 30;
                    str.append("June 1, ");
                }
                break;
                case 7: {
                    day += 31;
                    str.append("July 1, ");
                }
                break;
                case 8: {
                    day += 31;
                    str.append("August 1, ");
                }
                break;
                case 9: {
                    day += 30;
                    str.append("September 1, ");
                }
                break;
                case 10: {
                    day += 31;
                    str.append("November 1, ");
                }
                break;
                case 11: {
                    day += 30;
                    str.append("October 1, ");
                }
                break;
                case 12: {
                    day += 31;
                    str.append("December 1, ");
                }
            }
            //采用+这种方式，是调用stringbuilder的append（）方法，每次都会新生成一个对象，在循环足够次数很大的情况下，可能会浪费内存。
            if (((year % 4 == 0) && (year % 100 != 0)) || ((year % 400 == 0) && (year % 100 == 0))) {
                if (month != 1)
                    day += 1;
            }

            System.out.println(str.toString() + year + str2);
            str = new StringBuilder();
            str2 = "";
        }
    }
}
