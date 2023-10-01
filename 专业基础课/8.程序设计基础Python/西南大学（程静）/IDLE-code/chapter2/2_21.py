number_str = input("Number: ")
sum = 0

while number_str != "#" :
      number = int(number_str)
      if number % 2 == 1: 
          print ("Error, only even numbers please.")
          number_str = input("Number: ")
          continue 
      sum += number
      number_str = input("Number: ")

print ("The sum is:",sum)
