#include <stdio.h>

#include "main_window.h"

#define True 1
#define False 0

int main(int argc, char *argv[]) {
    //初始化主窗口
    GtkWidget *Enter_window;
    gtk_init(&argc, &argv);
    Enter_window = create_Enter_window(); //创建主窗口
    gtk_widget_show_all(Enter_window); //显示主窗口
    gtk_main();
    return 0;
}
//gcc main.c window_main.c common_func.c linklist_function.c system_functions.c -o main `pkg-config --cflags --libs gtk+-3.0` -g

