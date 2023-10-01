public class ChainedExceptionDemo {
    public static void main(String[] args) {
        try{
            mathod1();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void mathod1() throws Exception {
        try{
            mathod2();
        }
        catch (Exception ex){
            throw new Exception("New from 1",ex);
        }
    }
    public static void mathod2() throws Exception {
        throw new Exception("new from 2");
    }
}
