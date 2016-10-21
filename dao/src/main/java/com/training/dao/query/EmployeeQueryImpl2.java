/**
 * 
 */
package com.training.dao.query;

import com.training.dao.model.Employee;

import java.util.Date;
import java.util.List;

/**
 * @author yangkai
 *
 */
public class EmployeeQueryImpl2 implements EmployeeQuery {

    private String nameLike;
    private String level;
    private Date birthdayBefore;
    private Date birthdayAfter;

    protected QueryBuilder queryBuilder;

    public EmployeeQueryImpl2() {}

    @Override
    public EmployeeQuery nameLike(String nameLike) {
        this.nameLike = nameLike;
        return  this;
    }

    @Override
    public EmployeeQuery level(String level) {
        this.level = level;
        return this;
    }

    @Override
    public EmployeeQuery birthdayBefore(Date birthdayBefore) {
        this.birthdayBefore = birthdayBefore;
        return this;
    }

    @Override
    public EmployeeQuery birthdayAfter(Date birthdayAfter){
        this.birthdayAfter = birthdayAfter;
        return this;
    }


    @Override
    public EmployeeQuery asc() {
        return null;
    }

    @Override
    public EmployeeQuery desc() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Employee single() {
        return null;
    }

    @Override
    public List<Employee> list() {
        return null;
    }

    @Override
    public List<Employee> listPage(int offset, int limit) {
        return null;
    }
}
