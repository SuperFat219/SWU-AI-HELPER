import sys
import numpy as np
from torch.utils.data import DataLoader
from tqdm import tqdm
from SMO import SMO
import pickle

from dataloader import mnist_dataset


class SVM:
    def __init__(self, train_dataset, test_dataset, C=0, toler=0, maxIter=0, batch_size=16, **kernelargs):
        self.classNum = len(train_dataset.classes)
        self.labels = [i for i in range(self.classNum)]
        self.classifierNum = (self.classNum * (self.classNum - 1)) / 2
        self.classifiers = []
        self.dataSet = [[[] for _ in range(self.classNum)] for k in range(len(train_dataset) // batch_size)]
        self.C = C
        self.toler = toler
        self.maxIter = maxIter
        self.kernelargs = kernelargs
        self.train_dataset = train_dataset
        self.test_dataset = test_dataset

        for batch in range(len(self.dataSet)):
            count = 0
            for i in range(count, count + batch_size):
                image, label = self.train_dataset[i]
                image = np.array(image).flatten()
                self.dataSet[batch][label].append(image)
            count += batch_size
        print(len(self.dataSet))

    def train(self):
        # train n * (n-1) classifiers
        for batch in range(len(self.dataSet)):
            print("batch {}:".format(batch))
            for i in range(self.classNum):
                for j in range(i + 1, self.classNum):
                    # print("train classifier {} and {}.".format(i, j))
                    data = []
                    label = [1.0] * np.shape(self.dataSet[batch][self.labels[i]])[0]
                    label.extend([-1.0] * np.shape(self.dataSet[batch][self.labels[j]])[0])
                    data.extend(self.dataSet[batch][self.labels[i]])
                    data.extend(self.dataSet[batch][self.labels[j]])
                    svm = SMO(np.array(data), np.array(label), self.C, self.toler, self.maxIter, **self.kernelargs)
                    svm.smoP()
                    self.classifiers.append(svm)

    def test(self, test_dataset):
        """
        test phase
        :param test_dataset:
        :return:
        """
        predict_labels = []
        count = 0.0
        for n in tqdm(range(len(test_dataset))):
            image, label = test_dataset[0]
            result = [0] * self.classNum
            index = -1
            for i in range(self.classNum):
                for j in range(i + 1, self.classNum):
                    index += 1
                    classifier = self.classifiers[index]
                    # t = classifier.predict([data[n]])[0]
                    t = classifier.predict([np.array(image)])[0]
                    if t > 0.0:
                        result[i] += 1
                    else:
                        result[j] += 1
            predict_labels.append(result.index(max(result)))
            if predict_labels[-1] != label:
                count += 1
        return count / len(test_dataset)

    def predict(self, predict_dataset):
        """
        predict phase
        :param images: a set of images without labels
        :return: a list of results
        """
        predict_labels = []
        for n in range(len(predict_dataset)):
            result = [0] * self.classNum
            index = -1
            for i in range(self.classNum):
                for j in range(i + 1, self.classNum):
                    index += 1
                    classifier = self.classifiers[index]
                    t = classifier.predict(np.array([predict_dataset[n]]))[0]
                    if t > 0.0:
                        result[i] += 1
                    else:
                        result[j] += 1
            predict_labels.append(result.index(max(result)))
        return predict_labels

    def save(self, filename):
        fw = open(filename, 'wb')
        pickle.dump(self, fw, 2)
        fw.close()

    @staticmethod
    def load(filename):
        fr = open(filename, 'rb')
        svm = pickle.load(fr)
        fr.close()
        return svm


def main():
    # '''
    # data, label = loadImage('trainingDigits')
    train_data, test_data = mnist_dataset(download=True)
    # print("dataset is loaded!")
    # svm = SVM(train_data, 200, 0.0001, 10000, name='rbf', theta=20)
    # svm.train()
    # svm.save("svm.txt")
    # '''
    svm = SVM.load("svm.txt")
    # test,testlabel = loadImage('testDigits')
    result = svm.test(test_data)
    print(result)


if __name__ == "__main__":
    sys.exit(main())
