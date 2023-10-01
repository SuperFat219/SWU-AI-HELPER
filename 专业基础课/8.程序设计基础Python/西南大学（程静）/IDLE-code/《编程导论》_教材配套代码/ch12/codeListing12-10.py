class MyClass (object):
    ''' parent is object'''
    pass

class MyChildClass (MyClass):
    ''' parent is MyClass '''
    pass

my_child_instance = MyChildClass()
my_class_instance = MyClass()

print(MyChildClass.__bases__)        # the parent class
print(MyClass.__bases__)             # ditto
print(object.__bases__)              # ditto

print(my_child_instance.__class__)   # class from which the instance came
print(type(my_child_instance))       # same question, asked via function
