package com.keriwinyang.training.hystrix.main;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author yangkai
 * @date 2018/9/10
 * @email yangkai@hujiang.com
 * @description
 */
public class HystrixDemo {

    public static class HelloHystrix extends HystrixCommand<String> {

        protected String name;

        public HelloHystrix(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("HelloService"));
            this.name = name;
        }

        @Override
        protected String run() throws Exception {
            return String.format("Hello %s!", name);
        }
    }

}
