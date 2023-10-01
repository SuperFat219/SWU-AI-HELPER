/*Number:2.10
 *Author:yzs
 *Time:2021.11.06
 */
import javax.swing.JOptionPane;
import java.util.Scanner;
 
public class compute_change_gui{
	public static void main(String[] args) {
		String amount_string=JOptionPane.showInputDialog("Enter an amount in int,for example 1156:");
		int amount=Integer.parseInt(amount_string);
		int dollars=amount/100;
		amount=amount%100;
		int quarters=amount/25;
		amount=amount%25;
		int dimes=amount/10;
		amount=amount%10;
		int nickels=amount/5;
		amount=amount%5;
		int pennys=amount;
		
		String output="Your amount " + amount + " consists of\n"+"\t"+dollars + " dollars";
		//显示

	    output+="\n\t"+quarters + " quarters";
	    output+="\n\t"+dimes + " dimes";
	    output+="\n\t"+nickels + " nickels";
	    output+="\n\t"+pennys + " pennies";
	    JOptionPane.showMessageDialog(null,output);
	}
}
