/*开始界面+系统界面*/
#include "window_main.h"

GtkWidget *create_main_window()//创建主窗口
{
    //参考https://tennysonsky.blog.csdn.net/article/details/42740865?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EOPENSEARCH%7Edefault-8.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EOPENSEARCH%7Edefault-8.no_search_link
    //void on_Enter_button_clicked(GtkWidget *widget,gpointer window);
    //void set_widget_font_size(GtkWidget *widget, int size, gboolean is_button);//改变控件字体大小

    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *main_window;
    GtkWidget *Enter_button;
    GtkWidget *Exit_button;
    GtkWidget *about_button;

    //gtk_init(&argc,&argv);
    builder=gtk_builder_new();
    gtk_builder_add_from_file(builder,"main_window.glade",NULL);//从glade获取控件

    main_window=GTK_WIDGET(gtk_builder_get_object(builder,"main_window"));//获取主窗体
    gtk_window_set_icon(GTK_WINDOW(main_window), create_pixbuf("addressbook.png"));//设置软件图标
    gtk_window_set_title(GTK_WINDOW(main_window),"通讯录信息管理系统");//设置主窗口标题
    Enter_button=GTK_WIDGET(gtk_builder_get_object(builder,"Enter_button"));//获取主窗口中的进入按钮
    Exit_button=GTK_WIDGET(gtk_builder_get_object(builder,"Exit_button"));//获取主窗口中的退出按钮
    about_button=GTK_WIDGET(gtk_builder_get_object(builder,"About_button"));//获取主窗口中的退出按钮
    gtk_widget_set_opacity(main_window,0.2);
    //set_widget_font_size(Enter_button,50,TRUE);//改变控件字体大小
    // PangoFontDescription *font_desc = pango_font_description_from_string("Sans");  
    // pango_font_description_set_size (font_desc, 15 * PANGO_SCALE);

    // gtk_widget_modify_font (GTK_WIDGET(Enter_button),  font_desc);
    // pango_font_description_free (font_desc);

    g_signal_connect(main_window,"destroy",G_CALLBACK(gtk_main_quit),NULL);//设置主窗口关闭信号链接

    //gtk_builder_connect_signals(builder,NULL);
    //g_object_unref(main_window);
    g_signal_connect(Enter_button,"clicked",G_CALLBACK(on_Enter_button_clicked),main_window);//设置“进入系统”按钮的回调函数
    g_signal_connect(Exit_button,"clicked",G_CALLBACK(gtk_main_quit),NULL);//设置“退出按钮”的连接动作
    g_signal_connect(about_button,"clicked",G_CALLBACK(on_About_button_clicked),main_window);
    //gtk_widget_show_all(main_window);
    //gtk_main();
    //return 0;
    return main_window;
}

void on_Enter_button_clicked(GtkWidget *button,gpointer window)
{
    //gtk_window_set_resizable(GTK_WINDOW(window),FALSE);  //如何隐藏前置窗口？？？
    //gtk_widget_hide_on_delete(window);
    gtk_widget_hide(GTK_WIDGET(window));
    gtk_widget_show_all(create_system_window());
}
void on_About_button_clicked(GtkWidget *button,gpointer window)
{
    //const char *authors[]={"Yan_Emperor","Yu"};
    //const gchar **p=authors;
    GdkPixbuf *pixbuf = gdk_pixbuf_new_from_file("addressbook.png",NULL);
    GtkWidget *dialog = gtk_about_dialog_new();
    //gtk_window_set_title(GTK_WINDOW(dialog),"软件说明");
    gtk_window_set_transient_for(GTK_WINDOW(dialog), window);//设置窗口主从关系
    //gtk_about_dialog_add_credit_section(GTK_ABOUT_DIALOG(dialog),"致谢",authors);版本须3.4以上方可支持
    gtk_about_dialog_set_program_name(GTK_ABOUT_DIALOG(dialog),"现代通讯录管理系统");
    gtk_about_dialog_set_version(GTK_ABOUT_DIALOG(dialog), "1.0"); 
    //gtk_about_dialog_set_authors(GTK_ABOUT_DIALOG(dialog),authors);版本3.4以上支持
    gtk_about_dialog_set_copyright(GTK_ABOUT_DIALOG(dialog),"(c) Copyright 2021_Yan_SWU");
    gtk_about_dialog_set_license(GTK_ABOUT_DIALOG(dialog),"licence");
    gtk_about_dialog_set_comments(GTK_ABOUT_DIALOG(dialog), "您管理通讯录信息的好帮手！");
    gtk_about_dialog_set_website(GTK_ABOUT_DIALOG(dialog), "http://www.baidu.com");
    //gtk_about_dialog_set_logo_icon_name(GTK_ABOUT_DIALOG(dialog), "addressbook.png");
    gtk_about_dialog_set_logo(GTK_ABOUT_DIALOG(dialog), pixbuf);
    //g_object_unref(pixbuf), pixbuf = NULL;
    gtk_dialog_run(GTK_DIALOG(dialog));
    gtk_widget_destroy(dialog);
}

