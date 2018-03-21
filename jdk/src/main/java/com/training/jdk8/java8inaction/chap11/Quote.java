package com.training.jdk8.java8inaction.chap11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private String shopName;
    private double price;
    private int code;
    
    public static Quote parse(String s) {
        String[] split = s.split(":");
        return new Quote(split[0], Double.parseDouble(split[1]), Integer.parseInt(split[2]));
    }
    
    public static Quote parse(Integer s) {

      return null;
  }
}
