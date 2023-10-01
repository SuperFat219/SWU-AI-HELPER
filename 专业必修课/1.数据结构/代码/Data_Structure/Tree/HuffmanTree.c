/*
**名称：实现霍夫曼编码、解码,同时可对任意文本文件进行编码解码输出
**Author：yzs
**Time:2021.10.13
*/
//该代码运行效率不高，当节点>15时运行速度很慢，此外对于解码问题需用到字符匹配算法，留待后续解决
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 10000
typedef struct Node
{
    char value;                 //节点值
    int weight;                 //权重
    int lchild, rchild, parent; //顺序存储会更方便
} HTreeNode;
typedef struct
{
    int bit[8]; //存放当前节点编码
    int start;
} HCode;

int main()
{
    int count_unique_char(char *string, char *unique_char, HTreeNode *HuffmanNode[]);
    HTreeNode *HuffmanNode[100]; //存放哈夫曼树
    /*     FILE *ip,*ip2,*op;
    ip=fopen("input.txt","r");
    ip2=fopen("input2.txt","r");
    op=fopen("output.txt","w"); */
    // int n;//节点个数
    // scanf("%d",&n);
    char string[1000];
    // scanf("%s", string);
    gets(string);
    printf("%s\n", string);
    char unique_char[300];
    int n = count_unique_char(string, unique_char, HuffmanNode);
    int node_num = n;
    int huffman_code[1000];
    int count = 0;

    int processing_node = n;
    for (; processing_node != 1;) //找出两个权值最小的节点
    {
        int x1 = N, x2 = N;
        int index1 = 0, index2 = 0;
        for (int i = 0; i < n; i++)
        {
            if (HuffmanNode[i]->parent == -1 && HuffmanNode[i]->weight <= x1)
            {
                x2 = x1;
                index2 = index1;
                x1 = HuffmanNode[i]->weight;
                index1 = i;
            }
            else if (HuffmanNode[i]->parent == -1 && HuffmanNode[i]->weight <= x2)
            {
                x2 = HuffmanNode[i]->weight;
                index2 = i;
            }
        }
        if (x1 > x2)
        {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }
        HuffmanNode[n] = (HTreeNode *)malloc(sizeof(HTreeNode));
        HuffmanNode[index1]->parent = n;
        HuffmanNode[index2]->parent = n;
        HuffmanNode[n]->weight = x1 + x2;
        HuffmanNode[n]->lchild = index1;
        HuffmanNode[n]->rchild = index2;
        HuffmanNode[n]->parent = -1;
        HuffmanNode[n]->value = 'm';
        n += 1;
        processing_node = processing_node - 2 + 1;
    }
    /*     for (int i=0;i<n;i++)
    {   
        fprintf(op,"%d %c %d %d %d %d\n",i,HuffmanNode[i]->value,HuffmanNode[i]->weight,HuffmanNode[i]->lchild,HuffmanNode[i]->rchild,HuffmanNode[i]->parent);
    } */
    /*编码*/
    HCode *HuffmanCode[100]; //存放哈夫曼编码
    for (int i = 0; i < node_num; i++)
    {
        HuffmanCode[i] = (HCode *)malloc(sizeof(HCode));
        HuffmanCode[i]->start = 0;
        int current_node = i;
        //HTreeNode *p=HuffmanNode[HuffmanNode[i]->parent];
        int p = HuffmanNode[i]->parent;
        while (p != -1)
        {
            if (HuffmanNode[p]->rchild == current_node)
            {
                HuffmanCode[i]->bit[HuffmanCode[i]->start] = 1;
                HuffmanCode[i]->start += 1;
            }
            else
            {
                HuffmanCode[i]->bit[HuffmanCode[i]->start] = 0;
                HuffmanCode[i]->start += 1;
            }
            current_node = p;
            p = HuffmanNode[p]->parent;
        }
    }
    int length = strlen(string);
    for (int i = 0; i < length; i++)
    {
        for (int j = 0; j < node_num; j++)
        {
            if (HuffmanNode[j]->value == string[i])
            {
                for (int k = HuffmanCode[j]->start - 1; k >= 0; k--) //倒序输出
                {
                    //printf("%d", HuffmanCode[j]->bit[k]);
                    huffman_code[count++] = HuffmanCode[j]->bit[k];
                }
            }
        }
    }
    FILE *op;
    op = fopen("output2.txt", "w");
    for (int i = 0; i < count; i++)
    {
        fprintf(op, "%d", huffman_code[i]);
    }
    // printf("\n");
    for (int i = 0; i < node_num; i++)
    {
        // fprintf(op,"%c ",HuffmanNode[i]->value);
        printf("%c ", HuffmanNode[i]->value);
        printf("%d ", HuffmanNode[i]->weight);
        for (int j = HuffmanCode[i]->start - 1; j >= 0; j--) //倒序输出
        {

            // fprintf(op,"%d",HuffmanCode[i]->bit[j]);
            printf("%d", HuffmanCode[i]->bit[j]);
        }
        printf("\n");
    }
    fclose(op);
    /*    fclose(ip);fclose(ip2);fclose(op); */
}

int count_unique_char(char *string, char *unique_char, HTreeNode *HuffmanNode[])
{
    //char string[30];
    //scanf("%s", string);
    int length = strlen(string);
    int unique_count = 0;
    for (int i = 0; i < length; i++)
    {
        int flag = 1; //标志该字符是否为首次出现
        for (int j = 0; j < unique_count; j++)
        {
            if (string[i] == unique_char[j]) //重复出现
            {
                HuffmanNode[j]->weight += 1;
                flag = 0;
                break;
            }
        }
        if (flag == 1) //第一次出现
        {
            HuffmanNode[unique_count] = (HTreeNode *)malloc(sizeof(HTreeNode));
            HuffmanNode[unique_count]->value = string[i];
            HuffmanNode[unique_count]->weight = 1;
            HuffmanNode[unique_count]->lchild = -1;
            HuffmanNode[unique_count]->rchild = -1;
            HuffmanNode[unique_count]->parent = -1;
            unique_char[unique_count] = string[i];
            unique_count += 1;
        }
    }
    unique_char[unique_count] = '\0';

    return unique_count;
}