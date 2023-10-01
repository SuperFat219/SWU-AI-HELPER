// import java.util.Random
/*
 *Number:3.4
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Scanner;
public class add{
    public static void main(String[] args) {
        int num1=(int)(Math.random()*100);
        int num2=(int)(Math.random()*100);
        System.out.println("What is "+num1+"+"+num2+" ?");
        Scanner input = new Scanner(System.in);
        int res=input.nextInt();
        if (num1+num2 == res)
        {
            System.out.println("True");
        }
        else{
            System.out.println("False");
        }


    }
}