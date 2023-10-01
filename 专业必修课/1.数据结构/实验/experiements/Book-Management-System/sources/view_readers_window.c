//
// Created by god on 2021/12/15.
//

#include "headers/view_readers_window.h"

GtkWidget *create_view_readers_window() {
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *view_readers_window;
    GtkWidget *scrolled_window;
    GtkWidget *import_button;
    GtkWidget *delete_button;
    GtkWidget *delete_all_button;
    GtkWidget *return_button;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/view_reader_window.glade",
                              NULL);//从glade获取控件
    view_readers_window = GTK_WIDGET(gtk_builder_get_object(builder, "view_readers_window"));//获取主窗体
    gtk_window_set_icon(GTK_WINDOW(view_readers_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(view_readers_window), "图书信息管理系统");//设置主窗口标题
    scrolled_window = GTK_WIDGET(gtk_builder_get_object(builder, "scrolled_window"));
    import_button = GTK_WIDGET(gtk_builder_get_object(builder, "import_button"));
    delete_button = GTK_WIDGET(gtk_builder_get_object(builder, "delete_button"));
    delete_all_button = GTK_WIDGET(gtk_builder_get_object(builder, "delete_all_button"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));

    GtkTreeStore *store = gtk_tree_store_new(N_READER_COLUMNS, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING,
                                             G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING);//新建树视图

    for (int i = 0; i < readerData->readersNum; i++) {
        GtkTreeIter iter;
        char borrowed_num[5];
        sprintf(borrowed_num, "%d", readerData->readers[i]->borrowedNum);
        char max_borrow_num[5];
        sprintf(max_borrow_num, "%d", readerData->readers[i]->maxBorrowNum);
        gtk_tree_store_append(store, &iter, NULL);
        gtk_tree_store_set(store, &iter, COLUMN_READERID, readerData->readers[i]->readerId, COLUMN_READERNAME,
                           readerData->readers[i]->readerName, COLUMN_READERSEX,
                           readerData->readers[i]->readerSex, COLUMN_READERTEL, readerData->readers[i]->readerTel,
                           COLUMN_READEREMAIL, readerData->readers[i]->email, COLUMN_BORROWEDNUM,
                           borrowed_num, COLUMN_MAXBORROWNUM, max_borrow_num, -1);
    }

    /*建立窗口视图*/
    GtkWidget *tree_view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
    GtkCellRenderer *renderer = gtk_cell_renderer_text_new();//使用文本渲染器
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_READERID, "读者编号", renderer, "text",
                                                COLUMN_READERID, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_READERNAME, "读者姓名", renderer, "text",
                                                COLUMN_READERNAME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_READERSEX, "读者性别", renderer, "text",
                                                COLUMN_READERSEX, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_READERTEL, "电话", renderer, "text",
                                                COLUMN_READERTEL, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_READEREMAIL, "邮箱", renderer, "text",
                                                COLUMN_READEREMAIL, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_BORROWEDNUM, "已借图书数量", renderer,
                                                "text", COLUMN_BORROWEDNUM, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_MAXBORROWNUM, "最大可借阅图书数量", renderer,
                                                "text", COLUMN_MAXBORROWNUM, NULL);
    gtk_tree_view_expand_all(GTK_TREE_VIEW(tree_view));
    gtk_tree_view_columns_autosize(GTK_TREE_VIEW(tree_view));
    gtk_container_add(GTK_CONTAINER(scrolled_window), tree_view);

    view_readers_passing_parameters *parameters = (view_readers_passing_parameters *) malloc(
            (sizeof(view_readers_passing_parameters)));
    parameters->window = view_readers_window;
    parameters->store = store;
    parameters->tree_view = tree_view;
    parameters->selection = gtk_tree_view_get_selection(GTK_TREE_VIEW(tree_view));
    parameters->model = gtk_tree_view_get_model(GTK_TREE_VIEW(tree_view));

    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), view_readers_window);
    g_signal_connect(import_button, "clicked", G_CALLBACK(on_import_readers_button_clicked), parameters);
    g_signal_connect(delete_button, "clicked", G_CALLBACK(on_delete_reader_button_clicked), parameters);
    g_signal_connect(delete_all_button, "clicked", G_CALLBACK(on_delete_all_readers_button_clicked), parameters);

    return view_readers_window;
}

