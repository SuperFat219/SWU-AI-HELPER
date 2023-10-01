def parse_line(line_str):
    '''Expects a line of form last, first, exam1, exam2, final.
    returns a tuple containing first+last and list of scores.'''
    field_list = line_str.strip().split(',')
    name_str = field_list[1] + ' ' + field_list[0]
    score_list = []
    # gather the scores, now strings, as a list of ints
    for element in field_list[2:]:
        score_list.append(int(element))
    return name_str,score_list

