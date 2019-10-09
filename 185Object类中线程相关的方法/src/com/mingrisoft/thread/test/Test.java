package com.mingrisoft.thread.test;

public class Test {


    private volatile int a = 0;

    private final int max = 200;


    public class MyDemo1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Test.this) {

                    a++;

//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }


                    System.out.println("生产了" + a);

                    if (a >= max) {
                        try {
                            Test.this.wait();//将生产者线程进行等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (a >= 1) {
                            Test.this.notifyAll();//可以继续进行消费了 等于将在上面的线程加入了就绪状态，当生产者一旦脱离锁，就可以拿到这把锁进行消费
                        }
                    }
                }
            }
        }
    }


    public class MyDemo2 implements Runnable {

        @Override
        public void run() {

            while (true) {
                synchronized (Test.this) {

//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    if (a == 0) {
                        try {
                            Test.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //通知该线程启动
                        a--;
                        if (a < max) {
                            Test.this.notifyAll();//可以继续进行生产了
                        }

                        System.out.println("消费了" + a);

                    }
                }
            }
        }
    }


    public static void main(String[] argc) {

        Test test = new Test();

        MyDemo1 myDemo1 = test.new MyDemo1();

        MyDemo2 myDemo2 = test.new MyDemo2();


        new Thread(myDemo1).start();

        new Thread(myDemo2).start();


    }

}
