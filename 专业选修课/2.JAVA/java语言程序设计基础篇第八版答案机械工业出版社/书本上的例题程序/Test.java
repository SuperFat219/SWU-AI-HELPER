public class Test {
  public static void main(String[] args) {

    for (int c = 0; c <= 40; c++) 
      System.out.println(c + "\t\t" + celsiusToFahrenheit(c));
    
    
  }

  public static double celsiusToFahrenheit(double c) {
	return (9.0 / 5) * c + 32; 
  }
  
  
  public static char getGrade(double score) {
    if (score >= 90.0) {
      return 'A';
    } 
    else if (score >= 80.0) {
      return 'B';
    } 
    else if (score >= 70.0) {
      return 'C';
    }
    else if (score >= 60.0) {
      return 'D';
    } 
    else {
      return 'D';
    }
  }
  
  public static void printGrade(double score) {
	if (score > 100 || score < 0) {
	  System.out.println("Invalid score");
	  return;
	}
	
    if (score >= 90.0) {
      System.out.println('A');
    } 
    else if (score >= 80.0) {
      System.out.println('B');
    } 
    else if (score >= 70.0) {
      System.out.println('C');
    } 
    else if (score >= 60.0) {
      System.out.println('D');
    } 
    else {
      System.out.println('F');
    }
  }
}

