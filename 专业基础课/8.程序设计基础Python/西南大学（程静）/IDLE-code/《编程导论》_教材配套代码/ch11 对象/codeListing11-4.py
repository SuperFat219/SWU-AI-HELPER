
import math  # need sqrt (square root)

# a Point is a Cartesion point (x,y)
# all values are float unless otherwise stated
class Point(object):
    def __init__(self):  # create and initialize
        self.x = 0.0
        self.y = 0.0
        
    def distance (self,param_pt):  # standard distance formula
        """Distance between self and a Point"""
        x_diff = self.x - param_pt.x  # (x1 - x2)
        y_diff = self.y - param_pt.y  # (y1 - y2)
        # square differences, sum, and take sqrt
        return math.sqrt(x_diff**2 + y_diff**2) 
    
    def sum (self,param_pt):  # new point from vector sum
        """Vector Sum of self and a Point"""
        new_pt = Point()           # create a new point
        new_pt.x = self.x + param_pt.x   # calculate x value sum from self and pt
        new_pt.y = self.y + param_pt.y
        return new_pt
