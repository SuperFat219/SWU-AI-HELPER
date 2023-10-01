public class A {
    public static void main(String[] args) {
        B b = new B();
    }
}
class B{
    final static int a=1;
    public B() {
        System.out.println(a);
    }
}