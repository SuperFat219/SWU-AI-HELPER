m = int(input("请输入一个正整数到m："))
n = int(input("请输入另一个正整数到n（n>m)："))
print("程序将计算从m到n之间的奇数之和！")

the_sum =0
for i in range(m,n+1):
    if (i%2==1):
        the_sum += i

print("奇数之和是：",the_sum)
