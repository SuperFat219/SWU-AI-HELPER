//
// Created by god on 2021/12/16.
//

#ifndef BOOK_SYSTEM_BORROW_WINDOW_H
#define BOOK_SYSTEM_BORROW_WINDOW_H

#include "headers/common.h"
#include "headers/data_operations.h"

GtkWidget *create_borrow_window();

void on_borrow_name_entry_clicked(GtkWidget *name_entry, GtkTreeStore *store);
#endif //BOOK_SYSTEM_BORROW_WINDOW_H
