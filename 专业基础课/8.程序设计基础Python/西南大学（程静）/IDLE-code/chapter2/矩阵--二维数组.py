#将已知矩阵matrix打印输出
matrix = [[1,2,3],[4,5,6]]
print("按照列表打印！",matrix)
row = len(matrix)
col = len(matrix[0])
print("\n\n行为:",row,"，列为:",col)
print("按照矩阵打印！")
for i in range(row):
    for j in range(col):
        print(matrix[i][j], end=" ")
    print()


print("\n\n求矩阵的每行的和！")
for i in range(row):
    s = 0
    for j in range(col):
        s = s+matrix[i][j]
    print("第{}行的和为：{}".format(i+1,s))


print("\n\n求矩阵的每列的和！")
for j in range(col):
    s = 0
    for i in range(row):
        s = s+matrix[i][j]
    print("第{}列的和为：{}".format(j+1,s))


print("\n\n求矩阵的所有元素的和！")
s = 0
for i in range(row):
    for j in range(col):
        s = s+matrix[i][j]
print("矩阵的所有元素的和为：{}".format(s))


#从键盘上输入若干数字，转换放入矩阵
nrow,ncol = 3,4
B=[]
print("\n\n我将从键盘上输入矩阵B({}×{})!".format(nrow,ncol))
for i in range(nrow):
     a_row = input("请输入矩阵的第{}行:".format(i))
     print(a_row)
     ls = a_row.split()
     print(ls)
     ls_num = []
     for elem in ls:
         elem_num = float(elem)
         ls_num.append(elem_num)
     B.append(ls_num)

#将矩阵B输出
print("\n\n将矩阵B输出!")
for i in range(3):
    for j in range(4):
        print(B[i][j], end=" ")
    print()

A = matrix 
# 两个矩阵A(m,n)●B(m,n)

# 两个矩阵A(m,n)×B(n,k)

