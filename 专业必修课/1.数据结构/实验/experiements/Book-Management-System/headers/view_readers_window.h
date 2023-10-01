//
// Created by god on 2021/12/15.
//

#ifndef BOOK_SYSTEM_VIEW_READERS_WINDOW_H
#define BOOK_SYSTEM_VIEW_READERS_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

typedef struct {
    GtkWidget *window;
    GtkTreeStore *store;
    GtkWidget *tree_view;
    GtkTreeSelection *selection;
    GtkTreeModel *model;
} view_readers_passing_parameters;

GtkWidget *create_view_readers_window();

void on_import_readers_button_clicked(GtkWidget *button, view_readers_passing_parameters *parameters);

void on_delete_reader_button_clicked(GtkWidget *button, view_readers_passing_parameters *parameters);

void on_delete_all_readers_button_clicked(GtkWidget *button, view_readers_passing_parameters *parameters);

#endif //BOOK_SYSTEM_VIEW_READERS_WINDOW_H
