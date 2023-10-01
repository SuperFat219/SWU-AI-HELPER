#Conversion program
def celsius_to_fahrenheit(x):
    fah = x*1.8+32
    x = x+10
    print("Def内：形参x=",x)
    return fah


#main part of the program
print("Main:Convert Celsius to Fahrenheit.")
celsius_float = float(input("Enter a Celsius temp:"))
print("函数调用前：实参celsius_float=",celsius_float)

fah_float = celsius_to_fahrenheit(celsius_float)
print(celsius_float," Converts to ",fah_float," Fahrenheit")

print("函数调用后：实参celsius_float=",celsius_float)

