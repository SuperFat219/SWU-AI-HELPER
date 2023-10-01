//
// Created by god on 2021/12/25.
//

#include "header/plane_window.h"

GtkWidget *create_plane_window() {
    GtkBuilder *builder;
    GtkWidget *plane_window;
    GtkWidget *scrolled_window;
    GtkWidget *time_button;
    GtkWidget *cost_button;
    GtkWidget *road_button;
    GtkWidget *return_button;
    GtkWidget *departure_entry;
    GtkWidget *destination_entry;

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "/home/god/Projects/Traffic-Information-System/interface/plane_window.glade",
                              NULL);//从glade获取控件

    plane_window = GTK_WIDGET(gtk_builder_get_object(builder, "plane_window"));//获取主窗体
//    gtk_window_set_icon(GTK_WINDOW(search_window),create_pixbuf("/home/god/Projects/Book-Management-System/pictures/book_system.png"));//设置软件图标
    scrolled_window = GTK_WIDGET(gtk_builder_get_object(builder, "scrolled_window"));
    gtk_window_set_title(GTK_WINDOW(plane_window), "交通咨询系统——飞机");//设置主窗口标题
    time_button = GTK_WIDGET(gtk_builder_get_object(builder, "time_button"));
    cost_button = GTK_WIDGET(gtk_builder_get_object(builder, "cost_button"));
    road_button = GTK_WIDGET(gtk_builder_get_object(builder, "road_button"));
    return_button = GTK_WIDGET(gtk_builder_get_object(builder, "return_button"));
    departure_entry = GTK_WIDGET(gtk_builder_get_object(builder, "departure_entry"));
    destination_entry = GTK_WIDGET(gtk_builder_get_object(builder, "destination_entry"));

    GtkTreeStore *store = gtk_tree_store_new(N_COLUMNS, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING,
                                             G_TYPE_STRING, G_TYPE_STRING, G_TYPE_STRING);//新建树视图

    GtkWidget *tree_view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
    GtkCellRenderer *renderer = gtk_cell_renderer_text_new();//使用文本渲染器
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_DEPARTURE, "出发地", renderer, "text",
                                                COLUMN_DEPARTURE, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_DESTINATION, "到达地", renderer, "text",
                                                COLUMN_DESTINATION, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_CLASSID, "航班号", renderer, "text",
                                                COLUMN_CLASSID, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_DEPARTTIME, "出发时间", renderer, "text",
                                                COLUMN_DEPARTTIME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_ARRIVE_TIME, "到达时间", renderer, "text",
                                                COLUMN_ARRIVE_TIME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_COST_TIME, "用时", renderer, "text",
                                                COLUMN_COST_TIME, NULL);
    gtk_tree_view_insert_column_with_attributes(GTK_TREE_VIEW(tree_view), COLUMN_PRICE, "票价", renderer, "text",
                                                COLUMN_PRICE, NULL);
    gtk_tree_view_expand_all(GTK_TREE_VIEW(tree_view));
    gtk_tree_view_columns_autosize(GTK_TREE_VIEW(tree_view));
    gtk_container_add(GTK_CONTAINER(scrolled_window), tree_view);

    flightData = (FlightInfo *) malloc(sizeof(FlightInfo));
    flightData->flightNum = 0;
    FILE *ip;
    ip = fopen("/home/god/Projects/Traffic-Information-System/data/time.txt", "r");
    import_flight_data_from_file(ip, flightData);
    fclose(ip);
    GtkTreeIter iter;
    int a = flightData->flightNum;
    for (int i = 0; i < flightData->flightNum; i++) {
        gtk_tree_store_append(store, &iter, NULL);
        gtk_tree_store_set(store, &iter, COLUMN_DEPARTURE, flightData->flights[i]->departure, COLUMN_DESTINATION, \
                flightData->flights[i]->destination, COLUMN_CLASSID, flightData->flights[i]->classId, COLUMN_DEPARTTIME,
                           flightData->flights[i]->departTime, COLUMN_ARRIVE_TIME, flightData->flights[i]->arriveTime,
                           COLUMN_COST_TIME, flightData->flights[i]->costTime,
                           COLUMN_PRICE, flightData->flights[i]->price, -1);
    }

    passing_parameters *parameters = (passing_parameters *) malloc(sizeof(passing_parameters));
    parameters->store = store;
    parameters->departure_entry = departure_entry;
    parameters->destination_entry = destination_entry;
    parameters->tree_view = tree_view;
    parameters->selection = gtk_tree_view_get_selection(GTK_TREE_VIEW(tree_view));
    parameters->model = gtk_tree_view_get_model(GTK_TREE_VIEW(tree_view));

    g_signal_connect(return_button, "clicked", G_CALLBACK(on_return_button_clicked), plane_window);
    g_signal_connect(destination_entry, "activate", G_CALLBACK(on_destination_entry_activate), parameters);
    g_signal_connect(time_button, "clicked", G_CALLBACK(on_time_button_clicked), parameters);
    g_signal_connect(cost_button, "clicked", G_CALLBACK(on_cost_button_clicked), parameters);
    g_signal_connect(road_button, "clicked", G_CALLBACK(on_road_button_clicked), parameters);

    gtk_widget_show_all(plane_window);
    return plane_window;
}


