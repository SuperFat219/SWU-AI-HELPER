import java.util.*;

public class Exercise27_4 {
  public static void main(String[] args) {
	String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
	  "Denver", "Kansas City", "Chicago", "Boston", "New York",
	  "Atlanta", "Miami", "Dallas", "Houston", "Savannah", "Charlston"};
	    
    int[][] edges = {
	      {0, 1}, {0, 3}, {0, 5},
	      {1, 0}, {1, 2}, {1, 3},
	      {2, 1}, {2, 3}, {2, 4}, {2, 10},
	      {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
	      {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
	      {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
	      {6, 5}, {6, 7},
	      {7, 4}, {7, 5}, {7, 6}, {7, 8},
	      {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
	      {9, 8}, {9, 11},
	      {10, 2}, {10, 4}, {10, 8}, {10, 11},
	      {11, 8}, {11, 9}, {11, 10}, {12, 13}, {13, 12}
	    };

    MyGraph<String> graph = 
      new MyGraph<String>(edges, vertices);
    AbstractGraph<String>.Tree dfs = 
      graph.dfs(graph.getIndex("Chicago")); 
    
    System.out.println(graph.getConnectedComponents());
  }
	  
  public static class MyGraph<V> extends UnweightedGraph<V> {	  
	/** Construct an empty graph */
	public MyGraph() {
	}
		  
	  /** Construct a graph from edges and vertices stored in arrays */
	  public MyGraph(int[][] edges, V[] vertices) {
	    super(edges, vertices);
	  }
	
	  /** Construct a graph from edges and vertices stored in List */
	  public MyGraph(List<Edge> edges, List<V> vertices) {
	    super(edges, vertices);
	  }
	
	  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
	  public MyGraph(List<Edge> edges, int numberOfVertices) {
	    super(edges, numberOfVertices);
	  }
	  
	  /** Construct a graph from integer vertices 0, 1, and edge array */
	  public MyGraph(int[][] edges, int numberOfVertices) {
	    super(edges, numberOfVertices);
	  }
	  
	public List<List<Integer>> getConnectedComponents() {
	  List<List<Integer>> list = new ArrayList<List<Integer>>();
	  
	  List<Integer> vertexIndices = new ArrayList<Integer>();	
	  for (int i = 0; i < vertices.size(); i++)
		vertexIndices.add(i);  
	  
	  while (vertexIndices.size() > 0) {
		Tree tree = dfs(vertexIndices.get(0));
		list.add(tree.getSearchOrders());
		vertexIndices.removeAll(tree.getSearchOrders());
	  }
	  
	  return list;
	}
  }
}