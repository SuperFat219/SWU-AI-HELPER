public class Exercise7_10 {
    public static void main(String[] args) {
        int[][] board = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = (int)(Math.random() * 2);
                System.out.print(board[i][j]);
            }

            System.out.println();
        }

        // Check rows
        for (int i = 0; i < 3; i++)
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2])
                System.out.println("All " + board[i][0] + "'s on row " + i);

        // Check columns
        for (int j = 0; j < 3; j++)
            if (board[0][j] == board[1][j] && board[0][j] == board[2][j])
                System.out.println("All " + board[0][j] + "'s on column " + j);


        // Check major diagonal
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2])
            System.out.println("All " + board[0][0] + "'s on major diagonal");

        // Check subdiagonal
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0])
            System.out.println("All " + board[0][2] + "'s on subdiagonal");
    }
}
