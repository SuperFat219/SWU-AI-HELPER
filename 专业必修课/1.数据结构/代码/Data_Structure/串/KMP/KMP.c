#include <stdio.h>
#include <stdlib.h>
typedef struct 
{
    char *data;
}String;
;
int main()
{
    String s;
    s.data="adbsda";
    printf("%d",strlen(s.data));

}