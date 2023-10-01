/*
**名称：实现图的广度优先搜索——邻接矩阵/邻接表
**Author：yzs
**Time:2021.10.20
*/
#include "Breadth_First_Search.h"
int main()
{
    void CreateGraph(Mgraph *G,FILE *ip);
    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");
    // Mgraph *G1;
    // G1=(Mgraph*)malloc(sizeof(Mgraph));
    // CreateGraph(G1,ip);
    // BFSTraverse(G1,op);
    GraphAdjList *G;
    G=(GraphAdjList*)malloc(sizeof(GraphAdjList));
    create_graph_adlist(G,ip);
    BFSTraverse_adlist(G,op);
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
        fscanf(ip,"%d %d",&v1,&v2);//v1->v2的弧的权值
        G->arc[v1][v2]=1;
        G->arc[v2][v1]=1;//无向图的邻接矩阵为对称矩阵
    }
}
void create_graph_adlist(GraphAdjList *G,FILE *ip)
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
        int v1,v2;
        fscanf(ip,"%d %d",&v1,&v2);
        EdgeNode *e;
        e=(EdgeNode*)malloc(sizeof(EdgeNode));//v1->v2的弧
        //e->info=w;
        e->adjvex=v2;
        e->next=G->adjList[v1]->firstNode;
        G->adjList[v1]->firstNode=e;
        //若为无向图则两个节点均需插入邻接结点
        e=(EdgeNode*)malloc(sizeof(EdgeNode));
        e->adjvex=v1;
        //e->info=w;
        e->next=G->adjList[v2]->firstNode;
        G->adjList[v2]->firstNode=e;

    }
}

/*邻接表*/
void BFSTraverse(Mgraph *G,FILE *op)
{
    SqQueue *queue=(SqQueue*)malloc(sizeof(SqQueue));
    init_queue(queue);
    for (int i=0;i<G->Nodenum;i++)
    {
        visited[i]=0;
    }
    for (int i=0;i<G->Nodenum;i++)
    {
        if (!visited[i])
        {
            visited[i]=1;
            fprintf(op,"%c ",G->vexs[i]);
            EnQueue(queue,i);
            while (!queue_empty(queue))
            {
                i=DelQueue(queue);
                for (int j=0;j<G->Nodenum;j++)
                {
                    if (G->arc[i][j]==1 && !visited[j])
                    {
                        visited[j]=1;
                        fprintf(op,"%c ",G->vexs[j]);
                        EnQueue(queue,j);
                    }
                }
            }
        }
    }
}
void init_queue(SqQueue *sq)//队列初始化
{
    sq->data=(EdgeType*)malloc(sizeof(EdgeType)*MAXVEX);
    sq->front=0;
    sq->rear=0;
}
void EnQueue(SqQueue *sq,EdgeType value)//进队
{
    if ((sq->rear+1)%MAXVEX == sq->front)
    {
        printf("The queue is full!\n");
    }
    else
    {
        *(sq->data+sq->rear)=value;
        sq->rear=(sq->rear+1)%MAXVEX;
    }
}
EdgeType DelQueue(SqQueue *sq)//出队
{
    if (sq->rear==sq->front)
    {
        printf("The queue is empty!\n");
    }
    else
    {
        EdgeType value=*(sq->data+sq->front);
        sq->front=(sq->front+1)%MAXVEX;
        return value;
    }
}
EdgeType queue_empty(SqQueue *sq)
{
    if (sq->front==sq->rear)
    {
        return 1;
    }
    return 0;
}
/*邻接矩阵*/
void BFSTraverse_adlist(GraphAdjList *G,FILE *op)
{
    SqQueue *queue=(SqQueue*)malloc(sizeof(SqQueue));
    init_queue(queue);
    for (int i=0;i<G->node_num;i++)
    {
        visited[i]=0;
    }
    for (int i=0;i<G->node_num;i++)
    {
        if (!visited[i])
        {
            visited[i]=1;
            fprintf(op,"%c ",G->adjList[i]->data);
            EnQueue(queue,i);
            while (!queue_empty(queue))
            {
                i=DelQueue(queue);
                EdgeNode *p;
                p=G->adjList[i]->firstNode;
                while (p)
                {
                    if (!visited[p->adjvex])
                    {
                        visited[p->adjvex]=1;
                        fprintf(op,"%c ",G->adjList[p->adjvex]->data);
                        EnQueue(queue,p->adjvex);
                    }
                    p=p->next;
                }
            }
        }
    }
}