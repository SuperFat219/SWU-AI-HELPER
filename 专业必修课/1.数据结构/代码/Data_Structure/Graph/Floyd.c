/**
 * @file Floyd.c
 * @author yzs
 * @brief Floyd
 * @version 0.1
 * @date 2022-01-03
 *
 * @copyright Copyright (c) 2022
 *
 */

#include <stdio.h>
#define MAXVEX 100
typedef struct
{
    int vexs[MAXVEX];
    int arc[MAXVEX][MAXVEX];
    int numVertexes, numEdges;
} MGraph;
typedef int Pathsrc[MAXVEX][MAXVEX];
typedef int ShortPathTable[MAXVEX][MAXVEX];

void Floyd(MGraph *G, Pathsrc *P, ShortPathTable *D)
{
    for (int v = 0; v < G->numVertexes; v++)
    {
        for (int w = 0; w < G->numVertexes; w++)
        {
            (*D)[v][w] = G->arc[v][w];
            (*P)[v][w] = w;
        }
    }
    for (int k = 0; k < G->numVertexes; k++)
    {
        for (int v = 0; v < G->numVertexes; v++)
        {
            for (int w = 0; w < G->numVertexes; w++)
            {
                if ((*D)[v][w] > (*D)[v][k] + (*D)[k][w])
                {
                    (*D)[v][w] = (*D)[v][k] + (*D)[k][w];
                    (*P)[v][w] = (*P)[v][k];
                }
            }
        }
    }
    for (int v = 0; v < G->numVertexes; v++)
    {
        for (int w = v + 1;w<G->numVertexes;w++){
            printf("%d-%d weight:\n",v,w,D[v][w]);
            int k = P[v][w];
            printf("Path: %d",v);
            while (k!=w){
                printf("->%d",k);
                k = P[k][w];
            }
            printf("->%d",w);
        }
        printf("\n");
    }
}
