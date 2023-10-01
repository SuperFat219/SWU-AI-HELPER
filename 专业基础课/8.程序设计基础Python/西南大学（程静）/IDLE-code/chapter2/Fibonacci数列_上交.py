'''
f0,f1 = 1,1
print(f0,f1,end=" ")
count =0
while (f1<=100):
    f0,f1 = f1,f0+f1
    print(f1,end=" ")
    count += 1
print("\nThe first number:",f1)
print("循环次数：",count)
'''

f0 = 1
f1 = 1
print(f0,f1,end=" ")
while (f1<=100):
    tem = f1
    f1 = f0+f1
    f0 = tem
    print(f1,end=" ")
print("\nThe while is end!")
print("f0={},f1={}".format(f0,f1))
