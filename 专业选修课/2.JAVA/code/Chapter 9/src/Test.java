import org.w3c.dom.ls.LSOutput;

public class Test {
    public static void main(String[] args) {
//        String s = "abcd";
//        s = "welcome";
//        s = s.replace("el","abc");
//        StringBuilder stringBuffer = new StringBuilder(s);
//        s = stringBuffer.reverse().toString();
        String s = "Java Java Java".replaceAll("v\\w", "wi") ;
        System.out.println(s);
    }


}
