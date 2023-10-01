number_str = input("Number: ")
sum = 0

while number_str != "#" :
      if not number_str.isdigit(): 
          print ("Error, only numbers please.")
      else:
          number = int(number_str)
          sum += number
      number_str = input("Number: ")

print ("The sum is:",sum)
