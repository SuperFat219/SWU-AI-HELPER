//
// Created by god on 2021/12/25.
//

#include "header/common.h"

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

int transform_time_to_double(char *time) {
    char hour[3];
    char minute[3];
    int minutes;
    const char timeBreak[] = ":";
    strcpy(hour, strtok(time, timeBreak));
    strcpy(minute, strtok(NULL, timeBreak));
    minutes = atoi(hour) * 60 + atoi(minute);
    return minutes;
}