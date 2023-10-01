/*
**名称：实现线性表链式存储结构的基本操作（带根节点）——静态链表
**Author：yzs
**Time:2021.9.28
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 20 //数组实际长度只有MAXSIZE-2
typedef struct 
{
    int value;
    int cur;//游标
}component;

int main()
{
    void print_linklist(FILE *op,component *array,int head);
    void init_list(FILE *ip,component *array);
    void insert(component *array,int index,int value);
    void delete(component *array,int index);
    int list_length(component *array);
    void sort(component *array);


    FILE *ip,*op;
    FILE *ip2;
    ip = fopen("input.txt", "r");
    ip2 = fopen("input2.txt", "r");
    op = fopen("output.txt", "w");

    component array[MAXSIZE];//新建结构体数组作为静态链表
    init_list(ip,array);
    print_linklist(op,array,MAXSIZE-1);
    fprintf(op,"\n");

    insert(array,3,99);
    delete(array,5);
    print_linklist(op,array,MAXSIZE-1);

    sort(array);
    fprintf(op,"\n");
    print_linklist(op,array,MAXSIZE-1);
    //printf("%d",list_length(array));

    fclose(ip);
    fclose(ip2);
    fclose(op);
}

void init_list(FILE *ip,component *array)//链表数据初始化
{
    int mallocArr(component *array);
    for (int i=0; i<MAXSIZE; i++)
    {
        array[i].cur=i+1;//将每个数组分量链接到一起
        array[i].value=0;
    }
    array[MAXSIZE-1].cur=0;//链表最后一个结点(倒数第二个元素)的游标值为0

    int head=MAXSIZE-1;  //最后一个元素作为数据链表的头结点（不存储数据）
    int new_value;
    int temp=head;
    for (;fscanf(ip,"%d",&new_value)!=0;)
    {   
        int location=mallocArr(array);
        if (location)
        {
            array[location].value=new_value;
            array[temp].cur=location;
            temp=location;
        }
    }
    array[temp].cur=0;//新的链表最后一个结点的指针设置为0
}
int mallocArr(component *array)//分配内存空间进行存储
{
    int i=array[0].cur;
    if (array[0].cur) 
    {
        if (array[i].cur!=MAXSIZE-1)
        {
            array[0].cur=array[i].cur;
        }
        else array[0].cur=0;
    }
    return i;
}
void print_linklist(FILE *op,component *array,int head)//输出链表数据
{
    int temp=head;
    for (;array[temp].cur!=0;)
    {
        temp=array[temp].cur; 
        fprintf(op,"%d ",array[temp].value);  
    }
}
void insert(component *array,int index,int value)//插入到第index个位置之后
{
    int j=mallocArr(array);
    int l=MAXSIZE-1;
    if (j)
    {
        array[j].value=value;
        for (int k=1;k<=index;k++)
        {
            l=array[l].cur;
        }
        array[j].cur=array[l].cur;
        array[l].cur=j;
    }
}
void delete(component *array,int index)//删除index处的值
{
    void FreeArr(component *array,int k);
    int l=MAXSIZE-1;
    for (int i=1;i<index;i++)
    {
        l=array[l].cur;  //找到删除元素的上一个元素
    }
    int j=array[l].cur;  //删除元素的下标
    array[l].cur=array[j].cur;
    FreeArr(array,j);
}
void FreeArr(component *array,int k)//释放某节点的内存空间
{
    array[k].cur=array[0].cur;
    array[0].cur=k;
}
int list_length(component *array)//计算静态链表的长度
{
    int length=0;
    int l=MAXSIZE-1;
    for (;array[l].cur!=0;)
    {
        length+=1;
        l=array[l].cur;
    }
    return length;
}
void sort(component *array)//排序
{
    int k=MAXSIZE-1;
    for (int i=0;i<list_length(array);i++)
    {
        int temp=array[k].cur;
        int next=array[temp].cur;
        for (int j=0;j<list_length(array)-i-1;j++)
        {
            if (array[temp].value>array[next].value)
            {
                int temp_value=array[temp].value;
                array[temp].value=array[next].value;
                array[next].value=temp_value;
            }
            temp=next;
            next=array[next].cur;
        }
    }
}