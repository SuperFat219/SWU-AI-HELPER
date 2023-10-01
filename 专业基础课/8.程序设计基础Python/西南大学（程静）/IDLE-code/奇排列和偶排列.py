#奇偶逆序判断
col = input("请输入你要判断的序列：")
length = len(col)
x = length-1
sum = 0
print(col," :",x)
while x>=0:
    i = 0
    n = 0
    while i<x:
        if int(col[i]) - int(col[x]) >0:
           n = n+1
           i = i+1
   
    sum = sum+n
    x = x-1

print(sum)
