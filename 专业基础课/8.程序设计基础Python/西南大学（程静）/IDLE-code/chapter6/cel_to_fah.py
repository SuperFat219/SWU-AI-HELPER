#Conversion program
def celsius_to_fahrenheit(celsius_float):
    fah = celsius_float*1.8+32
    return fah

#--main------------------
cel = float(input("请输入一个摄氏温度:"))
fah = celsius_to_fahrenheit(cel)
print("摄氏温度是：{:.2f}，相应华氏温度是：{:.2f}.".format(cel,fah))
