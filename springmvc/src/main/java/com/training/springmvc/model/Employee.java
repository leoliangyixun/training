/**
 * 
 */
package com.training.springmvc.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
@XmlRootElement(name = "employee")
@XmlType(propOrder = { "name", "age", "company","projects" })
public class Employee implements Serializable {

	private static final long serialVersionUID = 1127159722025777772L;

    private String name;

    private Integer age;

    private Company company;

    private List<Project> projects;
/*	private List<Employee> staffs;*/
	//@XmlJavaTypeAdapter
	//private Map<String, Object> map;
	public Employee() {
		
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    @XmlElement
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
    @XmlElement
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

    @XmlElementWrapper(name="projects")
    @XmlElement(name="project")
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

/*    @XmlElements({
        @XmlElement(name = "staff", type = Employee.class)
    })
    public List<Employee> getStaffs() {
        return staffs;
    }
 
    public void setStaffs(List<Employee> staffs) {
        this.staffs = staffs;
    }*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

/*    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }*/

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
