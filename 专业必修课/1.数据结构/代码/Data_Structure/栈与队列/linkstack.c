/*
**名称：实现链栈的基本操作
**用途：通常用于两个栈的空间需求有相反关系的时候
**Author：yzs
**Time:2021.10.2
*/
#include <stdio.h>
#include <stdlib.h>
typedef struct node
{
    int value;
    struct node *next;
}Stacknode;
typedef struct 
{
    Stacknode *top;//头结点指向栈顶
    int count;//栈中元素个数
}Linkstack;

int main()
{
    void print_stack(Linkstack *stack,FILE *op);
    void push(Linkstack *stack,int value);
    int pop(Linkstack *stack);

    Linkstack *stack=(Linkstack *)malloc(sizeof(Linkstack));
    stack->top=NULL;
    FILE *ip,*op;
    ip = fopen("input.txt","r");
    op = fopen("output.txt","w");

    int new_value;
    while (fscanf(ip,"%d",&new_value)!=0)
    {
        push(stack,new_value);
    }
    print_stack(stack,op);
    int e=pop(stack);
    fprintf(op,"\n%d\n",e);
    print_stack(stack,op);

    fclose(ip);
    fclose(op);
    
}
void print_stack(Linkstack *stack,FILE *op)//先进后出输出
{
    /* 这种遍历方法会导致整个栈发生变化
    Linkstack *p=stack;
    for (;p->top!=NULL;)
    {
        fprintf(op,"%d ",p->top->value);
        p->top=p->top->next;
    }
    */
    Stacknode *p=stack->top;
    for (;p!=NULL;)
    {
       fprintf(op,"%d ",p->value);
       p=p->next;
    }
}
void push(Linkstack *stack,int value)
{
    Stacknode *new_node=(Stacknode *)malloc(sizeof(Stacknode));
    new_node->value=value;
    new_node->next=stack->top;
    stack->top=new_node;
    stack->count+=1;
}
int pop(Linkstack *stack)
{
    Stacknode *q=stack->top;
    stack->top=stack->top->next;
    int return_value=q->value;
    free(q);
    stack->count-=1;
    return return_value;
}