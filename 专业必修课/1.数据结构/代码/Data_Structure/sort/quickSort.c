#include <stdio.h>
#include <stdlib.h>
typedef struct
{
    int array[30];
    int length;
} Sqlist;

int Partiton(Sqlist *L, int low, int high)
{
    L->array[0] = L->array[low];
    int pivotkey = L->array[low];
    while (low < high)
    {
        while (low < high && L->array[high] >= pivotkey)
            high--;
        L->array[low] = L->array[high];
        while (low < high && L->array[low] <= pivotkey)
            low++;
        L->array[high] = L->array[low];
    }
    L->array[low] = L->array[0];
    return low;
}
void QuickSort(Sqlist *L)
{
    void Qsort(Sqlist * L, int low, int high);
    Qsort(L, 1, L->length);
}
void Qsort(Sqlist *L, int low, int high)
{
    int pivot;
    if (low < high)
    {
        pivot = Partiton(L, low, high);
        for (int i = 1; i <= L->length; i++)
        {
            printf("%d ", L->array[i]);
        }
        printf("\n");
        Qsort(L, low, pivot - 1);
        Qsort(L, pivot + 1, high);
    }
}
int main()
{
    Sqlist *L = (Sqlist *)malloc(sizeof(Sqlist));
    L->array[1] = 21;
    L->array[2] = 25;
    L->array[3] = 49;
    L->array[4] = 25;
    L->array[5] = 16;
    L->array[6] = 8;
    L->length = 6;
    QuickSort(L);
}