class MyClass (object):
    pass


#my_instance = MyClass()
#print("class:::",dir(MyClass))
#print()
#print("instance::",dir(my_instance))

MyClass.class_attribute = "hello"
print(MyClass.class_attribute)
print("class:::::",dir(MyClass))
print()

my_instance = MyClass()
your_instance = MyClass()
my_instance.instance_attribute = "world"
your_instance.properties = "python"
print("my:::::",dir(my_instance))
print()
print("your:::::",dir(your_instance))

print()
print()
print("看看__dict__属性，__dir__属性")
print("Class.__dict__:::::",MyClass.__dict__)
print("Class.__dict__[key]::::::::",MyClass.__dict__["__module__"])
print("Class.__dir__:::::",MyClass.__dir__)
print()
print("Object：my.__dict__::::::",my_instance.__dict__)
print("Object：my.__dir__::::::",my_instance.__dir__)
