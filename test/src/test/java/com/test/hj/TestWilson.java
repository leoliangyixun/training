package com.test.hj;

import com.hujiang.basic.framework.core.util.JsonUtil;

import com.google.common.collect.Lists;

import lombok.Data;

import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author yangkai
 * @date 2020/9/17
 * @email yangkai@hujiang.com
 * @description
 */
public class TestWilson {
    @Data
    public static class AbsMessageApplicationDto {

        private Integer absMsgAppId;
        private String msgAppName;
        private String msgAppAvatar;
        private String msgAppDescription;
        private Integer topLevel;
        private Integer status;
        private Date createdAt;

        public AbsMessageApplicationDto(String msgAppName, Integer topLevel) {
            this.msgAppName = msgAppName;
            this.topLevel = topLevel;
        }
    }

    @Test
    public void testAppSortByTopLevel() {
        List<AbsMessageApplicationDto> apps = Lists.newArrayList(
            new AbsMessageApplicationDto("系统消息", 200),
            new AbsMessageApplicationDto("开心词场", 0),
            new AbsMessageApplicationDto("沪江网校", 1),
            new AbsMessageApplicationDto("小D", 1),
            new AbsMessageApplicationDto("CCTalk", 0)
        );
        System.out.println("before sort:" + apps);
        apps.sort((o1, o2) -> {
            if (o2.getTopLevel() > o1.getTopLevel()) {
                return -1;
            } else if (o2.getTopLevel() < o1.getTopLevel()) {
                return 1;
            } else {
                return 0;
            }
        });
        System.out.println("after sort:" + apps);
    }

}
