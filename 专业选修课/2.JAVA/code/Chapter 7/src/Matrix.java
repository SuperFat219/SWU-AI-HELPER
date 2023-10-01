import java.util.Scanner;

/**
 * Function:求n*n整数矩阵主对角线的和以及实现矩阵乘法
 * Time:2021.12.4
 * 7.2,7.6
 */
public class Matrix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a n-by-n matrix row by row");
        System.out.println("Enter the dimension of the matrix");
        int n = input.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = input.nextInt();
            }
        }
        int res = sumMajorDialogonal(matrix);
        System.out.println("Sum of the elements in the major diagonal is " + res);

        double[][] matrix1 = Create_Matrix(input);
        double[][] matrix2 = Create_Matrix(input);
        System.out.println("The matrices are multiplied as follows");
        double[][] res2 = multiplyMatrix(matrix1, matrix2);

        if (res2!=null) {
            print_matrix(res2);
        }
    }

    public static int sumMajorDialogonal(int[][] matrix) {
        int n = matrix.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }


    public static double[][] Create_Matrix(Scanner input) {
        System.out.print("Enter the row of the matrix:");
        int row = input.nextInt();
        System.out.print("Enter the column of the matrix:");
        int column = input.nextInt();
        System.out.println("Enter the matrix");
        double[][] matrix = new double[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }
        return matrix;
    }

    public static void print_matrix(double[][] matrix) {
        int column = matrix[0].length;
        for (double[] doubles : matrix) {
            for (int j = 0; j < column; j++) {
                System.out.printf("%.1f ", doubles[j]);
            }
            System.out.print("\n");
        }
    }

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        int row1 = a.length;
        int column1 = a[0].length;
        int row2 = b.length;
        int column2 = b[0].length;
        if (column1 != row2) {
            System.out.println("Error!");
            return null;
        } else {
            double[][] result = new double[row1][column2];
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < column2; j++) {
                    double sum = 0;
                    for (int k = 0; k < column1; k++) {
                        sum += a[i][k] * b[k][j];
                    }
                    result[i][j] = sum;
                }
            }
            return result;
        }

    }
}
