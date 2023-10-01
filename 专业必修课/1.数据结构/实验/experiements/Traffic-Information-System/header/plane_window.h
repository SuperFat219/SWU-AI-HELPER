//
// Created by god on 2021/12/25.
//

#ifndef TRAFFIC_INFORMATION_SYSTEM_PLANE_WINDOW_H
#define TRAFFIC_INFORMATION_SYSTEM_PLANE_WINDOW_H

#include "header/common.h"
typedef struct {
    GtkWidget *window;
    GtkTreeStore *store;
    GtkWidget *departure_entry;
    GtkWidget *destination_entry;
    GtkWidget *tree_view;
    GtkTreeSelection *selection;
    GtkTreeModel *model;
}passing_parameters;
GtkWidget *create_plane_window();
void on_destination_entry_activate(GtkWidget *destination_entry,passing_parameters *parameters);
void on_time_button_clicked(GtkWidget *button,passing_parameters *parameters);
void on_cost_button_clicked(GtkWidget *button,passing_parameters *parameters);
void on_road_button_clicked(GtkWidget *button,passing_parameters *parameters);

//数据处理函数
int partition_flight(Flight *array[], int low, int high);
void Qsort_flight(Flight *array[], int low, int high);
void Quick_sort_flight(Flight *array[], int length);
int import_flight_data_from_file(FILE *ip, FlightInfo *flightInfo);
void insert_flight(Flight *flight,FlightInfo *flightInfo);
#endif //TRAFFIC_INFORMATION_SYSTEM_PLANE_WINDOW_H
