package com.training.pass.oauth;

import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.pass.support.common.oauth.OauthBase.*;

import com.training.pass.oauth.OauthBase.QQBusiness;
import com.training.pass.oauth.OauthBase.SinaBusiness;
import com.training.pass.oauth.OauthBase.WeiXinBusiness;

/**
 * Created by yangkai on 2017/5/24.
 */
public enum OauthType {
    QQ(1000, "qq") {
        @Override
        public OauthBase getOauthBusiness(OauthHelper.Builder builder) {
            return new QQBusiness(builder);
        }
    },
    Weibo(1001, "weixin") {
        @Override
        public OauthBase getOauthBusiness(OauthHelper.Builder builder) {
            return new WeiXinBusiness(builder);
        }
    },
    WeiXin(1002, "sina") {
        @Override
        public OauthBase getOauthBusiness(OauthHelper.Builder builder) {
            return new SinaBusiness(builder);
        }
    },
    None(9999, "none") {
        @Override
        public OauthBase getOauthBusiness(OauthHelper.Builder builder) {
            throw new SysException(500, "not support oauth type");
        }
    };

    private Integer code;
    private String value;

    OauthType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer code() {
        return this.code;
    }

    public String value() {
        return this.value;
    }


    @Deprecated
    public abstract OauthBase getOauthBusiness(OauthHelper.Builder builder);

    public static OauthType convert(Integer code) {
        if (code != null) {
            for (OauthType type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
        }

        return None;
    }

    public static OauthType convert(String value) {
        if (value != null) {
            for (OauthType type : values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
        }

        return None;
    }
}
