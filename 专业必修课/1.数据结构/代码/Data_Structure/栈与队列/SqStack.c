/*
**名称：实现顺序栈的基本操作
**Author：yzs
**Time:2021.9.30
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 20
typedef struct
{
    int *data;
    int top;//栈顶指针,指向栈顶元素的上一个位置
}SqStack;

int main()
{
    SqStack *init_stack(SqStack *stack,FILE *ip);
    void print_stack(SqStack *stack,FILE *op);
    void push(SqStack *stack,int value);
    int pop(SqStack *stack);

    FILE *ip,*op;
    ip = fopen("input.txt", "r");
    op = fopen("output.txt", "w");

    SqStack *stack;
    stack=(SqStack*)malloc(sizeof(SqStack));
    stack->data=(int *)malloc(sizeof(int)*MAXSIZE);
    stack=init_stack(stack,ip);
    int e=pop(stack);
    push(stack,9);
    fprintf(op,"%d\n",e);
    print_stack(stack,op);

    fclose(ip);
    //fclose(ip2);
    fclose(op);

}
SqStack *init_stack(SqStack *stack,FILE *ip)
{
    SqStack *p=stack;
    int new_value;
    for (int i=0;fscanf(ip,"%d",&new_value)!=0;i++)
    {
        *(p->data+i)=new_value;
        p->top+=1;
    }
    return p;
}
void print_stack(SqStack *stack,FILE *op)
{
    SqStack *p=stack;
    //printf("%d",p->top);
    for (int i=0;i<p->top;i++)
    {
        fprintf(op,"%d ",*(p->data+i));
    }
}
void push(SqStack *stack,int value)//进栈
{
    SqStack *p=stack;
    if (p->top > MAXSIZE)
    {
        return;
    }
    else
    {
        *(p->data+p->top)=value;
        p->top+=1;
    }
}
int pop(SqStack *stack)//出栈
{
    SqStack *p=stack;
    if (p->top!=0)
    {
        int value=*(p->data+p->top-1);
        p->top-=1;
        return value;
    }
}

