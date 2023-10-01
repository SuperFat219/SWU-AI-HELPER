import time
def test():
     time.sleep(2)
     print("test is running!")

def deco(func):  
     start = time.time()
     func()
     stop = time.time()
     print("time::",stop-start)
     #return deco
     return func

####如果deco函数返回的是deco##########
#t = test
#test=deco(test)
#test(t)

####如果deco函数返回的是func##########
test = deco(test)
test()

#1）没有修改被装饰函数test的源码
#2) 没有修改被装饰函数test的调用方式
#3) 有没有增加test的功能（似乎没有）


