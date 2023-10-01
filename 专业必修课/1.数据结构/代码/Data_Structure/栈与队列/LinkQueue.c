/*
**名称：实现链队列的基本操作
**Author：yzs
**Time:2021.10.03
*/
#include <stdio.h>
#include <stdlib.h>
typedef int Elemtype;
typedef struct Node
{
    struct Node *next;
    Elemtype value;
}Node;
typedef struct 
{
    Node *front;//队头指针，指向头结点,value存放队列长度
    Node *rear;//队尾指针
}LinkQueue;

int main()
{
    void Enqueue(LinkQueue *sq,Elemtype value);
    Elemtype Delqueue(LinkQueue *sq);

    LinkQueue *lkq=(LinkQueue*)malloc(sizeof(LinkQueue));
    Node *root=(Node*)malloc(sizeof(Node));
    lkq->front=root;
    lkq->rear=root;
    lkq->front->next=NULL;
    lkq->rear->next=NULL;

    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");
    
    Elemtype new_value;
    while (fscanf(ip,"%d",&new_value)!=0)
    {
        Enqueue(lkq,new_value);
    }
    while (lkq->front !=lkq->rear)
    {
        fprintf(op,"%d ",Delqueue(lkq));
    }
    fclose(ip);
    fclose(op);

}

void Enqueue(LinkQueue *sq,Elemtype value)//进队
{
    Node *new_node=(Node*)malloc(sizeof(Node));
    new_node->value=value;
    new_node->next=NULL;
    sq->rear->next=new_node;
    sq->rear=new_node;//将新节点设为队尾
    sq->front->value+=1;
}

Elemtype Delqueue(LinkQueue *sq)//出队
{
    if (sq->front==sq->rear)
    {
        printf("The queue is NULL!\n");
    }
    else
    {
        Elemtype value=sq->front->next->value;
        Node *q=sq->front->next;
        sq->front->next=q->next;
        if (sq->rear==q)
        {
            sq->rear=sq->front;
        }
        free(q);
        sq->front->value-=1;
        return value;
    }
}