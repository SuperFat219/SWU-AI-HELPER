global_X = 27

def my_function(param1=123,param2="hi mom"):
    local_x = 654.321
    print("\n=== local namespace =====")
    for key,val in locals().items():
        print('key:{},object:{}'.format(key,str(val)))
    print("local_x:",local_x)
    print('global_X:',global_X)

my_function()
print("__name__::",__name__)
dict_ = dict()
#dict_ = __dict__
#print("__dict__::",__dict__)
