def isPrime(param):
   N = param
   for i in (2,N):
       if (param%i == 0):
           return False
    return True


#---主程序--------
for x in range(2:101):
    if (isPrime(x)):
        print(x,end=" ")
