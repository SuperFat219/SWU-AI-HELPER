#include <stdio.h>
/*
int main()
{
    int i,j;
    for (i=1;i<5;i++)
    {
        if (j==6) printf("\n");
        for (j=1;j<6;j++)
        {
            if (i==3)// && j==1) 
            {
                continue;
            }
            else
            {
            printf("%d ",i*j);
            }
        }
    }
    return 0;
}
*/
/*
#include <math.h>
int main()
{
    int num,i;
    scanf("%d",&num);
    for (i=2;i<=sqrt(num);i++)
    {
        if (num%i==0) break;
    }
    if (i<=sqrt(num))
    {
        printf("no");
    }
    else {printf("yes");}
    return 0;
}*/
/*
int main()
{
    int array[10]={4,3,2,6,7,1,34,67,23,89};
    for (int i=0;i<9;i++)
    {
        for (int j=0;j<9-i;j++)
        {
            if (array[j]>array[j+1])
            {
                int temp=array[j+1];
                array[j+1]=array[j];
                array[j]=temp;
            }
        }
    }
    for (int i=0;i<10;i++)
    {
        printf("%d ",array[i]);
    }
    for (int i=0;i<10;i++)
    {
        int maxindex=i;
        for (int j=i+1;j<10;j++)
        {
            if (array[j]>array[maxindex])
            {
                maxindex=j;
            }
        }
        if (maxindex!=i)
        {
            int temp=array[maxindex];
            array[maxindex]=array[i];
            array[i]=temp;
        }
    }
    for (int i=0;i<10;i++)
    {
        printf("%d ",array[i]);
    }
    return 0;
}
*/
/*
int main()
{
    int a[2][3]={1,2,3,4,5,6};
    int (*p)[3];
    p=a;
    int n,m;scanf("%d %d",&n,&m);
    printf("%d",*(*(p+n)+m));
    return 0;
}*/
/*
int main()
{
    float score[2][3]={1,2,3,4,5,6};
    float *p;
    float sum=0;
    p=*score;
    float *p_end=p+5;
    for (;p<=p_end;p++)
    {
        sum+=(*p);
    }
    printf("%f\n",sum/6);
    float (*pt)[3];
    pt=score;
    for (int i=0;i<3;i++)
    {
        printf("%f ",*(*(pt+1)+i));
    }
    return 0;
}*/
/*
int main()
{
    int score[2][3]={1,2,3,4,5,5};
    int *search(int (*p)[3],int n);
    int k;
    scanf("%d",&k);
    int *p;
    p=search(score,k);
    for (int i=0;i<3;i++)
    {
        printf("%d ",*(p+i));
    }
    return 0;
}
int* search(int (*p)[3],int n)
{
    int *pt;
    pt=*(p+n);
    return pt;
}
*/
#include <string.h>
int main()
{
    // char *name[]={"sad","asfe","qwr"};
    // char **p;
    // p=name+1;
    // printf("%s",name[1]);
    char a[]="ab\n\012\\\"";
    printf("%s\n",a);
    printf("%d\n",strlen(a));
    printf("%x",177);
    return 0;
}