package com.mingrisoft.thread;

public class Bank {
    private int account = 100;// 余额100

    public void deposit(int money) {// ���˻���Ǯ�ķ���
        account += money;
    }

    public int getAccount() {// ����˻����ķ���
        return account;
    }
}
