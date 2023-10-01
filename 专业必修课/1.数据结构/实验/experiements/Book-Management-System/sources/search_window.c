//
// Created by god on 2021/12/11.
//
#include "headers/search_window.h"

void *create_search_window() {
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *search_window;
    GtkWidget *scrolled_window;
    GtkWidget *change_button;
    GtkWidget *delete_button;
    GtkWidget *return_button;
    GtkWidget *id_entry;
    GtkWidget *name_entry;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/search_window.glade",
                              NULL);//从glade获取控件

    search_window = GTK_WIDGET(gtk_builder_get_object(builder, "search_window"));//获取主窗体
    gtk_window_set_icon(GTK_WINDOW(search_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    scrolled_window = GTK_WIDGET(gtk_builder_get_object(builder, "scrolled_window"));
    gtk_window_set_title(GTK_WINDOW(search_window), "图书信息管理系统");//设置主窗口标题
    change_button = GTK_WIDGET(gtk_builder_get_object(builder, "change_button"));
    delete_button = GTK_WIDGET(gtk_builder_get_object(builder, "delete_button"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    id_entry = GTK_WIDGET(gtk_builder_get_object(builder, "id_entry"));
    name_entry = GTK_WIDGET(gtk_builder_get_object(builder, "name_entry"));

    GtkTreeStore *store = gtk_tree_store_new(N_COLUMNS, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING,
                                             G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING);//新建树视图

    GtkWidget *tree_view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
    GtkCellRenderer *renderer = gtk_cell_renderer_text_new();//使用文本渲染器
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_ID, "图书编号", renderer, "text",
                                                COLUMN_ID, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_NAME, "图书名称", renderer, "text",
                                                COLUMN_NAME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_TYPE, "图书类别", renderer, "text",
                                                COLUMN_TYPE, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_AUTHOR, "作者", renderer, "text",
                                                COLUMN_AUTHOR, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PUB, "出版社", renderer, "text",
                                                COLUMN_PUB, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PAGES, "总页数", renderer, "text",
                                                COLUMN_PAGES, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_STATUS, "借阅状态", renderer, "text",
                                                COLUMN_STATUS, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWTIME, "应归还时间", renderer, "text",
                                                COLUMN_BORROWTIME, NULL);
    gtk_tree_view_expand_all(GTK_TREE_VIEW(tree_view));
    gtk_tree_view_columns_autosize(GTK_TREE_VIEW(tree_view));
    gtk_container_add(GTK_CONTAINER(scrolled_window), tree_view);


    search_passing_parameters *parameters = (search_passing_parameters *) malloc(sizeof(search_passing_parameters));
    parameters->store = store;
    parameters->name_entry = name_entry;
    parameters->id_entry = id_entry;
    parameters->tree_view = tree_view;
    parameters->selection = gtk_tree_view_get_selection(GTK_TREE_VIEW(tree_view));
    parameters->model = gtk_tree_view_get_model(GTK_TREE_VIEW(tree_view));

    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), search_window);
    g_signal_connect(id_entry, "activate", G_CALLBACK(on_id_entry_activate), parameters);
    g_signal_connect(name_entry, "activate", G_CALLBACK(on_name_entry_activate), parameters);
    g_signal_connect(delete_button, "clicked", G_CALLBACK(on_search_delete_button_clicked), parameters);
    g_signal_connect(change_button, "clicked", G_CALLBACK(on_change_button_clicked), parameters);

    gtk_widget_show_all(search_window);
    if (Identity == true) {
        gtk_widget_hide(change_button);//hide指令必须联合show()一起使用
        gtk_widget_hide(delete_button);
    }
//    return search_window;
}

void on_id_entry_activate(GtkWidget *id_entry, search_passing_parameters *parameters) {
    GtkTreeIter iter;
    gtk_tree_store_clear(parameters->store);
    char id_target[30];
    char name_target[30];
    strcpy(id_target, gtk_entry_get_text(GTK_ENTRY(id_entry)));
    strcpy(name_target, gtk_entry_get_text(GTK_ENTRY(parameters->name_entry)));
    for (int i = 0; i < bookData->booksNum; i++) {
        if (strstr(bookData->books[i]->bookId, id_target) != NULL &&
            strstr(bookData->books[i]->bookName, name_target) != NULL) {
            char *status;
            if (bookData->books[i]->status == 1) {
                status = "已借出";
            } else {
                status = "在馆";
            }
            gtk_tree_store_append(parameters->store, &iter, NULL);
            gtk_tree_store_set(parameters->store, &iter, COLUMN_ID, bookData->books[i]->bookId, COLUMN_NAME, \
            bookData->books[i]->bookName, COLUMN_TYPE, bookData->books[i]->bookType, COLUMN_AUTHOR,
                               bookData->books[i]->bookAuthor, COLUMN_PUB, bookData->books[i]->bookPub, COLUMN_PAGES,
                               bookData->books[i]->bookPages, COLUMN_STATUS, status, COLUMN_BORROWTIME,
                               bookData->books[i]->borrowTime, -1);
        }
    }
}

