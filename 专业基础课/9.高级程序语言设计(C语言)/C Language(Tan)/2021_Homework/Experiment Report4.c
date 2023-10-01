//使用字符指针实现字符函数
#include <stdio.h>
int main()
{
    int strcmp_self(char *string1,char *string2);
    int strlen_self(char *string);
    void strcpy_self(char *string1,char*string2);
    char *strcat_self(char *string1,char *string2);

    char *test_string1="Liu Yun is a good teacher!";
    char *test_string2="Liu Yun speaks too fast!";
    char test_string4[]="Liu Yun is a good teacher!";
    char test_string3[]="Liu Yun speaks too fast!";
    char test_string5[]="Liu Yun speaks too fast!";
    printf("%d\n",strlen_self(test_string1)); //字符串的长度计算
    printf("%d\n",strcmp_self(test_string1,test_string2));//字符串的比较
    //strcpy_self(test_string1,test_string2);
    char *p1=test_string3;
    strcpy_self(test_string1,p1);//字符串的复制
    printf("string1:%s,string2:%s\n",test_string1,test_string3);
    printf("%s",strcat_self(test_string4,test_string5));//字符串连接
    return 0;
}
int strcmp_self(char *string1,char *string2)  //字符串比较函数
{
    int i=0;
    while(*(string1+i)==*(string2+i)) //逐个比较
    {
        i++;
	    if (*(string1+i)=='\0')
        {
		    return 0;  //若两字符串相等则返回 0
		} 
	}
	return(*(string1+i)-*(string2+i));  //返回两字符串第一个不相等字符的ASCII码差值
}
int strlen_self(char *string)  //字符串长度计算函数
{
    int length=0;
    for (;*string!='\0';string++)   //字符指针可变，如果是string[]，则变量名常量不可自增运算
    {
        length+=1;
    }
    return length;
}
void strcpy_self(char *string1,char *string2)  //字符串复制函数
{
    //char *p=string2;
    for (;*string1!='\0';string1++,string2++)
    {
        *string2=*string1;      //字符串被创建后就相当于是常量，被存放在堆中，不可修改。转换为数组后，数组是根据栈存储的，可以访问地址并修改。
    }
    *string2='\0';
}
char *strcat_self(char *string1,char *string2)
{
    char *q,*p;
	for(p=string1;*p;p++);
	for(q=string2;*q;q++,p++)
		*p = *q;
	*p = '\0';
	return string1;
}
