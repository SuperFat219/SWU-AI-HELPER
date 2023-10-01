import numpy as np
import pandas as pd
# import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, MinMaxScaler

# 读取数据
f = open(r'D:\desktop\vehicle.dat', encoding='utf-8')
sentimentlist = []
count = 0
for line in f:
    # print(line)
    if count <= 22:
        count += 1
        continue
    s = line.strip().split(', ')
    sentimentlist.append(s)
    count += 1
f.close()
vehicle_data = pd.DataFrame(sentimentlist,
                            columns=['Compactness', 'Circularity', 'Distance_circularity', 'Radius_ratio',
                                     'Praxis_aspect_ratio', 'Praxis_aspect_ratio', 'Max_length_aspect_ratio',
                                     'Scatter_ratio',
                                     'Elongatedness', 'Praxis_rectangular', 'Length_rectangular', 'Major_variance',
                                     'Minor_variance',
                                     'Gyration_radius', 'Major_skewness', 'Minor_skewness', 'Minor_kurtosis',
                                     'Hollows_ratio', 'Class'])
Class = vehicle_data[['Class']]
del vehicle_data['Class']
vehicle_data=MinMaxScaler().fit(vehicle_data).transform(vehicle_data)
# vehicle_data=StandardScaler().fit(vehicle_data).transform(vehicle_data)
Class_num = Class.copy(deep=True)  # 标签数值化
uniq_list = list(np.unique(Class))
for i in range(len(Class)):
    for j in range(4):
        if Class_num.iloc[i].item() == uniq_list[j]:
            Class_num.loc[i] = j
# 对dataframe里的数据类型进行修改
Class_num = np.array(Class_num, dtype=np.intc)
Class_num = pd.DataFrame(Class_num, columns=['Class'])
vehicle_data = np.array(vehicle_data, dtype=np.float)
vehicle_data = pd.DataFrame(vehicle_data, columns=['Compactness', 'Circularity', 'Distance_circularity', 'Radius_ratio',
                                                   'Praxis_aspect_ratio', 'Praxis_aspect_ratio',
                                                   'Max_length_aspect_ratio', 'Scatter_ratio',
                                                   'Elongatedness', 'Praxis_rectangular', 'Length_rectangular',
                                                   'Major_variance', 'Minor_variance',
                                                   'Gyration_radius', 'Major_skewness', 'Minor_skewness',
                                                   'Minor_kurtosis',
                                                   'Hollows_ratio'])

X_train, X_test, y_train, y_test = train_test_split(vehicle_data, Class_num, test_size=0.2)


class Layer:
    def __init__(self, n_input, n_output, activation=None, weights=None, bias=None):
        self.activation = activation
        self.weights = weights if weights is not None else np.random.randn(n_input, n_output) * np.sqrt(1 / n_output)
        self.bias = bias if bias is not None else np.random.rand(n_output) * 0.1
        self.activation_output = None

    def forward(self, x_input):
        r = np.dot(x_input, self.weights) - self.bias  # 向量点积，结果为output维数
        self.activation_output = self.apply_activation(r)
        return self.activation_output

    def apply_activation(self, r):
        if self.activation is None:
            return r
        elif self.activation == 'relu':
            return np.maximum(r, 0)
        elif self.activation == 'sigmoid':
            # l = len(r)
            # y = []
            # for i in range(l):
            #     if r[i] >= 0:
            #         y.append(1.0 / (1 + np.exp(-r[i])))
            #     else:
            #         y.append(np.exp(r[i]) / (np.exp(r[i]) + 1))
            # y=np.array(y)
            # return y
            x_ravel = r.ravel()  # 将numpy数组展平
            length = len(x_ravel)
            y = []
            for index in range(length):
                if x_ravel[index] >= 0:
                    y.append(1.0 / (1 + np.exp(-x_ravel[index])))
                else:
                    y.append(np.exp(x_ravel[index]) / (np.exp(x_ravel[index]) + 1))
            return np.array(y).reshape(r.shape)
            # TODO:sigmoid函数的优化
            # return 1 / 1 + np.exp(-r)

    def apply_activation_derivative(self, r):
        if self.activation is None:
            return np.ones_like(r)
        elif self.activation == 'relu':
            grad = np.array(r, copy=True)
            grad[r > 0] = 1.
            grad[r <= 0] = 0.
            return grad
        elif self.activation == 'sigmoid':
            return r * (1 - r)
        return r


