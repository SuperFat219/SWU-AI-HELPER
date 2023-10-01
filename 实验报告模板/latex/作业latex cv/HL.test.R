HL.test <- function(X){
  Y <- apply(X,1,mean)
  k <- nrow(X)
  b <- ncol(X)
  Y2 <- data.frame(Y,Y,Y)
  AX <- X-Y2
  R <- matrix(rank(AX),byrow = TRUE,ncol=ncol(X))
  R.j <- apply(R,2,mean)
  r1 <- (R-R.j)^2
  r2 <- (k-1)*b^2/sum(r1)
  r3 <- (R.j-(k*b+1)/2)^2
  Q <- r2*sum(r3)
  chi <- pchisq(0.95,df=k-1)
  if (Q > chi) {
    print('Reject the original hypothesis.')
  } else {
    print('Do not reject the original hypothesis.')
  }
}
