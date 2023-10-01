import time
def test():
     time.sleep(2)
     print("test is running!")

def deco(func):  
     start = time.time()
     func() #2
     stop = time.time()
     print(stop-start)

deco(test) #1
