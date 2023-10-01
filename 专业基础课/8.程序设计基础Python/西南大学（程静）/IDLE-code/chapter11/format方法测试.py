#coding=utf-8
class formatest:
    def __init__(self, name, age):
        self.name,self.age = name, age

    def __format__(self,specification):
        print("specif is:",specification)
        print("self is:",self.name)
        
        if specification == "":
            return str(self)

        strformat = specification.replace("%s",self.name).replace("%r",str(self.age))
        return strformat

    def __str__(self):
        return self.name+",id="+str(id(self))

if __name__ == "__main__":
    people1 = formatest("zhanglin", 31)
    people2 = formatest("Jing",42)
    print("################")
    print ("{1},,,,,{0}".format(people1,people2))
    print("#################")
    print ("{1:%s-%r},{0:%s---------%r}".format(people1,people2))
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@A")
    print (format(people1, "%s-%r"))
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@B") 
    print (format(people2))




'''
class people:
    def __init__(self, name, age):
        self.name,self.age = name, age

    def __format__(self,specification):
        if specification == "":
            return str(self)

        strformat = specification.replace("%s",self.name).replace("%r",self.age)
        return strformat

class hand:
    def __init__(self, *people):
        self.peoples = []
        self.peoples= list(people)
    def __format__(self, specification):
        if specification == "":
            return str(self)        
        return ",".join("{:{fs}}".format(c, fs=specification) for c in self.peoples)
  
if __name__ == "__main__":
    handobj = hand(people("zhangsan", "18"), people("lisi", "28"), people("zhanglin", "38"))
    print ("{:%s-%r}".format(handobj))

'''
