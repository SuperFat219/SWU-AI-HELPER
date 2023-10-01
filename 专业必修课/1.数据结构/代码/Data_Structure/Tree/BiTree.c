#include "BiTree.h"
/*
**名称：实现二叉树的基本操作、四种遍历方式，节点路径查找，二叉树转双向链表
**Author：yzs
**Time:2021.10.09 10.12
*/
int main()
{
    BiTNode *tree;
    BiTNode **p;
    p=&tree;

    paras *paramters;
    paramters=(paras*)malloc(sizeof(paras));
    paramters->stack=(SqStack *)malloc(sizeof(SqStack));
    paramters->stack->data=(char *)malloc(sizeof(char)*MAXSIZE);

    //tree->data='A';
    // tree->data='A';
    // tree->lchild='B';
    // tree->rchild='C';
    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");
    init_tree(p,ip,tree);
    preorder_print(tree,op);
    fprintf(op,"\n");
    inorder_print(tree,op);
    fprintf(op,"\n");
    postorder_print(tree,op);
    fprintf(op,"\n");
    level_order_travel1(tree,op);
    fprintf(op,"\n");
    fprintf(op,"%d\n",tree_depth(tree));
    fprintf(op,"%d\n",count_node_num(tree));
    fprintf(op,"%d\n",count_leafnode_num(tree));
    //paramters=search_node(tree,paramters,'I');
    //if (paramters->res==1) print_stack(paramters->stack,op);

    BiTNode *lastnode;
    BiTNode **pl;
    pl=&lastnode;
    BiTNode *list=Bitree_to_double_linklist(tree,pl);
    BiTNode *ph;
    ph=list;
    for (;ph!=NULL;)
    {
        fprintf(op,"%c ",ph->data);
        ph=ph->rchild;
    }
    fclose(ip);
    fclose(op);
}
//传递指针的指针，否则函数结束内存就释放了
void init_tree(BiTNode **tree,FILE *ip,BiTNode *parent)//三叉链表实现数据初始化
{
    //按照前序遍历序列输入节点建立二叉树
    //BiTNode *p=tree;
    char ch=fgetc(ip);
    if (ch =='#')
    {
        *tree=NULL;
    }
    else
    {
        *tree=(BiTNode *)malloc(sizeof(BiTNode));//在堆中分配内存，子函数中指针作为变量处理，子函数结束即释放
        (*tree)->data=ch;
        //(*tree)->parent=parent;
        init_tree(&(*tree)->lchild,ip,*tree);
        init_tree(&(*tree)->rchild,ip,*tree);
    }  
}

/*四种遍历方式*/
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
    fprintf(op,"%c ",tree->data);
    //printf("%c",tree->data);
}

void level_order_travel1(BiTNode *tree,FILE *op)//层序遍历，借助队列/数组实现
{
    SqQueue *queue;
    queue=(SqQueue*)malloc(sizeof(SqQueue));
    //queue->nodes=(BiTNode*)malloc(sizeof(BiTNode)*MAXSIZE);
    if (tree!=NULL)
    {
        EnQueue(queue,tree);
    }
    while (queue->front!=queue->rear)
    {
        fprintf(op,"%c ",queue->nodes[queue->front]->data);
        if (queue->nodes[queue->front]->lchild!=NULL)
        {
            EnQueue(queue,queue->nodes[queue->front]->lchild);
        }
        if (queue->nodes[queue->front]->rchild!=NULL)
        {
            EnQueue(queue,queue->nodes[queue->front]->rchild);
        }
        DelQueue(queue);
    }
}
//若要利用数组实现，用一个数组存放结构体数组，设置in/out标识符即可，不断将左右子树加入到其中
void FloorPrint(BiTNode* Tree)  //层序遍历(数组实现)
{
    BiTNode* temp[100];   //创建pTreeNode指针类型的指针数组
    int in = 0;
    int out = 0;

    temp[in++] = Tree;  //先保存二叉树根节点 

    while (in > out)
    {
        if (temp[out])
        {
            printf("%c",temp[out]->data);
            temp[in++] = temp[out]->lchild;
            temp[in++] = temp[out]->rchild;
        }
        out++;
    }
}
int tree_depth(BiTNode *tree)
{
    if (tree==NULL)
    {
        return 0;
    }
    else
    {
        int m=tree_depth(tree->lchild);
        int n=tree_depth(tree->rchild);
        if (m>n) return m+1;
        else return n+1;
    }
}
int count_node_num(BiTNode *tree)
{
    if (tree==NULL)
    {
        return 0;
    }
    return (count_node_num(tree->lchild)+count_node_num(tree->rchild)+1);
}
int count_leafnode_num(BiTNode *tree)
{
    if (tree==NULL)
    {
        return 0;
    }
    if (tree->lchild ==NULL && tree->rchild==NULL)
    {
        return 1;
    } 
    return count_leafnode_num(tree->lchild)+count_leafnode_num(tree->rchild);
}

