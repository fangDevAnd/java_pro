package com.mingrisoft.thread;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread userThread = new Thread(new Worker()); // 创建用户线程
        Thread daemonThread = new Thread(new Timer()); // 创建守护线程
        daemonThread.setDaemon(true); // 设置守护线程
        userThread.start(); // 启动用户线程
        daemonThread.start(); // 启动守护线程
    }
}
