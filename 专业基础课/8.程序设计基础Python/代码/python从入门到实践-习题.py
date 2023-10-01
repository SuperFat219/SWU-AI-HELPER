#第三章 列表
'''习题1（3.2）
3-4 嘉宾名单：如果你可以邀请任何人一起共进晚餐（无论是在世的还是故去的），
你会邀请哪些人？请创建一个列表，其中包含至少 3 个你想邀请的人；然后，使用这个列表打印消息，邀请这些人来与你共进晚餐。
3-5 修改嘉宾名单：你刚得知有位嘉宾无法赴约，因此需要另外邀请一位嘉宾。
 以完成练习 3-4 时编写的程序为基础，在程序末尾添加一条 print 语句，指出哪位嘉宾无法赴约。
 修改嘉宾名单，将无法赴约的嘉宾的姓名替换为新邀请的嘉宾的姓名。
 再次打印一系列消息，向名单中的每位嘉宾发出邀请。
3-6 添加嘉宾：你刚找到了一个更大的餐桌，可容纳更多的嘉宾。请想想你还想邀请哪三位嘉宾。
 以完成练习 3-4 或练习 3-5 时编写的程序为基础，在程序末尾添加一条 print 语句，指出你找到了一个更大的餐桌。
 使用 insert()将一位新嘉宾添加到名单开头，使用 insert()将另一位新嘉宾添加到名单中间。
 使用 append()将最后一位新嘉宾添加到名单末尾。
 打印一系列消息，向名单中的每位嘉宾发出邀请。
3-7 缩减名单：你刚得知新购买的餐桌无法及时送达，因此只能邀请两位嘉宾。
 以完成练习 3-6 时编写的程序为基础，在程序末尾添加一行代码，打印一条你只能邀请两位嘉宾共进晚餐的消息。
 使用 pop()不断地删除名单中的嘉宾，直到只有两位嘉宾为止。每次从名单中弹出一位嘉宾时，都打印一条消息，让该嘉宾知悉你很抱歉，无法邀请他来共进晚餐。
 对于余下的两位嘉宾中的每一位，都打印一条消息，指出他依然在受邀人之列。
 使用 del 将最后两位嘉宾从名单中删除，让名单变成空的。打印该名单，核实程序结束时名单确实是空的。
'''
'''
guests_list=['jack','mary','john']
def invitation(x):
    for i in range(x):
        print('邀请'+guests_list[i]+'共进晚餐')
invitation(3)
print(guests_list[1]+'不能赴约')
guests_list[1]='nancy'
invitation(3)
print()
print('we have a bigger one,so we can invite more people')
guests_list.insert(0,'Robert')
guests_list.insert(1,'Tom')
guests_list.append('Johnson')
print(guests_list)
invitation(6)
print()
print('on account of the space limitation,we can only invite two with us')
def delete():
    name=guests_list.pop()
    print('sorry for '+name)
for i in range(4):
    delete()
for i in range(2):
    print(guests_list[i]+' is still in the list')
del guests_list[0]
del guests_list[0]
print(guests_list)
'''
'''
list=['sad','sadsa','fga','tyfddv','fag']
print(sorted(list))
print(list)
list.sort()
print(list)
'''
#习题2 列表解析4.3
'''
4-6 奇数：通过给函数 range()指定第三个参数来创建一个列表，其中包含 1~20 的
奇数；再使用一个 for 循环将这些数字都打印出来。
4-7 3 的倍数：创建一个列表，其中包含 3~30 内能被 3 整除的数字；再使用一个 for
循环将这个列表中的数字都打印出来。
4-8 立方：将同一个数字乘三次称为立方。例如，在 Python 中，2 的立方用 2**3
表示。请创建一个列表，其中包含前 10 个整数（即 1~10）的立方，再使用一个 for 循
环将这些立方数都打印出来。
4-9 立方解析：使用列表解析生成一个列表，其中包含前 10 个整数的立方。
4-10 用1，2，3，4组成数字不重复的三位数
'''
#4-6:
'''
list1=list(range(1,21,2))
for i in list1:
    print(i,end=' ')
'''
#4-7:
'''
list=[]
for i in range(3,31):
    if i%3==0:
        list.append(i)
for i in list:
    print(i,end=' ')
'''
#4-8,4-9：
'''list=[value**3 for value in range(1,11)]
print(' '.join('%s'%id for id in list))
'''
#4-10:
'''for i in range(1,5):
    for j in range(1,5):
        for k in range(1,5):
            if (i!=j) and (j!=k) and (i!=k):
                print(i*100+j*10+k,end='  ') 
'''
#if语句
#5.4
'''PROBLEM:
5-8 以特殊方式跟管理员打招呼：创建一个至少包含 5 个用户名的列表，且其中一
个用户名为'admin'。想象你要编写代码，在每位用户登录网站后都打印一条问候消息。
遍历用户名列表，并向每位用户打印一条问候消息。
 如果用户名为'admin'，就打印一条特殊的问候消息，如“Hello admin, would you like to see a status report?”。
 否则，打印一条普通的问候消息，如“Hello Eric, thank you for logging in again”。
5-9 处理没有用户的情形：在为完成练习 5-8 编写的程序中，添加一条 if 语句，检查用户名列表是否为空。
 如果为空，就打印消息“We need to find some users!”。
 删除列表中的所有用户名，确定将打印正确的消息。
5-10 检查用户名：按下面的说明编写一个程序，模拟网站确保每位用户的用户名都独一无二的方式。
 创建一个至少包含 5 个用户名的列表，并将其命名为 current_users。  再创建一个包含 5 个用户名的列表，
将其命名为 new_users，并确保其中有一两个用户名也包含在列表 current_users 中。
 遍历列表 new_users，对于其中的每个用户名，都检查它是否已被使用。如果是这样，就打印一条消息，
指出需要输入别的用户名；否则，打印一条消息，指出这个用户名未被使用。
 确保比较时不区分大小写；换句话说，如果用户名'John'已被使用，应拒绝用户名'JOHN'。
5-11 序数：序数表示位置，如 1st 和 2nd。大多数序数都以 th 结尾，只有 1、2 和 3例外。
 在一个列表中存储数字 1~9。  遍历这个列表。
 在循环中使用一个 if-elif-else 结构，以打印每个数字对应的序数。输出内容
应为 1st、2nd、3rd、4th、5th、6th、7th、8th 和 9th，但每个序数都独占一行。
'''
#5-8，5-9
'''
users_name=['admin','john','robert','mary','jack']
if users_name:
    for i in users_name[:]:
        if i == 'admin':
            print('hello %s,would you like to see a status report?'%i)
            users_name.remove(i)
        else:
            print('hello %s,thank you for logging in again'%i)
            users_name.remove(i)
#在遍历列表时，在循环内部使用remove方法删除元素，会出现输出信息错误的情况。
# 如上面代码，第一个数字 1 进来，打印出1，在a中去掉1，列表变成了[2, 3, 4, 5]。
# 再次进入循环时，应该取出列表的第二个数字，由于去掉了1，所以第二个元素变成了3， 跳过了2。
#solution:建立一个列表的副本即可
else:
    print('we need to find some users!')
print(users_name)
'''
#5-10
'''
current_users=['john','jack','mary','robert','tom']
new_users=[]
def name():
    new_user=input('input your user\'s name')
    if new_user.lower() in current_users:
        print('please rewrite a name:')
        again()
    else:
        print('this name is callable!')
        new_users.append(new_user)        
def again():
    name()
def main():
    name()
main()
'''
#5-11
'''
number_list=[1,2,3,4,5,6,7,8,9]
for i in number_list:
    if i==1:
        print('%sst'%i)
    elif i==2:
        print('%snd'%i)
    elif i==3:
        print('%srd'%i)
    else:
        print('%sth'%i)
'''
#6-5 河流：创建一个字典，在其中存储三条大河流及其流经的国家。其中一个键—值对可能是'nile': 'egypt'。 
#  使用循环为每条河流打印一条消息，如“The Nile runs through Egypt.”。
#  使用循环将该字典中每条河流的名字都打印出来。
#  使用循环将该字典包含的每个国家的名字都打印出来
'''
dict={'nile':'egypt','changjiang':'china','yamaxun':'brazil'}
for k,v in dict.items():
    print('The',k.title(),'runs through',v.title())
for k in dict:
    print(k)
for k in dict.values():
    print(k)
    '''
