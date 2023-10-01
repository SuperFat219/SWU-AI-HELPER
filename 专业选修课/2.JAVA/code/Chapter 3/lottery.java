/*
 *Number:3.15
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Random;
import java.util.Scanner;
public class lottery{
    public static void main(String[] args) {
        Random r = new Random();
        int res = r.nextInt(900)+100;
        System.out.println(res);
        //generate num between (a,b],use r.nextInt(b-a+1)+a
        Scanner input = new Scanner(System.in);
        int num=input.nextInt();
        if (num==res)
        {
            System.out.println("You win $10000");
        }
        else if (judgement1(res, num)==1)
        {
            System.out.println("You win $3000");
        }
        else if(judgement1(res,num)==2)
        {
            System.out.println("You win $1000");
        }
        else
        {
            System.out.println("Sorry,next time you will Win!");
        }

        }
    public static int judgement1(int r,int res)
    {
        int[] r_num = new int[3];
        int[] res_num = new int[3];
        r_num[2]=r%10;r=r/10;
        r_num[1]=r%10;r=r/10;
        r_num[0]=r;
        res_num[2]=res%10;res=res/10;
        res_num[1]=res%10;res=res/10;
        res_num[0]=res;
        int[] visited = new int[3];
        visited[0]=0;visited[1]=0;visited[2]=0;
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if (res_num[i] == r_num[j] && visited[j]!=1)
                {
                    visited[j]=1;
                }
            }
        }
        if (visited[0]==1 && visited[1]==1 && visited[2]==1)
        {
            return 1;
        }
        else if (visited[0]==1 || visited[1]==1 || visited[2]==1){
            return 2;
        }
        return 0;

    }
}