//
// Created by god on 2021/12/4.
//

#include "main_window.h"

GtkWidget *create_Enter_window() //创建进入窗口
{
    GtkBuilder *builder; //新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *Enter_window;
    GtkWidget *Enter_button;
    GtkWidget *Exit_button;
    GtkWidget *about_button;

    // gtk_init(&argc,&argv);
    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Huffman/Enter_window.glade",
                              NULL);                                            //从glade获取控件
    Enter_window = GTK_WIDGET(gtk_builder_get_object(builder, "Enter_window")); //获取主窗体
    // gtk_window_set_icon(GTK_WINDOW(Enter_window),
    // create_pixbuf("addressbook.png"));//设置软件图标
    //    gtk_window_set_title(GTK_WINDOW(Enter_window), "Huffman"); //设置主窗口标题
    Enter_button = GTK_WIDGET(
            gtk_builder_get_object(builder, "Enter_button")); //获取主窗口中的进入按钮
    Exit_button = GTK_WIDGET(
            gtk_builder_get_object(builder, "Exit_button")); //获取主窗口中的退出按钮
    about_button = GTK_WIDGET(
            gtk_builder_get_object(builder, "About_button")); //获取主窗口中的退出按钮
    //    gtk_widget_set_opacity(GTK_WIDGET(Enter_window), 0.2);
    // set_widget_font_size(Enter_button,50,TRUE);//改变控件字体大小
    //  PangoFontDescription *font_desc =
    //  pango_font_description_from_string("Sans");
    //  pango_font_description_set_size (font_desc, 15 * PANGO_SCALE);

    // gtk_widget_modify_font (GTK_WIDGET(Enter_button),  font_desc);
    // pango_font_description_free (font_desc);

    g_signal_connect(Enter_window, "destroy", G_CALLBACK(gtk_main_quit),
                     NULL); //设置主窗口关闭信号链接
    g_signal_connect(Enter_button, "clicked", G_CALLBACK(on_Enter_button_clicked),
                     Enter_window); //设置“进入系统”按钮的回调函数
    g_signal_connect(Exit_button, "clicked", G_CALLBACK(gtk_main_quit),
                     NULL); //设置“退出按钮”的连接动作
    g_signal_connect(about_button, "clicked", G_CALLBACK(on_About_button_clicked),
                     Enter_window);
    return Enter_window;
}

void on_Enter_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_hide(GTK_WIDGET(window));
    gtk_widget_show_all(create_main_window());
}

void on_About_button_clicked(GtkWidget *button, gpointer window) {
    GdkPixbuf *pixbuf = gdk_pixbuf_new_from_file("/home/god/Projects/Huffman/编码器.png", NULL);
    GtkWidget *dialog = gtk_about_dialog_new();
    gtk_window_set_transient_for(GTK_WINDOW(dialog), window);//设置窗口主从关系
    gtk_about_dialog_set_program_name(GTK_ABOUT_DIALOG(dialog), "赫夫曼编译码器");
    gtk_about_dialog_set_copyright(GTK_ABOUT_DIALOG(dialog), "(c) Copyright 2021_Yan_SWU");
    gtk_about_dialog_set_license(GTK_ABOUT_DIALOG(dialog), "Apache License 2.0");
    gtk_about_dialog_set_comments(GTK_ABOUT_DIALOG(dialog), "高效数据压缩，助力数据管理！");
    gtk_about_dialog_set_website(GTK_ABOUT_DIALOG(dialog), "https://github.com/ZS-Yan/Huffman");
    gtk_about_dialog_set_logo(GTK_ABOUT_DIALOG(dialog), pixbuf);
    gtk_dialog_run(GTK_DIALOG(dialog));
    gtk_widget_destroy(dialog);
}

/**
 * 创建主系统窗口
 * @return
 */
