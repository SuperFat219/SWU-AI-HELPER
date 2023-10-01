/*
**名称：实现线性表链式存储结构的基本操作（带根节点）——单链表
**Author：yzs
**Time:2021.9.28
*/
#include <stdio.h>
#include <stdlib.h>
typedef struct Node
{
    struct Node *next;
    int value;
}Linklist;

int main()
{
    Linklist* init_linklist(FILE *ip,Linklist *list);
    void print_linklist(FILE *op,Linklist *list);
    void insert(Linklist *list,int index,int value);
    void delete(Linklist *list,int index);
    int get_elem_value(Linklist *list,int index);
    void sort(Linklist *list);
    Linklist* merge(Linklist *list1,Linklist *list2);

    FILE *ip,*op;
    FILE *ip2;
    ip = fopen("input.txt", "r");
    ip2 = fopen("input2.txt", "r");
    op = fopen("output.txt", "w");

    Linklist* lst;
    lst=(Linklist*)malloc(sizeof(Linklist));
    lst->next=NULL;
    lst=init_linklist(ip,lst);

    Linklist* lst2;
    lst2=(Linklist*)malloc(sizeof(Linklist));
    lst2->next=NULL;
    lst2=init_linklist(ip2,lst2);
    //printf("%d\n",lst->value);
    //insert(lst,4,2);
    //delete(lst,3);
    sort(lst);
    sort(lst2);
    print_linklist(op,lst);
    fprintf(op,"\n");
    print_linklist(op,lst2);
    fprintf(op,"\n");
    lst=merge(lst,lst2);
    print_linklist(op,lst);

    fclose(ip);
    fclose(ip2);
    fclose(op);

}

Linklist* init_linklist(FILE *ip,Linklist *list)//初始化链表
{
    Linklist *p=list;
    int new_value;
    for (;fscanf(ip,"%d",&new_value)!=0;)
    {
        Linklist *node=(Linklist*)malloc(sizeof(Linklist));
        node->value=new_value;
        p->next=node;
        p=node;
        list->value+=1;//根节点存放链表长度
    }
    p->next=NULL;
    return list;
}

void print_linklist(FILE *op,Linklist *list)//输出链表
{
    Linklist *p=list;
    for ( ;p->next!=NULL;)
    {
        fprintf(op,"%d ",p->next->value);
        p=p->next;
    }
}

void insert(Linklist *list,int index,int value)//插入元素
{
    Linklist *p=list;
    for (int i=0;i<index;i++)
    {
        p=p->next;
    }
    Linklist *new_node=(Linklist*)malloc(sizeof(Linklist));
    new_node->value=value;
    new_node->next=p->next;
    p->next=new_node;
    list->value+=1;
}

void delete(Linklist *list,int index)//删除元素
{
    Linklist *p=list;
    for (int i=1;i<index;i++)
    {
        p=p->next;
    }
    Linklist *q=p->next;
    p->next=q->next;
    free(q);
    list->value-=1;
}

int get_elem_value(Linklist *list,int index)//获取某一节点值
{
    Linklist *p=list;
    for (int i=0;i<index;i++)
    {
        p=p->next;
    }
    return p->value;
}
void sort(Linklist *list)//冒泡排序（双向链表会更方便）
{
    Linklist *p=list->next;
    for (int i=0;i<list->value-1;i++)
    {
        p=list->next;//切记每次循环时将p移至链表开头
        for (int j=0;j<list->value-i-1;j++)
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
Linklist* merge(Linklist *list1,Linklist *list2)//有序合并链表(合并至list1)
{
    Linklist *p1=list1->next;
    Linklist *p2=list2->next;
    Linklist *p=list1;
    while (p1 && p2)
    {
        if (p1->value > p2->value)
        {
            p->next=p2;
            p=p->next;
            p2=p2->next;
        }
        else
        {
            p->next=p1;
            p=p->next;
            p1=p1->next;
        }
    }
    while (p1)  
    { 
        p->next=p1;
        p1=p1->next;
        p=p->next;
    }
    while (p2) 
    {
        p->next=p2;
        p2=p2->next;
        p=p->next;
    }
    p->next=NULL;

    list1->value=list1->value+list2->value;
    Linklist *q=list2;
    free(q);
    return list1;
}