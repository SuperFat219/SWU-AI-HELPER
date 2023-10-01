/**
 * 14.1
 */
public class TestGeometricObject {
    public static void main(String[] args) {
        Circle circle1 = new Circle("circle1" , 4);
        Circle circle2 = new Circle("circle2",10);
        Circle circle = (Circle) GeometricObject.max(circle1, circle2);
        System.out.println("The max circle is " +circle.getName());
        System.out.println("The max circle's area is " +circle.getArea());
        Square square1 = new Square(2,1,"square1");
        Square square2 = new Square(4,1,"square2");
        Square square = (Square) GeometricObject.max(square1, square2);
        System.out.println("The max square is " +square.getName());
        System.out.println("The max square's area is " +square.getArea());


    }
}
