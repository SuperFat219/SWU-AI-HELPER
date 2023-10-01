def replace_myself(source, old, new):
    m = len(old)
    index = source.find(old)
    result = ""
    while(index != -1):
        result += source[0:index]
        result += new
        source = source[index+m:]
        #print("source:",source)
        index = source.find(old)
        #print("index:", index)
        #print()
    result += source #把剩余的部分添加进来
    return result

#source = "This is a cat from this isis abc dkdjle thisaais webis"
source = "西南大学的学生热爱西南大学，早上西南西！"
print("长度:",len(source))
old = "西南"
new = "SWU"
source_new = replace_myself(source, old, new)
print(source_new)
