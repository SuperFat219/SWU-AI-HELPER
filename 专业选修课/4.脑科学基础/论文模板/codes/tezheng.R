# 非平稳性验证==================================================================

ggAcf(sales,lag.max = 80)+ggtitle('')

# 趋势性========================================================================

## 长期性趋势-------------------------------------------------------------------

### 一次差分的ACF图
d1_sales <- diff(sales,lag = 1)
ggAcf(d1_sales,lag.max = 80)+ggtitle('')

### 移动平均
ydpj3 <- stats::filter(data$`100msales`/3.0,rep(1,3))     # 3个月移动平均
ydpj5 <- stats::filter(data$`100msales`/5.0,rep(1,5))     # 5个月移动平均

ydpj12 <- stats::filter(data$`100msales`/12.0,rep(1,12))  # 12个月移动平均
ydpj122 <- stats::filter(ydpj12/2.0,rep(1,2)) 

sales %>%                                                 # 整理绘图
  autoplot(facets=TRUE) +
  xlab("year") + #lab("100 million sales") + 
  geom_line(aes(y=ydpj5),color="#f47920") +
  geom_line(aes(y=ydpj3),color="#009ad6") +
  geom_line(aes(y=ydpj122),color="#d93a49") +
  theme(plot.title = element_text(hjust = 0.5))

## 季节性趋势-------------------------------------------------------------------

### 季节图
ggseasonplot(sales, polar=TRUE) +
  xlab("month")+
  ylab("100 million (yuan)") +
  ggtitle("Seasonal plot: E-commerce sales in Guangzhou")+
  theme(plot.title = element_text(hjust = 0.5))


