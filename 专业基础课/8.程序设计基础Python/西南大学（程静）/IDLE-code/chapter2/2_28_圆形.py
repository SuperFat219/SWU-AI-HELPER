import math
import pylab

r = 2.0  #半径
a,b = (0.0,1.0)  #圆心
t=0.0

x_values = []
y_values = []


while t < math.pi*2:
     x_values.append(a+r*math.cos(t))
     y_values.append(b+r*math.sin(t))     
     t = t + 0.01
      
#plot the x and y values as red c i r c l e s
pylab.plot(x_values,y_values,'ro')
pylab.show()
                      
