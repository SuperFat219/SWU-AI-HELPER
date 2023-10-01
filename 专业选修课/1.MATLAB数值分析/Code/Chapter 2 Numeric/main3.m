%สตั้10
%2021.11.16
dimension=300;
N=zeros(dimension,dimension);
for i=1:dimension
    N(i,i)=2;
    if i~=dimension
        N(i,i+1)=1;
    end;
    if i~=1
        N(i,i-1)=1;
    end;
end;
b=zeros(300,1);
b(2:299)=4;b(1)=3;b(300)=3;
tic;
X1=Selected_column_principal_Gaussian_elimination(N,b);
toc;
tic;
X2=Sequential_Gaussian_elimination(N,b);
toc;
tic;
X3=N\b;
toc;