Durbin.test <- function(X,y){
  k <- nrow(X)
  b <- ncol(X)
  r <- b-y
  t <- k-y
  R <- matrix(rank(X,na.last = FALSE),byrow = TRUE,ncol=ncol(X))
  R.j <- apply(R,2,mean)
  r1 <- 12*(k-1)/(r*k*(t^2-1))
  r2 <- sum((R.j-r*(t+1)/2)^2)
  D <- r1*r2
  chi <- pchisq(0.95,df=k-1)
  if (D > chi) {
    print('Reject the original hypothesis.')
  } else {
    print('Do not reject the original hypothesis.')
  }
}