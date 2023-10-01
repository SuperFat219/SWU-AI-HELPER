/*
**名称：实现树的基本操作——孩子兄弟表示法
**Author：yzs
**Time:2021.10.09
*/
typedef struct CSnode
{
    struct CSnode *first_child,*rightsib;//第一个孩子和右兄弟
    int data;//节点数据
}CSNode;
typedef struct 
{
    CSNode nodes[50];
    int root,n;
}CStree;

