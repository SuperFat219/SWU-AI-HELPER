public class Circle extends GeometricObject{

    private double radius = 0;
    private String name;

    public Circle() {

    }
    public Circle(String name, double radius) {
        this.name = name;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPerimeter() {
        return Math.PI*2*radius;
    }

    public double getArea() {
        return Math.PI*Math.pow(radius,2);
    }

    public int compareTo(GeometricObject o) {
        if(getArea()> ((Circle) o).getArea()) {
            return 1;
        }else if(getArea() < ((Circle) o ).getArea()) {
            return -1;
        }else
            return 0;
    }
}
