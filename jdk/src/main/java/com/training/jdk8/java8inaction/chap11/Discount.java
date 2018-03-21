package com.training.jdk8.java8inaction.chap11;

public class Discount {
    public String applyDiscount(Quote quete) {
        return quete.getShopName() +" price is " + quete.getPrice() * ((100 - quete.getCode()) /100);
    }
}
