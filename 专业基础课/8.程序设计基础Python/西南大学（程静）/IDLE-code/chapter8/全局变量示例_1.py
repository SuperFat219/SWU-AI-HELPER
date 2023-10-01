def local_fun():
    print("全局变量x的值::in function::x=",x)
    #x = -100
    return
    
#--主程序----------
x = 10000
print("全局变量x的值::主程序::before::x=", x)
local_fun()
x = -1
print("全局变量x的值::主程序::中间::x=", x)
local_fun()
print("全局变量x的值::主程序::after::x=", x)
