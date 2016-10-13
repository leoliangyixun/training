/**
 * 
 */
package com.training.dao.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author yangkai
 *
 */
public class Employee extends PersistentEntity {

    private static final long serialVersionUID = 1L;
    private String name;
    private Integer age;
    private String gender;
    private Department dept;
    private String level;


    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String namespace() {
        return "com.training.dao.model.Employee";
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static enum Level {
        P1("P1","培训生"),
        P2("P2","实习生"),
        P3("P3","助理级"),
        P4("P4","初级"),
        P5("P5","中级"),
        P6("P6","高级"),
        P7("P7","资深"),
        P8("P8","专家"),
        P9("P9","资深专家"),
        P10("P10","研究员"),
        M1("M1", "主管"),
        M2("M2", "经理"),
        M3("M3", "高级经理"),
        M4("M4", "总监"),
        M5("M5", "高级总监");

        private String code;
        private String descr;

        private Level(String code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public String code() {
            return code;
        }

        public String descr() {
            return descr;
        }


        public static Level getLevel(String code){
            if (code == null) return null;
            for(Level level : values()){
                if(level.code.equalsIgnoreCase(code)){
                    return level;
                }
            }
            return null;
        }
    }

}
