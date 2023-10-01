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
typedef struct 
{
    EdgeType *data;
    EdgeType front;//指向队头
    EdgeType rear;//指向队尾的下一个位置
}SqQueue;//循环队列
int visited[MAXVEX];
void init_queue(SqQueue *sq);//队列初始化
void EnQueue(SqQueue *sq,EdgeType value);//进队
EdgeType DelQueue(SqQueue *sq);//出队
void CreateGraph(Mgraph *G,FILE *ip);
void create_graph_adlist(GraphAdjList *G,FILE *ip);
void BFSTraverse(Mgraph *G,FILE *op);
EdgeType queue_empty(SqQueue *sq);
void BFSTraverse_adlist(GraphAdjList *G,FILE *op);