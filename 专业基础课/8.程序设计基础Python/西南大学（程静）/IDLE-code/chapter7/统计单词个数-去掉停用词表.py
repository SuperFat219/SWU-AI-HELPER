file_obj = open("gettysburg.txt","r")
stop = "the,this,a,it,is,for,to,so,and,in,of,that,on,by,but,not,have,can,shall,be,from,here,are,we,do,did,will,us,cannot,--"
stop_ls = stop.strip(",")
print("stop list:",stop_ls)
whole_ls = []

for line in file_obj:
    line = line.lower()
    line_ls = line.split()
    for elem in line_ls:
        elem = elem.strip(".,?\"")
        if elem not in stop_ls:
            whole_ls.append(elem)

print()
print(whole_ls)
print()
print("文章的单词个数：",len(whole_ls))


file_obj.close()
