import time

def timer(func):
     def deco(*args, **kwargs):  
         start = time.time()
         func(*args, **kwargs)
         stop = time.time()
         print("new function:",stop-start)
     return deco


#test函数有一个参数，或多个参数都可以
@timer
def test(para1,para2): #8
     time.sleep(2)
     print("test is running!",para1,para2)   


test(100,"salf") 
