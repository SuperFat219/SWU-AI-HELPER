public class Test2 {
    public int i=10;
    public int sum(){
        return getI()+10;
    }
    public int sum1(){
        return i+10;
    }
    static{
        System.out.println("A的静态代码块");
    }
    {
        System.out.println("A的代码块");
    }
    Test2(){
        System.out.println("A的无参构造器");
    }
    Test2(String name){
        System.out.println(name+"A有参");
        System.out.println("hhhh");
    }
    int hh(int a){
        return a+1;
    }

    public int getI() {
        return i;
    }
}
class Test3 extends Test2 {
    public int i=20;
//    public int sum(){
//        return i+20;
//    }
//    public int sum1(){
//        return i+10;
//    }
    static int k =5;
    static{
        System.out.println("B的静态代码块");
    }
    {
        System.out.println("B的代码块");
    }
    Test3(){
//        super("小红");
        this("小李");
        System.out.println("B的无参");
    }
    Test3(String name){
        System.out.println(name+"B有参");
    }

    @Override
    public int getI() {
        return i;
    }

    @Override
    int hh(int a) {
        return super.hh(a)+2;
    }
    void hh(int a,int b){
        System.out.println(a+b);
    }
}
