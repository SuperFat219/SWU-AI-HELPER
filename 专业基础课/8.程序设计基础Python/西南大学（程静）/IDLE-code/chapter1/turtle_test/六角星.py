import turtle

turtle.pensize(10)
turtle.pencolor("yellow")
turtle.fillcolor("yellow")
turtle.begin_fill()
turtle.circle(100,steps=3)
turtle.penup()
turtle.goto(0,200)
turtle.pendown()
turtle.left(180)
turtle.circle(100,steps=3)
turtle.end_fill()
