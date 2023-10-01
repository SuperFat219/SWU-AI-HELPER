/*
 *Number:3.24
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Scanner;

public class card {
    public static void main(String[] args) {
        System.out.println("Select a card");
        Scanner input = new Scanner(System.in);
        int card=input.nextInt();
        int num=card%13;
        int color=card/13;
        String[] card_color=new String[]{"Clubs","Diamond","Heart","Spades"};
        if (num==1)
        {
            System.out.println("The card you picked is Ace of "+card_color[color]);
        }
        else if (num==11)
        {
            System.out.println("The card you picked is Jack of "+card_color[color]);
        }
        else if (num==12)
        {
            System.out.println("The card you picked is Queen of "+card_color[color]);
        }
        else if (num==13)
        {
            System.out.println("The card you picked is King of "+card_color[color]);
        }
        else
        {
            System.out.println("The card you picked is "+num+" of "+card_color[color]);
        }
    }
}
