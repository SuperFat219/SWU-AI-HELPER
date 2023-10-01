#1.Babylonian square root algorithm (古巴比伦平方根算法)
#古巴比伦人计算算术平方根按如下步骤 ( 这里以根号2的计算为例) :
#1) 猜想．
#2) 用猜想的数去除被开方数 2．
#3) 求前两步得到的数的平均值．
#4) 令该平均值为新的猜测值，回到步骤 1) ，并且重复以上过程．

Number=float(input('print your number:'))
guess=float(input('print your guess number:'))
n=0
while True:
    n=n+1
    average=((Number/guess)+guess)/2
    guess=average
    if abs(guess-average)<0.01:
        print(guess)
        print(n)
        break

'''
Number=float(input('print your number:'))
guess=float(input('print your guess number:'))
n=0
while True:
    n+=1
    average=((Number/guess)+guess)/2
    guess=average
    if n>500:
        print(average)
        break
'''
#算法质量：细致程度，有效性（时间复杂度，空间复杂度），