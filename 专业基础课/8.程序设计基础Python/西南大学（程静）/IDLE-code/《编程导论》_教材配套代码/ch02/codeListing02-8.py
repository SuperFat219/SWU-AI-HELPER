# simple while

x_int = 0     # initialize loop-control variable

# test loop-control variable at beginning of loop
while x_int < 10:
    print(x_int, end=' ')  # print the value of x_int each time through the while loop
    x_int = x_int + 1      # change loop-control variable

print()
print("Final value of x_int: ", x_int)  # bigger than value printed in loop!