void on_name_entry_activate(GtkWidget *name_entry, search_passing_parameters *parameters) {
    GtkTreeIter iter;
    gtk_tree_store_clear(parameters->store);
    char id_target[30];
    char name_target[30];
    strcpy(name_target, gtk_entry_get_text(GTK_ENTRY(name_entry)));
    strcpy(id_target, gtk_entry_get_text(GTK_ENTRY(parameters->id_entry)));
    for (int i = 0; i < bookData->booksNum; i++) {
        if (strstr(bookData->books[i]->bookId, id_target) != NULL &&
            strstr(bookData->books[i]->bookName, name_target) != NULL) {
            char *status;
            if (bookData->books[i]->status == 1) {
                status = "已借出";
            } else {
                status = "在馆";
            }
            gtk_tree_store_append(parameters->store, &iter, NULL);
            gtk_tree_store_set(parameters->store, &iter, COLUMN_ID, bookData->books[i]->bookId, COLUMN_NAME, \
            bookData->books[i]->bookName, COLUMN_TYPE, bookData->books[i]->bookType, COLUMN_AUTHOR,
                               bookData->books[i]->bookAuthor, COLUMN_PUB, bookData->books[i]->bookPub, COLUMN_PAGES,
                               bookData->books[i]->bookPages, COLUMN_STATUS, status, COLUMN_BORROWTIME,
                               bookData->books[i]->borrowTime, -1);
        }
    }
}

void on_search_delete_button_clicked(GtkWidget *button, search_passing_parameters *parameters) {
    GtkWidget *dialog;
    gint res;
    dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                    GTK_MESSAGE_QUESTION, GTK_BUTTONS_YES_NO, "您确定要删除该书籍信息吗？");
    gtk_window_set_title(GTK_WINDOW(dialog), "提示");
    gtk_dialog_set_default_response(GTK_DIALOG(dialog), GTK_RESPONSE_YES);
    res = gtk_dialog_run(GTK_DIALOG(dialog));
    if (res == GTK_RESPONSE_YES) {
        GtkTreeIter iter;
        if (gtk_tree_selection_get_selected(parameters->selection, &parameters->model, &iter))//让iter指向选择的节点
        {
            char *book_id, *status;
            gtk_tree_model_get(parameters->model, &iter, COLUMN_ID, &book_id, COLUMN_STATUS, &status, -1);
            if (strcmp(status, "已借出") == 0) {
                GtkWidget *warning_dialog;
                gint answer;
                warning_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                        GTK_MESSAGE_INFO, GTK_BUTTONS_CANCEL, "该书籍尚未归还，不建议删除馆藏信息！");
                gtk_dialog_add_button(GTK_DIALOG(warning_dialog), "强制删除", 2);
                gtk_window_set_title(GTK_WINDOW(warning_dialog), "Warning");
                gtk_dialog_set_default_response(GTK_DIALOG(warning_dialog), 2);
                answer = gtk_dialog_run(GTK_DIALOG(warning_dialog));
                if (answer == 2) {
                    gtk_tree_store_remove(parameters->store, &iter);
                }
                delete_book(book_id, bookData);
                gtk_widget_destroy(warning_dialog);
            } else {
                gtk_tree_store_remove(parameters->store, &iter);
                delete_book(book_id, bookData);
            }
        }
    }
    gtk_widget_destroy(dialog);
}

