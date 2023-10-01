def main ():
    '''Compare the Gettysburg Address and the Declaration of Independence.'''
    gettysburg_address_set = set()
    declaration_of_independence_set = set()
    gettysburg_file = open('gettysburg.txt')
    declaration_independence_file = open('declOfInd.txt')
    for line in gettysburg_file:
        process_line(line, gettysburg_address_set)
    for line in declaration_independence_file:
        process_line(line,declaration_of_independence_set)
    pretty_print(gettysburg_address_set, declaration_of_independence_set)
