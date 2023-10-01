func = @(x,y) x+y;
[t,y]=ode113(func,[0,3],1);
plot(t,y,'-o');
legend('ode113')
[t,y]=ode113(func,0:3,1);
[t,y]