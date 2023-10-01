#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    /*setwindowicon*/
    //setWindowIcon(QIcon(QStringLiteral(":/background/mainlogo")));
    ui->setupUi(this);
    initMainWindow(); //执行初始化
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::initMainWindow()  //对系统主窗体进行初始化
{
    //Qt::WindowFlags flags = this->windowFlags();
    //this->setWindowFlags(flags|Qt::WindowStaysOnTopHint);
    ui->stackedWidget->setCurrentIndex(1);  //置于好友管理页面
    ui->Friend_Operation->setCurrentIndex(0);     //“添加好友”页置于前面
    QSqlQueryModel *GroupModel = new QSqlQueryModel(this);  //好友类别模型数据
    GroupModel->setQuery("select Group_Name from `Group`");
    //QStringList strings;
    //QSqlQuery query("SELECT Group_Name FROM Group");
    //while (query.next()) {
      //        QString group_name = query.value(0).toString();
        //      strings.append(group_name);
          //}
    //ui->newGroupComboBox->addItems(strings);
    friends_model=new QSqlTableModel(this);
    friends_model->setTable("friends_inf");
    friends_model->select();
    ui->FriendsTableView->setModel(friends_model);   //初始化好友列表
    ui->FriendsTableView_2->setModel(friends_model);
    ui->FriendsTableView_3->setModel(friends_model);
    ui->newGroupComboBox->setModel(GroupModel);  //初始化下拉框
    ui->preGroupComboBox->setModel(GroupModel);
    ui->fixedGroupComboBox->setModel(GroupModel);
    loadPreFriends();
}
void MainWindow::onTableSelectChange(int row)  //“好友信息”页：用户选择信息时进行表单更新
{
    int r=1;
    if (row!=0) r=ui->FriendsTableView_3->currentIndex().row();
    QModelIndex index;
    index=friends_model->index(r,0);  //ID
    ui->fixedIDLabel->setText(friends_model->data(index).toString());
    index=friends_model->index(r,1);  //name
    ui->fixedNameLabel->setText(friends_model->data(index).toString());
    index=friends_model->index(r,2);  //manual
    ui->fixedmanualLabel->setText(friends_model->data(index).toString());
    index=friends_model->index(r,3);  //signature
    ui->fixedsignatureLabel->setText(friends_model->data(index).toString());
    index=friends_model->index(r,4);  //reference
    ui->changableReferenceLineEdit->setText(friends_model->data(index).toString());
    showFriendPhoto();
    QSqlQuery query;
    query.exec(QString("select Group_Name from `Group` where Group_ID=(select Group_ID from FriendsList where Name='%1')").arg(ui->fixedNameLabel->text()));
    query.next();
    ui->fixedGroupComboBox->setCurrentText(query.value(0).toString());  //实现分组信息联动
}
void MainWindow::showFriendPhoto()  //显示头像
{
    QPixmap photo;
    QModelIndex index;
    QSqlQueryModel *pictureModel =new QSqlQueryModel(this);
    QString name=ui->fixedNameLabel->text();
    pictureModel->setQuery("select Picture from FriendsList where Name='"+name+"'");
    index=pictureModel->index(0,0);
    photo.loadFromData(pictureModel->data(index).toByteArray(),"JPG");
    ui->fixedpictureLabel->setPixmap(photo);
}
void MainWindow::loadPreFriends()  //“删除好友”页：对应分组加载好友列表
{
    QSqlQueryModel *friends_name_model=new QSqlQueryModel(this);
    friends_name_model->setQuery(QString("select Name from FriendsList where Group_ID=(select Group_ID from `Group` where Group_Name='%1')").arg(ui->preGroupComboBox->currentText()));
    ui->preNameComboBox->setModel(friends_name_model);
    onPreNameComboBoxChange();
}
void MainWindow::onPreNameComboBoxChange()  //“删除好友”页：改选好友时联动显示好友信息
{
    QSqlQueryModel *PreFriendsModel=new QSqlQueryModel(this);
    QString name=ui->preNameComboBox->currentText();
    PreFriendsModel->setQuery("select User_ID,manual,signature,reference,Picture from FriendsList where Name='"+name+"'");
    QModelIndex index;
    index=PreFriendsModel->index(0,0);  //账号
    ui->preIDLabel->setText(PreFriendsModel->data(index).toString());
    index=PreFriendsModel->index(0,1);  //性别
    ui->preManualLabel->setText(PreFriendsModel->data(index).toString());
    index=PreFriendsModel->index(0,2);  //个性签名
    ui->preSignatureLabel->setText(PreFriendsModel->data(index).toString());
    index=PreFriendsModel->index(0,3);  //备注
    ui->preReferenceLineEdit->setText(PreFriendsModel->data(index).toString());
    //获取头像
    QPixmap photo;
    index=PreFriendsModel->index(0,4);
    photo.loadFromData(PreFriendsModel->data(index).toByteArray(),"JPG");
    ui->prePictureLabel->setPixmap(photo);
}
void MainWindow::on_FriendsTableView_3_clicked(const QModelIndex &index)  //“好友信息”页：联动显示好友信息
{
    onTableSelectChange(1);
}
void MainWindow::on_preGroupComboBox_currentIndexChanged(int index)   //“删除好友”页：根据类别对应加载对应好友列表
{
    loadPreFriends();
}
void MainWindow::on_preNameComboBox_currentIndexChanged(int index)  //“删除好友”页：选择好友时联动显示好友信息
{
    onPreNameComboBoxChange();
}
void MainWindow::on_DeletePushButton_clicked()  //“删除好友”页：删除好友操作
{
    QSqlQuery query;
    query.exec(QString("delete from FriendsList where Name='%1'").arg(ui->preNameComboBox->currentText()));
    ui->preManualLabel->setText("");
    ui->preSignatureLabel->setText("");
    ui->preIDLabel->setText("");
    ui->preReferenceLineEdit->setText("");
    ui->prePictureLabel->clear();
    friends_model->setTable("friends_inf");
    friends_model->select();
    ui->FriendsTableView->setModel(friends_model);
    ui->FriendsTableView_2->setModel(friends_model);
    ui->FriendsTableView_3->setModel(friends_model);
}
void MainWindow::on_newUploadPushButton_clicked()  //上传头像
{
    QString picturename=QFileDialog::getOpenFileName(this,"选择头像",".","Image File(*.png *.jpg *.jpeg *.bmp)");
    if (picturename.isEmpty()) return;
    myPicImg.load(picturename);
    ui->NewPictureLabel->setPixmap(QPixmap::fromImage(myPicImg));
}
void MainWindow::on_NewPutinPushButton_clicked() //添加好友
{
    QSqlQuery query;
    query.exec(QString("select Group_ID from `Group` where Group_Name='%1'").arg(ui->newGroupComboBox->currentText()));
    query.next();
    int Group_ID=query.value(0).toInt();
    QString name =ui->newNameLineEdit->text();
    int User_ID=ui->newIDLineEdit->text().toInt();
    QString maunal=ui->newmanualLineEdit->text();
    QString signature=ui->newSignatureLineEdit->text();
    QString reference=ui->newRefenrenceLineEdit->text();
    query.exec(QString("insert into FriendsList(User_ID,Group_ID,Name,manual,signature,reference,Picture)values(%1,%2,'%3','%4','%5','%6',NULL)").arg(User_ID).arg(Group_ID).arg(name).arg(maunal).arg(signature).arg(reference));
    //插入照片
    QByteArray picdata;
    QBuffer buffer(&picdata);
    buffer.open(QIODevice::WriteOnly);
    myPicImg.save(&buffer,"JPG");
    QVariant var(picdata);
    QString sqlstr="update FriendsList set Picture=? where Name='"+name+"'";
    query.prepare(sqlstr);
    query.addBindValue(var);
    if (!query.exec())
    {
        QMessageBox::information(0,QObject::tr("提示"),"添加失败");
    }
    else
    {
        QMessageBox::information(0,QObject::tr("提示"),"添加成功！");
    }
    /*ui->newIDLineEdit->setText("");
    ui->newNameLineEdit->setText("");
    ui->newmanualLineEdit->setText("");
    ui->newSignatureLineEdit->setText("");
    ui->newRefenrenceLineEdit->setText("");
    ui->NewPictureLabel->clear();*/
    friends_model->setTable("friends_inf");   //刷新网格信息
    friends_model->select();
    ui->FriendsTableView->setModel(friends_model);
    ui->FriendsTableView_2->setModel(friends_model);
    ui->FriendsTableView_3->setModel(friends_model);
}
void MainWindow::on_ChangePushButton_clicked()
{
    QSqlQuery query;
    QString new_reference=ui->changableReferenceLineEdit->text();
    query.exec(QString("update FriendsList set reference='%1' where Name='%2'").arg(new_reference).arg(ui->fixedNameLabel->text()));
    if (!query.exec())
    {
        QMessageBox::information(0,QObject::tr("提示"),"修改失败");
    }
    else
    {
        QMessageBox::information(0,QObject::tr("提示"),"修改成功！");
    }
    friends_model->setTable("friends_inf");   //刷新网格信息
    friends_model->select();
    ui->FriendsTableView->setModel(friends_model);
    ui->FriendsTableView_2->setModel(friends_model);
    ui->FriendsTableView_3->setModel(friends_model);
}
void MainWindow::on_SearchPushButton_clicked()
{
    QSqlQuery query;
    query.exec(QString("select User_ID,Group_ID,Name,manual,signature,reference,Picture from FriendsList where User_ID='%1'").arg(ui->SearchLineEdit->text().toInt()));
    if (query.exec())
    {
        query.next();
        ui->fixedIDLabel->setText(query.value(0).toString());
        ui->fixedNameLabel->setText(query.value(2).toString());
        ui->fixedmanualLabel->setText(query.value(3).toString());
        ui->fixedsignatureLabel->setText(query.value(4).toString());
        ui->changableReferenceLineEdit->setText(query.value(5).toString());
        showFriendPhoto();
        query.exec(QString("select Group_Name from `Group` where Group_ID=(select Group_ID from FriendsList where Name='%1')").arg(ui->fixedNameLabel->text()));
        query.next();
        ui->fixedGroupComboBox->setCurrentText(query.value(0).toString());
    }
    else
    {
        QMessageBox::information(0,QObject::tr("提示"),"未检测到此好友");
    }
}
