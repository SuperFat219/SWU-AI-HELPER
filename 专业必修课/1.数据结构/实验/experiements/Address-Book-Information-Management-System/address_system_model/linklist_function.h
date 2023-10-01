#ifndef __LINKLIST_FUNCTION_H__
#define __LINKLIST_FUNCTION_H__
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXSIZE 50
#define N 1024

typedef struct Node
{
    struct Node *next;
    char num[MAXSIZE];//身份证号  
    char name[MAXSIZE];//姓名，只能为英文，如果是中文在排序时会出错，编码问题暂未搞明白
    char gender[MAXSIZE];//性别
    char phone_number[MAXSIZE];//手机号
    char address[MAXSIZE];//地址
}Linklist;

typedef struct 
{
    Linklist *root;//头结点
    Linklist *rear;//尾指针，始终指向链表的最后一个元素
}Index_linklist;//A-Z 26个字母为索引，分别对应一个链表

int linklist_length(Linklist* list);//计算链表元素个数
void insert_linklist(Linklist *list,int index,Linklist *new_node);//在链表第index（从0计数）个元素后插入新节点
void delete_linklist(Linklist *list,int index);//删除链表中第index个元素(1-length)
#endif // __LINKLIST_FUNTION_H__
