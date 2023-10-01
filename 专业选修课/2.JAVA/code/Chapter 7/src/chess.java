import java.util.Scanner;

/**
 * Function:四子棋 R：1 Y：-1
 * Time:2021.12.4
 * 7.20
 */
public class chess {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] chessboard = new int[6][7];
//        show(chessboard);
//        System.out.println(check(chessboard,1,0,0));
        int flag = 1;
        while (true) {
            if (judgeFull(chessboard)) {
                System.out.println("Tie!");
                break;
            }
            if (flag == 1) {
                System.out.print("Drop a red disk at column(0-6):");
                int column = input.nextInt();
                int row = getRow(chessboard, column);
                chessboard[row][column] = 1;
                show(chessboard);
                if (check(chessboard, flag, row, column)) {
                    System.out.println("The Red player win!");
                    break;
                }
            } else {
                System.out.print("Drop a yellow disk at column(0-6):");
                int column = input.nextInt();
                int row = getRow(chessboard, column);
                chessboard[row][column] = -1;
                show(chessboard);
                if (check(chessboard, flag, row, column)) {
                    System.out.println("The yellow player win!");
                    break;
                }
            }
            flag = flag * (-1);
        }

    }

    public static void show(int[][] chessboard) {
        for (int index = 0; index < 6; index++) {
            show_row(chessboard[index]);
        }
        System.out.println("-----------------------------");
    }

    public static void show_row(int[] row) {
        for (int i = 0; i < 7; i++) {
            if (row[i] == 0) {
                System.out.print("|   ");
            } else if (row[i] == 1) {
                System.out.print("| R ");
            } else {
                System.out.print("| Y ");
            }
            if (i == 6) {
                System.out.print("|\n");
            }
        }
    }

    public static boolean check(int[][] chessboard, int flag, int place_row, int place_column) {
        //row check
//        int row_check_flag = 0;
//        for (int row = 5; row >= 0; row--) {
        for (int column = 0; column < 4; column++) {
            if (chessboard[place_row][column] == flag
                    && chessboard[place_row][column] == chessboard[place_row][column + 1]
                    && chessboard[place_row][column] == chessboard[place_row][column + 2]
                    && chessboard[place_row][column] == chessboard[place_row][column + 3]) {
//                    row_check_flag =1;
                return true;
            }
        }
//        }
        //column check
//        for (int column = 0; column<7; column++) {
        for (int row = 0; row < 3; row++) {
            if (chessboard[row][place_column] == flag
                    && chessboard[row][place_column] == chessboard[row + 1][place_column]
                    && chessboard[row][place_column] == chessboard[row + 2][place_column]
                    && chessboard[row][place_column] == chessboard[row + 3][place_column]) {
                return true;
            }
        }
//        }
        //diagonal
        while (place_row != 0 && place_column != 0) {
            place_row--;
            place_column--;
        }
//        System.out.printf("%d %d", place_row, place_column);
        if (place_row <= 2 && place_column <= 3) {
            while (place_row != 2 && place_column != 3) {
//            if (chessboard[place_row+1][place_column+1] !=)
                if (chessboard[place_row][place_column] == flag
                        && chessboard[place_row][place_column] == chessboard[place_row + 1][place_column + 1]
                        && chessboard[place_row][place_column] == chessboard[place_row + 2][place_column + 2]
                        && chessboard[place_row][place_column] == chessboard[place_row + 3][place_column + 3]) {
                    return true;
                }
                place_row++;
                place_column++;
            }
        }
        //TODO:如何更快的搜索，避免重复搜索，KMP算法
        return false;
    }

    public static int getRow(int[][] chessboard, int column) {
        for (int row = 5; row >= 0; row--) {
            if (chessboard[row][column] == 0) {
                return row;
            }
        }
        return -1;
    }

    public static boolean judgeFull(int[][] chessboard) {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                if (chessboard[row][column] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
