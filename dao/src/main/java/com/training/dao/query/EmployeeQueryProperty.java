package com.training.dao.query;

/**
 * Created by yangkai on 16/10/6.
 */
public class EmployeeQueryProperty implements QueryProperty {

    public static final EmployeeQueryProperty NAME = new EmployeeQueryProperty("name");
    public static final EmployeeQueryProperty LEVEL = new EmployeeQueryProperty("level");

    private String name;

    private EmployeeQueryProperty(String name) {
        this.name  = name;
    }

    @Override
    public String getName() {
        return null;
    }
}
