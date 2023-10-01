% 2021.11.16
function x=naguass(a,b,flag)
%顺序高斯消去法 AX=B
if nargin<3,flag=0;end
n=length(b);a=[a,b];
%消元
for k=1:n-1
    a((k+1):n,(k+1):(n+1))=a((k+1):n,(k+1):(n+1))-a((k+1):n,k)/a(k,k)*a(k,(k+1):(n+1));
    a((k+1):n,k)=zeros(n-k,1);%每列下三角部分置0
    if flag==0,a,end  %显示中间过程
end
%回代
x=zeros(n,1);
x(n)=a(n,n+1)/a(n,n);
for k=n-1:-1:1
    x(k)=(a(k,n+1)-a(k,(k+1):n)*x((k+1):n))/a(k,k);%倒推x的解
end
