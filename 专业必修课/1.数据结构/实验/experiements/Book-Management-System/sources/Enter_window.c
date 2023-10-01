//
// Created by god on 2021/12/16.
//
#include "headers/Enter_window.h"
#include "headers/login_window.h"
#include "headers/mainWindow.h"
#include "headers/user_window.h"

GtkWidget *create_Enter_window() {

    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *Enter_window;
    GtkWidget *Enter_button;
    GtkWidget *Exit_button;
//    GtkWidget *About_button;//TODO:关于信息窗口

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/Enter_window.glade",
                              NULL);//从glade获取控件

    Enter_window = GTK_WIDGET(gtk_builder_get_object(builder, "Enter_window"));//获取主窗体
    gtk_window_set_icon(GTK_WINDOW(Enter_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(Enter_window), "图书信息管理系统");//设置主窗口标题
    Enter_button = GTK_WIDGET(gtk_builder_get_object(builder, "Enter_button"));//获取主窗口中的进入按钮
    Exit_button = GTK_WIDGET(gtk_builder_get_object(builder, "Exit_button"));//获取主窗口中的退出按钮
//    about_button=GTK_WIDGET(gtk_builder_get_object(builder,"About_button"));//获取主窗口中的按钮

    g_signal_connect(Enter_window, "destroy", G_CALLBACK(gtk_main_quit), NULL);//设置主窗口关闭信号链接
    g_signal_connect(Exit_button, "clicked", G_CALLBACK(gtk_main_quit), NULL);//设置“退出按钮”的连接动作
    g_signal_connect(Enter_button, "clicked", G_CALLBACK(on_Enter_button_clicked), Enter_window);//设置“进入系统”按钮的回调函数
//    g_signal_connect(About_button, "clicked", G_CALLBACK(on_About_button_clicked), Enter_window);

    return Enter_window;
}

void on_Enter_button_clicked(GtkWidget *button, gpointer window) {
    bookTypeData = (BookType *) malloc(sizeof(BookType));
    bookTypeData->bookType[0] = "计算机类";
    bookTypeData->bookType[1] = "生活类";
    bookTypeData->bookType[2] = "文学类";
    bookTypeData->bookType[3] = "历史类";
    bookTypeData->bookType[4] = "体育类";
    bookTypeData->bookType[5] = "艺术类";
    bookTypeData->bookType[6] = "杂志";
    bookTypeData->bookType[7] = "其他";
    bookTypeData->typeNum = 8;

    bookData = (BookInfo *) malloc(sizeof(BookInfo));
    bookData->booksNum = 0;
    FILE *ip;
    ip = fopen("/home/god/Projects/Book-Management-System/Data/book.txt", "r");
    import_book_data_from_file(ip, bookData);
    fclose(ip);
    gtk_widget_hide(window);
    if (Identity == true) {
        gtk_widget_show_all(create_user_window());
    } else {
        gtk_widget_show_all(create_main_window());
    }
}

void on_About_button_clicked(GtkWidget *button, gpointer window) {}
