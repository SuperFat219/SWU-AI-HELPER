public class Square extends GeometricObject{
    private double size1 =1;
    private double size2 =2;
    private String name;

    public Square() {
    }

    public Square(double size1, double size2, String name) {
        this.size1 = size1;
        this.size2 = size2;
        this.name = name;
    }

    public double getSize1() {
        return size1;
    }

    public void setSize1(double size1) {
        this.size1 = size1;
    }

    public double getSize2() {
        return size2;
    }

    public void setSize2(double size2) {
        this.size2 = size2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPerimeter() {
        return 2*(size1+size2);
    }

    public double getArea() {
        return size1*size2;
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
