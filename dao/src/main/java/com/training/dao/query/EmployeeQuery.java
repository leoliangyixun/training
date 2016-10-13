/**
 * 
 */
package com.training.dao.query;

import com.training.dao.model.Employee;

import java.util.Date;

/**
 * @author yangkai
 *
 */
public interface EmployeeQuery extends Query<EmployeeQuery, Employee> {

    EmployeeQuery nameLike(String nameLike);

    EmployeeQuery level(String level);

    EmployeeQuery birthdayBefore(Date birthdayBefore);

    EmployeeQuery birthdayAfter(Date birthdayAfter);

}
