#include <stdio.h>
//7.6 (回溯+递归)
/*
int main()
{
    int age(int n);
    printf("%d",age(5));
    return 0;
}
int age(int n)
{
    int num;
    if (n==1) num=10;
    else num=age(n-1)+2;
    return num;
}
*/
//7.7 用递归求n！
/*
int main()
{
    int n;
    int calculation(int n);
    scanf("%d",&n);
    printf("%d",calculation(n));
    return 0;
}
int calculation(int n)
{
    int res;
    if (n<0) printf("Error!");
    if (n==0||n==1) res=1;
    else res=calculation(n-1)*n;
    return res;
}
*/
//7.8 汉诺塔问题
/*
int count=0;
int main()
{
    //int count=0;
    void haoni(int n,char one,char two,char three);//将n个盘从one借助two移到three
    int m;
    scanf("%d",&m);
    haoni(m,'A','B','C');
    printf("use all %d times",count);
    return 0;
}
void haoni(int n,char one,char two,char three)
{
    void move(char x, char y);
    if (n==1) move(one,three);
    else
    {
        haoni(n-1,one,three,two);
        move(one,three);
        haoni(n-1,two,one,three);
    }
}
void move(char x,char y)
{
    printf("%c --> %c\n",x,y);
    count+=1;
}
*/

//7.20 删除一个字符串中的指定字符
/*
int main()
{
    char string[30];
    char ch=' ';
    gets(string);
    int i,j;
    for (i=0,j=0;string[i]!='\0';i++)
    {
        if (string[i]!=ch) string[j++]=string[i];   //先调用j再自增
    }
    string[j]='\0';

    printf("%s",string);
    return 0;
}
*/
//Exercise 7.13 n阶勒让德多项式
/*
int main()
{
    int n;
    double x;
    double lerande(int n,double x);
    scanf("%d %lf",&n,&x);
    //lerande(n,x);
    printf("%.4lf",lerande(n,x));
    return 0;
}
double lerande(int n,double x)
{
    double res;
    if (n==0) res=1;
    else if(n==1) res=x;
    else  res=((2*n-1)*x-lerande(n-1,x)-(n-1)*lerande(n-2,x))/n;
    return res;
}
*/

//7.14 计算平均分方差
/*
int main()
{
    int grades[3][3];
    void average(int array[][3],int x);
    void find_the_max(int array[][3]);
    void fangcha(int array[][3]);

    for (int i=0;i<3;i++)
    {
        scanf("%d %d %d",&grades[i][0],&grades[i][1],&grades[i][2]);
    }
    //printf("%d",grades[2][3]);
    average(grades,3);
    find_the_max(grades);
    fangcha(grades);
    return 0;
}
void average(int array[][3],int x)
{  
    for (int j=0;j<x;j++)
    {
        double sum=0;
        for (int i=0;i<x;i++) sum+=array[i][j];
        printf("the %d class grade average is %.2lf\n",j+1,sum/3);
    }
}
void find_the_max(int array[][3])
{
    int max=0;
    int i_index[100],j_index[100];
    int count=0;
    for (int i=0;i<3;i++)
    {
        for (int j=0;j<3;j++)
        {
            if (array[i][j]>max) max=array[i][j];
        }
    }
    printf("%d\n",max);
    for (int i=0;i<3;i++)
    {
        for (int j=0;j<3;j++)
        {
            if (array[i][j]==max)
            {
                i_index[count]=i+1;j_index[count]=j+1;
                count+=1;
            }
        }
    }
    printf("%d\n",count);
    for (;count>0;count--)
    {
        printf("the %d student,the %d class\n",i_index[count-1],j_index[count-1]);
    }
}
void fangcha(int array[][3])
{
    double sum2=0,sum1=0;
    for (int i=0;i<3;i++)
    {
        double sum=0;
        for (int j=0;j<3;j++)
        {
            sum+=array[i][j];
        }
        sum2+=(sum/3)*(sum/3);
        sum1+=(sum/3);
        printf("the %d average grade is %.2lf\n",i+1,sum/3);
    }
    //printf("%lf %lf",sum1,sum2);
    printf("%lf",sum2/3-(sum1/3)*(sum1/3));
}
*/
//7.17 用递归法将整数n转换成字符串
/*
int main()
{
    int number;
    void convert(int n);
    scanf("%d",&number);
    if(number<0)
    {
        putchar('-');
        number=-number;
        //convert(number);
    }
    convert(number);
    return 0;
}
void convert(int n)
{
    int i=n/10;
    if (i!=0)  convert(i);
    putchar(n%10+'0');
}
*/

