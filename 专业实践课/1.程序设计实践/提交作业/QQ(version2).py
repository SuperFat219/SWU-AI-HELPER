from tkinter import *
from tkinter import font
from tkinter import messagebox
from os import path
from os import remove


#编辑类弹出窗口
class PopupWindow(object):
    #建立弹出窗口，输入好友信息
    def __init__(self,master,window,title,contact=None):
        self.window=window
        top=self.top=Toplevel(master)#创建子窗口
        top.title(title)
        w = 280
        h = 320
        top.geometry('%dx%d+%d+%d' % (w, h, (ws - w) / 2, (hs - h) / 2))
        top.bind('<Escape>',lambda event:top.destroy())#使当前窗口关闭

        m=font.Font(size=15)#规定字体大小

        label_name=Label(top,text="姓名",font=m)#创建文字标签
        label_name.pack(side=TOP,pady=5)
        self.entry_name=Entry(top)
        self.entry_name.pack(side=TOP,padx=15,ipady=3,fill=X)
        if contact is not None:     #如果contact列表不为空，那么插入名字
            self.entry_name.insert(0,contact.name)#在开始的位置插入名字

        label_ID=Label(top,text="账号",font=m)
        label_ID.pack(side=TOP,pady=5)
        self.entry_ID=Entry(top)
        self.entry_ID.pack(side=TOP,padx=15,ipady=3,fill=X)
        if contact is not None:
            self.entry_ID.insert(0,contact.ID)

        label_remarks=Label(top,text="备注",font=m)
        label_remarks.pack(side=TOP,pady=5)
        self.entry_remarks=Entry(top)
        self.entry_remarks.pack(side=TOP,padx=15,ipady=3,fill=X)
        if contact is not None:   
            self.entry_remarks.insert(0,contact.remarks)
        #如果第一次编辑，则为“添加”，之后为“编辑”
        if contact is None:   
            b=Button(top,text="添加",width=10,command=lambda:self.add_infor(None))
            self.entry_remarks.bind('<Return>',self.add_infor)   #绑定事件执行add_infor函数
        else:
            b=Button(top,text="编辑",width=10,command=lambda:self.edit_infor(None))
            self.entry_remarks.bind('<Return>',self.edit_infor)
        b.pack(side=LEFT,padx=20,pady=10)
        b0=Button(top,text="取消",width=10,command=lambda:top.destroy())
        b0.pack(side=RIGHT,padx=20,pady=10)



    #编辑好友信息
    def edit_infor(self,evet):
        name=self.entry_name.get()
        if not name:#判断姓名一栏是否为空，若为空则停止操作
            messagebox.showinfo("Error!","姓名不能为空。")
            return
        ID=self.entry_ID.get()
        if not ID:#判断账号一栏是否为空，若为空则停止操作
            messagebox.showinfo("Error!","账号不能为空。")
            return
        remarks=self.entry_remarks.get()
        self.window.edit_value(name,ID,remarks)#将编辑信息传入列表
        self.top.destroy()#释放当前子窗口
    
    #添加好友信息
    def add_infor(self,evet):
        name=self.entry_name.get()
        if not name:
            messagebox.showinfo("Error!","姓名不能为空。")
            return
        ID=self.entry_ID.get()
        if not ID:
            messagebox.showinfo("Error!","账号不能为空。")
            return
        remarks=self.entry_remarks.get()
        self.window.add_value(name,ID,remarks)
        self.top.destroy()

