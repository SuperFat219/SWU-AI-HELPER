def main():
    gettysburg_address_set = set()
    declaration_of_independence_set = set()
    gettysburg_file = open('gettysburg.txt')
    declaration_independence_file = open('dec_of_independence.txt')
    for line in gettysburg_file:
        process_line(line, gettysburg_address_set)
    for line in declaration_independence_file:
        process_line(line,declaration_of_independence_set)

    pretty_print(gettysburg_address_set, declaration_of_independence_set)



def add_word(word, word_set):
    if len(word)>3:
        word_set.add(word)


import string
def process_line(line, word_set):
    line = line.strip()
    word_list = line.split()
    for word in word_list:
        if word != '--':
            word = word.strip()
            # get commas , peri ods and other punctuation out as well
            word = word.strip(string.punctuation)
            word = word.lower()
            add_word(word, word_set)


def pretty_print(ga_set, doi_set):
    print('Count of unique words of length 4 or greater')
    print('Gettysburg Addr: {}, Decl of Ind: {}\n'.format(len(ga_set),len(doi_set)))
    print('{:15s} {:15s}'.format('Operation', 'Count'))
    print('-'*35)
    print('{:15s} {:15d}'.format('Union', len(ga_set.union(doi_set))))
    print('{:15s} {:15d}'.format('Intersection', len(ga_set.intersection(doi_set))))
    print('{:15s} {:15d}'.format('Sym Diff', len(ga_set.symmetric_difference(doi_set))))
    print('{:15s} {:15d}'.format('GA-DoI', len(ga_set.difference(doi_set))))
    print('{:15s} {:15d}'.format('DoI-GA', len(doi_set.difference(ga_set))))


    intersection_set = ga_set.intersection(doi_set)
    word_list = list(intersection_set)
    word_list.sort()
    print('\n Common words to both')
    print('-'*20)
    count = 0
    for w in word_list:
        if count % 5 == 0:
            print()
        print('{:13s}'.format(w), end=' ')
        count += 1
