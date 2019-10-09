package com.mingrisoft.thread;

public class SyncTask {

    public static void main(String[] argc) {

        Bank bank = new Bank();

        Thread thread = new Thread(new RunnableTask(bank));
        thread.start();

        Thread thread1 = new Thread(new RunnableTask(bank));
        thread1.start();


    }


    static class RunnableTask implements Runnable {

        Bank bank;

        RunnableTask(Bank bank) {
            this.bank = bank;
        }


        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                bank.addMoney1();
            }
        }
    }


    static class Bank {
        private int money = 100;

        /**
         * 使用线程同步块，我们发现
         * 锁对象应该是独立实例的，所以可以实现,下面是打印的日志
         * <p>
         * 当前账户的余额110
         * 当前账户的余额120
         * 当前账户的余额130
         * 当前账户的余额140
         * 当前账户的余额150
         * 当前账户的余额160
         * 当前账户的余额170
         * 当前账户的余额180
         * 当前账户的余额190
         * 当前账户的余额200
         * 当前账户的余额210
         * 当前账户的余额220
         * 当前账户的余额230
         * 当前账户的余额240
         * 当前账户的余额250
         * 当前账户的余额260
         * 当前账户的余额270
         * 当前账户的余额280
         * 当前账户的余额290
         * 当前账户的余额300
         */
        public synchronized void addMoney() {
            money += 10;
            System.out.print("当前账户的余额" + getAccount() + "\n");
        }

        /**
         * 使用线程同步
         */
        public void addMoney1() {
            synchronized (this) {
                money += 10;
                System.out.print("当前账户的余额" + getAccount() + "\n");
            }
        }


        public int getAccount() {
            return money;
        }
    }

}
