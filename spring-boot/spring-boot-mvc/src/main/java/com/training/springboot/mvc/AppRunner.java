package com.training.springboot.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = "com.training.springboot.mvc")
public class AppRunner implements CommandLineRunner {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AppRunner.class, args);

/*        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    }

    @Override
    public void run(String... strings) throws Exception {
        long start = System.nanoTime();
        String product = "yk_iphone8_plus";
        Stream<CompletableFuture<Void>> stream = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")));


        //stream.collect(Collectors.toSet());
        //一定要结束stream才会有结果，哪怕stream.map(e -> e.join())也没用
        stream.map(e -> e.join()).collect(Collectors.toSet());
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }


    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll"),
        new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static class Shop {

        private final String name;
        private final Random random;

        public Shop(String name) {
            this.name = name;
            random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
        }

        public String getPrice(String product) {
            double price = calculatePrice(product);
            Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
            return name + ":" + price + ":" + code;
        }

        public double calculatePrice(String product) {
            Util.delay();
            return Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
        }

        public String getName() {
            return name;
        }
    }

    public static class Discount {

        public enum Code {
            NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

            private final int percentage;

            Code(int percentage) {
                this.percentage = percentage;
            }
        }

        public static String applyDiscount(Quote quote) {
            return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
        }

        private static double apply(double price, Code code) {
            Util.delay();
            return Util.format(price * (100 - code.percentage) / 100);
        }

    }

    public static class Quote {

        private final String shopName;
        private final double price;
        private final Discount.Code discountCode;

        public Quote(String shopName, double price, Discount.Code discountCode) {
            this.shopName = shopName;
            this.price = price;
            this.discountCode = discountCode;
        }

        public static Quote parse(String s) {
            String[] split = s.split(":");
            String shopName = split[0];
            double price = Double.parseDouble(split[1]);
            Discount.Code discountCode = Discount.Code.valueOf(split[2]);
            return new Quote(shopName, price, discountCode);
        }

        public String getShopName() {
            return shopName;
        }

        public double getPrice() {
            return price;
        }

        public Discount.Code getDiscountCode() {
            return discountCode;
        }
    }

    public static class Util {
        private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        public static void delay() {
            int delay = 1000;
            //int delay = 500 + RANDOM.nextInt(2000);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public static double format(double number) {
            synchronized (formatter) {
                return new Double(formatter.format(number));
            }
        }
    }
}
