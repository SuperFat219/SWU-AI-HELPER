#定义增加好友函数
def add():
    p4={}
    print("\n添加到roommate分组请按1\n添加到friend分组请按2\n添加到classmate分组请按3")
    m=int(input("请输入你的选择："))
    Name=input("请输入增加好友的昵称：")
    Number=input("请输入增加好友的号码：")
    Remark=input("请输入增加好友的备注：")
    p4["name"] = Name
    p4["num"] = Number
    p4["remark"] = Remark
    #按键选择实现添加到具体分组
    if(m==1):
        roommate.append(p4)
    elif(m==2):
        friend.append(p4)
    elif(m==3):
        classmate.append(p4)
    print("增加成功!\n")
    #每一次功能实现后显示好友
    print("-------------你目前的好友：-------------")
    print("roommate:\n")
    for i in roommate:
        print(i)
    print("\nfriend:\n")
    for j in friend:
        print(j)
    print("\nclassmate:\n")
    for k in classmate:
        print(k)


#定义删除好友函数
def delete(Li):
    remark = input("请输入要删除好友的备注：")
    index = -1
    for i in Li:
      if i["remark"]== remark:     #在分组中查找是否有该好友
            index = Li.index(i)
            break
    if index != -1:
        del(Li[index])
        print("删除成功!\n")
    else:
        print("查无此人!\n")
    #每一次功能实现后显示好友
    print("-------------你目前的好友：-------------")
    print("roommate:\n")
    for i in roommate:
        print(i)
    print("\nfriend:\n")
    for j in friend:
        print(j)
    print("\nclassmate:\n")
    for k in classmate:
        print(k)


#定义加入黑名单函数
def blacklist(Li,Lj):
    remark = input("请输入要加入黑名单好友的备注：")
    index = -1
    for i in Li:
      if i["remark"] == remark:
            index = Li.index(i)
            break
    if len(Lj)<=20:
        if index != -1:
            Lj.append(i)
            del(Li[index])
            print("加入黑名单成功!\n")
        else:
            print("查无此人!\n")
    else:
        print("黑名单人数已达上限20人!\n")
    #每一次功能实现后显示好友
    print("-------------你目前的好友：-------------")
    print("roommate:\n")
    for i in roommate:
        print(i)
    print("\nfriend:\n")
    for j in friend:
        print(j)
    print("\nclassmate:\n")
    for k in classmate:
        print(k)


#定义修改信息函数
def modify(Li):
    remark=input("请输入你要修改人的备注：")
    index = -1
    for i in Li:
        if i["remark"]==remark:
            index=Li.index(i)
            break
    if index !=-1:
        i["remark"]=input("请输入你要改成的备注：")
        print("修改成功!\n")
    else:
        print("查无此人\n")
    #每一次功能实现后显示好友
    print("-------------你目前的好友：-------------")
    print("roommate:\n")
    for i in roommate:
        print(i)
    print("\nfriend:\n")
    for j in friend:
        print(j)
    print("\nclassmate:\n")
    for k in classmate:
        print(k)


#定义分组查找和移动函数好友移动到分组
def group_find():
      if(m=="roommate"):
            print("\nroommate:\n")
            for i in roommate:
                  print(i)
      elif(m=="friend"):
            print("friend:\n")
            for j in friend:
                  print(j)
      elif(m=="classmate"):
            print("classmate:\n")
            for k in classmate:
                  print(k)
      else:
            print("该分组不存在!\n")
      print("\n按N结束分组查找功能\n按Y继续移动分组的功能\n")
      k=input("输入你的选择：")
      if(k=="Y"):
          remark=input("请输入你想移动的好友的备注：")
          if(m=="roommate"):
              print("\n移动到roomate分组请按1\n移动到friend分组请按2\n移动到classmate分组请按3\n")
              n=int(input("请输入你的选择："))
              for i in roommate:
                  if(i["remark"]==remark):
                        if(n==1):
                              print("该好友已在该分组!\n")
                        elif(n==2):
                              friend.append(i)
                              del(roommate[roommate.index(i)])
                              print("已移动到friend分组\n")
                        elif(n==3):
                              classmate.append(i)
                              del(roommate[roommate.index(i)])
                              print("已移动到classmate分组\n")
                        else:
                              print("\n输入错误!")
                  else:
                        print("roommate分组不存在该好友!\n")
          elif(m=="friend"):
              print("\n移动到roommate分组请按1\n移动到friend分组请按2\n移动到classmate分组请按3\n")
              n=int(input("请输入你的选择："))
              for i in friend:
                  if(i["remark"]==remark):
                        if(n==1):
                              roommate.append(i)
                              del(friend[friend.index(i)])
                              print("已移动到roomate分组\n")
                        elif(n==2):
                              print("该好友已在该分组!\n")
                        elif(n==3):
                              classmate.append(i)
                              del(friend[friend.index(i)])
                              print("已移动到classmate分组\n")
                        else:
                              print("\n输入错误!")
                  else:
                        print("friend分组不存在该好友!\n")
          elif(m=="classmate"):
              print("\n移动到roommate分组请按1\n移动到friend分组请按2\n移动到classmate分组请按3\n")
              n=int(input("请输入你的选择："))
              for i in classmate:
                  if(i["remark"]==remark):
                        if(n==1):
                              roommate.append(i)
                              del(classmate[classmate.index(i)])
                              print("已移动到roomate分组\n")
                        elif(n==2):
                              friend.append(i)
                              del(classmate[classmate.index(i)])
                              print("已移动到friend分组\n")
                        elif(n==3):
                              print("该好友已在该分组!\n")
                        else:
                              print("\n输入错误!")
                  else:
                        print("classmate分组不存在该好友!\n")
          else:
              print("请重新输入分组的名称!\n")
      elif(k=="N"):
            print("欢迎使用分组查找功能!\n")
      else:
            print("输入错误!")
      #每一次功能实现后显示好友
      print("-------------你目前的好友：-------------")
      print("roommate:\n")
      for i in roommate:
          print(i)
      print("\nfriend:\n")
      for j in friend:
          print(j)
      print("\nclassmate：")
      for k in classmate:
          print(k)
        
