#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QMessageBox>
#include <QFileDialog>
#include <QBuffer>
#include "opencv2/opencv.hpp"  //Opencv文件包含
#include <QSqlDatabase>        //MySQL数据库类
#include <QSqlTableModel>      //MySQL表模型类
#include <QSqlQuery>           //MySQL查询库类
#include <QTime>
#include <QPixmap>             //图像处理类库

using namespace cv;  //opencv命名空间

namespace Ui { class MainWindow; }

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    void initMainWindow();  //界面初始化方法
    void onTableSelectChange(int row);  //用户在好友列表网格选取好友时进行好友信息表单更新
    void showFriendPhoto();  //显示好友头像
    void loadPreFriends();  //“删除好友”页：对应分组加载好友列表
    void onPreNameComboBoxChange();  //“删除好友”页：改选好友时联动显示好友信息
private slots:
    void on_FriendsTableView_3_clicked(const QModelIndex &index); //“好友信息”页：联动显示好友信息
    void on_preGroupComboBox_currentIndexChanged(int index); //“删除好友”页：根据类别对应加载对应好友列表
    void on_preNameComboBox_currentIndexChanged(int index);//“删除好友”页：选择好友时联动显示好友信息
    void on_DeletePushButton_clicked();  //删除好友操作
    void on_newUploadPushButton_clicked();  //上传头像
    void on_NewPutinPushButton_clicked(); //添加好友
    void on_ChangePushButton_clicked();//修改备注
    void on_SearchPushButton_clicked();//搜索好友
private:
    Ui::MainWindow *ui;
    QImage myPicImg;  //保存好友头像
    QSqlTableModel *friends_model;  //访问数据库好友信息视图的模型
};
static bool createMysqlConn()  //访问MySQL数据库的静态方法
{
    QSqlDatabase sqldb=QSqlDatabase::addDatabase("QMYSQL");
    sqldb.setHostName("127.0.0.1");  //主机名
    sqldb.setDatabaseName("friendsList");  //数据库名称
    sqldb.setPort(3306); //接口名
    sqldb.setUserName("root"); //用户名
    sqldb.setPassword("20020619");//登录密码
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
