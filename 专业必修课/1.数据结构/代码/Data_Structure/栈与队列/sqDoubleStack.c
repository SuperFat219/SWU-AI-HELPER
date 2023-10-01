/*
**名称：实现顺序栈的基本操作——两栈共享空间
**用途：通常用于两个栈的空间需求有相反关系的时候
**Author：yzs
**Time:2021.10.2
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 20

typedef struct
{
    int *data;
    int top1;//指向第一个栈的栈顶
    int top2;//指向第二个栈的栈顶
}sqDoubleStack;

int main()
{
    sqDoubleStack *init_stack();
    void print_stack(sqDoubleStack *stack,FILE *op);
    void push(sqDoubleStack *stack,int value,int stackNum);
    int pop(sqDoubleStack *stack,int stacknum);

    FILE *ip,*op,*ip2;
    ip = fopen("input.txt","r");
    op = fopen("output.txt","w");
    ip2 = fopen("nodenum.txt","r");

    sqDoubleStack *stack=init_stack();
    int new_value;
    while (fscanf(ip,"%d",&new_value)!=0)
    {
        int node_num;
        if (fscanf(ip2,"%d",&node_num)!=0)
        {
            push(stack,new_value,node_num);
        }
    }
    //输出结果
    print_stack(stack,op);
    fprintf(op,"\n");
    int e=pop(stack,1);
    push(stack,99,1);
    int q=pop(stack,2);
    push(stack,98,2);
    print_stack(stack,op);
    printf("%d %d",e,q);

    fclose(ip);
    fclose(ip2);
    fclose(op);
}
sqDoubleStack *init_stack()//初始化
{
    //栈顶指针指向栈顶元素
    sqDoubleStack *stack=(sqDoubleStack*)malloc(sizeof(sqDoubleStack));
    stack->data=(int *)malloc(sizeof(int)*MAXSIZE);
    stack->top1=-1;
    stack->top2=MAXSIZE;//此时栈2为空
    return stack;
}
void print_stack(sqDoubleStack *stack,FILE *op)//输出两栈数据
{
    //输出栈1
    for (int i=0;i<=stack->top1;i++)
    {
        fprintf(op,"%d ",*(stack->data+i));
    }
    fprintf(op,"\n");
    //输出栈2
    for (int i=MAXSIZE-1;i>=stack->top2;i--)
    {
        fprintf(op,"%d ",*(stack->data+i));
    }
}
void push(sqDoubleStack *stack,int value,int stackNum)//进栈
{
    if (stack->top1+1==stack->top2)
    {
        return;
    }
    if (stackNum==1)
    {
        stack->top1++;
        *(stack->data+stack->top1)=value;
    }
    else if (stackNum==2)
    {
        stack->top2--;
        *(stack->data+stack->top2)=value;
    }
}
int pop(sqDoubleStack *stack,int stacknum)//出栈
{
    if (stacknum==1)
    {
        if (stack->top1 == -1)
        {
            printf("ERROR!");
        }
        else
        {
            stack->top1-=1;
            return *(stack->data+stack->top1+1);
        }
    }
    else if (stacknum==2)
    {
        if (stack->top2 == MAXSIZE)
        {
            printf("ERROR!");
        }
        else
        {
            stack->top2+=1;
            return *(stack->data+stack->top2-1);
        }
    }
}