def main ():
    '''Get a line_str from the file, print the final grade nicely.'''
    file_name = input('Open what file:')
    grade_file = open(file_name, 'r')
    print('{:>13s}  {:>15s}'.format('Name','Grade'))
    print('-'*30)
    for line_str in grade_file:
        name_str,score_list = parse_line(line_str)
        grade_float = weighted_grade(score_list)
        print('{:>15s} {:14.2f} '.format(name_str, grade_float))