class Network:
    def __init__(self):
        self.layers = []

    def add_layer(self, layer):
        self.layers.append(layer)

    def feed_forward(self, x_input):
        # 前向传播
        for layer in self.layers:
            x_input = layer.forward(x_input)
        return x_input

    def backward(self, X, y, learning_rate):
        # 反向传播
        output = self.feed_forward(X)
        g = output * (1 - output) * (y - output)  # g.size=[n_output,1]
        for i in reversed(range(len(self.layers))):
            layer = self.layers[i]
            if layer == self.layers[-1]:  # 输出层
                last_layer = self.layers[i - 1]
                # print(len(last_layer.activation_output))
                delta_weight = [[] for k in range(len(last_layer.activation_output))]
                for h in range(len(last_layer.activation_output)):
                    for j in range(len(layer.activation_output)):
                        delta_weight[h].append(learning_rate * g[j] * last_layer.activation_output[h])
                delta_weight = np.array(delta_weight)
                # delta_weight=learning_rate*g*last_layer.activation_output
                layer.weights = layer.weights + delta_weight
                delta_bias = -learning_rate * g
                layer.bias = layer.bias + delta_bias
            else:
                next_layer = self.layers[i + 1]
                if i + 1 == len(self.layers) - 1:  # 输出层前一隐藏层
                    last_layer = self.layers[i - 1]
                    delta_weight = [[] for k in range(len(last_layer.activation_output))]
                    for k in range(len(last_layer.activation_output)):
                        for h in range(len(layer.activation_output)):
                            sum = 0
                            for j in range(len(next_layer.activation_output)):
                                sum += next_layer.weights[h][j] * g[j] * layer.activation_output[h] * (
                                        1 - layer.activation_output[h]) * last_layer.activation_output[i]
                            delta_weight[k].append(sum)
                    delta_weight = np.array(delta_weight)
                    layer.weights += delta_weight * learning_rate
                    delta_bias = []
                    for h in range(len(layer.activation_output)):
                        sum = 0
                        for j in range(len(next_layer.activation_output)):
                            sum += next_layer.weights[h][j] * g[j] * layer.activation_output[h] * (
                                    1 - layer.activation_output[h]) * (-1)
                        delta_bias.append(sum)
                    delta_bias = np.array(delta_bias)
                    layer.bias += delta_bias
                    # last_layer = self.layers[i - 1]
                    # e = layer.activation_output * (1 - layer.activation_output) * np.sum(next_layer.weights * g, axis=1)
                    # delta_weight = [[] for k in range(len(last_layer.activation_output))]
                    # for j in range(len(last_layer.activation_output)):
                    #     for h in range(len(layer.activation_output)):
                    #         delta_weight[j].append(learning_rate * e[h] * last_layer.activation_output[j])
                    # delta_weight = np.array(delta_weight)
                    # layer.weights = layer.weights + delta_weight
                    # delta_bias = -learning_rate * e
                    # layer.bias = layer.bias + delta_bias

                # elif i == 0:  # 输入层后隐藏层
                else:
                    output_layer = self.layers[-1]
                    # TODO:隐藏层1的权重更新也要变化
                    # e = layer.activation_output * (1 - layer.activation_output) * np.sum(next_layer.weights * g, axis=1)
                    # delta_weight = [[] for k in range(len(X))]
                    # for j in range(len(X)):
                    #     for h in range(len(layer.activation_output)):
                    #         delta_weight[j].append(learning_rate * e[h] * X[j])
                    # delta_weight = np.array(delta_weight)
                    # layer.weights = layer.weights + delta_weight
                    # delta_bias = -learning_rate * e
                    # layer.bias = layer.bias + delta_bias
                    delta_weight = [[] for i in range(len(X))]
                    for t in range(len(X)):
                        for k in range(len(layer.activation_output)):
                            sum = 0
                            for h in range(len(next_layer.activation_output)):
                                for j in range(len(y)):
                                    sum += output_layer.weights[h][j] * g[j] * next_layer.activation_output[h] * (
                                            1 - next_layer.activation_output[h]) * (next_layer.weights[k][h]) * \
                                           layer.activation_output[k] * (1 - layer.activation_output[k]) * X[t]
                            delta_weight[t].append(sum)
                    delta_weight = np.array(delta_weight)*learning_rate
                    layer.weights += delta_weight
                    delta_bias = []
                    for k in range(len(layer.activation_output)):
                        sum = 0
                        for h in range(len(next_layer.activation_output)):
                            for j in range(len(y)):
                                sum += output_layer.weights[h][j] * g[j] * next_layer.activation_output[h] * (
                                        1 - next_layer.activation_output[h]) * (next_layer.weights[k][h]) * \
                                       layer.activation_output[k] * (1 - layer.activation_output[k]) * (-1)
                        delta_bias.append(sum)
                    delta_bias = np.array(delta_bias)*learning_rate
                    layer.bias = layer.bias + delta_bias

                # else:
                #     last_layer = self.layers[i - 1]
                #     delta_weight = [[] for k in range(len(last_layer.activation_output))]
                #     for k in range(len(last_layer.activation_output)):
                #         for h in range(len(layer.activation_output)):
                #             sum = 0
                #             for j in range(len(next_layer.activation_output)):
                #                 sum += next_layer.weights[h][j] * g[j] * layer.activation_output[h] * (
                #                         1 - layer.activation_output[h]) * last_layer.activation_output[i]
                #             delta_weight[k].append(sum)
                #     delta_weight = np.array(delta_weight)
                #     layer.weights += delta_weight * learning_rate
                #     delta_bias = []
                #     for h in range(len(layer.activation_output)):
                #         sum = 0
                #         for j in range(len(next_layer.activation_output)):
                #             sum += next_layer.weights[h][j] * g[j] * layer.activation_output[h] * (
                #                     1 - layer.activation_output[h]) * (-1)
                #         delta_bias.append(sum)
                #     delta_bias = np.array(delta_bias)
                #     layer.bias += delta_bias
            # print(layer.weights)

    def train(self, X_train, X_test, y_train, y_test, learning_rate, max_epochs):
        # 对标签进行one-hot编码
        y_onehot = np.zeros((y_train.shape[0], 4))
        y_onehot[np.arange(y_train.shape[0]), np.array(y_train).flatten()] = 1
        mses = []
        for epoch in range(max_epochs):
            for x in range(len(X_train)):
                self.backward(np.array(X_train.iloc[x]), y_onehot[x], learning_rate)
                mse = np.mean(np.square(y_onehot - self.feed_forward(X_train)))
                mses.append(mse)
            print('Epoch: #%s, MSE: %f, Accuracy: %f%%' %
                  (
                      epoch + 1, float(mses[epoch]),
                      self.accuracy(self.predict(X_test), np.array(y_test).flatten()) * 100))

    def accuracy(self, y_predict, y_test):  # 计算准确度
        return np.sum(y_predict == y_test) / len(y_test)

    def predict(self, X_predict):
        y_predict = self.feed_forward(X_predict)
        y_predict = np.argmax(y_predict, axis=1)
        return y_predict



nn = Network()  # 实例化网络类
nn.add_layer(Layer(18, 4, 'relu'))  # 隐藏层 1, 2=>25
nn.add_layer(Layer(4, 4, 'relu'))  # 隐藏层 2, 25=>50
nn.add_layer(Layer(4, 4, 'sigmoid'))  # 输出层, 25=>2
nn.train(X_train, X_test, y_train, y_test, learning_rate=3e-4, max_epochs=50)
# TODO:k折交叉验证