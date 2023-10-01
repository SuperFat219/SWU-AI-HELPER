//统计一个文档中的行数，中英文字符数，空格数，数字的个数及其他字符数
#include <stdio.h>
#include <string.h>
int *Count_file(char *filename,int *statistical_data);
int main()
{
    char filename[30];
    int statistical_data[8]={0,0,0,0,0,0,0,0}; 
    /*statistical_data[0]:行数，data[1]:英文字符数，data[2]:英文单词数,data[3]:中文字数,
    data[4]:空格数，data[5]:数字数，data[6]:其它字符数,data[7]:总字符数*/
    printf("Input filename:\n");
    scanf("%s",filename);
    if (Count_file(filename,statistical_data))
    {
        printf(" Total number:%d\n lines:%d\n English characters:%d\n English words:%d\n Chinese words:%d\n Space:%d\n Number:%d \n Other characters:%d\n",\
        statistical_data[7],statistical_data[0],statistical_data[1],statistical_data[2],statistical_data[3],\
        statistical_data[4],statistical_data[5],statistical_data[6]);
    }
    else
    {
        printf("Error!\n");
    }
    return 0;
}
int *Count_file(char *filename,int *statistical_data)
{
    FILE *fp;  //对应文件的指针
    char buffer[1003];  //定义缓冲区
    int bufferLen;   //缓冲区中内容的实际长度
    int i;  //按行读取当前读到第i个字符
    char c; //按行读取当前读到的字符
    int isLastBlank=0; //判断上一个字符是否为空格
    int char_num_en=0;  //英文字符数
    int word_num=0;  //单词数
    int word_num_cn=0; //中文字数
    int space_number=0; //空格数
    int num_number=0; //数字数
    int other_char_num=0; //其它字符数

    if ((fp=fopen(filename,"rb"))==NULL)
    {
        perror(filename); //输出错误原因
        return NULL;
    }
    printf(" line   En_char   En_words   CN_char  Space   Number  Other_char\n");
    while (fgets(buffer,1003,fp)!=NULL)    //读取一行数据并存储至缓冲区
    {
        bufferLen=strlen(buffer);  //读取该行字符串的实际长度
        for (i=0;i<bufferLen;i++)
        {
            c=buffer[i];
            if (c==' ' || c=='\t')   //判断空格及英文单词
            {
                if (isLastBlank==0) 
                {
                    word_num++;
                }
                isLastBlank=1;
                space_number+=1;
            }
            else if (c!='\n'&&c!='\r')
            {
                if (c<0) 
                {
                    word_num_cn++;
                    i+=1; //一个中文字符占两个字符
                }
                else if (c>'0'&&c<'9') 
                {
                    num_number+=1;
                }
                else if ((c>'A'&&c<'Z')||(c>'a'&&c<'z')) 
                {
                    char_num_en+=1;
                }
                else 
                {
                    other_char_num+=1;
                }
                isLastBlank=0;
            }
        }
        !isLastBlank && word_num++; //判断每行的最后一个单词
        isLastBlank=0;
        statistical_data[0]++; //行数+1
        statistical_data[1]+=char_num_en;
        statistical_data[2]+=word_num;
        statistical_data[3]+=word_num_cn;
        statistical_data[4]+=space_number;
        statistical_data[5]+=num_number;
        statistical_data[6]+=other_char_num;
        statistical_data[7]+=bufferLen;
        printf(" %-10d%-10d%-10d%-10d%-8d%-8d%-10d\n",statistical_data[0],char_num_en,word_num,word_num_cn,\
        space_number,num_number,other_char_num);
        char_num_en=0;word_num_cn=0;word_num=0;
        space_number=0;num_number=0;other_char_num=0;  //置0开始读取下一行
    }
    return statistical_data;
}


/*
int main(int argc, char* argv) {
    FILE* rf = fopen("experiment.txt","r");
    char a[1024][128];
    int i=0;
    while(fgets(a[i],128,rf))i++;
    int n=i; 
    printf("行数：%d\n",n);
    fclose(rf);
    i=0;
    while(i<n)printf("%s",a[i++]);
    system("pause");
    return 0;
}
*/
//#define LEN 40
/*
int main(int argc, char *argv[])
{
    FILE *in,*out;
    int ch;
    char name[LEN];
    int count=0;
    if (argc<2)
    {
        fprintf(stderr,"Usage:%s filename\n",argv[0]);
        exit(EXIT_FAILURE);
    }
    if ((in=fopen(argv[1],"r"))==NULL)
    {
        fprintf(stderr,"I couldn't open the file\"%s\"\n",argv[1]);
        exit(EXIT_FAILURE);
    }
    strncpy(name,argv[1],LEN-5);
    name[LEN-5]='\0';
    strcat

}
*/
