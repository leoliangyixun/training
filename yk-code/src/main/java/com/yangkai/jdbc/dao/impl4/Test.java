package org.yangkai.jdbc.dao.impl4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.yangkai.jdbc.dao.CommonDAO;
import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.Article;
import org.yangkai.jdbc.domain.Employee;
import org.yangkai.jdbc.domain.User;

public class Test {

	public static void main(String[] args) {

		CommonDAO dao=new CommonInheritanceDaoImpl();
		
		User user=dao.findById(User.class, 7);
		System.out.println("id:"+user.getId());
		System.out.println("name:"+user.getName());
		System.out.println("sex:"+user.getSex());
		System.out.println("birthday:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getBirthday()));
		System.out.println("birthday:"+ DateFormat.getDateTimeInstance().format(user.getBirthday()));
		System.out.println("addr:"+user.getAddr());
		System.out.println("-----------------------------");
		
		Account account=dao.findById(Account.class, 4);
		System.out.println("id:"+account.getId());
		System.out.println("name:"+account.getName());
		System.out.println("balance:"+account.getBalance());
		System.out.println("-----------------------------");
		
		Employee employee=dao.findById(Employee.class, 3);
		System.out.println("id:"+employee.getId());
		System.out.println("name:"+employee.getName());
		System.out.println("job:"+employee.getJob());
		System.out.println("sal:"+employee.getSal());
		System.out.println("hiredate:"+new SimpleDateFormat("yyyy-MM-dd").format(employee.getHiredate()));
		System.out.println("-----------------------------");
		/*
		Article article=dao.findById(Article.class, 1);
		System.out.println("id:"+article.getArtid());
		System.out.println("username:"+article.getUsername());
		System.out.println("title:"+article.getArttitle());
		System.out.println("content:"+article.getArtcontent());
		System.out.println("date:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getArtdate()));
        */
		List<User> list=dao.findAll(User.class, 7);
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
		
	}

}
