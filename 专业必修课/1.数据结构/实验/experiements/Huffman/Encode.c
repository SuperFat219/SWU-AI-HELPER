//
// Created by god on 2021/12/5.
//
#include "huffman.h"

//int main()
//{
//    char string[100];
//    Huffmancode *Code = huffman_encode_from_string(string);
//    FILE *op;
//    op = fopen("output2.txt", "w");
//    for (int i=0;i<Code->code_length;i++)
//    {
//        fprintf(op,"%d",Code->huffman_code[i]);
//    }
//    fclose(op);
//
//}
/**
 * 对字符串进行Huffman编码
 * @param string input string
 * @return Code
 */
Huffmancode *huffman_encode_from_string(char *string) {
    HTreeNode *HuffmanNode[500]; //存放哈夫曼树
    HCode *HuffmanCode[100]; //存放哈夫曼字符集编码
    Huffmancode *Code = (Huffmancode *) malloc(sizeof(Huffmancode));
    Code->code_length = 0;
//    static int huffman_code[3000];//存放字符串转换二进制编码
//    int locationFlag = 0;

    int uniqueChar_num = generate_huffmanNode(string, HuffmanNode);
    generate_huffmanTree(HuffmanNode, uniqueChar_num);
    generate_huffmanCode(HuffmanNode, HuffmanCode, uniqueChar_num);

    unsigned int length = strlen(string);
    for (int i = 0; i < length; i++) {
        for (int j = 0; j < uniqueChar_num; j++) {
            if (HuffmanNode[j]->value == string[i]) {
                for (int k = HuffmanCode[j]->start - 1; k >= 0; k--) //倒序输出
                {
                    Code->huffman_code[Code->code_length++] = HuffmanCode[j]->bit[k];
//                    huffman_code[locationFlag++] = HuffmanCode[j]->bit[k];
                }
                break;
            }
        }
    }
    Code->huffman_code[Code->code_length] = '\0';
    return Code;
}

/**
 * Read data from txt file
 * @param ip the pointer to file
 * @return HuffmanCode result
 */
Huffmancode *huffman_encode_from_file(FILE *ip) {
    HTreeNode *HuffmanNode[500]; //存放哈夫曼树
    char string[3000];
    char buffer[300];
    unsigned int line_len = 0;
    while (fgets(buffer, N, ip)) {
        strcpy(&string[line_len], buffer);
        line_len += strlen(buffer);
    }
    HCode *HuffmanCode[100]; //存放哈夫曼字符集编码
    Huffmancode *Code = (Huffmancode *) malloc(sizeof(Huffmancode));
    Code->code_length = 0;
    int uniqueChar_num = generate_huffmanNode(string, HuffmanNode);
    generate_huffmanTree(HuffmanNode, uniqueChar_num);
    generate_huffmanCode(HuffmanNode, HuffmanCode, uniqueChar_num);

    unsigned int length = strlen(string);
    for (int i = 0; i < length; i++) {
        for (int j = 0; j < uniqueChar_num; j++) {
            if (HuffmanNode[j]->value == string[i]) {
                for (int k = HuffmanCode[j]->start - 1; k >= 0; k--) //倒序输出
                {
                    Code->huffman_code[Code->code_length++] = HuffmanCode[j]->bit[k];
                    if ((Code->code_length + 1) % 96 == 0 && Code->code_length > 0) //0-94 95-189
                    {
                        Code->huffman_code[Code->code_length++] = '\n';
                    }
//                    huffman_code[locationFlag++] = HuffmanCode[j]->bit[k];
                }
            }
        }
    }
    Code->huffman_code[Code->code_length] = '\0';
    return Code;

}

/**
 * 读取输入字符串，生成HuffmanTree初始节点
 * @param string input string
 * @param HuffmanNode HuffmanTree
 * @return the number of unique character in the input string = the initial huffmanTree nodeNum
 */
