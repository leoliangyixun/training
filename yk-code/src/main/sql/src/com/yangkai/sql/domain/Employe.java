package com.yangkai.sql.domain;

import java.util.Date;

public class Employe {

	private int employeId;
	private String employeName;
	private String employeJob;
	private float employeSal;
	private Date employeHiredate;
	public Employe() {	
	}
	public int getEmployeId() {
		return employeId;
	}
	public void setEmployeId(int employeId) {
		this.employeId = employeId;
	}
	public String getEmployeName() {
		return employeName;
	}
	public void setEmployeName(String employeName) {
		this.employeName = employeName;
	}
	public String getEmployeJob() {
		return employeJob;
	}
	public void setEmployeJob(String employeJob) {
		this.employeJob = employeJob;
	}
	public float getEmployeSal() {
		return employeSal;
	}
	public void setEmployeSal(float employeSal) {
		this.employeSal = employeSal;
	}
	public Date getEmployeHiredate() {
		return employeHiredate;
	}
	public void setEmployeHiredate(Date employeHiredate) {
		this.employeHiredate = employeHiredate;
	}

}
