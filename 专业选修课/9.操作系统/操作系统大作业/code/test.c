/*
 * @Author: Johnson 1103894895@qq.com
 * @Date: 2023-06-20 03:47:54
 * @LastEditors: Johnson 1103894895@qq.com
 * @LastEditTime: 2023-06-20 04:00:43
 * @FilePath: \code\test.c
 * @Description:
 *
 * Copyright (c) 2023 by Johnson, All Rights Reserved.
 */
#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

static int __init hello_init(void)
{
    printk(KERN_INFO "Hello, world!\\n");
    return 0;
}
static void __exit hello_exit(void)
{
    printk(KERN_INFO "Goodbye, world!\\n");
}

module_init(hello_init);
module_exit(hello_exit);

MODULE_DESCRIPTION("A simple hello world module");

obj - m : = hello.o #要生成的模块名

                KERNELDIR
                ? = / lib / modules / $(shell uname - r) / build PWD
                : = $(shell pwd)

                        all : make -
                              C $(KERNELDIR) M = $(PWD) modules

                                                     clean : make -
                                                             C $(KERNELDIR) M = $(PWD) clean

• sudo make：编译当前目录下的代码，并生成可执行文件或者模块文件。
• ls：列出当前目录下的文件。
• sudo insmod hello.ko：将名为hello.ko的模块文件插入内核中。
• lsmod：列出当前已经插入内核的模块文件。
• modinfo hello.ko：查看名为hello.ko的模块文件的详细信息。
• dmesg：显示内核在启动时产生的所有日志消息，包括模块加载和卸载时的消息。
• sudo rmmod hello：将名为hello的模块文件从内核中移除。

#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>

#define BUFFER_SIZE 10

                                                                                int buffer[BUFFER_SIZE];
int in = 0;
int out = 0;

sem_t mutex;
sem_t empty;
sem_t full;

int produce_item()
{
    return 1;
}

void consume_item(int item)
{
    printf("Consumed item %d\\n", item);
}

void *producer(void *arg)
{
    while (1)
    {
        int item = produce_item();
        printf("Produced item %d\\n", item);
        sem_wait(&empty);
        sem_wait(&mutex);
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        sem_post(&mutex);
        sem_post(&full);
    }
}

void *consumer(void *arg)
{
    while (1)
    {
        sem_wait(&full);
        sem_wait(&mutex);
        int item = buffer[out];
        printf("Consumed item %d\\n", item);
        out = (out + 1) % BUFFER_SIZE; // 模拟环形缓冲区
        sem_post(&mutex);
        sem_post(&empty);
        consume_item(item);
    }
}

int main()
{
    sem_init(&mutex, 0, 1);
    sem_init(&empty, 0, BUFFER_SIZE);
    sem_init(&full, 0, 0);

    pthread_t producer_thread, consumer_thread;
    pthread_create(&producer_thread, NULL, producer, NULL);
    pthread_create(&consumer_thread, NULL, consumer, NULL);

    pthread_join(producer_thread, NULL);
    pthread_join(consumer_thread, NULL);

    sem_destroy(&mutex);
    sem_destroy(&empty);
    sem_destroy(&full);

    return 0;
}

const int PAGE_NUM = 50;
int page_req[PAGE_NUM];
srand(time(NULL));

// generate random page requests
for (int i = 0; i < PAGE_NUM; i++)
{
    page_req[i] = rand() % 20;
}

const int PAGE_FRAME_NUM = 5;
int page_frame[PAGE_FRAME_NUM];

int page_fault = 0;
int found;

