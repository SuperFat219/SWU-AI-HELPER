public class Exercise14_6 {
  public static void main(String[] args) {
    Rectangle11_6 obj1 = new Rectangle11_6();
    Rectangle11_6 obj2 = new Rectangle11_6();
    System.out.println(obj1.equals(obj2));
    System.out.println(obj1.compareTo(obj2));
  }
}

// Rectangle.java: The Rectangle class that extends GeometricObject
class Rectangle11_6 extends GeometricObject implements Comparable {
  private double width;
  private double height;

  /** Default constructor */
  public Rectangle11_6() {
    this(1.0, 1.0);
  }

  /** Construct a rectangle with width and height */
  public Rectangle11_6(double width, double height) {
    this.width = width;
    this.height = height;
  }

  /** Return width */
  public double getWidth() {
    return width;
  }

  /** Set a new width */
  public void setWidth(double width) {
    this.width = width;
  }

  /** Return height */
  public double getHeight() {
    return height;
  }

  /** Set a new height */
  public void setHeight(double height) {
    this.height = height;
  }

  /** Implement the getArea method in GeometricObject */
  public double getArea() {
    return width*height;
  }

  /** Implement the getPerimeter method in GeometricObject */
  public double getPerimeter() {
    return 2*(width + height);
  }

  /** Override the toString method defined in the Object class */
  public String toString() {
    return "[Rectangle] width = " + width +
      " and height = " + height;
  }

  public int compareTo(Object obj) {
    if (this.getArea() > ((Rectangle11_6)obj).getArea())
      return 1;
    else if (this.getArea() < ((Rectangle11_6)obj).getArea())
      return -1;
    else
      return 0;
  }

  public boolean equals(Object obj) {
    return this.getArea() == ((Rectangle11_6)obj).getArea();
  }
}
