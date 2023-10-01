/*
**名称：实现循环队列的基本操作
**Author：yzs
**Time:2021.10.03
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 20
typedef int Elemtype;
typedef struct 
{
    Elemtype *data;
    Elemtype front;//指向队头
    Elemtype rear;//指向队尾的下一个位置
}SqQueue;

int main()
{

    void init_queue(SqQueue *sq);//队列初始化
    Elemtype Queue_length(SqQueue *sq);//计算队列长度
    void EnQueue(SqQueue *sq,Elemtype value);//进队
    Elemtype DelQueue(SqQueue *sq);//出队

    SqQueue *queue=(SqQueue *)malloc(sizeof(SqQueue));
    init_queue(queue);

    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");
    
    Elemtype new_value;
    while (fscanf(ip,"%d",&new_value)!=0)
    {
        EnQueue(queue,new_value);
    }
    while (queue->front !=queue->rear)
    {
        fprintf(op,"%d ",DelQueue(queue));
    }
    fclose(ip);
    fclose(op);
}
void init_queue(SqQueue *sq)//队列初始化
{
    sq->data=(Elemtype*)malloc(sizeof(Elemtype)*MAXSIZE);
    sq->front=0;
    sq->rear=0;
}
Elemtype Queue_length(SqQueue *sq)//计算队列长度
{
    return (sq->rear+MAXSIZE-sq->rear)%MAXSIZE;
}
void EnQueue(SqQueue *sq,Elemtype value)//进队
{
    if ((sq->rear+1)%MAXSIZE == sq->front)
    {
        printf("The queue is full!\n");
    }
    else
    {
        *(sq->data+sq->rear)=value;
        sq->rear=(sq->rear+1)%MAXSIZE;
    }
}
Elemtype DelQueue(SqQueue *sq)//出队
{
    if (sq->rear==sq->front)
    {
        printf("The queue is empty!\n");
    }
    else
    {
        Elemtype value=*(sq->data+sq->front);
        sq->front=(sq->front+1)%MAXSIZE;
        return value;
    }

}