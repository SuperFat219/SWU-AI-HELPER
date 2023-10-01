/*Number:2.12(2)
 *Author:yzs
 *Time:2021.11.07
 */
import javax.swing.JOptionPane;
public class interest_cal_gui{
	public static void main(String[] args) {
		String[] data=JOptionPane.showInputDialog("Enter balance and interest rate(e.g.,3 for 3%):").split(" ");
		double balance=Double.parseDouble(data[0]);
		double rate=Double.parseDouble(data[1]);
        double interest = balance*rate/1200;
        String output=String.format("The interest is %.5f",interest);
        JOptionPane.showMessageDialog(null,output);
    }
}