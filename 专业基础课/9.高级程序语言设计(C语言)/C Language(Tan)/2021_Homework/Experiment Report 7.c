#include <stdio.h>
#include <stdlib.h>
struct student
{
    int num;
    char name[10];
    float grade[3];
    float average;
};
int main()
{
    //void file_open(FILE *fp,char file_name[]);
    void data_input(struct student data[]);
    void file_save(FILE *fp,char file_name[],struct student data[],int length);
    void data_sort(struct student data[],int length);
    void file_read(FILE *fp,char file_name[],int length);
    void data_insert(struct student data[]);
    FILE *fp1,*fp2;
    struct student data[10];

    //file_open(fp1,"stud.dat");
    //file_open(fp2,"stud_sort.dat");
    /*数据输入*/
    data_input(data);
    file_save(fp1,"stud.dat",data,5);
    file_read(fp1,"stud.dat",5);
    printf("\n");
    /*数据排序*/
    data_sort(data,5);
    file_save(fp2,"stud_sort.dat",data,5);
    file_read(fp2,"stud_sort.dat",5);
    printf("\n");
    /*数据插入*/
    data_insert(data);
    file_save(fp2,"stud_sort.dat",data,6);
    file_read(fp2,"stud_sort.dat",6);
    return 0;
}
// void file_open(FILE *fp,char file_name[])
// {
//     if ((fp=fopen(file_name,"wb+"))==NULL)
//     {
//         printf("can not open the file");
//         exit(0);
//     }
//     printf("%d\n",fp);
// }
void data_input(struct student data[])  //数据输入，存储至结构体数组data[]中
{
    for (int i=0;i<5;i++)
    {
        scanf("%d%s%f%f%f",&data[i].num,data[i].name,\
        &data[i].grade[0],&data[i].grade[1],&data[i].grade[2]);
        data[i].average=(data[i].grade[0]+data[i].grade[1]+data[i].grade[2])/3;
    }
}
void file_save(FILE *fp,char file_name[],struct student data[],int length)  //将数据存储至磁盘文件,length为数据条数
{
    if ((fp=fopen(file_name,"wb+"))==NULL)
    {
        printf("can not open the file");
        exit(0);
    }
    //printf("%d\n",fp);
    for (int i=0;i<length;i++)
    { 
        if (fp == NULL)  printf("open fail\n");
        else
        {
            if (fwrite(&data[i],sizeof(struct student),1,fp)!=1)  //写入数据
            {
                printf("write error!");
            }
        }
    }
    fclose(fp);
}
void data_sort(struct student data[],int length)  //数据排序（冒泡算法）
{
    for (int i=0;i<length-1;i++)
    {
        for (int j=0;j<length-1-i;j++)
        {
            if (data[j].average<data[j+1].average)
            {
                struct student temp;
                temp=data[j+1];
                data[j+1]=data[j];
                data[j]=temp;
            }
        }
    }
}
void file_read(FILE *fp,char file_name[],int length)  //读取数据
{
    if ((fp=fopen(file_name,"rb+"))==NULL)
    {
        printf("can not open the file");
        exit(0);
    }
    struct student stud[10];
    for (int i=0;i<length;i++)
    {
        fread(&stud[i],sizeof(struct student),1,fp);
        printf("%d %-5s %.2f\n",stud[i].num,stud[i].name,stud[i].average);
    }
    fclose(fp);
}
void data_insert(struct student data[])  //数据插入
{
    void data_sort(struct student data[],int length);
    scanf("%d%s%f%f%f",&data[5].num,data[5].name,\
    &data[5].grade[0],&data[5].grade[1],&data[5].grade[2]);
    data[5].average=(data[5].grade[0]+data[5].grade[1]+data[5].grade[2])/3;
    data_sort(data,6);
}