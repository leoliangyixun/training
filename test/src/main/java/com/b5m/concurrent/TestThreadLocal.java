package com.b5m.concurrent;

/**
 * Created by izene on 2016/5/18.
 */
public class TestThreadLocal {

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

        public Task(String name, Account account) {
            super(name);
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0 ; i < 10; i++) {
                if (i == 6) {
                    account.setStr(getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(account.getStr() + " 账户的i值: " + i);
            }
        }
    }

    public static  void main (String[] args) {
        Account account = new Account("初始名");
        new Task("线程甲",account).start();
        new Task("线程乙",account).start();

    }
}