GtkWidget *create_main_window() {
    GtkBuilder *builder; //新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Huffman/main_window.glade", NULL);   //从glade获取控件
    GtkWidget *main_window = GTK_WIDGET(gtk_builder_get_object(builder, "main_window"));        //获取系统窗体
    //      gtk_window_set_icon(GTK_WINDOW(main_window),create_pixbuf("addressbook.png"));
    GtkWidget *encode_button = GTK_WIDGET(gtk_builder_get_object(builder, "encode_button"));    //获取系统窗口中的“编码”按钮
    GtkWidget *decode_button = GTK_WIDGET(gtk_builder_get_object(builder, "decode_button"));    //获取系统窗口中的“解码”按钮
    GtkWidget *system_exit_button = GTK_WIDGET(gtk_builder_get_object(builder, "Exit_system")); //获取系统窗口中的“退出系统”按钮
    // set_widget_font_size(Enter_button,50,True);//改变控件字体大小

    gtk_window_set_title(GTK_WINDOW(main_window), "Huffman编译码器"); //设置系统窗口标题
    g_signal_connect(encode_button, "clicked", G_CALLBACK(on_encode_button_clicked), main_window);
    g_signal_connect(decode_button, "clicked", G_CALLBACK(on_decode_button_clicked), main_window);
    g_signal_connect(system_exit_button, "clicked", G_CALLBACK(gtk_main_quit), NULL); //设置“退出按钮”的连接动作
    g_signal_connect(main_window, "destroy", G_CALLBACK(gtk_main_quit), NULL);        //设置系统窗口关闭信号链接

    return main_window;
}

void on_encode_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_hide(GTK_WIDGET(window));
    gtk_widget_show_all(create_encode_window());
}

void on_decode_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_hide(GTK_WIDGET(window));
    gtk_widget_show_all(create_decode_window());
}

GtkWidget *create_encode_window() {
    GtkBuilder *builder;
    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Huffman/encode_window.glade", NULL); //从glade获取控件
    GtkWidget *encode_window = GTK_WIDGET(gtk_builder_get_object(builder, "encode_window"));    //获取系统窗体
    //      gtk_window_set_icon(GTK_WINDOW(main_window),create_pixbuf("addressbook.png"));
    GtkWidget *encode_entry = GTK_WIDGET(gtk_builder_get_object(builder, "encode_entry"));
    GtkWidget *result_entry = GTK_WIDGET(gtk_builder_get_object(builder, "result_entry"));
    //    GtkWidget *text_scrolled_window = GTK_WIDGET(gtk_builder_get_object(builder, "text_scrolled_window"));
    GtkWidget *text_view = GTK_WIDGET(gtk_builder_get_object(builder, "text_view"));
    gtk_text_view_set_editable(GTK_TEXT_VIEW(text_view), gtk_false());
    GtkWidget *import_button = GTK_WIDGET(gtk_builder_get_object(builder, "import_button"));
    encode_import_passing_parameters *parameters = (encode_import_passing_parameters *) malloc(
            sizeof(encode_import_passing_parameters));
    parameters->window = encode_window;
    parameters->text_view = text_view;
    GtkWidget *export_button = GTK_WIDGET(gtk_builder_get_object(builder, "export_button"));
    GtkWidget *return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    // set_widget_font_size(Enter_button,50,True);//改变控件字体大小

    gtk_window_set_title(GTK_WINDOW(encode_window), "Huffman编码器"); //设置系统窗口标题
    g_signal_connect(encode_entry, "activate", G_CALLBACK(on_encode_entry_activate), result_entry);
    g_signal_connect(import_button, "clicked", G_CALLBACK(on_encode_import_button_clicked), parameters);
    g_signal_connect(export_button, "clicked", G_CALLBACK(on_encode_export_button_clicked), parameters);
    g_signal_connect(encode_window, "destroy", G_CALLBACK(gtk_main_quit), NULL); //设置系统窗口关闭信号链接
    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), encode_window);
    return encode_window;
}

void on_encode_entry_activate(GtkWidget *entry, GtkWidget *result_entry) {
    char string[1000];
    strcpy(string, gtk_entry_get_text(GTK_ENTRY(entry)));
    Huffmancode *Code = huffman_encode_from_string(string);
    GtkEntryBuffer *buffer = gtk_entry_buffer_new(Code->huffman_code, sizeof(char) * (Code->code_length));
    gtk_entry_set_buffer(GTK_ENTRY(result_entry), buffer);
}

void on_encode_import_button_clicked(GtkWidget *button, encode_import_passing_parameters *parameters) {
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
        //open_file(filename);
        //printf("%s",filename);
        FILE *ph;
        if ((ph = fopen(filename, "r")) == NULL) {
            GtkWidget *warning_dialog;
            warning_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                    GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "文件打开出错！");
            gtk_window_set_title(GTK_WINDOW(warning_dialog), "错误");
            gtk_dialog_run(GTK_DIALOG(warning_dialog));
            gtk_widget_destroy(warning_dialog);
        } else {
            Huffmancode *Code = huffman_encode_from_file(ph);
            fclose(ph);
            GtkWidget *prompt_dialog;
            prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                   GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "成功读取数据！请查看编码结果");
            gtk_window_set_title(GTK_WINDOW(prompt_dialog), "提示");
            gtk_dialog_run(GTK_DIALOG(prompt_dialog));
            gtk_widget_destroy(prompt_dialog);

            g_free(filename);
            GtkTextBuffer *buffer = gtk_text_buffer_new(gtk_text_tag_table_new());
            gtk_text_buffer_set_text(buffer, Code->huffman_code, sizeof(char) * (Code->code_length));
            gtk_text_view_set_buffer(GTK_TEXT_VIEW(parameters->text_view), buffer);
        }
    }
    gtk_widget_destroy(dialog);
}

