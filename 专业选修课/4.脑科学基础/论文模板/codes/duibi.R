# 基于前移验证的模型预测性能对比(对应章节4.2)

# ARIMA和ETS模型部分============================================================

X <- sales_st                   # 标准化后的电商销售额数据
n_samples <- 62                 # 总样本量
btda2 <- data.frame()           # 存储各个模型相关数据的数据框

## 对ARIMA和ETS模型做前移验证的循环代码-----------------------------------------
for (i in c(1:8)) {
  train_size <- (i+1)*6+2       # 训练窗口大小
  test_size <- 6                # 预测窗口大小
  
  ### 训练和测试窗口的划分
  if (i%%2 == 1){
    train <- ts(X[c(1:train_size)],frequency = 12, start = c(2017,1))
    test <- ts(X[c((1+train_size):(test_size+train_size))],frequency = 12, 
               start = c((2017+2*i-1),3))
  } else {
    train <- ts(X[c(1:train_size)],frequency = 12, start = c(2017,1))
    test <- ts(X[c((1+train_size):(test_size+train_size))],frequency = 12, 
               start = c((2017+i/2),9))
  }
  
  ### 模型ARIMA
  fit_arima <- forecast::auto.arima(train, seasonal = TRUE) # 建模
  s_arima <- summary(fit_arima)                             # 模型信息输出
  fc_arima <- forecast(fit_arima,h=6)                       # 预测
  bt <- Box.test(fit_arima$residuals,type="Ljung-Box",lag=1)# Ljung-Box检验
  test_f <- ts(fc_arima[["mean"]])                          # 预测结果
  test <- ts(test)                                          # 测试数据
  a_arima <- accuracy(test,test_f)                          # 模型性能数据
  
  ### 数据录入数据框
  model <- paste(fc_arima[["method"]],collapse=',')
  box_test <- paste(bt[["p.value"]],collapse=',')
  me_train <- paste(round(s_arima[1],digits=3),collapse=',')
  rmse_train <- paste(round(s_arima[2],digits=3),collapse=',')
  mae_train <- paste(round(s_arima[3],digits=3),collapse=',')
  mpe_train <- paste(round(s_arima[4],digits=3),collapse=',')
  mape_train <- paste(round(s_arima[5],digits=3),collapse=',')
  me_test <- paste(round(a_arima[1],digits=3),collapse=',')
  rmse_test <- paste(round(a_arima[2],digits=3),collapse=',')
  mae_test <- paste(round(a_arima[3],digits=3),collapse=',')
  mpe_test <- paste(round(a_arima[4],digits=3),collapse=',')
  mape_test <- paste(round(a_arima[5],digits=3),collapse=',')
  fore_test <- paste(c(fc_arima[["mean"]]),collapse=',')
  btda2 <- rbind(btda2,data.frame(model="ARIMA",fit_model=model,id=i,
                                  train_size=train_size,test_size=6,
                                  box_test=box_test,ME_train=me_train,
                                  RMSE_train=rmse_train,MAE_train=mae_train,
                                  MPE_train=mpe_train,MAPE_train=mape_train,
                                  ME_test=me_test,RMSE_test=rmse_test,
                                  MAE_test=mae_test,MPE_test=mpe_test,
                                  MAPE_test=mape_test,fore_test=fore_test))
  
  ### 模型ets
  fit_ets <- ets(train,model = "ZZZ")                       # 建模
  s_ets <- summary(fit_ets)                                 # 模型信息输出
  fc_ets <- forecast(fit_ets,h=6)                           # 预测
  bt <- Box.test(fit_ets$residuals,type="Ljung-Box",lag=1)  # Ljung-Box检验
  test_f <- ts(fc_ets[["mean"]])                            # 预测结果
  test <- ts(test)                                          # 测试数据
  a_ets <- accuracy(test_f, test)                           # 模型性能数据
  
  ### 数据录入数据框
  box_test <- paste(bt[["p.value"]],collapse=',')
  model <- paste(fc_ets[["method"]],collapse=',')
  me_train <- paste(round(s_ets[1],digits=3),collapse=',')
  rmse_train <- paste(round(s_ets[2],digits=3),collapse=',')
  mae_train <- paste(round(s_ets[3],digits=3),collapse=',')
  mpe_train <- paste(round(s_ets[4],digits=3),collapse=',')
  mape_train <- paste(round(s_ets[5],digits=3),collapse=',')
  me_test <- paste(round(a_ets[1],digits=3),collapse=',')
  rmse_test <- paste(round(a_ets[2],digits=3),collapse=',')
  mae_test <- paste(round(a_ets[3],digits=3),collapse=',')
  mpe_test <- paste(round(a_ets[4],digits=3),collapse=',')
  mape_test <- paste(round(a_ets[5],digits=3),collapse=',')
  fore_test <- paste(c(fc_ets[["mean"]]),collapse=',')
  btda2 <- rbind(btda2,data.frame(model="ETS",fit_model=model,id=i,
                                  train_size=train_size,test_size=6,
                                  box_test=box_test,ME_train=me_train,
                                  RMSE_train=rmse_train,MAE_train=mae_train,
                                  MPE_train=mpe_train,MAPE_train=mape_train,
                                  ME_test=me_test,RMSE_test=rmse_test,
                                  MAE_test=mae_test,MPE_test=mpe_test,
                                  MAPE_test=mape_test,fore_test=fore_test))
}

