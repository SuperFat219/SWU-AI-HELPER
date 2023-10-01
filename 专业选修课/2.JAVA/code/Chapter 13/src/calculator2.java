import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 13.2
 */
public class calculator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a,b;
        while (true){
            try{
                a = scanner.nextInt();
                b = scanner.nextInt();
                System.out.println(a+" + "+b+" = "+(a+b));
            }
            catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Wrong input!Input once again!");
                continue;
            }
            break;
        }
    }
}
