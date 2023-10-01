function x = binary( fname,a,b,e )
% 二分法求解非线性方程f(x)=0
if nargin<4,e=1e-4;end;
fa=fname(a);fb=fname(b);
x=(a+b)/2;
if fa*fb>=0,error('函数值两端必须异号');end;
if fname(x)~=0
    while (b-a)>(2*e)
        fx=fname(x);
        if fa*fx<0,b=x;fb=fx;else a=x;fa=fx;end
        x=(a+b)/2;
    end
end
end

