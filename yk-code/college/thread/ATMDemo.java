package com.yangkai.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ATMDemo {

	public ATMDemo() {

	}

	public static void main(String[] args) {
		ATM atm = new ATM();
		new DespositAccount(atm, "��Ǯ�߼�", 800.00f).start();
		new DrawAccount(atm, "ȡǮ����", 800.00f).start();
		new DespositAccount(atm, "��Ǯ�߱�", 800.00f).start();
		new DrawAccount(atm, "ȡǮ�߶�", 800.00f).start();

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
				System.out.println(count + " . " + name + " �Ѿ�ȡ�� " + money
						+ " Ԫ ");
				System.out.println("ATM�����: " + balance + " Ԫ ");
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
			 * System.out.println(count + " . " + name + " �Ѿ�ȡ�� " + money +
			 * " Ԫ "); System.out.println("ATM�����: " + balance + " Ԫ ");
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
				System.out.println(count + " . " + name + " �Ѿ����� " + money
						+ " Ԫ ");
				System.out.println("ATM�����: " + balance + " Ԫ ");
				cond.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

			/*
			 * lock.lock(); try { if (!isDraw) { balance += money; isDraw =
			 * true; count++; System.out.println(count + " . " + name + " �Ѿ����� "
			 * + money + " Ԫ "); System.out.println("ATM�����: " + balance +
			 * " Ԫ "); cond.signalAll(); } cond.await(); } catch
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
