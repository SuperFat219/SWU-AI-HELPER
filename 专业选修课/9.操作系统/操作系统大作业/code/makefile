obj-m := hello.o #要生成的模块名

KERNELDIR ?= /lib/modules/$(shell uname -r)/build
PWD := $(shell pwd)

all:
   make -C $(KERNELDIR) M=$(PWD) modules

clean:
   make -C $(KERNELDIR) M=$(PWD) clean
