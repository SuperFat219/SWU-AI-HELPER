% f=@(x)5*x^23-6*x^7+8*x^6-5*x^2;
% f=@(x)3*x^5-4*x^3+2*x-1;
% f=@(x)x^2+x+1;
% f=@(x)x^4-2^x;
% fplot(f)
% ylim([-2,10])
% grid on;
figure;
ezplot('(x-2)^2+(y-3+2*x)^2=5')
hold on;
ezplot('2*(x-3)^2+(y/3)^2=4')
xlim([-2,6]);