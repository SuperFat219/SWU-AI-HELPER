import numpy as np
import random

class KMeans:
    def __init__(self, n_clusters=3, random_state=0):
        assert n_clusters >=1, " must be valid"
        self._n_clusters = n_clusters
        self._random_state = random_state
        self._X = None
        self._center = None
        self.cluster_centers_ = None
        
    def distance(self, M, N):
        return (np.sum((M - N) ** 2, axis = 1))** 0.5
    
    def _generate_labels(self, center, X):
        return np.array([np.argmin(self.distance(center, item)) for item in X])

    def _generate_centers(self, labels, X):
        return np.array([np.average(X[labels == i], axis=0) for i in np.arange(self._n_clusters)])

    def fit_predict(self, X):
        k = self._n_clusters
        if self._random_state:
            random.seed(self._random_state)
        center_index = [random.randint(0, X.shape[0]) for i in np.arange(k)]
        center = X[center_index]
        n_iters = 1e3
        while n_iters > 0:
            last_center = center
            labels = self._generate_labels(last_center, X)
            self.labels_ = labels
            center = self._generate_centers(labels, X)
            self.cluster_centers_ = center
            if (last_center == center).all():
                self.labels_ = self._generate_labels(center, X)
                break
            n_iters = n_iters - 1
        sses = []
        for i in range(len(X)):
            sses.append(((X[i] - center[labels[i]])**2).sum())
        sses = np.array(sses)
        self.sse = sses.sum()
        return self
