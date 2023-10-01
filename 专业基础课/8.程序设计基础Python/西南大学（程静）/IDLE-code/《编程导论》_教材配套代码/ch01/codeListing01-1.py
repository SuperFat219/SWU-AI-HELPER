# calculate the area and circumference of a circle from its radius
# Step 1: prompt for a radius
# Step 2: apply the area formula
# Step 3: Print out the results

import math #导入数学模块

radius_str = input("Enter the radius of your circle: ") #键盘输入半径
radius_int = int(radius_str) #将键盘输入的符号转换为整数

circumference = 2 * math.pi * radius_int #计算园周长
area = math.pi * (radius_int ** 2)  #计算园面积

print ("The cirumference is:",circumference, ", \
and the area is:",area) #输出结果
