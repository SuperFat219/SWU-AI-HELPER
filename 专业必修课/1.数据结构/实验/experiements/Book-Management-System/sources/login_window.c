//
// Created by god on 2021/12/16.
//
#include "headers/login_window.h"
#include "headers/user_window.h"
#include "headers/mainWindow.h"

GtkWidget *create_login_window() {
    /*加载系统内读者和管理员身份数据*/
    administratorData = (AdministratorInfo *) malloc(sizeof(AdministratorInfo));
    administratorData->administratorNum = 0;
    FILE *ip1;
    ip1 = fopen("/home/god/Projects/Book-Management-System/Data/administrator_data.txt", "r");
    import_administrator_data_from_file(ip1, administratorData);
    fclose(ip1);
    readerData = (ReaderInfo *) malloc(sizeof(ReaderInfo));
    readerData->readersNum = 0;
    FILE *ip2;
    ip2 = fopen("/home/god/Projects/Book-Management-System/Data/readers.txt", "r");
    import_reader_data_from_file(ip2, readerData);
    fclose(ip2);
    GtkBuilder *builder;
    GtkWidget *login_window;
    GtkWidget *username_entry;
    GtkWidget *password_entry;
    GtkWidget *login_button;
    GtkWidget *exit_button;
    GtkWidget *choose_switch;
    GtkWidget *register_button;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/Login_window.glade",
                              NULL);
    login_window = GTK_WIDGET(gtk_builder_get_object(builder, "login_window"));
    gtk_window_set_icon(GTK_WINDOW(login_window),
                        create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));
    gtk_window_set_title(GTK_WINDOW(login_window), "图书信息管理系统");
    login_button = GTK_WIDGET(gtk_builder_get_object(builder, "login_button"));
    exit_button = GTK_WIDGET(gtk_builder_get_object(builder, "exit_button"));
    register_button = GTK_WIDGET(gtk_builder_get_object(builder, "register_button"));
    username_entry = GTK_WIDGET(gtk_builder_get_object(builder, "username_entry"));
    password_entry = GTK_WIDGET(gtk_builder_get_object(builder, "password_entry"));
    choose_switch = GTK_WIDGET(gtk_builder_get_object(builder, "choose_switch"));

    login_passing_parameters *parameters = (malloc(sizeof(login_passing_parameters)));
    parameters->window = login_window;
    parameters->password_entry = password_entry;
    parameters->username_entry = username_entry;
    parameters->choose_switch = choose_switch;

    g_signal_connect(login_window, "destroy", G_CALLBACK(gtk_main_quit), NULL);
    g_signal_connect(exit_button, "clicked", G_CALLBACK(gtk_main_quit), NULL);
    g_signal_connect(login_button, "clicked", G_CALLBACK(on_login_button_clicked), parameters);
    g_signal_connect(password_entry, "activate", G_CALLBACK(on_password_entry_activate), parameters);
    g_signal_connect(register_button, "clicked", G_CALLBACK(on_register_button_clicked), parameters);

    return login_window;
}

