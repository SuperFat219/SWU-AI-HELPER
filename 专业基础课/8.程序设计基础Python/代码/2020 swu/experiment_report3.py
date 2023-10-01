#确定一个文件的长度，以字数为单位。
#计算文件中唯一的字数的个数。
import wordcloud
import numpy as np
from PIL import Image
words_list=[]
txt=open(r'gettysburg.txt','r',encoding='utf-8').read()
txt = txt.lower() 
for ch in '!"#$%&()*+,-./:;<=>?@[\\]^_‘{|}~':
    txt = txt.replace(ch, " ")  
words=txt.split()
print('文件长度为',len(words))
for word in words:
    if word not in words_list:
        words_list.append(word)
#print(words_list)
#print('所有不同单词的个数为：',len(words_list))
mk=np.array(Image.open('love.jpg'))
c=wordcloud.WordCloud(background_color='white',mask=mk)
words2_list=' '.join(words_list)
c.generate(words2_list)
c.to_file('words.png')



'''
for word in words:
    if (len(word) == 1) or (word in Exception_list) or (word.isdigit()== True):
        continue
    else:
        counts[word] = counts.get(word, 0) + 1#dict.get(key, default=None)
                                              #key -- 字典中要查找的键。
                                            #default -- 如果指定键的值不存在时，返回该默认值。
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
'''