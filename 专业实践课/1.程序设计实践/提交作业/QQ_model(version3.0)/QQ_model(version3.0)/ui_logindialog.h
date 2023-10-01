/********************************************************************************
** Form generated from reading UI file 'logindialog.ui'
**
** Created by: Qt User Interface Compiler version 5.12.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LOGINDIALOG_H
#define UI_LOGINDIALOG_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>

QT_BEGIN_NAMESPACE

class Ui_LoginDialog
{
public:
    QLabel *label;
    QLabel *label_2;
    QLabel *label_3;
    QLineEdit *adminLineEdit;
    QLineEdit *pwdLineEdit;
    QPushButton *loginPushButton;
    QPushButton *exitPushButton;
    QLabel *label_4;

    void setupUi(QDialog *LoginDialog)
    {
        if (LoginDialog->objectName().isEmpty())
            LoginDialog->setObjectName(QString::fromUtf8("LoginDialog"));
        LoginDialog->resize(480, 339);
        LoginDialog->setSizeGripEnabled(false);
        label = new QLabel(LoginDialog);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(120, 40, 261, 61));
        QFont font;
        font.setPointSize(18);
        label->setFont(font);
        label->setLayoutDirection(Qt::LeftToRight);
        label->setAlignment(Qt::AlignCenter);
        label_2 = new QLabel(LoginDialog);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setGeometry(QRect(90, 140, 61, 31));
        QFont font1;
        font1.setPointSize(14);
        label_2->setFont(font1);
        label_3 = new QLabel(LoginDialog);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setGeometry(QRect(90, 200, 67, 21));
        label_3->setFont(font1);
        adminLineEdit = new QLineEdit(LoginDialog);
        adminLineEdit->setObjectName(QString::fromUtf8("adminLineEdit"));
        adminLineEdit->setGeometry(QRect(160, 140, 191, 41));
        pwdLineEdit = new QLineEdit(LoginDialog);
        pwdLineEdit->setObjectName(QString::fromUtf8("pwdLineEdit"));
        pwdLineEdit->setGeometry(QRect(160, 200, 191, 41));
        pwdLineEdit->setEchoMode(QLineEdit::Password);
        loginPushButton = new QPushButton(LoginDialog);
        loginPushButton->setObjectName(QString::fromUtf8("loginPushButton"));
        loginPushButton->setGeometry(QRect(100, 270, 111, 41));
        QFont font2;
        font2.setPointSize(16);
        loginPushButton->setFont(font2);
        exitPushButton = new QPushButton(LoginDialog);
        exitPushButton->setObjectName(QString::fromUtf8("exitPushButton"));
        exitPushButton->setGeometry(QRect(260, 270, 111, 41));
        exitPushButton->setFont(font2);
        label_4 = new QLabel(LoginDialog);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setGeometry(QRect(0, 0, 481, 341));
        label_4->setPixmap(QPixmap(QString::fromUtf8("elements/login.jpg")));
        label_4->setScaledContents(true);
        label_4->raise();
        label->raise();
        label_2->raise();
        label_3->raise();
        adminLineEdit->raise();
        pwdLineEdit->raise();
        loginPushButton->raise();
        exitPushButton->raise();

        retranslateUi(LoginDialog);

        QMetaObject::connectSlotsByName(LoginDialog);
    } // setupUi

    void retranslateUi(QDialog *LoginDialog)
    {
        LoginDialog->setWindowTitle(QApplication::translate("LoginDialog", "Dialog", nullptr));
        label->setText(QApplication::translate("LoginDialog", "\346\254\242\350\277\216\344\275\277\347\224\250QQ_model", nullptr));
        label_2->setText(QApplication::translate("LoginDialog", "\350\264\246\345\217\267\357\274\232", nullptr));
        label_3->setText(QApplication::translate("LoginDialog", "\345\257\206\347\240\201\357\274\232", nullptr));
        adminLineEdit->setText(QApplication::translate("LoginDialog", "yzs219", nullptr));
        loginPushButton->setText(QApplication::translate("LoginDialog", "\347\231\273\345\275\225", nullptr));
        exitPushButton->setText(QApplication::translate("LoginDialog", "\351\200\200\345\207\272", nullptr));
        label_4->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class LoginDialog: public Ui_LoginDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LOGINDIALOG_H
