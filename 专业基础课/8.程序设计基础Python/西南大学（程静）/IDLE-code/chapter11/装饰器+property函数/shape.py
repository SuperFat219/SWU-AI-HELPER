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
    def __init__(self, radius, x=0, y=0):
        super().__init__(x, y)
        self.radius = radius

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


'''
----在shell中调用代码---------------
p = Shape.Point(28, 45)
c = Shape.Circle(5, 28, 45)
p.distance_from_origin() # returns: 53.0
c.distance_from_origin() # returns: 53.0
c.edge_distance_from_origin()


p = shape.Point(3, 9)
repr(p)                                   # returns: 'Point(3, 9)'
q = eval(p.__module__ + "." + repr(p))    #搞清楚eval函数的含义,
                                          #p.__module__里面的存储的是文件名"shape"
                                          # eval("shape.Point(3,9)")的结果就是一个
                                          #Point对象：Point(3,9), 可以用id函数看看p和q是不是同一个对象
repr(q)                                   #returns: 'Point(3, 9)'

'''
