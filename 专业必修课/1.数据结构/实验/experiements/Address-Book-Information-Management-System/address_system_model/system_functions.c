#include "system_functions.h"
//#include "linklist_function.h"

/*
**从文件读取数据初始化链表
*/
int data_insert_from_file(Index_linklist *system,FILE *ip)//从文件读取数据
{
	const char whitespace[] = " ";//分隔符
    const char linebreak[]="\n";//换行符
    char buffer[200];//缓冲区
    int count=0;//记录读取了多少条数据
    while (fgets(buffer,N,ip))//按行读取
    {   
        Linklist *new_node=(Linklist *)malloc(sizeof(Linklist));
        int length=strlen(buffer);
        /*进行字符串分割*/
        strcpy(new_node->num,strtok(buffer,whitespace));
        strcpy(new_node->name,strtok(NULL,whitespace));
        strcpy(new_node->gender,strtok(NULL,whitespace));
        strcpy(new_node->phone_number,strtok(NULL,whitespace));
        strcpy(new_node->address,strtok(NULL,linebreak));
        if (insert_data(system,new_node)==1)
        {
            count+=1;
        }
    }
    return count;
    // fscanf(ip,"%s",new_node->num);
    // fscanf(ip,"%s",new_node->name);
    // fscanf(ip,"%s",new_node->gender);
    // fscanf(ip,"%s",new_node->phone_number);
    // fgets(new_node->address,MAXSIZE,ip);

}
int data_export_to_file(Index_linklist *system,char *filename)
{
    FILE *op;
    if ((op=fopen(filename,"w"))!=NULL)
    {
        print_data(system,op);
        fclose(op);
        return 1;
    }
    else return 0;
}
int insert_data(Index_linklist *system,Linklist *node)//从终端读取数据进行插入
{
    if (strcmp(node->gender,"woman")==0)
    {
        strcpy(node->gender,"女");
    }
    if (strcmp(node->gender,"man")==0)
    {
        strcpy(node->gender,"男");
    }
    if (strcmp(node->gender,"男")==0 || strcmp(node->gender,"女")==0)
    {        
        //判断性别输入是否合理
        int index=to_upper(node->name[0])-'A';
        Linklist *p=system[index].root;
        //按照姓名顺序进行插入
        if (p->next==NULL)//若链表为空则直接插入
        {
            system[index].rear->next=node;
            node->next=NULL;
            system[index].rear=node;
        }
        else
        {
            for (;strcmp(p->next->name,node->name) <= 0;)
            {
                p=p->next;
                if (p->next==NULL)
                {
                    node->next=NULL;
                    p->next=node;
                    system[index].rear=node;
                    return 1;
                }
            }
            //找到第一个name比新节点大的节点的前一个节点,新节点插在其之前
            node->next=p->next;
            p->next=node;
        }
        return 1;
    }
    else
    {
        //printf("%s \'s gender input ERROR!\n",node->name);
        return 0;
    }


}
void print_data(Index_linklist system[],FILE *op)
{
    for (int i=0;i<26;i++)
    {
        Linklist *p=system[i].root->next;
        while (p!=NULL)
        {
            fprintf(op,"%s %s %s %s %s\n",p->num,p->name,p->gender,p->phone_number,p->address);
            p=p->next;
        }
    }
}
int search_data(Index_linklist const system[],char *num,char *name)//根据ID进行完全匹配搜索
{
    int index=to_upper(name[0])-'A';
    Linklist *p=system[index].root->next;
    int flag=0; //标志是否存在
    int location=0;
    for (;p!=NULL;location+=1)
    {
        if (strcmp(p->num,num)==0)
        {
            //printf("%s %s %s %s %s\n",p->num,p->name,p->gender,p->phone_number,p->address);
            flag=1;
            location+=1;
            break;
        }
        p=p->next;
    }
    if (flag==0) return -1;//printf("%s is not in the address book!",name);
    return location;//当前链表中第location个位置(从头结点下一个节点开始从1计数)
}
void delete_data(Index_linklist *system,char *num,char *name)//删除数据
{
    if (name==NULL)
    {
        return;
    }
    int index=to_upper(name[0])-'A';
    Linklist *p=system[index].root;
    int location=search_data(system,num,name);
    delete_linklist(p,location);
}
int judge_system_empty(Index_linklist *system)//判断系统数据是否为空
{

}
void system_empty(Index_linklist *system)//将系统数据清空
{
    for (int i=0;i<26;i++)
    {
        Linklist *p=system[i].root->next;
        system[i].rear=system[i].root;
        for (;p!=NULL;)
        {
            Linklist *q=p;
            p=p->next;
            free(q);
        }
        system[i].root->next=NULL;
    }
}
int count_linklist_elem_num(Index_linklist *system,char *name)
{
    if (name==NULL)
    {
        return -1;
    }
    int index=to_upper(name[0])-'A';
    Linklist *p=system[index].root;
    if (p->next==NULL) 
    {
        return 0;//无元素
    }
    else if (p->next->next==NULL) 
    {
        return 1;//一个元素
    }
    else
    {
        return 2;
    }
}