/*
#include <stdio.h>
int main()
{ void convert(int n);
  int number;
  printf("input an integer: ");
  scanf("%d",&number);
  printf("output: ");
  if (number<0)
    {putchar('-');putchar(' ');   // 先输出一个‘-’号和空格 
     number=-number;
    }
  convert(number);
  printf("\n");
  return 0;
}
void convert(int n)
{ int i;
  if ((i=n/10)!=0)
    convert(i);
  putchar(n%10+'0');
  putchar(32);
}
*/
//7.20
/*
int main()
{
    char string[100];
    char new_string[100];
    char del;
    char *p;p=string;

    gets(string);
    scanf("%c",&del);
    //int index=0;
    for (int i=0;*p!='\0';i++)
    {
        if (*p==del)
        {
            p++;
        }
        new_string[i]=*p;
        p++;
    }
    printf("%s",new_string);
    return 0;
}
*/
/*
int main()
{
    char string[100];
    char str;
    int i,j;
    gets(string);
    scanf("%c",&str);
    for (i=j=0;string[i]!='\0';i++)
    {
        if (string[i]!=str)
        {
            string[j++]=string[i];
        }
    }
    string[j]='\0';
    printf("%s",string);
    return 0;
}
*/
//进制转换
//#include <string.h>
#include <math.h>
int main() 
{
    // void ten_to_two(int num);
    // void two_to_ten(char num2[]);
    // void two_to_ten(int num2);
    // void ten_to_eight(int num);
    // int num10,num2;
    //char num2[100];
    //scanf("%d",&num10);
    //scanf("%s",num2);
    //scanf("%d",&num2);
    int ten_to(int num,int n);
    int to_ten(int num,int n);
    int transform(int num,int n1,int n2);
    int num;
    scanf("%d",&num);
    printf("%d",transform(num,10,2));
    //printf("%d",ten_to(num,2));

    return 0;
}
int ten_to(int num,int n)  //从十进制转为n进制
{
    //int num10_n[100];
    int left_num,i,j;
    int sum=0; //存放进制转换后的数
    for (i=0;num!=0;i++)
    {
        int temp=num;
        num=num/n;
        left_num=temp%n;
        sum+=left_num*pow(10,i);
        //num10_n[i]=left_num;
    }
    return sum;
    // for (j=i-1;j>=0;j--)
    // {
    //     printf("%d",num10_n[j]);
    // }
    //printf("\n");
}
int to_ten(int num,int n)  //从n进制转为十进制
{
    int sum=0;
    for (int i=0;num!=0;i++)
    {
        sum+=(num%10)*pow(n,i);
        num=num/10;
    }
    //printf("%d\n",sum);
    return sum;
}
int transform(int num,int n1,int n2)  //从n1进制转到n2进制
{
    int ten_to(int num,int n);
    int to_ten(int num,int n);
    if (n1==10)
    {
        return ten_to(num,n2);
    }
    if (n2==10)
    {
        return to_ten(num,n1);
    }
    else
    {
        int a=to_ten(num,n1);  //先换成十进制
        return ten_to(a,n2);  //再从十进制转为n2进制
    }
}
// void ten_to_two(int num) //十进制转二进制
// {
//     int num10_2[100];
//     int left_num,i,j;
//     for (i=0;num!=0;i++)
//     {
//         int temp=num;
//         num=num/2;
//         left_num=temp%2;
//         num10_2[i]=left_num;
//     }
//     for (j=i-1;j>=0;j--)
//     {
//         printf("%d",num10_2[j]);
//     }
//     printf("\n");
// }
// void two_to_ten(char num2[])  //二进制转十进制
// {
//     int length=strlen(num2);
//     int sum=0;
//     for (int i=0;i<length;i++)
//     {
//         sum+=(num2[i]-'0')*pow(2,length-i-1);
//     }
//     printf("%d\n",sum);
// }
// void two_to_ten(int num2)
// {
//     int sum=0;
//     for (int i=0;num2!=0;i++)
//     {
//         sum+=(num2%10)*pow(2,i);
//         num2=num2/10;
//     }
//     printf("%d\n",sum);
// }
// void ten_to_eight(int num) //十进制转八进制
// {
//     int num10_8[100];
//     int left_num,i,j;
//     for (i=0;num!=0;i++)
//     {
//         int temp=num;
//         num=num/8;
//         left_num=temp%8;
//         num10_8[i]=left_num;
//     }
//     for (j=i-1;j>=0;j--)
//     {
//         printf("%d",num10_8[j]);
//     }
//     printf("\n");
// }
// void eight_to_ten(int num8)  //八进制转十进制
// {
//     int sum=0;
//     for (int i=0;num8!=0;i++)
//     {
//         sum+=(num8%10)*pow(8,i);
//         num8=num8/10;
//     }
//     printf("%d\n",sum);
// }
