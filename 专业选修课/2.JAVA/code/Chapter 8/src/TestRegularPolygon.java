public class TestRegularPolygon {
    public static void main(String[] args) {
        RegularPolygon A = new RegularPolygon();
        RegularPolygon B= new RegularPolygon(6,4);
        RegularPolygon C = new RegularPolygon(10,4,5.6,7.8);
        System.out.println("无参构造方法的周长为："+A.getPerimeter());
        System.out.println("无参构造方法的面积为："+A.getArea());
        System.out.println("side=1,n=6的正多边形周长为："+B.getPerimeter());
        System.out.println("side=1,n=6的正多边形面积为："+B.getArea());
        System.out.println("side=4,n=10,x=5.6,y=7.8的正多边形周长为："+C.getPerimeter());
        System.out.println("side=4,n=10,x=5.6,y=7.8的正多边形面积为："+C.getArea());

    }
}