int generate_huffmanNode(char *string, HTreeNode *HuffmanNode[]) {
    char unique_char[1000];
    unsigned int length = strlen(string);
    int uniqueChar_count = 0;
    for (int i = 0; i < length; i++) {
        int flag = 1; //标志该字符是否为首次出现
        for (int j = 0; j < uniqueChar_count; j++) {
            if (string[i] == unique_char[j]) //重复出现
            {
                HuffmanNode[j]->weight += 1;
                flag = 0;
                break;
            }
        }
        if (flag == 1) //第一次出现
        {
            HuffmanNode[uniqueChar_count] = (HTreeNode *) malloc(sizeof(HTreeNode));
            HuffmanNode[uniqueChar_count]->value = string[i];
            HuffmanNode[uniqueChar_count]->weight = 1;
            HuffmanNode[uniqueChar_count]->lchild = -1;
            HuffmanNode[uniqueChar_count]->rchild = -1;
            HuffmanNode[uniqueChar_count]->parent = -1;
            unique_char[uniqueChar_count] = string[i];
            uniqueChar_count += 1;
        }
    }
    unique_char[uniqueChar_count] = '\0';

    return uniqueChar_count;
}

/**
 * 生成HuffmanTree
 * @param HuffmanNode HuffmanTree
 * @param uniqueChar_num initial number of the HuffmanTree
 * @return the root node index of the created HuffmanTree
 */
int generate_huffmanTree(HTreeNode *HuffmanNode[], int uniqueChar_num) {
    int processing_node = uniqueChar_num; //处理过的节点个数
    for (; processing_node != 1;) //找出两个权值最小的节点
    {
        int x1 = N, x2 = N;
        int index1 = 0, index2 = 0;
        for (int i = 0; i < uniqueChar_num; i++) {
            if (HuffmanNode[i]->parent == -1 && HuffmanNode[i]->weight <= x1) {
                x2 = x1;
                index2 = index1;
                x1 = HuffmanNode[i]->weight;
                index1 = i;
            } else if (HuffmanNode[i]->parent == -1 && HuffmanNode[i]->weight <= x2) {
                x2 = HuffmanNode[i]->weight;
                index2 = i;
            }
        }
        if (x1 > x2) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }
        HuffmanNode[uniqueChar_num] = (HTreeNode *) malloc(sizeof(HTreeNode));//新建父节点
        HuffmanNode[index1]->parent = uniqueChar_num;
        HuffmanNode[index2]->parent = uniqueChar_num;
        HuffmanNode[uniqueChar_num]->weight = x1 + x2;
        HuffmanNode[uniqueChar_num]->lchild = index1;
        HuffmanNode[uniqueChar_num]->rchild = index2;
        HuffmanNode[uniqueChar_num]->parent = -1;
        uniqueChar_num += 1;
        processing_node = processing_node - 2 + 1;
    }
    int root_location = uniqueChar_num - 1;
    // printf("%d",root_location);
    return root_location;
}

/**
 * encoding the unique char
 * @param HuffmanNode generated HuffmanTree
 * @param HuffmanCode respective HuffmanCode
 * @param node_num initial nodeNum = UniqueChar_num
 */
void generate_huffmanCode(HTreeNode *HuffmanNode[], HCode *HuffmanCode[], int node_num) {
    for (int i = 0; i < node_num; i++) {
        HuffmanCode[i] = (HCode *) malloc(sizeof(HCode));
        HuffmanCode[i]->start = 0;
        int current_node = i;
        int p = HuffmanNode[i]->parent;
        while (p != -1) {
            if (HuffmanNode[p]->rchild == current_node) {
                HuffmanCode[i]->bit[HuffmanCode[i]->start] = '1';
                HuffmanCode[i]->start += 1;
            } else {
                HuffmanCode[i]->bit[HuffmanCode[i]->start] = '0';
                HuffmanCode[i]->start += 1;
            }
            current_node = p;
            p = HuffmanNode[p]->parent;
        }
    }
}
