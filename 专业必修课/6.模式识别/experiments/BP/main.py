import numpy as np
import matplotlib.pyplot as plt
import torchvision.datasets as dataset
from tqdm import tqdm


def fashion_mnist_dataset(download=False):
    """
    dataset preparation
    :param download: whether to download to lacally or not
    :return: Fashion MNIST dataset (PIL Image)
    """
    # train_dataset = dataset.MNIST(root="mnist", train=True, transform=transforms.ToTensor(), download=download)
    # test_dataset = dataset.MNIST(root="mnist", train=False, transform=transforms.ToTensor(), download=download)
    train_dataset = dataset.FashionMNIST(root="fashion_mnist", train=True, download=download)
    test_dataset = dataset.FashionMNIST(root="fashion_mnist", train=False, download=download)
    return train_dataset, test_dataset


class Layer:
    def __init__(self, n_input, n_output, activation=None, weights=None, bias=None):
        self.activation = activation
        self.weights = weights if weights is not None else np.random.randn(n_input, n_output) * np.sqrt(1 / n_output)
        self.bias = bias if bias is not None else np.random.rand(n_output) * 0.1
        self.activation_output = None

    def forward(self, x_input):
        r = np.dot(x_input, self.weights) - self.bias
        self.activation_output = self.apply_activation(r)
        return self.activation_output

    def apply_activation(self, r):
        if self.activation is None:
            return r
        elif self.activation == 'relu':
            return np.maximum(r, 0)
        elif self.activation == 'sigmoid':
            x_ravel = r.ravel()
            length = len(x_ravel)
            y = []
            for index in range(length):
                if x_ravel[index] >= 0:
                    y.append(1.0 / (1 + np.exp(-x_ravel[index])))
                else:
                    y.append(np.exp(x_ravel[index]) / (np.exp(x_ravel[index]) + 1))
            return np.array(y).reshape(r.shape)

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
    def __init__(self, train_dataset=None, test_dataset=None, train_bs=16, test_bs=1, init_lr=0.0002):
        self.train_batch_size = train_bs
        self.test_batch_size = test_bs
        self.learning_rate = init_lr
        self.layers = []
        self.classNum = len(train_dataset.classes)
        self.labels = [i for i in range(self.classNum)]
        self.train_dataset = train_dataset
        self.test_dataset = test_dataset
        self.trainData = []
        self.trainLabel = []
        self.testData = []
        self.testLabel = []

        # for k in range(len(self.train_dataset)):
        for k in range(500):
            image, label = train_dataset[k]
            image = np.array(image).flatten() / 255
            self.trainData.append(image)
            self.trainLabel.append(label)

        # for k in range(len(self.test_dataset)):
        for k in range(100):
            image, label = test_dataset[k]
            image = np.array(image).flatten() / 255
            self.testData.append(image)
            self.testLabel.append(label)

    def add_layer(self, layer):
        self.layers.append(layer)

    def forward(self, x_input):
        for layer in self.layers:
            x_input = layer.forward(x_input)
        return x_input

    def backward(self, x_input, y, learning_rate):
        """
        back propagation
        :param x_input:
        :param y:
        :param learning_rate:
        :return:
        """
        output = self.forward(x_input)
        g = output * (1 - output) * (y - output)  # g.size=[n_output,1]
        for i in reversed(range(len(self.layers))):
            layer = self.layers[i]
            if layer == self.layers[-1]:  # output layer
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
                if i + 1 == len(self.layers) - 1:  # hidder layer 2
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
                else:  # hidden layer 1
                    output_layer = self.layers[-1]
                    delta_weight = [[] for i in range(len(x_input))]
                    for t in range(len(x_input)):
                        for k in range(len(layer.activation_output)):
                            sum = 0
                            for h in range(len(next_layer.activation_output)):
                                for j in range(len(y)):
                                    sum += output_layer.weights[h][j] * g[j] * next_layer.activation_output[h] * (
                                            1 - next_layer.activation_output[h]) * (next_layer.weights[k][h]) * \
                                           layer.activation_output[k] * (1 - layer.activation_output[k]) * x_input[t]
                            delta_weight[t].append(sum)
                    delta_weight = np.array(delta_weight) * learning_rate
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
                    delta_bias = np.array(delta_bias) * learning_rate
                    layer.bias = layer.bias + delta_bias

    def train(self, learning_rate=0.0002, max_epochs=200):
        y_onehot = np.zeros((len(self.trainData), 10))  # one-hot encoding
        y_onehot[np.arange(len(self.trainData)), np.array(self.trainLabel).flatten()] = 1

        for epoch in tqdm(range(max_epochs)):
            count = 0
            for i in tqdm(range(1, len(self.trainData) // self.train_batch_size + 1)):
                for k in range(count, count + self.train_batch_size):
                    self.backward(np.array(self.trainData[k]), y_onehot[k], learning_rate)
                count += self.train_batch_size
                # print("batch {}".format(i))
                i += 1
            test_count = 0
            for i in range(len(self.testData)):
                y_label = np.argmax(self.forward(self.testData[i]))
                if y_label == self.testLabel[i]:
                    test_count += 1
                # mse = np.mean(np.square(y_label - self.testLabel[i]))

            print('Epoch: #%s, Accuracy: %.2f' % (epoch + 1, test_count / len(self.testData)))
            # self.accuracy(self.predict(X_test),
            #               np.array(y_test).flatten()) * 100))

    # def test(self):
    #     mses = []
    #     for i in range(len(self.testData)):
    #         y_label = np.argmax(self.forward(self.testData[i]), axis=1)
    #         mse = np.mean(np.square(y_label - self.testLabel[i]))
    #         mses.append(mse)

    # def accuracy(self, y_predict, y_test):
    #     return np.sum(y_predict == y_test) / len(y_test)

    def predict(self, x):
        y_predict = self.forward(x)
        y_predict = np.argmax(y_predict, axis=1)
        return y_predict


if __name__ == '__main__':
    train_dataset, test_dataset = fashion_mnist_dataset(download=False)

    network = Network(train_dataset=train_dataset, test_dataset=test_dataset, train_bs=2, test_bs=1, init_lr=0.0002)
    network.add_layer(Layer(28 * 28, 15, 'relu'))
    network.add_layer(Layer(15, 10, 'relu'))
    network.add_layer(Layer(10, 10, 'sigmoid'))
    network.train(max_epochs=10)
