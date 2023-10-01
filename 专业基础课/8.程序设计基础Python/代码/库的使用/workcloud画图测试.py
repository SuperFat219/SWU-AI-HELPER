#make a workcloud_picture
#在Python的str数据类型中，\u是转移字符，表示后边是unicode码，而恰恰\u有出现在了以三引号包围的形式的字符串中。所以即使三引号代表注释也会报错
#上面情况的解决方法：
#<1> 把注释全部更改成#号注释
#在正式的Python中(非注释的程序或模块)处理方式：
#在路径字符串前加一个r，如下代码
from PIL import Image
import wordcloud
import numpy as np
list1=['https','http','com','twitter','and','I\'m','I\'ll','a','t','co','the','for','by','We need','that','in','of','all','We are','not','We have',\
'because','on','If you','Tune','today','Thank you','his','their','from','We','but','with','We must','I ll','I','them','out','as']
mk=np.array(Image.open('cloud.jpg'))
c=wordcloud.WordCloud(\
    background_color='white',stopwords=list1,max_words=200,mask=mk)
f=open(r'C:\Users\God\Desktop\数模全套学习资料\2020亚太杯数据\JoeBidenTweets utf-8.txt','r',encoding='utf-8')
txt=f.read()
f.close()
c.generate(txt)
c.to_file('picture_pywordcloud99999.png')
