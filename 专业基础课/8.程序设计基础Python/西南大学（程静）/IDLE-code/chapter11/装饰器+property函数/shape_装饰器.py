import math

class Point:
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y
        
    def distance_from_origin(self):
        return math.hypot(self.x, self.y)

    def __eq__(self, other):
        return (self.x==other.x) and (self.y==other.y)

    def __repr__(self):
        return "Point({0.x!r}, {0.y!r})".format(self)

    def __str__(self):
        return "({0.x!r}, {0.y!r})".format(self)


class Circle(Point):
    def __init__(self, rr=0, x=0, y=0):
        super().__init__(x, y)
        assert rr > 0, "::radius must be nonzero and non-negative"
        self._radius = rr

    def edge_distance_from_origin(self):
        return abs(self.distance_from_origin() - self.radius)

    def area(self):
        return math.pi * (self.radius ** 2)

    def circumference(self):
        return 2 * math.pi * self.radius

    def __eq__(self, other):
        return (self.radius == other.radius) and super().__eq__(other)

    def __repr__(self):
        return "Circle({0.radius!r}, {0.x!r}, {0.y!r})".format(self)

    def __str__(self):
        return repr(self)

    #----property装饰器----------------
    @property
    def radius(self):
        return self._radius

    @radius.setter
    def radius(self,rr):
        assert rr > 0, "radius must be nonzero and non-negative"
        self._radius = rr 



#----main---------------
'''
cc = Circle(-2)   #设置半径为负数，会抛出异常
print(cc.radius)  #对象cc没有定义
cc = Circle(10)
print(cc.radius)
cc.radius = -10   #设置半径为负数，会抛出异常
print(cc.radius)  #cc.radius 仍然为10，-10没有设置进去
'''
