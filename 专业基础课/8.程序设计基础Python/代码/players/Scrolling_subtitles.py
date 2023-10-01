
def rolling():
    import time  #导入时间模块
    subtitle = '周羽好可爱!'
    i = 0
    while True:  #死循环
        display = subtitle[i:] + subtitle[:i]  #起到循环作用的主体语句
        i += 1
        print(display, end="", flush=True)
        #end=""，不换行
        #flush=True，立即输出数据，清空之前缓存
        #若flush=False，则可能会在缓存中存储很多，再显示出来，形成阻塞
        time.sleep(1)  #休息时间
        print("\r", end="", flush=True)  #“\r”，回车
        i %= len(subtitle)
        