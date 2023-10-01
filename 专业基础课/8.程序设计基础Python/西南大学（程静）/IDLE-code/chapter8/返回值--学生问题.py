def func():
    str_local = ""
    for i in range(10):
        str_local = str_local + str(i)
    print("函数内部的字符串：",str_local)
    return str_local


#--主程序--------------
str_main = func()
print("主函数的串：",str_main)