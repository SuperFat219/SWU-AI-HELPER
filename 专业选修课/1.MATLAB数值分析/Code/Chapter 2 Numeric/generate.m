function N = generate(dimension)
%GENERATE 生成n阶方阵
%dimension:方阵维数
N=zeros(dimension,dimension);
for i=1:dimension
    N(i,i)=5;
    if i~=dimension
        N(i,i+1)=6;
    end;
    if i~=1
        N(i,i-1)=1;
    end;
end;

end

