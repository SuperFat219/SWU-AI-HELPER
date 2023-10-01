#=====尝试使用matplotlib==============
#import numpy as np
#import matplotlib.pyplot as plt

#x=[1,2,3,4,5]
#y=[2,4,6,8,10]
#plt.plot(x,y)
#plt.show()

#===使用pylab=======================
import pylab
list_of_ints = []
for counter in range(10):
    list_of_ints.append(counter*2)
    
print(list_of_ints)
print(len(list_of_ints))


pylab.plot(list_of_ints)
pylab.show()
