import java.io.*;
import java.util.Arrays;

/**
 * 9.19
 */

public class sort_data {
    public static void main(String[] args) {
        File file = new File("Exercise9_19.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                s.append((int) (Math.random() * 100)).append(" ");
                if (i % 9 == 0 && i != 0) {
                    s.append("\n");
                }
            }
            fw.write(s.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader rf = new BufferedReader(new InputStreamReader(new FileInputStream(file)) {
            });
            String[] array = new String[100];
            String s = "";
            StringBuilder res = new StringBuilder();
            try {
                while ((s = rf.readLine()) != null) {
                    res.append(s);
//                    res.append(" ");
                }
                array = res.toString().split(" ");
                System.out.println("原始数据为：");
                System.out.println(res);
                int[] num = new int[100];
                for (int i = 0; i < 100; i++) {
                    num[i] = Integer.parseInt(array[i]);
                }
                Arrays.sort(num);
                System.out.println("排序后的数据为：");
                for (int i : num) {
                    System.out.print(i + " ");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
