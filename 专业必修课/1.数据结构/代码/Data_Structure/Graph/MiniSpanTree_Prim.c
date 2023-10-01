/**
 * @file MiniSpanTree_Prim.c
 * @author yzs
 * @brief 算法
 * @version 0.1
 * @date 2022-01-02
 *
 * @copyright Copyright (c) 2022
 *
 */
#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 100 //最大顶点数
#define INFINITY 65535
typedef char VertexType; //定义定点类型
typedef int EdgeType;    //定义边的类型
typedef struct
{
    VertexType vexs[MAXVEX];
    EdgeType arc[MAXVEX][MAXVEX]; //邻接矩阵
    int Nodenum, Edgenum;         //图中当前的顶点数和边数
} Mgraph;

int main()
{
    void CreateGraph(Mgraph * G, FILE * ip);
    void Prim(Mgraph * G);
    FILE *ip, *op;
    ip = fopen("input.txt", "r");
    op = fopen("output.txt", "w");
    Mgraph *G;
    G = (Mgraph *)malloc(sizeof(Mgraph));
    CreateGraph(G, ip);
    // for (int i = 0; i < G->Nodenum; i++)
    // {
    //     printf("%c ", G->vexs[i]);
    // }
    fclose(ip);
    fclose(op);
    Prim(G);
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
        fscanf(ip, "%d %d %d", &v1, &v2, &w); // v1->v2的弧的权值
        G->arc[v1][v2] = w;
        G->arc[v2][v1] = w; //无向图的邻接矩阵为对称矩阵
    }
}
void Prim(Mgraph *G)
{
    // int min;
    int lowcost[MAXVEX];
    int adjvex[MAXVEX];
    lowcost[0] = 0;
    adjvex[0] = 0;
    for (int i = 1; i < G->Nodenum; i++)
    {
        lowcost[i] = G->arc[0][i];
        adjvex[i] = 0;
    }
    for (int i = 1; i < G->Nodenum; i++)
    {
        int min = INFINITY;
        int j = 1, k = 0;
        while (j < G->Nodenum)
        {
            if (lowcost[j] != 0 && lowcost[j] < min)
            {
                min = lowcost[j];
                k = j;
            }
            j++;
        }
        printf("%d %d\n", adjvex[k], k);
        lowcost[k] = 0;
        for (j = 1; j < G->Nodenum; j++)
        {
            if (lowcost[j] != 0 && G->arc[k][j] < lowcost[j])
            {
                lowcost[j] = G->arc[k][j];
                adjvex[j] = k;
            }
        }
    }
}