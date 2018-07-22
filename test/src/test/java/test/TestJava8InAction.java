package test;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangkai
 * @date 2018/5/5
 * @email yangkai@hujiang.com
 * @description
 */
public class TestJava8InAction {


    public static class Shop {

        private final String name;
        private final Random random;

        public Shop(String name) {
            this.name = name;
            random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
        }

        public String getPrice(String product) {
            System.out.println(Thread.currentThread().getName() + " shop: " + this.name);
            THREAD_LOCAL.set(Thread.currentThread().getName() + " shop: " + this.name);

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
            System.out.println(THREAD_LOCAL.get());
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

        @Override
        public String toString() {
            return "Quote{" +
                "shopName='" + shopName + '\'' +
                ", price=" + price +
                ", discountCode=" + discountCode +
                '}';
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

    private static final ThreadLocal<String> THREAD_LOCAL =  new ThreadLocal<>();

    private static final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll"),
        new Shop("ShopEasy"));

    private static final Executor executor = Executors.newFixedThreadPool(shops.size(), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    private  static final AtomicInteger index = new AtomicInteger();

    @Test
    public void test_chap11_findPricesStream_1() {
        long start = System.nanoTime();
        String product = "yk_iphone8_plus";
        Stream<CompletableFuture<Void>> stream = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")));
        List<Void> list = stream.map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
        //串型输出
    }

    @Test
    public void test_chap11_findPricesStream_1_1() {
        long start = System.nanoTime();
        String product = "yk_iphone8_plus";
        List<String> list =  shops.parallelStream()
            .map(shop ->shop.getPrice(product))
            .map(price -> Quote.parse(price))
            .map(quote ->Discount.applyDiscount(quote))
            .collect(Collectors.toList());

        System.out.println(list);
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
        //串型输出
    }

    @Test
    public void test_chap11_findPricesStream_2() throws Exception {
        long start = System.nanoTime();
        String product = "yk_iphone8_plus";
        Stream<CompletableFuture<Void>> stream = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")));
        stream.collect(Collectors.toList());
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
        Thread.sleep(50000);
    }

    @Test
    public void test_chap11_findPricesStream_3() {
        long start = System.nanoTime();
        String product = "yk_iphone8_plus";
        CompletableFuture[] futures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .map(f -> f.thenAccept(s -> System.out.println(THREAD_LOCAL.get() + "<------>" + s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    @Test
    public void test_chap11_findPricesStream_4() {
        long start = System.nanoTime();
        String product = "yk_iphone8_plus";
        CompletableFuture[] futures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(f -> f.thenAccept(s -> System.out.println(THREAD_LOCAL.get() + "<------>" + s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }
}
