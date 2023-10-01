import turtle
import random


def line(x, y, m, size, color, rad):

    import turtle
    turtle.pensize(size)
    turtle.pencolor(color)
    turtle.penup()
    turtle.goto(x, y)
    turtle.pendown()
    turtle.right(rad)
    turtle.forward(m)
    turtle.right(135)
    turtle.forward((m / 8))
    turtle.right(180)
    turtle.forward((m / 8))
    turtle.left(90)
    turtle.forward((m / 8))

while 1:
    x = random.randint((-200), 200)
    y = random.randint((-200), 200)
    rad = random.randint(0, 360)
    n = random.randint(1, 5)
    if (n == 1):
        color = 'red'
    elif (n == 2):
        color = 'yellow'
    elif (n == 3):
        color = 'blue'
    elif (n == 4):
        color = 'green'
    elif (n == 5):
        color = 'grey'
    else:
        pass
    size = random.randint(1, 10)
    m = random.randint(10, 100)
    line(x, y, m, size, color, rad)
