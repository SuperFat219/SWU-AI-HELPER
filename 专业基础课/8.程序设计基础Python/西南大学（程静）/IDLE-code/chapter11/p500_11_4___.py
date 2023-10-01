import math

class Point(object):
    def __init__(self,xx=0.0,yy=0.0):
        self.x = xx
        self.y = yy

    def distance(self,param_pt):
        """ Distance between s e l f and a Point """
        x_diff = self.x - param_pt.x
        y_diff = self.y - param_pt.y
        return math.sqrt(x_diff**2 + y_diff**2)

    def sum(self,param_pt):
        """ Vector Sum of s e l f and a Point """
        new_pt = Point() 
        new_pt.x = self.x + param_pt.x
        new_pt.y = self.y + param_pt.y
        return new_pt


obj = Point(10,2)
print("Point::::::",dir(Point))
print()
print("object of Point:::::::",dir(obj))
print()
print("Point::::class:",Point.__class__,",hash:",Point.__hash__,",hash:::",hash(Point))
print()
print("object::::class:",obj.__class__,",hash:",obj.__hash__,",hash:::",hash(obj))
