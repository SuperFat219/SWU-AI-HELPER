/**
 * @file MiniSpanTree_Kruskal.c
 * @author yzs
 * @brief 克鲁斯卡尔算法
 * @version 0.1
 * @date 2022-01-02
 *
 * @copyright Copyright (c) 2022
 */

#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 100 //最大顶点数
#define MAXEDGE 100
#define INFINITY 65535
typedef struct
{
    int begin;
    int end;
    int weight;
} Edge;

typedef struct
{
    int NodeNum;
    int EdgeNum;
    char Nodes[MAXVEX];
    Edge *arcs[MAXEDGE];
} Graph;

int main()
{
    FILE *ip, *op;
    ip = fopen("input.txt", "r");
    op = fopen("output.txt", "w");
    Graph *G;
    G = (Graph *)malloc(sizeof(Graph));
    CreateGraph(G, ip);
}
void CreateGraph(Graph *G, FILE *ip)
{
    fscanf(ip, "%d %d", &G->NodeNum, &G->EdgeNum); //输入顶点数和边数
    fgetc(ip);                                     //去除行尾换行符
    for (int i = 0; i < G->NodeNum; i++)
    {
        fscanf(ip, "%c", &G->Nodes[i]);
    }
    for (int i = 0; i < G->EdgeNum; i++)
    {
        int v1, v2, w;
        fscanf(ip, "%d %d %d", &v1, &v2, &w); // v1->v2的弧的权值
        Edge *edge = (Edge *)malloc(sizeof(Edge));
        edge->begin = v1;
        edge->end = v2;
        edge->weight = w;
        G->arcs[i] = edge;
    }
    //TODO:将边按权重排序
}
void Kruskal(Graph *G)
{
    int n, m;
    Edge edges[MAXEDGE];
    int parent[MAXVEX];

    for (int i = 0; i < G->NodeNum; i++)
    {
        parent[i] = 0;
    }
    for (int i = 0; i < G->EdgeNum; i++)
    {
        n = find(parent, edges[i].begin);
        m = fing(parent, edges[i].end);
        if (n != m)
        {
            parent[n] = m;
            printf("%d %d %d\n",edges[i].begin,edges[i].end,edges[i].weight);
        }
    }
}
int find(int *parent, int f)
{
    while (parent[f] > 0)
    {
        f = parent[f];
    }
    return f;
}