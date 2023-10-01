class MyClass (object):
    pass
    

MyClass.class_attribute = "Hello"
my_instance = MyClass()
your_instance = MyClass()
my_instance.instance_atrribute = "World"
your_instance.another_attri = "Python"

print("\n==看看实例的数据类型======")
print(my_instance.__class__)
print(your_instance.__class__)

print("\n==看看类MyClass的数据类型======")
print(MyClass.__class__)

print("\n对象的属性列表=====================")
print("<<<<:",dir(my_instance))
print()
print("<<<<:",dir(your_instance))

print()
print()
print(my_instance.class_attribute)
print(your_instance.class_attribute)

###看一看:主模块的命名空间里面#########
print()
print()
print("--------看看命名空间-------------")

key,val = 0,0
print("\n-----global namespace ------")
for key,val in globals().items():
    print("key:{},object:{}".format(key,str(val)))



print("\n\n---看看类MyClass的属性---------")
print(dir(MyClass))


print("\n\n---看看类MyClass的__dict__---------")
print(MyClass.__dict__)

print("\n\n---看看实例my_instance的属性---------")
print(dir(my_instance))


print("\n\n---看看实例my_instance的__dict__---------")
print(my_instance.__dict__)

