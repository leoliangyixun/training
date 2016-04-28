/**
 * 
 */
package com.training.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.springmvc.model.Company;
import com.training.springmvc.model.Employee;
import com.training.springmvc.model.Project;

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
@Controller
@RequestMapping("/test")
public class TestController {
    
    @ResponseBody
    @RequestMapping(value="/save", method= RequestMethod.POST)
    public void save(HttpServletRequest request, @RequestBody Employee employee, String sign) {

       
    }
    
    @ResponseBody
    @RequestMapping(value="/find", method= RequestMethod.GET)
    public Model find(HttpServletRequest request, Model model) {
        Employee e = initData();
        model.addAttribute("employee", e);
        return model;
       
    }

    @RequestMapping(value="/find2", method= RequestMethod.GET)
    public void find2(HttpServletRequest request, Model model) {
        Employee e = initData();
        model.addAttribute("employee", e);
        model.addAttribute("employee2", "employee2");
        model.addAttribute("employee3", e);
    }

    private Employee initData() {
        Employee e = new Employee();
        Company c = new Company();
        Project p = new Project();
        p.setName("Order");
        p.setProjectManager("天雷");
        c.setName("B5M");
        c.setAddress("pudong south road 1118");
        e.setName("jiqingchuan");
        e.setAge(28);
        e.setCompany(c);
        e.setProject(p);
        
        Employee staff1 = new Employee();
        staff1.setName("eryue");
        staff1.setProject(p);
        staff1.setCompany(c);
        Employee staff2 = new Employee();
        staff2.setName("taogengxian");
        staff2.setProject(p);
        staff2.setCompany(c);
        Employee staff3 = new Employee();
        staff3.setName("yutianhe");
        staff3.setProject(p);
        staff3.setCompany(c);
        List<Employee> staffs = new ArrayList<Employee>();
        staffs.add(staff1);
        staffs.add(staff2);
        staffs.add(staff3);
        e.setStaffs(staffs);
        return e;
    }

}
