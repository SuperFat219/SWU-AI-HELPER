def calc_efficiency (line_str, the_dict):
    """Calculate player efficiency."""
    fields_list = line_str.split(',')
    first_name = fields_list[1]
    last_name = fields_list[2]

    # mapping fields in a line to their particular variable.
    # league is a str, everything else an int
    leag,gp,mins,pts,oreb,dreb,reb,asts,stl,blk,to,pf,fga,fgm,fta,ftm,tpa,tpm = \
     fields_list[3],int(fields_list[4]),int(fields_list[5]),int(fields_list[6]),\
     int(fields_list[7]),int(fields_list[8]),int(fields_list[9]),int(fields_list[10]),\
     int(fields_list[11]),int(fields_list[12]),int(fields_list[13]),\
     int(fields_list[14]),int(fields_list[15]),int(fields_list[16]),\
     int(fields_list[17]),int(fields_list[18]), int(fields_list[19]),\
     int(fields_list[20])

    # calculate the player's efficiency
    efficiency = ((pts+reb+asts+stl+blk)-((fga-fgm)+(fta-ftm)+to))/gp

    the_dict[last_name+first_name] = {'first':first_name, 'last':last_name, \
                            'league':leag,'mins':mins,'gp':gp,'pts':pts,\
                            'oreb':oreb,'dreb':dreb,'reb':reb,'asts':asts,\
                            'stl':stl,'blk':blk,'to':to,'fga':fga,'fgm':fgm,\
                            'fta':fta,'ftm':ftm,'tpa':tpa,'tpm':tpm,\
                            'efficiency':efficiency}

def find_most_efficient(the_dict,how_many):
    '''return list of tuples(efficiency, name) from dictionary
    how_many is number of tuples to gather'''
    # user must implement
    return []

def print_results(lst):
    ''' pretty print the results '''
    print('The top {} players in efficency are'.format(len(lst)))
    print('*'*20)

# main program as a function
def main (file_name):
    nba_file = open(file_name)
    nba_dict={}
    for line_str in nba_file:
        calc_efficiency(line_str,nba_dict)
    results_list = find_most_efficient(nba_dict,20)
    print_results(results_list)
    nba_file.close()