void on_import_readers_button_clicked(GtkWidget *button, view_readers_passing_parameters *parameters) {
    GtkWidget *dialog;
    GtkFileChooserAction action = GTK_FILE_CHOOSER_ACTION_OPEN;
    gint res;
    dialog = gtk_file_chooser_dialog_new("Open File", GTK_WINDOW(parameters->window), action, "_Cancel",
                                         GTK_RESPONSE_CANCEL, "_open", GTK_RESPONSE_ACCEPT, NULL);
    res = gtk_dialog_run(GTK_DIALOG(dialog));
    if (res == GTK_RESPONSE_ACCEPT) {
        char *filename;
        GtkFileChooser *chooser = GTK_FILE_CHOOSER(dialog);
        filename = gtk_file_chooser_get_filename(chooser);
        FILE *ph;
        if ((ph = fopen(filename, "r")) == NULL) {
            GtkWidget *warning_dialog;
            warning_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                    GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "文件打开出错！");
            gtk_window_set_title(GTK_WINDOW(warning_dialog), "错误");
            gtk_dialog_run(GTK_DIALOG(warning_dialog));
            gtk_widget_destroy(warning_dialog);
        } else {
            ResultDisplay *result = (ResultDisplay *) malloc(sizeof(ResultDisplay));
            result = import_reader_data_from_file(ph, readerData);
//            Quick_sort(bookData->books,bookData->booksNum);
            fclose(ph);
            GtkWidget *prompt_dialog;
            prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                   GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "成功读取%d条数据!\n有%d条数据未成功读取，请检查格式错误",
                                                   result->correctNum, result->wrongNum);
            gtk_window_set_title(GTK_WINDOW(prompt_dialog), "提示");
            gtk_dialog_run(GTK_DIALOG(prompt_dialog));
            gtk_widget_destroy(prompt_dialog);

            g_free(filename);
        }
    }
    gtk_tree_store_clear(parameters->store);
    for (int i = 0; i < readerData->readersNum; i++) {
        GtkTreeIter iter;
        char borrowed_num[5];
        sprintf(borrowed_num, "%d", readerData->readers[i]->borrowedNum);
        char max_borrow_num[5];
        sprintf(max_borrow_num, "%d", readerData->readers[i]->maxBorrowNum);
        gtk_tree_store_append(parameters->store, &iter, NULL);
        gtk_tree_store_set(parameters->store, &iter, COLUMN_READERID, readerData->readers[i]->readerId,
                           COLUMN_READERNAME,
                           readerData->readers[i]->readerName, COLUMN_READERSEX,
                           readerData->readers[i]->readerSex, COLUMN_READERTEL, readerData->readers[i]->readerTel,
                           COLUMN_READEREMAIL, readerData->readers[i]->email, COLUMN_BORROWEDNUM,
                           borrowed_num, COLUMN_MAXBORROWNUM, max_borrow_num, -1);
    }
    gtk_widget_destroy(dialog);
}

void on_delete_reader_button_clicked(GtkWidget *button, view_readers_passing_parameters *parameters) {
    GtkWidget *dialog;
    gint res;
    dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                    GTK_MESSAGE_QUESTION, GTK_BUTTONS_YES_NO, "您确定要删除该读者信息吗？");
    gtk_window_set_title(GTK_WINDOW(dialog), "提示");
    gtk_dialog_set_default_response(GTK_DIALOG(dialog), GTK_RESPONSE_YES);
    res = gtk_dialog_run(GTK_DIALOG(dialog));
    if (res == GTK_RESPONSE_YES) {
        GtkTreeIter iter;
        if (gtk_tree_selection_get_selected(parameters->selection, &parameters->model, &iter))//让iter指向选择的节点
        {
            char *reader_id, *borrow_num;
            gtk_tree_model_get(parameters->model, &iter, COLUMN_READERID, &reader_id, -1);
            gtk_tree_model_get(parameters->model, &iter, COLUMN_BORROWEDNUM, &borrow_num, -1);
            if (atoi(borrow_num) > 0) {
                GtkWidget *warning_dialog;
                gint answer;
                warning_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                        GTK_MESSAGE_INFO, GTK_BUTTONS_CANCEL, "该读者尚有书籍未归还，不建议删除其信息！");
                gtk_dialog_add_button(GTK_DIALOG(warning_dialog), "强制删除", 2);
                gtk_window_set_title(GTK_WINDOW(warning_dialog), "Warning");
                gtk_dialog_set_default_response(GTK_DIALOG(warning_dialog), 2);
                answer = gtk_dialog_run(GTK_DIALOG(warning_dialog));
                if (answer == 2) {
                    gtk_tree_store_remove(parameters->store, &iter);
                    delete_reader(reader_id, readerData);
                    gtk_widget_destroy(warning_dialog);
                }
            } else {
                gtk_tree_store_remove(parameters->store, &iter);
                delete_reader(reader_id, readerData);
            }
        }
    }
    gtk_widget_destroy(dialog);
}


void on_delete_all_readers_button_clicked(GtkWidget *button, view_readers_passing_parameters *parameters) {
    GtkWidget *dialog;
    gint res;
    dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                    GTK_MESSAGE_QUESTION, GTK_BUTTONS_YES_NO, "您确定要删除全部读者信息吗？");
    gtk_window_set_title(GTK_WINDOW(dialog), "提示");
    gtk_dialog_set_default_response(GTK_DIALOG(dialog), GTK_RESPONSE_OK);
    res = gtk_dialog_run(GTK_DIALOG(dialog));
    if (res == GTK_RESPONSE_YES) {
        GtkTreeIter iter;
        delete_all_readers(readerData);
        if (gtk_tree_model_get_iter_first(parameters->model, &iter) == FALSE) return;
        gtk_tree_store_clear(parameters->store);
    }
    gtk_widget_destroy(dialog);
}