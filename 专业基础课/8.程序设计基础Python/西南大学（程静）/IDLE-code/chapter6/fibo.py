def PrintFib(n): #打印斐波那契数列前n项
    a,b = 1,1
    for i in range(1,n+1):
        print(a, end=" ")
        a,b = b,a+b
    print()

def GetFib(n): #返回斐波那契数列前n项，到一个列表里
    fib = []
    a,b = 1,1
    for i in range(1,n+1):
        fib.append(a)
        a,b = b,a+b
    return fib
