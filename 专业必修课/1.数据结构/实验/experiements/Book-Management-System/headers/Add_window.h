//
// Created by god on 2021/12/14.
//

#ifndef BOOK_SYSTEM_ADD_WINDOW_H
#define BOOK_SYSTEM_ADD_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

typedef struct {
    GtkWidget *window;
    GtkWidget *id_entry;
    GtkWidget *name_entry;
    GtkWidget *type_comboBox;
    GtkWidget *type_entry;
    GtkWidget *pages_entry;
    GtkWidget *author_entry;
    GtkWidget *pub_entry;

} add_passing_parameters;

GtkWidget *create_add_window();

void on_add_button_clicked(GtkWidget *button, add_passing_parameters *parameters);

void on_import_button_clicked(GtkWidget *button, gpointer window);

#endif //BOOK_SYSTEM_ADD_WINDOW_H
