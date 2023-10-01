//
// Created by god on 2021/12/18.
//

#ifndef BOOK_SYSTEM_USER_RETURN_WINDOW_H
#define BOOK_SYSTEM_USER_RETURN_WINDOW_H

#include "headers/data_operations.h"
#include "headers/common.h"

typedef struct {
    GtkWidget *window;
    GtkWidget *id_entry;
    GtkWidget *name_entry;
    GtkWidget *type_entry;
    GtkWidget *pages_entry;
    GtkWidget *author_entry;
    GtkWidget *pub_entry;
    GtkWidget *status_label;
    int index;
} user_return_passing_parameters;

int return_flag;

void create_user_return_window();

void on_return_window_name_entry_leave_notified(GtkWidget *name_entry, GdkEvent *event,
                                                user_return_passing_parameters *parameters);

void on_return_book_button_clicked(GtkWidget *button, user_return_passing_parameters *parameters);

#endif //BOOK_SYSTEM_USER_RETURN_WINDOW_H
