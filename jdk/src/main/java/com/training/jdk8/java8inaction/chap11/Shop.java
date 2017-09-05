package com.training.jdk8.java8inaction.chap11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private String name;
    
    public String getPrice(String product) {
        return String.format("%s:%.2f:%s", product, 8.88f, 20);
    }
}
