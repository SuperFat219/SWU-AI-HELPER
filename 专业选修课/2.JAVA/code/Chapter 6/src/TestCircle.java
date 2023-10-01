import java.util.ArrayList;

public class TestCircle {
    public static void main(String[] args) {
        ArrayList array = new ArrayList();
        Circle circle = new Circle(2);
        Circle circle1 = new Circle(5);
        swap1(circle,circle1);
        System.out.println(circle.radius);
        System.out.println(circle1.radius);
        swap2(circle,circle1);
        System.out.println(circle.radius);
        System.out.println(circle1.radius);
        array.add(circle1);
        array.add(circle);


    }
    public static void swap1(Circle x,Circle y){
        Circle temp = x;
        x= y;
        y= temp;
    }
    public static void swap2(Circle X, Circle Y){
        double temp = X.radius;
        X.radius = Y.radius;
        Y.radius = temp;
    }
}
class Circle{
    double radius = 0;
    Circle(double newradius){
        radius = newradius;
    }
}