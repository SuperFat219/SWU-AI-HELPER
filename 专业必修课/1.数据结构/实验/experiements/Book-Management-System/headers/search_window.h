//
// Created by god on 2021/12/11.
//

#ifndef BOOK_SYSTEM_SEARCH_WINDOW_H
#define BOOK_SYSTEM_SEARCH_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

typedef struct {
    GtkWidget *window;
    GtkTreeStore *store;
    GtkWidget *id_entry;
    GtkWidget *name_entry;
    GtkWidget *tree_view;
    GtkTreeSelection *selection;
    GtkTreeModel *model;
} search_passing_parameters;
typedef struct {
    GtkWidget *window;
    GtkWidget *id_entry;
    GtkWidget *name_entry;
    GtkWidget *type_comboBox;
    GtkWidget *type_entry;
    GtkWidget *pages_entry;
    GtkWidget *author_entry;
    GtkWidget *pub_entry;

} change_passing_parameters;

void *create_search_window();

void on_id_entry_activate(GtkWidget *id_entry, search_passing_parameters *parameters);

void on_name_entry_activate(GtkWidget *entry, search_passing_parameters *parameters);

void on_search_delete_button_clicked(GtkWidget *button, search_passing_parameters *parameters);

void on_change_button_clicked(GtkWidget *button, search_passing_parameters *search_parameters);

void on_affirm_change_button_clicked(GtkWidget *button, change_passing_parameters *parameters);

#endif //BOOK_SYSTEM_SEARCH_WINDOW_H
