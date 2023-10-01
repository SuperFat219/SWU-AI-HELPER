# 等额分期偿贷 
B0 <- 20000; n <- 5; i <- 0.06; v <- 1/(1+i);
a <-function(k) 
{a <-(1-v^k)/i 
} 
R <- B0/a(n);
#每年末偿还金额中的利息和本金金额 # 利息 
Ik <-array(0, c(1, n, 1))
for(k in 1:n)
{
  Ik[k] = i*R*a(n-k+1)
} 
Ik <-round(Ik, digits = 2); Ik # 本金 
Pk <-array(0, c(1, n,1)) 
for (k in 1:n)
{
  Pk[k] = B0/a(n)*v^(n-k+1)
} 
Pk <- round(Pk, digits = 2); 
Pk