package com.mingrisoft.thread;

public class JoinThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new EmergencyThread());
        thread.start();
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前主线程" + i + "运行");
            try {
                /**
                 * 根据运行的结果，我们发现join只会调用一次  ，那一次就是用来将线程加入到当前线程中，之后不在
                 *进行加入
                 *
                 *
                 * 当前主线程1运行
                 * 紧急线程1运行
                 * 紧急线程2运行
                 * 紧急线程3运行
                 * 紧急线程4运行
                 * 紧急线程5运行
                 * 当前主线程2运行
                 * 当前主线程3运行
                 * 当前主线程4运行
                 * 当前主线程5运行
                 *
                 *
                 */
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}