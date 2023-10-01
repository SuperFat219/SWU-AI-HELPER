# 几个变量？bmi,height,weight
height = float(input("请输入身高（cm):"))
weight = float(input("请输入体重(kg):"))
bmi = weight/(height**2)
print("高度：",height,"，体重是：",weight,"体重指数：",bmi)
print()

#=========用if实现（有问题）========
if(bmi<18.5):
    print("体重过轻")
if(18.5<=bmi<24):
    print("体重正常")
if(24<=bmi<27):
    print("体重过重")
#else:
#    print("肥胖")

if(bmi>=27):
    print("肥胖")
    


#=========用if-else=================
'''
if (bmi<18.5):
    print("体重过轻")
else:
    if(bmi<24):
       print("体重正常")
    else:
        if(bmi<27):
           print("体重过重")
        else:
           print("肥胖")
'''

#========用if-elif-else================
'''
if (bmi<18.5):
    print("体重过轻")
elif (bmi<24):
    print("体重正常")
elif (bmi<27):
    print("体重过重")
else:
    print("肥胖")
'''
