//
// Created by god on 2021/12/12.
//

#ifndef BOOK_SYSTEM_DATA_OPERATIONS_H
#define BOOK_SYSTEM_DATA_OPERATIONS_H

#include "headers/common.h"

int import_book_data_from_file(FILE *ip, BookInfo *bookInfo);

ResultDisplay *import_reader_data_from_file(FILE *ip, ReaderInfo *readerInfo);

int import_borrow_data_from_File(FILE *ip, BorrowInfo *borrowInfo);

void import_administrator_data_from_file(FILE *ip, AdministratorInfo *administratorInfo);

void write_book_data_to_file(FILE *op);

void write_reader_data_to_file(FILE *op);

void write_borrow_data_to_file(FILE *op);

int insert_book(Book *book, BookInfo *bookInfo);

int insert_reader(Reader *reader, ReaderInfo *readerInfo);

void insert_borrow_book(BorrowBook *borrowBook, BorrowInfo *borrowInfo);

void delete_book(char *book_id, BookInfo *bookInfo);

void delete_all_books(BookInfo *bookInfo);

void delete_reader(char *reader_id, ReaderInfo *readerInfo);

void delete_all_readers(ReaderInfo *readerInfo);

void delete_borrow_book(char *book_id, BorrowInfo *borrowInfo);

void get_current_borrow_time(char current_time[]);

void get_return_book_time(char return_time[]);

#endif //BOOK_SYSTEM_DATA_OPERATIONS_H
