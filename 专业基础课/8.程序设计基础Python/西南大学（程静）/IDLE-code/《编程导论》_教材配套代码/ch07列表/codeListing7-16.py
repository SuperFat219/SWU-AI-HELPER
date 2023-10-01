import numpy
import pylab

# Generate lists of points for both sine and cosine
x_values = numpy.arange(0, 4*numpy.pi, 0.1)
y1_values = numpy.sin(x_values)
y2_values = numpy.cos(x_values)

# Plot two curves on the same graph
pylab.title('Sine and Cosine Plot')
pylab.plot(x_values,y1_values,'b')
pylab.plot(x_values,y2_values, 'r')
pylab.show()
