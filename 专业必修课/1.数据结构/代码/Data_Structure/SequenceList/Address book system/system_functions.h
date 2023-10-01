#include "linklist_function.h"
//通讯录系统操作
void data_init(Index_linklist *system,FILE *ip);//数据初始化
void insert_data(Index_linklist *system,Linklist *node);//从终端读取数据进行插入
void print_data(Index_linklist system[],FILE *op);
int search_data(Index_linklist const system[],char *name);
void delete_data(Index_linklist *system,char *name);



