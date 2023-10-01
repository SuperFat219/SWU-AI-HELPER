import math

class Point(object):
    def __init__(self):
        self.x = 0.0
        self.y = 0.0

    def distance(self,param_pt):
        """ Distance between s e l f and a Point """
        x_diff = self.x - param_pt.x
        y_diff = self.y - param_pt.y
        return math.sqrt(x_diff**2 + y_diff**2)

    def sum(self,param pt):
        """ Vector Sum of s e l f and a Point """
        new_pt = Point() 
        new_pt.x = self.x + param_pt.x
        new_pt.y = self.y + param_pt.y
return new pt
