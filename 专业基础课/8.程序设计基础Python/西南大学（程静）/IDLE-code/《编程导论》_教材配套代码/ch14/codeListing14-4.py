#  search for a string:
#  starting from the current directory, walk a directory tree
#  look in all text files (extension ".txt") for the string

import os

# walk the directory subtree starting at the current directory
# search for search_str, count files examined,                                 
# keep lists of files found and directories
def check(search_str,count,files_found_list,dirs_found_list):
    for dirname,dir_list,file_list in os.walk("."):   # walk the subtree
        for f in file_list:                       
            if os.path.splitext(f)[1] == ".txt":  # if it is a text file
                count = count + 1            # add to count of files examined
                a_file = open(os.path.join(dirname,f),'r')  # open text file
                file_str = a_file.read()          # read whole file into string
                if search_str in file_str:              # is search_str in file?
                    filename = os.path.join(dirname,f)# if so, create path for file
                    files_found_list.append(filename)         # and add to file list
                    if dirname not in dirs_found_list:        # if directory is not 
                        dirs_found_list.append(dirname)       # and directory list
                a_file.close()
    return count

search_str = input('What string to look for: ')
file_list = []    # list of files containing string
dir_list = []     # list of directories of files containing string
count = 0         # count of text files examined

# call our function that examines directory tree for string
count = check(search_str,count,file_list,dir_list)

print('Looked at {} text files'.format(count))
print('Found {} directories containing ".txt" files and target string:{}'.\
       format(len(dir_list),search_str))
print('Found {} files ".txt" files containing the target string: {}'.\
       format(len(file_list),search_str))
print('\n*****Directory List*****')
for a_dir in dir_list:
    print(a_dir)

print('\n-----File List-----')
for a_file in file_list:  
    print(a_file)
