#ifndef LOGINDIALOG_H
#define LOGINDIALOG_H

#include <QDialog>
#include <QSqlQuery>  //查询MYSQL的库
#include <QMessageBox>
#include <QCryptographicHash>

namespace Ui {
class LoginDialog;
}

class LoginDialog : public QDialog
{
    Q_OBJECT

public:
    explicit LoginDialog(QWidget *parent = 0);
    ~LoginDialog();
    QString strToMd5(QString str);

private slots:
    void on_loginPushButton_clicked();  //登录按钮单击事件槽
    void on_exitPushButton_clicked();   //退出按钮单击事件槽

private:
    Ui::LoginDialog *ui;
};

#endif // LOGINDIALOG_H
