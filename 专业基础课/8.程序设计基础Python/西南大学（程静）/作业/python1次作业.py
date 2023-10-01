for i in range(5):
    inch=int(input('输入降雨量：'))
    size=43560
    feet=inch/12
    area=size*feet
    gallon=area*7.48051945
    print('这一英亩的土地上可积累'+str(gallon)+'加仑的降雨量')
