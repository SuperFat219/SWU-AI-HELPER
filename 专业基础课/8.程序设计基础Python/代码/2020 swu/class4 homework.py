import jieba as jb
txt = open(r'd:\desktop\test\程静上课\西南大学.txt', 'r').read()
f = open('SWU.txt', 'w')
words = jb.lcut(txt)
for word in words:
    if word == '西南':
        word = 'SWU'
    f.write(word)
f.close()
f2 = open('SWU.txt', 'r')
for line in f2:
    print(line, end='')
