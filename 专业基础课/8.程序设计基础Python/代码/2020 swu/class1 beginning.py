import turtle,time
'''
turtle.pensize(25)
turtle.pencolor('red')
turtle.goto(0,0)
turtle.forward(100)
turtle.penup()
turtle.goto(50,50)
turtle.right(90)
turtle.pendown()
turtle.forward(200)
turtle.goto(50,0)
turtle.right(45)
turtle.forward(150)
turtle.goto(50,0)
turtle.left(90)
turtle.forward(150)
'''
'''
turtle.color('red')
turtle.begin_fill()
turtle.pensize(5)
turtle.pencolor('blue')
turtle.circle(100)
turtle.end_fill()
turtle.done()
'''
'''
turtle.goto(0,0)
turtle.color('red')
turtle.begin_fill()
turtle.circle(50,steps=3)
turtle.end_fill()
turtle.penup()
turtle.setx(120)
turtle.pendown()
turtle.color('blue')
turtle.begin_fill()
turtle.circle(50,steps=5)
turtle.end_fill()
turtle.penup()
turtle.setx(240)
turtle.pendown()
turtle.color('yellow')
turtle.begin_fill()
turtle.circle(50,steps=8)
turtle.end_fill()
turtle.done()
'''
'''
n=0
s=1
while True:
   n+=1
   if n%2==0:
       continue
   s=s+n
   if n>100:
       break
print(s) 
'''
'''
i=100
y=0

while i>0:
    x=i%8
    if x==1:
        y=y+1
    i=i-1
print(y)
'''
'''
list=[]
while True:
    a=float(input('write your number:'))
    if a!=-1:
        list.append(a)
    if a==-1:
        break
b=sum(list)
print(b)
'''
'''
for i in range(1,4):
    for j in range(1,4):
        for k in range(1,4):
            if (i!=j) and (j!=k) and (i!=k):
                print(i*100+j*10+k,end='  ') 
'''
while True:
    x=int(input('输入一个数：'))
    s=0
    for i in range(1,x-1):
        if x%i ==0:
            s=s+i
    if s==x:
        print(x)
        break
    else:
        print('not perfect number')