void on_destination_entry_activate(GtkWidget *destination_entry, passing_parameters *parameters) {

}

void on_time_button_clicked(GtkWidget *button, passing_parameters *parameters) {

}

void on_cost_button_clicked(GtkWidget *button, passing_parameters *parameters) {

}

void on_road_button_clicked(GtkWidget *button, passing_parameters *parameters) {

}

int partition_flight(Flight *array[], int low, int high) {
    Flight *flag = array[low];
    while (low < high) {
        while (low < high && strcmp(array[high]->departTime, flag->departTime) >= 0) {
            high--;
        }
        array[low] = array[high];
        while (low < high && strcmp(array[low]->departTime, flag->departTime) < 0) {
            low++;
        }
        array[high] = array[low];
    }
    array[low] = flag;
    return low;
}

void Qsort_flight(Flight *array[], int low, int high) {
    if (low < high) {
        int flag = partition_flight(array, low, high);
        Qsort_flight(array, low, flag - 1);
        Qsort_flight(array, flag + 1, high);
    }
}

void Quick_sort_flight(Flight *array[], int length) {
    Qsort_flight(array, 0, length - 1);
}

int import_flight_data_from_file(FILE *ip, FlightInfo *flightInfo) {
    const char whitespace[] = " "; //分隔符
    const char linebreak[] = "\n"; //换行符
    char buffer[200];              //缓冲区
    int count = 0;                 //记录读取了多少条数据
    while (fgets(buffer, N, ip))   //按行读取
    {
        Flight *flight = (Flight *) malloc(sizeof(Flight));
        unsigned int length = strlen(buffer);
        /*进行字符串分割*/
//        char cost_time[10];
//        char price[20];
        strcpy(flight->departure, strtok(buffer, whitespace));
        strcpy(flight->destination, strtok(NULL, whitespace));
        strcpy(flight->classId, strtok(NULL, whitespace));
        strcpy(flight->departTime, strtok(NULL, whitespace));
        strcpy(flight->arriveTime, strtok(NULL, whitespace));
        strcpy(flight->costTime, strtok(NULL, whitespace));
//        flight->costTime = transform_time_to_double(cost_time);
        strcpy(flight->price, strtok(NULL, linebreak));
//        char * p =flight->departTime;
//        char *l = flight->costTime;
//        flight->price = atof(price);
        insert_flight(flight, flightInfo);
        count++;
    }
    /*进行排序*/
//    Quick_sort_flight(flightInfo->flights, flightInfo->flightNum);
    return count;
}

void insert_flight(Flight *flight, FlightInfo *flightInfo) {
    flightInfo->flights[flightInfo->flightNum] = (Flight *) malloc(sizeof(Flight));
    flightInfo->flights[flightInfo->flightNum] = flight;
    flightInfo->flightNum++;
}

void ShortestPath(Graph G, int v0, double D[], int Path[]) {
    int ok[maxNum], i, j; // ok数组标记是否确定最短路径
    for (i = 0; i < G.vexnum; i++) {
        ok[i] = 0;
        Path[i] = -1;
        D[i] = N;
    }
    D[v0] = 0;
    for (i = 0; i < G.vexnum; i++) {
        int min_node = -1;
        for (j = 0; j < G.vexnum; j++) {
            if (ok[j] == 0 && (min_node == -1 || D[j] < D[min_node])) {
                min_node = j;
            }
        }
        if (min_node == -1) break;
        ok[min_node] = 1;

        Node *cur = G.vertices[min_node].firstarc;
        while (cur != NULL) {
            if (ok[cur->adjver] == 0 && D[cur->adjver] > D[min_node] + cur->distance) {
                D[cur->adjver] = D[min_node] + cur->distance;
                Path[cur->adjver] = min_node;
            }
            cur = cur->nextarc;
        }
    }
}

void ShortestMoney(Graph G, int v0, double D[], int Path[]) {
    int ok[maxNum], i, j;
    for (i = 0; i < G.vexnum; i++) {
        ok[i] = 0;
        Path[i] = -1;
        D[i] = N;
    }
    D[v0] = 0;
    for (i = 0; i < G.vexnum; i++) {
        int min_node = -1;
        for (j = 0; j < G.vexnum; j++) {
            if (ok[j] == 0 && (min_node == -1 || D[j] < D[min_node])) {
                min_node = j;
            }
        }
        if (min_node == -1) break;
        ok[min_node] = 1;

        Node *cur = G.vertices[min_node].firstarc;
        while (cur != NULL) {
            if (ok[cur->adjver] == 0 && D[cur->adjver] > D[min_node] + cur->price) {
                D[cur->adjver] = D[min_node] + cur->price;
                Path[cur->adjver] = min_node;
            }
            cur = cur->nextarc;
        }
    }
}