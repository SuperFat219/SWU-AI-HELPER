number_str = input("请输入奇数,以#结束：") #输入字符：数字/#
s = 0

while (number_str != "#"):
    x = int(number_str)
    number_str = input("请再次输入奇数,以#结束：")
    if (x%2 == 0):    #x不是奇数
        continue
    s = s + x
    # number_str = input("请再次输入奇数,以#结束：")

print("End of While")
print("奇数之和：",s)
