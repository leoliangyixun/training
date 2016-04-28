/**
 * 
 */
package com.training.springmvc.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年4月27日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月27日       jiqingchuan          1.0             Why & What is modified
 */
@XmlRootElement(name="e")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1127159722025777772L;
	private String name;
	private Integer age;
	private Company company;
	private Project project;
	private List<Employee> staffs;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the name
	 */
	@XmlElement
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	@XmlElement
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return the company
	 */
	@XmlElement
	public Company getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
    /**
     * @return the project
     */
	@XmlElement
    public Project getProject() {
        return project;
    }
    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }
    /**
     * @return the staffs
     */
  
    public List<Employee> getStaffs() {
        return staffs;
    }
    /**
     * @param staffs the staffs to set
     */
    @XmlElements({
        @XmlElement(name = "staffs", type = Employee.class)
    })
    public void setStaffs(List<Employee> staffs) {
        this.staffs = staffs;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
	

}
