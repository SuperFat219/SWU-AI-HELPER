import turtle
import random

def pentagram(x,y):
    turtle.penup()
    turtle.goto(x,y)
    turtle.pendown()
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)


turtle.setup(width=500, height=400)
for i in range(0,10):
    x = random.uniform(-150,150)
    y = random.uniform(-150,150)
    pentagram(x,y)






