//
// Created by god on 2021/12/16.
//
#include "headers/user_window.h"
#include "headers/login_window.h"
#include "headers/View_window.h"
#include "headers/search_window.h"
#include "headers/user_borrow_window.h"
#include "headers/user_return_window.h"
#include "headers/user_person_window.h"

GtkWidget *create_user_window() {
    borrowData = (BorrowInfo *) malloc(sizeof(BorrowInfo));
    borrowData->borrowNum = 0;
    FILE *ip3;
    ip3 = fopen("/home/god/Projects/Book-Management-System/Data/borrow.txt", "r");
    import_borrow_data_from_File(ip3, borrowData);
    fclose(ip3);
    userId = (char *) malloc(sizeof(char) * 30);
    for (int i = 0; i < readerData->readersNum; i++) {
        if (strcmp(readerData->readers[i]->username, username) == 0) {
            strcpy(userId, readerData->readers[i]->readerId);
        }
    }
    GtkBuilder *builder;
    GtkWidget *user_window;
    GtkWidget *user_view_button;
    GtkWidget *user_borrow_button;
    GtkWidget *user_search_button;
    GtkWidget *user_return_book_button;
    GtkWidget *user_person_button;
    GtkWidget *user_exit_button;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/user_window.glade",
                              NULL);
    user_window = GTK_WIDGET(gtk_builder_get_object(builder, "user_window"));
    user_view_button = GTK_WIDGET(gtk_builder_get_object(builder, "user_view_button"));
    user_borrow_button = GTK_WIDGET(gtk_builder_get_object(builder, "user_borrow_button"));
    user_search_button = GTK_WIDGET(gtk_builder_get_object(builder, "user_search_button"));
    user_return_book_button = GTK_WIDGET(gtk_builder_get_object(builder, "user_return_book_button"));
    user_person_button = GTK_WIDGET(gtk_builder_get_object(builder, "user_person_button"));
    user_exit_button = GTK_WIDGET(gtk_builder_get_object(builder, "user_exit_button"));
    gtk_window_set_icon(GTK_WINDOW(user_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));
    gtk_window_set_title(GTK_WINDOW(user_window), "图书信息管理系统");

    g_signal_connect(user_view_button, "clicked", G_CALLBACK(on_user_view_button_clicked), user_window);
    g_signal_connect(user_search_button, "clicked", G_CALLBACK(on_user_search_button_clicked), user_window);
    g_signal_connect(user_borrow_button, "clicked", G_CALLBACK(on_user_borrow_button_clicked), user_window);
    g_signal_connect(user_return_book_button, "clicked", G_CALLBACK(on_user_return_button_clicked), user_window);
    g_signal_connect(user_person_button, "clicked", G_CALLBACK(on_user_person_button_clicked), user_window);
    g_signal_connect(user_window, "destroy", G_CALLBACK(gtk_main_quit), NULL);
    g_signal_connect(user_exit_button, "clicked", G_CALLBACK(on_exit_button_clicked), NULL);

    return user_window;
}

void on_user_view_button_clicked(GtkWidget *button, gpointer window) {
    create_view_window();
}

void on_user_search_button_clicked(GtkWidget *button, gpointer window) {
    create_search_window();
}

void on_user_borrow_button_clicked(GtkWidget *button, gpointer window) {
    create_user_borrow_window();
}

void on_user_return_button_clicked(GtkWidget *button, gpointer window) {
    create_user_return_window();
}

void on_user_person_button_clicked(GtkWidget *button, gpointer window) {
    create_user_person_window();
}

