public class TestRectangle {
    public static void main(String[] args) {
        Rectangle A = new Rectangle(4,40);
        Rectangle B = new Rectangle(3.5,35.9);
        System.out.println("A的宽为："+A.getWidth());
        System.out.println("A的高为："+A.getHeight());
        System.out.println("A的面积为："+A.getArea());
        System.out.println("A的周长为："+A.getPerimeter());
        System.out.println("B的宽为："+B.getWidth());
        System.out.println("B的高为："+B.getHeight());
        System.out.println("B的面积为："+B.getArea());
        System.out.println("B的周长为："+B.getPerimeter());
    }
}
