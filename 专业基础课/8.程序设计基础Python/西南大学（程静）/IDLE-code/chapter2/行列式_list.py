def yuzishi(A,k,s=0): #A是二维矩阵,k是列号，s是行号，缺省值为0
    n = len(A)
    B = []
    for i in range(1,n):  # i是行下标（1:n-1),s=0
        ls = []
        for j in range(0,n):  #j是列下标
            if (j!=k):
               ls.append(A[i][j])
               #print("@@@:",ls)
        B.append(ls)
    print("\n::::::B matrix::::::",B)
    return B
        
def print_matrix(A):
    row = len(A)
    col = len(A[0])
    for i in range(row):
        for j in range(col):
            print(A[i][j], end=" ")
        print()


def hanglieshi(A):
    n = len(A)
    print("调用行列式，n=",n)
    if (n==1):
        return A[0][0]
    elif (n==2):
        re = A[0][0]*A[1][1]-A[0][1]*A[1][0]
        return re
    else:
        re = 0
        for j in range(0,n):
            M = yuzishi(A,j)
            print("M is",M,"length is:",len(M))         
            x = hanglieshi(M)
            term = ((-1)**j) * A[0][j]*x
            re = re + term
            print("j=",j,"x=",x,",term=",term,"re=",re)            
        return re



#n = int(input("请输入行列式的行数："))      
matrix = [[1,2,3,4],[5,6,17,8],[9,20,11,12],[13,14,15,16]]

print_matrix(matrix)

print("\n\n行列式的值：",hanglieshi(matrix))
    

        

