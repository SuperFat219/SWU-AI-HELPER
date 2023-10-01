"'my name is cheng jing'"
import math
global_X = 27

def my_function(par_1=123,par_2 ="hi mom"):
    "'this is a local function!!!!!'"
    import math
    local_x = math.pi
    print("\n === local namespace ===")
    for key,val in locals().items():
        print("key:{},object:{}".format(key,str(val)))
    #print("local_x:",local_x)
    #print("global_X:",global_X)
    #print("local::__name__  ",__name__)
    ###my_dict = my_function.__dict__
    ####print("length=",len(my_dict))
    print("<<<<<",dir())
    #print("<<<<<",dir(my_function))
    #print("<<<<<",my_function.__str__)
    #print("<<<<<",my_function.__dict__)

my_function()

key,val = 0,0
print("\n-----global namespace ------")
for key,val in globals().items():
    print("key:{},object:{}".format(key,str(val)))

print("\n\n------------------------")
#print("Global_X:",global_X)
#print("Math.pi",math.pi)
#print("pi:",pi)
print("global::__doc__  ",__doc__)
print("global::__name__  ",__name__)
print("global::__file__  ",__file__)
print("local::__str__  ",my_function.__str__)
print("local::__doc__  ",my_function.__doc__)


#获得当前模块的属性列表
print("\n\n---------dir函数:当前模块的命名空间---------------")
print(dir())

#获得函数my_function的属性列表
print("\n\n---------dir函数:my_function的属性列表---------------")
print("<<<<<",dir(my_function))