GtkWidget *create_system_window()
{ //创建系统窗口
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *system_window;
    builder=gtk_builder_new();
    gtk_builder_add_from_file(builder,"system_window.glade",NULL);//从glade获取控件
    system_window=GTK_WIDGET(gtk_builder_get_object(builder,"System_window"));//获取系统窗体
    gtk_window_set_icon(GTK_WINDOW(system_window), create_pixbuf("addressbook.png"));
    GtkWidget *View_button=GTK_WIDGET(gtk_builder_get_object(builder,"View_all_contact"));//获取系统窗口中的“查看所有联系人”按钮
    GtkWidget *Search_button=GTK_WIDGET(gtk_builder_get_object(builder,"Search_contact"));//获取系统窗口中的“查找联系人”按钮
    GtkWidget *Create_button=GTK_WIDGET(gtk_builder_get_object(builder,"Create_new_contact"));//获取系统窗口中的“新建联系人”按钮
    GtkWidget *Import_button=GTK_WIDGET(gtk_builder_get_object(builder,"Import_contact"));//获取系统窗口中的“导入联系人”按钮
    GtkWidget *Export_button=GTK_WIDGET(gtk_builder_get_object(builder,"Export_contact"));//获取系统窗口中的“导出联系人”按钮
    GtkWidget *system_exit_button=GTK_WIDGET(gtk_builder_get_object(builder,"Exit_system"));//获取系统窗口中的“退出系统”按钮
    //set_widget_font_size(Enter_button,50,True);//改变控件字体大小

    gtk_window_set_title(GTK_WINDOW(system_window),"通讯录信息管理系统");//设置系统窗口标题
    g_signal_connect(system_window,"destroy",G_CALLBACK(gtk_main_quit),NULL);//设置系统窗口关闭信号链接
    g_signal_connect(system_exit_button,"clicked",G_CALLBACK(gtk_main_quit),NULL);//设置“退出按钮”的连接动作
    g_signal_connect(View_button,"clicked",G_CALLBACK(on_View_button_clicked),system_window);//设置“查看联系人”按钮的回调函数
    g_signal_connect(Import_button,"clicked",G_CALLBACK(on_import_button_clicked),system_window);//设置“导入数据”按钮的回调函数
    g_signal_connect(Export_button,"clicked",G_CALLBACK(on_export_button_clicked),system_window);//设置“导出数据”按钮的回调函数
    g_signal_connect(Create_button,"clicked",G_CALLBACK(on_create_button_clicked),system_window);//设置“新建联系人”按钮的回调函数
    g_signal_connect(Search_button,"clicked",G_CALLBACK(on_search_button_clicked),system_window);

    //gtk_builder_connect_signals(builder,NULL);
    //g_object_unref(main_window);
    //g_signal_connect(Exit_button,"clicked",G_CALLBACK(gtk_main_quit),NULL);//设置“退出按钮”的连接动作
    //gtk_widget_show_all(main_window);
    //gtk_main();

    //window_test = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    //gtk_widget_show(window_test);
    //gtk_main();
    //return 0;
    return system_window;
}
void on_View_button_clicked(GtkWidget *button,gpointer window)
{
    //参考https://www.cnblogs.com/xchsp/p/4322026.html
    //https://blog.csdn.net/qq_24729895/article/details/71171322
    //https://docs.gtk.org/gtk3/treeview-tutorial.html
    char *alpha[26]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int flag[26]={0};
    //“查看联系人”界面
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *return_button;
    GtkWidget *delete_button;
    GtkWidget *delete_all_button;
    GtkWidget *view_contacts_window;
    //GtkWidget *fixed_vector3;
    GtkWidget *scrolled_window;
    builder=gtk_builder_new();
    gtk_builder_add_from_file(builder,"information_window.glade",NULL);//从glade获取控件
    return_button=GTK_WIDGET(gtk_builder_get_object(builder,"return_button"));
    delete_button=GTK_WIDGET(gtk_builder_get_object(builder,"delete_button"));
    delete_all_button=GTK_WIDGET(gtk_builder_get_object(builder,"delete_all_button"));

    view_contacts_window=GTK_WIDGET(gtk_builder_get_object(builder,"contact_information_window"));//获取联系人列表界面
    gtk_window_set_icon(GTK_WINDOW(view_contacts_window), create_pixbuf("addressbook.png"));


    //fixed_vector3=GTK_WIDGET(gtk_builder_get_object(builder,"fixed_vector3"));
    scrolled_window=GTK_WIDGET(gtk_builder_get_object(builder,"scrolled_window"));
    GtkTreeStore *store=gtk_tree_store_new(N_COLUMNS,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING);//新建树视图
    //GtkTreeIter parents_iter[26];//=(GtkTreeIter*)malloc(sizeof(GtkTreeIter));//存储26个父节点
    GtkTreeIter child_iter;

    //添加一个联系人为空时的提示信息

    //获取联系人信息
    for (int i=0;i<26;i++)
    {
        Linklist *p=system_data[i].root->next;
        while (p!=NULL)
        {
            char first_alpha=p->name[0];
            if (first_alpha >='a' && first_alpha <='z')
            {
                first_alpha=first_alpha-32;
            }
            int index=first_alpha-'A';
            if (flag[index] == 0)
            {
                gtk_tree_store_append(store,&parents_iter[index],NULL);//在树中填入一个GtkTreeIter对象，代表一个新行，参数为NULL则是一个顶行，参数为父行则为子行
                gtk_tree_store_set(store,&parents_iter[index],COLUMN_INDEX,alpha[index],-1);
            }
            insert_tree_view(store,parents_iter[index],child_iter,p);
            *(flag+index)=1;
            p=p->next;
        }
    }
/*     for (int i=0;i<26;i++)
    {
        gtk_tree_store_append(store,&parents_iter[i],NULL);//在树中填入一个GtkTreeIter对象，代表一个新行，参数为NULL则是一个顶行，参数为父行则为子行
        gtk_tree_store_set(store,&parents_iter[i],COLUMN_INDEX,alpha[i],-1);
    } */
    //gtk_tree_store_append(store,&child_iter,&parents_iter[0]);
    //gtk_tree_store_set(store,&child_iter,COLUMN_NUM,"107865",COLUMN_NAME,"Amy",COLUMN_GENDER,"woman",COLUMN_PHONE_NUMBER,"15676879845",COLUMN_ADDRESS,"3 Sun Avenue",-1);
    /*gtk_tree_store_append(store,&child_iter,&parents_iter[0]);
    gtk_tree_store_set(store,&child_iter,COLUMN_NUM,"007965",COLUMN_NAME,"Amy",COLUMN_GENDER,"woman",COLUMN_PHONE_NUMBER,"15676879845",COLUMN_ADDRESS,"3 Sun Avenue",-1);
    gtk_tree_store_set(store,&parent_iter,COLUMN_INDEX,"B",-1);
    gtk_tree_store_append(store,&child_iter,&parents_iter[1]);
    gtk_tree_store_set(store,&child_iter,COLUMN_NUM,"007965",COLUMN_NAME,"Bmy",COLUMN_GENDER,"woman",COLUMN_PHONE_NUMBER,"15676879845",COLUMN_ADDRESS,"3 Sun Avenue",-1); */

    GtkWidget *tree_view=gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));//建立窗口视图
    GtkCellRenderer *renderer = gtk_cell_renderer_text_new();//使用文本渲染器
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_INDEX,"索引",renderer,"text",COLUMN_INDEX,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_NUM,"身份证号",renderer,"text",COLUMN_NUM,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_NAME,"姓名",renderer,"text",COLUMN_NAME,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_GENDER,"性别",renderer,"text",COLUMN_GENDER,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_PHONE_NUMBER,"手机号",renderer,"text",COLUMN_PHONE_NUMBER,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_ADDRESS,"地址",renderer,"text",COLUMN_ADDRESS,NULL);
    //gtk_container_add(GTK_CONTAINER(view_contacts_window),tree_view);
    gtk_tree_view_expand_all(GTK_TREE_VIEW(tree_view));
    //gtk_tree_view_set_column_drag_function(GTK_TREE_VIEW(tree_view),NULL,NULL,NULL);如何使文本居中？如何使列宽可拖拽
    gtk_tree_view_columns_autosize(GTK_TREE_VIEW(tree_view));
    //gtk_scrolled_window_add_with_viewport(GTK_SCROLLED_WINDOW(scrolled_window),tree_view);//废弃
    gtk_container_add(GTK_CONTAINER(scrolled_window),tree_view);
    //gtk_fixed_put(GTK_FIXED(fixed_vector3),tree_view,114,190);

    //获取当前选中节点并进行相关操作(此时以编号为唯一标识符)
    GtkTreeSelection *tree_selection = gtk_tree_view_get_selection(GTK_TREE_VIEW(tree_view));
    GtkTreeModel *model=gtk_tree_view_get_model(GTK_TREE_VIEW(tree_view));
    //printf("%d",gtk_tree_selection_count_selected_rows(tree_selection));

    /*信号处理部分*/
    g_signal_connect(return_button,"clicked",G_CALLBACK(on_return_button_clicked),view_contacts_window);//返回按钮设置回调函数
    //“删除”按钮的参数传递和信号连接
    delete_passing_parameters *delete_parameters;
    delete_parameters=(delete_passing_parameters*)malloc(sizeof(delete_passing_parameters));
    delete_parameters->model=model;
    delete_parameters->selection=tree_selection;
    delete_parameters->store=store;
    delete_parameters->window=view_contacts_window;
    //delete_parameters->parents_iter=parents_iter;
    g_signal_connect(delete_button,"clicked",G_CALLBACK(on_delete_button),delete_parameters);
    //“删除全部”按钮的参数传递和信号连接
    delete_all_passing_parameters *delete_all_parameters;
    delete_all_parameters=(delete_all_passing_parameters*)malloc(sizeof(delete_all_passing_parameters));
    delete_all_parameters->window=view_contacts_window;
    delete_all_parameters->store=store;
    delete_all_parameters->model=model;
    g_signal_connect(delete_all_button,"clicked",G_CALLBACK(on_delete_all_button),delete_all_parameters);

    gtk_widget_show_all(view_contacts_window);
}
void insert_tree_view(GtkTreeStore *store,GtkTreeIter parent_iter,GtkTreeIter child_iter,Linklist *node)//向树形容器中插入数据
{

    //GtkTreeIter child_iter;//设置迭代对象指向父行和子行
    //GtkTreeIter parent_iter=parents_iter[index];
    //gtk_tree_store_insert(store,&child_iter,&parent_iter,-1);
    gtk_tree_store_append(store,&child_iter,&parent_iter);
    gtk_tree_store_set(store,&child_iter,COLUMN_NUM,node->num,COLUMN_NAME,node->name,COLUMN_GENDER,node->gender,COLUMN_PHONE_NUMBER,node->phone_number,COLUMN_ADDRESS,node->address,-1);
    //gtk_tree_store_append: assertion 'VALID_ITER (parent, tree_store)' failed报错：父节点或子节点缺失
}
void on_return_button_clicked(GtkWidget *button,gpointer window)
{
    gtk_widget_hide(GTK_WIDGET(window));
}

