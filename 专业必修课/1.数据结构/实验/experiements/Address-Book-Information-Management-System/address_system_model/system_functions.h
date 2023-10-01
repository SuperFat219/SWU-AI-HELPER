#ifndef __SYSTEM_FUNTIONS_H__
#define __SYSTEM_FUNCTIONS_H__

#include "linklist_function.h"
#include "common_func.h"
//通讯录系统操作
int data_insert_from_file(Index_linklist *system,FILE *ip);//数据初始化
int insert_data(Index_linklist *system,Linklist *node);//从终端读取数据进行插入
int data_export_to_file(Index_linklist *system,char *filename);
void print_data(Index_linklist system[],FILE *op);
int search_data(Index_linklist const system[],char *num,char *name);
void delete_data(Index_linklist *system,char* num,char *name);
int judge_system_empty(Index_linklist *system);//判断系统数据是否为空
void system_empty(Index_linklist *system);//将系统数据清空
int count_linklist_elem_num(Index_linklist *system,char *name);//判断链表元素个数

#endif // __SYSTEM_FUNTIONS_H__