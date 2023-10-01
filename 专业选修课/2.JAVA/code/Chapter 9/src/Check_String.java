import java.util.Scanner;

/**
 * 9.3
 */
public class Check_String {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        String string = input.next();
        if (check(string)){
            System.out.println("Valid Password");
        }
        else{
            System.out.println("Invalid Password");
        }

    }
    public static boolean check(String string){
        if (string.length()<8){
            return false;
        }
        int numCount = 0;
        int valid_flag = 1;
        for (int i=0;i<string.length();i++){
            if (string.charAt(i)>='0' && string.charAt(i)<='9'){
                numCount++;
                continue;
            }
            if (string.charAt(i)>='A' && string.charAt(i)<='Z'){
                continue;
            }
            else if (string.charAt(i)>='a' && string.charAt(i)<='z'){
                continue;
            }
            else {
                valid_flag = 0;
            }
        }
        return valid_flag != 0 && numCount >= 2;
    }

}