void on_change_button_clicked(GtkWidget *button, search_passing_parameters *search_parameters) {
    GtkTreeIter iter;
    if (gtk_tree_selection_get_selected(search_parameters->selection, &search_parameters->model, &iter)) {
        GtkBuilder *builder;
        GtkWidget *change_window;
        GtkWidget *affirm_change_button;
        GtkWidget *return_button;

        builder = gtk_builder_new();
        gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/change_window.glade",
                                  NULL);//从glade获取控件
        change_window = GTK_WIDGET(gtk_builder_get_object(builder, "change_window"));//获取主窗体
        gtk_window_set_icon(GTK_WINDOW(change_window),
                            create_pixbuf(
                                    "/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
        gtk_window_set_title(GTK_WINDOW(change_window), "图书信息管理系统");//设置主窗口标题
        affirm_change_button = GTK_WIDGET(gtk_builder_get_object(builder, "affirm_change_button"));
        return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));

        change_passing_parameters *parameters = (change_passing_parameters *) malloc(sizeof(change_passing_parameters));
        parameters->window = change_window;
        parameters->id_entry = GTK_WIDGET(gtk_builder_get_object(builder, "id_entry"));
        parameters->name_entry = GTK_WIDGET(gtk_builder_get_object(builder, "name_entry"));
        parameters->type_comboBox = GTK_WIDGET(gtk_builder_get_object(builder, "type_comboBoxText"));
        parameters->type_entry = GTK_WIDGET(gtk_builder_get_object(builder, "type_entry"));
        parameters->pages_entry = GTK_WIDGET(gtk_builder_get_object(builder, "pages_entry"));
        parameters->author_entry = GTK_WIDGET(gtk_builder_get_object(builder, "author_entry"));
        parameters->pub_entry = GTK_WIDGET(gtk_builder_get_object(builder, "pub_entry"));
        for (int i = 0; i < bookTypeData->typeNum; i++) {
            gtk_combo_box_text_append_text(GTK_COMBO_BOX_TEXT(parameters->type_comboBox), bookTypeData->bookType[i]);
        }

        char *book_id, *book_name, *book_type, *book_author, *book_pub, *pages, *status, *borrow_time;
        gtk_tree_model_get(search_parameters->model, &iter, COLUMN_ID, &book_id, COLUMN_NAME, &book_name, COLUMN_TYPE,
                           &book_type, COLUMN_PAGES, &pages, COLUMN_AUTHOR, &book_author, COLUMN_PUB, &book_pub,
                           COLUMN_STATUS, &status, COLUMN_BORROWTIME, &borrow_time, -1);
        gtk_entry_set_text(GTK_ENTRY(parameters->id_entry), book_id);
        gtk_entry_set_text(GTK_ENTRY(parameters->name_entry), book_name);
        gtk_entry_set_text(GTK_ENTRY(parameters->type_entry), book_type);
        gtk_entry_set_text(GTK_ENTRY(parameters->author_entry), book_author);
        gtk_entry_set_text(GTK_ENTRY(parameters->pub_entry), book_pub);
        gtk_entry_set_text(GTK_ENTRY(parameters->pages_entry), pages);
        g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked),
                         change_window);//设置“进入系统”按钮的回调函数
        g_signal_connect(affirm_change_button, "clicked", G_CALLBACK(on_affirm_change_button_clicked), parameters);
        gtk_widget_show_all(change_window);
    }
}

void on_affirm_change_button_clicked(GtkWidget *button, change_passing_parameters *parameters) {
    int index = search_book(bookData->books, bookData->booksNum, gtk_entry_get_text(GTK_ENTRY(parameters->id_entry)));
    char *book_type = gtk_combo_box_text_get_active_text(GTK_COMBO_BOX_TEXT(parameters->type_comboBox));
    strcpy(bookData->books[index]->bookName, gtk_entry_get_text(GTK_ENTRY(parameters->name_entry)));
    strcpy(bookData->books[index]->bookType, book_type);
    strcpy(bookData->books[index]->bookPages, gtk_entry_get_text(GTK_ENTRY(parameters->pages_entry)));
    strcpy(bookData->books[index]->bookAuthor, gtk_entry_get_text(GTK_ENTRY(parameters->author_entry)));
    strcpy(bookData->books[index]->bookPub, gtk_entry_get_text(GTK_ENTRY(parameters->pub_entry)));
    int res = 0;
    for (int i = 0; i < bookTypeData->typeNum; i++) {
        if (strcmp(bookTypeData->bookType[i], book_type) == 0) res = 1;
    }
    if (res == 0) {
        bookTypeData->bookType[bookTypeData->typeNum] = (char *) malloc(sizeof(char) * 30);
        strcpy(bookTypeData->bookType[bookTypeData->typeNum], book_type);
        bookTypeData->typeNum++;
        gtk_combo_box_text_append_text(GTK_COMBO_BOX_TEXT(parameters->type_comboBox), book_type);
    }
    GtkWidget *dialog;
    dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT, GTK_MESSAGE_INFO,
                                    GTK_BUTTONS_OK, "修改图书信息成功！");
    gtk_window_set_title(GTK_WINDOW(dialog), "提示");
    gtk_dialog_run(GTK_DIALOG(dialog));

    gtk_entry_set_text(GTK_ENTRY(parameters->id_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->name_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->type_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->pages_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->author_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->pub_entry), "");

    gtk_widget_destroy(dialog);
}