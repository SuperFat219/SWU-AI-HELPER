'''
number_str = input("请输入一个奇数，以#结束：")
s = 0

while number_str != "#" :
      number = int(number_str)
      if number % 2 == 0:  #number是偶数 
          print ("错误，只能是奇数！")
          number_str = input("请再输入一个奇数，以#结束：")
          continue 
      s += number
      number_str = input("请再输入一个奇数，以#结束：")

print("End of While!")
print("The sum is:",s)

'''

number_str = input("请输入一个奇数，以#结束：")
s = 0

while number_str != "#":
      number = int(number_str)
      if (number%2 !=0):  #是奇数
            s = s + number
      else:
            print(number,"不是奇数！重新输入一个奇数！")
      number_str = input("请输入一个奇数，以#结束：")

print("End of While!")
print("The sum is:",s)
