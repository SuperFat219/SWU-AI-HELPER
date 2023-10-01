//
// Created by god on 2021/12/11.
//

#ifndef BOOK_SYSTEM_VIEW_WINDOW_H
#define BOOK_SYSTEM_VIEW_WINDOW_H


#include "headers/common.h"
#include "headers/data_operations.h"

typedef struct {
    GtkWidget *window;
    GtkWidget *tree_view;
    GtkTreeSelection *selection;
    GtkTreeModel *model;
    GtkTreeStore *store;
    GtkWidget *current_label;
    GtkWidget *borrow_label;
} delete_passing_parameters;

void *create_view_window();

int find_type_index(char *book_type, BookType *book_type_data);

int count_unique_type_bookNum(BookInfo *bookInfo, char *book_type);

void insert_tree_view(GtkTreeStore *store, GtkTreeIter parent_iter, GtkTreeIter child_iter, Book *book);

void on_delete_button_clicked(GtkWidget *button, delete_passing_parameters *parameters);

void on_delete_all_button_clicked(GtkWidget *button, delete_passing_parameters *parameters);

void update_label(GtkWidget *current_label, GtkWidget *borrow_label);

#endif //BOOK_SYSTEM_VIEW_WINDOW_H
