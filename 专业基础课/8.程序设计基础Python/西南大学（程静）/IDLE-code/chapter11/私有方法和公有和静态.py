class Book(object):
    bookName = "Class Varible:HallyPoter"

    def __init__(self,si=0,ty="fiction"):
        self.size = si
        self.__type = ty

    def __str__(self):
        return 'bookName:{},size:{},type:{}'.format(Book.bookName,self.size,self.__type)

    def __privateMethod(self):
        return (self.size,self.__type)

    def publicMethod(self):
        print("The Name of Book:",Book.bookName) #类变量在引用时，必须加上：类名.变量名
        (s,t) = self.__privateMethod()
        print("BookSize:",s,"BookType:",t)
        return

    def staticMethod():
        return Book.bookName   #凡是用类变量的地方，全部要写上“类名.类变量”


def main():
    book_cj = Book(123,"fiction")
    print("size:",book_cj.size)
    print("type:",book_cj._Book__type)
    print(book_cj)
    #print(book_cj.__privateMethod()) #直接调用私有方法不行，但可以换一个名字调用
    print(book_cj._Book__privateMethod())

    #调用共有方法
    book_cj.publicMethod()

    #调用静态方法（类方法）
    #print("调用静态方法，打印bookName:",book_cj.staticMethod())
    print("调用静态方法，打印bookName:",Book.staticMethod())

    
