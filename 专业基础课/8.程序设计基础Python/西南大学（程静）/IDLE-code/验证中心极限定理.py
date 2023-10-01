import numpy as np 
import matplotlib.pyplot as plt

data = np.random.randint(1, 7,100000)
#print(data)
#sns.distplot(data)
print(data.mean())# 打印平均值
print(data.std())# 打印标准差

#----从data中随机抽取一组数------------
sample1 = []
for i in range(0,10):
    rnd = int(np.random.random()*len(data))#0~99999的随机数生产
    sample1.append(data[rnd])
print("\n\n",sample1)
sample1_np = np.array(sample1)
print("一个samle的平均值：",sample1_np.mean())
print("一个samle的标准差：",sample1_np.std())

#---现在抽取N=1000组，每组n=50个----------
#---中心极限定理的内容是：不论总体的分布是什么，只有抽取足够多的组(N=1000)的样本，每组样本
#---的个数够大（n>=30)，则各组样本的平均数的分布呈正态分布，且分布的均值为总体均值μ，
#---分布的标准差为总体方差为σ^2/n，总体的标准差为σ/sqrt(n)
sample_mean = []  # 平均值
sample_std = []  # 标准差
for i in range(0,1000):  # 对1000次采样，1000组样本
    sample = []
    for j in range(0,50):
        sample.append( data[int(np.random.random() * len(data))])
        #得到一组n=50个样本
 
    sample_mean.append(np.array(sample).mean())
    sample_std.append(np.array(sample).std())

# ----以数据频率分布作直方图------
plt.hist(sample_mean)  
plt.show()
plt.hist(sample_std)  
plt.show()

 
print("\n\n1000组样本均值分布的均值：",np.array(sample_mean).mean())
print("1000组样本均值分布的标准差：",np.array(sample_std).std())