void on_login_button_clicked(GtkWidget *button, login_passing_parameters *parameters) {
    Identity = gtk_switch_get_active(GTK_SWITCH(parameters->choose_switch));
    if (Identity == true) {
        username = gtk_entry_get_text(GTK_ENTRY(parameters->username_entry));
        int exist_flag = -1;
        for (int i = 0; i < readerData->readersNum; i++) {
            if (strcmp(readerData->readers[i]->username, username) == 0) {
                exist_flag = i;
            }
        }
        if (exist_flag == -1) {
            GtkWidget *error_dialog;
            error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "该用户名不存在！");
            gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
            gtk_dialog_run(GTK_DIALOG(error_dialog));
            gtk_widget_destroy(error_dialog);
        } else {
            const char *password = gtk_entry_get_text(GTK_ENTRY(parameters->password_entry));
            if (strcmp(password, readerData->readers[exist_flag]->password) == 0) {
                GtkWidget *prompt_dialog;
                prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                       GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "登录成功！");
                gtk_window_set_title(GTK_WINDOW(prompt_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(prompt_dialog));
                gtk_widget_destroy(prompt_dialog);
                gtk_widget_hide(parameters->window);
                gtk_widget_show_all(create_Enter_window());
            } else {
                GtkWidget *error_dialog;
                error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                      GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "密码输入错误！");
                gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(error_dialog));
                gtk_widget_destroy(error_dialog);
            }
        }
    } else {
        administrator_name = gtk_entry_get_text(GTK_ENTRY(parameters->username_entry));
        int exist_flag = -1;
        for (int i = 0; i < administratorData->administratorNum; i++) {
            if (strcmp(administratorData->administrator[i]->administrator_name, administrator_name) == 0) {
                exist_flag = i;
            }
        }
        if (exist_flag == -1) {
            GtkWidget *error_dialog;
            error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "管理者身份用户名验证失败！");
            gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
            gtk_dialog_run(GTK_DIALOG(error_dialog));
            gtk_widget_destroy(error_dialog);
        } else {
            const char *password = gtk_entry_get_text(GTK_ENTRY(parameters->password_entry));
            if (strcmp(password, administratorData->administrator[exist_flag]->password) == 0) {
                GtkWidget *prompt_dialog;
                prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                       GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "登录成功！");
                gtk_window_set_title(GTK_WINDOW(prompt_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(prompt_dialog));
                gtk_widget_destroy(prompt_dialog);
                gtk_widget_hide(parameters->window);
                gtk_widget_show_all(create_Enter_window());
            } else {
                GtkWidget *error_dialog;
                error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                      GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "密码输入错误！");
                gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(error_dialog));
                gtk_widget_destroy(error_dialog);
            }
        }
    }
}

void on_password_entry_activate(GtkWidget *password_entry, login_passing_parameters *parameters) {
    Identity = gtk_switch_get_active(GTK_SWITCH(parameters->choose_switch));
    if (Identity == true) {
        //用户
        username = gtk_entry_get_text(GTK_ENTRY(parameters->username_entry));
        int exist_flag = -1;
        int num = readerData->readersNum;
        for (int i = 0; i < readerData->readersNum; i++) {
            if (strcmp(readerData->readers[i]->username, username) == 0) {
                exist_flag = i;
            }
        }
        if (exist_flag == -1) {
            GtkWidget *error_dialog;
            error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "该用户名不存在！");
            gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
            gtk_dialog_run(GTK_DIALOG(error_dialog));
            gtk_widget_destroy(error_dialog);
        } else {
            const char *password = gtk_entry_get_text(GTK_ENTRY(parameters->password_entry));
            if (strcmp(password, readerData->readers[exist_flag]->password) == 0) {
                GtkWidget *prompt_dialog;
                prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                       GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "登录成功！");
                gtk_window_set_title(GTK_WINDOW(prompt_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(prompt_dialog));
                gtk_widget_destroy(prompt_dialog);
                gtk_widget_hide(parameters->window);
                gtk_widget_show_all(create_Enter_window());
            } else {
                GtkWidget *error_dialog;
                error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                      GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "密码输入错误！");
                gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(error_dialog));
                gtk_widget_destroy(error_dialog);
            }
        }
    } else {
        //管理员
        administrator_name = gtk_entry_get_text(GTK_ENTRY(parameters->username_entry));
        int exist_flag = -1;
        for (int i = 0; i < administratorData->administratorNum; i++) {
            if (strcmp(administratorData->administrator[i]->administrator_name, administrator_name) == 0) {
                exist_flag = i;
            }
        }
        if (exist_flag == -1) {
            GtkWidget *error_dialog;
            error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                  GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "管理者身份用户名验证失败！");
            gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
            gtk_dialog_run(GTK_DIALOG(error_dialog));
            gtk_widget_destroy(error_dialog);
        } else {
            const char *password = gtk_entry_get_text(GTK_ENTRY(parameters->password_entry));
            if (strcmp(password, administratorData->administrator[exist_flag]->password) == 0) {
                GtkWidget *prompt_dialog;
                prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                       GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "登录成功！");
                gtk_window_set_title(GTK_WINDOW(prompt_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(prompt_dialog));
                gtk_widget_destroy(prompt_dialog);
                gtk_widget_hide(parameters->window);
                gtk_widget_show_all(create_Enter_window());
            } else {
                GtkWidget *error_dialog;
                error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                      GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "密码输入错误！");
                gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
                gtk_dialog_run(GTK_DIALOG(error_dialog));
                gtk_widget_destroy(error_dialog);
            }
        }
    }
}

