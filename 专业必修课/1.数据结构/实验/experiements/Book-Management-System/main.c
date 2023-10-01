//
// Created by god on 2021/12/11.
//
#include "headers/login_window.h"

int main(int argc, char *argv[]) {
    GtkWidget *login_window;
    gtk_init(&argc, &argv);
    login_window = create_login_window(); //创建进入窗口
    gtk_widget_show_all(login_window); //显示进入窗口
    gtk_main();
}