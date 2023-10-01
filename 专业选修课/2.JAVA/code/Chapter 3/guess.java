/*
 *Number:3.17
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Random;
import java.util.Scanner;

public class guess {
    public static void main(String[] args) {
        Random r = new Random();
        int computer=r.nextInt(3);
        System.out.println("scissor(0),rock(1),paper(2)");
        Scanner input = new Scanner(System.in);
        int num=input.nextInt();
        while (num > 2 || num <0)
        {
            System.out.println("请重新输入：");
            num=input.nextInt();
        }
        String[] string = new String[3];
        string[0]="scissor";string[1]="rock";string[2]="paper";
        if (computer==num)
        {
            System.out.println("The computer is "+string[computer]+" .You are "+string[num]+" too.It is a draw.");
        }
        else if(computer==1 &&num ==0)
        {
            System.out.println("The computer is "+string[computer]+" .You are "+string[num]+" You lose!");
        }
        else if(computer==0 &&num ==1)
        {
            System.out.println("The computer is "+string[computer]+" .You are "+string[num]+" You win!");
        }
        else if(computer==2 &&num ==0)
        {
            System.out.println("The computer is "+string[computer]+" .You are "+string[num]+" You win!");
        }
        else if(computer==0 &&num ==2)
        {
            System.out.println("The computer is "+string[computer]+" .You are "+string[num]+" You lose!");
        }
        else if(computer==1 &&num ==2)
        {
            System.out.println("The computer is "+string[computer]+" .You are "+string[num]+" You win!");
        }
        else if(computer==2 &&num ==1)
        {
            System.out.println("The computer is "+string[computer]+" .You are "+string[num]+" You lose!");
        }
    }
}
