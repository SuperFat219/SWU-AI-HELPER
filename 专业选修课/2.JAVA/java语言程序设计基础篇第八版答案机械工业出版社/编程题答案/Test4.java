public class Test4 {
  public static void main(String[] args) {
    A a = new A(3);
  }
}

class A extends B {
  public A(int t) { 
    //super();
    System.out.println("A's constructor is invoked");
  }
}

class B {
  public B() {
    System.out.println("B's constructor is invoked");
  }
  
  public B(int r) {
    System.out.println("rrrr");
  }
}
