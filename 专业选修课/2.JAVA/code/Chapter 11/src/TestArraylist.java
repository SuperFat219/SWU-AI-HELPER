import java.util.ArrayList;
import java.util.Date;

public class TestArraylist {
    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Person person = new Person("小王","公园道1号","1872136193","12312@sina.com");
        Date date = new Date();
        Triangle triangle = new Triangle(1,2,1,"red",true);

        arrayList.add(person);
        arrayList.add(date);
        arrayList.add("hello");
        arrayList.add(triangle);
        for (Object o : arrayList) {
            System.out.println(o.toString());
        }
//        for(int i=0; i<arrayList.size(); i++) {
//            System.out.println(arrayList.get(i));
//            System.out.println();
//        }


    }
}
