/**
 * 
 */
package com.training.spring.aop;

import com.training.spring.bean.model.Employee;

/**
 * @author yangkai
 *
 */
public interface EmployeeService {
    
    void getEmployeeList() ;
    public Employee findBy(Integer age, String name);
}
