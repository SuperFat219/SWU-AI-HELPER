//
// Created by god on 2021/12/6.
//
#include "huffman.h"

/**
 *
 * @param code 编码
 * @param HuffmanNode 哈夫曼树
 * @param root_location 根节点位置
 * @param decode_string 译码
 */
void huffman_decode_from_string(char *code, HTreeNode *HuffmanNode[], int root_location, char *decode_string) {
    char decode_string_length = 0;
    char *flag;
    flag = code;
    int p = root_location;;
    while (*flag != '\0') {
        if (*flag == '0') {
            p = HuffmanNode[p]->lchild;
        } else {
            p = HuffmanNode[p]->rchild;
        }
        if (HuffmanNode[p]->lchild == -1 && HuffmanNode[p]->rchild == -1) {
            decode_string[decode_string_length++] = HuffmanNode[p]->value;
            p = root_location;
        }
        flag++;
    }
    decode_string[decode_string_length] = '\0';
}

/**
 * 对导入编码文件进行译码
 * @param ip 编码文件指针
 * @param HuffmanNode 哈夫曼树
 * @param root_location 根节点位置
 * @param decode_string 译码
 */
void huffman_decode_from_file(FILE *ip, HTreeNode *HuffmanNode[], int root_location, char *decode_string) {
    char string[5000];
    char buffer[200];
    unsigned int line_len = 0;
    while (fgets(buffer, N, ip))
    {
        // 排除换行符
        strcpy(&string[line_len], buffer);
        unsigned int buffer_len = strlen(buffer);
        line_len += buffer_len;
        if ('\n' == buffer[buffer_len - 1])
        {
            line_len -= 1;
        }
    }
//    printf("%d\n", strlen(string));
    int decode_string_length = 0;
    char *flag;
    flag = string;
    int p = root_location;
//    printf("%d\n", p);
    while (*flag != '\0')
    {
        // printf("%c", *flag);
        if (*flag == '0')
        {
            p = HuffmanNode[p]->lchild;
        }
        else
        {
            p = HuffmanNode[p]->rchild;
        }
        if (HuffmanNode[p]->lchild == -1 && HuffmanNode[p]->rchild == -1)
        {
            decode_string[decode_string_length++] = HuffmanNode[p]->value;
            p = root_location;
        }
        flag++;
    }
    decode_string[decode_string_length] = '\0';
}