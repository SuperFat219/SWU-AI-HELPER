//
// Created by god on 2021/12/4.
//

#ifndef HUFFMAN_MAIN_WINDOW_H
#define HUFFMAN_MAIN_WINDOW_H


#include <gtk/gtk.h>
#include "huffman.h"

typedef struct {
    GtkWidget *window;
    GtkWidget *text_view;
} encode_import_passing_parameters;

typedef struct {
    GtkWidget *window;
    GtkWidget *result_entry;
    HTreeNode *HuffmanNode[500];
    HCode *HuffmanCode[100];
    int root_location;
    GtkWidget *text_view;
} decode_passing_parameters;

typedef struct {
    GtkWidget *dialog;
    GtkWidget *text_view;
} passing_parameters2;

GtkWidget *create_Enter_window();

void on_Enter_button_clicked(GtkWidget *button, gpointer window);

void on_About_button_clicked(GtkWidget *button, gpointer window);

GtkWidget *create_main_window();

void on_encode_button_clicked(GtkWidget *button, gpointer window);

void on_decode_button_clicked(GtkWidget *button, gpointer window);

GtkWidget *create_encode_window();

void on_encode_entry_activate(GtkWidget *entry, GtkWidget *result_entry);

void on_encode_import_button_clicked(GtkWidget *button, encode_import_passing_parameters *parameters);

void on_encode_export_button_clicked(GtkWidget *button, encode_import_passing_parameters *parameters);

void on_export_file_button_clicked(GtkWidget *button,passing_parameters2 *parameters2);

        void on_return_button_clicked(GtkWidget *button, gpointer window);

GtkWidget *create_decode_window();

void on_import_encode_button_clicked(GtkWidget *button, decode_passing_parameters *parameters);

void on_decode_entry_activate(GtkWidget *entry, decode_passing_parameters *parameters);

void on_decode_import_button_clicked(GtkWidget *button, decode_passing_parameters *parameters);

void on_decode_export_button_clicked(GtkWidget *button, decode_passing_parameters *parameters);

void on_decode_export_file_button_clicked(GtkWidget *button, passing_parameters2 *parameters);

#endif //HUFFMAN_MAIN_WINDOW_H
