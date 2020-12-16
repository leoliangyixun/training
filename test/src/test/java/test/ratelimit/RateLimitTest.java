package test.ratelimit;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2020/4/28
 * @email yangkai@hujiang.com
 * @description
 */
public class RateLimitTest {

    @Test
    public void test_slide_window() {

    }

    @Test
    public void test_token_bucket() {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter();


    }

    public static class TokenBucketRateLimiter {
        //当前桶剩余令牌数量
        private int tokens;
        //桶容量
        private int capacity = 10;
        //生产令牌速率
        private int rate = 10;

        public boolean acquire() {
            return true;
        }

    }

}
