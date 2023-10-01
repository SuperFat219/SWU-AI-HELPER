import numpy as np
import matplotlib.pyplot as plt
from kmeans import KMeans

from sklearn.datasets import load_iris
iris = load_iris()

X = iris.data
X = X[:, 2:]
y = iris.target
sses=[]
for i in range(1,8):
    clf = KMeans(n_clusters=i)
    s = clf.fit_predict(X)
    sses.append(clf.sse)
# print(sses)
plt.plot(range(1,8),sses,label='k-means',marker='o')
plt.xlabel('n_clusters')
plt.ylabel('SSE')
plt.show()

# plt.figure(figsize=(16, 8))
# plt.scatter(X[:, 0], X[:, 1], c=clf.labels_)
# plt.grid()
# # plt.xlim(0, 7)
# # plt.ylim(0, 7)
# center = clf.cluster_centers_
# plt.scatter(center[:, 0], center[:, 1], marker="*", s=200)
# # plt.show()
# plt.savefig('kmeans.png')
