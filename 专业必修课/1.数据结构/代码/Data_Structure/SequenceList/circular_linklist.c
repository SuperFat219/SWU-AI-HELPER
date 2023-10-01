/*
**名称：实现线性表链式存储结构的基本操作（带根节点）——循环链表
**操作：循环链表的合并
**Author：yzs
**Time:2021.9.28
*/
#include <stdio.h>
#include <stdlib.h>
typedef struct Node
{
    struct Node *next;
    int value;
}circular_linklist;

int main()
{
    circular_linklist* init_linklist(FILE *ip,circular_linklist *list);
    void print_linklist(FILE *op,circular_linklist *rear);
    circular_linklist* merge(circular_linklist *rear1,circular_linklist* rear2);
    void sort(circular_linklist *rear);

    FILE *ip,*op;
    FILE *ip2;
    ip = fopen("input.txt", "r");
    ip2 = fopen("input2.txt", "r");
    op = fopen("output.txt", "w");

    circular_linklist *lst=(circular_linklist*)malloc(sizeof(circular_linklist));
    lst->next=NULL;
    circular_linklist* rear1=init_linklist(ip,lst);

    circular_linklist *lst2=(circular_linklist*)malloc(sizeof(circular_linklist));
    lst2->next=NULL;
    circular_linklist * rear2=init_linklist(ip2,lst2);


    print_linklist(op,rear1);
    printf("%d ",rear1->next->value);
    fprintf(op,"\n");
    print_linklist(op,rear2);
    printf("%d ",rear2->next->value);


    rear2=merge(rear1,rear2);
    sort(rear2);
    fprintf(op,"\n");
    print_linklist(op,rear2);
    printf("%d ",rear2->next->value);


    fclose(ip);
    fclose(ip2);
    fclose(op);
}
circular_linklist* init_linklist(FILE *ip,circular_linklist *list)//初始化链表
{
    circular_linklist *p=list;//头指针
    int new_value;
    for (;fscanf(ip,"%d",&new_value)!=0;)
    {
        circular_linklist *node=(circular_linklist*)malloc(sizeof(circular_linklist));
        node->value=new_value;
        p->next=node;
        p=node;
        list->value+=1;//头节点存放链表长度
    }
    circular_linklist *rear=p;
    rear->next=list;//尾节点指向头元素
    return rear;//返回尾指针
}
void print_linklist(FILE *op,circular_linklist *rear)//输出链表
{
    circular_linklist *p=rear->next->next;
    for ( ;p!=rear->next;)
    {
        fprintf(op,"%d ",p->value); 
        p=p->next;
    }
}
circular_linklist* merge(circular_linklist *rear1,circular_linklist* rear2)//将链表2合并至1的后方
{
    //circular_linklist *rear1=list1;
    //circular_linklist *rear2=list2;
    rear1->next->value+=rear2->next->value;//长度
    circular_linklist* q=rear2->next;
    rear2->next=rear1->next;
    rear1->next=q->next;
    free(q);
    return rear2;
}
void sort(circular_linklist *rear)//链表排序
{
    int length=rear->next->value;
    circular_linklist *p=rear->next->next;
    for (int i=0;i<length-1;i++)
    {
        p=rear->next->next;
        for (int j=0;j<length-i-1;j++)
        {
            if (p->value > p->next->value)
            {
                int temp=p->value;
                p->value=p->next->value;
                p->next->value=temp;
            }
            p=p->next;
        }
    }
}