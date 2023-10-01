def special_sum(a,b):
    ''' sum two ints or convert params to ints
    and add. return 0 if conversion fails '''
    if type(a)==int and type(b)==int:
        result = a + b
    else:
        try:
            result = int(a) + int(b)
        except ValueError:
            result = 0
    return result
