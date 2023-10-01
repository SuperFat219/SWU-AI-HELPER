#统计一下一个英文文档中出现次数最多的单词,并制作一个词云图
import wordcloud
import numpy as np
from PIL import Image
word_list = []


def get_text():
    txt = open(
        r'C:\Users\God\Desktop\数模全套学习资料\2020亚太杯数据\JoeBidenTweets utf-8.txt',
        "r",
        encoding='UTF-8').read()
    txt = txt.lower()  #将所有大写字符转换成小写
    for ch in '!"#$%&()*+,-./:;<=>?@[\\]^_‘{|}~':
        txt = txt.replace(ch, " ")  # 将文本中特殊字符替换为空格
    return txt


file_txt = get_text()
words = file_txt.split()  # 对字符串进行分割，获得单词列表
counts = {}
Exception_list=['this','https','the','to','com','and','co','of','in','we','our','for','is','on','10','that','you','it','01','have','with','20','are','09',\
'will','be','as','who','not','your','at','has','http','us','but','today','one','my','he','from','up','do','by','if','it\'s','out','about','they','no','get',\
'was','an','what','his','now','it’s','can','day','should','me','than','or','just','vp']
for word in words:
    if (len(word) == 1) or (word in Exception_list) or (word.isdigit()== True):
        continue
    else:
        counts[word] = counts.get(word, 0) + 1
items = list(counts.items())
items.sort(key=lambda x: x[1], reverse=True)
for i in range(50):
    word, count = items[i]
    word_list.append(word)
    #print("{0:<5}->{1:>5}".format(word,count))
mk = np.array(Image.open('tree.jpg'))
c = wordcloud.WordCloud(background_color='white', mask=mk)
word_list2 = ' '.join(word_list)
c.generate(word_list2)
c.to_file('biden2.png')
