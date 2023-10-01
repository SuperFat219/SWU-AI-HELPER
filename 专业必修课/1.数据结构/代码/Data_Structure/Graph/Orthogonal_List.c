/*
**名称：实现有向图的十字链表表示
**Author：yzs
**Time:2021.10.14
*/
#include "Orthogonal_list.h"
int main()
{
    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");

    OLGraph *G;
    G=(OLGraph*)malloc(sizeof(OLGraph));
    create_graph(G,ip);
    insert_edge(G,2,0,6);
    insert_edge(G,3,1,2);
    delete_edge(G,3,1);
    print_graph(G,op);
    fclose(ip);
    fclose(op);

}
void create_graph(OLGraph *G,FILE *ip)
{
    fscanf(ip,"%d %d",&G->node_num,&G->edge_num);
    fgetc(ip);
    for (int i=0;i<G->node_num;i++)
    {
        G->Olist[i]=(VertexNode*)malloc(sizeof(VertexNode));
        G->Olist[i]->firstin=(EdgeNode*)malloc(sizeof(EdgeNode));
        G->Olist[i]->firstout=(EdgeNode*)malloc(sizeof(EdgeNode));
        G->Olist[i]->firstin=NULL;
        G->Olist[i]->firstout=NULL;
        fscanf(ip,"%c",&G->Olist[i]->data);
    }
    for (int i=0;i<G->edge_num;i++)
    {
        int v1,v2,w;
        fscanf(ip,"%d %d %d",&v1,&v2,&w);
        EdgeNode *e;
        e=(EdgeNode *)malloc(sizeof(EdgeNode));//v1->v2
        e->info=w;
        e->tailvex=v1;//弧起点
        e->headvex=v2;//弧终点
        e->taillink=G->Olist[v1]->firstout;//指向起点相同的边
        G->Olist[v1]->firstout=e;   
        e->headlink=G->Olist[v2]->firstin;//指向终点相同的边
        G->Olist[v2]->firstin=e;
    }
}
void print_graph(OLGraph *G,FILE *op)
{
    //打印每一个结点的出度，入度，出结点，入结点，以及每条边的权值
    for (int i=0;i<G->node_num;i++)
    {
        fprintf(op,"%c结点的出度为%d,入度为%d.",G->Olist[i]->data,count_out_degree(G->Olist[i]),count_in_degree(G->Olist[i]));
        fprintf(op,"%c的出节点为：",G->Olist[i]->data);
        EdgeNode *p=G->Olist[i]->firstout;
        while (p!=NULL)
        {
            fprintf(op,"%c ",G->Olist[p->headvex]->data);
            p=p->taillink;
        }
        fprintf(op,"入结点为：");
        p=G->Olist[i]->firstin;
        while (p!=NULL)
        {
            fprintf(op,"%c ",G->Olist[p->tailvex]->data);
            p=p->headlink;
        }
        fprintf(op,"\n");
    }
}
int count_out_degree(VertexNode *node)
{
    EdgeNode *p=node->firstout;
    int out_degree=0;
    while (p!=NULL)
    {
        out_degree+=1;
        p=p->taillink;
    }
    return out_degree;
}
int count_in_degree(VertexNode *node)
{
    EdgeNode *p=node->firstin;
    int in_degree=0;
    while (p!=NULL)
    {
        in_degree+=1;
        p=p->headlink;
    } 
    return in_degree;
}
int LocalVertex(OLGraph *G,VertexNode *node)//定位结点位置
{
    int index = -1;
    for (int i = 0; i < G->node_num; i++)
    {
        if(node->data == G->Olist[i]->data)
        {
            index = i;
            break;
        }
    }
    return index;
}
void insert_edge(OLGraph *G,int v1,int v2,int weight)
{
    EdgeNode *e;
    e=(EdgeNode*)malloc(sizeof(EdgeNode));
    e->tailvex=v1;
    e->headvex=v2;
    e->info=weight;
    e->taillink=G->Olist[v1]->firstout;//指向起点相同的边
    G->Olist[v1]->firstout=e;   
    e->headlink=G->Olist[v2]->firstin;//指向终点相同的边
    G->Olist[v2]->firstin=e;
}
void delete_edge(OLGraph *G,int v1,int v2)//删除v1->v2边
{
    EdgeNode *p;
    EdgeNode *pre;
    /*对v1处理*/
    pre=G->Olist[v1]->firstout;
    p=G->Olist[v1]->firstout->taillink;
    if (pre->headvex == v2)//去除的为链表中首个结点
    {
        G->Olist[v1]->firstout=p;
    }
    else
    {
        for (;p->headvex!=v2;)
        {

            pre=p;
            p=p->taillink;
        }
        pre->taillink=p->taillink;
    }

    /*对v2处理*/
    pre=G->Olist[v2]->firstin;
    p=G->Olist[v2]->firstin->headlink;
    if (pre->tailvex == v1)//去除的为链表中首个结点
    {
        G->Olist[v2]->firstin=p;
        free(pre);
    }
    else
    {
        for (;p->tailvex!=v1;)
        {
            pre=p;
            p=p->headlink;
        }
        pre->headlink=p->headlink;
        free(p);
    }

}