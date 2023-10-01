/*
**名称：已知二叉树的前序、中序，求后序/已知中序、后序求前序
**Author：yzs
**Time:2021.10.09 10.12
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 20
#define N 1024
typedef char Elemtype;
typedef struct BiTree_Node
{
    struct BiTree_Node *lchild,*rchild;
    Elemtype data;
}BiTNode;
int main()
{
    void buildTree(BiTNode **node,char *pre,char *mid,int len);
    void buildTree2(BiTNode **node,char *mid,char *post,int len);
    void preorder_print(BiTNode *tree,FILE *op);//前序遍历
    void postorder_print(BiTNode *tree,FILE *op);//后序遍历
    FILE *ip,*op;
    ip=fopen("input2.txt","r");
    op=fopen("output.txt","w");

    char pre[MAXSIZE],mid[MAXSIZE],post[MAXSIZE];
    //fgets(pre,N,ip);
    fgets(mid,N,ip);
    fgets(post,N,ip);

    BiTNode *tree;
    BiTNode **p;
    p=&tree;
    //buildTree(p,pre,mid,7);
    buildTree2(p,mid,post,7);
    preorder_print(tree,op);

    fclose(ip);
    fclose(op);

}
void buildTree(BiTNode **node,char *pre,char *mid,int len)
{
    /**
     * @brief 重构二叉树
     * @pre 前序
     * @mid 中序
     */
    if(len<0)      //当结尾下标<0时，没有子树
    {
        *node=NULL;
        return;
    }
    for(int i=0;i<=len;i++)   //遍历mid数组找到子树的root节点 并为其分配空间，赋值。
    {
        if(pre[0]==mid[i])    //中序遍历中找到root节点（前序第一个字符就是root节点）
        {
            *node=(BiTNode*)malloc(sizeof(BiTNode));
            (*node)->data=pre[0];
            buildTree(&(*node)->lchild,pre+1,mid,i-1);    //递归地找左子树的root节点
            buildTree(&(*node)->rchild,pre+i+1,mid+i+1,len-i-1);  //递归地找右子树的root节点
        }
    }
}
void buildTree2(BiTNode **node,char *mid,char *post,int len)
{
    /**
     * @brief 重构二叉树
     * @mid 中序
     * @post 后序
     */
    if(len<0)      //当结尾下标<0时，没有子树
    {
        *node=NULL;
        return;
    }
    for(int i=0;i<=len;i++)   //遍历mid数组找到子树的root节点 并为其分配空间，赋值。
    {
        if(post[len]==mid[i])    //中序遍历中找到root节点（前序第一个字符就是root节点）
        {
            *node=(BiTNode*)malloc(sizeof(BiTNode));
            (*node)->data=post[len];
            buildTree(&(*node)->lchild,mid,post,i-1);    //递归地找左子树的root节点
            buildTree(&(*node)->rchild,mid+i+1,post+i,len-i-1);  //递归地找右子树的root节点
        }
    }
}
void preorder_print(BiTNode *tree,FILE *op)//前序遍历
{
    if (tree==NULL)
    {
        return;
    }
    fprintf(op,"%c ",tree->data);
    //printf("%c",tree->data);
    preorder_print(tree->lchild,op);
    preorder_print(tree->rchild,op);
}
void inorder_print(BiTNode *tree,FILE *op)//中序遍历
{
    if (tree==NULL)
    {
        return;
    }
    inorder_print(tree->lchild,op);
    fprintf(op,"%c ",tree->data);
    //printf("%c",tree->data);
    inorder_print(tree->rchild,op);
}
void postorder_print(BiTNode *tree,FILE *op)//后序遍历
{
    if (tree==NULL)
    {
        return;
    }
    postorder_print(tree->lchild,op);
    postorder_print(tree->rchild,op);
    fprintf(op,"%c",tree->data);
    //printf("%c",tree->data);
}