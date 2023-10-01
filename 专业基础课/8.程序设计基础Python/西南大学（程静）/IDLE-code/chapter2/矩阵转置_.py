matrix =[[1,2,3],[4,5,6]]
row = len(matrix)
col = len(matrix[0])
B=[]
for i in range(col):
    ls = []
    for j in range(row):
        ls.append(0)
    B.append(ls) 
print(B)
print()


for i in range(row):
    for j in range(col):
        B[j][i] = matrix[i][j]
        #print(i,j,matrix[i][j],"   :",B)

print(B)
print()
