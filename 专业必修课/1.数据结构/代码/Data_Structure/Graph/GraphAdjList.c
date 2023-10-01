/*
**名称：实现图的邻接表表示
**Author：yzs
**Time:2021.10.14
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 100//最大顶点数
typedef char VertexType;//定义定点类型
typedef int EdgeType;//定义边的类型
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
}GraphAdjList;
int main()
{
    void create_graph(GraphAdjList *G,FILE *ip);    
    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");

    GraphAdjList *G;
    G=(GraphAdjList*)malloc(sizeof(GraphAdjList));
    create_graph(G,ip);
    fclose(ip);fclose(op);

}
void create_graph(GraphAdjList *G,FILE *ip)
{
    fscanf(ip,"%d %d",&G->node_num,&G->edge_num);
    fgetc(ip);
    for (int i=0;i<G->node_num;i++)
    {
        G->adjList[i]=(VertexNode*)malloc(sizeof(VertexNode));
        G->adjList[i]->firstNode=(EdgeNode*)malloc(sizeof(EdgeNode));
        fscanf(ip,"%c",&G->adjList[i]->data);//读入结点信息
        G->adjList[i]->firstNode=NULL;
    }
    /*建立边表*/
    for (int i=0;i<G->edge_num;i++)
    {
        int v1,v2,w;
        fscanf(ip,"%d %d %d",&v1,&v2,&w);
        EdgeNode *e;
        e=(EdgeNode*)malloc(sizeof(EdgeNode));//v1->v2的弧
        e->info=w; 
        e->adjvex=v2;
        e->next=G->adjList[v1]->firstNode;
        G->adjList[v1]->firstNode=e;
        //若为无向图则两个节点均需插入邻接结点
        // e=(EdgeNode*)malloc(sizeof(EdgeNode));
        // e->adjvex=i;
        // e->info=w;
        // e->next=G->adjList[v2]->firstNode;
        // G->adjList[v2]->firstNode=e;

    }
}

