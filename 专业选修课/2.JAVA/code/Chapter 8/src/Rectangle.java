/**
 * 8.1
 * 2021.12.4
 */
public class Rectangle {
    private double width=1;
    private double height=1;

    public Rectangle() {

    }
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    public double getArea(){
//        return getHeight()*getWidth();
        return width*height;
    }
    public double getPerimeter(){
        return 2*(height+width);
    }
}
