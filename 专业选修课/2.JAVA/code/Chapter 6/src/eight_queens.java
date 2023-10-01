import java.util.Arrays;

/**
 * Time:2021.11.27
 * 6.20 八皇后问题  从第一行开始摆，也可以交换次序，借助回溯法
 */
public class eight_queens {
    public static void main(String[] args) {
        int[][] chess = new int[8][8];
        int[] num = new int[]{6,0,2,1,4,5,7,3};
        for (int i=0;i<8;i++)
        {
            int column =check(chess,num[i]);
            if (column!=-1){
                chess[num[i]][column] = 1;
            }
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (j==7){
                    if (chess[i][j]==1){
                        System.out.print("|Q|\n");
                    }
                    else{
                        System.out.print("| |\n");
                    }
                }
                else {
                    if (chess[i][j]==1){
                        System.out.print("|Q");
                    }
                    else{
                        System.out.print("| ");
                    }
                }
            }
        }
    }

    /**
     * chess:棋盘
     * row:皇后当前待摆放行数
     * return:摆放的列
     */
    public static int check(int[][] chess, int row) {
        int place_column = -1;
        //确定row,查找可摆放的列
        for (int column = 0; column < 8; column++) {
            int flag = 1; //标志是否满足列不冲突
            int flag2 = 1;//标志是否满足对角线不冲突
            for (int j = 0; j < 8; j++) { //判断该列是否已经有皇后
                if (chess[j][column] == 1) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 0) {
                continue;
            } else {
                int check_row = row;
                int check_column = column;
                while (check_column != 0 && check_row != 0) {
                    check_row--;
                    check_column--;
                }
                while (check_row < 7 && check_column < 7) {
                    if (chess[check_row][check_column] == 1) {
                        flag2 = 0;
                        break;
                    }
                    check_row++;
                    check_column++;
                }
            }
            if (flag2 == 1) {
                place_column = column;
                return place_column;
            }
        }
        return place_column;
    }
}