void on_encode_export_button_clicked(GtkWidget *button, encode_import_passing_parameters *parameters) {
    GtkWidget *dialog;
    GtkWidget *export_file_button;
    dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT, GTK_MESSAGE_INFO,
                                    GTK_BUTTONS_CANCEL, "请选择导出文件位置");
    gtk_window_set_title(GTK_WINDOW(dialog), "提示");
    export_file_button = gtk_dialog_add_button(GTK_DIALOG(dialog), "选择文件夹../", 0);
    //    parameters->window = dialog;
    passing_parameters2 *parameters2 = (passing_parameters2 *) malloc(sizeof(passing_parameters2));
    parameters2->text_view = parameters->text_view;
    parameters2->dialog = dialog;
    g_signal_connect(export_file_button, "clicked", G_CALLBACK(on_export_file_button_clicked), parameters2);
    gtk_dialog_run(GTK_DIALOG(dialog));
    gtk_widget_destroy(dialog);
}

void on_export_file_button_clicked(GtkWidget *button, passing_parameters2 *parameters2) {
    GtkWidget *folder_select_dialog;
    GtkFileChooserAction action = GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER;
    gint res;
    folder_select_dialog = gtk_file_chooser_dialog_new("Open Folder", GTK_WINDOW(parameters2->dialog), action, "_取消",
                                                       GTK_RESPONSE_CANCEL, "_确定", GTK_RESPONSE_ACCEPT, NULL);
    res = gtk_dialog_run(GTK_DIALOG(folder_select_dialog));
    if (res == GTK_RESPONSE_ACCEPT) {
        char *foldername;
        //        foldername = (char *) malloc(sizeof(char) * 2000);
        //        foldername = (char*)malloc(sizeof(char)*100);
        GtkFileChooser *chooser = GTK_FILE_CHOOSER(folder_select_dialog);
        foldername = gtk_file_chooser_get_current_folder(chooser);
        if (foldername == NULL) {
            GtkWidget *error_dialog;
            error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters2->dialog), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "请选择正确的路径！");
            gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
            gtk_dialog_run(GTK_DIALOG(error_dialog));
            gtk_widget_destroy(error_dialog);
        } else {
            char *output_file_location = (char *) malloc(sizeof(char) * 200);
            strcpy(output_file_location, foldername);
            //https://www.linuxquestions.org/questions/programming-9/why-do-i-get-corrupted-memory-error-free-invalid-next-size-fast-875737/
            output_file_location = strcat(output_file_location,
                                          "/Huffman_Encode_Result.txt"); //strcat函数改变了foldername的内存占用
            FILE *op;
            op = fopen(output_file_location, "w");
            GtkTextBuffer *buffer = gtk_text_view_get_buffer(GTK_TEXT_VIEW(parameters2->text_view));
            GtkTextIter start, end;
            gtk_text_buffer_get_start_iter(buffer, &start);
            gtk_text_buffer_get_end_iter(buffer, &end);
            char *Code = gtk_text_buffer_get_slice(buffer, &start, &end, FALSE);
            //            printf("%s", Code);
            fprintf(op, "%s", Code);
            fclose(op);
            GtkWidget *promptdialog;
            promptdialog = gtk_message_dialog_new(GTK_WINDOW(parameters2->dialog), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "导出成功！");
            gtk_window_set_title(GTK_WINDOW(promptdialog), "提示");
            gtk_dialog_run(GTK_DIALOG(promptdialog));
            gtk_widget_destroy(promptdialog);
            //            g_free(output_file_location);
        }
        g_free(foldername);
    }
    gtk_widget_destroy(folder_select_dialog);
}

void on_return_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_hide(window);
    gtk_widget_show_all(create_main_window());
}