//如果要支持多选怎么做？？GTK_SELECTION_MUTIPLE
void on_delete_button(GtkWidget *button,delete_passing_parameters *parameters)
{
    GtkWidget *dialog;
    gint res;
    dialog=gtk_message_dialog_new(GTK_WINDOW(parameters->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_QUESTION,GTK_BUTTONS_YES_NO,"您确定要删除该联系人信息吗？");
    gtk_window_set_title(GTK_WINDOW(dialog),"提示");
    gtk_dialog_set_default_response(GTK_DIALOG(dialog),GTK_RESPONSE_YES);
    res=gtk_dialog_run(GTK_DIALOG(dialog));
    if (res==GTK_RESPONSE_YES)
    {
        GtkTreeIter iter;
        //参阅https://docs.gtk.org/gtk3/treeview-tutorial.html
        if (gtk_tree_selection_get_selected(parameters->selection,&parameters->model, &iter))//让iter指向选择的节点
        {
            char *num,*name;
            gtk_tree_model_get(parameters->model,&iter,COLUMN_NUM,&num,COLUMN_NAME,&name,-1);
            if (count_linklist_elem_num(system_data,name)==1)
            {
                GtkTreeIter parent_iter;
                //GtkTreeIter *final;
                //int index=to_upper(name[0])-'A';
                //g_print("%d",index);
                //GtkTreeIter p_iter=parents_iter[index];
                gtk_tree_model_iter_parent(parameters->model,&parent_iter,&iter);
                //g_print("%d",gtk_tree_model_iter_has_child(parameters->model,&p_iter));
                //gtk_tree_store_remove(parameters->store, &iter);
                gtk_tree_store_remove(parameters->store,&parent_iter);//&parent_iter);//此处报错！double free or corruption (out)，内存重复释放，释放父节点时再次释放子节点
                //报段错误是由于跨线程传递指针时出错
                //gtk_tree_iter_free(&parent_iter);
            }
            else
            {
                gtk_tree_store_remove(parameters->store, &iter);
            }
            delete_data(system_data,num,name);
            //删除后如何立刻刷新联系人列表？
        }
        //gtk_tree_iter_free(&iter);

    }
    gtk_widget_destroy(dialog); 
}
void on_delete_all_button(GtkWidget *button,delete_all_passing_parameters *parameters)
{
    //加一个确认对话框
    GtkWidget *dialog;
    gint res;
    //GtkWidget *confirm_button;
    //GtkWidget *action_area;
    dialog=gtk_message_dialog_new(GTK_WINDOW(parameters->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_QUESTION,GTK_BUTTONS_YES_NO,"您确定要删除全部联系人信息吗？");
    gtk_window_set_title(GTK_WINDOW(dialog),"提示");
    gtk_dialog_set_default_response(GTK_DIALOG(dialog),GTK_RESPONSE_OK);
    //confirm_button=gtk_dialog_add_button(GTK_DIALOG(dialog),"确定(O)",1);
    //g_signal_connect(confirm_button,"clicked",G_CALLBACK(on_confirm_button_clicked),parameters->store);
    res=gtk_dialog_run(GTK_DIALOG(dialog));
    if (res==GTK_RESPONSE_YES)
    {
        GtkTreeIter iter;
        system_empty(system_data);
        if (gtk_tree_model_get_iter_first(parameters->model, &iter) == FALSE)  return;
        gtk_tree_store_clear(parameters->store);
    }
    gtk_widget_destroy(dialog); 
    //gtk_widget_hide(dialog);
}
/* void on_confirm_button_clicked(GtkWidget *button,GtkTreeStore *store)
{
    system_empty(system_data);
    gtk_tree_store_clear(store);
} */


void on_search_button_clicked(GtkWidget *button,gpointer window)
{
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *search_change_button;
    GtkWidget *search_delete_button;
    GtkWidget *search_return_button;
    GtkWidget *search_window;
    GtkWidget *search_scrolled_window;
    GtkWidget *search_name_entry;
    GtkWidget *search_phone_number_entry;

    builder=gtk_builder_new();
    gtk_builder_add_from_file(builder,"search_window.glade",NULL);//从glade获取控件
    search_window=GTK_WIDGET(gtk_builder_get_object(builder,"search_window"));
    search_change_button=GTK_WIDGET(gtk_builder_get_object(builder,"search_change_button"));
    search_delete_button=GTK_WIDGET(gtk_builder_get_object(builder,"search_delete_button"));
    search_return_button=GTK_WIDGET(gtk_builder_get_object(builder,"search_return_button"));
    search_scrolled_window=GTK_WIDGET(gtk_builder_get_object(builder,"search_scrolled_window"));
    search_name_entry=GTK_WIDGET(gtk_builder_get_object(builder,"search_name_entry"));
    search_phone_number_entry=GTK_WIDGET(gtk_builder_get_object(builder,"phone_number_entry"));
    gtk_window_set_icon(GTK_WINDOW(search_window), create_pixbuf("addressbook.png"));

    GtkTreeStore *store=gtk_tree_store_new(N_COLUMNS,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING,G_TYPE_STRING);//新建树视图
    GtkWidget *tree_view=gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));//建立窗口视图
    GtkCellRenderer *renderer = gtk_cell_renderer_text_new();//使用文本渲染器
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_INDEX,"索引",renderer,"text",COLUMN_INDEX,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_NUM,"身份证号",renderer,"text",COLUMN_NUM,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_NAME,"姓名",renderer,"text",COLUMN_NAME,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_GENDER,"性别",renderer,"text",COLUMN_GENDER,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_PHONE_NUMBER,"手机号",renderer,"text",COLUMN_PHONE_NUMBER,NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view),COLUMN_ADDRESS,"地址",renderer,"text",COLUMN_ADDRESS,NULL);
    //gtk_tree_view_expand_all(GTK_TREE_VIEW(tree_view));
    //gtk_tree_view_set_column_drag_function(GTK_TREE_VIEW(tree_view),NULL,NULL,NULL);如何使文本居中？如何使列宽可拖拽
    gtk_tree_view_columns_autosize(GTK_TREE_VIEW(tree_view));
    gtk_container_add(GTK_CONTAINER(search_scrolled_window),tree_view);

    GtkTreeModel *model=gtk_tree_view_get_model(GTK_TREE_VIEW(tree_view));
    GtkTreeSelection *selection=gtk_tree_view_get_selection(GTK_TREE_VIEW(tree_view));
    /*信号连接部分*/
    ///修改窗口的参数传递
    search_change_passing_parameters *search_parameters;
    search_parameters=(search_change_passing_parameters *)malloc(sizeof(search_change_passing_parameters));
    search_parameters->model=model;
    search_parameters->selection=selection;
    search_parameters->store=store;
    search_parameters->window=search_window;
    g_signal_connect(search_change_button,"clicked",G_CALLBACK(on_search_change_button_clicked),search_parameters);//"修改"按钮设置回调函数
    //删除按钮的参数传递
    delete_passing_parameters *delete_parameters;
    delete_parameters=(delete_passing_parameters*)malloc(sizeof(delete_passing_parameters));
    delete_parameters->model=model;
    delete_parameters->selection=selection;
    delete_parameters->store=store;
    delete_parameters->window=search_window;
    g_signal_connect(search_delete_button,"clicked",G_CALLBACK(on_search_delete_button_clicked),delete_parameters);
    g_signal_connect(search_return_button,"clicked",G_CALLBACK(on_return_button_clicked),search_window);
    search_passing_parameters *search_name_entry_parameters;
    search_name_entry_parameters=(search_passing_parameters*)malloc(sizeof(search_passing_parameters));
    search_name_entry_parameters->store=store;
    search_name_entry_parameters->entry=search_phone_number_entry;
    g_signal_connect(search_name_entry,"activate",G_CALLBACK(on_search_name_entry_activate),search_name_entry_parameters);
    search_passing_parameters *search_phone_entry_parameters;
    search_phone_entry_parameters=(search_passing_parameters*)malloc(sizeof(search_passing_parameters));
    search_phone_entry_parameters->store=store;
    search_phone_entry_parameters->entry=search_name_entry;
    g_signal_connect(search_phone_number_entry,"activate",G_CALLBACK(on_phone_number_entry_activate),search_phone_entry_parameters);

    gtk_widget_show_all(search_window);
}

