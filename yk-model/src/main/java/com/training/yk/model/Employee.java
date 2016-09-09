
package com.training.yk.model;
import java.io.Serializable;
public class Employee implements Serializable {


    private static final long serialVersionUID = 7739680836160756009L;

    
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    
    
}