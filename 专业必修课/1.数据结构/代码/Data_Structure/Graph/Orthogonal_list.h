#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 100//最大顶点数
typedef char VertexType;//定义定点类型
typedef int EdgeType;//定义边的类型
typedef struct Node
{
    int tailvex;//弧起点下标
    int headvex;//弧终点下标
    struct Node *headlink;//指向终点相同的下一条边
    struct Node *taillink;//指向起点相同的下一条边
    int info;//权值
}EdgeNode;//边表结点

typedef struct VertexNode
{
    VertexType data;
    EdgeNode *firstin;
    EdgeNode *firstout;
}VertexNode;
typedef struct 
{
    VertexNode* Olist[MAXVEX];
    int node_num,edge_num;
}OLGraph;

void create_graph(OLGraph *G,FILE *ip);   
void print_graph(OLGraph *G,FILE *op);
int count_out_degree(VertexNode *node);
int count_in_degree(VertexNode *node);
int LocalVertex(OLGraph *G,VertexNode *node);//定位结点位置
void insert_edge(OLGraph *G,int v1,int v2,int weight);
void delete_edge(OLGraph *G,int v1,int v2);//删除v1->v2边

