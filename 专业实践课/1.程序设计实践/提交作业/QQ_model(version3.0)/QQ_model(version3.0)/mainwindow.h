#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QMessageBox>
#include <QFileDialog>
#include <QBuffer>
#include "opencv2/opencv.hpp"
#include <QSqlDatabase>
#include <QSqlTableModel>
#include <QSqlQuery>
#include <QTime>
#include <QPixmap>

using namespace cv;

namespace Ui { class MainWindow; }

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    void initMainWindow();
    void onTableSelectChange(int row);  //用户变更信息时进行表单更新
    void showFriendPhoto();  //显示头像
    void loadPreFriends();  //加载好友列表
    void onPreNameComboBoxChange();  //表单联动
private slots:
    void on_FriendsTableView_3_clicked(const QModelIndex &index);
    void on_preGroupComboBox_currentIndexChanged(int index);
    void on_preNameComboBox_currentIndexChanged(int index);
    void on_DeletePushButton_clicked();  //删除好友操作
    void on_newUploadPushButton_clicked();  //上传头像
    void on_NewPutinPushButton_clicked(); //添加好友
    void on_ChangePushButton_clicked();//修改备注
    void on_SearchPushButton_clicked();//搜索好友
private:
    Ui::MainWindow *ui;
    QImage myPicImg;
    QSqlTableModel *friends_model;
};
static bool createMysqlConn()
{
    QSqlDatabase sqldb=QSqlDatabase::addDatabase("QMYSQL");
    sqldb.setHostName("127.0.0.1");
    sqldb.setDatabaseName("friendsList");
    sqldb.setPort(3306);
    sqldb.setUserName("root");
    sqldb.setPassword("20020619");
    if (!sqldb.open())
    {
        QMessageBox::critical(0,QObject::tr("后台数据库连接失败"),"无法创建连接！请检查故障后重新连接！",QMessageBox::Cancel);
        return false;
    }
    //写入图片数据
    /*char num='0';
    QSqlQuery query (sqldb);
    for (int i=1;i<=9;i=i+1)
    {
        num=num+1;
        QString s="a.jpeg";
        s[0]=num;
        QString t="10000";
        t[4]=num;
        QString photoPath="/home/god/QQ_model/QQ_photos/"+s;
        QFile photoFile(photoPath);
        if (photoFile.exists())
        {
            //存入数据库
            QByteArray picdata;
            photoFile.open(QIODevice::ReadOnly);
            picdata=photoFile.readAll();
            photoFile.close();
            QVariant var(picdata);
            QString sqlstr = "update FriendsList set Picture =? where User_ID="+t;
            query.prepare(sqlstr);
            query.addBindValue(var);
        }
        if (!query.exec())
        {
            QMessageBox::information(0,QObject::tr("提示"),"照片写入失败！");
        }
        else
        {
            QMessageBox::information(0,QObject::tr("提示"),"照片上传成功！");
        }
    }

    QString photoPath="/home/god/QQ_model/QQ_photos/10.jpeg";  //对10号照片处理
    QFile photoFile(photoPath);
    if (photoFile.exists())
    {
        //存入数据库
        QByteArray picdata;
        photoFile.open(QIODevice::ReadOnly);
        picdata=photoFile.readAll();
        photoFile.close();
        QVariant var(picdata);
        QString sqlstr = "update FriendsList set Picture =? where User_ID=10010";
        query.prepare(sqlstr);
        query.addBindValue(var);
    }
    if (!query.exec())
    {
        QMessageBox::information(0,QObject::tr("提示"),"照片写入失败！");
    }
    else
    {
        QMessageBox::information(0,QObject::tr("提示"),"照片上传成功！");
    }
    */
    return true;
}
#endif // MAINWINDOW_H
