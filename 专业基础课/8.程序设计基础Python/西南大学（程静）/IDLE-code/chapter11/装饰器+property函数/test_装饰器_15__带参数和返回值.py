import time

def timer(func):
     def deco(*args, **kwargs):  
         start = time.time()
         re = func(*args, **kwargs)
         stop = time.time()
         print("new function:",stop-start)
         return(re+10) #可以根据需要随意返回一个值，
                       #不应非要是被修饰函数的返回值 
     return deco


#test函数有一个参数，或多个参数都可以
@timer
def test(para1,para2): #8
     time.sleep(2)
     print("test is running!",para1,para2)
     return(para1)


x = test(100,"salf")
print("x=",x)
