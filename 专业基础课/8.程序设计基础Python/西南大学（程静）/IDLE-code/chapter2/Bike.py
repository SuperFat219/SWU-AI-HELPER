x = int(input("请输入距离："))
t1 = x/1.2
t2 = x/3.0+27+23
if(t1<t2):
    print("Walk")
elif (t1>t2):
    print("Bike")
else:
    print("All")