GtkWidget *create_decode_window() {
    GtkBuilder *builder;
    builder = gtk_builder_new();
    //此处用绝对路径的原因是：在IDE中，cmake生成的可执行文件与源码并不在同一个目录下，而终端执行时并没有这样的问题
    //https://blog.csdn.net/qq_41437512/article/details/119700812
    gtk_builder_add_from_file(builder, "/home/god/Projects/Huffman/decode_window.glade", NULL); //从glade获取控件
    GtkWidget *decode_window = GTK_WIDGET(gtk_builder_get_object(builder, "decode_window"));    //获取系统窗体
    //      gtk_window_set_icon(GTK_WINDOW(main_window),create_pixbuf("addressbook.png"));
    GtkWidget *decode_entry = GTK_WIDGET(gtk_builder_get_object(builder, "decode_entry"));
    //    GtkWidget *text_scrolled_window = GTK_WIDGET(gtk_builder_get_object(builder, "text_scrolled_window"));
    GtkWidget *text_view = GTK_WIDGET(gtk_builder_get_object(builder, "text_view"));
    GtkWidget *import_encode_button = GTK_WIDGET(gtk_builder_get_object(builder, "import_encode_button"));
    GtkWidget *result_entry = GTK_WIDGET(gtk_builder_get_object(builder, "result_entry"));
    GtkWidget *import_button = GTK_WIDGET(gtk_builder_get_object(builder, "import_button"));
    GtkWidget *export_button = GTK_WIDGET(gtk_builder_get_object(builder, "export_button"));
    GtkWidget *return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    // set_widget_font_size(Enter_button,50,True);//改变控件字体大小

    decode_passing_parameters *parameters = (decode_passing_parameters *) malloc(sizeof(decode_passing_parameters));
    parameters->window = decode_window;
    //    parameters->decode_entry = decode_entry;
    parameters->result_entry = result_entry;
    parameters->text_view = text_view;
    gtk_window_set_title(GTK_WINDOW(decode_window), "Huffman编码器"); //设置系统窗口标题
    g_signal_connect(import_encode_button, "clicked", G_CALLBACK(on_import_encode_button_clicked), parameters);
    g_signal_connect(decode_entry, "activate", G_CALLBACK(on_decode_entry_activate), parameters);
    g_signal_connect(import_button, "clicked", G_CALLBACK(on_decode_import_button_clicked), parameters);
    g_signal_connect(export_button, "clicked", G_CALLBACK(on_decode_export_button_clicked), parameters);
    g_signal_connect(decode_window, "destroy", G_CALLBACK(gtk_main_quit), NULL); //设置系统窗口关闭信号链接
    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), decode_window);

    return decode_window;
}

void on_import_encode_button_clicked(GtkWidget *button, decode_passing_parameters *parameters) {
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
        //open_file(filename);
        //printf("%s",filename);
        FILE *ph;
        if ((ph = fopen(filename, "r")) == NULL) {
            GtkWidget *warning_dialog;
            warning_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                    GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "文件打开出错！");
            gtk_window_set_title(GTK_WINDOW(warning_dialog), "错误");
            gtk_dialog_run(GTK_DIALOG(warning_dialog));
            gtk_widget_destroy(warning_dialog);
        } else {
            char string[3000];
            char buffer[300];
            unsigned int line_len = 0;
            while (fgets(buffer, N, ph)) {
                strcpy(&string[line_len], buffer);
                line_len += strlen(buffer);
            }
            int uniqueChar_num = generate_huffmanNode(string, parameters->HuffmanNode);
            parameters->root_location = generate_huffmanTree(parameters->HuffmanNode, uniqueChar_num);
            generate_huffmanCode(parameters->HuffmanNode, parameters->HuffmanCode, uniqueChar_num);
            fclose(ph);
            GtkWidget *prompt_dialog;
            prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                   GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "成功生成编码集！");
            gtk_window_set_title(GTK_WINDOW(prompt_dialog), "提示");
            gtk_dialog_run(GTK_DIALOG(prompt_dialog));
            gtk_widget_destroy(prompt_dialog);
            g_free(filename);
        }
    }
    gtk_widget_destroy(dialog);
}

void on_decode_entry_activate(GtkWidget *entry, decode_passing_parameters *parameters) {
    char code[5000];
    strcpy(code, gtk_entry_get_text(GTK_ENTRY(entry)));
    //    printf("%s", code);
    char decode_string[3000];
    huffman_decode_from_string(code, parameters->HuffmanNode, parameters->root_location, decode_string);
    //    printf("%s", decode_string);
    GtkEntryBuffer *buffer = gtk_entry_buffer_new(decode_string, sizeof(char) * strlen(decode_string));
    gtk_entry_set_buffer(GTK_ENTRY(parameters->result_entry), buffer);
}

