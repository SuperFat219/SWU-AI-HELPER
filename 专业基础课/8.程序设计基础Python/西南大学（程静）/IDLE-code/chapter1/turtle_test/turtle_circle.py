import turtle

turtle.pensize(10)
turtle.pencolor("red")
turtle.fillcolor("yellow")
turtle.begin_fill()
turtle.penup()
turtle.goto(-150,0)
turtle.pendown()
turtle.circle(50,steps=3)

turtle.penup()
turtle.goto(-30,0)
turtle.pendown()
turtle.circle(50,steps=4)
turtle.end_fill()
