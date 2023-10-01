/*各窗口调用的公共函数文件*/
#ifndef __COMMON_FUNC_H__
#define __COMMON_FUNC_H__

#include <gtk/gtk.h>

GdkPixbuf *create_pixbuf(const gchar * filename);
char to_upper(char p);//小写转大写
//void set_widget_font_size(GtkWidget *widget, int size, gboolean is_button);//改变控件字体大小

#endif // __COMMON_FUNC_H__