void on_register_button_clicked(GtkWidget *button, login_passing_parameters *parameters) {
    Identity = gtk_switch_get_active(GTK_SWITCH(parameters->choose_switch));
    if (Identity == true) {
        gtk_widget_hide(parameters->window);
        GtkBuilder *builder;
        GtkWidget *register_window;
        GtkWidget *username_entry;
        GtkWidget *password_entry;
        GtkWidget *confirm_password_entry;
        GtkWidget *tel_entry;
        GtkWidget *sex_switch;
        GtkWidget *name_entry;
        GtkWidget *email_entry;
        GtkWidget *confirm_button;
        GtkWidget *return_button;

        builder = gtk_builder_new();
        gtk_builder_add_from_file(builder, "/home/god/Projects/Book-Management-System/interface/register_window.glade",
                                  NULL);
        register_window = GTK_WIDGET(gtk_builder_get_object(builder, "register_window"));
        username_entry = GTK_WIDGET(gtk_builder_get_object(builder, "username_entry"));
        password_entry = GTK_WIDGET(gtk_builder_get_object(builder, "password_entry"));
        confirm_password_entry = GTK_WIDGET(gtk_builder_get_object(builder, "confirm_password_entry"));
        name_entry = GTK_WIDGET(gtk_builder_get_object(builder, "name_entry"));
        sex_switch = GTK_WIDGET(gtk_builder_get_object(builder, "sex_switch"));
        email_entry = GTK_WIDGET(gtk_builder_get_object(builder, "email_entry"));
        tel_entry = GTK_WIDGET(gtk_builder_get_object(builder, "tel_entry"));
        confirm_button = GTK_WIDGET(gtk_builder_get_object(builder, "confirm_button"));
        return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
        gtk_window_set_icon(GTK_WINDOW(register_window),
                            create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));
        gtk_window_set_title(GTK_WINDOW(register_window), "图书信息管理系统");

        register_passing_parameters *parameters1 = (register_passing_parameters *) malloc(
                sizeof(register_passing_parameters));
        parameters1->register_window = register_window;
        parameters1->tel_entry = tel_entry;
        parameters1->name_entry = name_entry;
        parameters1->sex_switch = sex_switch;
        parameters1->email_entry = email_entry;
        parameters1->username_entry = username_entry;
        parameters1->password_entry = password_entry;
        parameters1->confirm_password_entry = confirm_password_entry;

        g_signal_connect(register_window, "destroy", G_CALLBACK(on_register_return_button_clicked), register_window);
        g_signal_connect(confirm_button, "clicked", G_CALLBACK(on_register_confirm_button_clicked), parameters1);
        g_signal_connect(return_button, "clicked", G_CALLBACK(on_register_return_button_clicked), register_window);
        gtk_widget_show_all(register_window);

        //TODO:添加用户名(该用户名已被注册)、密码格式合法判断，注册时，正则表达式
    } else {
        GtkWidget *error_dialog;
        error_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                              GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "您没有管理员权限\n无法注册管理员身份！");
        gtk_window_set_title(GTK_WINDOW(error_dialog), "Error");
        gtk_dialog_run(GTK_DIALOG(error_dialog));
        gtk_widget_destroy(error_dialog);
    }
}

