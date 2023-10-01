#ifndef __WINDOW_MAIN_H__
#define __WINDOW_MAIN_H__

//#include <gtk/gtk.h>
#include "common_func.h"
#include "system_functions.h"
#include "linklist_function.h"

enum{
    COLUMN_INDEX,
    COLUMN_NUM,
    COLUMN_NAME,
    COLUMN_GENDER,
    COLUMN_PHONE_NUMBER,
    COLUMN_ADDRESS,
    N_COLUMNS
};

typedef struct 
{
    GtkWidget *window;
    GtkWidget *num_entry;
    GtkWidget *name_entry;
    GtkWidget *gender_entry;
    GtkWidget *phone_number_entry;
    GtkWidget *address_entry;
}add_new_node;

typedef struct 
{
    GtkWidget *window;
    GtkTreeStore *store;
    GtkTreeModel *model;
}delete_all_passing_parameters;

typedef struct 
{
    GtkTreeStore *store;
    GtkTreeModel *model;
    GtkTreeSelection *selection;
    GtkWidget *window;
}delete_passing_parameters;
//对数据数组链表进行创建
typedef struct 
{
    GtkTreeStore *store;
    GtkWidget *entry;
}search_passing_parameters;

typedef struct 
{
    GtkTreeSelection *selection;
    GtkTreeStore *store;
    GtkTreeModel *model;
    GtkWidget *window;
}search_change_passing_parameters;//修改窗口参数

typedef struct 
{
    GtkWidget *window;
    Linklist *change_node;
    GtkWidget *num_entry;
    GtkWidget *name_entry;
    GtkWidget *gender_entry;
    GtkWidget *phone_number_entry;
    GtkWidget *address_entry;
    GtkTreeStore *store;
}change_passing_parameters;


Index_linklist system_data[26];
GtkTreeIter parents_iter[26];//存储26个父节点


//主界面
GtkWidget *create_main_window();
void on_Enter_button_clicked(GtkWidget *button,gpointer window);
void on_About_button_clicked(GtkWidget *button,gpointer window);
void on_return_button_clicked(GtkWidget *button,gpointer window);
//系统界面
GtkWidget *create_system_window();
//“查看联系人信息”界面
void on_View_button_clicked(GtkWidget *button,gpointer window);
void insert_tree_view(GtkTreeStore *store,GtkTreeIter parent_iter,GtkTreeIter child_iter,Linklist *node);//向树形容器中插入数据
void on_delete_button(GtkWidget *button,delete_passing_parameters *parameters);
void on_delete_all_button(GtkWidget *button,delete_all_passing_parameters *parameters);
//void on_confirm_button_clicked(GtkWidget *button,GtkTreeStore *store);
//“查询联系人”页面
void on_search_button_clicked(GtkWidget *button,gpointer window);//查询联系人
void on_search_change_button_clicked(GtkWidget *button,search_change_passing_parameters *parameters);//“信息修改按钮”
void on_change_button_clicked(GtkWidget *button,change_passing_parameters *parameters);//修改界面的确认修改按钮
void on_search_delete_button_clicked(GtkWidget *button,delete_passing_parameters *parameters);
void on_search_name_entry_activate(GtkWidget *entry,search_passing_parameters *name_entry_parameters);//查询文本输入框回车键按钮回调函数
void on_phone_number_entry_activate(GtkWidget *entry,search_passing_parameters *phone_parameters);
//“新建联系人”页面
void on_create_button_clicked(GtkWidget *button,gpointer window);//新建联系人
void on_add_button_clicked(GtkWidget *button,add_new_node *new_node);
//“导入导出”页面
void on_import_button_clicked(GtkWidget *button,gpointer window);
void on_export_button_clicked(GtkWidget *button,gpointer window);
void on_export_file_button_clicked(GtkWidget *button,gpointer window);

#endif // __WINDOW_MAIN_H__