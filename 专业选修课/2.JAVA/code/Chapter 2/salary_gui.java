/*Number:2.11(2)
 *Author:yzs
 *Time:2021.11.06
 */
import javax.swing.JOptionPane;
public class salary_gui{
	public static void main(String[] args) {
		String name=JOptionPane.showInputDialog("Enter employee's name:");
		String hoursString=JOptionPane.showInputDialog("Enter numbers of hours worked in a week:");
		double hours=Double.parseDouble(hoursString);
		String rateString=JOptionPane.showInputDialog("Enter hourly pay rate:");
		double rate=Double.parseDouble(rateString);
		String taxString=JOptionPane.showInputDialog("Enter federal tax withholding rate:");
		double tax=Double.parseDouble(taxString);
		String tax2String=JOptionPane.showInputDialog("Enter state tax withholding rate:");
		double tax2=Double.parseDouble(tax2String);
		double m1=hours*rate*tax;
		double m2=hours*rate*tax2;
		String output="Employee's Name:"+name+"\n";
		output+="hours worked:"+hours+"\n";
		output+="Pay rate:  $"+rate+"\n";
		output+=String.format("Gross Pay:  $"+hours*rate+"\n"+"Deductions:\n");
		output+=String.format("\tFederal withholding(%.1f%%):  $%.1f\n",tax*100,m1);
		output+=String.format("\tStates withholding(%.1f%%)  $%.1f\n",tax2*100,m2);
		output+="\tTotal Deduction:  $"+(m1+m2)+"\n";
		output+="Net pay:  $"+(hours*rate-m1-m2);
		JOptionPane.showMessageDialog(null,output);
		// System.out.println();

		// System.out.println();
		// //double rate = input.nextDouble();
		// System.out.println("");
		// //double tax = input.nextDouble();
		// System.out.println("");
		// //double tax2 = input.nextDouble();
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out.println();

		// System.out.printf();
		// System.out.printf();
		// System.out.println();
		// System.out.println();
	}
}
	
