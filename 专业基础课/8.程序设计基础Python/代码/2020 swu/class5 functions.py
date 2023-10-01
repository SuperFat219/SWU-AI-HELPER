#写一个自己的求字符串长度的函数。
#改进：写一个函数，统计字符串中字母的个数。
#写一个和find功能类似的函数，要求在“西南大学.txt”，调用自己写的函数，完成子串“西南”的查找
#替换的函数能不能自己做（可以用replace）
#求素数

#code:
import jieba as jb
str='sdjbhvSDLKC13213'
def len1(string):
    n=0
    for i in string:
        n+=1
    return n

def alpha_count(string):
    string=string.lower()
    n=0
    for i in string:
        if i.isalpha()==True:
            n+=1
    return n

def find1(string,target):
    for index in range(len(string)):
        if string[index]==target:
            position=index
            return position
    return None

def index_of_str(s1, s2):
    lt=s1.split(s2,1)
    if len(lt)==1:
        return -1
    return len(lt[0])
#print(index_of_str('12abc34de5f', 'c34'))

def sushu(x):
    if x>2:
        for i in range(2,x):
            b=x%i
            if b==0:
                print('不是素数')
                break
        else:   #循环中有break语句，else跟在循环体后面，若循环体中的break语句没有执行，则执行else语句。
            print('素数')
    else:
        print('不是素数')
sushu(89)