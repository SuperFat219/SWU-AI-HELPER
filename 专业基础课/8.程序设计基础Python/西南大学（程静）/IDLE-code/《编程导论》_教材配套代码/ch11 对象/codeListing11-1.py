class Student(object):
    """Simple Student class."""
    def __init__(self,first='', last='', id=0): # initializer
        self.first_name_str = first
        self.last_name_str = last
        self.id_int = id
    
    def __str__(self):  # string representation, e.g. for printing
        return "{} {}, ID:{}".format\
               (self.first_name_str, self.last_name_str, self.id_int)
