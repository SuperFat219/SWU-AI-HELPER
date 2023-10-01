#include <stdio.h>
//6.1 逆序输出数组
/*
int main()
{
    // int n,a[n];
    // scanf("%d",&n);
    // for (int i=0;i<n;i++) a[i]=i;
    // for (int i=(n-1);i>=0;i--) printf("%d",a[i]);      数组大小不可以是变量，即不能临时指定数组大小，如需这样做，需要使用指针+动态分配malloc
    // return 0;
    int i,a[10];
    for (i=0;i<=9;i++) a[i]=i;
    for (i=9;i>=0;i--) printf("%d ",a[i]);
    return 0;
} 
*/
//6.2 冒泡排序 升序
/*
#include <stdio.h>
int main()
{
    int a[10],num;
    for (int i=0;i<=9;i++) scanf("%d",&a[i]);
    for (int i=0;i<=8;i++)   //十个数循环9次即可排序完成
    {
        for (int j=0;j<9-i;j++)   //十个数比较9-i次即将最大数放至最后
        {
            if (a[j]>a[j+1]) 
            {
                num=a[j];
                a[j]=a[j+1];
                a[j+1]=num;
            }
        }
    }
    for (int i=0;i<=9;i++) printf("%d ",a[i]);
    return 0;

}
*/
//选择排序 （升序）
/*
int main()
{
    int array[10];
    int MinIndex;
    for (int i=0;i<=9;i++) scanf("%d",&array[i]);//输入数组

    for (int i=0;i<9;i++)
    {
        MinIndex=i;
        for (int j=i+1;j<=9;j++)
        {
            if (array[MinIndex]>array[j]) MinIndex=j;
        }
        if (MinIndex!=i)
        {
            int num;
            num=array[i];
            array[i]=array[MinIndex];
            array[MinIndex]=num;
        }
    }
    for (int i=0;i<=9;i++) printf("%d ",array[i]);
    return 0;
}
*/

//6.4 将二维数组转置
/*
#include <stdio.h>
int main()
{
    int a[3][4],b[4][3];
    for (int i=0;i<=2;i++)
    {
        for (int j=0;j<=3;j++)
        {
            scanf("%d",&a[i][j]);
            //printf("%d",a[i][j]);
        }
        //printf("\n");
    }
    for (int i=0;i<=3;i++)
    {
        for (int j=0;j<=2;j++)
        {
            b[i][j]=a[j][i];
            printf("%d ",b[i][j]);
        }
        printf("\n");
    }
}
*/
//6.5 找出某矩阵中最大元素
/*
int main()
{
    int a[3][4];
    int max_number=0;
    for (int i=0;i<=2;i++)
    {
        for (int j=0;j<4;j++)
        {
            scanf("%d",&a[i][j]);
            if (a[i][j]>max_number) max_number=a[i][j];
        }
    }
    printf("max number is :%d",max_number);
    return 0;
}
*/
//6.8 输入一行字符，统计其中有多少个单词，单词之间用空格隔开
/*
int main()
{
    char word_array[1000];
    int num=0,space=0;
    gets(word_array);
    for (int i=0;(word_array[i])!='\0';i++)
    {
        if (word_array[i]==' ')
        {
            space=0;
        }
        else if(space==0)
        {
            space=1;
            num+=1;
        }
    }
    printf("%d",num);
    return 0;
}
*/
//6.9 找出三个字符串其中最大者
/*
#include <stdio.h>
#include <string.h>
int main()
{
    char string_array[3][100];
    char string[100];

    for (int i=0;i<=2;i++) scanf("%s",string_array[i]);
    if (strcmp(string_array[0],string_array[1])>0) strcpy(string,string_array[0]);
    else strcpy(string,string_array[1]);
    if (strcmp(string_array[2],string)>0) strcpy(string,string_array[2]);
    printf("%s",string);

    return 0;
}
*/


//Chapter 6 Exercise
//6.3 求矩阵对角线元素之和
/*
int main()
{
    int matrix[3][3],sum=0;
    for (int i=0;i<=2;i++)  //矩阵输入
    {
        for (int j=0;j<=2;j++)
        {
            scanf("%d",&matrix[i][j]);
            if ((i==j)||(i+j==2)) sum+=matrix[i][j];
        }
    }
    printf("%d",sum);
    return 0;
} 
*/

