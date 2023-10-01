//
// Created by god on 2021/12/25.
//

#ifndef TRAFFIC_INFORMATION_SYSTEM_MAIN_WINDOW_H
#define TRAFFIC_INFORMATION_SYSTEM_MAIN_WINDOW_H

#include "header/common.h"

GtkWidget *create_main_window();

void on_plane_button_clicked(GtkWidget *button, gpointer window);

void on_train_button_clicked(GtkWidget *button, gpointer window);

void on_bus_button_clicked(GtkWidget *button, gpointer window);

#endif //TRAFFIC_INFORMATION_SYSTEM_MAIN_WINDOW_H
