import java.util.Arrays;

/** This program works, but the design is not good. Redesign it */
public class Exercise22_12 {
  // Each row in points represents a point
  private double[][] points;

  public static void main(String[] args) {
    Point[] points = new Point[100];
    for (int i = 0; i < points.length; i++) {
      points[i] = new Point(Math.random() * 100, Math.random() * 100);
    }

    System.out.println("Sort on x-coordinates");
    Arrays.sort(points);
    for (int i = 0; i < points.length; i++) {
      System.out.println(points[i]);
    }
    
    System.out.println("Sort on y-coordinates");
    Arrays.sort(points, new CompareY());
    for (int i = 0; i < points.length; i++) {
      System.out.println(points[i]);
    }  
  }

  /** Define a class for a point with x- and y- coordinates */
  static class Point implements Comparable<Point> {
    double x;
    double y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public int compareTo(Point p2) {
      if (this.x < p2.x)
        return -1;
      else if (this.x == p2.x) {
        // Secondary order on y-coordinates
        if (this.y < p2.y)
          return -1;
        else if (this.y == p2.y)
          return 0;
        else
          return 1;
      } else
        return 1;
    }
    
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  /**
   * A comparator for comparing points on their y-coordinates. If y-coordinates
   * are the same, compare their x-coordinator.
   */
  static class CompareY implements java.util.Comparator<Point> {
    public int compare(Point p1, Point p2) {
      if (p1.y < p2.y)
        return -1;
      else if (p1.y == p2.y) {
        // Secondary order on x-coordinates
        if (p1.x < p2.x)
          return -1;
        else if (p1.x == p2.x)
          return 0;
        else
          return 1;
      } else
        return 1;
    }
  }
}