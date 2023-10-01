#include <stdio.h>
#include <stdlib.h>
/*
**名称：实现线索二叉树的基本操作
**Author：yzs
**Time:2021.10.11
*/
typedef char Elemtype;
typedef enum{link,thread}PointerTag;//标志是指向后继还是孩子
typedef struct ThreadBinaryTree
{
    struct ThreadBinaryTree *lchild,*rchild;
    Elemtype data;
    PointerTag Ltag;
    PointerTag Rtag;
}TBtreeNode;
TBtreeNode *pre;
int main()
{
    void init_TBtree(TBtreeNode **tree,FILE *ip);
    void InThreading(TBtreeNode *tree);//中序遍历线索化
    void inorder_print(TBtreeNode *tree,FILE *op);

    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");
    TBtreeNode *tree;
    tree=(TBtreeNode*)malloc(sizeof(TBtreeNode));
    TBtreeNode **p;
    p=&tree;
    init_TBtree(p,ip);
    TBtreeNode* head = (TBtreeNode*)malloc(sizeof(TBtreeNode)); //构造一个头节点
    head -> Ltag = 0; // 头节点有左孩子，若树非空，则其左孩子为树根
    head -> Rtag = 1;
    head -> rchild = head; // 右指针指向自己

    if(!tree)
        head -> lchild = head; // 若树为空，则左指针也指向自己
    else{
        head -> lchild = tree; // 左孩子指向根节点
        pre = head;
        InThreading(tree); // 出来之后 (*pre) 是最右边的节点
        pre -> rchild = head; // 出来之后 (*pre) 是最右边的节点,其右孩子指向头结点
        pre -> Rtag = 1;
        head -> rchild = pre; // 头节点的右索引指向最右边的节点
    }
    inorder_print(head,op);
    fclose(ip);
    fclose(op);
}
void init_TBtree(TBtreeNode **tree,FILE *ip)
{
    //按照先序遍历进行数据初始化
    char ch=fgetc(ip);
    if (ch =='#')
    {
        *tree=NULL;
    }
    else
    {
        *tree=(TBtreeNode *)malloc(sizeof(TBtreeNode));//在堆中分配内存，子函数中指针作为变量处理，子函数结束即释放
        (*tree)->data=ch;
        init_TBtree(&(*tree)->lchild,ip);
        init_TBtree(&(*tree)->rchild,ip);
    } 
}
void InThreading(TBtreeNode *tree)//中序遍历线索化
{
    if (tree)
    {
        InThreading(tree->lchild);
        if (!tree->lchild)//没有左孩子,则让该节点的左孩子指针指向前驱
        {
            tree->Ltag=thread;
            tree->lchild=pre;
        }
        if (!pre->rchild)//如果前驱没有右孩子，前驱右孩子指向后继节点
        {
            pre->Rtag=thread;
            pre->rchild=tree;
        }
        pre=tree;
        InThreading(tree->rchild);
    }
}
//pre必须声明为全局变量，否则在每次函数调用以后都会内存释放恢复为头结点
void inorder_print(TBtreeNode *head,FILE *op)
{
    TBtreeNode *p;p=head->lchild;//p指向根节点
    while (p!=head)//空树或者遍历结束时P=head
    {
        while(p->Ltag==link)
        {
            p=p->lchild;//找到中序序列第一个节点
        }
        fprintf(op,"%c ",p->data);
        while (p->Rtag==thread && p->rchild!=head)
        {
            p=p->rchild;
            fprintf(op,"%c ",p->data);
        }
        p=p->rchild;//若遇到右孩子为真孩子时，则p进至其右子树
    }

}