function [l,u] = nalu( a )
% 求可逆方程的LU分解
n = length(a);
u=zeros(n,n);l=eye(n,n);
u(1,:)=a(1,:);l(2:n,1)=a(2:n,1)/u(1,1);
for k=2:n
    u(k,k:n)=a(k,k:n)-l(k,1:k-1)*u(1:k-1,k:n);
    l(k+1:n,k)=(a(k+1:n,k)-l(k+1:n,1:k-1)*u(1:k-1,k))/u(k,k);
end;


end

