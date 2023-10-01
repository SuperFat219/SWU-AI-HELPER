//
// Created by god on 2021/12/25.
//
#include "header/enter_window.h"
#include "header/main_window.h"

GtkWidget *create_Enter_window() {
    GtkBuilder *builder;
    GtkWidget *Enter_window;
    GtkWidget *Enter_button;
    GtkWidget *Exit_button;
    GtkWidget *About_button;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Traffic-Information-System/interface/enter_window.glade",
                              NULL);//从glade获取控件

    Enter_window = GTK_WIDGET(gtk_builder_get_object(builder, "Enter_window"));//获取主窗体
//    gtk_window_set_icon(GTK_WINDOW(Enter_window),create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(Enter_window), "交通信息咨询系统");//设置主窗口标题
    Enter_button = GTK_WIDGET(gtk_builder_get_object(builder, "Enter_button"));//获取主窗口中的进入按钮
    Exit_button = GTK_WIDGET(gtk_builder_get_object(builder, "Exit_button"));//获取主窗口中的退出按钮
    About_button = GTK_WIDGET(gtk_builder_get_object(builder, "About_button"));//获取主窗口中的按钮

    g_signal_connect(Enter_window, "destroy", G_CALLBACK(gtk_main_quit), NULL);//设置主窗口关闭信号链接
    g_signal_connect(Exit_button, "clicked", G_CALLBACK(gtk_main_quit), NULL);//设置“退出按钮”的连接动作
    g_signal_connect(Enter_button, "clicked", G_CALLBACK(on_Enter_button_clicked), Enter_window);//设置“进入系统”按钮的回调函数
    g_signal_connect(About_button, "clicked", G_CALLBACK(on_About_button_clicked), Enter_window);

    return Enter_window;
}

void on_Enter_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_hide(window);
    gtk_widget_show_all(create_main_window());
}

void on_About_button_clicked(GtkWidget *button, gpointer window) {
    GdkPixbuf *pixbuf = gdk_pixbuf_new_from_file("/home/god/Projects/Traffic-Information-System/pictures/服务咨询.png",NULL);
    GtkWidget *dialog = gtk_about_dialog_new();
    gtk_window_set_transient_for(GTK_WINDOW(dialog), window);//设置窗口主从关系
    gtk_about_dialog_set_program_name(GTK_ABOUT_DIALOG(dialog),"交通咨询系统");
    gtk_about_dialog_set_version(GTK_ABOUT_DIALOG(dialog), "1.0");
    gtk_about_dialog_set_copyright(GTK_ABOUT_DIALOG(dialog),"(c) Copyright 2021_Yan_SWU");
    gtk_about_dialog_set_license(GTK_ABOUT_DIALOG(dialog),"licence");
    gtk_about_dialog_set_comments(GTK_ABOUT_DIALOG(dialog), "交通咨询系统，助力出行无忧！");
    gtk_about_dialog_set_website(GTK_ABOUT_DIALOG(dialog), "https://github.com/ZS-Yan/Traffic-Information-System");
    gtk_about_dialog_set_logo(GTK_ABOUT_DIALOG(dialog), pixbuf);
    gtk_dialog_run(GTK_DIALOG(dialog));
    gtk_widget_destroy(dialog);
}