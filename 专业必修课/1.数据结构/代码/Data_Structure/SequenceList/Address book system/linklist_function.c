#include "linklist_function.h"

int linklist_length(Linklist* list)//计算链表元素个数
{
    Linklist *p=list;
    int length=0;
    for (;list->next!=NULL;)
    {
        length+=1;
    }
    return length;
}

void insert_linklist(Linklist *list,int index,Linklist *new_node)//在链表第index（从0计数）个元素后插入新节点
{
    Linklist *p=list;
    for (int i=0;i<index;i++)
    {
        p=p->next;
    }
    new_node->next=p->next;
    p->next=new_node;
}
void delete_linklist(Linklist *list,int index)//删除链表中第index个元素(1-length)
{
    Linklist *p=list;
    for (int i=1;i<index;i++)
    {
        p=p->next;//指向第index-1个元素
    }
    Linklist *q=p->next;
    p->next=p->next->next;
    free(q);
}