//
// Created by god on 2021/12/21.
//

#ifndef BOOK_SYSTEM_USER_PERSON_WINDOW_H
#define BOOK_SYSTEM_USER_PERSON_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

enum {
    COLUMN_PERSONBOOKNAME,
    COLUMN_PERSONBOOKAUTHOR,
    COLUMN_PERSONBOOKPUB,
    COLUMN_PERSONBORROWTIME,
    COLUMN_PERSONRETRUNTIME,
    N_PERSON_COLUMNS
};
typedef struct {
    GtkWidget *window;
    GtkWidget *name_entry;
    GtkWidget *sex_entry;
    GtkWidget *tel_entry;
    GtkWidget *email_entry;
    GtkWidget *current_label;
    GtkTreeStore *store;
} user_person_passing_parameters;

void create_user_person_window();

void on_user_person_change_button_clicked(GtkWidget *button, user_person_passing_parameters *parameters);

void on_change_password_button_clicked(GtkWidget *button);

#endif //BOOK_SYSTEM_USER_PERSON_WINDOW_H