#首字母查找好友
def f_l_find():
    for i in L:
        for j,dic in enumerate(i):
            if(dic["name"][0]==m):
                print(dic)         

#主函数
print("-------------欢迎进入YY程序-------------\n")
#最初好友列表展示
p1={'name':'Yq','num':'123564','remark':'大笨蛋'}
p2={'name':'Zyx','num':'562341','remark':'大聪明'}
p3={'name':'Wxc','num':'8962314','remark':'大美女'}
p4={'name':'Hoi','num':'1246659','remark':'路人甲'}
p5={'name':'Hui','num':'1246799','remark':'路人乙'}
p6={'name':'Wty','num':'7891256','remark':'未知生物'}
roommate=[p1,p4]
friend=[p2,p5]
classmate=[p3,p6]
L=[roommate,friend,classmate]
L_black=[]
#打印目前已拥有的好友
print("---------------你的好友：---------------\n")
print("roommate:\n")
for i in roommate:
      print(i)
print("\nfriend:\n")
for j in friend:
      print(j)
print("\nclassmate:\n")
for k in classmate:
      print(k)

#功能展示
print("\n----------------------------------------")
print("--------------增加好友请按a-------------")
print("--------------删除好友请按b-------------")
print("------------修改好友备注请按c-----------")
print("--------------拉黑好友请按d-------------")
print("---------分组查找或移动分组请按e--------")
print("-----------首字母查找好友请按f----------")
print("--------------退出程序请按g-------------")
print("----------------------------------------\n")

#功能选择循环
while(1):
      n=input("\n请输入要选择的功能：")
      if(n=='a'):
            add()
      elif(n=='b'):
            print("\nroommate分组中删除请按1\nfriend分组中删除请按2\nclassmate分组中删除请按3")
            m=int(input("请输入你的选择：")) 
            if(m==1):
                  delete(roommate)
            elif(m==2):
                  delete(friend)
            elif(m==3):
                  delete(classmate)
            else:
                print("\n输入错误!")
      elif(n=='c'):
            print("\nroommate分组中修改请按1\nfriend分组中修改请按2\nclassmate分组中修改请按3\n")
            m=int(input("请输入你的选择："))
            if(m==1):
                  modify(roommate)
            elif(m==2):
                  modify(friend)
            elif(m==3):
                  modify(classmate)
            else:
                  print("\n输入错误!")
      elif(n=='d'):
            print("\n将roommate分组中的好友加入黑名单请按1\n将friend分组中的好友加入黑名单请按2\n将classmate分组中的好友加入黑名单请按3\n")
            m=int(input("请输入你的选择："))
            if(m==1):
                  blacklist(roommate,L_black)
            elif(m==2):
                  blacklist(friend,L_black)
            elif(m==3):
                  blacklist(classmate,L_black)
            else:
                  print("\n输入错误!")
      elif(n=='e'):
            m=input("请输入你想查找的分组：")
            group_find()
      elif(n=='f'):
            m=input("\n请输入你想查找的首字母：")
            print("你要查找的好友:\n")
            f_l_find()
      elif(n=='g'):
            print("---------------感谢使用程序-------------\n")
            break
      else:
            print("----------输入错误，请重新输入!---------\n")
#最终好友列表展示
print("-------------你最终的好友：-------------\n")
print("roommate:\n")
for i in roommate:
      print(i)
print("\nfriend:\n")
for j in friend:
      print(j)
print("\nclassmate:\n")
for k in classmate:
      print(k)

#打印黑名单中的人
print("---------------黑名单：--------------")
for i in L_black:
      print(i)


        
    