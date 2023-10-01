in_out_file = open("input.txt","r+")
i = 1
for line in in_out_file:
    print(line,end="")
    print(i*1111,file=in_out_file)
    i = i+1

    
in_out_file.close()
