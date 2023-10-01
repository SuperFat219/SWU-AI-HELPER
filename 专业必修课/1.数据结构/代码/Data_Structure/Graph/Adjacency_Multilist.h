#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 100//最大顶点数
typedef char VertexType;//定义定点类型
typedef int EdgeType;//定义边的类型
typedef struct EdgeNode
{
    int ivex;
    int jvex;
    struct EdgeNode *ilink;//指向依附顶点ivex的下一条边
    struct EdgeNode *jlink;//指向依附顶点jvex的下一条边
    int info;//权值
}EdgeNode;
typedef struct
{
    VertexType data;
    EdgeNode *first_edge;
}VertexNode;
typedef struct 
{
    VertexNode* AMlist[MAXVEX];
    int node_num,edge_num;
}AMGraph;
void create_graph(AMGraph *G,FILE *ip);
void insert_edge(AMGraph *G,int v1,int v2,int info);
void print_graph(AMGraph *G,FILE *op);
int count_degree(VertexNode *node,int i);
void delete_egde(AMGraph *G,int v1,int v2);