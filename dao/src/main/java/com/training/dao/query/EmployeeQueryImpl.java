/**
 * 
 */
package com.training.dao.query;

import java.util.Date;
import java.util.List;

/**
 * @author yangkai
 *
 */
public class EmployeeQueryImpl extends AbstractQuery implements EmployeeQuery {

    private String nameLike;
    private String level;
    private Date birthdayBefore;
    private Date birthdayAfter;

    public EmployeeQueryImpl() {}

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


}
