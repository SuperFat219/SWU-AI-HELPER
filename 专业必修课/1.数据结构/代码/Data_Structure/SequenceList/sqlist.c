/*
**名称：实现线性表顺序存储结构的基本操作
**Author：yzs
**Time:2021.9.28
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXLENGTH 20
typedef struct
{
    int *data;
    int length;
}Sqlist;

/*不准许在结构内赋初值,因为它本身是一个结构,而不是一个数据.
**如果被赋初值表示它是一个数值,所以要是想赋值需要在结构外赋值.
**因为申请内存空间的时候它不具备初始化的能力,只是将某部分的内存交给一个地址,所以不可以在结构内赋初值
*/

int main()
{
    void sqlist_print(FILE *op,Sqlist *sq);
    void init_list(FILE *ip,Sqlist *sq);
    void insert_list(Sqlist *sq,int i,int value);
    int del_value_list(Sqlist *sq,int i);
    int get_elem_list(Sqlist const sq,int i);
    Sqlist *list_merge(Sqlist *sq1,Sqlist *sq2);
    void sort_list(Sqlist *sq);


    Sqlist *sq;
    sq=(Sqlist*)malloc(sizeof(Sqlist));//要先为该结构体指针分配内存，否则即为空指针
    sq->data=(int*)malloc(sizeof(int)*MAXLENGTH);
    /*
    Sqlist *sq2;
    sq2=(Sqlist*)malloc(sizeof(Sqlist));//要先为该结构体指针分配内存，否则即为空指针
    sq2->data=(int*)malloc(sizeof(int)*MAXLENGTH);
    Sqlist *sq3;
    sq3=(Sqlist*)malloc(sizeof(Sqlist));//要先为该结构体指针分配内存，否则即为空指针
    sq3->data=(int*)malloc(sizeof(int)*MAXLENGTH);
    */
    FILE *ip,*op;
    //FILE *ip2;
    ip = fopen("input.txt", "r");
    op = fopen("output.txt", "w");
    //ip2 = fopen("input2.txt", "r");


    init_list(ip,sq);
    //init_list(ip2,sq2);
    //insert_list(sq,4,78);
    //sqlist_print(op,sq);
    //int e=del_value_list(sq,7);
    //int f=get_elem_list(*sq,2);
    sqlist_print(op,sq);
    fprintf(op,"\n");
    sort_list(sq);
    sqlist_print(op,sq);
    //fprintf(op,"\n");
    //sq3=list_merge(sq,sq2);
    //sqlist_print(op,sq3);
    //fprintf(op,"\n%d",f);

    fclose(ip);
    fclose(op);
}

void sqlist_print(FILE *op,Sqlist *sq)//输出顺序表
{
    for (int i=0;i<sq->length;i++)
    {
        //printf("%d ",*((sq->data)+i));
        fprintf(op,"%d ",*((sq->data)+i));//输出到文件
    }
}

void init_list(FILE* ip,Sqlist *sq)//初始化顺序表
{
    int i=0;
    //while (scanf("%d",sq->data+i)!=0)
    while (fscanf(ip,"%d",sq->data+i)!=0)//从文件读取输入
    {
        i+=1;
        sq->length+=1;
        if (sq->length >=MAXLENGTH)
        {
            sq->data=realloc(sq->data,sizeof(int)*(sq->length+1));
            //但是如果每次重新进行内存分配很耗费时间，所以最好还是提前预估好数组大小
        }
    }
}

void insert_list(Sqlist *sq,int i,int value)//插入操作，第i个位置从1下标开始计算，插入到第i个元素之后
{
    for (int index=sq->length-1;index>=i;index--)
    {
        *(sq->data+index+1)=*(sq->data+index);
    }
    *(sq->data+i)=value;
    sq->length+=1;
}

int del_value_list(Sqlist *sq,int i) //删除第i个元素
{
    int value=*(sq->data+i-1);
    for (int index=i-1;index<=sq->length-1;index++)
    {
        *(sq->data+index)=*(sq->data+index+1);
    }
    sq->length-=1;
    return value;
}

int get_elem_list(Sqlist const sq,int i)//获得第i个元素的值
{
    return *(sq.data+i-1);
}

Sqlist *list_merge(Sqlist *sq1,Sqlist *sq2) //将两个有序顺序表按序合并
{
    Sqlist *sq;
    sq=(Sqlist*)malloc(sizeof(Sqlist));
    sq->data=(int*)malloc(sizeof(int)*(sq1->length+sq2->length));
    int *p1=sq1->data;
    int *p2=sq2->data;
    int *p=sq->data;
    for ( ;p1<(sq1->data+sq1->length) && p2<(sq2->data+sq2->length); )
    {   
        sq->length+=1;
        if (*p1<*p2)  *p++=*p1++;
        else  *p++=*p2++;
    }
    if (p1<sq1->data+sq1->length)  
    {
        *p++=*p1++;
        sq->length+=1;
    }
    else 
    {
        *p++=*p2++;
        sq->length+=1;
    }

    return sq;
}
void sort_list(Sqlist *sq)//(顺序表排序(冒泡))
{
    for(int i=0;i<sq->length-1;i++)
    {
        for (int j=0;j<sq->length-i-1;j++)
        {
            if (*(sq->data+j)>*(sq->data+j+1))
            {
                int temp=*(sq->data+j);
                *(sq->data+j)=*(sq->data+j+1);
                *(sq->data+j+1)=temp;
            }
        }
    }
}