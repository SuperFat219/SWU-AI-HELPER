//
// Created by god on 2021/12/16.
//

#include "headers/borrow_window.h"

GtkWidget *create_borrow_window() {
    borrowData = (BorrowInfo *) malloc(sizeof(BorrowInfo));
    borrowData->borrowNum = 0;
    FILE *ip3;
    ip3 = fopen("/home/god/Projects/Book-Management-System/Data/borrow.txt", "r");
    import_borrow_data_from_File(ip3, borrowData);
    fclose(ip3);
    GtkBuilder *builder;
    GtkWidget *borrow_window;
    GtkWidget *scrolled_window;
    GtkWidget *return_button;
    GtkWidget *name_entry;
    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/borrow_window.glade",
                              NULL);
    borrow_window = GTK_WIDGET(gtk_builder_get_object(builder, "borrow_window"));
    scrolled_window = GTK_WIDGET(gtk_builder_get_object(builder, "scrolled_window"));
    name_entry = GTK_WIDGET(gtk_builder_get_object(builder, "name_entry"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    gtk_window_set_icon(GTK_WINDOW(borrow_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));
    gtk_window_set_title(GTK_WINDOW(borrow_window), "图书信息管理系统");


    GtkTreeStore *store = gtk_tree_store_new(N_BORROW_COLUMNS, G_TYPE_INT, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING,
                                             G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING);//新建树视图

    GtkWidget *tree_view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
    GtkCellRenderer *renderer = gtk_cell_renderer_text_new();//使用文本渲染器
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWID, "借阅序号", renderer, "text",
                                                COLUMN_BORROWID, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWREADERNAME, "借阅读者姓名", renderer,
                                                "text",
                                                COLUMN_BORROWREADERNAME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWBOOKNAME, "图书名称", renderer,
                                                "text",
                                                COLUMN_BORROWBOOKNAME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWBOOKAUTHOR, "作者", renderer,
                                                "text",
                                                COLUMN_BORROWBOOKAUTHOR, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWBOOKPUB, "出版社", renderer, "text",
                                                COLUMN_BORROWBOOKPUB, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWBORROWTIME, "借阅时间", renderer,
                                                "text",
                                                COLUMN_BORROWBORROWTIME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_RETURNTIME, "应归还时间", renderer, "text",
                                                COLUMN_RETURNTIME, NULL);
    gtk_tree_view_expand_all(GTK_TREE_VIEW(tree_view));
    gtk_tree_view_columns_autosize(GTK_TREE_VIEW(tree_view));
    gtk_container_add(GTK_CONTAINER(scrolled_window), tree_view);

    GtkTreeIter iter;
    for (int i = 0; i < borrowData->borrowNum; i++) {
        gtk_tree_store_append(store, &iter, NULL);
        gtk_tree_store_set(store, &iter, COLUMN_BORROWID, i + 1, COLUMN_BORROWREADERNAME, \
        borrowData->borrowBook[i]->readerName, COLUMN_BORROWBOOKNAME, borrowData->borrowBook[i]->bookName,
                           COLUMN_BORROWBOOKAUTHOR, borrowData->borrowBook[i]->bookAuthor,
                           COLUMN_BORROWBOOKPUB, borrowData->borrowBook[i]->bookPub,
                           COLUMN_BORROWBORROWTIME, borrowData->borrowBook[i]->borrowTime,
                           COLUMN_RETURNTIME, borrowData->borrowBook[i]->returnTime, -1);
    }

    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), borrow_window);
    g_signal_connect(name_entry, "activate", G_CALLBACK(on_borrow_name_entry_clicked), store);
    return borrow_window;
}

void on_borrow_name_entry_clicked(GtkWidget *name_entry, GtkTreeStore *store) {
    GtkTreeIter iter;
    gtk_tree_store_clear(store);
    char name_target[30];
    strcpy(name_target, gtk_entry_get_text(GTK_ENTRY(name_entry)));
    for (int i = 0; i < borrowData->borrowNum; i++) {
        if (strstr(borrowData->borrowBook[i]->readerName, name_target) != NULL) {
            gtk_tree_store_append(store, &iter, NULL);
            gtk_tree_store_set(store, &iter, COLUMN_BORROWID, i + 1, COLUMN_BORROWREADERNAME, \
        borrowData->borrowBook[i]->readerName, COLUMN_BORROWBOOKNAME, borrowData->borrowBook[i]->bookName,
                               COLUMN_BORROWBOOKAUTHOR, borrowData->borrowBook[i]->bookAuthor,
                               COLUMN_BORROWBOOKPUB, borrowData->borrowBook[i]->bookPub,
                               COLUMN_BORROWBORROWTIME, borrowData->borrowBook[i]->borrowTime,
                               COLUMN_RETURNTIME, borrowData->borrowBook[i]->returnTime, -1);
        }
    }
}