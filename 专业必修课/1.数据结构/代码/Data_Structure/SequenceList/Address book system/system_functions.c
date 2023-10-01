#include "system_functions.h"
//#include "linklist_function.h"

/*
**从文件读取数据初始化链表
*/
void data_init(Index_linklist *system,FILE *ip)
{
    void insert_data(Index_linklist *system,Linklist *node);//从终端读取数据进行插入
	const char whitespace[] = " ";//分隔符
    const char linebreak[]="\n";//换行符
    char buffer[200];//缓冲区
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
        if (strcmp(new_node->gender,"woman")==0 || strcmp(new_node->gender,"man")==0)
        {
            //判断性别输入是否合理
            insert_data(system,new_node);
        }
        else
        {
            printf("%s \'s gender input ERROR!\n",new_node->name);
        }
        // int index=new_node->name[0]-'A';
        // system[index].rear->next=new_node;
        // new_node->next=NULL;
        // system[index].rear=new_node;
    }
    // fscanf(ip,"%s",new_node->num);
    // fscanf(ip,"%s",new_node->name);
    // fscanf(ip,"%s",new_node->gender);
    // fscanf(ip,"%s",new_node->phone_number);
    // fgets(new_node->address,MAXSIZE,ip);

}
void insert_data(Index_linklist *system,Linklist *node)//从终端读取数据进行插入
{
    int index=node->name[0]-'A';
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
                return;
            }
        }
        //找到第一个name比新节点大的节点的前一个节点,新节点插在其之前
        node->next=p->next;
        p->next=node;

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
int search_data(Index_linklist const system[],char *name)
{
    int index=name[0]-'A';
    Linklist *p=system[index].root->next;
    int flag=0; //标志是否存在
    int location=0;
    for (;p!=NULL;location+=1)
    {
        if (strcmp(p->name,name)==0)
        {
            printf("%s %s %s %s %s\n",p->num,p->name,p->gender,p->phone_number,p->address);
            flag=1;
            //break;
        }
        p=p->next;
    }
    if (flag==0) printf("%s is not in the address book!",name);
    return location;
}
void delete_data(Index_linklist *system,char *name)
{
    
}
