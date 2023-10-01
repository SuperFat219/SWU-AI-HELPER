#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 20

typedef char Elemtype;
typedef struct BiTree_Node
{
    struct BiTree_Node *lchild,*rchild,*parent;
    Elemtype data;
}BiTNode;
typedef struct
{
    Elemtype *data;
    int top;//栈顶指针,指向栈顶元素的上一个位置
}SqStack;
typedef struct 
{
    BiTNode *nodes[MAXSIZE];
    Elemtype front;//指向队头
    Elemtype rear;//指向队尾的下一个位置
}SqQueue;
typedef struct 
{
    SqStack *stack;
    int res;
}paras;
void init_tree(BiTNode **tree,FILE *ip,BiTNode *parent);
void preorder_print(BiTNode *tree,FILE *op);//前序遍历
void inorder_print(BiTNode *tree,FILE *op);
void postorder_print(BiTNode *tree,FILE *op);
paras* search_node(BiTNode *tree,paras *parameters,Elemtype p);//搜索树中到某个节点的路径(借助栈实现)
void push(SqStack *stack,Elemtype value);//进栈
int pop(SqStack *stack);//出栈
void print_stack(SqStack *stack,FILE *op);
BiTNode *Bitree_to_double_linklist(BiTNode* tree,BiTNode **lastnode);//按照中序遍历将二叉树转化为双向链表
int tree_depth(BiTNode *tree);
int count_node_num(BiTNode *tree);
int count_leafnode_num(BiTNode *tree);
void EnQueue(SqQueue *sq,BiTNode *node);//进队
void DelQueue(SqQueue *sq);//出队
void level_order_travel1(BiTNode *tree,FILE *op);//层序遍历，借助队列/数组实现