#8-7 专辑：编写一个名为 make_album()的函数，它创建一个描述音乐专辑的字典。
#这个函数应接受歌手的名字和专辑名，并返回一个包含这两项信息的字典。使用这个函数创建三个表示不同专辑的字典，并打印每个返回的值，以核实字典正确地存储了专辑的信息。
#给函数 make_album()添加一个可选形参，以便能够存储专辑包含的歌曲数。如果调用这个函数时指定了歌曲数，就将这个值添加到表示专辑的字典中。
# 调用这个函数，并至少在一次调用中指定专辑包含的歌曲数。
#8-8 用户的专辑：在为完成练习 8-7 编写的程序中，编写一个 while 循环，让用户输入一个专辑的歌手和名称。
# 获取这些信息后，使用它们来调用函数 make_album()，并将创建的字典打印出来。在这个 while 循环中，务必要提供退出途径。
'''
def make_album(name,album,number=''):
    dict={'singer_name':name,'album_name':album}
    if number:
        dict['songs_number']=number
    print(dict)
    return dict
make_album('jay','gun',3)
make_album('jj','love')
make_album('jie','sky',6)
'''
#9-1 餐馆：创建一个名为 Restaurant 的类，其方法__init__()设置两个属性：
#restaurant_name 和 cuisine_type。创建一个名为 describe_restaurant()的方法和一个名为 open_restaurant()的方法，
# 其中前者打印前述两项信息，而后者打印一条消息，指出餐馆正在营业。
#根据这个类创建一个名为 restaurant 的实例，分别打印其两个属性，再调用前述两个方法。
#9-2 三家餐馆：根据你为完成练习 9-1 而编写的类创建三个实例，并对每个实例调用方法 describe_restaurant()。
#9-4 就餐人数：在为完成练习 9-1 而编写的程序中，添加一个名为 number_served的属性，并将其默认值设置为 0。
# 根据这个类创建一个名为 restaurant 的实例；打印有多少人在这家餐馆就餐过，然后修改这个值并再次打印它。
#添加一个名为 set_number_served()的方法，它让你能够设置就餐人数。调用这个方法并向它传递一个值，然后再次打印这个值。
#添加一个名为 increment_number_served()的方法，它让你能够将就餐人数递增。
#调用这个方法并向它传递一个这样的值：你认为这家餐馆每天可能接待的就餐人数。
#9-6 冰淇淋小店：冰淇淋小店是一种特殊的餐馆。编写一个名为 IceCreamStand 的类，
# 让它继承你为完成练习 9-1 或练习 9-4 而编写的 Restaurant 类。这两个版本的Restaurant 类都可以，
# 挑选你更喜欢的那个即可。添加一个名为 flavors 的属性，用于存储一个由各种口味的冰淇淋组成的列表。
# 编写一个显示这些冰淇淋的方法。创建一个IceCreamStand 实例，并调用这个方法。
'''
class Restaurant():
    def __init__(self,restaurant_name,cuisine_type):
        self.name=restaurant_name
        self.type=cuisine_type
        self.number_served=0
    def describe_restaurant(self):
        print(self.name,end=' ')
        print(self.type)
    def open_restaurant(self):
        print('the restaurant is opening')
    def set_number_served(self,number):
        self.number_served=number
        print(self.number_served)
    def increment_number_served(self,number):
        self.number_served+=number
        print(self.number_served)
#restaurant=Restaurant('starbucks','dish')
#print(restaurant.name)
#print(restaurant.type)
#print(restaurant.number_served)
#restaurant.set_number_served(25)
#restaurant.increment_number_served(78)
#restaurant.describe_restaurant()
#restaurant.open_restaurant()
class IceCreamStand(Restaurant):
    def __init__(self, restaurant_name, cuisine_type):
        super().__init__(restaurant_name, cuisine_type)
        self.flavors=['a','v','b']
stand=IceCreamStand('kkk','iii')
stand.describe_restaurant()
print(stand.flavors)
'''
#9-3 用户：创建一个名为 User 的类，其中包含属性 first_name 和 last_name，还有用户简介通常会存储的其他几个属性。
# 在类 User 中定义一个名为 describe_user()的方法，它打印用户信息摘要；再定义一个名为 greet_user()的方法，它向用户发出个性化的问候。
#创建多个表示不同用户的实例，并对每个实例都调用上述两个方法。
#9-5 尝试登录次数：在为完成练习 9-3 而编写的 User 类中，添加一个名为login_attempts 的属性。
# 编写一个名为 increment_login_attempts()的方法，它将属性login_attempts 的值加 1。
# 再编写一个名为 reset_login_attempts()的方法，它将属性login_attempts 的值重置为 0。
#根据 User 类创建一个实例，再调用方法 increment_login_attempts()多次。
# 打印属性 login_attempts 的值，确认它被正确地递增；然后，调用方法 reset_login_attempts()，
#并再次打印属性 login_attempts 的值，确认它被重置为 0。
#9-7 管理员：管理员是一种特殊的用户。编写一个名为 Admin 的类，让它继承你为完成练习 9-3 或练习 9-5 而编写的 User类。
# 添加一个名为 privileges 的属性，用于存储一个由字符串（如"can add post"、"can delete post"、"can ban user"等）
# 组成的列表。编写一个名为 show_privileges()的方法，它显示管理员的权限。创建一个 Admin实例，并调用这个方法。
#9-8 权限：编写一个名为 Privileges 的类，它只有一个属性——privileges，其中存储了练习 9-7 所说的字符串列表。
# 将方法 show_privileges()移到这个类中。在 Admin类中，将一个 Privileges 实例用作其属性。
# 创建一个 Admin 实例，并使用方法show_privileges()来显示其权限。
'''
class User():
    def __init__(self,first,last):
        self.first_name=first
        self.last_name=last
        self.login_attempts=0
    def describe_user(self):
        print(self.first_name,end=' ')
        print(self.last_name)
    def greet_user(self):
        print('hello '+self.first_name+' '+self.last_name)
    def increment_login_attempts(self):
        self.login_attempts+=1
    def reset_login_attempts(self):
        self.login_attempts=0
class admin(User):
    def __init__(self,first,last,number):
        super().__init__(first,last)
        self.privilege=privileges(number)
    
class privileges():
    def __init__(self,number):
        self.privileges=['can add post','can delete post','can ban user']
        self.privilege=self.privileges[number]
    def show_privileges(self):
        print(self.privilege)
admin1=admin('john','biden',1)
admin1.describe_user()
admin1.greet_user()
admin1.privilege.show_privileges()

user1=User('jack','brown')
user2=User('morgan','freeman')
user1.describe_user()
user1.greet_user()
for i in range(3):
    user1.increment_login_attempts()
print(user1.login_attempts)
user1.reset_login_attempts()
print(user1.login_attempts)
user2.describe_user()
user2.greet_user()
'''
'''
9-9 电瓶升级：在本节最后一个 electric_car.py 版本中，给 Battery 类添加一个名为
upgrade_battery()的方法。这个方法检查电瓶容量，如果它不是 85，就将它设置为 85。
创建一辆电瓶容量为默认值的电动汽车，调用方法 get_range()，然后对电瓶进行升级，
并再次调用 get_range()。你会看到这辆汽车的续航里程增加了。
'''

