package com.yangkai.sql.reflection;

import com.yangkai.sql.domain.Student;
import com.yangkai.sql.domain.User;



public class Client {
	public static void main(String[] args) {
		/*********************************************************/
		ClientServiceImpl cs=new ClientServiceImpl();
		String sql="SELECT s_id AS studentId, s_name AS studentName, s_sex AS studentSex, s_major AS studentMajor, s_dep AS studentDep "
			+"FROM student WHERE s_id=?";
		Student s=cs.find(Student.class, sql, 2);
		System.out.println(s.getStudentId());
		System.out.println(s.getStudentName());
		System.out.println(s.getStudentSex());
		System.out.println(s.getStudentMajor());
		System.out.println(s.getStudentDep());
		/*********************************************************/
		
	}

}
