#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 20//最大顶点数
#define INFINITY 65535
typedef char VertexType;//定义定点类型
typedef int EdgeType;//定义边的类型
typedef struct 
{
    VertexType vexs[MAXVEX];
    EdgeType arc[MAXVEX][MAXVEX];//邻接矩阵
    int Nodenum,Edgenum;//图中当前的顶点数和边数
}Mgraph;
typedef struct EdgeNode
{
    struct EdgeNode *next;
    EdgeType info;//存储权值
    int adjvex;//存储该顶点对应的下标
}EdgeNode;
typedef struct VertexNode
{
    VertexType data;//存储结点域信息
    EdgeNode *firstNode;//存储结点的第一个邻接对象
}VertexNode;
typedef struct 
{
    VertexNode* adjList[MAXVEX];
    int node_num,edge_num;
}GraphAdjList;//邻接表
int visited[MAXVEX];
void CreateGraph(Mgraph *G,FILE *ip);
void create_graph_adlist(GraphAdjList *G,FILE *ip);
void DFS(Mgraph *G,int i,FILE *op);
void DFS_Traverse(Mgraph *G,FILE *op);//对邻接矩阵的深度遍历操作
void DFS_adlist(GraphAdjList *G,int i,FILE *op);
void DFS_Traverse_adlist(GraphAdjList *G,FILE *op);