## prophet模型部分--------------------------------------------------------------

train_size <- (i+1)*6+2         # 训练窗口大小, i表示前移轮数
test_size <- 6                  # 测试窗口大小
btda_p <- data.frame()          # 存储各个模型相关数据的数据框

### 训练和测试窗口数据划分
if (i%%2 == 1){
  train <- ts(X[c(1:train_size)],frequency = 12, start = c(2017,1))
  test <- ts(X[c((1+train_size):(test_size+train_size))],frequency=12,
             start=c((2017+2*i-1),3))
} else {
  train <- ts(X[c(1:train_size)],frequency = 12, start = c(2017,1))
  test <- ts(X[c((1+train_size):(test_size+train_size))],frequency=12,
             start=c((2017+i/2),9))
}

### 转化为数据框
train_df <- data.frame(ds=as.vector(data[c(1:train_size),1]),y=train)
test_df <- data.frame(ds=as.vector(data[c((1+train_size):(test_size+train_size)),1]),
                      y=test)
colnames(train_df) <- c("ds","y")
colnames(test_df) <- c("ds","y")
train_df <- mutate(train_df,ds=ym(ds))
test_df <- mutate(test_df,ds=ym(ds))

num_cp <- round(train_size*17/54)

### 对不同的前移窗口,分别训练不同的prophet模型
### i=1,2
fit_pro <- prophet(train_df,n.changepoints=num_cp,
                   changepoint.range=0.99,
                   changepoint.prior.scale=0.9,
                   yearly.seasonality=TRUE,seasonality.mode='additive',
                   seasonality.prior.scale=60)
### i=3
fit_pro <- prophet(train_df,n.changepoints=num_cp,
                   changepoint.range=0.99,
                   changepoint.prior.scale=1.1,
                   yearly.seasonality=TRUE,seasonality.mode='additive',
                   seasonality.prior.scale=80)
### i=4
fit_pro <- prophet(train_df,n.changepoints=num_cp,
                   changepoint.range=0.99,
                   changepoint.prior.scale=0.7,
                   yearly.seasonality=TRUE,seasonality.mode='additive',
                   seasonality.prior.scale=70)
### i=5
fit_pro <- prophet(train_df,n.changepoints=num_cp,
                   changepoint.range=0.99,
                   changepoint.prior.scale=1.4,
                   yearly.seasonality=TRUE,seasonality.mode='additive',
                   seasonality.prior.scale=80)
###i=6
fit_pro <- prophet(train_df,n.changepoints=num_cp,
                   changepoint.range=0.99,
                   changepoint.prior.scale=0.7,
                   yearly.seasonality=TRUE,seasonality.mode='additive',
                   seasonality.prior.scale=90)
###i=7
fit_pro <- prophet(train_df,n.changepoints=num_cp,
                   changepoint.range=0.99,
                   changepoint.prior.scale=1.3,
                   yearly.seasonality=TRUE,seasonality.mode='additive',
                   seasonality.prior.scale=60)
### i=8
fit_pro <- prophet(train_df,n.changepoints=num_cp,
                   changepoint.range=0.99,
                   changepoint.prior.scale=0.6,
                   yearly.seasonality=TRUE,seasonality.mode='additive',
                   seasonality.prior.scale=70)

