/**
 * @file Dijkstra.c
 * @author yzs
 * @brief 迪杰斯特拉算法
 * @version 0.1
 * @date 2022-01-02
 *
 * @copyright Copyright (c) 2022
 *
 */

#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 100 //最大顶点数
#define MAXEDGE 100
#define INFINITY 65535
typedef struct
{
    int vexs[MAXVEX];
    int arc[MAXVEX][MAXVEX];
    int numVertexes, numEdges;
} MGraph;
typedef int Pathsrc[MAXVEX];
typedef int ShortPathTable[MAXVEX];

/**
 * @brief v0顶点到其他顶点的最短路径及长度求解
 *
 * @param G
 * @param v0
 * @param P
 * @param D
 */
void dijkstra(MGraph *G, int v0, Pathsrc *P, ShortPathTable *D)
{
    int flag[MAXVEX]; //标志该节点最短路径是否已求出
    for (int v = 0; v < G->numVertexes; v++)
    {
        flag[v] = 0;
        (*D)[v] = G->arc[v0][v];
        (*P)[v] = -1;
    }
    (*D)[v0] = 0;
    flag[v0] = 1;
    int k, min;
    for (int v = 1; v < G->numVertexes; v++)
    {
        min = INFINITY;
        for (int w = 0; w < G->numVertexes; w++)
        {
            if (!flag[w] && (*D)[w] < min)
            {
                k = w;
                min = (*D)[w];
            }
        }
        flag[k] = 1;
        for (int w = 0; w < G->numVertexes; w++)
        {
            if (!flag[w] && (min+G->arc[k][w]<(*D)[w]))
            {
                (*D)[w] = min+G->arc[k][w];
                (*P)[w] = k;
            }
        }
    }
}