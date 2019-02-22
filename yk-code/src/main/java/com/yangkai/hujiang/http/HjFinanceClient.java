/**
 * 
 */
package com.yangkai.hujiang.http;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hujiang.pass.integration.sdk.HjFinanceSDK;
import com.hujiang.pass.integration.sdk.HjFinanceSDK.KeyValuePair;

/**
 * @author yangkai
 *
 */
@Component
@Scope(value = "prototype")
public class HjFinanceClient {
    @Value("${finance.api.url}")
    private String baseUrl;

    //不可重复的header
    private Set<KeyValuePair> headers1 = Sets.newHashSet();

    //可重复的header
    private List<KeyValuePair> headers2 = Lists.newArrayList();


    HjFinanceClient header(String key, String value) {
        return this;
    }

    HjFinanceClient addHeader(String key, String value) {
        return this;
    }

    HjFinanceSDK getHjFinanceSDK() {
        return null;
    }
}
