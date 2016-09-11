/**
 * 
 */
package com.training.springmvc.core;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.training.springmvc.model.Employee;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年5月3日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年5月3日       jiqingchuan          1.0             Why & What is modified
 */
public class DefaultXmlAdapter extends XmlAdapter<String, Employee> {

    @Override
    public Employee unmarshal(String v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String marshal(Employee v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
