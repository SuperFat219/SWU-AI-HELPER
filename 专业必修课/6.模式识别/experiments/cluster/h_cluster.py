
### 1. loading data

# 导入程序包
import pandas as pd
import numpy as np
from sklearn.datasets import load_iris
import matplotlib.pyplot as plt
# 加载数据集
iris = load_iris()
# 获取特征
X = iris.data
# 获取特征名
features = iris.feature_names
# 获取标签
y = iris.target
# 获取标签名
target_names = iris.target_names
# 查看数据
df = pd.DataFrame(X, columns=features)
df['label'] = y


### 2. 距离函数

# 定义欧式距离
def euclidean_distance(x1, x2):
    dist = np.sqrt(np.sum((x1 - x2) ** 2))
    return dist
 
### 3. 聚类函数

# 聚类函数
def hierarchical_clustering(X, n_clusters):
    # 初始化簇和聚类中心
    clusters = [[i] for i in range(len(X))]
    centroids = np.array(X)
 
    # 迭代
    while len(clusters) > n_clusters:
        # 计算距离矩阵
        dist_matrix = np.zeros((len(clusters), len(clusters)))
        for i in range(len(clusters)):
            for j in range(i+1, len(clusters)):
                dist_matrix[i][j] = euclidean_distance(centroids[i], centroids[j])
                dist_matrix[j][i] = dist_matrix[i][j]
 
        # 找出最短距离
        min_dist = 10000
        x = 0
        y = 0
        for i in range(len(clusters)):
            for j in range(i+1,len(clusters)):
                if 	dist_matrix[i][j] < min_dist:
                    x = i
                    y = j
                    min_dist = dist_matrix[i][j]
                
        # min_dist = np.min(dist_matrix)
        # min_dist_index = np.where(dist_matrix == min_dist)
        # min_dist_index = np.array(min_dist_index).tolist()
        # y = min_dist_index[0]
        # x = min_dist_index[1]
 
        # 合并簇
        # clusters = np.array(clusters).astype(int)
        # clusters = [list(c) for c in clusters]
        clusters[x].extend(clusters[y])
        del clusters[y]
 
        # 更新聚类中心
        centroid_x = np.mean(X[clusters[x]], axis=0)
        centroids[x] = centroid_x
        centroids = list(centroids)
        del centroids[y]
        centroids = np.array(centroids)
 
    return clusters, centroids 
 
### 4. 结果可视化
# 聚类
clusters, centroids = hierarchical_clustering(X, 3)
print(clusters)
# 将聚类结果添加到数据框中
df['cluster'] = 0
for i in range(len(clusters)):
    for j in range(len(clusters[i])):
        df.loc[clusters[i][j], 'cluster'] = i
# 可视化
markers = ['o', '^', '*']
colors = ['r', 'g', 'b']
plt.figure()
for i in range(len(clusters)):
    x = df[df['cluster'] == i]
    plt.scatter(x['sepal length (cm)'], x['sepal width (cm)'], marker=markers[i], c=colors[i])
plt.title('cluster results')
plt.xlabel('sepal length (cm)')
plt.ylabel('sepal width (cm)')
plt.show()


# 层次聚类树
from scipy.cluster.hierarchy import dendrogram, linkage
# 使用ward方法计算距离矩阵
Z = linkage(X, 'ward')
# 画出聚类树
plt.figure(figsize=(20, 5))
dendrogram(Z, labels=y, leaf_rotation=90, leaf_font_size=8)
plt.title('Cluster Tree')
plt.show()

