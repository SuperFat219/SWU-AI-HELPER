#===若value设置为private属性====================
class FuzzyBool(object):
    def __init__(self, vv):
        self._value = vv if 0.0<=vv<=1.0 else 0.0

    def __invert__(self):
        return FuzzyBool(1.0-self._value)
    
    def __and__(self, other):
        other = FuzzyBool(other)
        return FuzzyBool(min(self._value, other._value))

    def __iand__(self, other):
        other = FuzzyBool(other)
        self._value = min(self._value,other._value)
        
    def __repr__(self):
        return ("{0}({1})".format(self.__class__.__name__, self._value))
    
    def __str__(self):
        return "This is a FuzzyBool number:"+str(self._value)
    
    def __bool__(self):
        return self._value > 0.5

    def __int__(self):
        return round(self._value)

    def __float__(self):
        return self._value

    def __eq__(self,other):
        other = FuzzyBool(other)
        return self._value == other._value

    def __hash__(self):
        print("@@@@@@:",id(self),",&&&&&&:",type(id(self)))
        return hash(id(self))

    @property
    def value(self):
        return self._value

    @value.setter
    def value(self,vv):
        assert 0.0<=vv<=1.0, "not in the scopy of FuzzyBool"
        self._value = vv 

    @staticmethod
    def conjunction(*fuzzies):
         return FuzzyBool(min( [float(x) for x in fuzzies] ))

#=============main======================

f = FuzzyBool(0.15)
g = FuzzyBool(.75)
t = f&(-0.1)
print("逻辑与的结果：",t)
s = ~f
print("逻辑非的结果:",s)
print("调用repr函数：",repr(s))

print()
print("逻辑真还是逻辑假：",bool(g))
print()
print("是否可以hashable？f:",hash(f),",g:",hash(g))

#----试试get vs set方法----------------
f.value = 0.21
g.value = 0.11
print("\n试试get vs set属性：")
print("f=",f,"，，，g=",g)
print("看看hash编码? f:",hash(f),",g:",hash(g))  #f和g的hash code都没有改变



#看看类方法conjunction的使用============
#f = FuzzyBool(0.15)  #前面已经定义了
#g = FuzzyBool(.75)
h = FuzzyBool(0.01)
print("\n合取运算（类来调用），就多个FuzzyBool的最小值：",FuzzyBool.conjunction(f, g, h))
print("合取运算（实例来调用)，就多个FuzzyBool的最小值：",h.conjunction(f, g, h))
print("合取运算（实例来调用)，就多个FuzzyBool的最小值：",f.conjunction(f, g, h))








'''
# ===若value设置为public属性====================
class FuzzyBool(object):
    def __init__(self, vv):
        self.value = vv if 0.0<=vv<=1.0 else 0.0
    def __invert__(self):
        return FuzzyBool(1.0-self.value)
    
    def __and__(self, other):
        other = FuzzyBool(other)
        return FuzzyBool(min(self.value, other.value))


    def __iand__(self, other):
        other = FuzzyBool(other)
        self.value = min(self.value,other.value)
        
    def __repr__(self):
        return ("{0}({1})".format(self.__class__.__name__, self.value))
    
    def __str__(self):
        return "This is a FuzzyBool number:"+str(self.value)
    
    def __bool__(self):
        return self.value > 0.5

    def __int__(self):
        return round(self.value)

    def __float__(self):
        return self.value

    def __eq__(self,other):
        other = FuzzyBool(other)
        return self.value == other.value

    def __hash__(self):
        print("@@@@@@:",id(self),",&&&&&&:",type(id(self)))
        return hash(id(self))


#=============main======================
#当value成为共有属性，则可以在class外面直接使用该属性，比如对该属性进行赋值
#当赋值后，f.value的值改变，但f对象的hash code没有改变
f = FuzzyBool(.56)
hash(f)
#@@@@@@: 1000706484040 ,&&&&&&: <class 'int'>
#1000706484040
f.value = 0.35
hash(f)
#@@@@@@: 1000706484040 ,&&&&&&: <class 'int'>
#1000706484040

'''
