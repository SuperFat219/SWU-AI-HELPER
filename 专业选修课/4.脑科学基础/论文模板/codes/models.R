# 时间序列数据标准化------------------------------------------------------------
# 其中data['100msales']为电商销售额原始数据(单位:亿元)

sales_st <- scale(data['100msales'],scale = TRUE)           # 数据标准化
sales_st <- ts(sales_st,frequency = 12, start = c(2017,1))  # 转为时间序列格式
autoplot(sales_st)                                          # 标准化后的时序图
ggtsdisplay(sales_st)

# 单位根检验--------------------------------------------------------------------

library(urca)

sales_st %>% ur.kpss() %>% summary()        # 对标准化后的数据做KPSS单位根检验
ndiffs(sales_st)                            # 查看需要进行差分的次数才能平稳

d1sales_st <- diff(sales_st,lag = 1)        # 一次差分
d1sales_st %>% ur.kpss() %>% summary()      # 一次差分后的KPSS单位根检验

sales_st %>%  diff(lag=12) %>% ndiffs()     # 一次季节差分

d12d1sales_st <- diff(d1sales_st,lag = 12)  # 对一次差分后的序列再做一次季节差分

# 1. ARIMA----------------------------------------------------------------------

## 划分训练集和测试集
sales_train <- window(sales_st,start=c(2017,1),end=c(2021,8))
sales_test <- window(sales_st,start=c(2021,9))
test_1 <- c(rep(NA,56),sales_test)

## 模型建立
fit1 <- forecast::auto.arima(sales_train, seasonal = TRUE,trace = TRUE)
summary(fit1)               # 模型简要数据
checkresiduals(fit1)        # 残差检验

bt <- Box.test(fit1$residuals,type="Ljung-Box",lag=1)   # Ljung-Box检验
fc_arima <- forecast(fit1,h=6)                          # 模型预测
fc_arima_fit <- c(fc_arima[["fitted"]],rep(NA,6))       # 训练集拟合数据
fc_arima_fore <- c(rep(NA,56),fc_arima[["mean"]])       # 预测结果数据

## 拟合和预测结果与真实数据对比图
sales_st %>% 
  autoplot(facets=TRUE) +
  xlab("year") + ylab("standardised 100 million sales")+
  geom_line(aes(y=fc_arima_fit),color="#f47920")+
  geom_line(aes(y=test_1),color="#009ad6")+
  geom_line(aes(y=fc_arima_fore),color="#d93a49")
dev.off()

## 预测结果时序图
fit1 %>% forecast(h=6) %>% autoplot(include=80) +
  xlab("year") + ylab("standardised 100 million sales")

## 输出预测结果
fc_arima <- forecast(fit1,h=6)

## 模型性能
accuracy(fc_arima,sales_test)
res_fore <- c(sales_test)-c(fc_arima[['mean']])
(res_fore_pt <- res_fore/c(sales_test))


# 2. ets------------------------------------------------------------------------

fit2 <- ets(sales_train,model = "ZZZ")             # 模型建立
coef(fit2)                                         # 模型系数
summary(fit2)                                      # 模型简要数据
checkresiduals(fit2)                               # 残差检验
Box.test(fit2$residuals,type="Ljung-Box",lag=1)    # Ljung-Box检验

fc_ets <- forecast(fit2,h=6)                       # 模型预测
fc_ets_fit <- c(fc_ets[["fitted"]],rep(NA,6))      # 训练集拟合数据
fc_ets_fore <- c(rep(NA,56),fc_ets[["mean"]])      # 预测结果数据
test_1 <- c(rep(NA,56),test)

## 拟合和预测结果与真实数据对比图
sales_st %>% 
  autoplot(facets=TRUE) +
  xlab("year") + ylab("standardised 100 million sales")+
  geom_line(aes(y=fc_ets_fit),color="#f47920")+
  geom_line(aes(y=test_1),color="#009ad6")+
  geom_line(aes(y=fc_ets_fore),color="#d93a49")

## 预测结果时序图
fit2 %>% forecast(h=6) %>% autoplot(include=80) +
  xlab("year") + ylab("standardised 100 million sales")

## 输出预测结果
fc_ets <- forecast(fit2,h=6)

## 模型性能
accuracy(fc_ets,sales_test)
res_fore <- c(sales_test)-c(fc_ets[['mean']])
(res_fore_pt <- res_fore/c(sales_test))

# 3. Prophet---------- ---------------------------------------------------------

y <- as.vector(sales_st)                              # 标准化样本数据
df_all <- data.frame(ds=dat['time'],y=y)              # 转为数据框
colnames(df_all) <- c("ds","y")                       # 列名重命名
df_all <- mutate(df_all,ds=ym(ds))                    # 数据框转化

df <- df_all[c(1:56),]                                # 训练集数据框
df_test <- df_all[c(57:62),]                          # 测试集数据框

## 模型建立
fit3 <- prophet(df,growth = 'linear',n.changepoints = 17,
                changepoint.range = 0.99,changepoint.prior.scale = 0.6,
                yearly.seasonality = TRUE,seasonality.mode = 'additive',
                seasonality.prior.scale = 70)

## 预测数据框建立
future <- make_future_dataframe(fit3,periods=6,
                                freq='month',include_history=TRUE)
forecast_p <- predict(fit3, future)  # 预测

## 拟合和预测结果与真实数据对比图
plot(fit3, forecast_p)+ 
  add_changepoints_to_plot(fit3,threshold = 0.02,cp_color = "#a7324a",
                           cp_linetype = "dashed",trend = TRUE)+
  geom_point(aes(y=test_1),color="#33a3dc")+
  xlab("year") + ylab("standardised 100 million sales")

## 时间序列各成分的拟合和预测结果图
prophet_plot_components(fit3, forecast_p)

res_pro_train <- forecast_p[c(1:56),16]-df_all[c(1:56),2]   # 残差
Box.test(res_pro_train,type="Ljung-Box",lag=1)              # Ljung-Box检验
checkresiduals(ts(res_pro_train))                           # 残差分析

## 模型性能
x_train <- accuracy(forecast_p[c(1:56),16],df_all[c(1:56),2])
x_test <- accuracy(forecast_p[c(57:62),16],df_all[c(57:62),2])
res_fore <- c(sales_test)-c(c(tail(forecast_p[,16],6)))
(res_fore_pt <- res_fore/c(sales_test))
