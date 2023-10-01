# # makefile
# Objects = main.o window_main.o system_functions.o common_func.o linklist_function.o
# all : Address System
# CFLAGS = -g -Wall `pkg-config --cflags gtk+-3.0`
# LDFLAGS = `pkg-config --libs gtk+-3.0`
# CC = gcc
# INSTDIR = /home/god/Projects/address_system_model/bin
# INCLUDE = .
# #edit : $(objects)
# #	$(CC) $(CFLAGS) $< -o main $(LDFLAGS)
# Address System : $(Objects)# main.o window_main.o system_functions.o common_func.o linklist_function.o
# 	$(CC) -o system $(Objects)
# main.o : main.c
# 	$(CC) -I$(INCLUDE) $(CFLAGS) -c main.c
# window_main.o : window_main.c window_main.h
# 	$(CC) -I$(INCLUDE) $(CFLAGS) -c window_main.c
# system_functions.o : system_functions.c system_functions.h
# 	$(CC) -I$(INCLUDE) $(CFLAGS) -c system_functions.c
# common_func.o : common_func.c common_func.h
# 	$(CC) -I$(INCLUDE) $(CFLAGS) -c common_func.c
# linklist_function.o : linklist_function.c linklist_function.h
# 	$(CC) -I$(INCLUDE) $(CFLAGS) -c linklist_function.c
# .PHONY : clean
# clean :
# 	rm main $(Objects) 
# # end of makefile 
CC=gcc
PROG_NAME=main
INCS=
SRCS=main.c window_main.c system_functions.c linklist_function.c common_func.c
#INSTDIR=/usr/local/bin
#INCLUDE=.
#从xx.c 文件得到 xx.o 文件
OBJS=${SRCS:.c=.o}
LIBS=gtk+-3.0
CFLAGS=`pkg-config --cflags ${LIBS}` -g -Wall
LDFLAGS=`pkg-config --libs ${LIBS}` -g -Wall

all: ${PROG_NAME}
#
${PROG_NAME}:${OBJS}
	${CC} -o ${PROG_NAME} ${OBJS} ${LDFLAGS}

#如果有头文件进行修改，则自动编译源文件
${OBJS}:${INCS}

.c.o:
	${CC} -c $< ${CFLAGS}
clean:
	rm -f *.o ${PROG_NAME}
rebuild:
	clean all