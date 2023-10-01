//
// Created by god on 2021/12/14.
//
#include "headers/Add_window.h"

GtkWidget *create_add_window() {
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *add_window;
    GtkWidget *import_button;
    GtkWidget *add_button;
    GtkWidget *return_button;
    add_passing_parameters *parameters = (add_passing_parameters *) malloc(sizeof(add_passing_parameters));
    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/add_window.glade",
                              NULL);//从glade获取控件
    add_window = GTK_WIDGET(gtk_builder_get_object(builder, "add_window"));//获取主窗体
    gtk_window_set_icon(GTK_WINDOW(add_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(add_window), "图书信息管理系统");//设置主窗口标题
    import_button = GTK_WIDGET(gtk_builder_get_object(builder, "import_button"));
    add_button = GTK_WIDGET(gtk_builder_get_object(builder, "add_button"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));

    parameters->window = add_window;
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

    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), add_window);//设置“进入系统”按钮的回调函数
    g_signal_connect(add_button, "clicked", G_CALLBACK(on_add_button_clicked), parameters);
    g_signal_connect(import_button, "clicked", G_CALLBACK(on_import_button_clicked), add_window);
    return add_window;
}

void on_add_button_clicked(GtkWidget *button, add_passing_parameters *parameters) {
    Book *book = (Book *) malloc(sizeof(Book));
    strcpy(book->bookId, gtk_entry_get_text(GTK_ENTRY(parameters->id_entry)));
    strcpy(book->bookName, gtk_entry_get_text(GTK_ENTRY(parameters->name_entry)));
    strcpy(book->bookType, gtk_combo_box_text_get_active_text(GTK_COMBO_BOX_TEXT(parameters->type_comboBox)));
    strcpy(book->bookPages, gtk_entry_get_text(GTK_ENTRY(parameters->pages_entry)));
    strcpy(book->bookAuthor, gtk_entry_get_text(GTK_ENTRY(parameters->author_entry)));
    strcpy(book->bookPub, gtk_entry_get_text(GTK_ENTRY(parameters->pub_entry)));
    book->status = 0;
    strcpy(book->borrowTime, "无");//注意不可直接赋值，因为字符数组不可直接用字符指针赋值。
    int res = insert_book(book, bookData);
    if (res == 0) {
        gtk_combo_box_text_append_text(GTK_COMBO_BOX_TEXT(parameters->type_comboBox), book->bookType);
    }
    GtkWidget *dialog;
    dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT, GTK_MESSAGE_INFO,
                                    GTK_BUTTONS_OK, "添加图书信息成功！");
    gtk_window_set_title(GTK_WINDOW(dialog), "提示");
    gtk_dialog_run(GTK_DIALOG(dialog));
//    gtk_entry_reset_im_context(GTK_ENTRY(new_contact->name_entry));

    //数据插入成功后文本框复位到初始状态
    gtk_entry_set_text(GTK_ENTRY(parameters->id_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->name_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->type_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->pages_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->author_entry), "");
    gtk_entry_set_text(GTK_ENTRY(parameters->pub_entry), "");

    gtk_widget_destroy(dialog);
}

void on_import_button_clicked(GtkWidget *button, gpointer window) {
    GtkWidget *dialog;
    GtkFileChooserAction action = GTK_FILE_CHOOSER_ACTION_OPEN;
    gint res;
    dialog = gtk_file_chooser_dialog_new("Open File", GTK_WINDOW(window), action, "_Cancel",
                                         GTK_RESPONSE_CANCEL, "_open", GTK_RESPONSE_ACCEPT, NULL);
    res = gtk_dialog_run(GTK_DIALOG(dialog));
    if (res == GTK_RESPONSE_ACCEPT) {
        char *filename;
        GtkFileChooser *chooser = GTK_FILE_CHOOSER(dialog);
        filename = gtk_file_chooser_get_filename(chooser);
        FILE *ph;
        if ((ph = fopen(filename, "r")) == NULL) {
            GtkWidget *warning_dialog;
            warning_dialog = gtk_message_dialog_new(GTK_WINDOW(window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                    GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "文件打开出错！");
            gtk_window_set_title(GTK_WINDOW(warning_dialog), "错误");
            gtk_dialog_run(GTK_DIALOG(warning_dialog));
            gtk_widget_destroy(warning_dialog);
        } else {
            int count = import_book_data_from_file(ph, bookData);
//            Quick_sort(bookData->books,bookData->booksNum);
            fclose(ph);
            GtkWidget *prompt_dialog;
            prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                   GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "成功读取%d条数据！", count);
            gtk_window_set_title(GTK_WINDOW(prompt_dialog), "提示");
            gtk_dialog_run(GTK_DIALOG(prompt_dialog));
            gtk_widget_destroy(prompt_dialog);

            g_free(filename);
        }
    }
    gtk_widget_destroy(dialog);
}