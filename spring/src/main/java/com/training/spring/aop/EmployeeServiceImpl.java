/**
 * 
 */
package com.training.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.training.spring.bean.model.Employee;
import com.training.spring.test.User;

/**
 * @author yangkai
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * 
     */
    public EmployeeServiceImpl() {

    }

    @OptLogger
    @Override
    public void getEmployeeList() {
        System.out.println("......");

    }
    
    @OptLogger
    @Override
    public Employee findBy(Integer age, String name) {
        System.out.println("age" + age + ", name" + name);
        Employee e = new Employee();
        e.setAge(age);
        e.setName(name);
        return e;
        
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        EmployeeService employeeService = (EmployeeService) ctx.getBean("employeeServiceImpl");

        employeeService.findBy(18, "yk");
    }

}
