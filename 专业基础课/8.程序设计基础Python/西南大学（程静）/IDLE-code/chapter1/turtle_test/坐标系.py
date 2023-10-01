import turtle

def line(x,y,m,size,color,rad):  
    turtle.pensize(size)
    turtle.pencolor(color)
    turtle.penup()
    turtle.goto(x,y)
    turtle.pendown()
    turtle.right(rad)
    turtle.forward(m)
    turtle.right(135)
    turtle.forward(m/100)
    turtle.right(180)
    turtle.forward(m/100)
    turtle.left(90)
    turtle.forward(m/100)
    
line(0,-150,300,3,'red',270)
line(-200,0,1000,3,'blue',225)

turtle.penup()
turtle.goto(-7,-7)
turtle.pendown()
turtle.circle (5)
turtle.penup()
turtle.goto(0,0)
turtle.pendown()
