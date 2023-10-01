/*
**名称：顺序表查找
**Author：yzs
**Time:2021.12.2
*/
int Sequential_search(int *list, int n, int key)
{
    for (int i = 0; i < n; i++)
    {
        if (*(list + i) == key)
        {
            return i;
        }
    }
    return -1;
}