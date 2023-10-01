objects=RPN.o RPN_cal.o
cc=gcc
edit : $(objects)
	cc -o main $(objects)
	
RPN_cal.o : RPN_cal.c
RPN.o : RPN.c RPN.h
.PHONY : clean
clean :
	rm main RPN.o RPN_cal.o
