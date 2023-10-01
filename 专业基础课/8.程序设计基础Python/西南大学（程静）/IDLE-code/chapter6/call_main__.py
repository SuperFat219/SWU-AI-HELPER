from test_lib import aa,bb

if __name__ == '__main__':
    print("sum is:",aa(10,21.1))
    bb()
    print ('funtion main:::::::now __name__ is %s' %__name__)
else:
    print("function is not main:::now __name__ is %s" %__name__)
