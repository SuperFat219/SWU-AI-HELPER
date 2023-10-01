# Generate a Hailstone sequence 
import turtle
number_str = input("Enter a positive integer:") 
number = int(number_str) 
count = 0 
 
print("Starting with number:",number) 
#print("Sequence is: ", end=' ') 
 
while number > 1:  # stop when the sequence reaches 1 
 
    if number%2:        # number is odd 
        number = number*3 + 1 
    else:            # number is even 
        number = number/2 
    #print(number,",", end=' ')   # add number to sequence
    
 
    count +=1        # add to the count
    #turtle.penup()
    turtle.goto(count*4,number//64)
    #turtle.dot(3,"blue")
    #turtle.dot(count,number)
    #goto(count,number)
 
else: 
    print()    # blank line for nicer output 
    print("Sequence is ",count," numbers long") 
