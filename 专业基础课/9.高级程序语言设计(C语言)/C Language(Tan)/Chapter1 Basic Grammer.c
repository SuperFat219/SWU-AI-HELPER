// 1.3 求两数较大者/三数
/*求两数
#include <stdio.h>
int main(void)  
{
    int a,b;
    scanf("%d,%d",&a,&b);
    if (a>b) printf("%d",a);
    else printf("%d",b);
    return 0;
}
*/
/*求三数
#include <stdio.h>
int max(int x,int y);
int main(void)
{
    int a,b,c,res1,res2,res;
    scanf("%d %d %d",&a,&b,&c);
    res1=max(a,b);
    res2=max(b,c);
    res=max(res1,res2);
    printf("%d",res);

    return 0;

}
int max(int x,int y)
{
    if (x>y) return x;
    else return y;
}
*/
//2.1 计算阶乘
/*
#include <stdio.h>
int main(void)
{
    int num,res=1;
    //printf("%d\n",res);
    scanf("%d",&num);
    while (num>=1)
    {
        res*=num;
        num-=1;
        //printf("%d\n",res);
    }
    printf("%d",res);
    return 0;
}
*/

//2.3 判断闰年
/*
#include <stdio.h>
int main()
{
    int year;
    scanf("%d",&year);
    if ((year%4==0 && year%100!=0) || (year%400==0)) printf("true");
    else printf("wrong");
    return 0;    
}
*/

//2.4 计算1-1/2+1/3……+1/99-1/100
/*
#include <stdio.h>
int main()
{
    double num=1,flag=1;
    double sum=1;
    while (num<100)
    {
        flag=flag*(-1);
        num+=1;
        sum+=flag*(1/num);  //如果定义num为int，两整形数据相除结果仍为整数（丢弃小数部分）
    }
    printf("%lf",sum);
    return 0;
}
*/

// 2.5 判断一个整数是否为素数
/*
#include <stdio.h>
#include <math.h>
int main()
{
    int i=2,num;
    scanf("%d",&num);
    while (i<sqrt((double)num))   // 求平方根，注意sqrt()的参数为 double 类型，这里要强制转换m的类型 
    {
        if (num%i==0) 
        {
            printf("No");break;
        }
        else i++;
    }
    if (i>sqrt((double)num)) printf("Yes");

    return 0;
}
*/

// Chapter 2.Exercises.4
// 4.1两数互换
// 法一
#include <stdio.h>
/*
int main()
{
    int a,b,c;
    scanf("%d %d",&a,&b);
    c=a;
    a=b;
    b=c;
    printf("%d ",a);
    printf("%d",b);
    return 0;
}
*/
//法二 （异或），不建立临时变量，a^b=c,a^c=b,b^c=a
/*
int main()
{
    int a,b,c;
    scanf("%d %d",&a,&b);
    a=a^b;
    b=b^a;
    a=a^b;
    printf("%d %d",a,b);
    return 0;
}
*/

//4.2 给十个数排序(升序)  冒泡排序  相邻数比较大小，每次循环即将最大数移至数组的最后
/*
int main()
{
    int i,j,k,a[10],num;
    for (i=0;i<=9;i++)
    {
        scanf("%d",&num);
        a[i]=num;
    }
    
    for (k=9;k>=0;k--)  printf("%d ",a[9-k]);
    printf("\n");
    for (i=0;i<=9;i++)
    {
        for (j=0;j<9-i;j++)
        {
            if (a[j]<a[j+1]) 
            {
                num=a[j];
                a[j]=a[j+1];
                a[j+1]=num;
            }
        }
    }
    for (k=9;k>=0;k--)  printf("%d ",a[9-k]);
    
    return 0;
}
*/

//4.7 最大公约数gcd,最小公倍数lcm  //定理：两个整数的最大公约数等于其中较小的那个数和两数相除余数的最大公约数。gcd(a,b) = gcd(b,a mod b)
//辗转相除法,有两整数a和b：① a%b得余数c② 若c=0，则b即为两数的最大公约数③ 若c≠0，则a=b，b=c，再回去执行①
// lcm=a*b/gcd
/*
int main()
{
    int a,b,c,gcd,lcm,m,n;
    scanf("%d %d",&a,&b);
    m=a;
    n=b;
    c=a%b;
    if (c==0) gcd==b;
    else {
        while (c){
            a=b;
            b=c;
            c=a%b;
        }
        gcd=b;
    }
    printf("gcd=%d\n",gcd);
    printf("lcm=%d",m*n/gcd);
}
*/

//Chapter4.Exercise
//4.9 计算位数+逆序输出
/*
int main()  //计算位数
{
    unsigned int num,len;
    scanf("%d",&num);
    for (len=1;num>0;len++)
    {
        num=num/10;
        if (num==0) break;
        //printf("%d %d\n",num,len);
    }
    printf("%d",len);
}

int main() //逆序输出
{
    int len(int x);
    int i,num,num2,length;
    scanf("%d",&num);
    //printf("%d\n",len(num));
    length=len(num);     //如果不用一个变量储存这个值的话，随着num的改变，len(num)会不停地在改变
    for (i=1;i<=length;i++)
    {
        //printf("%d ",i);
        num2=num%10;
        printf("%d",num2);
        num=num/10;
        //printf("%d\n",num);
        //printf("%d",num2);
    }
    
    return 0;
}
int len(int x)
{
    int len;
    for (len=0;x>0;len++)
    {
        x=x/10;
        //if (x==0) break;
        //printf("%d %d\n",num,len);
    }
    return len;
}
*/
/*
//#include <string.h>
int main()  //判断字符串长度(法一，用lenstr()函数) (法二：自己定义)
{
    char a[100];
    gets(a);
    int count=0;
    for (int i=0;a[i]!='\0';i++)
    {
        count++;
    }   
    printf("%d",count);
    return 0;
}
*/
//5.6 输出4*5的矩阵
/*
int main()
{
    int k=1;
    for (int i=1;i<=4;i++)
    {
        for (int j=1;j<=5;j++)
        {
            //if (k==3 && j==5) continue;
            printf("%d\t",k*j);
        }
        printf("\n");
        k+=1;
    }
    return 0;
}
*/

