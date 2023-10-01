import turtle
import time
turtle.pensize(5)
turtle.pencolor("yellow")
turtle.fillcolor("red")
turtle.begin_fill()

for i in range(5):
    turtle.forward(200)
    turtle.right(144)

turtle.end_fill()

#在边上写上"Done"
time.sleep(2)
turtle.penup()
turtle.goto(-150,-120)
turtle.color("violet")
turtle.write("Done", font=('Arial', 40, 'normal'))
#turtle.mainloop()
