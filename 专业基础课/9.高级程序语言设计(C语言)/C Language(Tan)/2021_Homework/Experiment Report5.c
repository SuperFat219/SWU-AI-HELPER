#include<stdio.h>
#include<string.h>
int main()
{
    void sort(char *string[5]);
    void number_sort(int **p,int n);
	char *string[]={"I am handsome","You are smart","Greatest Power","Lion Messi","Computer Language"};
    int n,**p,*number[20],data[20];
	sort(string); //对字符串进行排序
	for (int i=0;i<5;i++)   printf("%s\n",string[i]);  //输出排序后字符串
    scanf("%d",&n);
    for(int i=0;i<n;i++)
    {
        number[i]=&data[i];  //number[]存放各个整数的地址
    }
    for(int i=0;i<n;i++)
    {
        scanf("%d",number[i]); //输入n个整数
    }
    p=number;  //指针指向首元素的地址
    number_sort(p,n);  //对n个整数使用冒泡排序
    for (int i=0;i<n;i++,p++)
    {
        printf("%d ",**p); //输出排序后的整数序列
    }
	return 0;
}
void sort(char *string[5])  //字符串排序
{
	int i,j;
	for (i=0;i<5;i++)
    {
		for (j=i;j<5;j++)
        {
			if (strcmp(string[i], string[j])> 0)
            {
				char *temp = string[i]; 
				string[i] = string[j];  
				string[j] = temp;  //直接通过地址交换
			}
		}
	}
}
void number_sort(int **p,int n)  //冒泡排序
{
    int *temp;
    for(int i=0;i<n-1;i++) 
    {
        for(int j=0;j <n-1-i;j++)
        {
            if(**(p+j)>**(p+j+1))
                {
                    temp=*(p+j);
                    *(p+j)=*(p+j+1);
                    *(p+j+1)=temp;
                }
        }
    }
}