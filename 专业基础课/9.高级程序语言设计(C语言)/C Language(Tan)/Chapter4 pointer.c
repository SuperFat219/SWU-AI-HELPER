#include <stdio.h>
//8.1
/*
int main()
{
    int *pointer1,*pointer2;  //整形数据的指针变量(储存整形数据的地址)
    int a=100,b=200;
    pointer1=&a;
    pointer2=&b;   //(指针=地址)
    printf("%d %d ",*pointer1,*pointer2);
    printf("%d",pointer1);
    return 0;
}
*/
//8.2 输入两个数，按从大到小顺序输出
/*
int main()
{
    int *p1,*p2,a,b;
    scanf("%d %d",&a,&b);
    p1=&a;p2=&b;
    if (a<b)
    {
        p1=&b;p2=&a; //a.b值并未发生交换，地址交换
    }
    printf("%d %d",*p1,*p2);
    return 0;
}
*/
//8.6 输出数组全部元素
/*
int main()  //通过数组名计算数组元素地址
{
    int a[10];
    for (int i=0;i<10;i++)
    {
        scanf("%d",a+i);  //等价于scanf("%d",&a[i])
    }
    for (int i=0;i<10;i++)
    {
        printf("%d",*(a+i));
    }
    return 0;
}
int main() //用指针变量指向数组元素
{
    int a[10];
    int *p;
    for (int i=0;i<10;i++)
    {
        scanf("%d",a+i);
    }
    for(p=a;p<(a+10);p++)  //此法费时更少，执行效率更快  //数组名a代表数组第一个元素的地址
    {
        printf("%d",*p);
    }
    return 0;
}
*/
/*
int main()
{
    int *p;
    int a[10];
    void sort(int array[],int length);
    for (p=a;p<(a+10);)
    {
        scanf("%d",p++);
    }
    sort(a,10);
    for (p=a;p<(a+10);)
    {
        printf("%d ",*p++);
    }
    return 0;
}
void sort(int array[],int length)
{
    for (int i=0;i<length;i++)
    {
        int maxindex=i;
        for (int j=i+1;j<length;j++)
        {
            if (*(array+j)>*(array+maxindex))
            maxindex=j;
        }
        if (maxindex!=i)
        {
            int temp;
            temp=*(array+maxindex);
            *(array+maxindex)=*(array+i);
            *(array+i)=temp;
        }
    }
}
*/
/*
int main()
{
    int a[3][4]={1,2,3,4,5,6,7,8,9,1,2,3};
    // int *p;
    // //p=a[0];
    // //printf("%d",*p);
    // for (p=a[0];p<(a[0]+12);)
    // {printf("%d ",*p++);
    // if ((p-a[0])%4==0)
    // {
    //     printf("\n");
    // }
    // }
    int (*p)[4],i,j;
    p=a;
    scanf("%d%d",&i,&j);
    printf("%d",*(*(p+i)+j));
    return 0;
}
*/
//8.14
/*
int main()
{
    int aver(int *p,int n);
    void search(int (*p)[3],int n);
    int score[3][3]={12,23,34,45,56,67,78,89,90};
    printf("%d\n",aver(*score,9));
    search(score,2);
    printf("\n%d",(12+23+34+45+56+67+78+89+90)/9);
    return 0;
}
int aver(int *p,int n)
{
    int sum=0,average;
    int *fp;
    fp=p;
    for (;fp<(p+n);)
    {
        sum+=*fp++;
    }
    average=sum/n;
    return average;
}
void search(int (*p)[3],int n)
{
    for (int i=0;i<3;i++)
    {
        printf("%d ",*(*(p+n)+i));
    }
}
*/
//指向函数的指针 //8.22
/*
int main()
{
    int max(int x,int y);
    int (*p)(int,int);
    p=max;
    int a,b;
    scanf("%d%d",&a,&b);
    int c;
    c=(*p)(a,b);
    printf("%d",c);
    return 0;
}
int max(int x,int y)
{
    if (x>y) return x;
    else return y;
}*/
//8.23
/*
int main()
{
    int max(int x,int y);
    int min(int x,int y);
    int (*p)(int,int);
    int a,b,c,choice;
    scanf("%d%d",&a,&b);
    scanf("%d",&choice);
    if (choice==1)
    {
        p=max;
        c=(*p)(a,b);
        printf("%d",c);
    }
    else 
    {
        p=min;
        c=(*p)(a,b);
        printf("%d",c);
    }
    return 0;
}
int max(int x,int y)
{
    if (x>y) return x;
    else return y;
}
int min(int x,int y)
{
    if (x<y) return x;
    else return y;
}*/
//8.24
/*
int main()
{
    void fun(int x,int y,int (*p)(int,int));
    int f1(int x,int y);
    int f2(int x,int y);
    int f3(int x,int y);
    int a,b,n;
    scanf("%d%d",&a,&b);
    scanf("%d",&n);
    if (n==1) fun(a,b,f1);
    if (n==2) fun(a,b,f2);
    else fun(a,b,f3);
}
void fun(int x,int y,int (*p)(int,int))
{
    int c;
    c=(*p)(x,y);
    printf("%d",c);
}
int f1(int x,int y)
{
    if (x>y) return x;
    else return y;
}
int f2(int x,int y)
{
    if (x<y) return x;
    else return y;
}
int f3(int x,int y)
{
    return (x+y);
}*/
// 求定积分的通用函数
/*
#include <math.h>
int main()
{
    float integral(float a,float b,float (*fun)(float,float));
    float f1(float x,float y);
    /**
     * @brief 求定积分
     * a：下限
     * b：上限
     * (*fun)(float) :function
     **/
