/*
 *Number:Chapter 1
 *Author:yzs
 *Time:2021.11.06 15:24
 */
public class Welcome{
    public static void main(String[] args) {
    System.out.println("1.1 ");
    System.out.println("Welcome to Java,Welcome to Computer Science");
    System.out.println("Programming is fun!");

    System.out.println("1.2");
    for (int i=0;i<5;i++)
    {
        System.out.println("Welcome to Java");
    }

    System.out.println("1.3");
    System.out.println("    J    A    V     V    A");
    System.out.println("    J   A A    V   V    A A");
    System.out.println("J   J  AAAAA    V V    AAAAA");
    System.out.println(" J J  A     A    V    A     A");

    System.out.println("1.4");
    System.out.println("a    a^2    a^3");
    for (int i=0;i<3;i++)
    {
    	System.out.printf("%d    %d    %d\n",i+1,(i+1)*(i+1),(i+1)*(i+1)*(i+1));
    }
    System.out.println("4    16   64");

    System.out.println("1.5");
    System.out.println((9.5*4.5-2.5*3)/(45.5-3.5));
    
    System.out.println("1.6");
    int sum=0;
    for (int i=1;i<=9;i++)
    {
    	sum+=i;
    }
    System.out.println("sum = "+sum);
        
    System.out.println("1.7");
    int flag=-1;
    double num1=1;
    double num2=3;
    double pi=0;
    while ((Math.abs(1/num1)-Math.abs(1/num2))>0.00001){
           pi+=1/num1;
          num1=num2*flag;
         num2+=2;
        flag=flag*(-1);
    }
    System.out.println("pi = "+4*pi);
    }
}