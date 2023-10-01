import time

#===test()函数是被修饰函数=====
def test():
     time.sleep(2)
     print("test is running!") 

#===timer函数中返回是deco函数（地址）
def timer(func): 
     def deco():  
         start = time.time()
         func()
         stop = time.time()
         print(stop-start)
     return deco  
 

#通过timer函数（内嵌一个装饰函数deco的方式），改变了被装饰函数test的功能
#没有改变被装饰函数test的调用方式 ，没有改变被装饰函数test的源码
#但增加了test的功能，而且test中的print语句只执行一次
test = timer(test) 
test()  
