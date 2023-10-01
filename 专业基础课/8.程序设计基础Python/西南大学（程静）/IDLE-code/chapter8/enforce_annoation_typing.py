import enforce

@enforce.runtime_validation
def add_int(a:int,b:int=0)->int:
    return a+b

if __name__ =="__main__":
    print(add_int.__annotations__)
    print("result is:",add_int(3.1,4))
