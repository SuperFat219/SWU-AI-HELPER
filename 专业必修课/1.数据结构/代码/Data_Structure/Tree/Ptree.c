/*
**名称：实现树的基本操作——双亲表示法
**Author：yzs
**Time:2021.10.09
*/
#include <stdio.h>
#include <stdlib.h>
typedef struct 
{
    int data;//节点数据
    int parent;//节点双亲的位置
}PTnode;
typedef struct 
{
    PTnode nodes[50];//节点数组
    int root,n;//根的位置和节点数
}PTree;//双亲表示法



int main()
{
    
}