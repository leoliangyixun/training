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
    
    private 

    @Override
    public Employee getObject() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return false;
    }

}
