#include <stdio.h>
#include <stdlib.h>
typedef struct Node
{
    struct Node *next;
    int data;
} Linklist;
int main()
{
    void CreateLinklist(Linklist * root);
    void PrintLinklist(Linklist * list);
    void CreateLinklist2(Linklist * root);

    Linklist *list = (Linklist *)malloc(sizeof(Linklist));
    list->next = NULL;
    CreateLinklist2(list);
    reverseLinklist(list);
    PrintLinklist(list);
}
/**
 * @brief Create a Linklist object
 * 头插法
 * @param list
 */
void CreateLinklist(Linklist *root)
{
    int new_value;
    while (scanf("%d", &new_value) != 0)
    {
        Linklist *node = (Linklist *)malloc(sizeof(Linklist));
        node->data = new_value;
        node->next = root->next;
        root->next = node;
    }
}
/**
 * @brief 尾插法
 *
 */
void CreateLinklist2(Linklist *root)
{
    Linklist *rear = root;
    int new_value;
    while (scanf("%d", &new_value) != 0)
    {
        Linklist *node = (Linklist *)malloc(sizeof(Linklist));
        node->data = new_value;
        rear->next = node;
        rear = node;
        rear->next = NULL;
    }
}
void PrintLinklist(Linklist *list)
{
    Linklist *p = list;
    while (p->next)
    {
        printf("%d ", p->next->data);
        p = p->next;
    }
}
/**
 * @brief
 *
 * @param list
 * @param i
 */
void insert_data(Linklist *list, int i)
{
}
/**
 * @brief 就地逆置
 * @param list
 */
void reverseLinklist(Linklist *root)
{
    Linklist *p = root->next;
    root->next = NULL;
    while (p)
    {
        Linklist *q = p->next;
        p->next = root->next;
        root->next = p;
        p = q;
    }
}