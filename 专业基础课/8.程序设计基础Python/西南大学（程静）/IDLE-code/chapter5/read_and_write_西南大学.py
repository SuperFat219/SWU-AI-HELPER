input_file = open("西南大学_1.txt","r")
output_file = open("西南_replace.txt","w")
for line in input_file:
    new_line = line.replace("西南","swu")
    print(new_line,file=output_file)
    print(new_line)
    print()

    
input_file.close()
output_file.close()
