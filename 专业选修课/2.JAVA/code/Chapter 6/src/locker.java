/**
 * Time:2021.11.27
 * 6.23
 */
public class locker {
    public static void main(String[] args) {
        boolean[] lockers = new boolean[101];
//        System.out.println(lockers[0]);
        for (int i=1;i<=100;i++){
            for (int j=i;j<=100;j=j+i){
                lockers[j]= !lockers[j];
            }
        }
        int countOpen=0;
        for (int i=1;i<=100;i++)
        {
            if (lockers[i]){
                System.out.printf("%d ",i);
                countOpen++;
            }
        }
        System.out.print("is still open \n");
        System.out.println("still have "+countOpen+" lockers is open");
    }
}
