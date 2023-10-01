//
// Created by god on 2021/12/18.
//

//
// Created by god on 2021/12/16.
//
#include "headers/user_return_window.h"
#include "headers/user_window.h"

void create_user_return_window() {
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *user_return_window;
    GtkWidget *return_book_button;
    GtkWidget *return_button;
    GtkWidget *status_label;
    return_flag = 0;
    user_return_passing_parameters *parameters = (user_return_passing_parameters *) malloc(
            sizeof(user_return_passing_parameters));

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/user_return_window.glade",
                              NULL);//从glade获取控件
    user_return_window = GTK_WIDGET(gtk_builder_get_object(builder, "user_return_window"));//获取主窗体
    gtk_window_set_icon(GTK_WINDOW(user_return_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(user_return_window), "图书信息管理系统");//设置主窗口标题
    return_book_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_book_button"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    status_label = GTK_WIDGET(gtk_builder_get_object(builder, "status_label"));

    parameters->window = user_return_window;
    parameters->name_entry = GTK_WIDGET(gtk_builder_get_object(builder, "name_entry"));
    parameters->id_entry = GTK_WIDGET(gtk_builder_get_object(builder, "id_entry"));
    parameters->type_entry = GTK_WIDGET(gtk_builder_get_object(builder, "type_entry"));
    parameters->pages_entry = GTK_WIDGET(gtk_builder_get_object(builder, "pages_entry"));
    parameters->author_entry = GTK_WIDGET(gtk_builder_get_object(builder, "author_entry"));
    parameters->pub_entry = GTK_WIDGET(gtk_builder_get_object(builder, "pub_entry"));
    parameters->status_label = status_label;

    g_signal_connect(parameters->name_entry, "leave-notify-event", G_CALLBACK(
            on_return_window_name_entry_leave_notified), parameters);
    g_signal_connect(return_book_button, "clicked", G_CALLBACK(on_return_book_button_clicked), parameters);
    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), user_return_window);
    gtk_widget_show_all(user_return_window);
//    return user_borrow_window;
}

void on_return_window_name_entry_leave_notified(GtkWidget *name_entry, GdkEvent *event,
                                                user_return_passing_parameters *parameters) {
    GdkEventType type = event->type;
    if (type == GDK_LEAVE_NOTIFY) {
        const char *book_name = gtk_entry_get_text(GTK_ENTRY(name_entry));
        int index = -1;
        for (int i = 0; i < bookData->booksNum; i++) {
            if (strcmp(bookData->books[i]->bookName, book_name) == 0) {
                index = i;
                break;
            }
        }
//        int index = search_book_name(bookData->books, bookData->booksNum, book_name);
        if (index != -1) {
            parameters->index = index;
            gtk_entry_set_text(GTK_ENTRY(parameters->id_entry), bookData->books[index]->bookId);
            gtk_entry_set_text(GTK_ENTRY(parameters->type_entry), bookData->books[index]->bookType);
//            char *p = gtk_entry_get_text(GTK_ENTRY(parameters->type_entry));
            gtk_entry_set_text(GTK_ENTRY(parameters->pages_entry), bookData->books[index]->bookPages);
            gtk_entry_set_text(GTK_ENTRY(parameters->author_entry), bookData->books[index]->bookAuthor);
            gtk_entry_set_text(GTK_ENTRY(parameters->pub_entry), bookData->books[index]->bookPub);
            if (!bookData->books[index]->status) {
                //在馆
                gtk_label_set_text(GTK_LABEL(parameters->status_label), "在馆");
                return_flag = 1;
            } else {
                gtk_label_set_text(GTK_LABEL(parameters->status_label), "该图书尚未归还！");
                return_flag = 2;
            }
        }
    }
}

void on_return_book_button_clicked(GtkWidget *button, user_return_passing_parameters *parameters) {
    if (return_flag == 1) {
        GtkWidget *dialog;
        dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                        GTK_MESSAGE_INFO,
                                        GTK_BUTTONS_OK, "图书未被借出，无法归还！");
        gtk_window_set_title(GTK_WINDOW(dialog), "提示");
        gtk_dialog_run(GTK_DIALOG(dialog));
        gtk_widget_destroy(dialog);
    } else if (return_flag == 0) {
        GtkWidget *dialog;
        dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                        GTK_MESSAGE_INFO,
                                        GTK_BUTTONS_OK, "该书无馆藏，无法归还！");
        gtk_window_set_title(GTK_WINDOW(dialog), "提示");
        gtk_dialog_run(GTK_DIALOG(dialog));
        gtk_widget_destroy(dialog);
    } else {
        char *book_id = gtk_entry_get_text(GTK_ENTRY(parameters->id_entry));
        delete_borrow_book(book_id, borrowData);
        GtkWidget *dialog;
        dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                        GTK_MESSAGE_INFO,
                                        GTK_BUTTONS_OK, "成功归还！");
        gtk_window_set_title(GTK_WINDOW(dialog), "提示");
        gtk_dialog_run(GTK_DIALOG(dialog));
        gtk_entry_set_text(GTK_ENTRY(parameters->id_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->name_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->type_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->pages_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->author_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->pub_entry), "");
        bookData->books[parameters->index]->status = 0;
        strcpy(bookData->books[parameters->index]->borrowTime, "无");
        gtk_label_set_text(GTK_LABEL(parameters->status_label), "");
        return_flag = 0;
        int reader_index = search_reader(readerData->readers, readerData->readersNum, userId);
        readerData->readers[reader_index]->borrowedNum -= 1;
        gtk_widget_destroy(dialog);
    }
}