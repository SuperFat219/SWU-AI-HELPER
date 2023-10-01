#include <stdio.h>
//9.2 
/* //比较两个学生成绩
int main()
{
    struct student
    {
        int num;
        char name[20];
        float grade;
    };
    struct student student1,student2;
    scanf("%d%s%f",&student1.num,student1.name,&student1.grade);
    scanf("%d%s%f",&student2.num,student2.name,&student2.grade);
    if (student1.grade>student2.grade)
    {
        printf("%d\n%s\n%.2f\n",student1.num,student1.name,student1.grade);
    }
    return 0;
}*/
//9.3 统计选举人票数
/*
#include <string.h>
int main()
{
    struct person
    {
        char name[20];
        int count;
    };
    struct person leader[3]={"li",0,"zhang",0,"kai",0};
    for (int i=0;i<20;i++)
    {
        char leader_name[20];
        scanf("%s",leader_name);
        for (int j=0;j<3;j++)
        {
            if (strcmp(leader_name,leader[j].name)==0)
            {
                leader[j].count+=1;
            }
        }
    }
    for (int i=0;i<3;i++)
    {
        printf("%s:%d\n",leader[i].name,leader[i].count);
    }
    return 0;
}
*/
//9.4 排序n个学生成绩
/*
#include <stdlib.h>
int main()
{
    struct stu
    {
        int num;
        char name[20];
        float grade;
    };
    // int arraylen; //如何实现一个动态结构化数组？
    // int *array;
    // scanf("%d",&arraylen);
    // array=(int*)malloc(arraylen*sizeof(int));
    // struct stu array[arraylen];
    struct stu student[5]={{10101,"yan",99.5},{10102,"li",97},{10103,"ko",87.58},{10104,"ll",78},{10105,"er",59}};
    for (int i=0;i<4;i++)
    {
        int maxindex=i;
        for (int j=i+1;j<5;j++)
        {
            if (student[i].grade<student[j].grade)
            {
                maxindex=j;
            }
        }
        if (maxindex!=i)
        {
            struct stu temp;
            temp=student[i];
            student[i]=student[maxindex];
            student[maxindex]=temp;
        }
    }
    for (int i=0;i<5;i++)
    {
        printf("%d %s %f\n",student[i].num,student[i].name,student[i].grade);
    }

    return 0;
}
*/
//定义动态结构体数组
/*
#include <stdlib.h>
int main()
{
    struct stu
    {
        int num;
        char name[20];
        float grade;
    };
    int arraylen; //如何实现一个动态结构化数组？
    struct stu *array;
    scanf("%d",&arraylen);
    array=(struct stu*)malloc(arraylen*sizeof(struct stu));
    for (int i=0;i<arraylen;i++)
    {
        scanf("%d%s%f",&array[i].num,array[i].name,&array[i].grade);
    }
    for (int j=0;j<arraylen;j++)
    {
        printf("%d%s%f\n",array[j].num,array[j].name,array[j].grade);
    }
    return 0;
}
*/
//9.7 n个学生信息，按平均成绩排序
#include <stdlib.h>
int main()
{
    struct student
    {
        int num;
        char name[20];
        float grade[3];
        float average;
    };
    int arraylen;
    scanf("%d",&arraylen);
    struct student *array;
    array=(struct student*)malloc(arraylen*sizeof(struct student));  //建立动态结构体变量
    for (int i=0;i<arraylen;i++)  //输入数据并计算平均成绩
    {
        scanf("%d%s%f%f%f",&array[i].num,array[i].name,\
        &array[i].grade[0],&array[i].grade[1],&array[i].grade[2]);
        array[i].average=(array[i].grade[0]+array[i].grade[1]+array[i].grade[2])/3;
    }
    for (int j=0;j<arraylen-1;j++) //冒泡排序
    {
        for(int k=0;k<arraylen-j-1;k++)
        {
            if (array[k].average<array[k+1].average)
            {
                struct student temp;
                temp=array[k];
                array[k]=array[k+1];
                array[k+1]=temp;
            }
        }
    }
    for (int i=0;i<arraylen;i++)  //输出成绩
    {
        printf("%d %-5s:%.2f %.2f %.2f,average grade is:%.2f\n",\
        array[i].num,array[i].name,array[i].grade[0],array[i].grade[1],\
        array[i].grade[2],array[i].average);
    }
    return 0;
}