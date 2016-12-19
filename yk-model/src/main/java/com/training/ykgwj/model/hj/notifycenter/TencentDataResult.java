package com.training.ykgwj.model.hj.notifycenter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yangkai on 2016/12/16.
 */
public class TencentDataResult {

    @Data
    @NoArgsConstructor
    public static class Token {
        private String access_token;
        private long expires_in;
    }

    @Data
    @NoArgsConstructor
    public static class MessageSendResult {
        private long errcode;
        private String errmsg;
        private long msgid;
    }
}
