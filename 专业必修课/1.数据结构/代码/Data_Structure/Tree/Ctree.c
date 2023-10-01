/*
**名称：实现树的基本操作——孩子表示法，双亲孩子表示法
**Author：yzs
**Time:2021.10.09
*/
typedef struct child_node
{
    struct child_node *next;
    int child;//孩子节点下标
}childptr;//定义孩子节点
typedef struct 
{
    int data;//节点数据
    int parent;//双亲节点
    childptr *first_child;//首个孩子
}CTbox;//定义每个节点
typedef struct 
{
    CTbox nodes[50];
    int root,n;
}CTress;//定义树


