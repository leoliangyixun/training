/**
 * 
 */
package com.training.pass.oauth;

/**
 * @author yangkai
 *
 */
public class Main {
    public void test(){
        String redirectUrl = null;
        switch (thirdType) {
        case QQ:
            redirectUrl  = OauthHelper.builder()
                    .callbackUrl(callbackUrl)
                    .oauthType(thirdType)
                    .state(state)
                    .display("")
                    .parameter("")
                    .configKey("PC")
                    .build()
                    .authorizeUrl_v2(state, );
            break;
        case WEIBO:
            redirectUrl = OauthHelper.builder()
                    .callbackUrl(callbackUrl)
                    .oauthType(thirdType)
                    .state(state)
                    .display("default")
                    .parameter("")
                    .configKey("PC")
                    .build()
                    .authorizeUrl_v2();
            break;
        case WECHAT:
            redirectUrl = OauthHelper.builder()
                    .callbackUrl(callbackUrl)
                    .oauthType(thirdType)
                    .state(state)
                    .parameter("")
                    .configKey(isMicromessenger = ua != null ? ua.contains("micromessenger") ? "Wechat" + StringHelper.getHostStart(host) : "PC" + StringHelper.getHostStart(host))
                    .build()
                    .authorizeUrl_v2();
            break;
    }
    }
}
