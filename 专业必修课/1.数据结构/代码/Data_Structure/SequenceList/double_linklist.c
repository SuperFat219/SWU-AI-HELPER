/*
**名称：实现线性表链式存储结构的基本操作（带根节点）——双向链表
**操作：循环链表的合并
**Author：yzs
**Time:2021.9.29
*/
#include <stdio.h>
#include <stdlib.h>
typedef struct Node
{
    struct Node *next;
    struct Node *prior;
    int value;
} double_linklist;
int main()
{
    double_linklist *init_list(FILE * ip, double_linklist * list); //初始化链表
    void print_list(FILE * op, double_linklist * list);
    void insert(double_linklist * list, int index, int value);
    void delete (double_linklist * list, int index); //删除第index个元素;

    FILE *ip, *op;
    // FILE *ip2;
    ip = fopen("input.txt", "r");
    // ip2 = fopen("input2.txt", "r");
    op = fopen("output.txt", "w");

    double_linklist *lst = (double_linklist *)malloc(sizeof(double_linklist));
    lst->next = NULL;
    lst->prior = NULL;
    lst = init_list(ip, lst);
    printf("%d ", lst->value);
    insert(lst, 14, 99);
    printf("%d ", lst->value);
    delete (lst, 5);
    printf("%d ", lst->value);
    print_list(op, lst);

    fclose(ip);
    // fclose(ip2);
    fclose(op);
}
double_linklist *init_list(FILE *ip, double_linklist *list) //初始化链表
/*头结点的前驱指向尾节点，尾节点的后继指向头结点,头节点存储链表的长度*/
{
    double_linklist *p = list;
    int new_value;
    for (; fscanf(ip, "%d", &new_value) != 0;)
    {
        double_linklist *node = (double_linklist *)malloc(sizeof(double_linklist));
        node->value = new_value;
        p->next = node;
        node->prior = p;
        p = node;
        list->value += 1; //根节点存放链表长度
    }
    p->next = list;
    list->prior = p;
    return list;
}
void print_list(FILE *op, double_linklist *list) //输出链表
{
    double_linklist *p = list->next;
    for (; p != list;)
    {
        fprintf(op, "%d ", p->value);
        p = p->next;
    }
}
void insert(double_linklist *list, int index, int value) //插入到第index个元素之后
{
    double_linklist *p = list;
    for (int i = 0; i < index; i++)
    {
        p = p->next;
    } //循环结束后，p指向链表中第i个元素
    double_linklist *new_node = (double_linklist *)malloc(sizeof(double_linklist));
    new_node->value = value;
    new_node->next = p->next;
    new_node->prior = p;
    p->next->prior = new_node;
    p->next = new_node;
    list->value += 1;
}
void delete (double_linklist *list, int index) //删除第index个元素
{
    double_linklist *p = list;
    for (int i = 0; i < index; i++)
    {
        p = p->next;
    } //循环结束后，p指向链表中第i个元素
    double_linklist *q = p;
    p->prior->next = p->next;
    p->next->prior = p->prior;
    free(q);
    list->value -= 1;
}
