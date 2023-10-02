/*
 * @Author: Johnson 1103894895@qq.com
 * @Date: 2023-06-19 20:14:48
 * @LastEditors: Johnson 1103894895@qq.com
 * @LastEditTime: 2023-06-19 20:14:52
 * @FilePath: \code\basicModel.c
 * @Description:
 *
 * Copyright (c) 2023 by Johnson, All Rights Reserved.
 */
#include <linux/module.h>     /* 引入与模块相关的宏*/
#include <linux/init.h>       /* 引入module_init() module_exit()函数*/
#include <linux/moduleparam.h>/* 引入module_param() */
MODULE_AUTHOR("Yan Zhongsheng");
MODULE_LICENSE("GPL");
static int nbr = 10;
module_param(nbr, int, S_IRUGO);  /* 加载模块时传入参数*/
static int __init yuer_init(void) /* 加载模块时自动执行*/
{
    int i;
    for (i = 0; i < nbr; i++)
    {
        printk(KERN_ALERT "Hello, How are you. %d\n", i);
    }
    return 0;
}
static void __exit yuer_exit(void)
{
    printk(KERN_ALERT "I come from yuer's module, I have been unlad.\n");
}
module_init(yuer_init);
module_exit(yuer_exit);