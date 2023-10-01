/*
Function：简单通讯录系统实现
Author：yzs
Time：2021.10.3
*/
//#include "linklist_function.h"
#include "system_functions.h"

int main()
{
    void print_menu();    //主菜单

    FILE *ip,*op;
    ip=fopen("input.txt","r");
    op=fopen("output.txt","w");

    Index_linklist system[26];
    for (int i=0;i<26;i++)
    {
        system[i].root=(Linklist*)malloc(sizeof(Linklist));//为每一个链表头结点分配空间
        system[i].rear=system[i].root;
    }
    data_init(system,ip);
    print_menu();
    print_data(system,op);
    search_data(system,"Yann");

    fclose(ip);
    fclose(op);
}
void print_menu()    //主菜单
{
    system("cls");
    printf("\n\n\n");
    printf("      ***********************************************************\n");
    printf("      \t\t\t欢迎使用通讯录信息管理系统\n");
    printf("\n");
    printf("      \t\t1. 查看通讯录信息");
    printf("\t\t2. 插入联系人信息\n");
    printf("      \t\t3. 查找联系人信息");
    printf("\t\t4. \n");
    printf("      \t\t5. 查看数据");
    printf("\t\t6. 修改数据\n");
    printf("      \t\t7. 保存数据");
    printf("\t\t8. 返回主菜单\n");
    printf("      ***********************************************************\n");
    printf("      ~~~~~退~~~~~~~~~~出~~~~~~~~~~请~~~~~~~~~~按~~~~~~~~~~9~~~~~\n");
    printf("      -----------------------------------------------------------\n\n");
}