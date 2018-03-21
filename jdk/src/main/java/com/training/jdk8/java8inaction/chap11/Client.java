package com.training.jdk8.java8inaction.chap11;

import java.util.List;

import com.google.common.collect.Lists;

public class Client {
    
    public void findPrices(String product) {
        List<Shop> shops = Lists.newArrayList(new Shop("jd"),new Shop("tmall"),new Shop("dangdang"));
        shops.stream()
        .map(shop -> shop.getPrice(product)).map(Quote::parse);

    }
    
    public static void main(String[] args) {
        Client client = new Client();
        client.findPrices("java8 in action");
    }
}