//6.4 输出杨辉三角
/*
int main()
{
    int array[100]={1};
    for (int i=0;i<=9;i++)
    {
        int copy_array[100];
        for (int i=0;array[i];i++)
        {
            copy_array[i]=array[i];
        }
        for (int j=0;j<=i;j++)
        {
            if ((j==0)||(j==i)) array[j]=1;
            else array[j]=copy_array[j-1]+copy_array[j];
        }
        for (int k=0;k<=i;k++) printf("%d ",array[k]);
        printf("\n");
    }
    return 0;
}
*/
//6.5 魔方阵(n为奇数)
/*
8 1 6
3 5 7
4 9 2
魔方阵的排列规律如下：
⑴将1放在第一行中间一列；
⑵从2开始直到n×n止各数依次按下列规则存放；每一个数存放的行比前一个数的行数减1，列数加1（例如上面的三阶魔方阵，5在4的上一行后一列）；
⑶如果上一个数的行数为1，则下一个数的行数为n（指最下一行）；例如1在第一行，则2应放在最下一行，列数同样加1；
⑷当上一个数的列数为n时，下一个数的列数应为1，行数减去1。例如2在第3行最后一列，则3应放在第二行第一列；
⑸如果按上面规则确定的位置上已有数(不为0)，或上一个数是第一行第n列时，则把下一个数放在上一个数的下面。
例如按上面的规定，4应该放在第1行第2列，但该位置已经被占据，所以4就放在3的下面。
*/
/*
int main()
{
    int n;
    //int check(int x,int y);
    int array[100][100]={0};
    scanf("%d",&n);
    int row=1,column=(n+1)/2;

    for (int i=1;i<=n*n;i++)
    {
        if (i==1) array[row][column]=i;
        else if (row==1 && column!=n) 
        {
            row=n;
            column+=1;
            if (array[row][column]!=0)
            {
                row=2;
                column-=1;
                array[row][column]=i;
            }
            else  array[row][column]=i;
        }
        else if (column==n && row!=1)
        {
            column=1;
            row-=1;
            if (array[row][column]!=0)
            {
                row+=2;
                column=n;
                array[row][column]=i;
            }
            else  array[row][column]=i;
        }
        else if (row==1 && column==n)
        {
            row+=1;
            array[row][column]=i;
        }
        else 
        {
            row-=1;
            column+=1;
            if (array[row][column]!=0)
            {
                row+=2;
                column-=1;
                array[row][column]=i;
            }
            else array[row][column]=i;
        }
    }
    for (int i=1;i<=n;i++)
    {
        for (int j=1;j<=n;j++) 
        {
            printf("%d ",array[i][j]);
        }
        printf("\n");
    }

    return 0;
}
*/
//6.8 找出一个二维数组的鞍点，该数为该行最大数，该列最小数
/*
int main()
{
    int a[3][3]={{1,2,3},{2,3,8},{3,4,5}};
    //int res[100];
    int max_number,columnindex=0,min_number,flag=0;
    for (int i=0;i<3;i++)
    {
        max_number=a[i][0];
        for (int j=1;j<3;j++)
        {
            if (a[i][j]>max_number)
            {
                max_number=a[i][j];
                columnindex=j;
            }
        }
        //rowindex=i;
        min_number=a[i][columnindex];
        for (int k=0;k<3;k++)
        {
            if (a[k][columnindex]<min_number) 
            {
                min_number=a[k][columnindex];
            }
        }
        if (min_number==max_number) 
        {
            flag=1;
            printf("the dot is %d",max_number);
        }
    }
    if (flag==0) printf("No such a dot!");
    return 0;
}
*/
//6.9 二分查找(有序数组从小到大)
//version 1.0  (算法不够优化)
/*
int main()
{
    int a[10]={11,25,36,48,52,130,170,280,490,510};
    int length=sizeof(a)/sizeof(a[0]);
    int index=-1;
    int num=length/2;
    int find_number;
    scanf("%d",&find_number);
    while (num !=0)
    {
        if (a[num]==find_number)
        {
            printf("the index is %d",num+1);
            break;
        }
        else if(a[num]>find_number)
        {
            num=num/2;
        }
        else if (a[num]<find_number)
        {
            num=(num+10)/2;
        }
    }

    return 0;
}
*/
//version 2.0
/*
int binary_search(int num,int array[],int length)
{
    int left,right,mid,count;
    int flag=0;
    left=0;right=length-1;
    //mid=(left+right)/2;
    while (left<=right)
    {
        count+=1;
        mid=(left+right)/2;
        if (array[mid]>num)
        {
            right=mid-1;
        }
        else if(array[mid]<num)
        {
            left=mid+1;
        }
        if (array[mid]==num)
        {
            printf("the index is %d\n",mid+1);
            printf("seach for %d times",count);
            flag=1;break;
        }
    }
    if (flag==0) printf("No result!");
    return 0;
}
int main()
{
    int binary_search(int num,int array[],int length);
    int num,array[100],length;
    scanf("%d",&length);
    for (int i=0;i<length;i++) scanf("%d",&array[i]);
    scanf("%d",&num);
    binary_search(num,array,length);
    return 0;
}
*/
//6.10三行文字，每行10个字符，分别统计其中大写小写数字空格其他字符的个数
/*
int main()
{
    char a[3][100]={'\0'};
    //scanf("%s",a[0]);
    //scanf("%s",a[1]);//这样写的话系统会自动把空格作为字符串分隔符，则逻辑错误。
    //scanf("%s",a[2]);
    gets(a[0]);
    gets(a[1]);
    gets(a[2]);
    for (int i=0;i<3;i++)
    {
        int length;
        int upper_word=0,lower_word=0,space=0,number=0,else_word=0;
        //length=sizeof(a[i])/sizeof(a[i][0]);
        //printf("%c\n",a[i][3]);
        for (int j=0;(a[i][j]) != '\0';j++)
        {
            if (a[i][j]>='A' && a[i][j]<='Z') upper_word+=1;
            else if (a[i][j]>='a' && a[i][j]<='z') lower_word+=1;
            else if (a[i][j]>='0' &&a[i][j]<='9') number+=1;
            else if (a[i][j]==' ') space+=1;
            else else_word+=1;
        }
        printf("the %d row have %d upper_word,\n%d lower_word, %d numbers,\n%d space,%d else-word.\n",i+1,upper_word,lower_word,number,space,else_word);
        printf("\n");
    }
    return 0;
}
*/
//6.11
/*
int main()
{
    for (int i=0;i<5;i++)
    {
        for (int j=0;j<i;j++) printf("  ");
        for (int k=0;k<5;k++) printf("* ");
        printf("\n");
    }
    return 0;
}
*/
//6.12 密码转换
/*
int main()
{
    char str[100];
    gets(str);
    printf("%s",str);
    printf("\n");
    for (int i=0;str[i]!='\0';i++)
    {
        if (str[i]>='a' && str[i]<='z') str[i]='z'-(str[i]-'a');
        else if (str[i]>='A' && str[i]<='Z') str[i]='Z'-(str[i]-'A');
    }
    printf("%s",str);
    return 0;
}
*/
//6.13 strcat函数
/*
#include <string.h>
int main()
{
    char a[100],b[100];
    gets(a);
    gets(b);
    int length1=strlen(a);
    int length2=strlen(b);
    int j=0;
    for (int i=length1;i<=length1+length2;i++)
    {
        
        a[i]=b[j];
        j+=1;
    }
    printf("%s",a);
    return 0;

}
*/
//6.14 strcmp函数
/*
#include <string.h>
int main()
{
    char str1[100],str2[100];
    int max(int x,int y);
    gets(str1);gets(str2);
    int max_length=max(strlen(str1),strlen(str2));
    int flag=0;
    for (int i=0;i<max_length;i++)
    {
        if (str1[i]==str2[i]) continue;
        else 
        {
            flag=1;
            printf("%d",str1[i]-str2[i]);
            break;
        }
    }
    if (flag==0) printf("0");
    return 0;
}
int max(int x,int y)
{
    int max_number;
    if (x>y)  max_number=x;
    else max_number=y;
    return max_number;
}
*/
//6.15 strcpy函数
/*
#include <string.h>
int main()
{
    char a[100],b[100];
    gets(a);gets(b);
    int length1=strlen(a),length2=strlen(b);
    for (int i=0;i<=strlen(b);i++)
    {
        a[i]=b[i];
    }
    printf("%s",a);
    //printf("%c",a[4]);
    return 0;
}
*/