'''
with open(r'd:\desktop\asd.txt','r',encoding='utf-8') as file:
    lines=file.readlines()
    for line in lines:
        print(line.rstrip())
'''
#10-1 Python 学习笔记：在文本编辑器中新建一个文件，写几句话来总结一下你至此学到的 Python 知识，
#其中每一行都以“In Python you can”打头。将这个文件命名为learning_python.txt，
#并将其存储到为完成本章练习而编写的程序所在的目录中。
#编写一个程序，它读取这个文件，并将你所写的内容打印三次：第一次打印时读取整个文件；
#第二次打印时遍历文件对象；第三次打印时将各行存储在一个列表中，再在 with 代码块外打印它们。
#10-2 C 语言学习笔记：可使用方法 replace()将字符串中的特定单词都替换为另一个单词。
# 下面是一个简单的示例，演示了如何将句子中的'dog'替换为'cat'：
#>>> message = "I really like dogs." 
#>>> message.replace('dog', 'cat') 
#'I really like cats.' 
#读取你刚创建的文件 learning_python.txt 中的每一行，将其中的 Python 都替换为另一门语言的名称，如 C。
# 将修改后的各行都打印到屏幕上。
'''
with open('learning_python.txt') as f:
    txt=f.readlines()
    print(txt)
    for line in txt:
        print(line.replace('python','C'))
'''
#10-3 访客：编写一个程序，提示用户输入其名字；用户作出响应后，将其名字写入到文件 guest.txt 中。
#10-4 访客名单：编写一个 while 循环，提示用户输入其名字。用户输入其名字后，
#在屏幕上打印一句问候语，并将一条访问记录添加到文件 guest_book.txt 中。确保这个文件中的每条记录都独占一行。
#10-5 关于编程的调查：编写一个 while 循环，询问用户为何喜欢编程。每当用户输入一个原因后，都将其添加到一个存储所有原因的文件中。
'''
with open('a.txt','w',encoding='utf-8') as f:
    while True:
        name=input('输入你的名字： ')
        if name=='q':
            break
        print('hello ',name)
        f.write(name+' ')
        reason=input('why you like coding? ')
        if reason=='q':
            break
        f.write(reason+'\n')
'''
#10-6 加法运算：提示用户提供数值输入时，常出现的一个问题是，用户提供的是文本而不是数字。
# 在这种情况下，当你尝试将输入转换为整数时，将引发 TypeError 异常。
# 编写一个程序，提示用户输入两个数字，再将它们相加并打印结果。在用户输入的任何一个值不是数字时都捕获 TypeError 异常，并打印一条友好的错误消息。
# 对你编写的程序进行测试：先输入两个数字，再输入一些文本而不是数字。
#10-7 加法计算器：将你为完成练习 10-6 而编写的代码放在一个 while 循环中，让用户犯错（输入的是文本而不是数字）后能够继续输入数字。
'''
def plus_calculation():
    while True:
        try:
            num1=float(input('输入第一个数字： '))
            num2=float(input('输入第二个数字： '))
            result=num1+num2
        except ValueError:
            print('sorry,your input is invalid,please rewrite it')
            continue
        else:
            print(result)
            break
plus_calculation()
'''
#10-8 猫和狗：创建两个文件 cats.txt 和 dogs.txt，在第一个文件中至少存储三只猫的名字，在第二个文件中至少存储三条狗的名字。
# 编写一个程序，尝试读取这些文件，并将其内容打印到屏幕上。将这些代码放在一个 try-except 代码块中，
# 以便在文件不存在时捕获 FileNotFound 错误，并打印一条友好的消息。将其中一个文件移到另一个地方，并确认 except 代码块中的代码将正确地执行。
#10-9 沉默的猫和狗：修改你在练习 10-8 中编写的 except 代码块，让程序在文件不存在时一言不发。
'''
with open('cat.txt','w') as f:
    f.write('rose\tmary\tjack')
def read(fileName):
    try:
        with open(fileName) as f1:
            print(f1.read())
    except FileNotFoundError:
        pass
        #print('sorry,can\'t find',fileName)
list=['cat.txt','dog.txt']
for name in list:
    read(name)
'''

