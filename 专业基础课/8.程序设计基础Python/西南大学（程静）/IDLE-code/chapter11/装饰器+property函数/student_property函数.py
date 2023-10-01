class Student:
    def __init__(self,aa=10):
        self._age = aa

    def get_age(self):
        print('获取属性时执行的代码')
        return self._age

    def set_age(self, age):
        print('设置属性时执行的代码')
        self._age = age

    def del_age(self):
        print('删除属性时执行的代码')
        del self._age

    #property函数的用法
    age = property(get_age, set_age, del_age, '学生年龄')


#-------main--------------------
obj = Student(19)
# 注意要用"类名.属性.__doc__"的形式查看属性的文档字符串
print('查看属性的文档字符串：' + Student.age.__doc__)
"""
查看属性的文档字符串：学生年龄
"""

# 设置属性
obj.age = 18
"""
设置属性时执行的代码
"""

# 获取属性
print('学生年龄为：' + str(obj.age))
"""
获取属性时执行的代码
学生年龄为：18
"""

# 删除属性
del obj.age
"""
删除属性时执行的代码
"""