/*
    integral(1,2,f1);
    return 0;

}
float integral(float a,float b,float (*fun)(float,float))
{
    float res;
    res=(*fun)(a,b);
    printf("%.4f",res);
}
float f1(float x,float y)
{
    float f1_c(float x);
    float result;
    result=f1_c(y)-f1_c(x);
    return result;
}
float f1_c(float x)
{
    return (x+pow(x,2)/2);
}
*/
//8.6 返回指针值的函数
//Example 8.25
/*
int main()
{
    int score[][4]={{30,40,50,60},{40,50,60,70},{50,60,70,80}};
    int *search(int (*p)[4],int k);
    int *p1;
    int i,k;
    scanf("%d",&k);
    p1=search(score,k);  //指向k行0列
    for (i=0;i<4;i++)
    {
        printf("%d ",*(p1+i));
    }
    return 0;

}
int *search(int (*p)[4],int k)
{
    int *fp;
    fp=*(p+k);
    return (fp);
}
*/
/*
int main()
{
    int score[][4]={20,30,40,50,60,70,80,90,20,40,60,90};
    int *search(int (*p)[4]);
    for (int i=0;i<3;i++)
    {
        int *fp;
        fp=search(score+i);  //对每一行进行搜索，如果有，则返回该行0列地址
        if (fp==*(score+i))
        {
            for (int j=0;j<4;j++)
            {
                printf("%d ",*(fp+j));
            }
        }
    }
    return 0;
}
int *search(int (*p)[4])
{
    int *pt;
    pt=NULL;
    for (int i=0;i<4;i++)
    {
        if (*(*p+i)<60) pt=*p;
    }
    return pt;
}
*/
//8.7 指针数组
//8.27
/*
#include <string.h>
int main()
{
    char *name[]={"asde","uidj","nsdf","anui","jahd"};
    void sort(char *name[],int n);
    sort(name,5);
    for (int i=0;i<5;i++)
    {
        printf("%s\n",name[i]);
    }
    return 0;
}
void sort(char *name[],int n)
{
    for (int i=0;i<n-1;i++)
    {
        int minindex=i;
        for (int j=i+1;j<n;j++)
        {
            if (strcmp(name[minindex],name[j])>0) 
            {
                minindex=j;
            }
        }
        if (minindex!=i)
        {
            char *temp;
            temp=name[minindex];
            name[minindex]=name[i];
            name[i]=temp;
        }
    }
}
*/
// 指针指针


