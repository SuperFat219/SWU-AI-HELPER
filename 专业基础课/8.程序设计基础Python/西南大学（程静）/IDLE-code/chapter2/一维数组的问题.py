vector = [1,2,4,5]
print(vector)
print("向量的长度：",len(vector))

count=0
for i in vector:
    count = count+1
print("向量的长度2：",count)

print("\n计算两个向量的积！")
vec_mul_fac_1 = [2,3,4,5,6]
vec_mul_fac_2 = [10,20,30,40,50]
N = len(vec_mul_fac_1)
s = 0
for i in range(N):
    s = s + vec_mul_fac_1[i]*vec_mul_fac_2[i]

print("两个向量的积为：",s)
