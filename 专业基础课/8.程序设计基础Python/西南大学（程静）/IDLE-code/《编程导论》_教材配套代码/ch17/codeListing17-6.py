from math import pi

class Circle (object):
    ''' only allows setting radius. When radius is set, area and circumference
        are updated. Neither area nor circumference can be set
    '''
    def __init__(self, rad=1):
        self.__radius = rad
        self.__circumference = 2*pi*rad
        self.__area = rad*rad*pi
    def __str__(self):
      return 'Radius={:.2f}, Circumference={:.2f}, Area={:.2f}'.\
              format(self.__radius, self.__circumference, self.__area)

    # propety functions
    def get_radius(self):
        print('in get_radius')
        return self.__radius
    def set_radius(self,rad):
        print('in set_radius')
        self.__radius = rad
        self.__circumference = 2*pi*rad
        self.__area = rad*rad*pi
    def get_area(self):
        print('in get_area')
        return self.__area
    def get_circumference(self):
        print('in get_circumference')
        return self.__circumference

    #property attributes
    radius = property(fget=get_radius,fset=set_radius)
    circumference = property(fget=get_circumference)
    area = property(fget=get_area)



