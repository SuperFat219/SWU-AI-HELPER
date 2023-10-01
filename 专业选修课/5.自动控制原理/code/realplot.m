syms s;
F = (0.196*exp(-20.7*s))/(24*s+1);
% % F = 1/(s*s);
% f = ilaplace(F)
% ezplot(f,[0,5])
% x = [0 10 20 30 40 50 60 70 80 90 100];
% t0 = [185 187 190 195 205 214 219 223 226 228 230];
% p = polyfit(x,t0,3); 
% 
% num=0.196;
% den=[24 1];
% g=tf(num,den,'ioDelay',20.7);


H=laplace(F);
y=180;
Y=laplace(y);
x=ilaplace(y/H);%求出系统输入x(t)
ezplot(x,[-2,15])%显示x(t)在-2到15件的图形
% step(g)
% t = 0:0.1:5;
% u = polyval(p,t);
% lsim(g,u,t)