//5.7 求pai的近似值
/*
int main()
{
    int flag=1;
    double num=1,sum=1;  //flag=1;   //由于计算过程中有除法运算，所以全部定义为双精度数据结构
    while ((1/num)>=0.00000001)
    {
        flag=-flag;
        num+=2;
        sum+=1/(flag*num);
        //printf("%d ",num);
    }
    printf("%lf",sum*4);   //注意最后输出的格式化处理，整形/双精度

    return 0;
}
*/
//5.8 输出斐波那契数列
/*
int main()
{
    long long int a=1,b=1;
    for (int i=1;i<=10;i++)
    {
        printf("%d %d ",a,b);
        a=a+b;
        b=a+b;
    }
    return 0;
}
*/
//5.10 输出100-200的全部素数
/*
#include <math.h>
int main()
{
    for (int i=100;i<=200;i++)
    {
        int j=1;
        //printf("%lf ",sqrt((double)i));
        for (j=2;j<=sqrt(i);j++)
        {
            if (i%j==0) break;
        }
        if (j>sqrt(i)) printf("%d ",i);
    }
    return 0;
}
*/
//5.11 密码转换
/*
int main()
{
    char c;
    c=getchar();
    while (c != '\n')
    {
        //printf("%d ",c);
        // char a='C';
        // printf("%d",'W'<a<'Z'); // 这种算法会按照结合律运算，先计算'W'<'C',真值为0，再计算0<'Z',真值为1
        // printf("%d",a>'W'&&a<'Z'); //正确表达式
        if ((c>='a'&&c<='z')||('A'<c&&c<='Z'))
        {
            if ('W'<=c&&c<='Z' || 'w'<=c&&c<='z') c-=22;
            else c+=4;
        }
        //printf("%d ",c);
        printf("%c",c);
        c=getchar();
    }
    return 0;
}
*/

//Exercise 5
//5.4 统计一行字符中英文字母空格数字和其他字符的个数
/*
int main()
{
    char c;
    int alpha_number=0,num_number=0,else_number=0,space_number=0;
    while ((c=getchar()) != '\n')
    {
        if ((c>='A'&&c<='Z')||(c>='a'&&c<='z')) alpha_number+=1;
        else if (c==' ') space_number+=1;
        else if ('0'<=c&&c<='9') num_number+=1;
        else else_number+=1;
    }
    printf("%d %d %d %d",alpha_number,num_number,else_number,space_number);
    return 0;
}
*/
//5.5 求a+aa+aaa+aaa....
/*
#include <math.h>
int main()
{
    int a,n,sum,num;
    scanf("%d %d",&a,&n);
    sum=a;
    num=a;
    printf("%d+",a);
    for (int i=1;i<=(n-1);i++)
    {
        a=pow(10,i)*num+a;
        if (i==(n-1)) printf("%d=",a);
        else printf("%d+",a);
        sum+=a;
    }
    printf("%d",sum);
    return 0;
}
*/
//5.6 求1！+2！+3！+4！...
/*
int main()
{
    int n;
    long int sum=0;
    scanf("%d",&n);
    for (int i=1;i<=n;i++)
    {
        int res=1;
        for (int j=1;j<=i;j++) res=res*j;
        sum+=res;
    }
    printf("%d",sum);
    return 0;
}
*/
//5.8 水仙花数
/*
#include <math.h>
int main()
{
    int j,k,l;
    for (int i=100;i<=999;i++)
    {
        l=i;
        j=l%10;
        l=l/10;
        k=l%10;
        l=l/10;
        if ((pow(l,3)+pow(k,3)+pow(j,3))==i) printf("%d ",i);
    }
    return 0;
}
*/
//5.9  完数
/*
int main()
{
    for (int j=1;j<=1000;j++)
    {
        int sum=0;
        for (int i=1;i<j;i++)
        {
            if (j%i==0) sum+=i;
        }
        if (sum==j) printf("%d ",j);
    }
    return 0;
}
*/
//5.10
/*
int main()
{
    float f1=1,f2=2,f3;
    double sum=0;
    for (int i=1;i<=20;i++)
    {
        sum+=f2/f1;
        //printf("%lf ",f2/f1);
        f3=f1;
        f1=f2;
        f2=f3+f2;
    }
    printf("%lf",sum);
    return 0;
}
*/
//5.11 皮球反弹问题
/*
int main()
{
    double initial_height=100;
    double bounce_height=50,total_distance=100;
    for (int i=1;i<10;i++)
    {
        initial_height=initial_height/2;
        total_distance+=initial_height+bounce_height;
        bounce_height=bounce_height/2;
        //total_distance+=bounce_height;
        //initial_height=bounce_height;
    }
    printf("%.3lf %.6lf",total_distance,bounce_height);
    return 0;
}
*/
//5.12 猴子吃桃问题
/*
int main()
{
    int num=1;
    for (int i=1;i<=10;i++)
    {
        num=(num+1)*2;
    }
    printf("%d",num);
    return 0;
}
*/
