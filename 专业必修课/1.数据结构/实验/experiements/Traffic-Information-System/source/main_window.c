//
// Created by god on 2021/12/25.
//

#include "header/main_window.h"
#include "header/plane_window.h"

GtkWidget *create_main_window() {
    GtkBuilder *builder;
    GtkWidget *main_window;
    GtkWidget *plane_button;
    GtkWidget *train_button;
    GtkWidget *bus_button;
    GtkWidget *Exit_button;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Traffic-Information-System/interface/main_window.glade",
                              NULL);//从glade获取控件
    main_window = GTK_WIDGET(gtk_builder_get_object(builder, "main_window"));
//    gtk_window_set_icon(GTK_WINDOW(main_window),create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(main_window), "交通咨询系统");
    plane_button = GTK_WIDGET(gtk_builder_get_object(builder, "plane_button"));
    train_button = GTK_WIDGET(gtk_builder_get_object(builder, "train_button"));
    bus_button = GTK_WIDGET(gtk_builder_get_object(builder, "bus_button"));
    Exit_button = GTK_WIDGET(gtk_builder_get_object(builder, "Exit_button"));

    g_signal_connect(plane_button, "clicked", G_CALLBACK(on_plane_button_clicked), main_window);
    g_signal_connect(train_button, "clicked", G_CALLBACK(on_train_button_clicked), main_window);
    g_signal_connect(bus_button, "clicked", G_CALLBACK(on_bus_button_clicked), main_window);
//    g_signal_connect(Exit_button, "clicked", G_CALLBACK(on_exit_button_clicked), NULL);//设置“退出按钮”的连接动作
    g_signal_connect(main_window, "destroy", G_CALLBACK(gtk_main_quit), NULL);//设置主窗口关闭信号链接
    g_signal_connect(Exit_button, "clicked", G_CALLBACK(gtk_main_quit), main_window);
    return main_window;
}

void on_plane_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_show_all(create_plane_window());
}

void on_train_button_clicked(GtkWidget *button, gpointer window) {

}

void on_bus_button_clicked(GtkWidget *button, gpointer window) {

}