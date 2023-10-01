//
// Created by god on 2021/12/16.
//

#ifndef BOOK_SYSTEM_USER_WINDOW_H
#define BOOK_SYSTEM_USER_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

char *userId;

GtkWidget *create_user_window();

void on_user_view_button_clicked(GtkWidget *button, gpointer window);

void on_user_search_button_clicked(GtkWidget *button, gpointer window);

void on_user_borrow_button_clicked(GtkWidget *button, gpointer window);

void on_user_return_button_clicked(GtkWidget *button, gpointer window);

void on_user_person_button_clicked(GtkWidget *button, gpointer window);

#endif //BOOK_SYSTEM_USER_WINDOW_H
