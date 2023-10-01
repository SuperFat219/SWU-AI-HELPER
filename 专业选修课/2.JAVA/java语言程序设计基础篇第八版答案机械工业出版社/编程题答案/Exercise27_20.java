import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Exercise27_20 extends JFrame {
  private List<Vertex> vertices = new ArrayList<Vertex>();
  private List<AbstractGraph.Edge> edges =
    new ArrayList<AbstractGraph.Edge>();
  
  public Exercise27_20() {
    readGraph();
    
    Graph<Vertex> graph1 = new UnweightedGraph<Vertex>(edges, vertices);
    GraphView view = new GraphView(graph1);
    add(view);
  }
  
  public void readGraph() {
    try {
      System.out.print("Enter a file name for the graph: ");
      Scanner input = new Scanner(System.in);
      String filename = input.nextLine();
      
      Scanner file = new Scanner(new File(filename));
      int n = Integer.parseInt(file.nextLine());
      
      for (int i = 0; i < n; i++) {
        String[] tokens = file.nextLine().split("[\\s+]");
        int x = Integer.parseInt(tokens[1].trim()); 
        int y = Integer.parseInt(tokens[2].trim()); 
        vertices.add(new Vertex(i + "", x, y));
        
        for (int k = 3; k < tokens.length; k++)
          edges.add(new AbstractGraph.Edge(i, 
            Integer.parseInt(tokens[k].trim())));
      }    
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  
  class Vertex implements Displayable {
    private int x, y;
    private String name;
    
    Vertex(String name, int x, int y) {
      this.name = name;
      this.x = x;
      this.y = y;
    }
    
    public int getX() {
      return x;
    }
    
    public int getY() {
      return y;
    }
    
    public String getName() {
      return name;
    }
    
    public boolean equals(Object o) {
      return ((Vertex)o).name.equals(this.name);
    }
  }
  
  public static void main(String[] args) {
    JFrame frame = new Exercise27_20();
    frame.setTitle("Exercise27_20");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}