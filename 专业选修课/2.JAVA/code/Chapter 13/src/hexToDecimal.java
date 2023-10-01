import java.util.Scanner;

/**
 * 13.6
 */
public class hexToDecimal {
    public static void main(String[] args) {
        System.out.println(transformHex("A88"));
        System.out.println(transformHex("F11"));
        System.out.println(transformHex("T10"));
        System.out.println(transformHex("ABC"));
        System.out.println(transformHex("10A"));
    }

    public static int transformHex(String hexString){
        int value = hex_to_decimal(hexString.charAt(0));
        for (int i = 1; i < hexString.length(); i++) {
            value = value * 16 + hexString.charAt(i) - '0';
        }

        return value;
    }

    static int hex_to_decimal(char ch) {
        if (ch == 'A') {
            return 10;
        }
        else if (ch == 'B') {
            return 11;
        }
        else if (ch == 'C') {
            return 12;
        }
        else if (ch == 'D') {
            return 13;
        }
        else if (ch == 'E') {
            return 14;
        }
        else if (ch == 'F') {
            return 15;
        }
        else if (ch <= '9' && ch >= '0')
            return ch - '0';
        else
            throw new NumberFormatException("Illegal character: " + ch);
    }
}
