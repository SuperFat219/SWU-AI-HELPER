class P(object):
    # parent class 父类
    '''P xxxxclass'''
    Str1='aaaa'
    def __init__(self):
        print('created an instance of', self.__class__.__name__)

class C(P):
    # child class 子类
    Str2='bbb'

c = C()
#print(c.Str2)
#print(c.Str1)
#print(c.__class__) # class that created us 显示 c 所属的类名
#print(C.__bases__)

print("父类P的所有属性和方法：",dir(P))
print()
print("子类C的所有属性和方法：",dir(C))
print()
print("子类对象c的所有属性和方法：",dir(c))
