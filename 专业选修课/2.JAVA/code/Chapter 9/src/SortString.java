/**
 * 9.11
 */

public class SortString {
    public static void main(String[] args) {
        String string = "dcba";
        System.out.println("原来的字符串是："+string);
        System.out.println("排序后的字符串是："+sort(string));
    }
    public static String sort(String s){
        StringBuilder builder = new StringBuilder(s);
        int length = s.length();
        for (int i=0;i<length;i++){
            for (int j=0;j<length-i-1;j++){
                if (s.charAt(j)>s.charAt(j+1)){
                    char p = s.charAt(j);
                    builder.setCharAt(j,s.charAt(j+1));
                    builder.setCharAt(j+1,p);
                    s=builder.toString();
                }
            }
        }
        return s;
    }
}
