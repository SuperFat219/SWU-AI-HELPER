#include <iostream>
#include <cstring>
#include <string> 
/* run this program using the console pauser or add your own getch, system("pause") or input loop */
using namespace std;

void Prem(char *);
char* FindMaxForOne(char *p,char *q);
void Swap(char *, char *);

void Prem( char *s )   //全排列函数
 {
      char *pEnd = s + strlen(s) - 1;
      char *p = pEnd;  //p代表替换点
      //q代表替换点的下一个数 ，pMax 代表替换点后比替换点大的最小数
      char *q = new char,*pMax = new char;  //注意初始化！！！
      while (p !=  s)          //p == s 就结束循环
      {
         q = p;
         p--;
         if (*p < *q)
         {
             pMax = FindMaxForOne(p,pEnd);  //找与替换点交换的点
             Swap(p,pMax);         //交换
             //Reverse(q,pEnd);       //将替换点后所有数进行反转
             //Print(s);              //输出
             p = pEnd;             //将替换点置最后一个点，开始下一轮循环
         }
         if (s == p) break;           //结束条件
     }
     return;
 }

char* FindMaxForOne(char *p,char *q)
{
     char *p1 = p;
     char *p2 = q;
     while (*p2 <= *p1) p2--;
     return p2;
 }

void Swap(char **x, char **y){
	char *temp;
	temp = *x;
	*x = *y;
	*y = temp;
	return;
} 


int main(){
   //Prem("abcd")
   char *a ="1" , *b="2";
   Swap(&a, &b);
   //cout<<a<<" "<<b;
   return 0;
   
}
