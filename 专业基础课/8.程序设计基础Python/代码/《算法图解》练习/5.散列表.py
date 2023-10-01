#hash(),SHA函数
#Python 字典(Dictionary) get() 函数返回指定键的值。
#dict.get(key, default=None)
#key -- 字典中要查找的键。
#default -- 如果指定键的值不存在时，返回该默认值。

#投票检索
'''
voted={}
def check_voter(name):
    if voted.get(name):
        print('kick them out')
    else:
        voted[name]=True
        print('let them vote')    
check_voter('tom')
'''