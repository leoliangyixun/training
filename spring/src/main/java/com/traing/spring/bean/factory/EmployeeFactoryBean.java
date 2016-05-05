/**
 * 
 */
package com.traing.spring.bean.factory;

import org.springframework.beans.factory.FactoryBean;

import com.traing.spring.bean.model.Employee;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年4月28日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月28日       jiqingchuan          1.0             Why & What is modified
 */
public class EmployeeFactoryBean implements FactoryBean<Employee> {
    
    private String employeeInfo;

    @Override
    public Employee getObject() throws Exception {
        Employee e = new Employee();
        String[] infos = this.employeeInfo.split(",");
        e.setName(infos[0]);
        e.setAge(Integer.parseInt(infos[1]));
        return e;
    }

    @Override
    public Class<?> getObjectType() {
        return Employee.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(String employeeInfo) {
        this.employeeInfo = employeeInfo;
    }
    

}
