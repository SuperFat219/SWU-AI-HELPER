
#include "RPN.h"

char *transform(SqStack *stack,FILE *ip,char result[])//中缀表达式转后缀表达式
{
    void push(SqStack *stack,char value);//进栈
    char pop(SqStack *stack);//出栈
    int search_index(char string[],int advance_list[],char str);//判断运算符的优先级

    char string_list[6]={'+','-','*','/','(',')'};
    int advance_list[6]={1,1,2,2,0,0};
    char *expr=result;
    char new_value;
    int index=0;

    for (int i=0;(new_value=fgetc(ip))!=EOF;i++)
    {
        if (new_value >= '0' && new_value <= '9')
        {
            //fprintf(op,"%c ",new_value);//遇到数字直接输出
            result[index++]=new_value;
        }
        else 
        {
            if (stack->top ==0 || new_value=='(')
            {
                push(stack,new_value);//栈为空或为左括号时直接进栈
            }
            else if (new_value ==')')
            {
                while (stack->data[stack->top-1]!='(')
                {
                    //fprintf(op,"%c ",pop(stack));//一直到'('全部出栈输出
                    result[index++]=pop(stack);
                }
                pop(stack);//将'('出栈
            }
            else if (search_index(string_list,advance_list,new_value) <= search_index(string_list,advance_list,stack->data[stack->top-1]))
            {
                while (stack->top>0)//若优先级不高于栈顶元素则栈顶元素依次出栈并输出
                {
                    //fprintf(op,"%c ",pop(stack));
                    result[index++]=pop(stack);
                }
                push(stack,new_value);//当前符号进栈
            }
            else
            {
                push(stack,new_value);
            }
        }
    }
    while (stack->top>0)
    {
        //fprintf(op,"%c",pop(stack));
        result[index++]=pop(stack);

    }
    //printf("%d ",index);
    result[index-1]='\0';//消除换行符
    return expr;
}
/*
void print_stack(SqStack *stack,FILE *op)//输出栈中元素
{
    SqStack *p=stack;
    //printf("%d",p->top);
    for (int i=0;i<=p->top;i++)
    {
        fprintf(op,"%c",p->data[i]);
    }
}
*/
void push(SqStack *stack,char value)//进栈
{
    SqStack *p=stack;
    if (p->top == MAXSIZE)
    {
        return;
    }
    else
    {
        p->data[p->top]=value;
        p->top+=1;
    }
}
char pop(SqStack *stack)//出栈
{
    SqStack *p=stack;
    if (p->top>0)
    {
        char value=p->data[p->top-1];
        p->top-=1;
        return value;
    }
}
int search_index(char string[],int advance_list[],char str)//判断运算符的优先级
{
    for (int i=0;i<6;i++)
    {
        if (string[i]==str)
        {
            return advance_list[i];
        }
    }
    return -1;
}
int calculate(char const *rpn_res,SqStack *stack)
{
    int length=strlen(rpn_res);
    for (int i=0;i<length;i++)
    {
        if (rpn_res[i] >='0' && rpn_res[i] <='9')
        {
            push(stack,rpn_res[i]);
        }
        else if (rpn_res[i]=='+')
        {
            push(stack,((pop(stack)-'0')+(pop(stack)-'0'))+'0');
        }
        else if (rpn_res[i]=='-')
        {
            int value1=pop(stack)-'0';//减数
            int value2=pop(stack)-'0';//被减数
            //push(stack,(-((pop(stack)-'0')-(pop(stack)-'0')))+'0');
            push(stack,value2-value1+'0');
        }
        else if (rpn_res[i]=='*')
        {
            push(stack,((pop(stack)-'0') * (pop(stack)-'0'))+'0');
        }
        else if (rpn_res[i]=='/')
        {
            int value1=pop(stack)-'0';//除数
            int value2=pop(stack)-'0';//被除数
            push(stack,value2/value1+'0');
        }
    }
    return (pop(stack)-'0');
}