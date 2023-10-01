function X = Sequential_Gaussian_elimination(A,b)
% 顺序高斯消去法求解方程AX=b，其中A为方阵
dimension=length(b);
A=[A,b];
for i=1:dimension-1
    A((i+1):dimension,(i+1):(dimension+1))=A((i+1):dimension,(i+1):(dimension+1))-...
        A(i+1:dimension,i)./A(i,i).*A(i,(i+1):(dimension+1));
    A((i+1):dimension,i)=zeros(dimension-i,1);
    %A
end;
%回代
X=zeros(dimension,1);
X(dimension)=A(dimension,dimension+1)/A(dimension,dimension);
for k=dimension-1:-1:1
    X(k)=(A(k,dimension+1)-A(k,(k+1):dimension)*X((k+1):dimension))/A(k,k);
end;


