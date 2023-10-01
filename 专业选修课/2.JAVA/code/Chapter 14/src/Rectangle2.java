/**
 * 14.6
 */
public class Rectangle2 {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(2,4);
        Rectangle rectangle2 = new Rectangle(1,5);
        Rectangle rectangle3 = new Rectangle(4,2);
        System.out.println(rectangle1.equals(rectangle2));
        System.out.println(rectangle1.equals(rectangle3));
        System.out.println(rectangle1.compareTo(rectangle2));
    }
}

class Rectangle extends GeometricObject implements Comparable {
    private double width;
    private double height;

    public Rectangle() {
        this(1.0, 1.0);
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
    @Override
    public int compareTo(Object obj) {
        if (this.getArea() > ((Rectangle) obj).getArea())
            return 1;
        else if (this.getArea() < ((Rectangle) obj).getArea())
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getArea() == ((Rectangle) obj).getArea();
    }
}
