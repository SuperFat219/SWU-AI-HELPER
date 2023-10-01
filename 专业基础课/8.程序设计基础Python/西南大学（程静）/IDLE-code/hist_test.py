import numpy as np
from matplotlib import pyplot as plt 
a = np.array([22,87,5,43,56,73,55,54,11,20,51,5,79,31,27])
np.histogram(a,bins=[0,20,40,60,80,100]) 
hist,bins = np.histogram(a,bins=[0,20,40,60,80,100])  
print(hist) 
print(bins)


'''a = np.array([22,87,5,43,56,73,55,54,11,20,51,5,79,31,27]) '''
plt.hist(a, bins=[0,20,40,60,80,100]) 
plt.title("histogram") 
plt.show()




