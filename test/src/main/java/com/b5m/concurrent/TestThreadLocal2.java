package com.b5m.concurrent;

/**
 * Created by izene on 2016/5/18.
 */
public class TestThreadLocal2 {

    public static class Account {

        private ThreadLocal<String> name = new ThreadLocal<String>();

        public Account(String str) {
            this.name.set(str);
            System.out.println("------" + this.name.get() + "------");
        }

        public String getStr() {
            return this.name.get();
        }

        public void setStr(String str) {
            this.name.set(str);
        }
    }

    public static class Task extends Thread{
        private Account account;
        private String name;

        public Task(String name, Account account) {
            super(name);
            this.name = name;
            this.account = account;
        }

        @Override
        public void run() {
            account.setStr(name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + account.getStr());
        }
    }

    public static  void main (String[] args) {
        Account account = new Account("初始名");
        for (int i = 0; i < 10; i++) {
            new Task("线程" + i, account).start();
        }


    }
}