// simulate LRU
for (int i = 0; i < PAGE_NUM; i++)
{
    found = 0;
    // check if page is already in frame
    for (int j = 0; j < PAGE_FRAME_NUM; j++)
    {
        if (page_frame[j] == page_req[i])
        {
            found = 1;
            break;
        }
    }
    // if page is not in frame, find the oldest page and replace it
    if (!found)
    {
        page_fault++;
        printf("Page fault at index %d\n", i);
        for (int k = 0; k < FRAME_NUM; k++)
        { // 输出页框数组中的内容
            printf("%d ", frames[k]);
        }
        printf("\n");
        if (i < PAGE_FRAME_NUM)
        {
            page_frame[i] = page_req[i];
        }
        else
        {
            int oldest_page = 0;
            int oldest_time = PAGE_NUM;
            for (int j = 0; j < PAGE_FRAME_NUM; j++)
            {
                for (int k = i - 1; k >= 0; k--)
                {
                    if (page_frame[j] == page_req[k])
                    {
                        if (k < oldest_time)
                        {
                            oldest_page = j;
                            oldest_time = k;
                        }
                        break;
                    }
                }
                if (k < 0)
                {
                    oldest_page = j;
                    break;
                }
            }
            page_frame[oldest_page] = page_req[i];
        }
    }
}

const int NEW_PAGE_FRAME_NUM = 10;
int new_page_frame[NEW_PAGE_FRAME_NUM];
int new_page_fault = 0;

// simulate LRU with more page frames
for (int i = 0; i < NEW_PAGE_FRAME_NUM; i++)
{
    new_page_frame[i] = -1;
}

for (int i = 0; i < PAGE_NUM; i++)
{
    found = 0;
    // check if page is already in frame
    for (int j = 0; j < NEW_PAGE_FRAME_NUM; j++)
    {
        if (new_page_frame[j] == page_req[i])
        {
            found = 1;
            break;
        }
    }
    // if page is not in frame, find the oldest page and replace it
    if (!found)
    {
        new_page_fault++;
        if (i < NEW_PAGE_FRAME_NUM)
        {
            new_page_frame[i] = page_req[i];
        }
        else
        {
            int oldest_page = 0;
            int oldest_time = PAGE_NUM;
            for (int j = 0; j < NEW_PAGE_FRAME_NUM; j++)
            {
                for (int k = i - 1; k >= 0; k--)
                {
                    if (new_page_frame[j] == page_req[k])
                    {
                        if (k < oldest_time)
                        {
                            oldest_page = j;
                            oldest_time = k;
                        }
                        break;
                    }
                }
                if (k < 0)
                {
                    oldest_page = j;
                    break;
                }
            }
            new_page_frame[oldest_page] = page_req[i];
        }
    }
}
float page_fault_rate = (float)new_page_faults / PAGE_NUM;
printf("Page frames: %d, Page fault rate: %.4f\n", frames, page_fault_rate);

//

int add_page_to_frames_fifo(int page_num, int frame_num, int *head, int *tail)
{
    int replaced_page = -1;
    if (get_page_frame_index(page_num) == -1)
    { // 页面不在内存中，需要替换
        if (page_frames[*tail] != -1)
        { // 页框数组已满，需要替换
            replaced_page = page_frames[*tail];
        }
        page_frames[*tail] = page_num;
        *tail = (*tail + 1) % frame_num;
        if (*tail == *head)
        { // FIFO 算法的页面置换策略
            *head = (*head + 1) % frame_num;
        }
    }
    return replaced_page;
}
int main()
{
    srand(time(NULL));
    for (int i = 0; i < PAGE_NUM; i++)
    {
        page_requests[i] = rand() % 20;
    }
    for (int frame_num = 1; frame_num <= MAX_FRAME_NUM; frame_num++)
    {
        for (int j = 0; j < MAX_FRAME_NUM; j++)
        {
            page_frames[j] = -1;
        }
        int page_faults_lru = count_page_faults_lru(frame_num);
        float page_fault_rate_lru = (float)page_faults_lru / PAGE_NUM;
        printf("LRU: Page frames: %d, Total page faults: %d, Page fault rate: %.4f\n", frame_num, page_faults_lru, page_fault_rate_lru);
        int page_faults_fifo = count_page_faults_fifo(frame_num);
        float page_fault_rate_fifo = (float)page_faults_fifo / PAGE_NUM;
        printf("FIFO: Page frames: %d, Total page faults: %d, Page fault rate: %.4f\n", frame_num, page_faults_fifo, page_fault_rate_fifo);
    }
    return 0;
}