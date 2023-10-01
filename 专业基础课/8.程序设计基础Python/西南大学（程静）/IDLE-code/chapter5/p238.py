number_str = input("Input a floating-point number: ")

while True:
      try:
         number_float = float(number_str)
      except ValueError:
         number_str = input("Try again: input a floating-point number: ")

#number_float = float(number_str)

print("Number is",number_float)
