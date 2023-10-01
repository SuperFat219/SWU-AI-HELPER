public class FinalizationDemo {
  public static void main(String[] args) {
    Cake a1 = new Cake(1);
    Cake a2 = new Cake(2);
    Cake a3 = new Cake(3);

    // To dispose the objects a2 and a3
    a2 = a3 = null;
    System.gc(); // Invoke the Java garbage collector
  }
}

class Cake extends Object {
  private int id;

  public Cake(int id) {
    this.id = id;
    System.out.println("Cake object " + id + " is created");
  }

  protected void finalize() throws java.lang.Throwable {
    super.finalize();
    System.out.println("Cake object " + id + " is disposed");
  }
}
