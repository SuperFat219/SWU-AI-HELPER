try:
    print("Entering the try suite")
    dividend = float(input("Provide a dividend to divide:"))
    divisor = float(input("Provide a divisor to divide by:"))
    result = dividend/divisor   
    print("{:2.2f} divided by {:2.2f} yields {:2.2f}".\
        format(dividend,divisor,result))
except ZeroDivisionError:
    print("Divide by 0 error")
except ValueError:
    print("Value error, could not convert to a float")

print("Continuing on with the rest of the program")
