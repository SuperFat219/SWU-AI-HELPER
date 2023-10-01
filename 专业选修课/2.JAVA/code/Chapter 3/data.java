/*
 *Number:3.21
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Scanner;


public class data {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter year:");
        int year=input.nextInt();
        System.out.println("Enter month(1-12):");
        int month=input.nextInt();
        System.out.println("Enter the day of the month:");
        int q=input.nextInt();
        int k=year%100;
        int j=year/100;
        int h;
        if (month==1 || month ==2)
        {
            year=year-1;
            h=(q+26*(month+12+1)/10+k+k/4+j/4+5*j)%7;
        }
        else
        {
            h=(q+26*(month+1)/10+k+k/4+j/4+5*j)%7;
        }
        String[] data=new String[]{"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
        System.out.println("Day of the week is "+data[h]);

    }
}
