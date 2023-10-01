#test.py
print('Hello World!')
print('test::begin::__name__ value: ', __name__)



def aaa():
    print('this message is from aaa  function')


def main():
    print('this message is from main function')


if __name__ == '__main__':
    main()
    print ('test::now __name__ is %s' %__name__)
