
def main (file_name):
    '''
    >>> main('')
    File named  not found
    >>> main('x')
    Traceback (most recent call last):
        ...
        raise IOError('bad file format.')
    IOError: bad file format.
    >>> main('player_career.csv')
    The top 10 players in efficency are
    ********************
             Wilt Chamberlain : 41.50
                 Bill Russell : 31.71
              Oscar Robertson : 31.61
                   Bob Pettit : 31.11
          Kareem Abdul-jabbar : 30.93
                   Larry Bird : 29.77
                 Elgin Baylor : 29.74
               Michael Jordan : 29.19
                Magic Johnson : 29.10
              Charles Barkley : 28.16
    '''
    try:
        nba_file = open(file_name)
    except IOError:
        print('File named {} not found'.format(file_name))
    else:
        nba_dict={}
        line_str = nba_file.readline()
        if line_str[0:5]!='ilkid':
            raise IOError('bad file format.')       
        for line_str in nba_file:
            calc_efficiency(line_str,nba_dict)

        results_list = find_most_efficient(nba_dict,10)
        print_results(results_list)
        nba_file.close()

if __name__ == '__main__':
    import doctest
    doctest.testmod()
