import random
import math
import turtle
turtle.speed(0)
cd=0
times=1000
random.seed()
for i in range(0,times):
    x=200*random.random()
    y=200*random.random()
    d=math.sqrt(math.pow(x,2)+math.pow(y,2))
    turtle.penup()
    turtle.goto(x,y)
    turtle.pendown()    
    
    if d<=200:
        turtle.dot('red')    
        cd+=1
    else:
        turtle.dot('blue')    
print(4*cd/times)
