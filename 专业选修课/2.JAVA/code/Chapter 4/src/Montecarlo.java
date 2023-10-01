import java.util.Random;

/**
 * Time:2021.11.20
 * 4.44
 */
public class Montecarlo {
    public static void main(String[] args) {
        //长4宽2
        double count = 0;
        double area = 8;
        for (int i=0;i<1000000;i++)
        {
            double x = nextDouble(-2, 2);
            double y = nextDouble(-1, 1);
            //System.out.printf("%f %f\n",x,y);
            if (x < 0) {
                count+=1;
            }//y=-0.5x+1
            else if(x>0 && y>0 && y<-0.5*x+1){
                count+=1;
            }
        }
        System.out.printf("%f\n",count/1000000);
        System.out.printf("%f\n",5/area);
    }

    /**
     * 生成max到min范围的浮点数
     */
    public static double nextDouble(final double min, final double max) {
        return min + ((max - min) * new Random().nextDouble());
    }
}