paras* search_node(BiTNode *tree,paras *parameters,Elemtype p)//搜索树中到某个节点的路径(借助栈实现)
{
    if (tree==NULL)
    {
        parameters->res=0;
        return parameters;
    }
    push(parameters->stack,tree->data);
    if (tree->data == p) 
    {
        parameters->res=1;
        return parameters;
    }
    int b=0;
    if (tree->lchild !=NULL) b=search_node(tree->lchild, parameters,p)->res;
    if (!b && tree->rchild !=NULL) b=search_node(tree->rchild,parameters,p)->res;
    if (!b) pop(parameters->stack);
    return parameters;
}

BiTNode *Bitree_to_double_linklist(BiTNode* tree,BiTNode **lastnode)//按照中序遍历将二叉树转化为双向链表
{
    //tree为根节点
    BiTNode *head;
    //double_linklist *list=(double_linklist*)malloc(sizeof(double_linklist));//建立链表头结点
    if (tree==NULL)//若树为空，则返回空
    {
        *lastnode=NULL;
        return NULL;
    }
    if (tree->lchild==NULL)//若左子树为空，则头结点为根节点
    {
        head=tree;
    }
    else
    {//若左子树不为空，
        head=Bitree_to_double_linklist(tree->lchild,lastnode);
        tree->lchild=*lastnode;//根节点的前驱指向左子树的根节点
        (*lastnode)->rchild=tree;//左子树的尾节点连接根节点
    }
    if (tree->rchild==NULL)
    {
        *lastnode=tree;//若右子树为空，则尾节点为根节点
    }
    else
    {//若右子树不为空，则让根节点的后继为右子树的头结点
        tree->rchild=Bitree_to_double_linklist(tree->rchild,lastnode);
        tree->rchild->lchild=tree;//右子树的前驱为根节点
    }
    return head;
}

void push(SqStack *stack,Elemtype value)//进栈
{
    SqStack *p=stack;
    if (p->top > MAXSIZE)
    {
        return;
    }
    else
    {
        *(p->data+p->top)=value;
        p->top+=1;
    }
}
int pop(SqStack *stack)//出栈
{
    SqStack *p=stack;
    if (p->top!=0)
    {
        Elemtype value=*(p->data+p->top-1);
        p->top-=1;
        return value;
    }
}
void print_stack(SqStack *stack,FILE *op)
{
    SqStack *p=stack;
    //printf("%d",p->top);
    for (int i=0;i<p->top;i++)
    {
        fprintf(op,"%c ",*(p->data+i));
    }
}

void EnQueue(SqQueue *sq,BiTNode *node)//进队
{
    if ((sq->rear+1)%MAXSIZE == sq->front)
    {
        printf("The queue is full!\n");
    }
    else
    {
        //sq->nodes[sq->rear]=node;
        *(sq->nodes+sq->rear)=node;
        sq->rear=(sq->rear+1)%MAXSIZE;
    }
}
void DelQueue(SqQueue *sq)//出队
{
    if (sq->rear==sq->front)
    {
        printf("The queue is empty!\n");
    }
    else
    {
        //Elemtype value=*(sq->data+sq->front);
        sq->front=(sq->front+1)%MAXSIZE;
        //return value;
    }

}
