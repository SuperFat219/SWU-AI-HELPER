//
// Created by god on 2021/12/11.
//
#include "headers/common.h"
#include "headers/data_operations.h"

GdkPixbuf *create_pixbuf(const gchar *filename) {
    GdkPixbuf *pixbuf;
    GError *error = NULL;
    /*
     * 函数gdk_pixbuf_new_from_file() 从一个图片文件中加载图象数据，从而生成一个新的 pixbuf，
     * 至于文件中包含图象的格式，是由系统自动检测的。如果该函数返回值是NULL 的话，程序就会出现错误。
    */
    pixbuf = gdk_pixbuf_new_from_file(filename, &error);
    if (!pixbuf) {
        fprintf(stderr, "%s\n", error->message);
        g_error_free(error);
    }
    return pixbuf;
}

void on_return_button_clicked(GtkWidget *button, gpointer window) {
    gtk_widget_hide(GTK_WIDGET(window));
}

void on_exit_button_clicked(GtkWidget *button) {
    FILE *op1, *op2, *op3;
    op1 = fopen("/home/god/Projects/Book-Management-System/Data/book.txt", "w");
    write_book_data_to_file(op1);
    fclose(op1);
    op2 = fopen("/home/god/Projects/Book-Management-System/Data/readers.txt", "w");
    write_reader_data_to_file(op2);
    fclose(op2);
    op3 = fopen("/home/god/Projects/Book-Management-System/Data/borrow.txt", "w");
    write_borrow_data_to_file(op3);
    fclose(op3);
    gtk_main_quit();
}

int partition(Book *array[], int low, int high) //返回枢轴位置
{
    Book *flag = array[low];
    while (low < high) {
        while (low < high && strcmp(array[high]->bookId, flag->bookId) > 0) {
            high--;
        }
        array[low] = array[high];
        while (low < high && strcmp(array[low]->bookId, flag->bookId) < 0) {
            low++;
        }
        array[high] = array[low];
    }
    array[low] = flag;
    return low;
}

void Qsort(Book *array[], int low, int high) {
    if (low < high) {
        int flag = partition(array, low, high);
        Qsort(array, low, flag - 1);
        Qsort(array, flag + 1, high);
    }
}

void Quick_sort(Book *array[], int length) {
    Qsort(array, 0, length - 1);
}

int partition_reader(Reader *array[], int low, int high) //返回枢轴位置
{
    Reader *flag = array[low];
    while (low < high) {
        while (low < high && strcmp(array[high]->readerId, flag->readerId) > 0) {
            high--;
        }
        array[low] = array[high];
        while (low < high && strcmp(array[low]->readerId, flag->readerId) < 0) {
            low++;
        }
        array[high] = array[low];
    }
    array[low] = flag;
    return low;
}

void Qsort_reader(Reader *array[], int low, int high) {
    if (low < high) {
        int flag = partition_reader(array, low, high);
        Qsort_reader(array, low, flag - 1);
        Qsort_reader(array, flag + 1, high);
    }
}

void Quick_sort_reader(Reader *array[], int length) {
    Qsort_reader(array, 0, length - 1);
}

int search_book(Book *array[], int length, const char *book_id) {
    int low = 0;
    int high = length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        char *midVal = array[mid]->bookId;
        if (strcmp(midVal, book_id) < 0)
            low = mid + 1;
        else if (strcmp(midVal, book_id) > 0)
            high = mid - 1;
        else
            return mid;
    }
    return -1;
}

int search_reader(Reader *array[], int length, const char *reader_id) {
    int low = 0;
    int high = length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        char *midVal = array[mid]->readerId;
        if (strcmp(midVal, reader_id) < 0)
            low = mid + 1;
        else if (strcmp(midVal, reader_id) > 0)
            high = mid - 1;
        else
            return mid;
    }
    return -1;
}

int search_borrow(BorrowBook *array[], int length, const char *book_id) {
    int low = 0;
    int high = length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        char *midVal = array[mid]->bookID;
        if (strcmp(midVal, book_id) < 0)
            low = mid + 1;
        else if (strcmp(midVal, book_id) > 0)
            high = mid - 1;
        else
            return mid;
    }
    return -1;
}

int gbkstrlen(const char *str) {
    if (NULL == str) {
        return 0;
    }
    int len = 0;
    char *p = str;
    while ('\0' != *p) {
        /*中文字符直接跳过2个字符*/
        if (*p < 0 && (*(p + 1) < 0 || *(p + 1) > 63)) { /*中文字符*/
            p = p + 3;
        } else {
            p++;
        }
        len++;
    }
    return len;
}