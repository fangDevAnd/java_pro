package com.mingrisoft.thread.ececutors;

import java.util.concurrent.*;

public class Start1 {


    public static void main(String[] argc) {

        Start1 start1 = new Start1();

//        start1.demo1();

//        start1.demo2();
        start1.demo3();
//        start1.demo4();
    }


    public void demo1() {


        class SimpleThreadFactory implements ThreadFactory {
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }

        //上面是一个最简单的实现

        Executor executor = Executors.newFixedThreadPool(100, Executors.defaultThreadFactory());

        for (int i = 0; i < 1000; i++) {
            ((ExecutorService) executor).submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }

    public void demo2() {


        Executor executor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());


        for (int i = 0; i < 1000; i++) {
            ((ExecutorService) executor).submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }


    }

    public void demo3() {

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

        ScheduledFuture scheduledFuture = ses.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, TimeUnit.MILLISECONDS);


    }


    /**
     * 创建一个
     *
     */
    public void demo4() {


    }


}
