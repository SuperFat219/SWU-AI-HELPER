import java.util.Random;

/**
 * Time:2021.11.20
 * 5.29
 */
public class game {
    public static void main(String[] args) {
        int res = play();
        if (res == 2 || res == 3 || res == 12) {
            System.out.println("You lose!");
        } else if (res == 7 || res == 11) {
            System.out.println("You win!");
        } else {
            System.out.println("Point is " + res);
            int flag = 1;//标志游戏是否结束
            while (flag == 1) {
                int res2 = play();
                if (res2 == 7) {
                    System.out.println("You lose!");
                    flag = 0;
                } else if (res2 == res) {
                    System.out.println("You win!");
                    flag = 0;
                }
            }
        }
    }

    public static int play() {
        Random r = new Random();
        int x1 = r.nextInt(6) + 1;
        int x2 = r.nextInt(6) + 1;
        System.out.println("You rolled " + x1 + " + " + x2 + " = " + (x1 + x2));
        return (x1 + x2);
    }
}