void on_decode_import_button_clicked(GtkWidget *button, decode_passing_parameters *parameters) {
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
            GtkWidget *prompt_dialog;
            prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                   GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "成功读取数据！请查看译码结果");
            gtk_window_set_title(GTK_WINDOW(prompt_dialog), "提示");
            gtk_dialog_run(GTK_DIALOG(prompt_dialog));
            gtk_widget_destroy(prompt_dialog);
            char decode_string[3000];
            huffman_decode_from_file(ph, parameters->HuffmanNode, parameters->root_location, decode_string);
            g_free(filename);
            fclose(ph);
            GtkTextBuffer *buffer = gtk_text_buffer_new(gtk_text_tag_table_new());
            gtk_text_buffer_set_text(buffer, decode_string, sizeof(char) * (strlen(decode_string)));
            gtk_text_view_set_buffer(GTK_TEXT_VIEW(parameters->text_view), buffer);
        }
    }
    gtk_widget_destroy(dialog);
}

void on_decode_export_button_clicked(GtkWidget *button, decode_passing_parameters *parameters) {
    GtkWidget *dialog;
    GtkWidget *export_file_button;
    //GtkWidget *action_area;
    dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT, GTK_MESSAGE_INFO,
                                    GTK_BUTTONS_CANCEL, "请选择导出文件位置");
    gtk_window_set_title(GTK_WINDOW(dialog), "提示");
    export_file_button = gtk_dialog_add_button(GTK_DIALOG(dialog), "选择文件夹../", 0);
    //    parameters->window = dialog;
    passing_parameters2 *parameters2 = (passing_parameters2 *) malloc(sizeof(passing_parameters2));
    parameters2->text_view = parameters->text_view;
    parameters2->dialog = dialog;
    g_signal_connect(export_file_button, "clicked", G_CALLBACK(on_decode_export_file_button_clicked), parameters2);
    gtk_dialog_run(GTK_DIALOG(dialog));
    gtk_widget_destroy(dialog);
}

void on_decode_export_file_button_clicked(GtkWidget *button, passing_parameters2 *parameters) {
    GtkWidget *folder_select_dialog;
    GtkFileChooserAction action = GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER;
    gint res;
    folder_select_dialog = gtk_file_chooser_dialog_new("Open Folder", GTK_WINDOW(parameters->dialog), action, "_取消",
                                                       GTK_RESPONSE_CANCEL, "_确定", GTK_RESPONSE_ACCEPT, NULL);
    res = gtk_dialog_run(GTK_DIALOG(folder_select_dialog));
    if (res == GTK_RESPONSE_ACCEPT) {
        char *foldername = (char *) malloc(sizeof(char) * 100);
        GtkFileChooser *chooser = GTK_FILE_CHOOSER(folder_select_dialog);
        foldername = gtk_file_chooser_get_current_folder(chooser);
        //        printf("%s\n", foldername);
        if (foldername == NULL) {
            GtkWidget *error_dialog;
            error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->dialog), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "请选择正确的路径！");
            gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
            gtk_dialog_run(GTK_DIALOG(error_dialog));
            gtk_widget_destroy(error_dialog);
        } else {
            char *output_file_location = (char *) malloc(sizeof(char) * 200);
            strcpy(output_file_location, foldername);
            //https://www.linuxquestions.org/questions/programming-9/why-do-i-get-corrupted-memory-error-free-invalid-next-size-fast-875737/
            output_file_location = strcat(output_file_location,
                                          "/Huffman_Decode_Result.txt"); //strcat函数改变了foldername的内存占用
            //            char *output_file_location = strcat(foldername, "/Huffman_Decode_Result.txt");
            FILE *op;
            op = fopen(output_file_location, "w");
            GtkTextBuffer *buffer = gtk_text_view_get_buffer(GTK_TEXT_VIEW(parameters->text_view));
            GtkTextIter start, end;
            gtk_text_buffer_get_start_iter(buffer, &start);
            gtk_text_buffer_get_end_iter(buffer, &end);
            char *Code = gtk_text_buffer_get_slice(buffer, &start, &end, FALSE);
            fprintf(op, "%s", Code);
            fclose(op);
            GtkWidget *promptdialog;
            promptdialog = gtk_message_dialog_new(GTK_WINDOW(parameters->dialog), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "导出成功！");
            gtk_window_set_title(GTK_WINDOW(promptdialog), "提示");
            gtk_dialog_run(GTK_DIALOG(promptdialog));
            gtk_widget_destroy(promptdialog);
            g_free(output_file_location);
        }
        g_free(foldername);
    }
    gtk_widget_destroy(folder_select_dialog);
}

//起初运行效率很低是由于I/O耗时过多