void on_search_name_entry_activate(GtkWidget *entry,search_passing_parameters *name_entry_parameters)//查询文本输入框回车键按钮回调函数
{
    //gtk_list_store_clear(GTK_LIST_STORE(store));
    GtkTreeIter iter;
    gtk_tree_store_clear(name_entry_parameters->store);
    char name_target[30];
    char phone_target[30];
    strcpy(name_target,gtk_entry_get_text(GTK_ENTRY(entry)));
    strcpy(phone_target,gtk_entry_get_text(GTK_ENTRY(name_entry_parameters->entry)));
    for (int i=0;i<26;i++)
    {
        Linklist *p=system_data[i].root->next;
        while (p!=NULL)
        {
            if (strstr(p->name,name_target)!=NULL && strstr(p->phone_number,phone_target)!=NULL)
            //if (strcmp(p->name,target)==0)
            {
                gtk_tree_store_append(name_entry_parameters->store,&iter,NULL);
                gtk_tree_store_set(name_entry_parameters->store,&iter,COLUMN_NUM,p->num,COLUMN_NAME,\
                p->name,COLUMN_GENDER,p->gender,COLUMN_PHONE_NUMBER,p->phone_number,COLUMN_ADDRESS,p->address,-1);
            }
            p=p->next;
        }
        free(p);
    }
}
void on_phone_number_entry_activate(GtkWidget *entry,search_passing_parameters *phone_parameters)
{
    GtkTreeIter iter;
    gtk_tree_store_clear(phone_parameters->store);
    char phone_target[30];
    char name_target[30];
    strcpy(phone_target,gtk_entry_get_text(GTK_ENTRY(entry)));
    strcpy(name_target,gtk_entry_get_text(GTK_ENTRY(phone_parameters->entry)));
    for (int i=0;i<26;i++)
    {
        Linklist *p=system_data[i].root->next;
        while (p!=NULL)
        {
            if (strstr(p->phone_number,phone_target)!=NULL  && strstr(p->name,name_target)!=NULL)
            //if (strcmp(p->name,target)==0)
            {
                gtk_tree_store_append(phone_parameters->store,&iter,NULL);
                gtk_tree_store_set(phone_parameters->store,&iter,COLUMN_NUM,p->num,COLUMN_NAME,\
                p->name,COLUMN_GENDER,p->gender,COLUMN_PHONE_NUMBER,p->phone_number,COLUMN_ADDRESS,p->address,-1);
            }
            p=p->next;
        }
        free(p);
    }
}
void on_search_change_button_clicked(GtkWidget *button,search_change_passing_parameters *parameters)//“信息修改按钮”
{
    GtkWidget *change_contact_window;
    GtkWidget *change_button;
    GtkWidget *change_return_button;
    GtkWidget *num_entry;
    GtkWidget *name_entry;
    GtkWidget *gender_entry;
    GtkWidget *address_entry;
    GtkWidget *phone_number_entry;
    Linklist *change_node;
    change_node=(Linklist*)malloc(sizeof(Linklist));

    change_passing_parameters *change_parameters;
    change_parameters=(change_passing_parameters*)malloc(sizeof(change_passing_parameters));
    //获取界面控件
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    builder=gtk_builder_new();
    gtk_builder_add_from_file(builder,"change_window.glade",NULL);//从glade获取控件
    change_contact_window=GTK_WIDGET(gtk_builder_get_object(builder,"change_contact_window"));
    name_entry=GTK_WIDGET(gtk_builder_get_object(builder,"name_entry"));
    gender_entry=GTK_WIDGET(gtk_builder_get_object(builder,"gender_entry"));
    num_entry=GTK_WIDGET(gtk_builder_get_object(builder,"num_entry"));
    phone_number_entry=GTK_WIDGET(gtk_builder_get_object(builder,"phone_number_entry"));
    address_entry=GTK_WIDGET(gtk_builder_get_object(builder,"address_entry"));
    change_button=GTK_WIDGET(gtk_builder_get_object(builder,"change_button"));
    change_return_button=GTK_WIDGET(gtk_builder_get_object(builder,"change_return_button"));
    gtk_window_set_icon(GTK_WINDOW(change_contact_window), create_pixbuf("addressbook.png"));
    //gtk_widget_set_parent_window(change_contact_window,GDK_WINDOW(parameters->window));
    /*修改窗口背景*/
    // gtk_widget_set_app_paintable(change_contact_window,TRUE);
    // gtk_widget_realize(change_contact_window);
    // gtk_widget_queue_draw(change_contact_window);
    // GdkPixbuf *src_pixbuf=gdk_pixbuf_new_from_file("系统背景.jpg",NULL);
    // GdkPixbuf *dst_pixbuf=gdk_pixbuf_scale_simple(src_pixbuf,800,600,);
    //GtkWidget *image=gtk_image_new_from_file("系统背景.jpg");
    //gtk_container_add(GTK_CONTAINER(fixed_vector),image);
    
    //对用户信息进行拷贝并输入进文本输入框
    GtkTreeIter iter;
    if (gtk_tree_selection_get_selected(parameters->selection,&parameters->model,&iter))
    {
        char *num,*name,*gender,*phone_number,*address;
        //gtk_tree_model_get(parameters->model,&iter,COLUMN_NUM,&change_node->num,COLUMN_NAME,\
        &change_node->name,COLUMN_GENDER,&change_node->gender,COLUMN_PHONE_NUMBER,&change_node->phone_number,COLUMN_ADDRESS,&change_node->address,-1);
        // gtk_entry_set_text(GTK_ENTRY(change_parameters->name_entry),change_node->name);
        // gtk_entry_set_text(GTK_ENTRY(change_parameters->num_entry),change_node->num);
        // gtk_entry_set_text(GTK_ENTRY(change_parameters->gender_entry),change_node->gender);
        // gtk_entry_set_text(GTK_ENTRY(change_parameters->phone_number_entry),change_node->phone_number);
        // gtk_entry_set_text(GTK_ENTRY(change_parameters->address_entry),change_node->address);
        gtk_tree_model_get(parameters->model,&iter,COLUMN_NUM,&num,COLUMN_NAME,\
        &name,COLUMN_GENDER,&gender,COLUMN_PHONE_NUMBER,&phone_number,COLUMN_ADDRESS,&address,-1);
        gtk_entry_set_text(GTK_ENTRY(name_entry),name);
        gtk_entry_set_text(GTK_ENTRY(num_entry),num);
        gtk_entry_set_text(GTK_ENTRY(gender_entry),gender);
        gtk_entry_set_text(GTK_ENTRY(phone_number_entry),phone_number);
        gtk_entry_set_text(GTK_ENTRY(address_entry),address);
        strcpy(change_node->num,num);
        strcpy(change_node->name,name);
        strcpy(change_node->gender,gender);
        strcpy(change_node->phone_number,phone_number);
        strcpy(change_node->address,address);
        free(num);free(name);free(gender);free(phone_number);free(address);
    }
    //g_print("%d",gtk_editable_get_editable(GTK_EDITABLE(change_parameters->name_entry)));
    change_parameters->change_node=change_node;
    change_parameters->window=change_contact_window;
    change_parameters->num_entry=num_entry;
    change_parameters->name_entry=name_entry;
    change_parameters->gender_entry=gender_entry;
    change_parameters->phone_number_entry=phone_number_entry;
    change_parameters->address_entry=address_entry;
    change_parameters->store=parameters->store;
    g_signal_connect(change_button,"clicked",G_CALLBACK(on_change_button_clicked),change_parameters);
    g_signal_connect(change_return_button,"clicked",G_CALLBACK(on_return_button_clicked),change_contact_window);
    //先对性别修改进行判断
    gtk_widget_show_all(change_contact_window);
}

