//
// Created by god on 2021/12/12.
//

#include "headers/data_operations.h"

int import_book_data_from_file(FILE *ip, BookInfo *bookInfo) {
    const char whitespace[] = " "; //分隔符
    const char linebreak[] = "\n"; //换行符
    char buffer[200];              //缓冲区
    int count = 0;                 //记录读取了多少条数据
    while (fgets(buffer, N, ip))   //按行读取
    {
        Book *book = (Book *) malloc(sizeof(Book));
        unsigned int length = strlen(buffer);
        /*进行字符串分割*/
        strcpy(book->bookId, strtok(buffer, whitespace));
        strcpy(book->bookName, strtok(NULL, whitespace));
        strcpy(book->bookType, strtok(NULL, whitespace));
        strcpy(book->bookAuthor, strtok(NULL, whitespace));
        strcpy(book->bookPub, strtok(NULL, whitespace));
        strcpy(book->bookPages, strtok(NULL, whitespace));
        book->status = strtok(NULL, whitespace)[0] - '0';
        strcpy(book->borrowTime, strtok(NULL, linebreak));
        insert_book(book, bookInfo);
        count++;
    }
    /*进行排序*/
    Quick_sort(bookInfo->books, bookInfo->booksNum);
    return count;
}

ResultDisplay *import_reader_data_from_file(FILE *ip, ReaderInfo *readerInfo) {
    ResultDisplay *result = (ResultDisplay *) malloc(sizeof(ResultDisplay));
    result->wrongNum = 0;
    result->correctNum = 0;
    char username[30];
    char password[20];
    char readId[10];//读者编号
    char readerName[10];//读者姓名
    char readerSex[4];//性别
    char readerTel[13];//电话
    char email[20];//邮箱
    int borrowedNum;//已借阅数量
    int maxBorrowNum;//最大借阅数量
    while (EOF != fscanf(ip, "%s %s %s %s %s %s %s %d %d", username, password, readId, readerName,
                         readerSex, readerTel, email,
                         &borrowedNum, &maxBorrowNum)) {
        Reader *reader = (Reader *) malloc(sizeof(Reader));
        strcpy(reader->username, username);
        strcpy(reader->password, password);
        strcpy(reader->readerId, readId);
        strcpy(reader->readerName, readerName);
        strcpy(reader->readerSex, readerSex);
        strcpy(reader->readerTel, readerTel);
        strcpy(reader->email, email);
        reader->borrowedNum = borrowedNum;
        reader->maxBorrowNum = maxBorrowNum;
        if (insert_reader(reader, readerInfo) == true) {
            result->correctNum++;
        } else {
            result->wrongNum++;
        }
    }
    Quick_sort_reader(readerInfo->readers, readerInfo->readersNum);
    return result;
}

int import_borrow_data_from_File(FILE *ip, BorrowInfo *borrowInfo) {
    const char whitespace[] = " "; //分隔符
    const char linebreak[] = "\n"; //换行符
    char buffer[200];              //缓冲区
    int count = 0;                 //记录读取了多少条数据
    while (fgets(buffer, N, ip))   //按行读取
    {
        BorrowBook *borrowBook = (BorrowBook *) malloc(sizeof(BorrowBook));
        unsigned int length = strlen(buffer);
        /*进行字符串分割*/
        strcpy(borrowBook->readerName, strtok(buffer, whitespace));
        strcpy(borrowBook->bookID, strtok(NULL, whitespace));
//        char *p = borrowBook->bookID;char *l = borrowBook->readerName;
        strcpy(borrowBook->bookName, strtok(NULL, whitespace));
        strcpy(borrowBook->bookAuthor, strtok(NULL, whitespace));
        strcpy(borrowBook->bookPub, strtok(NULL, whitespace));
        strcpy(borrowBook->borrowTime, strtok(NULL, whitespace));
        strcat(borrowBook->borrowTime, " ");
        strcat(borrowBook->borrowTime, strtok(NULL, whitespace));
        strcpy(borrowBook->returnTime, strtok(NULL, linebreak));
        insert_borrow_book(borrowBook, borrowInfo);
        count++;
    }
    return count;
}

void import_administrator_data_from_file(FILE *ip, AdministratorInfo *administratorInfo) {
    const char whitespace[] = " "; //分隔符
    const char linebreak[] = "\n"; //换行符
    char buffer[200];              //缓冲区
    while (fgets(buffer, N, ip))   //按行读取
    {
        Administrator *administrator = (Administrator *) malloc(sizeof(Administrator));
        unsigned int length = strlen(buffer);
        /*进行字符串分割*/
        strcpy(administrator->administrator_name, strtok(buffer, whitespace));
        strcpy(administrator->password, strtok(NULL, linebreak));
        administratorInfo->administrator[administratorInfo->administratorNum++] = administrator;
    }
}

void write_book_data_to_file(FILE *op) {
    for (int i = 0; i < bookData->booksNum; i++) {
        fprintf(op, "%s %s %s %s %s %s %d %s\n", bookData->books[i]->bookId, bookData->books[i]->bookName,
                bookData->books[i]->bookType, bookData->books[i]->bookAuthor, bookData->books[i]->bookPub,
                bookData->books[i]->bookPages,
                bookData->books[i]->status, bookData->books[i]->borrowTime);
    }
}