future <- make_future_dataframe(fit_pro,
                                periods=6,
                                freq='month',
                                include_history=TRUE)   # 预测数据框建立
fc_pro <- predict(fit_pro, future)                      # 预测
test_f <- ts(fc_pro[c((1+train_size):(test_size+train_size)),16])  # 对应轮数的测试数据
train_f <- ts(fc_pro[c(1:train_size),16])                          # 对应轮数的训练数据
test <- ts(test)
train <- ts(train)
(a_pro <- accuracy(test_f,test))                        # 模型预测性能数据
b_pro <- accuracy(train_f,train)                        # 模型训练性能数据

res_pro_train <- fc_pro[c(1:train_size),16]-train_df[c(1:train_size),2] # 残差
bt <- Box.test(res_pro_train,type="Ljung-Box",lag=1)    # Ljung-Box检验

### 数据录入数据框
box_test <- paste(bt[["p.value"]],collapse=',')
me_train <- paste(round(b_pro[1],digits=3),collapse=',')
rmse_train <- paste(round(b_pro[2],digits=3),collapse=',')
mae_train <- paste(round(b_pro[3],digits=3),collapse=',')
mpe_train <- paste(round(b_pro[4],digits=3),collapse=',')
mape_train <- paste(round(b_pro[5],digits=3),collapse=',')
me_test <- paste(round(a_pro[1],digits=3),collapse=',')
rmse_test <- paste(round(a_pro[2],digits=3),collapse=',')
mae_test <- paste(round(a_pro[3],digits=3),collapse=',')
mpe_test <- paste(round(a_pro[4],digits=3),collapse=',')
mape_test <- paste(round(a_pro[5],digits=3),collapse=',')
fore_test <- paste(c(tail(fc_pro[,16],6)),collapse=',')
btda_p <- rbind(btda_p,data.frame(model="Prophet",fit_model="Prophet",id=i,
                                  train_size=train_size,test_size=6,
                                  box_test=box_test,ME_train=me_train,
                                  RMSE_train=rmse_train,MAE_train=mae_train,
                                  MPE_train=mpe_train,MAPE_train=mape_train,
                                  ME_test=me_test,RMSE_test=rmse_test,
                                  MAE_test=mae_test,MPE_test=mpe_test,
                                  MAPE_test=mape_test,fore_test=fore_test))

## 前移验证数据整理-------------------------------------------------------------
btda_arima <- read.csv("D:\\毕设\\code\\btda_arima.csv")
btda_ets <- read.csv("D:\\毕设\\code\\btda_ets.csv")
btda_prophet <- read.csv("D:\\毕设\\code\\btda_prophet.csv")

btda_arima_f <-as.vector(unlist(c(rep(NA,14),btda_arima[1,c(12:17)],
                                  btda_arima[2,c(12:17)],btda_arima[3,c(12:17)],
                                  btda_arima[4,c(12:17)],btda_arima[5,c(12:17)],
                                  btda_arima[6,c(12:17)],btda_arima[7,c(12:17)],
                                  btda_arima[8,c(12:17)])))
btda_ets_f <-as.vector(unlist(c(rep(NA,14),btda_ets[1,c(12:17)],btda_ets[2,c(12:17)],
                                btda_ets[3,c(12:17)],btda_ets[4,c(12:17)],
                                btda_ets[5,c(12:17)],btda_ets[6,c(12:17)],
                                btda_ets[7,c(12:17)],btda_ets[8,c(12:17)])))
btda_prophet_f <-as.vector(unlist(c(rep(NA,14),btda_prophet[1,c(12:17)],
                                    btda_prophet[2,c(12:17)],btda_prophet[3,c(12:17)],
                                    btda_prophet[4,c(12:17)],btda_prophet[5,c(12:17)],
                                    btda_prophet[6,c(12:17)],btda_prophet[7,c(12:17)],
                                    btda_prophet[8,c(12:17)])))

sales_st %>% 
  autoplot(facets=TRUE) +
  xlab("year") + ylab("standardised 100 million sales")+
  geom_line(aes(y=btda_arima_f),color="#d93a49")+
  geom_line(aes(y=btda_ets_f),color="#009ad6")

summary(btda_arima)
summary(btda_ets)
summary(btda_prophet)
