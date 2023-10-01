public class Exercise14_2 {
  // Main method
  public static void main(String[] args) {
    // Create two comarable rects
    ComparableCircle circle1 = new ComparableCircle(5);
    ComparableCircle circle2 = new ComparableCircle(15);

    // Display the max rect
    ComparableCircle circle3 = (ComparableCircle)Max.max(circle1, circle2);
    System.out.println("The max circle's radius is " + circle3.getRadius());
    System.out.println(circle3);
  }
}

class ComparableCircle extends Circle implements Comparable {
  /** Construct a ComparableRectangle with specified properties */
  public ComparableCircle(double radius) {
    super(radius);
  }

  /** Implement the compareTo method defined in Comparable */
  public int compareTo(Object o) {
    if (getRadius() > ((ComparableCircle) o).getRadius())
      return 1;
    else if (getRadius() < ((ComparableCircle) o).getRadius())
      return -1;
    else
      return 0;
  }
}

//Max.java: Find a maximum object
class Max {
  /** Return the maximum of two objects */
  public static Comparable max
      (Comparable o1, Comparable o2) {
    if (o1.compareTo(o2) > 0)
      return o1;
    else
      return o2;
  }
}
