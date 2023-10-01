global_X = 27

def my_function(param1=123,param2="hi mom"):
    local_x = 654.321
    print("\n=== local namespace =====")
    for key,val in locals().items():
        print('key:{},object:{}'.format(key,str(val)))
    print("local_x:",local_x)
    print('global_X:',global_X)
    print()
    print("@@@@@@@@::",dir())
    print()

my_function()
print("__name__::",__name__)
print("__doc__::::",__doc__)
print()

dict_ = dict()  #创建了一个空字典
print("###########:")
print()

print("$$$$$$$$$$$:",dir())
print()

print("my_function's dir:::::::",dir(my_function))
print("__class__::::",my_function.__class__)
print("__dict__::::",my_function.__dict__)
print("__dir__::::",my_function.__dir__)
