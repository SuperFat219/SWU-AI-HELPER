/*
**名称：实现图的深度优先搜索——邻接矩阵/邻接表
**Author：yzs
**Time:2021.10.19
*/
#include "Depth_First_Search.h"
int main()
{
    void CreateGraph(Mgraph * G, FILE * ip);
    FILE *ip, *op;
    ip = fopen("input.txt", "r");
    op = fopen("output.txt", "w");
    Mgraph *G1;
    G1 = (Mgraph *)malloc(sizeof(Mgraph));
    CreateGraph(G1, ip);
    DFS_Traverse(G1, op);
    // GraphAdjList *G;
    // G=(GraphAdjList*)malloc(sizeof(GraphAdjList));
    // create_graph_adlist(G,ip);
    // DFS_Traverse_adlist(G,op);
    // DFS_Traverse(G,op);
    fclose(ip);
    fclose(op);
}
void CreateGraph(Mgraph *G, FILE *ip)
{
    fscanf(ip, "%d %d", &G->Nodenum, &G->Edgenum); //输入顶点数和边数
    fgetc(ip);                                     //去除行尾换行符
    for (int i = 0; i < G->Nodenum; i++)
    {
        fscanf(ip, "%c", &G->vexs[i]);
        for (int j = 0; j < G->Nodenum; j++)
        {
            G->arc[i][j] = INFINITY;
        }
    }
    for (int i = 0; i < G->Edgenum; i++)
    {
        int v1, v2, w;
        fscanf(ip, "%d %d", &v1, &v2); // v1->v2的弧的权值
        G->arc[v1][v2] = 1;
        G->arc[v2][v1] = 1; //无向图的邻接矩阵为对称矩阵
    }
}
void create_graph_adlist(GraphAdjList *G, FILE *ip)
{
    fscanf(ip, "%d %d", &G->node_num, &G->edge_num);
    fgetc(ip);
    for (int i = 0; i < G->node_num; i++)
    {
        G->adjList[i] = (VertexNode *)malloc(sizeof(VertexNode));
        G->adjList[i]->firstNode = (EdgeNode *)malloc(sizeof(EdgeNode));
        fscanf(ip, "%c", &G->adjList[i]->data); //读入结点信息
        G->adjList[i]->firstNode = NULL;
    }
    /*建立边表*/
    for (int i = 0; i < G->edge_num; i++)
    {
        int v1, v2;
        fscanf(ip, "%d %d", &v1, &v2);
        EdgeNode *e;
        e = (EdgeNode *)malloc(sizeof(EdgeNode)); // v1->v2的弧
        // e->info=w;
        e->adjvex = v2;
        e->next = G->adjList[v1]->firstNode;
        G->adjList[v1]->firstNode = e;
        //若为无向图则两个节点均需插入邻接结点
        e = (EdgeNode *)malloc(sizeof(EdgeNode));
        e->adjvex = v1;
        // e->info=w;
        e->next = G->adjList[v2]->firstNode;
        G->adjList[v2]->firstNode = e;
    }
}
void DFS(Mgraph *G, int i, FILE *op) //对某一节点的深度优先递归算法
{
    visited[i] = 1;
    fprintf(op, "%c ", G->vexs[i]);
    int j;
    for (j = 0; j < G->Nodenum; j++)
    {
        if (G->arc[i][j] == 1 && visited[j] == 0)
        {
            DFS(G, j, op);
        }
    }
}
void DFS_Traverse(Mgraph *G, FILE *op) //对邻接矩阵的深度遍历操作
{
    for (int i = 0; i < G->Nodenum; i++)
    {
        visited[i] = 0;
    }
    for (int i = 0; i < G->Nodenum; i++)
    {
        if (!visited[i])
            DFS(G, i, op);
    }
}
void DFS_adlist(GraphAdjList *G, int i, FILE *op)
{
    //对某一个节点及其邻接点进行深度优先搜索
    EdgeNode *p;
    p = G->adjList[i]->firstNode;
    fprintf(op, "%c ", G->adjList[i]->data);
    visited[i] = 1;
    while (p)
    {
        if (visited[p->adjvex] != 1)
        {
            DFS_adlist(G, p->adjvex, op);
        }
        p = p->next;
    }
}
void DFS_Traverse_adlist(GraphAdjList *G, FILE *op)
{
    for (int i = 0; i < G->node_num; i++)
    {
        visited[i] = 0;
    }
    for (int i = 0; i < G->node_num; i++)
    {
        if (visited[i] != 1)
        {
            DFS_adlist(G, i, op);
        }
    }
}