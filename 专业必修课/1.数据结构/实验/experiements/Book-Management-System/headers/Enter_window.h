//
// Created by god on 2021/12/16.
//

#ifndef BOOK_SYSTEM_ENTER_WINDOW_H
#define BOOK_SYSTEM_ENTER_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

GtkWidget *create_Enter_window();

void on_Enter_button_clicked(GtkWidget *button, gpointer window);

void on_About_button_clicked(GtkWidget *button, gpointer window);

#endif //BOOK_SYSTEM_ENTER_WINDOW_H
