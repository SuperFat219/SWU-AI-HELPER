
#include <stdio.h>
int main()
{
    int i=1,j=1,k=1;
    //第一行
    for (i=1;i<=8;i++) putchar(' ');
    for (j=1;j<=9;j++) putchar('*');
    for (k=1;k<=11;k++) putchar(' ');
    for (j=9;j>=1;j--) putchar('*');
    for (i=9;i>=1;i--) putchar(' ');
    printf("\n");
    //第二行
    for (i=1;i<=4;i++) putchar(' ');
    for (j=1;j<=17;j++) putchar('*');
    for (k=1;k<=3;k++) putchar(' ');
    for (j=17;j>=1;j--) putchar('*');
    for (i=4;i>=1;i--) putchar(' ');
    printf("\n");
    //第三行
    for (i=1;i<=3;i++) putchar(' ');
    for (j=1;j<=39;j++) putchar('*');
    for (i=3;i>=1;i--) putchar(' ');
    printf("\n");
    //第四行
    for (i=1;i<=1;i++) putchar(' ');
    for (j=1;j<=43;j++) putchar('*');
    for (i=1;i>=1;i--) putchar(' ');
    printf("\n");
    //5-11行
    for (i=1;i<=6;i++)
    {
        if ((i==3)||(i==4))
        {
            for (j=1;j<=18;j++) putchar('*');
            printf("Lion Messi");
            for (j=18;j>=1;j--) putchar('*');
            printf("\n");
        }
        else 
        {
            for (j=1;j<=45;j++) putchar('*');
            printf("\n");
        }
    }
    for (i=1;i<=6;i++)
    {
        for (k=1;k<=i;k++) putchar(' ');
        for (j=1;j<=(45-i*2);j++) putchar('*');
        for (k=i;k>=1;k--) putchar(' ');
        printf("\n");
    }
    for (i=8;i<=12;i+=2)
    {
        for (k=1;k<=i;k++) putchar(' ');
        for (j=1;j<=(45-i*2);j++) putchar('*');
        for (k=i;k>=1;k--) putchar(' ');
        printf("\n");
    }
    for (i=15;i<=21;i+=3)
    {
        for (k=1;k<=i;k++) putchar(' ');
        for (j=1;j<=(45-i*2);j++) putchar('*');
        for (k=i;k>=1;k--) putchar(' ');
        printf("\n");
    }
    return 0;
}

//版本一：单个字符的心形图案
/*
#include <stdio.h>
int main(void)
{
    float a,x,y;
    for(y=1.5f; y>-1.5f; y-=0.1f)
    {
        for(x=-1.5f; x<1.5f; x+=0.05f)
        {
            a = x*x+y*y-1;
            //这里的@符号即为打印出的心形图案符号，可更改
            char ch = a*a*a-x*x*y*y*y<=0.0f?'*':' '; 
            putchar(ch);  
            //或者putchar(a*a*a-x*x*y*y*y<=0.0f?'*':' ');
        }
        printf("\n");
    }
    
    return 0;
}
*/
//空心版
/*
#include <stdio.h>
#include <math.h>
// The first heart shape in hollow
// (x^2+y^2-1)^3 - x^2*y^3 = 0
// y ~ (-1.1 , 1.3 )
// x ~ (-1.2 , 1.2 )
int main() {

    for (float y = 1.3; y >= -1.1; y -= 0.06)
    {
        for (float x = -1.2; x <= 1.2; x += 0.025)
        {
            if (pow((x * x + y * y - 1.0), 3) - x * x * y * y * y <= 0.0)
                printf(" ");
            else
                printf("*");
        }
        printf("\n");
    }

    return 0;
}

#include <stdio.h>
#include <math.h>
 
float f(float x, float y, float z) 
{
    float a = x*x + 9.0f/4.0f*y*y + z*z - 1;
    return a*a*a - x*x*z*z*z - 9.0f/80.0f*y*y*z*z*z;
}
 
float h(float x, float z) 
{
    for (float y = 1.0f; y >= 0.0f; y -= 0.001f)
    {
        if (f(x, y, z) <= 0.0f)
            return y;
    }
    return 0.0f;
}
 
int main(int argc, char *argv[]) 
{
    for (float z = 1.5f; z > -1.5f; z -= 0.05f) 
    {
        for (float x = -1.5f; x < 1.5f; x += 0.025f) 
        {
            float v = f(x, 0.0f, z);
            if (v <= 0.0f) 
            {
                float y0 = h(x, z);
                float ny = 0.01f;
                float nx = h(x + ny, z) - y0;
                float nz = h(x, z + ny) - y0;
                float nd = 1.0f / sqrtf(nx*nx + ny*ny + nz*nz);
                float d = (nx + ny - nz)*nd*0.5f + 0.5f;
                putchar(".:-=+*#%@"[(int)(d * 5.0f)]);
            }
            else
                putchar(' ');
        }
        putchar('\n');
    }
}
*/