package com.yangkai.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ATMDemo {

	public ATMDemo() {

	}

	public static void main(String[] args) {
		ATM atm = new ATM();
		new DespositAccount(atm, "存钱者甲", 800.00f).start();
		new DrawAccount(atm, "取钱者乙", 800.00f).start();
		new DespositAccount(atm, "存钱者丙", 800.00f).start();
		new DrawAccount(atm, "取钱者丁", 800.00f).start();

	}

	public static class ATM {
		private Lock lock = new ReentrantLock();
		private Condition cond = lock.newCondition();
		private float balance;
		private boolean isDraw = false;
		private int count = 0;

		public void draw(String name, float money) {

			lock.lock();
			try {
				while (!isDraw) {
					cond.await();
				}
				balance -= money;
				isDraw = false;
				count++;
				System.out.println(count + " . " + name + " 已经取走 " + money
						+ " 元 ");
				System.out.println("ATM机余额: " + balance + " 元 ");
				cond.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

			/*
			 * lock.lock(); try { if (isDraw) { balance -= money; isDraw =
			 * false; count++;
			 * 
			 * System.out.println(count + " . " + name + " 已经取走 " + money +
			 * " 元 "); System.out.println("ATM机余额: " + balance + " 元 ");
			 * cond.signalAll(); } cond.await();
			 * 
			 * } catch (InterruptedException e) { e.printStackTrace(); } finally
			 * { lock.unlock(); }
			 */

		}

		public void desposit(String name, float money) {

			lock.lock();
			try {
				while (isDraw) {
					cond.await();
				}
				balance += money;
				isDraw = true;
				count++;
				System.out.println(count + " . " + name + " 已经存入 " + money
						+ " 元 ");
				System.out.println("ATM机余额: " + balance + " 元 ");
				cond.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

			/*
			 * lock.lock(); try { if (!isDraw) { balance += money; isDraw =
			 * true; count++; System.out.println(count + " . " + name + " 已经存入 "
			 * + money + " 元 "); System.out.println("ATM机余额: " + balance +
			 * " 元 "); cond.signalAll(); } cond.await(); } catch
			 * (InterruptedException e) { e.printStackTrace(); } finally {
			 * lock.unlock(); }
			 */
		}
	}

	public static class DrawAccount extends Thread {
		private ATM atm;
		private String name;
		private float money;

		public DrawAccount(ATM atm, String name, float money) {
			this.atm = atm;
			this.name = name;
			this.money = money;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				atm.draw(name, money);
			}
		}
	}

	public static class DespositAccount extends Thread {
		private ATM atm;
		private String name;
		private float money;

		public DespositAccount(ATM atm, String name, float money) {
			this.atm = atm;
			this.name = name;
			this.money = money;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				atm.desposit(name, money);
			}
		}
	}

}
