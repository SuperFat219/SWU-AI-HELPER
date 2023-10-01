//
// Created by god on 2021/12/16.
//
#include "headers/user_borrow_window.h"
#include "headers/login_window.h"
#include "headers/user_window.h"

void create_user_borrow_window() {
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *user_borrow_window;
    GtkWidget *borrow_button;
    GtkWidget *return_button;
    GtkWidget *status_label;
    user_borrow_passing_parameters *parameters = (user_borrow_passing_parameters *) malloc(
            sizeof(user_borrow_passing_parameters));
    borrow_flag = 0;//标志书籍状态，0表示无馆藏，1表示可借出，2表示已被借出

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/user_borrow_window.glade",
                              NULL);//从glade获取控件
    user_borrow_window = GTK_WIDGET(gtk_builder_get_object(builder, "user_borrow_window"));//获取主窗体
    gtk_window_set_icon(GTK_WINDOW(user_borrow_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(user_borrow_window), "图书信息管理系统");//设置主窗口标题
    borrow_button = GTK_WIDGET(gtk_builder_get_object(builder, "borrow_button"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    status_label = GTK_WIDGET(gtk_builder_get_object(builder, "status_label"));

    parameters->window = user_borrow_window;
    parameters->name_entry = GTK_WIDGET(gtk_builder_get_object(builder, "name_entry"));
    parameters->id_entry = GTK_WIDGET(gtk_builder_get_object(builder, "id_entry"));
    parameters->type_entry = GTK_WIDGET(gtk_builder_get_object(builder, "type_entry"));
    parameters->pages_entry = GTK_WIDGET(gtk_builder_get_object(builder, "pages_entry"));
    parameters->author_entry = GTK_WIDGET(gtk_builder_get_object(builder, "author_entry"));
    parameters->pub_entry = GTK_WIDGET(gtk_builder_get_object(builder, "pub_entry"));
    parameters->status_label = status_label;

    g_signal_connect(parameters->name_entry, "leave-notify-event", G_CALLBACK(on_name_entry_leave_notified),
                     parameters);
    //https://gitlab.gnome.org/GNOME/gtk/-/blob/gtk-3-24/gtk/gtkwidget.h#L459
    //https://openhome.cc/Gossip/GTKGossip/GTKEventHandler.html
    g_signal_connect(borrow_button, "clicked", G_CALLBACK(on_borrow_button_clicked), parameters);
    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), user_borrow_window);
    gtk_widget_show_all(user_borrow_window);
}

void on_name_entry_leave_notified(GtkWidget *name_entry, GdkEvent *event, user_borrow_passing_parameters *parameters) {
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
//        int index = search_borrow(borrowData->borrowBook, borrowData->borrowNum, book_name);
        if (index != -1) {
            parameters->index = index;
            borrow_flag = 1;
            gtk_entry_set_text(GTK_ENTRY(parameters->id_entry), bookData->books[index]->bookId);
            gtk_entry_set_text(GTK_ENTRY(parameters->type_entry), bookData->books[index]->bookType);
            gtk_entry_set_text(GTK_ENTRY(parameters->pages_entry), bookData->books[index]->bookPages);
            gtk_entry_set_text(GTK_ENTRY(parameters->author_entry), bookData->books[index]->bookAuthor);
            gtk_entry_set_text(GTK_ENTRY(parameters->pub_entry), bookData->books[index]->bookPub);
            if (!bookData->books[index]->status) {
                //在馆
                gtk_label_set_text(GTK_LABEL(parameters->status_label), "在馆");
            } else {
                gtk_label_set_text(GTK_LABEL(parameters->status_label), "该图书已被借出！");
                borrow_flag = 2;
            }
        }
    }
}

void on_borrow_button_clicked(GtkWidget *button, user_borrow_passing_parameters *parameters) {
    if (borrow_flag == 2) {
        GtkWidget *dialog;
        dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                        GTK_MESSAGE_INFO,
                                        GTK_BUTTONS_OK, "图书已被借出，无法借阅！");
        gtk_window_set_title(GTK_WINDOW(dialog), "提示");
        gtk_dialog_run(GTK_DIALOG(dialog));
        gtk_widget_destroy(dialog);
    } else if (borrow_flag == 0) {
        GtkWidget *dialog;
        dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                        GTK_MESSAGE_INFO,
                                        GTK_BUTTONS_OK, "该书无馆藏，无法借阅！");
        gtk_window_set_title(GTK_WINDOW(dialog), "提示");
        gtk_dialog_run(GTK_DIALOG(dialog));
        gtk_widget_destroy(dialog);
    } else {
        BorrowBook *borrowBook = (BorrowBook *) malloc(sizeof(BorrowBook));
        strcpy(borrowBook->bookID, gtk_entry_get_text(GTK_ENTRY(parameters->id_entry)));
        strcpy(borrowBook->bookName, gtk_entry_get_text(GTK_ENTRY(parameters->name_entry)));
        strcpy(borrowBook->bookAuthor, gtk_entry_get_text(GTK_ENTRY(parameters->author_entry)));
        strcpy(borrowBook->bookPub, gtk_entry_get_text(GTK_ENTRY(parameters->pub_entry)));
        int reader_index = search_reader(readerData->readers, readerData->readersNum, userId);
        strcpy(borrowBook->readerName, readerData->readers[reader_index]->readerName);
        char borrowTime[30];
        char returnTime[30];
        get_current_borrow_time(borrowTime);
        get_return_book_time(returnTime);
        strcpy(borrowBook->borrowTime, borrowTime);
        strcpy(borrowBook->returnTime, returnTime);
        insert_borrow_book(borrowBook, borrowData);
        GtkWidget *dialog;
        dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                        GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "借阅成功！");
        gtk_window_set_title(GTK_WINDOW(dialog), "提示");
        gtk_dialog_run(GTK_DIALOG(dialog));

        gtk_entry_set_text(GTK_ENTRY(parameters->id_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->name_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->type_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->pages_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->author_entry), "");
        gtk_entry_set_text(GTK_ENTRY(parameters->pub_entry), "");
        gtk_label_set_text(GTK_LABEL(parameters->status_label), "馆藏无此书目");
        bookData->books[parameters->index]->status = 1;
        strcpy(bookData->books[parameters->index]->borrowTime, borrowTime);
        borrow_flag = 0;
        readerData->readers[reader_index]->borrowedNum += 1;
        gtk_widget_destroy(dialog);
    }

}