void write_reader_data_to_file(FILE *op) {
    for (int i = 0; i < readerData->readersNum; i++) {
        fprintf(op, "%s %s %s %s %s %s %s %d %d\n", readerData->readers[i]->username, readerData->readers[i]->password,
                readerData->readers[i]->readerId, readerData->readers[i]->readerName, readerData->readers[i]->readerSex,
                readerData->readers[i]->readerTel, readerData->readers[i]->email, readerData->readers[i]->borrowedNum,
                readerData->readers[i]->maxBorrowNum);
    }
}

void write_borrow_data_to_file(FILE *op) {
    for (int i = 0; i < borrowData->borrowNum; i++) {
        fprintf(op, "%s %s %s %s %s %s %s\n", borrowData->borrowBook[i]->readerName, borrowData->borrowBook[i]->bookID,
                borrowData->borrowBook[i]->bookName, borrowData->borrowBook[i]->bookAuthor,
                borrowData->borrowBook[i]->bookPub, borrowData->borrowBook[i]->borrowTime,
                borrowData->borrowBook[i]->returnTime);
    }
}

/**
 * @param book
 * @param bookInfo
 * @return 返回状态，1表示图书种类已存在，0表示图书种类新建
 */
int insert_book(Book *book, BookInfo *bookInfo) {
    //检测数据合法性
    int flag = 0;
    for (int i = 0; i < bookTypeData->typeNum; i++) {
        if (strcmp(book->bookType, bookTypeData->bookType[i]) == 0) {
            flag = 1;
            break;
        }
    }
    if (flag == 0) {
        bookTypeData->bookType[bookTypeData->typeNum] = (char *) malloc(sizeof(char) * 30);
        strcpy(bookTypeData->bookType[bookTypeData->typeNum], book->bookType);
        bookTypeData->typeNum++;
    }
    bookInfo->books[bookInfo->booksNum++] = book;
    Quick_sort(bookInfo->books, bookInfo->booksNum);
    return flag;
}

int insert_reader(Reader *reader, ReaderInfo *readerInfo) {
    if (strcmp(reader->readerSex, "man") == 0) {
        strcpy(reader->readerSex, "男");
    }
    if (strcmp(reader->readerSex, "woman") == 0) {
        strcpy(reader->readerSex, "女");
    }
    if (strcmp(reader->readerSex, "男") == 0 || strcmp(reader->readerSex, "女") == 0) {
        readerInfo->readers[readerInfo->readersNum++] = reader;
        Quick_sort_reader(readerInfo->readers, readerInfo->readersNum);
        return true;
    } else {
        return false;
    }
}

void insert_borrow_book(BorrowBook *borrowBook, BorrowInfo *borrowInfo) {
    borrowInfo->borrowBook[borrowInfo->borrowNum++] = borrowBook;
}

void delete_book(char *book_id, BookInfo *bookInfo) {
    int location = search_book(bookInfo->books, bookInfo->booksNum, book_id);//二分查找
    for (int i = location; i < bookInfo->booksNum; i++)//顺序移动
    {
        bookInfo->books[i] = bookInfo->books[i + 1];
    }
    bookInfo->booksNum -= 1;
}

void delete_all_books(BookInfo *bookInfo) {
    bookInfo->booksNum = 0;
}

void delete_reader(char *reader_id, ReaderInfo *readerInfo) {
    int location = search_reader(readerInfo->readers, readerInfo->readersNum, reader_id);//二分查找
    for (int i = location; i < readerInfo->readersNum; i++)//顺序移动
    {
        readerInfo->readers[i] = readerInfo->readers[i + 1];
    }
    readerInfo->readersNum -= 1;
}

void delete_all_readers(ReaderInfo *readerInfo) {
    readerInfo->readersNum = 0;
}

void delete_borrow_book(char *book_id, BorrowInfo *borrowInfo) {
    int location = search_borrow(borrowInfo->borrowBook, borrowInfo->borrowNum, book_id);
    for (int i = location; i < borrowInfo->borrowNum; i++)//顺序移动
    {
        borrowInfo->borrowBook[i] = borrowInfo->borrowBook[i + 1];
    }
    borrowInfo->borrowNum -= 1;
}

void get_current_borrow_time(char current_time[]) {
    time_t timecal_ptr;
    struct tm *tmp_ptr = NULL;
    time(&timecal_ptr);
    tmp_ptr = localtime(&timecal_ptr);
    sprintf(current_time, "%d.%d.%d %d:%d:%d", (1900 + tmp_ptr->tm_year), (1 + tmp_ptr->tm_mon), tmp_ptr->tm_mday,
            tmp_ptr->tm_hour, tmp_ptr->tm_min, tmp_ptr->tm_sec);
}

void get_return_book_time(char return_time[]) {
    time_t timecal_ptr;
    timecal_ptr = timecal_ptr + 30 * 24 * 60 * 60;
    struct tm *tmp_ptr = NULL;
    // time(&timecal_ptr);
    tmp_ptr = localtime(&timecal_ptr);
    sprintf(return_time, "%d.%d.%d %d:%d:%d", (1900 + tmp_ptr->tm_year), (1 + tmp_ptr->tm_mon), tmp_ptr->tm_mday,
            tmp_ptr->tm_hour, tmp_ptr->tm_min, tmp_ptr->tm_sec);
}