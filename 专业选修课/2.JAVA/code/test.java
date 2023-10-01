public class test {
    public static void main(String[] args) {
        B b=new B();
    }
}
class A{
    public A(){
        System.out.println("Aaaa");

    }
}
class B extends A{
    {
        System.out.println("hhh");
    }
}
