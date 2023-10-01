/*
**名称：实现无向图的邻接矩阵表示
**Author：yzs
**Time:2021.10.14
*/
#include <stdio.h>
#include <stdlib.h>
#define MAXVEX 100//最大顶点数
#define INFINITY 65535
typedef char VertexType;//定义定点类型
typedef int EdgeType;//定义边的类型
typedef struct 
{
    VertexType vexs[MAXVEX];
    EdgeType arc[MAXVEX][MAXVEX];//邻接矩阵
    int Nodenum,Edgenum;//图中当前的顶点数和边数
}Mgraph;
int main()
{
    void CreateGraph(Mgraph *G,FILE *ip);
    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");
    Mgraph *G;
    G=(Mgraph*)malloc(sizeof(Mgraph));
    CreateGraph(G,ip);

    fclose(ip);
    fclose(op);

}
void CreateGraph(Mgraph *G,FILE *ip)
{
    fscanf(ip,"%d %d",&G->Nodenum,&G->Edgenum);//输入顶点数和边数
    fgetc(ip);//去除行尾换行符
    for (int i=0;i<G->Nodenum;i++)
    {
        fscanf(ip,"%c",&G->vexs[i]);
        for (int j=0;j<G->Nodenum;j++)
        {
            G->arc[i][j]=INFINITY;
        }
    }
    for (int i=0;i<G->Edgenum;i++)
    {
        int v1,v2,w;
        fscanf(ip,"%d %d %d",&v1,&v2,&w);//v1->v2的弧的权值
        G->arc[v1][v2]=w;
        G->arc[v2][v1]=w;//无向图的邻接矩阵为对称矩阵
    }
}