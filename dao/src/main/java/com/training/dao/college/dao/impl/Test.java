package com.training.dao.college.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.yangkai.dao.MyAccountDao;
import com.yangkai.dao.MyUserDao;
import com.yangkai.dao.vo.Account;
import com.yangkai.dao.vo.User;

public class Test {

	public static void main(String[] args) {
		/*
		 * User
		 * */
		MyUserDao userDao=new MyUserDaoJdbcImpl();
		/*
		 * �ö��߳�ģ��һ��,���Ƿ��̰߳�ȫ��
		 * */
		
		
		User user=userDao.findUser(9);
		System.out.println("id:"+user.getId());
		System.out.println("name:"+user.getName());
		System.out.println("sex:"+user.getSex());
		System.out.println("birthday:"+user.getBirthday());
		System.out.println("addr:"+user.getAddr());
		System.out.println("-----------------------------");
//		List<User> users=userDao.findUser("С");
//		System.out.println(users);
//		System.out.println("-----------------------------");

//		User user=new User();
//		user.setName("������");
//		user.setSex("Ů");
//		user.setBirthday(new Date());
//		user.setAddr("̨��");
//		int generatedKey=userDao.addUser(user);
//		System.out.println(generatedKey);
		
		/*
		 * Account
		 * */
		MyAccountDao accountDao=new MyAccountDaoJdbcImpl();
//		Account account=accountDao.findAccount(3);
//		System.out.println("id:"+account.getId());
//		System.out.println("name:"+account.getName());
//		System.out.println("sex:"+account.getBalance());
//		System.out.println("----------------------------");
		
//		for(int i=0;i<1000;i++){
//			new Thread(new TestThreadImpl(userDao,accountDao)).start();
//			//System.out.println("in main():"+new Date().toLocaleString());
//
//		}
//
//		for(int i=0;i<1000;i++){
//			new TestThreadExt(userDao,accountDao).start();
//		}
//		
	}

}
class TestThreadImpl implements Runnable{
	public MyUserDao userDao;
	public MyAccountDao accountDao;
	public TestThreadImpl(MyUserDao userDao,MyAccountDao accountDao){
		this.accountDao=accountDao;
		this.userDao=userDao;
	}
	@Override
	public void run() {
		
//    	for(int i=0;i<100;i++){
//			int k=new Random().nextInt(10)+1;
//			User user=userDao.findUser(k);
//			System.out.println("id:"+user.getId());
//			System.out.println("name:"+user.getName());
//			System.out.println("sex:"+user.getSex());
//			System.out.println("birthday:"+user.getBirthday());
//			System.out.println("addr:"+user.getAddr());
//			System.out.println("-----------------------------");
//		}
//		
//		
//        
//		for(int j=0;j<100;j++){
//			int k=new Random().nextInt(5)+1;
//			Account account=accountDao.findAccount(k);
//			System.out.println("id:"+account.getId());
//			System.out.println("name:"+account.getName());
//			System.out.println("sex:"+account.getBalance());
//			System.out.println("-----------------------------");
//		}
		
		int k=new Random().nextInt(10)+1;
		User user=userDao.findUser(k);
		System.out.println("id:"+user.getId());
		System.out.println("name:"+user.getName());
		System.out.println("sex:"+user.getSex());
		System.out.println("birthday:"+user.getBirthday());
		System.out.println("addr:"+user.getAddr());
		//System.out.println("in run():"+new Date().toLocaleString());
		System.out.println("-----------------------------");
	}
}
class TestThreadExt extends Thread{
	public MyUserDao userDao;
	public MyAccountDao accountDao;
	public TestThreadExt(MyUserDao userDao,MyAccountDao accountDao){
		this.accountDao=accountDao;
		this.userDao=userDao;
	}
	@Override
	public void run() {
		
		
//    	for(int i=0;i<100;i++){
//			int k=new Random().nextInt(10)+1;
//			User user=userDao.findUser(k);
//			System.out.println("id:"+user.getId());
//			System.out.println("name:"+user.getName());
//			System.out.println("sex:"+user.getSex());
//			System.out.println("birthday:"+user.getBirthday());
//			System.out.println("addr:"+user.getAddr());
//			System.out.println("-----------------------------");
//		}
		
		
        
//		for(int j=0;j<100;j++){
//			int k=new Random().nextInt(5)+1;
//			Account account=accountDao.findAccount(k);
//			System.out.println("id:"+account.getId());
//			System.out.println("name:"+account.getName());
//			System.out.println("sex:"+account.getBalance());
//			System.out.println("-----------------------------");
//		}
		
		int k=new Random().nextInt(10)+1;
		User user=userDao.findUser(k);
		System.out.println("id:"+user.getId());
		System.out.println("name:"+user.getName());
		System.out.println("sex:"+user.getSex());
		System.out.println("birthday:"+user.getBirthday());
		System.out.println("addr:"+user.getAddr());
		//System.out.println("in run():"+new Date().toLocaleString());
		System.out.println("-----------------------------");
	}
}
