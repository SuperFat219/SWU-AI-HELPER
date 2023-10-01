/**
 * @file BinaryTree.c
 * @author yzs
 * @brief 二叉树
 * @version 0.1
 * @date 2022-01-03
 *
 * @copyright Copyright (c) 2022
 *
 */

#include "BinaryTree.h"

/**
 * @brief 先序遍历
 * @param tree
 */
void PreorderTraverse(BiTNode *tree)
{
    if (!tree)
    {
        return;
    }
    printf("%c", tree->data);
    PreorderTraverse(tree->lchild);
    PreorderTraverse(tree->rchild);
}
/**
 * @brief 中序遍历
 * @param tree
 */
void InorderTraverse(BiTNode *tree)
{
    if (!tree)
    {
        return;
    }
    InorderTraverse(tree->lchild);
    printf("%c", tree->data);
    InorderTraverse(tree->rchild);
}
/**
 * @brief 后序遍历
 * @param tree
 */
void PostorderTraverse(BiTNode *tree)
{
    if (!tree)
    {
        return;
    }
    PostorderTraverse(tree->lchild);
    PostorderTraverse(tree->rchild);
    printf("%c", tree->data);
}
/**
 * @brief Create a Binary Tree object
 * @param tree
 */
void CreateBiTree(BiTNode **tree, FILE *ip)
{
    char ch;
    // fscanf(ip, "%c", ch);
    ch = fgetc(ip);
    if (ch == '#')
    {
        *tree = NULL;
    }
    else
    {
        *tree = (BiTNode *)malloc(sizeof(BiTNode));
        (*tree)->data = ch;
        CreateBiTree(&(*tree)->lchild, ip);
        CreateBiTree(&(*tree)->rchild, ip);
    }
}
/**
 * @brief copy a binary tree
 * @param tree
 * @param newTree
 */
void CopyBiTree(BiTNode *tree, BiTNode **newTree)
{
    if (!tree)
    {
        *newTree = NULL;
        return;
    }
    else
    {
        *newTree = (BiTNode *)malloc(sizeof(BiTNode));
        (*newTree)->data = tree->data;
        CopyBiTree(tree->lchild, &(*newTree)->lchild);
        CopyBiTree(tree->rchild, &(*newTree)->rchild);
    }
}
/**
 * @brief 二叉树深度
 * @param tree
 * @return int
 */
int BiTreeDepth(BiTNode *tree)
{
    int m, n;
    if (!tree)
    {
        return 0;
    }
    else
    {
        m = BiTreeDepth(tree->lchild);
        n = BiTreeDepth(tree->rchild);
        if (m > n)
        {
            return m + 1;
        }
        else
        {
            return n + 1;
        }
    }
}
/**
 * @brief 二叉树节点数目
 * @param tree
 * @return int
 */
int BiTreeNodes(BiTNode *tree)
{
    if (!tree)
    {
        return 0;
    }
    else
    {
        return BiTreeNodes(tree->lchild) + BiTreeNodes(tree->rchild) + 1;
    }
}
/**
 * @brief 二叉树叶子节点个数
 * @param tree
 * @return int
 */
int LeafNodes(BiTNode *tree)
{
    if (!tree)
    {
        return 0;
    }
    else if (!tree->lchild & !tree->rchild)
    {
        return 1;
    }
    else
    {
        return LeafNodes(tree->lchild) + LeafNodes(tree->lchild);
    }
}
/**
 * @brief 统计二叉树的度为1的结点个数
 * @param tree
 * @return int
 */
int DegreeOneNodes(BiTNode *tree)
{
    if (!tree)
    {
        return 0;
    }
    else if (tree->lchild && !tree->rchild)
    {
        return 1 + DegreeOneNodes(tree->lchild);
    }
    else if (!tree->lchild && tree->rchild)
    {
        return 1 + DegreeOneNodes(tree->rchild);
    }
    else
    {
        return DegreeOneNodes(tree->lchild) + DegreeOneNodes(tree->rchild);
    }
}
/**
 * @brief 输出二叉树中从每个叶子结点到根结点的路径
 * @param tree
 */
void LeafNodesPath(BiTNode *tree, char path[], int pathlen)
{
    if (tree)
    {
        path[pathlen] = tree->data;
        if (!tree->lchild && !tree->rchild)
        {
            for (int i = pathlen; i >= 0; i--)
            {
                printf("%c ", path[i]);
            }
        }
        else
        {
            LeafNodesPath(tree->lchild, path, pathlen + 1);
            LeafNodesPath(tree->rchild, path, pathlen + 1);
        }
    }
}
/**
 * @brief 交换二叉树每个结点的左孩子和右孩子
 * @param tree
 */
void ExchangeNode(BiTNode **tree)
{
    BiTNode *temp;
    if (*tree != NULL)
    {
        temp = (*tree)->lchild;
        (*tree)->lchild = (*tree)->rchild;
        (*tree)->rchild = temp;
        ExchangeNode(&(*tree)->lchild);
        ExchangeNode(&(*tree)->rchild);
    }
}
/**
 * @brief 求第k层的节点数
 * @param tree
 * @param k
 * @return int
 */
int BitreeKnum(BiTNode *tree, int k)
{
    if (!tree)
    {
        return 0;
    }
    if (k == 1)
    {
        return 1;
    }
    return BitreeKnum(tree->lchild, k - 1) + BitreeKnum(tree->rchild, k - 1);
}
/**
 * @brief 求节点的左孩子
 * @param tree
 * @param data
 * @return BiTNode*
 */
BiTNode *FindLeftChild(BiTNode *tree, char data)
{
    BiTNode *ret;
    if (!tree)
    {
        return NULL;
    }
    if (tree->data == data && tree->lchild)
    {
        return tree->lchild;
    }
    ret = FindLeftChild(tree->lchild, data);
    if (!ret)
    {
        ret = FindLeftChild(tree->rchild, data);
    }
    return ret;
}
/**
 * @brief 寻找双亲结点
 * @param tree
 * @param data
 * @return BiTNode*
 */
BiTNode *FindParentNode(BiTNode *tree, char data)
{
    BiTNode *res;
    if (tree->data == data)
    {
        return NULL;
    }
    if (tree->lchild->data == data || tree->rchild->data == data)
    {
        return tree;
    }
    res = FindParentNode(tree->lchild, data);
    if (!res)
    {
        res = FindParentNode(tree->rchild, data);
    }
    return res;
}
int main()
{
    FILE *ip, *op;
    ip = fopen("input2.txt", "r");
    op = fopen("output.txt", "w");
    BiTNode *tree;
    BiTNode **p;
    p = &tree;
    CreateBiTree(p, ip);
    // PreorderTraverse(tree);
    // printf("\n");
    // ExchangeNode(&tree);
    // PreorderTraverse(tree);
    // printf("%d", BitreeKnum(tree, 3));
    printf("%c\n", FindLeftChild(tree, 'D')->data);
    printf("%c", FindParentNode(tree, 'I')->data);
    // InorderTraverse(tree);
    // BiTNode *newTree;
    // BiTNode **p2;
    // p2 = &newTree;
    // CopyBiTree(tree, p2);
    // printf("%d", DegreeOneNodes(tree));
    // PreorderTraverse(newTree);
    // char path[256];
    // int pathlen = 0;
    // LeafNodesPath(tree, path, pathlen);
}
// ABDH##I##E#J##CF##G##
// ABCDEF#######