/**
 * 
 */
package com.training.spring.aop.annotation.demo;


import com.training.spring.aop.annotation.Color;

/**
 * @author yangkai
 *
 */
public class Apple {
    @Color(value = "red")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public static void main(String[] args) {
        Apple apple = new Apple();
        System.out.println(apple.getColor());
    }

}
