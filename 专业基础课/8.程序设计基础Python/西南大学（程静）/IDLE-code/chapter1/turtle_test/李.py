import turtle

#设置画笔的尺寸20，颜色为红色
turtle.pensize(20)
turtle.pencolor('red')

#从原点(0,0)出发，沿水平方向前行100
turtle.forward(100)

#提笔，走到(50,50),无行迹路线
turtle.penup()
turtle.goto(50,50)
turtle.pendown()  #落笔，有行迹路线
turtle.right(90) #当前方向（水平），向右旋转90度
turtle.forward(90)

turtle.penup()
turtle.goto(50,0)
turtle.pendown()
turtle.right(45)
turtle.forward(100)

turtle.penup()
turtle.goto(50,0)
turtle.pendown()
turtle.left(90)
turtle.pensize(10)
turtle.pencolor('blue')
turtle.forward(100)

turtle.penup()
turtle.goto(25,-50)
turtle.left(45)
turtle.pendown()
turtle.forward(50)

turtle.right(135)
turtle.forward(33) #参数33，试出来

turtle.left(45)
turtle.forward(100)

turtle.right(135)
turtle.forward(30)

turtle.penup()
turtle.forward(75)#沿着之前的方向继续前进75，没有划线

turtle.pendown()
turtle.right(135)
turtle.forward(150)


