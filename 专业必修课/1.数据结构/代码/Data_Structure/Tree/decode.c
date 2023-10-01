#include "huffman.h"

int main()
{
    char *string = "abcdefg";
    HTreeNode *HuffmanNode[500]; //存放哈夫曼树
    HCode *HuffmanCode[100];     //存放哈夫曼字符集编码
    // Huffmancode *Code = (Huffmancode *)malloc(sizeof(Huffmancode));
    // Code->code_length = 0;

    int uniqueChar_num = generate_huffmanNode(string, HuffmanNode);//树根的位置
    generate_huffmanTree(HuffmanNode, uniqueChar_num);
    generate_huffmanCode(HuffmanNode, HuffmanCode, uniqueChar_num);
    // char *decode_String = "1101111010011010011000010111011110000111111001001101111011100100111110";
    // long int length = strlen(decode_String);


}