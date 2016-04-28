/**
 * 
 */
package com.traing.spring.bean.callback;

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
public class EmployeeRotationCallback implements Callback<Employee> {

    @Override
    public Employee execute() {
        // TODO Auto-generated method stub
        return null;
    }

/*    @Override
    public Class<String> getClassType() {
        // TODO Auto-generated method stub
        return null;
    }*/
    @Override
    public Class<Employee> getClassType() {
       
        return null;
    }

    @Override
    public Class<Employee> getClassType4Generic() {
        // TODO Auto-generated method stub
        return null;
    }


}