void on_register_confirm_button_clicked(GtkWidget *button, register_passing_parameters *parameters) {
//    wrong_information = (char *) malloc(sizeof(char) * 200);
    wrong_information[0] = '\0';
    int valid_flag = 1;
    char *invalid_char = "~!@#-_$%^&*()+=|{}':;',\\[\\\\]<>/?~！@#￥%……&*（）——+|{}【】《》 ‘；：”“’。，、？";
    Reader *reader = (Reader *) malloc(sizeof(Reader));
    const char *input_username = gtk_entry_get_text(GTK_ENTRY(parameters->username_entry));
    const char *input_password = gtk_entry_get_text(GTK_ENTRY(parameters->password_entry));
    const char *input_copassword = gtk_entry_get_text(GTK_ENTRY(parameters->confirm_password_entry));
    const char *input_name = gtk_entry_get_text(GTK_ENTRY(parameters->name_entry));
    gboolean input_sex = gtk_switch_get_active(GTK_SWITCH(parameters->sex_switch));
    const char *input_tel = gtk_entry_get_text(GTK_ENTRY(parameters->tel_entry));
    const char *input_email = gtk_entry_get_text(GTK_ENTRY(parameters->email_entry));
    for (int i = 0; i < readerData->readersNum; i++) {
        if (strcmp(readerData->readers[i]->username, input_username) == 0) {
            valid_flag = 0;
            strcat(wrong_information, "该用户名已存在！\n");
        }
    }
    if (strlen(input_password) < 6) {
        valid_flag = 0;
        strcat(wrong_information, "用户密码不得少于6位！\n");
    }
    for (int i = 0; i < gbkstrlen(input_username); i++) {
        //TODO:中文字符会被判定为非法字符，还需要优化
        if (strchr(invalid_char, input_username[i]) != NULL) {
            printf("%s", strchr(invalid_char, input_username[i]));
            valid_flag = 0;
            strcat(wrong_information, "用户名存在非法字符，请重新输入！\n");
            break;
        }
    }
    for (int i = 0; i < strlen(input_password); i++) {
        if (input_password[i] >= 'a' && input_password[i] <= 'z') {
            continue;
        } else if (input_password[i] >= 'A' && input_password[i] <= 'Z') {
            continue;
        } else if (input_password[i] >= '1' && input_password[i] <= '9') {
            continue;
        } else {
            valid_flag = 0;
            strcat(wrong_information, "密码不合法，请重新输入！\n");
            break;
        }
    }
    if (strcmp(input_password, input_copassword) != 0) {
        valid_flag = 0;
        strcat(wrong_information, "两次密码输入不一致!\n");
    }
    if (valid_flag == 1) {
        strcpy(reader->username, input_username);
        strcpy(reader->password, input_password);
        char readerid[4];
        sprintf(readerid, "%d", readerData->readersNum + 1);
        strcpy(reader->readerId, readerid);
        if (strcmp("", input_name) == 0 || input_name == NULL) {
            strcpy(reader->readerName, "无");
        } else {
            strcpy(reader->readerName, input_name);
        }
        if (input_sex) {
            strcpy(reader->readerSex, "女");
        } else {
            strcpy(reader->readerSex, "男");
        }
        if (strcmp("", input_tel) == 0 || input_tel == NULL) {
            strcpy(reader->readerTel, "无");
        } else {
            strcpy(reader->readerTel, input_tel);
        }
        if (strcmp("", input_email) == 0 || input_email == NULL) {
            strcpy(reader->email, "无");
        } else {
            strcpy(reader->email, input_email);
        }
        reader->borrowedNum = 0;
        reader->maxBorrowNum = 30;
        insert_reader(reader, readerData);
        FILE *op;
        op = fopen("/home/god/Projects/Book-Management-System/Data/readers.txt", "w");
        write_reader_data_to_file(op);
        fclose(op);
        GtkWidget *prompt_dialog;
        prompt_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->register_window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                               GTK_MESSAGE_INFO, GTK_BUTTONS_OK, "注册成功！！");
        gtk_window_set_title(GTK_WINDOW(prompt_dialog), "提示");
        gtk_dialog_run(GTK_DIALOG(prompt_dialog));
        gtk_widget_destroy(prompt_dialog);
        gtk_widget_hide(parameters->register_window);
        gtk_widget_show_all(create_login_window());
    } else {
        GtkWidget *warning_dialog;
        warning_dialog = gtk_message_dialog_new(GTK_WINDOW(parameters->register_window), GTK_DIALOG_DESTROY_WITH_PARENT,
                                                GTK_MESSAGE_WARNING, GTK_BUTTONS_OK, "%s", wrong_information);
        gtk_window_set_title(GTK_WINDOW(warning_dialog), "错误");
        gtk_dialog_run(GTK_DIALOG(warning_dialog));
        gtk_widget_destroy(warning_dialog);
    }
}

void on_register_return_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_hide(window);
    gtk_widget_show_all(create_login_window());
}
