def localVar_1(x):
    print("LocalVar_1 function::before::x=",x)
    x = 100
    print("LocalVar_1 function::after::x=", x)
    return

def localVar_2():
    x = -100
    print("LocalVar_2 function::before::x=",x)
    x = x+350
    print("LocalVar_2 function::after::x=", x)
    return

#---主程序--------------
x = 10000
print("主程序中的x::before::x=",x)
y = 1
localVar_1(y)
localVar_2()
print("主程序中的x::after::x=", x)
