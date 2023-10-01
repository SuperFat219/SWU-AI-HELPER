import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise27_24 extends JApplet {
  // Circles are stored in a list
  private List<Circle> circles = new ArrayList<Circle>();
  
  public Exercise27_24() {
    add (new CirclePanel()); // Add to circle panel to applet
  }
    
  /** Panel for displaying circles */
  class CirclePanel extends JPanel {
	public CirclePanel() {
	  addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		  if (isInsideACircle(e.getPoint())) { // Add a new circle
			for (int i = 0; i < circles.size(); i++) 
			  if (circles.get(i).contains(e.getPoint())) 
				circles.remove(i);
			repaint();
	      }
		  else { // Add a new circle
			circles.add(new Circle(e.getX(), e.getY()));
			repaint();
		  }
		}
	  });
	}
	
	/** Returns true if the point is inside an existing circle */
	private boolean isInsideACircle(Point p) {
	  for (Circle circle: circles) 
		if (circle.contains(p)) return true;

	  return false;
	}
	
	protected void paintComponent(Graphics g) {
	  if (circles.size() == 0) return; // Nothing to paint
		  
	  super.paintComponent(g);
	  
	  // Build the edges
	  List<AbstractGraph.Edge> edges 
	    = new ArrayList<AbstractGraph.Edge>();
	  for (int i = 0; i < circles.size(); i++)
		for (int j = i + 1; j < circles.size(); j++)
		  if (circles.get(i).overlaps(circles.get(j))) {
			 edges.add(new AbstractGraph.Edge(i, j));
			 edges.add(new AbstractGraph.Edge(j, i));
		  }
	  
	  // Create a graph with circles as vertices
	  Graph<Circle> graph 
	    = new UnweightedGraph<Circle>(edges, circles);
	  AbstractGraph<Circle>.Tree tree = graph.dfs(0); // a DFS tree
	  boolean isAllCirclesConnected = 
		circles.size() == tree.getNumberOfVerticesFound(); 
	  
	  for (Circle circle: circles) {
		int radius = circle.radius;		
		if (isAllCirclesConnected) { // All circles are connected
		  g.setColor(Color.RED);
		  g.fillOval(circle.x - radius, circle.y - radius, 
		    2 * radius, 2 * radius);
		}
		else // circles are not all connected
		  g.drawOval(circle.x - radius, circle.y - radius, 
		    2 * radius, 2 * radius);
	  }
	}
  }
  
  private static class Circle {
	int radius = 20;
	int x, y;
	
	Circle(int x, int y) {
	  this.x = x;
	  this.y = y;
	}	
	
	public boolean contains(Point p) {
	  double d = distance(x, y, p.x, p.y) ;
      return d <= radius;
    }
	
	public boolean overlaps(Circle circle) {    
	  return distance(this.x, this.y, circle.x, circle.y) 
	    <= radius + circle.radius;
	}
	  
	private static double distance(int x1, int y1, int x2, int y2) {
	  return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JApplet applet = new Exercise27_24();
    frame.add(applet);
    frame.setTitle("Exercise29_17");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setVisible(true);
  }
}