public class Exercise27_10 {
  public static void main(String[] args) throws Exception {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a file name: ");
    java.io.File file = new java.io.File(input.nextLine());
   
    if (!file.exists()) {
      System.out.println("File does not exist");
      System.exit(0);
    }
    
    System.out.print("Enter two vertices (integer indexes): ");
    int v1 = input.nextInt();
    int v2 = input.nextInt();

    java.util.Scanner inFile = new java.util.Scanner(file);
    
    // Read the number of vertices
    String s = inFile.nextLine();
    int numberOfVertices = Integer.parseInt(s);
    System.out.println("The number of vertices is " + numberOfVertices);
    
    java.util.List<AbstractGraph.Edge> list = 
      new java.util.ArrayList<AbstractGraph.Edge>();
    
    while (inFile.hasNext()) {
      s = inFile.nextLine();
      
      String[] tokens = s.split("[\\s+]");
      
      int v = Integer.parseInt(tokens[0].trim());
      for (int i = 1; i < tokens.length; i++) {
        int adjacentVertex = Integer.parseInt(tokens[i].trim());
        list.add(new AbstractGraph.Edge(v, adjacentVertex));
      }
    }
    
    Graph graph = new UnweightedGraph(list, numberOfVertices);
    
    graph.printEdges();
    AbstractGraph.Tree tree = graph.bfs(v1);
    
    java.util.List<Integer> path = tree.getPath(v2);
    
    System.out.print("The path is ");
    for (int i = 0; i < path.size(); i++)
      System.out.print(path.get(i) + " ");
  }
}
