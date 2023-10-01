'''
height = float(input('输入您的身高：'))
weight = float(input('输入您的体重：'))
BMI = weight / (height**2)

if 18.5 <= BMI < 24:
    print('正常')
elif BMI >= 24:
    print('过胖')
else:
    print('过瘦')
'''
score = float(input('输入分数:'))
if 90 <= score < 100:
    print('A')
elif 80 <= score < 90:
    print('B')
elif 70 <= score < 80:
    print('C')
elif 60 <= score < 70:
    print('D')
elif score < 60:
    print('E')
else:
    print('请重新输入')
