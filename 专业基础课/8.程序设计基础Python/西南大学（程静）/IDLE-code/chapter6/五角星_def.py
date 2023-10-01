def five_star(x,y):
    turtle.penup()
    turtle.goto(x,y)
    turtle.pendown()
    turtle.pensize(5)
    turtle.pencolor("yellow")
    turtle.fillcolor("red")
    turtle.begin_fill()
    #--画五角星-----------
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)
    turtle.right(144)
    turtle.forward(100)
    turtle.right(144)
    #--画五角星-----------
    turtle.end_fill()
    return 


import turtle
five_star(0,100)
five_star(-100,200)
x = input()

