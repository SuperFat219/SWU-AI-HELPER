# reverse each line of the input file in the output file

input_file = open("input.txt", "r")
output_file = open("output.txt", "w")

for line_str in input_file:
    new_str = ''
    line_str = line_str.strip()       # get rid of carriage return
    for char in line_str:
        new_str = char + new_str      # concat at the left (reverse)
    print(new_str,file=output_file)   # print to output_file
    
    # include a print to shell so we can observe progress
    print('Line: {:12s} reversed is: {:s}'.format(line_str, new_str))
input_file.close()
output_file.close()
