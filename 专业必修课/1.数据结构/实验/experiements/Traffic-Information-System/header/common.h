//
// Created by god on 2021/12/25.
//

#ifndef TRAFFIC_INFORMATION_SYSTEM_COMMON_H
#define TRAFFIC_INFORMATION_SYSTEM_COMMON_H

#define N 10000
#define maxNum 100

#include <gtk/gtk.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

enum {
    COLUMN_DEPARTURE,
    COLUMN_DESTINATION,
    COLUMN_CLASSID,
    COLUMN_DEPARTTIME,
    COLUMN_ARRIVE_TIME,
    COLUMN_COST_TIME,
    COLUMN_PRICE,
    N_COLUMNS
};
typedef struct {
    double x;
    double y;
    char name[30];
} City;
typedef struct {
    char departure[30];//出发
    char destination[30];//到达
    char classId[30];//班次
    char departTime[30];//出发时间
    char arriveTime[30];//到达时间
    char costTime[10];//用时
    char price[10];//票价
} Flight;
typedef struct {
    char departure[30];//出发
    char destination[30];//到达
    char classId[30];//班次
    char departTime[30];//出发时间
    char arriveTime[30];//到达时间
    char costTime[10];//用时
    char price[10];//票价
} Train;
typedef struct {
    City *cities[30];
    int cityNum;
} CityInfo;
typedef struct {
    Flight *flights[30];
    int flightNum;
} FlightInfo;
typedef struct {
    Train *train[30];
    int trainNum;
} TrainInfo;

CityInfo *cityData;
FlightInfo *flightData;
TrainInfo *trainData;

typedef struct ArcNode {
    int adjver; //该边所指向的顶点位置
    struct ArcNode *nextarc; //指向下一条边的指针
    double distance;
    double time;
    double price;
} Node;//边结点
typedef struct {
    City *city;
    Node *firstarc;
} VNode, AdjList[maxNum]; //顶点信息

typedef struct {
    AdjList vertices;
    int vexnum; //图的当前顶点数
    int arcnum; //图的当前边数
} Graph;

GdkPixbuf *create_pixbuf(const gchar *filename);

void on_return_button_clicked(GtkWidget *button, gpointer window);

int transform_time_to_double(char *time);

#endif //TRAFFIC_INFORMATION_SYSTEM_COMMON_H
