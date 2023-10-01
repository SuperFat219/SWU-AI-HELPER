#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXSIZE 30

typedef struct
{
    char data[MAXSIZE];//如果使用字符指针，字符串常量不可修改
    int top;//栈顶指针,指向栈顶上一个元素
}SqStack;

void push(SqStack *stack,char value);
char pop(SqStack *stack);
int search_index(char string[],int advance_list[],char str);
char *transform(SqStack *stack,FILE *ip,char result[]);//中缀表达式转后缀表达式
int calculate(char const *rpn_res,SqStack *stack);