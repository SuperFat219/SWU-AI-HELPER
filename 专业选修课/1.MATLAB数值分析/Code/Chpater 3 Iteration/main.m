clear;
clc;
% syms x
% f=x^3+2*x^2+1;
% % f(x)=sin(x)+x;
% f=@(x)5*x^23-6*x^7+8*x^6-5*x^2;
% df=@(x)115*x^22-42*x^6+48*x^5-10*x;
% f=@(x)3*x^5-4*x^3+2*x-1;
% df=@(x)15*x^4-12*x^2+2;
% fplot(f)
% f=@(x)x*(log(sqrt(x^2-1))+x)-sqrt(x^2-1)-0.5*x;
% df=@(x)x + log((x^2 - 1)^(1/2)) - x/(x^2 - 1)^(1/2) + x*(x/(x^2 - 1) + 1) - 1/2;
% fplot(f)
% 
% x1=binary(f,1,2)
% x2=newton_iteration(f,df,1,0.1,5000)
% x3=fsolve(f,2)
f=@(x)x^4-2^x;
x1=fsolve(f,-2)
x2=fsolve(f,2)


f=@func;
x0=[2,0];
x=fsolve(f,x0)