#主界面
class MainWindow(object):
    def __init__(self,root):
        self.contacts=[]#建立容纳联系人信息的列表
        self.root=root
        self.add_button()#添加操作按钮
        self.low_list()#底层列表和滚动条
        self.read_contact()#读取文件中的联系人
        self.sel_item=0

    #增添按钮
    def add_button(self):
        frame=Frame(self.root)
        frame.pack(pady=10)
        self.add_btn=Button(frame,text="添加联系人",command=lambda:self.popup("添加联系人"))
        self.add_btn.pack(padx=5,fill=X,side=LEFT)
        self.del_all=Button(frame,text="删除所有联系人",command=self.del_allcontacts)
        self.del_all.pack(padx=5,fill=X,side=LEFT)
        self.stor_all=Button(frame,text="保存所有联系人",command=self.save_allcontacts)
        self.stor_all.pack(padx=5,fill=X,side=LEFT)


    #底层列表
    def low_list(self):
        frame=Frame(self.root)
        frame.pack(pady=10)
        s=font.Font(size=20)
        self.lb=Listbox(frame,font=s,height=15,width=25)#创建可供选择的列表框
        scroll1=Scrollbar(frame,orient=VERTICAL)#创建上下滚动条
        scroll1.config(command=self.lb.yview)#向下滚动
        scroll1.pack(side=RIGHT,fill=Y)
        self.lb.config(yscrollcommand=scroll1.set,activestyle='none')
        scroll2 = Scrollbar(frame, orient=HORIZONTAL)#创建左右滚动条
        scroll2.config(command=self.lb.xview)#向上滚动
        scroll2.pack(side=BOTTOM, fill=X)
        self.lb.config(xscrollcommand=scroll2.set, activestyle='none')
        self.lb.pack(fill=BOTH)
        self.lb.bind('<Button-3>',self.right_menu)    #绑定右键菜单，等待呼应

    #右键菜单
    def right_menu(self,event):
        r_menu=Menu(self.root,tearoff=0)    #创建右键菜单
        r_menu.add_command(label='编辑',command=self.edit_contact)
        r_menu.add_command(label='删除',command=self.del_contact)
        r_menu.post(event.x_root,event.y_root)

    #编辑
    def edit_contact(self):
        selec=self.lb.curselection()   #返回当前的选中的索引
        self.sel_item=selec[0]
        contact=self.contacts[self.sel_item]
        self.popup("编辑联系人",contact=contact)

    #删除
    def del_contact(self):
        warn=messagebox.askyesno("出错了","是否删除此联系人？")
        if warn:
            self.lb.delete(self.sel_item,self.sel_item)
            self.contacts.pop(self.sel_item)
            messagebox.showinfo("提示","删除成功!")

    #添加、编辑弹窗
    def popup(self,title,contact=None):
        self.win=PopupWindow(self.root,self,title,contact)
        self.add_btn["state"]="disabled"
        self.root.wait_window(self.win.top)
        self.add_btn['state']="normal"

    #删除所有联系人
    def del_allcontacts(self):
        warnning=messagebox.askyesno("提示","是否删除所有人？")
        if warnning:     #如果确定则将该组的联系人列表删除
            self.contacts.clear()
            self.lb.delete(0,END)
            remove("联系人.txt")

    #保存所有联系人
    def save_allcontacts(self):
        file=open("联系人.txt","w",encoding='=utf-8')
        for contact in self.contacts:  #遍历每一个联系人列表并将其信息储存到文本文件中
            str="%s,%s,%s\n"%(contact.name,contact.ID,contact.remarks)
            file.write(str)
        file.close()
        messagebox.showinfo("提示","保存成功！")

    #读取已保存的联系人信息
    def read_contact(self):
        if not path.exists('联系人.txt'):#文件中没有联系人就返回
            return
        file=open("联系人.txt","r",encoding='utf-8')
        for line in file:   #遍历文件中的每一行
            a=line.strip().split(',')#创建一个新的列表来容纳文件数据
            contact=Contact(a[0],a[1],a[2])#将数据加入联系人的类对象
            self.contacts.append(contact)#将数据加入大的联系人列表
            self.lb.insert(END,'%sID:%s'%(contact.name,contact.ID))#在底部列表插入姓名和ID
        file.close()

    #添加联系人
    def add_value(self,name,ID,remarks):
        contact=Contact(name,ID,remarks)
        self.contacts.append(contact)    #将联系人信息逐个添加
        self.lb.insert(END, '%s ID:%s' % (name, ID))     #把信息传递到文本的列表

    #编辑联系人
    def edit_value(self, name, ID, remarks):
        contact=self.contacts[self.sel_item]   #读取旧列表的信息
        contact.name=name
        contact.ID=ID
        contact.remarks=remarks
        self.lb.delete(0,END)   #删除原本文本列表的信息
        for contact in self.contacts:   #输入新信息
            self.lb.insert(END,'%sID:%s'%(contact.name,contact.ID))

class Contact:
    def __init__(self,name,ID,remarks):
        self.name=name
        self.ID=ID
        self.remarks=remarks
    

# 程序启动入口
if __name__ == "__main__":
    root = Tk()
    root.wm_resizable(False, False)
    root.title('YY')
    w = 380
    h = 560
    ws = root.winfo_screenwidth()
    hs = root.winfo_screenheight()
    root.geometry('%dx%d+%d+%d' % (w, h, (ws - w) / 2, (hs - h) / 2))
    m = MainWindow(root)
    root.mainloop()