void on_change_button_clicked(GtkWidget *button,change_passing_parameters *parameters)
{
    GtkWidget *dialog;
    gint res;
    dialog=gtk_message_dialog_new(GTK_WINDOW(parameters->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_QUESTION,GTK_BUTTONS_YES_NO,"您确定要修改该联系人信息吗？");
    gtk_window_set_title(GTK_WINDOW(dialog),"提示");
    gtk_dialog_set_default_response(GTK_DIALOG(dialog),GTK_RESPONSE_YES);
    res=gtk_dialog_run(GTK_DIALOG(dialog));
    if (res==GTK_RESPONSE_YES)
    {
        strcpy(parameters->change_node->gender,gtk_entry_get_text(GTK_ENTRY(parameters->gender_entry)));
        if (strcmp(parameters->change_node->gender,"woman")==0)
        {
            strcpy(parameters->change_node->gender,"女");
        }
        if (strcmp(parameters->change_node->gender,"man")==0)
        {
            strcpy(parameters->change_node->gender,"男");
        }
        if (strcmp(parameters->change_node->gender,"男")==0 || strcmp(parameters->change_node->gender,"女")==0)
        {    
            delete_data(system_data,parameters->change_node->num,parameters->change_node->name);
            strcpy(parameters->change_node->num,gtk_entry_get_text(GTK_ENTRY(parameters->num_entry)));
            strcpy(parameters->change_node->name,gtk_entry_get_text(GTK_ENTRY(parameters->name_entry)));
            strcpy(parameters->change_node->phone_number,gtk_entry_get_text(GTK_ENTRY(parameters->phone_number_entry)));
            strcpy(parameters->change_node->address,gtk_entry_get_text(GTK_ENTRY(parameters->address_entry)));
            insert_data(system_data,parameters->change_node);
            GtkWidget *dialog;
            dialog=gtk_message_dialog_new(GTK_WINDOW(parameters->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"信息修改成功！");
            gtk_window_set_title(GTK_WINDOW(dialog),"提示");
            res=gtk_dialog_run(GTK_DIALOG(dialog));
            gtk_widget_destroy(dialog);
            gtk_tree_store_clear(parameters->store);
        }
        else
        {
            GtkWidget *dialog;
            dialog=gtk_message_dialog_new(GTK_WINDOW(parameters->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"信息修改失败，请检查数据格式是否正确！");
            gtk_window_set_title(GTK_WINDOW(dialog),"Error");
            res=gtk_dialog_run(GTK_DIALOG(dialog));
            gtk_widget_destroy(dialog);
        }
    }
    gtk_widget_destroy(dialog);
}

void on_search_delete_button_clicked(GtkWidget *button,delete_passing_parameters *parameters)
{
    GtkWidget *dialog;
    gint res;
    dialog=gtk_message_dialog_new(GTK_WINDOW(parameters->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_QUESTION,GTK_BUTTONS_YES_NO,"您确定要删除该联系人信息吗？");
    gtk_window_set_title(GTK_WINDOW(dialog),"提示");
    gtk_dialog_set_default_response(GTK_DIALOG(dialog),GTK_RESPONSE_YES);
    res=gtk_dialog_run(GTK_DIALOG(dialog));
    if (res==GTK_RESPONSE_YES)
    {
        GtkTreeIter iter;
        if (gtk_tree_selection_get_selected(parameters->selection,&parameters->model, &iter))//让iter指向选择的节点
        {
            char *num,*name;
            gtk_tree_model_get(parameters->model,&iter,COLUMN_NUM,&num,COLUMN_NAME,&name,-1);
            // if (count_linklist_elem_num(system_data,name)==1)
            // {
            //     GtkTreeIter parent_iter;
            //     gtk_tree_model_iter_parent(parameters->model,&parent_iter,&iter);
            //     gtk_tree_store_remove(parameters->store,&parent_iter);
            // }
            // else
            // {
            gtk_tree_store_remove(parameters->store, &iter);
            //}
            delete_data(system_data,num,name);
            free(num);free(name);
        }
    }
    gtk_widget_destroy(dialog); 
}



void on_create_button_clicked(GtkWidget *button,gpointer window)//新建联系人
{
    GtkBuilder *builder;//新建一个GtkBuilder对象用于读取GtkBuilder界面文件
    GtkWidget *create_add_button;
    GtkWidget *create_return_button;
    GtkWidget *create_contact_window;
    //GtkWidget *name_entry;
    //GtkWidget *gender_entry;
    //GtkWidget *phone_number_entry;
    //GtkWidget *num_entry;
    //GtkWidget *address_entry;
    GtkWidget *reference_text;
    add_new_node *new_contact_data=(add_new_node*)malloc(sizeof(add_new_node));//用来传递参数的结构体

    builder=gtk_builder_new();
    gtk_builder_add_from_file(builder,"create_window.glade",NULL);//从glade获取控件
    create_contact_window=GTK_WIDGET(gtk_builder_get_object(builder,"create_contact_window"));
    create_add_button=GTK_WIDGET(gtk_builder_get_object(builder,"create_add_button"));
    create_return_button=GTK_WIDGET(gtk_builder_get_object(builder,"create_return_button"));

    new_contact_data->name_entry=GTK_WIDGET(gtk_builder_get_object(builder,"name_entry"));
    new_contact_data->gender_entry=GTK_WIDGET(gtk_builder_get_object(builder,"gender_entry"));
    new_contact_data->num_entry=GTK_WIDGET(gtk_builder_get_object(builder,"num_entry"));
    new_contact_data->phone_number_entry=GTK_WIDGET(gtk_builder_get_object(builder,"phone_number_entry"));
    new_contact_data->address_entry=GTK_WIDGET(gtk_builder_get_object(builder,"address_entry"));
    new_contact_data->window=create_contact_window;

    gtk_window_set_icon(GTK_WINDOW(create_contact_window), create_pixbuf("addressbook.png"));
    g_signal_connect(create_return_button,"clicked",G_CALLBACK(on_return_button_clicked),create_contact_window);//返回按钮设置回调函数
    g_signal_connect(create_add_button,"clicked",G_CALLBACK(on_add_button_clicked),new_contact_data);
    gtk_widget_show_all(create_contact_window);
}
void on_add_button_clicked(GtkWidget *button,add_new_node *new_contact)//如果要实现多个参数传递，可使用结构体传递
{
    Linklist *new_node=(Linklist*)malloc(sizeof(Linklist));
    strcpy(new_node->name,gtk_entry_get_text(GTK_ENTRY(new_contact->name_entry)));
    strcpy(new_node->num,gtk_entry_get_text(GTK_ENTRY(new_contact->num_entry)));
    strcpy(new_node->gender,gtk_entry_get_text(GTK_ENTRY(new_contact->gender_entry)));
    strcpy(new_node->phone_number,gtk_entry_get_text(GTK_ENTRY(new_contact->phone_number_entry)));
    strcpy(new_node->address,gtk_entry_get_text(GTK_ENTRY(new_contact->address_entry)));
    //注意不可直接赋值，因为字符数组不可直接用字符指针赋值。

    if (insert_data(system_data,new_node)==1)
    {
        GtkWidget *dialog;
        dialog=gtk_message_dialog_new(GTK_WINDOW(new_contact->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"添加联系人信息成功！");
        gtk_window_set_title(GTK_WINDOW(dialog),"提示");
        gtk_dialog_run(GTK_DIALOG(dialog));
        gtk_entry_reset_im_context(GTK_ENTRY(new_contact->name_entry));

        //数据插入成功后文本框复位到初始状态
        gtk_entry_set_text(GTK_ENTRY(new_contact->name_entry),"");
        gtk_entry_set_text(GTK_ENTRY(new_contact->gender_entry),"");
        gtk_entry_set_text(GTK_ENTRY(new_contact->num_entry),"");
        gtk_entry_set_text(GTK_ENTRY(new_contact->address_entry),"");
        gtk_entry_set_text(GTK_ENTRY(new_contact->phone_number_entry),"");

        gtk_widget_destroy(dialog);
    }
    else
    {
        GtkWidget *dialog;
        dialog=gtk_message_dialog_new(GTK_WINDOW(new_contact->window),GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"数据插入失败，请检查数据格式是否正确！");
        gtk_window_set_title(GTK_WINDOW(dialog),"Error");
        gtk_dialog_run(GTK_DIALOG(dialog));
        gtk_widget_destroy(dialog);  
    }

/*     gtk_entry_set_placeholder_text(GTK_ENTRY(new_contact->num_entry),"（输入联系人身份证号）");
    gtk_entry_set_placeholder_text(GTK_ENTRY(new_contact->name_entry),"（输入联系人姓名）");
    gtk_entry_set_placeholder_text(GTK_ENTRY(new_contact->gender_entry),"（输入联系人性别，如男/女）");
    gtk_entry_set_placeholder_text(GTK_ENTRY(new_contact->phone_number_entry),"（输入联系人手机号）");
    gtk_entry_set_placeholder_text(GTK_ENTRY(new_contact->address_entry),"（输入联系人家庭住址）"); */

}

void on_import_button_clicked(GtkWidget *button,gpointer window)
{
    GtkWidget *dialog;
    GtkFileChooserAction action = GTK_FILE_CHOOSER_ACTION_OPEN;
    gint res;
    dialog=gtk_file_chooser_dialog_new("Open File",window,action,"_Cancel",GTK_RESPONSE_CANCEL,"_open",GTK_RESPONSE_ACCEPT,NULL);
    res=gtk_dialog_run(GTK_DIALOG(dialog));
    if (res == GTK_RESPONSE_ACCEPT)
    {
        char *filename;
        GtkFileChooser *chooser=GTK_FILE_CHOOSER(dialog);
        filename=gtk_file_chooser_get_filename(chooser);
        //open_file(filename);
        //printf("%s",filename);
        FILE *ph;
        if( (ph=fopen(filename,"r")) == NULL )
        {
            GtkWidget *dialog;
            dialog=gtk_message_dialog_new(window,GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"文件打开出错！");
            gtk_window_set_title(GTK_WINDOW(dialog),"错误");
            gtk_dialog_run(GTK_DIALOG(dialog));
            gtk_widget_destroy(dialog);           
        }
        int status=data_insert_from_file(system_data,ph);
        fclose(ph);
        if (!status)
        {
            GtkWidget *dialog;
            dialog=gtk_message_dialog_new(window,GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"未读取到有效数据，请检查数据格式是否正确！");
            gtk_window_set_title(GTK_WINDOW(dialog),"错误");
            gtk_dialog_run(GTK_DIALOG(dialog));
            gtk_widget_destroy(dialog);
        }
        else
        {
            GtkWidget *dialog;
            //char string1[30];
            //sprintf(string1,"成功读取%d条数据",status);
            dialog=gtk_message_dialog_new(window,GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"成功读取%d条数据！",status);
            gtk_window_set_title(GTK_WINDOW(dialog),"提示");
            gtk_dialog_run(GTK_DIALOG(dialog));
            gtk_widget_destroy(dialog);
        }

        g_free(filename);
    }
    gtk_widget_destroy(dialog);


}
void on_export_button_clicked(GtkWidget *button,gpointer window)
{
    GtkWidget *dialog;
    GtkWidget *export_file_button;
    //GtkWidget *action_area;
    dialog=gtk_message_dialog_new(window,GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_CANCEL,"请选择导出文件位置");
    gtk_window_set_title(GTK_WINDOW(dialog),"提示");
    //action_area=gtk_dialog_get_action_area(GTK_DIALOG(dialog));
    //export_file_button=gtk_button_new_with_label("选择文件夹路径../");
    //gtk_box_pack_start(GTK_BOX(action_area),export_file_button,TRUE,TRUE,0);
    //gtk_container_add(GTK_CONTAINER(dialog),export_file_button);
    //gtk_dialog_add_button(GTK_DIALOG(dialog),"选择文件夹路径../",)
    export_file_button=gtk_dialog_add_button(GTK_DIALOG(dialog),"选择../",0);
    g_signal_connect(export_file_button,"clicked",G_CALLBACK(on_export_file_button_clicked),dialog);
    gtk_dialog_run(GTK_DIALOG(dialog));
    gtk_widget_destroy(dialog);
}
void on_export_file_button_clicked(GtkWidget *button,gpointer window)
{
    GtkWidget *folder_select_dialog;
    GtkFileChooserAction action = GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER;
    gint res;
    folder_select_dialog=gtk_file_chooser_dialog_new("Open Folder",window,action,"_取消",GTK_RESPONSE_CANCEL,"_确定",GTK_RESPONSE_ACCEPT,NULL);
    res=gtk_dialog_run(GTK_DIALOG(folder_select_dialog));
    if (res == GTK_RESPONSE_ACCEPT)
    {
        char *foldername;
        foldername=(char *)malloc(sizeof(char)*100);
        GtkFileChooser *chooser=GTK_FILE_CHOOSER(folder_select_dialog);
        foldername=gtk_file_chooser_get_current_folder(chooser);
        //printf("%s",foldername);
        if (foldername==NULL)
        {
            GtkWidget *dialog;
            dialog=gtk_message_dialog_new(window,GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"请选择正确的路径！");
            gtk_window_set_title(GTK_WINDOW(dialog),"Error");
            gtk_dialog_run(GTK_DIALOG(dialog));
            gtk_widget_destroy(dialog);
        }
        else
        {
            char *output_file_location=strcat(foldername,"/output.txt");
            //报错：munmap_chunk(): invalid pointer  申请空间后，再次对申请的空间进行分配，最后释放的时候的多次释放。
            //C程序没有异常机制，一般用返回值标识错误，判断返回值进行错误的处理!
            int status=data_export_to_file(system_data,output_file_location);
            if (status==0)
            {
                GtkWidget *dialog;
                dialog=gtk_message_dialog_new(window,GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"导出失败！请选择正确的路径！");
                gtk_window_set_title(GTK_WINDOW(dialog),"错误");
                gtk_dialog_run(GTK_DIALOG(dialog));
                gtk_widget_destroy(dialog);
            }
            else
            {
                GtkWidget *dialog;
                dialog=gtk_message_dialog_new(window,GTK_DIALOG_DESTROY_WITH_PARENT,GTK_MESSAGE_INFO,GTK_BUTTONS_OK,"导出成功！");
                gtk_window_set_title(GTK_WINDOW(dialog),"提示");
                gtk_dialog_run(GTK_DIALOG(dialog));
                gtk_widget_destroy(dialog);
            }
        }
        g_free(foldername);

    }
    gtk_widget_destroy(folder_select_dialog);

}