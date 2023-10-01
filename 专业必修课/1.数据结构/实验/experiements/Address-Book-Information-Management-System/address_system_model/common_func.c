#include "common_func.h"

GdkPixbuf *create_pixbuf(const gchar * filename)
{
    GdkPixbuf *pixbuf;
    GError *error = NULL;
    /*
     * 函数gdk_pixbuf_new_from_file() 从一个图片文件中加载图象数据，从而生成一个新的 pixbuf，
     * 至于文件中包含图象的格式，是由系统自动检测的。如果该函数返回值是NULL 的话，程序就会出现错误。
    */
    pixbuf = gdk_pixbuf_new_from_file(filename, &error);
    if(!pixbuf) {
        fprintf(stderr, "%s\n", error->message);
        g_error_free(error);
    }
    return pixbuf;
}
char to_upper(char p)//小写转大写
{
    if (p>='a' && p<='z')
    {
        return (p-32);
    }
    return p;
}
/* void set_widget_font_size(GtkWidget *widget, int size, gboolean is_button)//改变控件字体大小
{
	GtkWidget *labelChild;  
	PangoFontDescription *font;  
	gint fontSize = size;  
 
	font = pango_font_description_from_string("Sans");          //"Sans"字体名   
	pango_font_description_set_size(font, fontSize*PANGO_SCALE);//设置字体大小   
 
	if(is_button){
		labelChild = gtk_bin_get_child(GTK_BIN(widget));//取出GtkButton里的label  
	}else{
		labelChild = widget;
	}
 
	//设置label的字体，这样这个GtkButton上面显示的字体就变了
	//gtk_widget_modify_font(GTK_WIDGET(labelChild), font);
    gtk_widget_override_font(GTK_WIDGET(labelChild),font);
	pango_font_description_free(font);
} */