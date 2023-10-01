import math
import pylab

t = 0.0
a=2.0

x_values = []
y_values = []


while t < math.pi*2:
     x_values.append(2*math.cos(t)-math.cos(2*t))
     y_values.append(2*math.sin(t)-math.sin(2*t))     
     t = t + 0.01
#print(x_values," ",y_values)
      
#plot the x and y values as red c i r c l e s
pylab.plot(x_values,y_values,'ro')
pylab.show()
                      
