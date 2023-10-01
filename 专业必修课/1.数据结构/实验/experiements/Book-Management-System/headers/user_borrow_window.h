//
// Created by god on 2021/12/16.
//

#ifndef BOOK_SYSTEM_USER_BORROW_WINDOW_H
#define BOOK_SYSTEM_USER_BORROW_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

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
} user_borrow_passing_parameters;

void create_user_borrow_window();

//void on_name_entry_leave_notified(GtkWidget *entry,user_borrow_passing_parameters *parameters);
void on_name_entry_leave_notified(GtkWidget *name_entry, GdkEvent *event, user_borrow_passing_parameters *parameters);

void on_borrow_button_clicked(GtkWidget *button, user_borrow_passing_parameters *parameters);

int borrow_flag;
//user_borrow_passing_parameters *parameters;
#endif //BOOK_SYSTEM_USER_BORROW_WINDOW_H
