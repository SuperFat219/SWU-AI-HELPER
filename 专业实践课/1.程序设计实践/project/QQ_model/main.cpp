#include "mainwindow.h"
#include "logindialog.h"
#include <QProcess>
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    if (!createMysqlConn())
    {
        QProcess process;
        process.start("/usr/sbin/mysqld.exe");
        if (!createMysqlConn()) return 1;
    }
    LoginDialog logindlg; //登录对话框
    if (logindlg.exec()==QDialog::Accepted)
    {
        MainWindow w;
        w.show();
        return a.exec();
    }
    else
    {
        return 0;
    }
}
