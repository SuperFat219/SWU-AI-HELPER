import matplotlib.pyplot as plt
import torchvision.datasets as dataset  # 公开数据集的下载和管理
import numpy as np

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


if __name__ == '__main__':
    train_dataset, test_dataset = fashion_mnist_dataset(download=True)
    print(type(train_dataset[0][0]), len(train_dataset), len(test_dataset))
    img, label = train_dataset[0]
    print(img.size)
    print(np.array(img))
    # plt.imshow(img)
    print(label)
    print(train_dataset.classes)
