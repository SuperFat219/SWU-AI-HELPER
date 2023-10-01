//
// Created by god on 2021/12/21.
//

#include "headers/user_person_window.h"
#include "headers/login_window.h"
#include "headers/user_window.h"

void create_user_person_window() {
    GtkBuilder *builder;
    GtkWidget *user_person_window;
    GtkWidget *username_entry;
    GtkWidget *password_entry;
    GtkWidget *tel_entry;
    GtkWidget *sex_entry;
    GtkWidget *name_entry;
    GtkWidget *email_entry;
    GtkWidget *current_label;
    GtkWidget *max_borrow_label;
    GtkWidget *scrolled_window;
    GtkWidget *return_button;
    GtkWidget *change_password_button;
    GtkWidget *change_button;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/user_person_window.glade",
                              NULL);
    user_person_window = GTK_WIDGET(gtk_builder_get_object(builder, "user_person_window"));
    username_entry = GTK_WIDGET(gtk_builder_get_object(builder, "username_entry"));
    password_entry = GTK_WIDGET(gtk_builder_get_object(builder, "password_entry"));
    name_entry = GTK_WIDGET(gtk_builder_get_object(builder, "name_entry"));
    sex_entry = GTK_WIDGET(gtk_builder_get_object(builder, "sex_entry"));
    tel_entry = GTK_WIDGET(gtk_builder_get_object(builder, "tel_entry"));
    email_entry = GTK_WIDGET(gtk_builder_get_object(builder, "email_entry"));
    current_label = GTK_WIDGET(gtk_builder_get_object(builder, "current_label"));
    max_borrow_label = GTK_WIDGET(gtk_builder_get_object(builder, "max_borrow_label"));
    scrolled_window = GTK_WIDGET(gtk_builder_get_object(builder, "scrolled_window"));
    change_password_button = GTK_WIDGET(gtk_builder_get_object(builder, "change_password_button"));
    change_button = GTK_WIDGET(gtk_builder_get_object(builder, "change_button"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    gtk_window_set_icon(GTK_WINDOW(user_person_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));
    gtk_window_set_title(GTK_WINDOW(user_person_window), "图书信息管理系统");

    char borrowed_num[4];
    char max_borrowed_num[4];
    int index = search_reader(readerData->readers, readerData->readersNum, userId);
    gtk_entry_set_text(GTK_ENTRY(username_entry), username);
    gtk_entry_set_text(GTK_ENTRY(name_entry), readerData->readers[index]->readerName);
    gtk_entry_set_text(GTK_ENTRY(sex_entry), readerData->readers[index]->readerSex);
    gtk_entry_set_text(GTK_ENTRY(tel_entry), readerData->readers[index]->readerTel);
    gtk_entry_set_text(GTK_ENTRY(email_entry), readerData->readers[index]->email);
    sprintf(borrowed_num, "%d", readerData->readers[index]->borrowedNum);
    gtk_label_set_text(GTK_LABEL(current_label), borrowed_num);
    sprintf(max_borrowed_num, "%d", readerData->readers[index]->maxBorrowNum);
    gtk_label_set_text(GTK_LABEL(max_borrow_label), max_borrowed_num);

    GtkTreeStore *store = gtk_tree_store_new(N_PERSON_COLUMNS, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING,
                                             G_TYPE_STRING,
                                             G_TYPE_STRING);//新建树视图
    GtkWidget *tree_view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
    GtkCellRenderer *renderer = gtk_cell_renderer_text_new();//使用文本渲染器
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PERSONBOOKNAME, "图书名称", renderer,
                                                "text",
                                                COLUMN_PERSONBOOKNAME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PERSONBOOKAUTHOR, "作者", renderer,
                                                "text",
                                                COLUMN_PERSONBOOKAUTHOR, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PERSONBOOKPUB, "出版社", renderer, "text",
                                                COLUMN_PERSONBOOKPUB, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PERSONBORROWTIME, "借阅时间", renderer,
                                                "text",
                                                COLUMN_PERSONBORROWTIME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PERSONRETRUNTIME, "应归还时间", renderer,
                                                "text",
                                                COLUMN_PERSONRETRUNTIME, NULL);
    gtk_tree_view_expand_all(GTK_TREE_VIEW(tree_view));
    gtk_tree_view_columns_autosize(GTK_TREE_VIEW(tree_view));
    gtk_container_add(GTK_CONTAINER(scrolled_window), tree_view);
    GtkTreeIter iter;
    for (int i = 0; i < borrowData->borrowNum; i++) {
        if (strcmp(borrowData->borrowBook[i]->readerName, readerData->readers[index]->readerName) == 0) {
            gtk_tree_store_append(store, &iter, NULL);
            gtk_tree_store_set(store, &iter, COLUMN_PERSONBOOKNAME, borrowData->borrowBook[i]->bookName,
                               COLUMN_PERSONBOOKAUTHOR, borrowData->borrowBook[i]->bookAuthor,
                               COLUMN_PERSONBOOKPUB, borrowData->borrowBook[i]->bookPub,
                               COLUMN_PERSONBORROWTIME, borrowData->borrowBook[i]->borrowTime,
                               COLUMN_PERSONRETRUNTIME, borrowData->borrowBook[i]->returnTime, -1);
        }
    }
    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), user_person_window);
    gtk_widget_show_all(user_person_window);
}

void on_user_person_change_button_clicked(GtkWidget *button, user_person_passing_parameters *parameters) {
//TODO:修改个人信息按钮
}

void on_change_password_button_clicked(GtkWidget *button) {
//TODO:修改密码按钮
}