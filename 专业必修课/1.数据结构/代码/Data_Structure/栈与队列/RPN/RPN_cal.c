/*
**名称：栈的应用——实现四则运算表达式求值
**用途：
**(1)中缀表达式转后缀表达式（只支持0-9以内的数）
**(2)对后缀表达式进行四则运算
**附属文件：RPN.c RPN.h
**Author：yzs
**Time:2021.10.02
*/
#include "RPN.h"
int main()
{
    FILE *ip,*op;
    ip = fopen("input.txt", "r");
    op = fopen("output.txt", "w");

    SqStack *stack;
    stack=(SqStack*)malloc(sizeof(SqStack));
    char expreesion[MAXSIZE];
    char *expr=expreesion;
    expr=transform(stack,ip,expreesion);
    /*    
    for (int i=0;i<strlen(expr);i++)
    {
        printf("%c ",*(expr+i));
    }*/
    int result=calculate(expr,stack);
    //printf("%d",strlen(expr));
    fprintf(op,"\n%d",result);

    fclose(ip);
    fclose(op);
}

//linux系统下编译多文件方式
/*
**gcc RPN.c  //生成目标文件RPN.o
**gcc RPN_cal.c  //生成目标文件 RPN_cal.o
**gcc RPN.o RPN_cal.o -o RPN_cal //链接多文件，生成可执行文件RPN_cal
**./RPN_cal 执行可执行文件 
*/

/*Makefile 
https://www.cnblogs.com/aiguona/p/9162500.html
*/