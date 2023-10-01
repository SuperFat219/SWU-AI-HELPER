#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXSIZE 20
#define N 1024

typedef struct Node
{
    struct Node *next;
    char num[MAXSIZE];//身份证号
    char name[MAXSIZE];//姓名
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