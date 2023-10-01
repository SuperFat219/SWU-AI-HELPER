//#include <stdio.h>

// int main()
// {
//     int n;
//     printf("This is a test file!\r\nInput a number: ");
//     scanf("%d",&n);
//     printf("The number is %d",n);
//     return 0;
// }

/*
#include <stdio.h>
int main(void)
{
    int num;
    num=1;
    printf("i am a simple\n");
    printf("computer.\n");
    printf("My number is %d",num);

    return 0;
}
*/
/*
#include <stdio.h>
#include <math.h>
int main()
{
    char a='C';
    printf("%d",'W'<a<'Z'); // 这种算法会按照结合律运算，先计算'W'<'C',真值为0，再计算0<'Z',真值为1
    printf("%d",a>'W'&&a<'Z');
    return 0;
}
*/

//#include <stdio.h>
// int main()
// {
//     char num="1235";
//     for (int i=1;i<=4;i++) printf("%s",num[i]);
//     return 0;
// }
/*
int main()
{
    unsigned long long int sum2=0,sum3,x;
    //int len(int x);
    scanf("%d",&x);
    while (x!=0)
    {
        sum3=x%10;
        sum2+=sum3;
        x=x/10;
        //printf("%d ",sum2);
    }
    printf("%d",sum2);
    return 0;
}
// int len(int x)
// {
//     int len;
//     for (len=0;x>0;len++)
//     {
//         x=x/10;
//         //if (x==0) break;
//         //printf("%d %d\n",num,len);
//     }
//     return len;
// }
*/
// #include <stdlib.h>
// int main()
// {
// 	char* p;
// 	int n;
// 	scanf("%d", &n);
// 	p = (char*)malloc(sizeof(char) * n);
//     for (int i=0;i<n;i++)
//     {
//         scanf("%s",p[i]);
//     }
//     for (int j=0;j<n;j++) printf("%s\n",p[j]);
//     return 0;
// }
/*
int main()
{
    char a[2][10][3];
    gets(a[0][0]);
    gets(a[1][0]);
    printf("%s",a[0][0]);
    return 0;

}

#include <time.h>
#include<stdio.h>
int main()
{
    time_t now;
    time(&now);
	void sort(int a[],int n);
	int i,j,k;
	int array[10]={3,2,7,78,45,23,-7,58,39,29}; 
	sort(array,10);
	printf("the sorted numbers:\n");
	for(i=0;i<10;i++)
	{
		printf("%d\t",array[i]);
	}
	printf("\n");
    printf("Program build time: %s\n", __TIMESTAMP__);
    printf("Current local time: %s", ctime(&now));
	return 0;
	 
}
void sort(int a[],int n)
{
	int i,j,k;
	for(i=0;i<n-1;i++)
	{
		for(j=0;j<n-i-1;j++)
		{
			if(a[j]>a[j+1])
			{
				k=a[j];
				a[j]=a[j+1];
				a[j+1]=k;
			}
		}
	}
}
*/
//二进制加减法
#include <stdio.h>
#include <string.h>
int main()
{
    char* plus(char *num1,char *num2);
    char pre_num1[100];
    char pre_num2[100];
    char num1[100]={0};
    char num2[100]={0};
    scanf("%s",pre_num1);
    scanf("%s",pre_num2);
    int l1=strlen(pre_num1);
    int l2=strlen(pre_num2);
    if (l1>l2)
    {
        int minus_num=l1-l2,i,j;
        int k=0;
        for (i=0;i<minus_num;i++) num2[i]='0';
        for (j=i;j<i+l2;j++) num2[j]=pre_num2[k++];
        strcpy(num1,pre_num1);
    }
    else 
    {
        int minus_num=l2-l1,i,j;
        int k=0;
        for (i=0;i<minus_num;i++) num1[i]='0';
        for (j=i;j<i+l1;j++) num1[j]=pre_num1[k++];
        strcpy(num2,pre_num2);
    }
    char* ph;
    ph=plus(num1,num2);
    return 0;
}
char* plus(char *num1,char *num2)  //二进制加法
{
    char plus_law2(char a,char b,char *p);
    int max(int a,int b);
    char plus_law(char a,char b,char *p);
    int times=strlen(num1);
    char res[100];
    char final_res[100]; 
    char addtional_num[1]={'0'};  //借位位
    char *p;
    p=addtional_num;
    for (int a=times;a>0;a--)
    {
        //char middle_num=*p;
        char temp=*p;
        res[a]=plus_law2(temp,plus_law(num1[a-1],num2[a-1],p),p);
    }
    if (*p=='1')
    {
        res[0]=*p;
        for (int a=0;a<=times;a++) printf("%c",res[a]);
    }
    else
    {
        for (int a=1;a<=times;a++) printf("%c",res[a]);
    }
    char *ph;
    ph=res;
    return ph;
}
char plus_law(char a,char b,char *p)  //处理加法位
{
    if (a=='0' && b=='0') 
    {
        *p='0';
        return '0';
    }
    else if ((a=='1'&&b=='0')||(a=='0'&&b=='1')) 
    {
        *p='0';
        return '1';
    }
    else 
    {
        *p='1';
        return '0';
    }
}
char plus_law2(char a,char b,char *p)   //处理进位加法
{
    if (a=='0' && b=='0') 
    {
        //*p=0;
        return '0';
    }
    else if ((a=='1'&&b=='0')||(a=='0'&&b=='1')) 
    {
        //*p=0;
        return '1';
    }
    else 
    {
        *p+=1;
        return '0';
    }
}
int max(int a,int b)
{
    if(a>b) return a;
    else return b;
}