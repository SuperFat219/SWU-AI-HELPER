#类NewClass的属性__attribute是私有属性，只能在类的内部直接引用，
#不能在类外部直接引用，只能通过方法进行访问
class NewClass(object):
    def __init__(self, attribute='default', name='Instance'):
        self.name = name               #public attribute
        self.__attribute = attribute   #private attribute

    def __str__(self):
        return '{} has attribute {}'.format(self.name, self.__attribute)


inst1 = NewClass(name="Monty",attribute = "Python")
print("打印整个实例inst1:",inst1)
print("打印整个实例inst1的公有属性:",inst1.name)
print("打印整个实例inst1的私有属性:",inst1.__attribute)
print("打印整个实例inst1的私有属性:",inst1._NewClass__attribute)
