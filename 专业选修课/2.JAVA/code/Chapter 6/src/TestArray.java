import java.util.Arrays;

public class TestArray {
    public static void main(String[] args) {
        int[][] array = new int[][]{{1,3,2,7,5},{1,2,3},{2}};
//        Arrays.sort(array);
        System.out.println(Arrays.deepToString(array));
        System.out.println(array[1].length);
    }
}
