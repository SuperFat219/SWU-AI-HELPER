matrix =[[1,2,3],[4,5,6]]
row = len(matrix)
col = len(matrix[0])
B=[]
for i in range(col):
    ls_row = []
    for j in range(row):
        ls_row = ls_row.append([1])
    B.append(ls_row) 

#Tls_row = [0]*row
#B = [[0,0],[0,0],[0,0]]
#for i in range(col):
#    B.append(Tls_row)
print(B)
print()


for i in range(1):
    for j in range(col):
        B[j][i] = matrix[i][j]
        print(i,j,matrix[i][j],"   :",B)
    #print("#",i,"  :",B)
    print()

