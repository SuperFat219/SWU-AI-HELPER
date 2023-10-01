import math  # need sqrt (square root)

# a Point is a Cartesion point (x,y)
# all values are float unless otherwise stated
class Point(object):
    def __init__(self, x_param = 0.0, y_param = 0.0):
        '''Create x and y attributes. Defaults are 0.0'''
        self.x = x_param
        self.y = y_param

    def distance (self,param_pt): 
        """Distance between self and a Point"""
        x_diff = self.x - param_pt.x  # (x1 - x2)
        y_diff = self.y - param_pt.y  # (y1 - y2)
        # square differences, sum, and take sqrt
        return math.sqrt(x_diff**2 + y_diff**2) 
    
    def sum (self,param_pt):  
        """Vector Sum of self and a Point
            return a Point instance"""
        # new_pt = Point()           
        # new_pt.x = self.x + param_pt.x   
        # new_pt.y = self.x + param_pt.x
        return Point(self.x + param_pt.x, self.x + param_pt.x)
        
    def __str__(self):
        """Print as a coordinate pair."""
        # print("called the __str__ method")
        return "({:.2f}, {:.2f})".format(self.x,self.y)


