import java.util.Scanner;

public class Exercise3_6 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    // Prompt the user to enter weight in pounds
    System.out.print("Enter weight in pounds: ");
    double weight = input.nextDouble();
    
    // Prompt the user to enter height 
    System.out.print("Enter feet: ");
    double feet = input.nextDouble();
    System.out.print("Enter inches: ");
    double inches = input.nextDouble();

    double height = feet * 12 + inches;
    
    // Compute BMI
    double bmi = weight * 0.45359237 / 
      ((height * 0.0254) * (height * 0.0254));
    
    // Display result
    System.out.println("Your BMI is " + bmi);
    if (bmi < 16)
      System.out.println("You are seriously underweight");
    else if (bmi < 18)
      System.out.println("You are underweight");
    else if (bmi < 24)
      System.out.println("You are normal weight");
    else if (bmi < 29)
      System.out.println("You are over weight");
    else if (bmi < 35)
      System.out.println("You are seriously over weight");
    else
      System.out.println("You are gravely over weight");
  }
}
