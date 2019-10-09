package com.mingrisoft.thread;

import javax.swing.JTextArea;

public class Transfer implements Runnable {
    private Bank bank;
    private JTextArea textArea;

    public Transfer(Bank bank, JTextArea textArea) {// ���ù��췽����ʼ������
        this.bank = bank;
        this.textArea = textArea;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {// ѭ��10�����˻���Ǯ
            bank.deposit(10);// ���˻�����10��Ǯ
            String text = textArea.getText();// ����ı�������
            textArea.setText(text + "�˻�������ǣ�" + bank.getAccount() + "\n");
        }
    }
}
