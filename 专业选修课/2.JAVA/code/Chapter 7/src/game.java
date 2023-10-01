import java.util.Scanner;

/**
 * Function:井字游戏 player_X表示-1，player_O表示1
 * Time:2021.12.4
 * 7.9
 */
public class game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] chessboard = new int[3][3];
        show(chessboard);
        int flag = 1;//标志当前谁走棋
        while (!judge(chessboard, flag)) {
            flag = flag * (-1);
            if (flag == -1) {
                System.out.print("Enter a row(1,2,or 3) for player X:");
                int row = input.nextInt() - 1;
                System.out.print("Enter a column(1,2,or 3) for player X:");
                int column = input.nextInt() - 1;
                chessboard[row][column] = flag;
            } else {
                System.out.print("Enter a row(1,2,or 3) for player O:");
                int row = input.nextInt() - 1;
                System.out.print("Enter a column(1,2,or 3) for player O:");
                int column = input.nextInt() - 1;
                chessboard[row][column] = flag;
            }
            show(chessboard);
        }
        if (flag == 1) {
            System.out.println("O player win!");
        } else {
            System.out.println("X player win!");
        }

    }

    /**
     * 显示棋盘
     *
     * @param chessboard 棋盘
     */
    public static void show(int[][] chessboard) {
        for (int index = 0; index < 3; index++) {
            System.out.println("-------------");
            show_row(chessboard[index]);
        }
        System.out.println("-------------");
    }

    public static void show_row(int[] row) {
        for (int i = 0; i < 3; i++) {
            if (row[i] == 0) {
                System.out.print("|   ");
            } else if (row[i] == 1) {
                System.out.print("| O ");
            } else {
                System.out.print("| X ");
            }
            if (i == 2) {
                System.out.print("|\n");
            }
        }
    }

    public static boolean judge(int[][] chessboard, int flag) {
        //row
        for (int row = 0; row < 3; row++) {
            if (chessboard[row][0] == chessboard[row][1] && chessboard[row][1] == chessboard[row][2] && chessboard[row][1] == flag) {
                return true;
            }
        }
        //column
        for (int column = 0; column < 3; column++) {
            if (chessboard[0][column] == chessboard[1][column] && chessboard[1][column] == chessboard[2][column] && chessboard[1][column] == flag) {
                return true;
            }
        }
        //diagonal
        if (chessboard[0][0] == chessboard[1][1] && chessboard[1][1] == chessboard[2][2] && chessboard[1][1] == flag) {
            return true;
        }
        if (chessboard[2][0] == chessboard[1][1] && chessboard[1][1] == chessboard[0][2] && chessboard[1][1] == flag) {
            return true;
        }
        return false;
    }

    //TODO:add check of chessboard full
    //TODO:add check for the repeated input
}
