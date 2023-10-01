number_str = input("请输入一串数字:")

N = len(number_str)
i = 0
s = 0
while i<N:  # 循环结束，是N，还是N-1
    the_char = number_str[i]
    for j in range(i+1,N):  #j变化的范围是什么？
       if (number_str[j]<the_char):  #正确代码
       # if (number_str[j]>the_char):  
            s = s+1
    print(the_char," ",s," End of for!") #调试信息
    i = i+1

print("End of While!") #调试信息
print("逆序数是：",s)
