/*
**名称：实现无向图的邻接多重表的表示
**Author：yzs
**Time:2021.10.18
*/
#include "Adjacency_Multilist.h"
int main()
{

    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");

    AMGraph *G;
    G=(AMGraph*)malloc(sizeof(AMGraph));

    create_graph(G,ip);
    delete_egde(G,0,3);
    print_graph(G,op);
    fclose(ip);
    fclose(op);
}
void create_graph(AMGraph *G,FILE *ip)
{
    fscanf(ip,"%d %d",&G->node_num,&G->edge_num);
    fgetc(ip);
    for (int i=0;i<G->node_num;i++)
    {
        G->AMlist[i]=(VertexNode*)malloc(sizeof(VertexNode));
        G->AMlist[i]->first_edge=(EdgeNode*)malloc(sizeof(EdgeNode));
        G->AMlist[i]->first_edge=NULL;
        fscanf(ip,"%c",&G->AMlist[i]->data);
    }
    for (int i=0;i<G->edge_num;i++)
    {
        int v1,v2,w;
        fscanf(ip,"%d %d %d",&v1,&v2,&w);
        insert_edge(G,v1,v2,w);
    }
}
void insert_edge(AMGraph *G,int v1,int v2,int info)
{
    EdgeNode *new_edge=(EdgeNode*)malloc(sizeof(EdgeNode));
    new_edge->info=info;
    new_edge->ivex=v1;
    new_edge->jvex=v2;
    new_edge->ilink=G->AMlist[v1]->first_edge;//利用头插法进行插入
    G->AMlist[v1]->first_edge=new_edge;
    new_edge->jlink=G->AMlist[v2]->first_edge;
    G->AMlist[v2]->first_edge=new_edge;
}
void print_graph(AMGraph *G,FILE *op)
{
    for (int i=0;i<G->node_num;i++)
    {
        fprintf(op,"%c",G->AMlist[i]->data);
        fprintf(op,"结点的度为%d.",count_degree(G->AMlist[i],i));
        fprintf(op,"与其相邻的结点为");
        EdgeNode *p=G->AMlist[i]->first_edge;
        while (p)
        {
            if (p->ivex==i)
            {
                fprintf(op,"%c ",G->AMlist[p->jvex]->data);
                p=p->ilink;
            }
            else
            {
                fprintf(op,"%c ",G->AMlist[p->ivex]->data);
                p=p->jlink;                
            }
        }
        fprintf(op,",与%c相邻接的边有：",G->AMlist[i]->data);
        p=G->AMlist[i]->first_edge;
        while (p)
        {
            fprintf(op,"%c-%c,权值为%d;",G->AMlist[p->ivex]->data,G->AMlist[p->jvex]->data,p->info);
            if (p->ivex==i)
            {
                p=p->ilink;
            }
            else
            {
                p=p->jlink;                
            }
        }
        fprintf(op,"\n");
    }
}
int count_degree(VertexNode *node,int i)
{
    int degree=0;
    EdgeNode *p=node->first_edge;
    while (p)
    {
        if (p->ivex==i)
        {
            degree+=1;
            p=p->ilink;
            //continue;
        }
        else //if (p->jvex==i)
        {
            degree+=1;
            p=p->jlink;
            //continue;
        }
    }
    return degree;
}
void delete_egde(AMGraph *G,int v1,int v2)
{
    /*删除v1关联边*/
    EdgeNode *p;
    EdgeNode *pre;
    p=G->AMlist[v1]->first_edge;
    if (p->jvex==v2)//待删除节点刚好为第一个节点，且i对应v1
    {
        G->AMlist[v1]->first_edge=p->ilink;
        //free(pre);
    }
    else if (pre->ivex==v2)//i对应v2
    {
        G->AMlist[v1]->first_edge=p->jlink;
    }
    else//不为第一个节点
    {
        int flag_pre=0;//标志pre从哪个指针域指向p
        int flag_p=0;//标志p哪个数据域为v1
        while (p)//p指向当前节点，pre指向上一个节点
        {
            if (p->ivex ==v1 && p->jvex==v2)
            {
                flag_p=0;
                break;
            }
            else if(p->jvex==v1 && p->ivex==v2)
            {
                flag_p=1;
                break;
            }
            if(p->ivex==v1)
            {
                pre=p;
                p=p->ilink;
                flag_pre=0;
            }
            else
            {
                pre=p;
                p=p->jlink;
                flag_pre=1;
            }
        }
        if (flag_p==0 && flag_pre==0)
        {
            pre->ilink=p->ilink;
        }
        else if (flag_pre==0 && flag_p==1)
        {
            pre->ilink=p->jlink;
        }
        else if(flag_pre==1 && flag_p==0)
        {
            pre->jlink=p->ilink;
        }
        else
        {
            pre->jlink=p->jlink;
        }
    }
    /*对v2处理*/
    p=G->AMlist[v2]->first_edge;
    if (p->jvex==v1)//待删除节点刚好为第一个节点，且i对应v2
    {
        G->AMlist[v2]->first_edge=p->ilink;
        //free(pre);
    }
    else if (pre->ivex==v1)//i对应v1
    {
        G->AMlist[v2]->first_edge=p->jlink;
    }
    else//不为第一个节点
    {
        int flag_pre=0;//标志pre从哪个指针域指向p
        int flag_p=0;//标志p哪个数据域为v2
        while (p)//p指向当前节点，pre指向上一个节点
        {
            if (p->ivex ==v2 && p->jvex==v1)
            {
                flag_p=0;
                break;
            }
            else if(p->jvex==v2 && p->ivex==v1)
            {
                flag_p=1;
                break;
            }
            if(p->ivex==v2)
            {
                pre=p;
                p=p->ilink;
                flag_pre=0;
            }
            else
            {
                pre=p;
                p=p->jlink;
                flag_pre=1;
            }
        }
        if (flag_p==0 && flag_pre==0)
        {
            pre->ilink=p->ilink;
        }
        else if (flag_pre==0 && flag_p==1)
        {
            pre->ilink=p->jlink;
        }
        else if(flag_pre==1 && flag_p==0)
        {
            pre->jlink=p->ilink;
        }
        else
        {
            pre->jlink=p->jlink;
        }
    }
    free(p);
}