#10-10 常见单词：访问项目 Gutenberg（http://gutenberg.org/），并找一些你想分析的图书。
# 下载这些作品的文本文件或将浏览器中的原始文本复制到文本文件中。
#你可以使用方法 count()来确定特定的单词或短语在字符串中出现了多少次。例如，下面的代码计算'row'在一个字符串中出现了多少次：
#>>> line = "Row, row, row your boat" 
#>>> line.count('row') 
#2 
#>>> line.lower().count('row')
#3 
#请注意，通过使用 lower()将字符串转换为小写，可捕捉要查找的单词出现的所有次数，而不管其大小写格式如何。
#编写一个程序，它读取你在项目 Gutenberg 中获取的文件，并计算单词'the'在每个文件中分别出现了多少次。
'''
with open('cat.txt') as f:
    txt=f.read().lower()
    print(txt.count('the'))

import json
try:
    with open('people.json') as f:
        users=json.load(f)
except FileNotFoundError:
    identity=input('输入用户身份： ')
    with open('people.json','w') as f2:
        json.dump(identity,f2)
        print('we will remember you next time',identity)
else:
    print('welcome back',users)
    '''
#10-11 喜欢的数字：编写一个程序，提示用户输入他喜欢的数字，并使用json.dump()将这个数字存储到文件中。
# 再编写一个程序，从文件中读取这个值，并打印消息“I know your favorite number! It’s _____.”。
#10-12 记住喜欢的数字：将练习 10-11 中的两个程序合而为一。如果存储了用户喜欢的数字，就向用户显示它，
# 否则提示用户输入他喜欢的数字并将其存储到文件中。运行这个程序两次，看看它是否像预期的那样工作。
#10-13 验证用户：最后一个 remember_me.py 版本假设用户要么已输入其用户名，要么是首次运行该程序。
# 我们应修改这个程序，以应对这样的情形：当前和最后一次运行该程序的用户并非同一个人。
#为此，在 greet_user()中打印欢迎用户回来的消息前，先询问他用户名是否是对的。
#如果不对，就调用 get_new_username()让用户输入正确的用户名。
'''
import json
def user_input():
    number=input('输入你喜欢的数字： ')
    with open('number.json','w') as f:
        json.dump(number,f)
    return number
def number_read():
    with open('number.json') as f2:
        user_number=json.load(f2)
        print('I know your favorite number!It\'s',user_number)
while True:
    if user_input()=='q':
        break
    number_read()
'''
#11-1 城市和国家：编写一个函数，它接受两个形参：一个城市名和一个国家名。这个函数返回一个格式为 City, Country 的字符串，如 Santiago, Chile。
# 将这个函数存储在一个名为 city _functions.py 的模块中。
#创建一个名为 test_cities.py 的程序，对刚编写的函数进行测试（别忘了，你需要导入模块 unittest 以及要测试的函数）。
# 编写一个名为 test_city_country()的方法，核实使用类似于'santiago'和'chile'这样的值来调用前述函数时，得到的字符串是正确的。
#运行 test_cities.py，确认测试 test_city_country()通过了。
#11-2 人口数量：修改前面的函数，使其包含第三个必不可少的形参 population，并返回一个格式为 City, Country – population xxx 的字符串，
# 如 Santiago, Chile – population 5000000。运行 test_cities.py，确认测试 test_city_country()未通过。
#修改上述函数，将形参 population 设置为可选的。再次运行 test_cities.py，确认测试 test_city_country()又通过了。
#再编写一个名为 test_city_country_population()的测试，核实可以使用类似于'santiago'、'chile'和'population=5000000'这样的值来调用这个函数。
# 再次运行test_cities.py，确认测试 test_city_country_population()通过了。
'''
def storage(city,country,population=''):
    if population:
        string=city+','+country+' - population '+str(population)
    else:
        string=city+','+country
    return string.title()

#print(storage('santiago','chile',50000))

import unittest
from city_functions import storage
class storage_test(unittest.TestCase):
    def test_city_country(self):
        string1=storage('santiago','chile')
        self.assertEqual(string1,'Santiago,Chile')
    def test_city_country_second(self):
        string1=storage('santiago','chile',50000)
        self.assertEqual(string1,'Santiago,Chile - Population 50000')
unittest.main()
'''
#11-3 雇员：编写一个名为 Employee 的类，其方法__init__()接受名、姓和年薪，并将它们都存储在属性中。
# 编写一个名为 give_raise()的方法，它默认将年薪增加 5000美元，但也能够接受其他的年薪增加量。
#为 Employee 编写一个测试用例，其中包含两个测试方法：test_give_default_raise()和 test_give_custom_raise()。
# 使用方法 setUp()，以免在每个测试方法中都创
#建新的雇员实例。运行这个测试用例，确认两个测试都通过了。
'''
class Employee():
    def __init__(self,first_name,last_name,salary):
        self.first_name=first_name
        self.last_name=last_name
        self.salary=salary
    def give_raise(self,number=5000):
        self.salary+=number
        return self.salary
import unittest

class testemployee(unittest.TestCase):
    def setUp(self):
        self.employee=Employee('jack','brown',50000)
    def test_give_default_raise(self):
        salary=self.employee.give_raise()
        self.assertEqual(salary,55000)
    def test_give_custom_raise(self):
        salary=self.employee.give_raise(10000)
        self.assertEqual(salary,60000)
unittest.main()
'''
