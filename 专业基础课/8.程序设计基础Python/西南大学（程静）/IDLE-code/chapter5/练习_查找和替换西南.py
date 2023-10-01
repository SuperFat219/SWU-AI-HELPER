input_file = open("西南大学.txt","r")
output_file = open("SWU.txt","w")

for line in input_file:
    print(line,end="####\n")
    new_line = line.replace("西南","SWU")
    print(new_line, file=output_file)
    
input_file.close()
output_file.close()

