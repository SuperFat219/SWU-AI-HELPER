# 模型的单一测试集对比(对应章节4.1)

## 三个模型的预测结果数据-------------------------------------------------------
arima_f <- c(fc_arima[["mean"]])          # ARIMA模型的预测数据
ets_f <- c(fc_ets[["mean"]])              # ETS模型的预测数据
prophet_f <- c(tail(forecast_p[,16],6))   # prophet模型的预测数据

## 三个模型的预测结果数据与真实数据的对比图-------------------------------------
ggplot()+
  geom_point(aes(x=df_test$ds,y=df_test$y),color="black") + 
  geom_line(aes(x=df_test$ds,y=df_test$y),color="black") +
  scale_x_continuous(breaks=df_test$ds,labels=c("2021-09","2021-10","2021-11",
                                                "2021-12","2022-01","2022-02"))+
  xlab("date") + ylab("100 million sales") + 
  geom_point(aes(x=df_test$ds,y=arima_f),color="#d93a49") + 
  geom_line(aes(x=df_test$ds,y=arima_f),color="#d93a49") +
  geom_point(aes(x=df_test$ds,y=ets_f),color="#009ad6") + 
  geom_line(aes(x=df_test$ds,y=ets_f),color="#009ad6") +
  geom_point(aes(x=df_test$ds,y=prophet_f),color="#8273B0") + 
  geom_line(aes(x=df_test$ds,y=prophet_f),color="#8273B0") +
  theme(plot.title = element_text(hjust = 0.5))