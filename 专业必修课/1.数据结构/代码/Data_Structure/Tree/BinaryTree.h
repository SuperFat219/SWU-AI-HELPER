#include <stdio.h>
#include <stdlib.h>
typedef struct BiTNode
{
    char data;
    struct BiTNode *lchild, *rchild;
} BiTNode;
void PreorderTraverse(BiTNode *tree);
void InorderTraverse(BiTNode *tree);
void PostorderTraverse(BiTNode *tree);
void CreateBiTree(BiTNode **tree, FILE *ip);
void CopyBiTree(BiTNode *tree, BiTNode **newTree);
int BiTreeDepth(BiTNode *tree);
int BiTreeNodes(BiTNode *tree);
int DegreeOneNodes(BiTNode *tree);
int LeafNodes(BiTNode *tree);
void LeafNodesPath(BiTNode *tree, char path[], int pathlen);
void ExchangeNode(BiTNode **tree);
int BitreeKnum(BiTNode *tree, int k);
BiTNode *FindLeftChild(BiTNode *tree, char data);
BiTNode *FindParentNode(BiTNode *tree, char data);