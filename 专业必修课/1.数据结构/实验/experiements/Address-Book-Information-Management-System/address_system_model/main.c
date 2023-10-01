//#include <stdio.h>
//#include <gtk/gtk.h>
#include "window_main.h"
#define True 1
#define False 0

int main(int argc,char *argv[])
{
    for (int i=0;i<26;i++)
    {
        system_data[i].root=(Linklist*)malloc(sizeof(Linklist));//为每一个链表头结点分配空间
        system_data[i].rear=system_data[i].root;
        system_data[i].root->next=NULL;
    }
    FILE *ip;
    ip=fopen("input.txt","r");
    data_insert_from_file(system_data,ip);//数据初始化
    fclose(ip);
    //初始化主窗口
    GtkWidget *main_window;
    gtk_init(&argc, &argv);
    main_window=create_main_window(); //创建主窗口
    gtk_widget_show_all(main_window); //显示主窗口
    gtk_main();

    return 0;

}