//Exercise
//4.
/*
#include <stdlib.h>
int main()
{
    int n,m;
    void change(int *array,int n,int m);
    scanf("%d%d",&n,&m);
    int *array,*p;
    array=(int *)malloc(n*sizeof(int));
    for (p=array;p<(array+n);)
    {
        scanf("%d",p++);
    }
    for (p=array;p<(array+n);)
    {
        printf("%d ",*p++);
    }
    printf("\n");
    change(array,n,m);
    for (int i=0;i<n;i++)
    printf("%d ",array[i]);
    return 0;
}
void change(int *array,int n,int m)
{
    int *mid;
    mid=(int *)malloc(m*sizeof(int));
    for (int j=0;j<m;j++)
    {
        *(mid+j)=*(array+n-m+j);
    }
    for (int i=0;i<n-m;i++)
    {
        *(array+m+i)=*(array+i);
    }
    for (int i=0;i<m;i++)
    {
        *(array+i)=*(mid+i);
    }
}
#include<stdio.h>
int main()
{
    int n,a[100],m,i,j,t;
    scanf("%d",&n);
    for(i=0;i<n;i++)
    {
        scanf("%d",&a[i]);
    }
    scanf("%d",&m);
    for(i=0;i<m;i++)
    {
        t=a[n-1]; //储存最后一个值
        for(j=n-1;j>0;j--)
        {
            a[j]=a[j-1];  //依次向后移动一位
        }
        a[j]=t;  //将最后一位移到开头
    }
    for(i=0;i<n;i++)
    {
        if(i==n-1)
        printf("%d\n",a[i]);
        else
        printf("%d ",a[i]);
    }
    return 0;
}*/
//5. 
/*
int main()
{
    int n,people[100];
    int people_num[100]={0};  //用户报的数
    int count(int array[],int n);
    scanf("%d",&n);
    for (int i=1;i<=n;i++)
    {
        people[i]=i; //用户代号
    }
    int num[3]={1,2,3};
    int *p;p=num;
    while (count(people_num,n)<(n-1))
    {
        for (int i=1;i<=n;i++)
        {
            if (people_num[i]==3)
            {
                continue;
            }
            else 
            {
                people_num[i]=*p;
                if (p==(num+2)) p=num;
                else p++;
            }
        }
    }
    for (int i=1;i<=n;i++)
    {
        if (people_num[i]!=3)
        printf("%d",people[i]);
    }
    return 0;
}
int count(int array[],int n)
{
    int count_num=0;
    for (int i=1;i<=n;i++)
    {
        if (array[i]==3) count_num+=1;
    }
    return count_num;
}
*/
/*
#include <stdio.h>
int main()
{
    int n;
    printf("输入人数：\n");
    scanf("%d",&n);
    int a[1000];
    int i,temp;
    int count = 0;
    temp = n; 
    for(i = 0;i < n;i++)
    {
            a[i] = i+1;   
    }
    i = 0;
    while (n>1)
    {
        if(a[i] != 0)   //不改变第一次报数值，只是让数字3的人为0
        {
            count++;
        }
        if(count == 3)
        {
            a[i] = 0;  //让数到3的人为0
            count = 0;
            n--;  //人数－1
        }
        i++;
        if(i == temp)
        {
            i = 0;  //到最后一个人时置零从头开始
        }
    }
    for(i = 0;i < temp;i++)
    {
        if(a[i] != 0)  //此时所有数到3的人都清零了，只有剩下的那一个不为0
        {
            printf("余下的为：%d号\n",a[i]);
        }
    }
    return 0;
}
*/
//9. 矩阵转置
/*
#include <stdlib.h>
int main()
{
    int row1,column1,row2,column2;
    int **matric_translate(int **matric,int row,int column);
    int **matric_multiply(int **matric1,int **matric2,int row1,int column1,int row2,int column2);
    int **matric_input(int row,int column);
    void matric_print(int **matric,int row,int column);

    scanf("%d%d",&row1,&column1);
    scanf("%d%d",&row2,&column2);
    int **matric1;
    matric1=matric_input(row1,column1);matric_print(matric1,row1,column1);
    int **matric2;
    matric2=matric_input(row2,column2);matric_print(matric2,row2,column2);
    int **matric_t=matric_translate(matric1,row1,column1); matric_print(matric_t,column1,row1);//求转置
    int **new_matric=matric_multiply(matric1,matric2,row1,column1,row2,column2);
    matric_print(new_matric,row1,column2);
    return 0;
}
int **matric_input(int row,int column)
{
    int **matric;
    matric=(int**)malloc(sizeof(int*)*row);
    for(int i=0;i<row;i++)
    {
        matric[i]=(int*)malloc(sizeof(int)*column);
    }
    for(int i=0;i<row;i++)
    {
        for(int j=0;j<column;j++)
        {
            scanf("%d",&matric[i][j]);
        }
    }
    return matric;
}
void matric_print(int **matric,int row,int column)
{
    for(int i=0;i<row;i++)
    {
        for(int j=0;j<column;j++)
        {
            printf("%d ",matric[i][j]);
        }
        printf("\n");
    }
}

int **matric_translate(int **matric,int row,int column)
{
    int **matric_t;
    matric_t=(int**)malloc(sizeof(int*)*column);
    for(int i=0;i<column;i++)
    {
        matric_t[i]=(int*)malloc(sizeof(int)*row);
    }
    for(int i=0;i<row;i++)
    {
        for(int j=0;j<column;j++)
        {
            matric_t[j][i]=matric[i][j];
        }
    }
    return matric_t;
}
int **matric_multiply(int **matric1,int **matric2,int row1,int column1,int row2,int column2)
{
    if (column1!=row2)
    {
        printf("can not multiply");
    }
    else
    {
        int **new_matric;
        new_matric=(int**)malloc(sizeof(int*)*row1);
        for(int i=0;i<row1;i++)
        {
            new_matric[i]=(int*)malloc(sizeof(int)*column2);
        }
        for (int i=0;i<row1;i++)
        {
            for (int j=0;j<column2;j++)
            {
                int sum=0;
                for (int k=0;k<column1;k++)
                {
                    sum+=matric1[i][k]*matric2[k][j];
                }
                new_matric[i][j]=sum;
            }
        }
        return new_matric;
    }
}*/
//9.
/*
#include <stdlib.h>
int main()
{
    int row,column;
    int **matric;
    void Bubble_Sort(int *array,int length);
    matric=(int**)malloc(sizeof(int*)*5);
    for(int i=0;i<5;i++)
    {
        matric[i]=(int*)malloc(sizeof(int)*5);
    }
    int max_number=0;
    int min_number[4];
    for(int i=0;i<5;i++)
    {
        for(int j=0;j<5;j++)
        {
            scanf("%d",&matric[i][j]);
            if (matric[i][j]>max_number)max_number=matric[i][j];
        }
    }   
    printf("%d\n",max_number);
    for (int i=0;i<4;i++)
    {
        min_number[i]=matric[0][i];
    }
    Bubble_Sort(min_number,4);
    for (int i=0;i<5;i++)
    {
        for (int j=0;j<5;j++)
        {
            if (matric[i][j]<min_number[3]) min_number[3]=matric[i][j];
            else if (matric[i][j]<min_number[2]) min_number[2]=matric[i][j];
            else if (matric[i][j]<min_number[1]) min_number[1]=matric[i][j];
            else if (matric[i][j]<min_number[0]) min_number[0]=matric[i][j];
        }
    }
    for (int i=0;i<4;i++)
    {
        printf("%d ",min_number[i]);
    }
    return 0;
}
void Bubble_Sort(int *array,int length)
{
    int num;
    for (int i=0;i<length-1;i++)  //循环length-1次
    {
        for (int j=0;j<length-i-1;j++)  //第i（0，1，2...10）次循环时，已有i(0.1.2...10)个最大元素在数组最后,所以此时只需比较length-i-1次
        {
            if (*(array+j)>*(array+j+1))   //将最大元素移到最后
            {
                num=*(array+j);
                *(array+j)=*(array+j+1);  
                *(array+j+1)=num;
            }
        }
    }
}
*/
//13.矩形法求定积分
/*
#include <stdio.h>
#include <stdlib.h>
#include <math.h>//引入sin x,cos x,e^x的库
//使用指向函数的指针变量来复用一个通用函数
int main()
{
    int i,j,k,n,m;
    float res;
    float (*fun)(float);//定义指向函数的指针变量
    float integral (float a,float b,float (*fun)(float),int n);//n为将积分区间（b-a)分成n等分，当n的值越大的时候结果越精确，数学上的定义是取无穷大
    float fsin(float);//sin x函数的声明
    float fcos(float);//cos x函数的声明
    float fexp(float);//e^x 函数的声明
    //printf ("请输入积分的下限\n");
    scanf ("%d",&m);
    //printf ("请输入积分的上限\n");
    scanf ("%d",&n);
    //printf("请输入你要计算的函数的具体函数\n");
    //printf("1.sin(x) 2.cos(x) 3.e^x\n");
    scanf ("%d",&i);
    switch(i)
    {
    case 1:
    fun=fsin;//函数地址（入口）交给指针变量，灵活性强
    break;
    case 2:
    fun=fcos;
    break;
    case 3:
    fun=fexp;
    }
    res=integral(m,n,fun,200000);
    printf("res=%f",res);
    return 0;
}
float fsin(float x)
{
 // printf("fsinx=%f\n",x);
    return sin(x);
}
float fcos(float x)
{
 //printf("fcosx=%f\n",x);
    return cos(x);
}
float fexp(float x)
{
 //getchar();
 //printf("fexp=%f\n",x);
    return exp(x);
}
float integral(float a,float b,float (*fun)(float),int n)
{
    //矩形法计算,定积分转换为连续求和的形式
    int i=0;
    float x=a,sum=0;
    float h=(b-a)/n;

    for (i=1;i<=n;i++)
    {
        x=x+h;
        sum=sum+((*fun)(x)*h);  //矩形面积求和
    }
    return sum;
}
*/
//16.
/*
#include <math.h>
#include <string.h>
int main()
{
    char string[1000];
    int num[100];
    scanf("%s",string);
    int length=strlen(string);
    int count=0;
    int i=0;
    while(i<length)
    {
        if (string[i]>'0' && string[i]<='9')
        {
            int j,sum=0;
            int a=1;
            for (j=i;(string[j]>=0 && string[j]<='9');j++)
            {
                //len+=1;
            }
            j-=1;
            int index=j;
            for (;j>=i;j--)
            {
                sum+=(string[j]-'0')*(pow(10,a-1));
                a+=1;
            }
            num[count++]=sum;
            i=index+1;
        }
        else
        {
            i++;
        }
    }
    for (int k=0;k<count;k++)
    {
        printf("%d ",num[k]);
    }
    return 0;
}
*/
