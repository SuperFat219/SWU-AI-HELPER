//排序算法实现(指针) 2021.5.15
#include <stdio.h>
int main()
{
    int array[10]={34,25,67,78,13,-35,89,344,24,-5};
    void Bubble_Sort(int *array,int length);
    void Select_Sort(int *array,int length);
    void Insertion_Sort(int *array,int length);
    void quicksort(int *array,int left,int right);
    void Merge(int *array,int left,int mid,int right);
    void mergeSort(int *array, int low, int high);

    printf("Bubble_sort:");
    Bubble_Sort(array,10);
    printf("Select_sort:");
    Select_Sort(array,10);
    printf("Insertion_sort:");
    Insertion_Sort(array,10);
    quicksort(array,0,9);
    printf("Quicksort:");
    for (int i=0;i<10;i++)  printf("%d ",array[i]);printf("\n");
    mergeSort(array,0,9);
    printf("mergesort:");
    for (int i=0;i<10;i++)  printf("%d ",array[i]);
    return 0;

}
/**
 * @brief 排序算法
 * @param array  //待排序数组
 * @param length //数组长度
 */
void Bubble_Sort(int *array,int length)
{
    int num;
    for (int i=0;i<length-1;i++)  //循环length-1次
    {
        for (int j=0;j<length-i-1;j++)  //第i（0，1，2...10）次循环时，已有i(0.1.2...10)个最大元素在数组最后,所以此时只需比较length-i-1次
        {
            if (*(array+j)>*(array+j+1))   //将最大元素移到最后
            {
                num=*(array+j);
                *(array+j)=*(array+j+1);  
                *(array+j+1)=num;
            }
        }
    }
    for (int i=0;i<length;i++)  printf("%d ",*(array+i));  //输出排序数组
    printf("\n");
}

void Select_Sort(int *array,int length)
{
    int MinIndex;
    for (int i=0;i<length;i++)
    {
        MinIndex=i;
        for (int j=i+1;j<length;j++)
        {
            if (*(array+MinIndex)>*(array+j)) MinIndex=j;
        }
        if (MinIndex!=i)
        {
            int num;
            num=*(array+i);
            *(array+i)=*(array+MinIndex);
            *(array+MinIndex)=num;
        }
    }
    int *p=array;
    for (int i=0;i<=9;i++,p++) printf("%d ",*p);
    printf("\n");
}

void Insertion_Sort(int *array,int length)
{
    int preindex,current;
    for(int i=1;i<length;i++)
    {
        preindex=i-1;
        current = *(array+i);
        while(preindex >= 0 && *(array+preindex) > current) 
        {
            *(array+preindex+1)=*(array+preindex);
            preindex-=1;
        }
        *(array+preindex+1) = current;
    }
    for (int i=0;i<=9;i++) printf("%d ",*(array+i));
    printf("\n");
}

void quicksort(int *array,int left,int right)
{
    if (left>right)
    {
        return;
    }
    int i=left,j=right;
    int temp;
    temp=*(array+i); //存放基准数，此处以第一个数为基准
    while (i!=j)
    {
        while (*(array+j)>temp && i<j) j--;
        while (*(array+i)<=temp && i<j) i++;
        if (i<j)
        {
            int t;
            t=*(array+i);
            *(array+i)=*(array+j);
            *(array+j)=t;
        }
    }
    *(array+left)=*(array+i);  //基准数归位
    *(array+i)=temp;
    quicksort(array,left,i-1);
    quicksort(array,i+1,right);
}

void Merge(int *array,int left,int mid,int right)
{
	int i,j,k,b[10];
	//将array中的元素复制到b中
	for(int k=left;k<=right;k++)
	{
		b[k] = *(array+k);
	}
	for(i=left,j=mid+1,k=i;i<=mid&&j<=right;k++)
	{
		if (*(b+i) < *(b+j))//比较b中左右两端中的元素，将较小值赋值到array中
        {
            *(b+(i++));//b[i++];
        }
        else
        {
            *(b+(j++));
        }
	}
	while(i <= mid)
	{
		*(array+(k++)) = *(b+(i++));
	}
	while(j <= right)
	{
		*(array+(k++)) = *(b+(j++)); 
	}
}

//归并排序
void mergeSort(int *array, int low, int high)
{
	if(low<high)
	{
		int mid = (low+high)/2; 
		mergeSort(array, low, mid); //对左侧子列进行排序
		mergeSort(array, mid+1, high);  //对右侧子序列进行排序
		Merge(array, low, mid, high);
	}

}